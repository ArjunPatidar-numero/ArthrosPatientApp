package com.numeroeins.arthros.patient.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.beans.RegisterModel
import com.numeroeins.arthros.patient.databinding.ActivityRegisterBinding
import com.numeroeins.arthros.patient.servermanager.APIClient.gsonAsConvert
import com.numeroeins.arthros.patient.servermanager.UrlManager
import com.numeroeins.arthros.patient.servermanager.request.CommonValueModel
import com.numeroeins.arthros.patient.servermanager.request.PostRequestModel
import com.numeroeins.arthros.patient.utility.*

import io.reactivex.disposables.Disposable

class RegisterActivity : BaseActivity(),View.OnClickListener {

    private var userPreference: UserPreference? = null
    lateinit var activityRegisterBinding: ActivityRegisterBinding
    private var deviceToken = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        userPreference = UserPreference.getInstance(applicationContext)
        init()
    }

    private fun init() {

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                //  Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            deviceToken = token!!
            userPreference!!.deviceToken=deviceToken
            userPreference!!.save(this)

        })

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
                checkValidation()
            }
        }
    }


    private fun checkValidation() {
        val validate = Validate()
        if (TextUtils.isEmpty(activityRegisterBinding.firstNameEdt.text.toString().trim())) {
            showSnackBar(activityRegisterBinding.firstNameEdt, resources.getString(R.string.error_email_address))
        }else if (TextUtils.isEmpty(activityRegisterBinding.emailEdt.text.toString().trim())) {
            showSnackBar(activityRegisterBinding.emailEdt, getResources().getString(R.string.error_email_address))
        } else if (!validate.isEmailValid(activityRegisterBinding.emailEdt.text.toString().trim())) {
            showSnackBar(activityRegisterBinding.emailEdt, getResources().getString(R.string.error_email_validation))
        }else if (TextUtils.isEmpty(activityRegisterBinding.phoneEdt.text.toString().trim())) {
            showSnackBar(activityRegisterBinding.phoneEdt, resources.getString(R.string.error_mobile_validation))
        } else if (TextUtils.isEmpty(activityRegisterBinding.passwordEdt.text.toString().trim())) {
            showSnackBar(activityRegisterBinding.passwordEdt, resources.getString(R.string.error_password))
        } else if (activityRegisterBinding.passwordEdt.text.toString().trim().length < 6) {
            showSnackBar(activityRegisterBinding.passwordEdt, resources.getString(R.string.error_6_digit))
        } else if (TextUtils.isEmpty(activityRegisterBinding.confirmPasswordEdt.text.toString().trim())) {
            showSnackBar(activityRegisterBinding.passwordEdt, resources.getString(R.string.error_password))
        } else if (activityRegisterBinding.passwordEdt.text.toString().trim()!=activityRegisterBinding.confirmPasswordEdt.text.toString().trim()) {
            showSnackBar(activityRegisterBinding.passwordEdt, resources.getString(R.string.error_matches_password))
        }else{
            val postRequestModel = PostRequestModel()
            postRequestModel.first_name = activityRegisterBinding.firstNameEdt.text.toString().trim()
            postRequestModel.email = activityRegisterBinding.emailEdt.text.toString().trim()
            postRequestModel.gender = ""
            postRequestModel.phone =  activityRegisterBinding.phoneEdt.text.toString().trim()
            postRequestModel.device_token = deviceToken
            postRequestModel.password = activityRegisterBinding.passwordEdt.text.toString().trim()
         //   postRequestModel.type = "user"

            val intent = Intent(this, OtpVerificationActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)

//            showLoader(resources.getString(R.string.please_wait))
//            val commonModel = CommonValueModel()
//            postApiCall(applicationContext, UrlManager.REGISTER_API, postRequestModel, commonModel)
        }

    }


    override fun onSuccess(result: String?, apiName: String?, disposable: Disposable?, commonModel: CommonValueModel?) {
        super.onSuccess(result, apiName, disposable, commonModel)
        closeLoader()
        //RegisterModel
        when (apiName) {

            UrlManager.REGISTER_API -> {
                val responseLoginModel: RegisterModel? = gsonAsConvert.fromJson<RegisterModel>(result, RegisterModel::class.java)
                if (responseLoginModel != null) {
                    if (responseLoginModel.status.equals(STATUS_SUCCESS) ) {
                     /*   userPreference!!.fullName = responseLoginModel.data!!.fullName
                        userPreference!!.email = responseLoginModel.data!!.email
                        userPreference!!.phone = responseLoginModel.data!!.phone
                        userPreference!!.id = responseLoginModel.data!!.id
                        userPreference!!.token = responseLoginModel.data!!.token
                        userPreference!!.phoneVerified = responseLoginModel.data!!.phoneVerified
                        userPreference!!.type = responseLoginModel.data!!.type
                        userPreference!!.image = responseLoginModel.data!!.image
                       // userPreference!!.loginStatus = LOGIN_CHECK
                        userPreference!!.save(this)*/
                      /*  val intent = Intent(applicationContext, DashboardActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                        finish()*/
                    } else {
                        showSnackBar(activityRegisterBinding.emailEdt, responseLoginModel.data)
                    }
                }

            }
         /*   UrlManager.COUNTRY_LIST_API -> {
            val countryCodeModel: ResponseCountryCodeModel? = gsonAsConvert.fromJson(result, ResponseCountryCodeModel::class.java)
            if (countryCodeModel != null) {
                if (countryCodeModel.status.equals(STATUS_SUCCESS) && countryCodeModel.data != null) {
                    countryCodeModelArrayList = java.util.ArrayList()
                    countryCodeModelArrayList.clear()
                    countryCodeModelArrayList.addAll(countryCodeModel.data)
                    initializeCountryCodeBottomBar()
                }else{
                    showSnackBar(activityRegisterBinding.emailEdt, countryCodeModel.error)
                }

            }
        }*/
        }
    }

    override fun onFailure(message: String?, apiName: String?, disposable: Disposable?, commonModel: CommonValueModel?) {
        super.onFailure(message, apiName, disposable, commonModel)
        closeLoader()
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

}