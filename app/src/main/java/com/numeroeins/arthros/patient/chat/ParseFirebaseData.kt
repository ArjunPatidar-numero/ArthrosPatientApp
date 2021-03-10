package com.numeroeins.arthros.patient.chat

import android.content.Context
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.numeroeins.arthros.patient.utility.*



import java.util.*

class ParseFirebaseData(context: Context?) {
    private val set: SettingsAPI


    fun getMessagesForSingleUser(dataSnapshot: DataSnapshot): List<ChatMessagesList> {
        val chats: MutableList<ChatMessagesList> = ArrayList<ChatMessagesList>()
        var text = ""
        var msgTime = ""
        var senderId = ""
        var senderName = ""
        var senderPhoto = ""
        var receiverId = ""
        var receiverName = ""
        var receiverPhoto = ""
        var uploadImage = ""
        
     
      
       
      
    
        var key= ""
        var read = java.lang.Boolean.TRUE

      /*  hm[NODE_TEXT] = activityChatBinding.chatEdt.text.toString()
        hm[NODE_TIMESTAMP] = System.currentTimeMillis().toString()
        hm[NODE_RECEIVER_ID] = receiverId
        hm[NODE_RECEIVER_NAME] = receiverName
        hm[NODE_RECEIVER_PHOTO] = receiverImage
        hm[NODE_SENDER_ID] = senderId
        hm[NODE_SENDER_NAME] = senderName
        hm[NODE_SENDER_PHOTO] = senderImage
        hm[NODE_IS_READ] = false*/
        
        for (data in dataSnapshot.children) {
            key=data.key.toString()
            Log.e("key","key  @@@@@"+ data.key)
            text = data.child(NODE_TEXT).value.toString()
            msgTime = data.child(NODE_TIMESTAMP).value.toString()
            senderId = data.child(NODE_SENDER_ID).value.toString()
            senderName = data.child(NODE_SENDER_NAME).value.toString()
            if (data.child(NODE_SENDER_PHOTO).value != null) {
                senderPhoto = data.child(NODE_SENDER_PHOTO).value.toString()
            }
            receiverId = data.child(NODE_RECEIVER_ID).value.toString()
            if (data.child(NODE_RECEIVER_NAME).value != null) {
                receiverName = data.child(NODE_RECEIVER_NAME).value.toString()
            }
            if (data.child(NODE_RECEIVER_PHOTO).value != null) {
                receiverPhoto = data.child(NODE_RECEIVER_PHOTO).value.toString()
            }
            if (data.child(NODE_IMAGE).value != null) {
                uploadImage = data.child(NODE_IMAGE).value.toString()
            }
            //Node isRead is added later, may be null
            read = data.child(NODE_IS_READ).value == null || java.lang.Boolean.parseBoolean(data.child(NODE_IS_READ).value.toString())

        /*    price  = data.child(NODE_PRICE).value.toString()
            description= data.child(NODE_DESCRIPTION).value.toString()
            offerStatus= data.child(NODE_OFFER_STATUS).value.toString()
        *//*    offerStatus = data.child(NODE_OFFER_STATUS).value == null || java.lang.Boolean.parseBoolean(data.child(NODE_OFFER_STATUS).value.toString())*//*
            offerId= data.child(NODE_OFFER_ID).value.toString()
            ownerId= data.child(NODE_OWNER_ID).value.toString()
            jobId= data.child(NODE_JOB_IDS).value.toString()*/

            chats.add(
                ChatMessagesList(text, msgTime, receiverId, receiverName, receiverPhoto, senderId, senderName
                    , senderPhoto, read, "", uploadImage,key))

        }
        return chats
    }

    fun getAllLastMessages(dataSnapshot: DataSnapshot): ArrayList<ChatMessagesList> {
        // TODO: 11/09/18 Return only last messages of every conversation current user is
        //  involved in
        val lastChats: ArrayList<ChatMessagesList> = ArrayList<ChatMessagesList>()
        var tempMsgList: ArrayList<ChatMessagesList>
        var lastTimeStamp: Long
        var text = ""
        var msgTime = ""
        var senderId = ""
        var senderName = ""
        var senderPhoto = ""
        var receiverId = ""
        var receiverName = ""
        var receiverPhoto = ""
        var uploadImage = ""
        var read = java.lang.Boolean.TRUE
        
     
      
       
      
    
        var key= ""
        for (wholeChatData in dataSnapshot.children) {
            tempMsgList = ArrayList<ChatMessagesList>()
            lastTimeStamp = 0
            for (data in wholeChatData.children) {
                Log.e("key","key  @@@@@"+ data.key)
                key=data.key.toString()
                msgTime = data.child(NODE_TIMESTAMP).value.toString()
                if (msgTime.toLong() > lastTimeStamp) {
                    lastTimeStamp = msgTime.toLong()
                }
                text = data.child(NODE_TEXT).value.toString()
                senderId = data.child(NODE_SENDER_ID).value.toString()
                senderName = data.child(NODE_SENDER_NAME).value.toString()
                if (data.child(NODE_SENDER_PHOTO).value != null) {
                    senderPhoto = data.child(NODE_SENDER_PHOTO).value.toString()
                }
                receiverId = data.child(NODE_RECEIVER_ID).value.toString()
                receiverName = data.child(NODE_RECEIVER_NAME).value.toString()
                if (data.child(NODE_RECEIVER_PHOTO).value != null) {
                    receiverPhoto = data.child(NODE_RECEIVER_PHOTO).value.toString()
                }
                if (data.child(NODE_IMAGE).value != null) {
                    uploadImage = data.child(NODE_IMAGE).value.toString()
                }



                //Node isRead is added later, may be null
                read = data.child(NODE_IS_READ).value == null || java.lang.Boolean.parseBoolean(data.child(NODE_IS_READ).value.toString())
                tempMsgList.add(ChatMessagesList(text, msgTime, receiverId, receiverName, receiverPhoto, senderId, senderName, senderPhoto, read, ""
                    , uploadImage,key))
            }
            for (oneTemp in tempMsgList) {
                if (set.readSetting(PREF_MY_ID).equals(oneTemp.receiver.id) ||
                    set.readSetting("myid").equals(oneTemp.sender.id)
                ) {
                    if (oneTemp.timestamp.equals(lastTimeStamp.toString())) {
                        lastChats.add(oneTemp)
                    }
                }
            }
        }
        return lastChats
    }

    fun getAllUnreadReceivedMessages(dataSnapshot: DataSnapshot,context: Context?): ArrayList<ChatMessagesList> {
        val lastChats: ArrayList<ChatMessagesList> = ArrayList<ChatMessagesList>()
        var tempMsgList: ArrayList<ChatMessagesList>
        var lastTimeStamp: Long
        var text = ""
        var msgTime = ""
        var senderId = ""
        var senderName = ""
        var senderPhoto = ""
        var receiverId = ""
        var receiverName = ""
        var receiverPhoto = ""
        var uploadImage = ""
        var read: Boolean
         
      
       
        
       
    
        var key= ""
        for (wholeChatData in dataSnapshot.children) {
            tempMsgList = ArrayList<ChatMessagesList>()
            lastTimeStamp = 0
            for (data in wholeChatData.children) {
                Log.e("key","key  @@@@@"+ data.key)
                key=data.key.toString()
                msgTime = data.child(NODE_TIMESTAMP).value.toString()
                if (msgTime.toLong() > lastTimeStamp) {
                    lastTimeStamp = msgTime.toLong()
                }
                text = data.child(NODE_TEXT).value.toString()
                senderId = data.child(NODE_SENDER_ID).value.toString()
                senderName = data.child(NODE_SENDER_NAME).value.toString()
                if (data.child(NODE_SENDER_PHOTO).value != null) {
                    senderPhoto = data.child(NODE_SENDER_PHOTO).value.toString()
                }
                receiverId = data.child(NODE_RECEIVER_ID).value.toString()
                receiverName = data.child(NODE_RECEIVER_NAME).value.toString()
                if (data.child(NODE_RECEIVER_PHOTO).value != null) {
                    receiverPhoto = data.child(NODE_RECEIVER_PHOTO).value.toString()
                }
                //Node isRead is added later, may be null
                read = data.child(NODE_IS_READ).value == null ||
                        java.lang.Boolean.parseBoolean(data.child(NODE_IS_READ).value.toString())
                if (data.child(NODE_IMAGE).value != null) {
                    uploadImage = data.child(NODE_IMAGE).value.toString()
                }

                /*price  = data.child(NODE_PRICE).value.toString()
                description= data.child(NODE_DESCRIPTION).value.toString()
                offerStatus= data.child(NODE_OFFER_STATUS).value.toString()
               // offerStatus = data.child(NODE_OFFER_STATUS).value == null || java.lang.Boolean.parseBoolean(data.child(NODE_OFFER_STATUS).value.toString())
                offerId= data.child(NODE_OFFER_ID).value.toString()
                ownerId= data.child(NODE_OWNER_ID).value.toString()

                jobId= data.child(NODE_JOB_IDS).value.toString()*/
                tempMsgList.add(
                    ChatMessagesList(
                        text, msgTime, receiverId, receiverName, receiverPhoto,
                        senderId, senderName, senderPhoto, read, "", uploadImage
                        ,key))

            }
            for (oneTemp in tempMsgList) {
                if (UserPreference.getInstance(context!!)!!.token.equals(oneTemp.receiver.id)) {
                    /*if (oneTemp.getTimestamp().equals(String.valueOf(lastTimeStamp)) &&
                        !oneTemp.isRead()) {
                        lastChats.add(oneTemp);
                    }*/
                    if (!oneTemp.isRead!!) {
                        lastChats.add(oneTemp)
                    }
                }
            }
        }
        return lastChats
    }

    private fun encodeText(msg: String): String {
        return msg.replace(",", "#comma#").replace("{", "#braceopen#").replace("}", "#braceclose#")
            .replace("=", "#equals#")
    }

    private fun decodeText(msg: String): String {
        return msg.replace("#comma#", ",").replace("#braceopen#", "{").replace("#braceclose#", "}")
            .replace("#equals#", "=")
    }

    init {
        set = SettingsAPI(context!!)
    }
}
