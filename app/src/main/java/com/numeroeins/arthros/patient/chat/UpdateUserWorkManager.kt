package com.numeroeins.arthros.patient.chat

import android.content.Context
import android.text.TextUtils
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.database.*
import com.numeroeins.arthros.patient.servermanager.UrlManager.Companion.MAIN_URL
import com.numeroeins.arthros.patient.utility.*


import java.util.*

class UpdateUserWorkManager(private var context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private var userPreference: UserPreference? = null
    private  var ref: DatabaseReference? = null
    private  var valueEventListener: ValueEventListener? = null
    private  var chatNode: String? = ""
    private var isCheck = false

    override fun doWork(): Result {
        userPreference = UserPreference.getInstance(context)
        updateUserInDb()
        return Result.success()
    }

    private fun updateUserInDb() {
        //if (!TextUtils.isEmpty(userPreference!!.user_id!!.trim())) {
           chatNode = "2"//userPreference!!.user_id!!
            valueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.e("Constant.LOG_TAG", "Data changed from activity")

                    if (!dataSnapshot.hasChild(chatNode!!)) {
                        val hm = HashMap<String, Any>()
                        hm.clear()
                        hm[USER_ID] = "2"//userPreference!!.user_id!!
                        hm[USER_NAME] ="Amit"// userPreference!!.fullName!!
                        hm[USER_PHOTO] =""//MAIN_URL+userPreference!!.image!!
                        hm[USER_EMAIL] =""// userPreference!!.email!!
                        hm[USER_CHAT_STATUS] = true
                        hm[USER_LAST_SEEN] = ""
                        ref!!.child(chatNode!!).setValue(hm)
//                        userPreference!!.firstDBUpdate = true
                    } else {
//                        userPreference!!.firstDBUpdate = true
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e("Constant.LOG_TAG", "Data changed from activity")
                    // showSnackBar(logoImg, getResources().getString(R.string.error_could_not_connect));
                }
            }
            ref = FirebaseDatabase.getInstance().getReference(USERS_CHILD)
            ref!!.addValueEventListener(valueEventListener as ValueEventListener)
            Log.e("Constant.LOG_TAG", "bottom")

        }
    //}
}