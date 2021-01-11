package com.numeroeins.arthros.patient.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.databinding.ActivityRegisterBinding

import com.numeroeins.arthros.patient.utility.UserPreference

class RegisterActivity : BaseActivity(),View.OnClickListener {

    private var userPreference: UserPreference? = null
    lateinit var activityRegisterBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        userPreference = UserPreference.getInstance(applicationContext)



        init()
    }

    private fun init() {
        activityRegisterBinding.loginTxt.setOnClickListener(this)
        activityRegisterBinding.signUpTxt.setOnClickListener(this)
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