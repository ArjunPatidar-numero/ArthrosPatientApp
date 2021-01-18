package com.numeroeins.arthros.patient.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.adapter.ChatUserListAdapter
import com.numeroeins.arthros.patient.beans.ChatUserListModel
import com.numeroeins.arthros.patient.databinding.ActivityChatUserListBinding
import com.numeroeins.arthros.patient.utility.*
import java.util.*
import  com.numeroeins.arthros.patient.chat.ChatActivity

class ChatUserListActivity :BaseActivity(), View.OnClickListener,ChatUserListAdapter.onRecyclerViewItemClickListener {
    private var userPreference: UserPreference? = null

    lateinit var chatAdapter: ChatUserListAdapter
    var friendArrayList: ArrayList<ChatUserListModel> = ArrayList<ChatUserListModel>()
    var ref: DatabaseReference? = null
    var userIdArrayList = ArrayList<String>()

    lateinit var activityChatUserListBinding: ActivityChatUserListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityChatUserListBinding = DataBindingUtil.setContentView(this, R.layout.activity_chat_user_list)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        userPreference = UserPreference.getInstance(applicationContext)
        init()
    }

    private fun init() {

        updateUserInDb()
    }

    override fun onClick(v: View?) {

    }

    override fun onItemClickListener(position: Int) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra(PARAM_RECEIVER_ID, friendArrayList[position].id)
        intent.putExtra(PARAM_RECEIVER_NAME, friendArrayList[position].name)
        intent.putExtra(PARAM_RECEIVER_IMAGE, friendArrayList[position].photo)
      /*  intent.putExtra(PARAM_JOB_ID, friendArrayList[position].jobId)
        intent.putExtra(PARAM_OWNER_ID, friendArrayList[position].ownerId)*/
        startActivity(intent)
    }


    private fun updateUserInDb() {
        ref = FirebaseDatabase.getInstance().getReference(MESSAGE_CHILD)
        ref!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                userIdArrayList.clear()
                for (snapshot in dataSnapshot.children) {
                    val node = snapshot.key!!.split("-".toRegex()).toTypedArray()
                    if (snapshot.key!!.contains(userPreference!!.user_id!!)) {
                        if (node[0] == userPreference!!.user_id) {
                            userIdArrayList.add(node[1])
                        } else if (node[1] == userPreference!!.user_id) {
                            userIdArrayList.add(node[0])
                        }
                    }
                    Log.e("DataSnapshot @@@@@@@@@@", snapshot.key!!)
                }
                Log.e("DataSnapshot @@@@@@@@@@", "" + userIdArrayList.size)
                getUserList()
            }

            override fun onCancelled(error: DatabaseError) {
                showSnackBar(activityChatUserListBinding.chatRv, resources.getString(R.string.error_could_not_connect))
            }
        })
    }

    private fun getUserList() {
        val ref = FirebaseDatabase.getInstance().getReference(USERS_CHILD)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (data in dataSnapshot.children) {

                    for (i in userIdArrayList.indices) {
                        if (data.key==userIdArrayList[i]){
                            val jobChatModel= ChatUserListModel()
                            if (data.child(USER_NAME).value != null) {
                                jobChatModel.name=(data.child(USER_NAME).value.toString())
                            }
                            if (data.child(USER_ID).value != null) {
                                //id = data.child(Constant.ID).getValue().toString();
                                jobChatModel.id=(data.child(USER_ID).value.toString())
                            }
                            if (data.child(USER_PHOTO).value != null) {
                                //  photo = data.child(Constant.PHOTO).getValue().toString();
                                jobChatModel.photo=(data.child(USER_PHOTO).value.toString())
                            }
                            if (data.child(USER_EMAIL).value != null) {
                                // email = data.child(Constant.USER_EMAIL).getValue().toString();
                                jobChatModel.email=(data.child(USER_EMAIL).value.toString())
                            }
                            if (data.child(USER_CHAT_STATUS).value != null) {
                                jobChatModel.userChatStatus=data.child(USER_CHAT_STATUS).value as Boolean
                            }
                            if (data.child(USER_LAST_SEEN).value != null) {
                                jobChatModel.userLastSeen=(data.child(USER_LAST_SEEN).value.toString())
                            }

                            friendArrayList.add(jobChatModel)
                        }

                    }


                }
                getJobDetails()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                showSnackBar(activityChatUserListBinding.chatRv, resources.getString(R.string.error_could_not_connect))
            }
        })
    }

    private fun getJobDetails() {
        chatAdapter = ChatUserListAdapter(this, friendArrayList)
        activityChatUserListBinding.chatRv.layoutManager = LinearLayoutManager(this)
        activityChatUserListBinding.chatRv.adapter = chatAdapter
        chatAdapter.setOnItemClickListener(this)
        chatAdapter.notifyDataSetChanged()
    }
}