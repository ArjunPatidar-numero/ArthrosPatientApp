package com.numeroeins.arthros.patient.chat

import android.content.Context
import android.text.TextUtils
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.database.*
import com.numeroeins.arthros.patient.utility.*

import java.util.*

class JobListWorkManager(private var context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private  var jobIdNode: String = ""
    private var jobName:String = ""
    private  var jobImage:String = ""
    private  var jobAddress:String = ""
    private  var jobOwner:String = ""
    private var myApplication: UserPreference? = null
    private  var ref: DatabaseReference? = null
    private  var valueEventListener: ValueEventListener? = null
    private  var chatNode: String? = ""

    override fun doWork(): Result {
       myApplication = UserPreference.getInstance(context)
        jobIdNode = inputData.getString(NODE_JOB_ID)!!
        jobName = inputData.getString(NODE_JOB_NAME)!!
        jobImage = inputData.getString(NODE_JOB_IMAGE)!!
        jobAddress = inputData.getString(NODE_JOB_ADDRESS)!!
        jobOwner = inputData.getString(NODE_JOB_OWNER)!!

        updateUserInDb()
        return Result.success()
    }


    private fun updateUserInDb() {
            chatNode =  "J_*"+jobIdNode
            valueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.e("Constant.LOG_TAG", "Data changed from activity")
                    if (!dataSnapshot.hasChild(jobIdNode!!)) {
                        val hm = HashMap<String, Any>()
                        hm.clear()
                        hm[NODE_JOB_ID] ="J_*"+jobIdNode
                        hm[NODE_JOB_NAME] = jobName
                        hm[NODE_JOB_ADDRESS] = jobAddress
                        hm[NODE_JOB_IMAGE] = jobImage
                        hm[NODE_JOB_OWNER] = jobOwner
                        ref!!.child(chatNode!!).setValue(hm)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e("Constant.LOG_TAG", "Data changed from activity")
                    // showSnackBar(logoImg, getResources().getString(R.string.error_could_not_connect));
                }
            }
            ref = FirebaseDatabase.getInstance().getReference(JOB_LIST)
            ref!!.addValueEventListener(valueEventListener as ValueEventListener)
            Log.e("Constant.LOG_TAG", "bottom")
    }
}

