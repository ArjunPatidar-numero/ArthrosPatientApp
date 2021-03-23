package com.numeroeins.arthros.patient.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.databinding.ActivitySettingBinding
import com.numeroeins.arthros.patient.utility.UserPreference

class SettingActivity : BaseActivity(), View.OnClickListener {

    private var userPreference: UserPreference? = null
    lateinit var activitySettingBinding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySettingBinding = DataBindingUtil.setContentView(this, R.layout.activity_setting)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        userPreference = UserPreference.getInstance(applicationContext)
        init()
    }

    private fun init() {
        activitySettingBinding.topHeader.navTitle.text = resources.getText(R.string.settings)

        activitySettingBinding.topHeader.backIcon.setOnClickListener(this)
        activitySettingBinding.editIconLinLay.setOnClickListener(this)
        activitySettingBinding.messagesLinLay.setOnClickListener(this)
        activitySettingBinding.notificationsLinLay.setOnClickListener(this)
        activitySettingBinding.aboutUsLinLay.setOnClickListener(this)
        activitySettingBinding.termsConditionsLinLay.setOnClickListener(this)
        activitySettingBinding.privacyPolicyLinLay.setOnClickListener(this)


    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.backIcon -> {
                finish()
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
            }
            R.id.editIconLinLay -> {
                val intent = Intent(this, EditProfileActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }
            R.id.messagesLinLay -> {
                val intent = Intent(this, ChatUserListActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }
            R.id.notificationsLinLay -> {
                val intent = Intent(this, EditProfileActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }
            R.id.aboutUsLinLay -> {

            }
            R.id.termsConditionsLinLay -> {

            }
            R.id.privacyPolicyLinLay -> {

            }


        }
    }

}