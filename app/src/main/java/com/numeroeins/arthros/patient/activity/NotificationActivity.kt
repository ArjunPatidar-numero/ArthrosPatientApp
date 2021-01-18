package com.numeroeins.arthros.patient.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.databinding.ActivityForgotPasswordBinding
import com.numeroeins.arthros.patient.databinding.ActivityNotifcationBinding

class NotificationActivity : BaseActivity(), View.OnClickListener {
    private var activityNotifcationBinding: ActivityNotifcationBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNotifcationBinding = DataBindingUtil.setContentView(this, R.layout.activity_notifcation)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        initView()
    }

    private fun initView() {


       // activityForgotPasswordBinding?.forgotPasswordTxt?.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

    }
}