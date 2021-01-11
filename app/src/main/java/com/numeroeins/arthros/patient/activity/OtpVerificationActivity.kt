package com.numeroeins.arthros.patient.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.databinding.ActivityOtpVerificationBinding
import com.numeroeins.arthros.patient.databinding.ActivityRegisterBinding
import com.numeroeins.arthros.patient.utility.UserPreference


class OtpVerificationActivity : BaseActivity(), View.OnClickListener {

    private var userPreference: UserPreference? = null
    lateinit var activityOtpVerificationBinding: ActivityOtpVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityOtpVerificationBinding = DataBindingUtil.setContentView(this, R.layout.activity_otp_verification)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        userPreference = UserPreference.getInstance(applicationContext)



        init()
    }

    private fun init() {
        activityOtpVerificationBinding.loginTxt.setOnClickListener(this)

    }
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.loginTxt -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }

            R.id.signUpTxt -> {
                val intent = Intent(this, OtpVerificationActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }
        }
    }

}