package com.numeroeins.arthros.patient.chat

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.databinding.AdapterChatListBinding
import com.numeroeins.arthros.patient.utility.ACCEPT_STATUS
import com.numeroeins.arthros.patient.utility.REJECT_STATUS
import com.numeroeins.arthros.patient.utility.UserPreference
import com.numeroeins.arthros.patient.utility.Utility

import java.util.ArrayList

class ChatAdapter(activity: Activity, var dataList: ArrayList<ChatMessagesList>) : RecyclerView.Adapter<ChatAdapter.ListViewHolder>() {

    var activity: Context = activity
    var MyApp: UserPreference
    var userId: String
    var utility: Utility? = null
    init {
        utility= Utility()

        MyApp = UserPreference.getInstance(activity)!!
        userId = MyApp.user_id!!
    }
    class ListViewHolder(var listBinding: AdapterChatListBinding) : RecyclerView.ViewHolder(listBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val listBinding = DataBindingUtil.
        inflate<AdapterChatListBinding>(LayoutInflater.from(parent.context), R.layout.adapter_chat_list, parent, false)
        return ListViewHolder(listBinding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

       // holder.listBinding.yardNameTxt.text = dataList[position].jobName


        val singleItem: ChatMessagesList = dataList!![position]
       // holder.listBinding.offerStatus.visibility = View.GONE
        if ("2" == singleItem.senderId) {
            holder.listBinding.receiverLinLay.visibility = View.GONE
            holder.listBinding.senderLinLay.visibility = View.VISIBLE


            if (!TextUtils.isEmpty(singleItem.text)) {
                holder.listBinding.senderChatTxt.text = singleItem.text!!.trim()
            }
            holder.listBinding.senderTimeTxt.text = utility!!.dateToTimeFormat(singleItem.readableTime)
          /*  if(!TextUtils.isEmpty(singleItem.price)){
                holder.listBinding.senderOfferLinLay.visibility = View.VISIBLE
                holder.listBinding.senderChatTxt.visibility = View.GONE
                holder.listBinding.offerSenderPrice.text = singleItem.price
                holder.listBinding.descSenderText.text = singleItem.description
                holder.listBinding.offerSenderTimeTxt.text = utility!!.dateToTimeFormat(singleItem.readableTime)
                if(!TextUtils.isEmpty(singleItem.offerStatus)){
                    holder.listBinding.offerStatus.visibility = View.VISIBLE
                    when(singleItem.offerStatus){
                        ACCEPT_STATUS->{
                            holder.listBinding.offerStatus.text = "OFFER ACCEPT"
                            holder.listBinding.offerStatus.setTextColor(ContextCompat.getColor(activity,
                                R.color.colorPrimaryDark))
                        }
                        REJECT_STATUS->{
                            holder.listBinding.offerStatus.text = "OFFER REJECT"
                            holder.listBinding.offerStatus.setTextColor(ContextCompat.getColor(activity,R.color.white))
                        }
                    }
                }else{

                }

            }else{
                holder.listBinding.senderTimeTxt.text = utility!!.dateToTimeFormat(singleItem.readableTime)
                holder.listBinding.senderChatTxt.visibility = View.VISIBLE
            }*/
        } else {
            holder.listBinding.receiverLinLay.visibility = View.VISIBLE
            holder.listBinding.senderLinLay.visibility = View.GONE


            if (!TextUtils.isEmpty(singleItem.text)) {
                holder.listBinding.receiverChatTxt.text = singleItem.text!!.trim()

            }
            holder.listBinding.receiverTimeTxt.text = utility!!.dateToTimeFormat(singleItem.readableTime)

          /*  if(!TextUtils.isEmpty(singleItem.price)){
                holder.listBinding.offerReceiverLinLay.visibility = View.VISIBLE
                holder.listBinding.receiverChatTxt.visibility = View.GONE



            }else{
                holder.listBinding.receiverTimeTxt.text = utility!!.dateToTimeFormat(singleItem.readableTime)
                holder.listBinding.receiverChatTxt.visibility = View.VISIBLE
            }*/


        }



    }


    private var mItemClickListener: OnChatItemClickListener? = null

    fun setOnItemClickListener(mItemClickListener: OnChatItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface OnChatItemClickListener {
        fun onItemClickListener(position: Int,status: String)

    }

}





/*(activity: Activity, dataList: ArrayList<ChatMessagesList>?) : RecyclerView.Adapter<ChatAdapter.ListViewHolder>() {

    */

 /*:
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val dataList: ArrayList<ChatMessagesList>?
    private val mContext: Context
    private var clickListener: RvOnClickListener? = null
    var MyApp: UserPreference
    var userId: String
    var utility:Utility? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_chat_list, parent, false)
        return ItemRowHolder(v)
    }
    
    

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as ItemRowHolder
        val singleItem: ChatMessagesList = dataList!![position]
        holder.offerStatus.visibility = View.GONE
        if (userId == singleItem.senderId) {
            holder.receiverLinLay.visibility = View.GONE
            holder.senderLinLay.visibility = View.VISIBLE

            holder.senderTimeTxt.text = utility!!.dateToTimeFormat(singleItem.readableTime)
            if (!TextUtils.isEmpty(singleItem.text)) {
                holder.senderChatTxt.text = singleItem.text!!.trim()
            }
            if(!TextUtils.isEmpty(singleItem.price)){
                holder.senderOfferLinLay.visibility = View.VISIBLE
                holder.senderChatTxt.visibility = View.GONE
                holder.offerSenderPrice.text = singleItem.price
                holder.descSenderText.text = singleItem.description

                if(!TextUtils.isEmpty(singleItem.offerStatus)){
                    holder.offerStatus.visibility = View.VISIBLE
                    when(singleItem.offerStatus){
                        ACCEPT_STATUS->{
                            holder.offerStatus.text = "OFFER ACCEPT"
                            holder.offerStatus.setTextColor(ContextCompat.getColor(mContext,R.color.colorPrimaryDark))
                        }
                        REJECT_STATUS->{
                            holder.offerStatus.text = "OFFER REJECT"
                            holder.offerStatus.setTextColor(ContextCompat.getColor(mContext,R.color.white))
                        }
                    }
                }else{

                }

            }else{
                holder.senderChatTxt.visibility = View.VISIBLE
            }
        } else {
            holder.receiverLinLay.visibility = View.VISIBLE
            holder.senderLinLay.visibility = View.GONE

       
            if (!TextUtils.isEmpty(singleItem.text)) {
                holder.receiverChatTxt.text = singleItem.text!!.trim()

            }

            if(!TextUtils.isEmpty(singleItem.price)){
                holder.offerReceiverLinLay.visibility = View.VISIBLE
                holder.receiverChatTxt.visibility = View.GONE
                holder.offerReceiverPrice.text = singleItem.price
                holder.descReceiverText.text = singleItem.description
                holder.offerReceiverTimeTxt.text = utility!!.dateToTimeFormat(singleItem.readableTime)
                if(!TextUtils.isEmpty(singleItem.offerStatus)){
                    holder.acceptIcon.visibility = View.GONE
                    holder.rejectIcon.visibility = View.GONE
                    holder.offerStatusReceiver.visibility = View.VISIBLE

                    
                    when(singleItem.offerStatus){


                        ACCEPT_STATUS->{
                            holder.offerStatusReceiver.text = "OFFER ACCEPT"
                            holder.offerStatusReceiver.setTextColor(ContextCompat.getColor(mContext,R.color.white))
                        }
                        REJECT_STATUS->{
                            holder.offerStatusReceiver.text = "OFFER REJECT"
                            holder.offerStatusReceiver.setTextColor(ContextCompat.getColor(mContext,R.color.black))
                        }
                    }
                }else{
                    holder.acceptIcon.visibility = View.VISIBLE
                    holder.rejectIcon.visibility = View.VISIBLE
                    holder.offerStatusReceiver.visibility = View.GONE
                }



            }else{
                holder.receiverTimeTxt.text = utility!!.dateToTimeFormat(singleItem.readableTime)
                holder.receiverChatTxt.visibility = View.VISIBLE
            }

            holder.acceptIcon.setOnClickListener {
                if (mItemClickListener != null) {
                    mItemClickListener?.onItemClickListener(position, ACCEPT_STATUS)
                }
            }
            holder.rejectIcon.setOnClickListener {
                if (mItemClickListener != null) {
                    mItemClickListener?.onItemClickListener(position,REJECT_STATUS)
                }
            }
        }



    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    private var mItemClickListener: OnChatItemClickListener? = null

    fun setOnItemClickListener(mItemClickListener: OnChatItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface OnChatItemClickListener {
        fun onItemClickListener(position: Int,status: String)

    }

    internal inner class ItemRowHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        // ImageView image;
        var senderTimeTxt: TextView
        var senderChatTxt: TextView
        var receiverTimeTxt: TextView
        var receiverChatTxt: TextView
        var senderLinLay: LinearLayout
        var receiverLinLay: LinearLayout
        var receiverImage: ImageView
        var senderImage: ImageView

        var  offerReceiverLinLay: LinearLayout
        var  senderOfferLinLay: LinearLayout

        var descSenderText: TextView
        var offerSenderPrice: TextView


        var acceptIcon: ImageView
        var rejectIcon: ImageView

        var offerReceiverPrice: TextView
        var descReceiverText: TextView
        var offerStatus: TextView

        var offerStatusReceiver: TextView

        offerReceiverTimeTxt: TextView


        init {
            offerReceiverPrice= itemView.findViewById(R.id.offerReceiverPrice)
            descReceiverText = itemView.findViewById(R.id.descReceiverText)


            acceptIcon = itemView.findViewById(R.id.acceptIcon)
            rejectIcon = itemView.findViewById(R.id.rejectIcon)


            senderOfferLinLay = itemView.findViewById(R.id.senderOfferLinLay)
            offerReceiverLinLay = itemView.findViewById(R.id.offerReceiverLinLay)
            senderTimeTxt = itemView.findViewById(R.id.senderTimeTxt)
            senderChatTxt = itemView.findViewById(R.id.senderChatTxt)
            receiverTimeTxt = itemView.findViewById(R.id.receiverTimeTxt)
            receiverChatTxt = itemView.findViewById(R.id.receiverChatTxt)
            senderLinLay = itemView.findViewById(R.id.senderLinLay)
            receiverLinLay = itemView.findViewById(R.id.receiverLinLay)
            receiverImage = itemView.findViewById(R.id.receiverImage)
            senderImage = itemView.findViewById(R.id.senderImage)
            descSenderText = itemView.findViewById(R.id.descSenderText)
            offerSenderPrice= itemView.findViewById(R.id.offerSenderPrice)
            offerStatus= itemView.findViewById(R.id.offerStatus)

            offerStatusReceiver= itemView.findViewById(R.id.offerStatusReceiver)


        }
    }

    init {
        utility= Utility()
        this.dataList = dataList
        mContext = context
        MyApp = UserPreference.getInstance(context)!!
        userId = MyApp.user_id!!
    }
}

*/