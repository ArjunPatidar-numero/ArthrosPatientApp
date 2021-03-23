package com.numeroeins.arthros.patient.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.adapter.NotificationListAdapter
import com.numeroeins.arthros.patient.beans.NotificationModel
import com.numeroeins.arthros.patient.databinding.ActivityNotificationBinding
import java.util.*

class NotificationActivity : BaseActivity(), View.OnClickListener,
    NotificationListAdapter.OnNotificationItemClickListener {
    lateinit var activityNotificationBinding: ActivityNotificationBinding
    lateinit var notificationListAdapter: NotificationListAdapter

    var notificationList: ArrayList<NotificationModel> = ArrayList<NotificationModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNotificationBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_notification)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        initView()
    }

    private fun initView() {
        activityNotificationBinding.topHeader.navTitle.text = resources.getText(R.string.notifications)
        activityNotificationBinding.topHeader.backIcon.setOnClickListener(this)

        setNotificationAdapter()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.backIcon ->{
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
                finish()
            }
        }
    }

    private fun addDummyEntry() {
        var notificationModel = NotificationModel()

        notificationModel.name = "Yuri Zhivago"
        notificationModel.notText = "llshdfpghgfdhgjhsdfjghjfdhgjdfghkkjgkjdfgkjdgkjdfjksfkhdsgfhsdshdfhgbfbfsbdgEPOWREPOW"
        notificationModel.notDate = "25-Feb-2021"
        notificationModel.notTime = "12:55 PM"
        notificationList.add(notificationModel)

        notificationModel = NotificationModel()
        notificationModel.name = "Yuri Zhivago"
        notificationModel.notText = "llshdfpghgfdhgjhsdfjghjfdhgjdfghkkjgkjdfgkjdgkjdfjksfkhdsgfhsdshdfhgbfbfsbdgEPOWREPOW"
        notificationModel.notDate = "25-Feb-2021"
        notificationModel.notTime = "12:55 PM"
        notificationList.add(notificationModel)

        notificationModel = NotificationModel()
        notificationModel.name = "Yuri Zhivago"
        notificationModel.notText = "llshdfpghgfdhgjhsdfjghjfdhgjdfghkkjgkjdfgkjdgkjdfjksfkhdsgfhsdshdfhgbfbfsbdgEPOWREPOW"
        notificationModel.notDate = "25-Feb-2021"
        notificationModel.notTime = "12:55 PM"
        notificationList.add(notificationModel)

        notificationModel = NotificationModel()
        notificationModel.name = "Yuri Zhivago"
        notificationModel.notText = "llshdfpghgfdhgjhsdfjghjfdhgjdfghkkjgkjdfgkjdgkjdfjksfkhdsgfhsdshdfhgbfbfsbdgEPOWREPOW"
        notificationModel.notDate = "25-Feb-2021"
        notificationModel.notTime = "12:55 PM"
        notificationList.add(notificationModel)

        notificationModel = NotificationModel()
        notificationModel.name = "Yuri Zhivago"
        notificationModel.notText = "llshdfpghgfdhgjhsdfjghjfdhgjdfghkkjgkjdfgkjdgkjdfjksfkhdsgfhsdshdfhgbfbfsbdgEPOWREPOW"
        notificationModel.notDate = "25-Feb-2021"
        notificationModel.notTime = "12:55 PM"
        notificationList.add(notificationModel)
    }

    private fun setNotificationAdapter() {
        addDummyEntry()
        notificationListAdapter = NotificationListAdapter(this, notificationList)
        activityNotificationBinding.notificationRv.layoutManager = LinearLayoutManager(this)
        activityNotificationBinding.notificationRv.adapter = notificationListAdapter
        notificationListAdapter.setOnItemClickListener(this)
        notificationListAdapter.notifyDataSetChanged()
    }

    override fun onNotificationClickListener(position: Int) {

    }
}