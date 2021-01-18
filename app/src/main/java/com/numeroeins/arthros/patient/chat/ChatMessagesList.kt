package com.numeroeins.arthros.patient.chat



class ChatMessagesList(var text: String?, var timestamp: String, var friendId: String?, var friendName: String?
                       , var friendPhoto: String?, var senderId: String?, var senderName: String?
                       , var senderPhoto: String?, var isRead: Boolean?, var email: String?
                       , var uploadImage: String?,var key: String?) {




    val readableTime: String?
        get() {
            return try {
                Tools.formatTime(java.lang.Long.valueOf(timestamp))
            } catch (ignored: NumberFormatException) {
                null
            }

        }

    val receiver: Friend
        get() = Friend(friendId!!, friendName!!, friendPhoto!!,email!!,false,"")

    val sender: Friend
        get() = Friend(senderId!!, senderName!!, senderPhoto!!,email!!,false,"")

    val comparableTimestamp: Long
        get() = java.lang.Long.parseLong(timestamp)


}
