package com.numeroeins.arthros.patient.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.databinding.DataBindingUtil
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.databinding.ActivityChangePasswordBinding
import com.numeroeins.arthros.patient.databinding.ActivityRegisterBinding
import com.numeroeins.arthros.patient.servermanager.UrlManager
import com.numeroeins.arthros.patient.servermanager.request.CommonValueModel
import com.numeroeins.arthros.patient.servermanager.request.PostRequestModel
import com.numeroeins.arthros.patient.utility.PARAM_USER_NAME
import com.numeroeins.arthros.patient.utility.UserPreference

class ChangePasswordActivity:BaseActivity(),View.OnClickListener {
    var userName: String = ""
    private var userPreference: UserPreference? = null
    lateinit var activityChangePasswordBinding: ActivityChangePasswordBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityChangePasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_change_password)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        userPreference = UserPreference.getInstance(applicationContext)
        init()
    }

    private fun init() {
        activityChangePasswordBinding.saveTxt.setOnClickListener(this)
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
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }
        }
    }
    private fun checkValidation() {
        if (TextUtils.isEmpty(activityChangePasswordBinding?.currentPasswordEdt?.text.toString().trim())) {
            showSnackBar(activityChangePasswordBinding?.currentPasswordEdt!!, resources.getString(R.string.error_current_password))
        } else if (activityChangePasswordBinding.currentPasswordEdt.text.toString().trim().length < 6) {
            showSnackBar(activityChangePasswordBinding.currentPasswordEdt, resources.getString(R.string.error_current_password_6_digit))
        }
        else if (TextUtils.isEmpty(activityChangePasswordBinding?.passwordEdt?.text.toString().trim())) {
            showSnackBar(activityChangePasswordBinding?.passwordEdt!!, resources.getString(R.string.error_password))
        } else if (activityChangePasswordBinding.passwordEdt.text.toString().trim().length < 6) {
            showSnackBar(activityChangePasswordBinding.passwordEdt, resources.getString(R.string.error_6_digit))
        } else if (TextUtils.isEmpty(activityChangePasswordBinding?.confirmPasswordEdt?.text.toString().trim())) {
            showSnackBar(activityChangePasswordBinding?.confirmPasswordEdt!!, resources.getString(R.string.error_confirm_password))
        }else if (activityChangePasswordBinding.confirmPasswordEdt.text.toString().trim().length < 6) {
            showSnackBar(activityChangePasswordBinding.confirmPasswordEdt, resources.getString(R.string.error_confirm_password_6_digit))
        }else if (!comparePassword(activityChangePasswordBinding?.passwordEdt?.text.toString().trim(), activityChangePasswordBinding?.confirmPasswordEdt?.text.toString())) {
            showSnackBar(activityChangePasswordBinding?.passwordEdt!!, resources.getString(R.string.error_matches_password))
        } else {
            val postRequestModel = PostRequestModel()
            postRequestModel.email = userName
            postRequestModel.password = activityChangePasswordBinding?.confirmPasswordEdt?.text.toString().trim()
            showLoader(resources.getString(R.string.please_wait))
            var commonModel = CommonValueModel()
            postApiCall(applicationContext, UrlManager.RESET_PASSWORD, postRequestModel, commonModel)
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