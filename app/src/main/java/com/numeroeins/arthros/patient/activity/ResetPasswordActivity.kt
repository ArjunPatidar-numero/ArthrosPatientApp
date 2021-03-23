package com.numeroeins.arthros.patient.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.databinding.ActivityResetPasswordBinding
import com.numeroeins.arthros.patient.utility.PARAM_USER_NAME
import com.numeroeins.arthros.patient.utility.UserPreference

class ResetPasswordActivity : BaseActivity(), View.OnClickListener {
    var userName: String = ""
    private var userPreference: UserPreference? = null
    lateinit var activityResetPasswordBinding: ActivityResetPasswordBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityResetPasswordBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_reset_password)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        userPreference = UserPreference.getInstance(applicationContext)
        init()
    }

    private fun init() {
        activityResetPasswordBinding.saveTxt.setOnClickListener(this)
        activityResetPasswordBinding.ivBtnBackChangePass.setOnClickListener(this)
    }


    private fun getBundleData() {
        val extras = getIntent().getExtras()
        if (null != extras) {
            if (extras.getString(PARAM_USER_NAME) != null) {
                userName = extras.getString(PARAM_USER_NAME)!!
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.saveTxt -> {
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }
            R.id.ivBtnBackChangePass -> {
                finish()
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
            }
        }
    }


    private fun comparePassword(p1: String, p2: String): Boolean {
        var auth = false
        if (p1.equals(p2, ignoreCase = true)) {
            auth = true
        }
        return auth
    }
}