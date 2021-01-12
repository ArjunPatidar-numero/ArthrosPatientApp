package com.numeroeins.arthros.patient.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.databinding.ActivityChangePasswordBinding
import com.numeroeins.arthros.patient.databinding.ActivityEditProfileBinding
import com.numeroeins.arthros.patient.utility.UserPreference

class EditProfileActivity :BaseActivity(), View.OnClickListener {

    private var userPreference: UserPreference? = null
    lateinit var activityEditProfileBinding: ActivityEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityEditProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        userPreference = UserPreference.getInstance(applicationContext)
        init()
    }

    private fun init() {
        activityEditProfileBinding.saveTxt.setOnClickListener(this)

    }
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.saveTxt -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }


        }
    }

}