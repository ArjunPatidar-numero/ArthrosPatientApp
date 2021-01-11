package com.numeroeins.arthros.patient.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.databinding.ActivityLoginBinding
import com.numeroeins.arthros.patient.utility.UserPreference

class LoginActivity : BaseActivity() , View.OnClickListener{

    private var userPreference: UserPreference? = null
    lateinit var activityLoginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        userPreference = UserPreference.getInstance(applicationContext)
        init()
    }

    private fun init() {
        activityLoginBinding.loginTxt.setOnClickListener(this)
        activityLoginBinding.registerTxt.setOnClickListener(this)


    }
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.loginTxt -> {
                val intent = Intent(this, DashBoardActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }
            R.id.registerTxt -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }
        }
    }

}