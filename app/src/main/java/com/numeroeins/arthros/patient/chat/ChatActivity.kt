package com.numeroeins.arthros.patient.chat
import com.numeroeins.arthros.patient.R
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.numeroeins.arthros.patient.activity.BaseActivity
import com.numeroeins.arthros.patient.databinding.ActivityChatBinding
import com.numeroeins.arthros.patient.servermanager.UrlManager.Companion.MAIN_URL

import com.numeroeins.arthros.patient.servermanager.request.CommonValueModel
import com.numeroeins.arthros.patient.utility.*
import io.reactivex.disposables.Disposable
import java.util.*

class ChatActivity : BaseActivity(), View.OnClickListener , ChatAdapter.OnChatItemClickListener {
    private lateinit var activityChatBinding: ActivityChatBinding
    private val chatItemArrayList: ArrayList<ChatMessagesList> = ArrayList<ChatMessagesList>()
    private var userPreference: UserPreference? = null
    private var chatAdapter: ChatAdapter? = null
    private var chatNode: String = ""
    private var chatNode_1: String = ""
    private var chatNode_2: String = ""
    private var senderId: String = ""
    private var senderName: String = ""
    private var senderImage: String = ""
    private var receiverName: String = ""
    private var receiverImage: String = ""
    private var jobId: String = ""
    private var ownerId: String = ""
    private var ownerName: String = ""
    private var receiverId: String = ""
    private var ref: DatabaseReference? = null
    lateinit var valueEventListener: ValueEventListener
    private var pfbd: ParseFirebaseData? = null


    var  isOfferExits: Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userPreference = UserPreference.getInstance(this)
        activityChatBinding = DataBindingUtil.setContentView(this, R.layout.activity_chat)
        initView()
    }

    private fun initView() {
        activityChatBinding.sendButton.setOnClickListener(this)
       // activityChatBinding.addOfferImg.setOnClickListener(this)
        activityChatBinding.topNavBar.backIcon.setOnClickListener(this)


        if (!TextUtils.isEmpty(userPreference!!.token)) {
            senderId = userPreference!!.token!!
        }else{
            senderId ="2"
        }

        if (!TextUtils.isEmpty(userPreference!!.fullName)) {
            senderName = userPreference!!.fullName!!
        }else{
            senderName ="Amit"
        }

        if (!TextUtils.isEmpty(userPreference!!.imageUrl))
        {
            senderImage = MAIN_URL + userPreference!!.imageUrl!!
        }else{
            senderImage =""
        }


    val bundle = intent.extras
    if (bundle != null)
    {
        if (bundle.getString(PARAM_RECEIVER_ID) != null) {
            receiverId = bundle.getString(PARAM_RECEIVER_ID)!!
        }
        if (bundle.getString(PARAM_RECEIVER_NAME) != null) {
            receiverName = bundle.getString(PARAM_RECEIVER_NAME)!!
            activityChatBinding.topNavBar.navTitle.text = receiverName
        }
        if (bundle.getString(PARAM_RECEIVER_IMAGE) != null) {
            receiverImage = bundle.getString(PARAM_RECEIVER_IMAGE)!!

           /* activityChatBinding.topNavBar.userImage.visibility=View.VISIBLE
            Glide.with(this).load( receiverImage)
                .placeholder(R.drawable.profile_icon)
                .into( activityChatBinding.topNavBar.userImage)*/
        }


    }
        initializedDataBase()
}

private fun initializedDataBase() {
    pfbd = ParseFirebaseData(this)
    chatNode_1 = "$senderId-$receiverId"
    chatNode_2 = "$receiverId-$senderId"

    valueEventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            if (dataSnapshot.hasChild(chatNode_1)) {
                chatNode = chatNode_1
            } else if (dataSnapshot.hasChild(chatNode_2)) {
                chatNode = chatNode_2
            } else {
                chatNode = chatNode_1
            }
            Log.d(LOG_TAG, "chatNode ghgh  "+chatNode)

            if(dataSnapshot.hasChild(chatNode)){
                chatItemArrayList.clear()
                chatItemArrayList.addAll(pfbd!!.getMessagesForSingleUser(dataSnapshot.child(chatNode)))

                //Here we are traversing all the messages and mark all received messages read
                for (data in dataSnapshot.child(chatNode).children) {
                    if (data.child(NODE_RECEIVER_ID).value.toString() == receiverId + "") {
                        data.child(NODE_IS_READ).ref.runTransaction(object :
                            Transaction.Handler {
                            override fun doTransaction(mutableData: MutableData): Transaction.Result {
                                mutableData.value = true
                                return Transaction.success(mutableData)
                            }
                            override fun onComplete(databaseError: DatabaseError?, b: Boolean, dataSnapshot: DataSnapshot?) {
                            }
                        })
                    }
                }

             /*   for (i in 0..chatItemArrayList.size - 1) {
                    if(!TextUtils.isEmpty( chatItemArrayList[i].price)) {
                        if(!TextUtils.isEmpty( chatItemArrayList[i].offerStatus)) {
                            if(chatItemArrayList[i].offerStatus.equals("1")){
                                activityChatBinding.addOfferImg.visibility=View.GONE
                            }
                            isOfferExits=false
                        } else{
                            isOfferExits=true
                        }
                    }
                }*/
                activityChatBinding.chatRv.setHasFixedSize(true)
                activityChatBinding.chatRv.layoutManager = LinearLayoutManager(this@ChatActivity, LinearLayoutManager.VERTICAL, false)
                chatAdapter = ChatAdapter(this@ChatActivity, chatItemArrayList)
                activityChatBinding.chatRv.adapter = chatAdapter
                chatAdapter!!.setOnItemClickListener(this@ChatActivity)
                chatAdapter!!.notifyDataSetChanged()
                if (chatItemArrayList.size > 0) {
                    activityChatBinding.chatRv.smoothScrollToPosition(chatItemArrayList.size - 1)
                }

            }

        }

        override fun onCancelled(databaseError: DatabaseError) {
            showSnackBar(activityChatBinding.chatEdt, resources.getString(R.string.error_could_not_connect))
        }
    }
    ref = FirebaseDatabase.getInstance().getReference(MESSAGE_CHILD)
    ref!!.addValueEventListener(valueEventListener)
}


override fun onClick(view: View?) {
    when (view?.id) {
        R.id.backIcon -> {
            finish()
        }
        /*  R.id.addOfferImg -> {
            if(!isOfferExits){
                openOfferDialog()
            }else{
                showSnackBar(activityChatBinding.chatEdt, resources.getString(R.string.error_offer_status))
            }

        }*/
        R.id.sendButton -> {
            if (!TextUtils.isEmpty(activityChatBinding.chatEdt.text.toString().trim())) {
                val hm = HashMap<String, Any>()
                hm.clear()
                hm[NODE_TEXT] = activityChatBinding.chatEdt.text.toString()
                hm[NODE_TIMESTAMP] = System.currentTimeMillis().toString()
                hm[NODE_RECEIVER_ID] = receiverId
                hm[NODE_RECEIVER_NAME] = receiverName
                hm[NODE_RECEIVER_PHOTO] = receiverImage
                hm[NODE_SENDER_ID] = senderId
                hm[NODE_SENDER_NAME] = senderName
                hm[NODE_SENDER_PHOTO] = senderImage
                hm[NODE_IS_READ] = false
                ref!!.child(chatNode).push().setValue(hm)
                hideKeyboard()
                activityChatBinding.chatEdt.setText("")
            } else {
                // showSnackBar(activityChatBinding.chatEdt, resources.getString(R.string.error_account_mobile_number))
            }
        }
    }
}

private fun hideKeyboard() {
    val view = this.currentFocus
    if (view != null) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

/*var dialog: Dialog? = null
var price: Int = 0
fun openOfferDialog() {

    dialog = Dialog(this)
    dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    dialog!!.setContentView(R.layout.dailog_offer)

    val layoutParams = WindowManager.LayoutParams()
    layoutParams.copyFrom(dialog!!.window!!.attributes)
    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
    dialog!!.window!!.attributes = layoutParams
    val priceTxt = dialog!!.findViewById(R.id.priceTxt) as EditText
    val offerDescriptionEdt = dialog!!.findViewById(R.id.offerDescriptionEdt) as EditText


    val cancelImg = dialog!!.findViewById(R.id.cancelImg) as ImageView
    cancelImg.setOnClickListener(View.OnClickListener {
        dialog!!.dismiss()
    })

    val submitTxt = dialog!!.findViewById(R.id.submitTxt) as TextView
    submitTxt.setOnClickListener(View.OnClickListener {
        if (TextUtils.isEmpty(priceTxt.text.toString().trim())) {
            showLongToast(resources.getString(R.string.error_price))
        } else if (TextUtils.isEmpty(offerDescriptionEdt.text.toString().trim())) {
            showLongToast(resources.getString(R.string.error_description))
        } else {
            dialog!!.dismiss()
            val hm = HashMap<String, Any>()
            hm.clear()
            hm[NODE_IMAGE] = ""
            hm[NODE_TEXT] = activityChatBinding.chatEdt.text.toString()
            hm[NODE_TIMESTAMP] = System.currentTimeMillis().toString()
            hm[NODE_RECEIVER_ID] = receiverId
            hm[NODE_RECEIVER_NAME] = receiverName
            hm[NODE_RECEIVER_PHOTO] = receiverImage
            hm[NODE_SENDER_ID] = senderId
            hm[NODE_SENDER_NAME] = senderName
            hm[NODE_SENDER_PHOTO] = senderImage
            hm[NODE_IS_READ] = false
            hm[NODE_PRICE] = priceTxt.text.toString()
            hm[NODE_DESCRIPTION] = offerDescriptionEdt.text.toString()
            hm[NODE_OFFER_STATUS] = ""
            hm[NODE_OFFER_ID] = ""
            hm[NODE_OWNER_ID] = ownerId
            hm[NODE_OWNER_NAME] = ownerName
            hm[NODE_JOB_IDS] = jobId
            ref!!.child(chatNode).push().setValue(hm)
        }

    })



    dialog!!.show()
}*/

override fun onSuccess(result: String?, apiName: String?, disposable: Disposable?, commonModel: CommonValueModel?) {
    super.onSuccess(result, apiName, disposable, commonModel)
    closeLoader()
    when (apiName) {
      /*  UrlManager.ACCEPT_OFFER -> {
            val responseLoginModel: SuccessModel? = APIClient.gsonAsConvert.fromJson(result, SuccessModel::class.java)
            if (responseLoginModel != null) {
                if (responseLoginModel.status.equals(STATUS_SUCCESS)){
                    jobStatus(commonModel!!.chatNode, commonModel.key!!,commonModel.status,"1")
                    openAcceptDialog(commonModel.price!!)
                }else {
                    showSnackBar(activityChatBinding.sendButton, responseLoginModel.error)
                }
            }
        }
        UrlManager.REVIEWS_RATING -> {
            val responseLoginModel: SuccessModel? = APIClient.gsonAsConvert.fromJson(result, SuccessModel::class.java)
            if (responseLoginModel != null) {
                if (responseLoginModel.status.equals(STATUS_SUCCESS)){
                    ratingDialog!!.dismiss()
                    showSnackBar(activityChatBinding.sendButton, responseLoginModel.data)
                }else {
                    showSnackBar(activityChatBinding.sendButton, responseLoginModel.error)
                }
            }
        }*/
    }
}

override fun onFailure(message: String?, apiName: String?, disposable: Disposable?, commonModel: CommonValueModel?) {
    super.onFailure(message, apiName, disposable, commonModel)
    closeLoader()
}


/*
var acceptDialog: Dialog? = null
private fun openAcceptDialog(price:String) {
    acceptDialog = Dialog(this)
    acceptDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
    acceptDialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    acceptDialog!!.setContentView(R.layout.dailog_accept)
    val layoutParams = WindowManager.LayoutParams()
    layoutParams.copyFrom(acceptDialog!!.window!!.attributes)
    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
    acceptDialog!!.window!!.attributes = layoutParams
    val priceTxt = acceptDialog!!.findViewById(R.id.priceTxt) as TextView
    priceTxt.text = price
    val sellerNameTxt = acceptDialog!!.findViewById(R.id.sellerNameTxt) as TextView
    sellerNameTxt.text = ownerName
    val submitTxt = acceptDialog!!.findViewById(R.id.submitTxt) as TextView
    val cancelImg = acceptDialog!!.findViewById(R.id.cancelImg) as ImageView
    cancelImg.setOnClickListener(View.OnClickListener {
        acceptDialog!!.dismiss()
    })

    submitTxt.setOnClickListener(View.OnClickListener {
        acceptDialog!!.dismiss()
        openRatingDialog()


    })

    acceptDialog!!.show()
}




var ratingDialog: Dialog? = null
private fun openRatingDialog() {
    ratingDialog = Dialog(this)
    ratingDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
    ratingDialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    ratingDialog!!.setContentView(R.layout.dailog_rating)
    val layoutParams = WindowManager.LayoutParams()
    layoutParams.copyFrom(ratingDialog!!.window!!.attributes)
    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
    ratingDialog!!.window!!.attributes = layoutParams

    val ratingBar = ratingDialog!!.findViewById(R.id.ratingBar) as CustomRatingBar
    val descriptionEdt = ratingDialog!!.findViewById(R.id.descriptionEdt) as EditText
    val submitTxt = ratingDialog!!.findViewById(R.id.submitTxt) as TextView
    val cancelImg = ratingDialog!!.findViewById(R.id.cancelImg) as ImageView
    cancelImg.setOnClickListener(View.OnClickListener {
        if (ratingBar.score==0.0f) {
            showLongToast(resources.getString(R.string.error_rating))
        }else if (TextUtils.isEmpty(descriptionEdt.text.toString().trim())) {
            showLongToast(resources.getString(R.string.error_description))
        }else {
            ratingDialog!!.dismiss()

        }
    })

    submitTxt.setOnClickListener(View.OnClickListener {
        val postRequestModel = PostRequestModel()
        postRequestModel.seller= ownerId
        postRequestModel.rating = ratingBar.score.toString()
        postRequestModel.feedback = descriptionEdt.text.toString()
        showLoader(resources.getString(R.string.please_wait))
        val commonModel = CommonValueModel()
        postApiCall(applicationContext, UrlManager.REVIEWS_RATING, postRequestModel, commonModel)
    })

    ratingDialog!!.show()
}
*/

override fun onItemClickListener(position: Int, status: String) {
    when(status){
       /* ACCEPT_STATUS->{
            val postRequestModel = PostRequestModel()
            postRequestModel.soldAtPrice= chatItemArrayList[position].price!!
            postRequestModel.isAccepted = true
            postRequestModel.itemId = chatItemArrayList[position].jobId!!.replace("J_*","")
            showLoader(resources.getString(R.string.please_wait))
            val commonModel = CommonValueModel()
            commonModel.key=chatItemArrayList[position].key!!
            commonModel.status=status
            commonModel.chatNode=chatNode
            commonModel.price=chatItemArrayList[position].price!!
            postApiCall(applicationContext, UrlManager.ACCEPT_OFFER, postRequestModel, commonModel)
        }
        REJECT_STATUS->{
            jobStatus(chatNode, chatItemArrayList[position].key!!,status,"")
        }*/
    }

}

}