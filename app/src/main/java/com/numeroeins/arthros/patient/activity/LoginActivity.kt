package com.numeroeins.arthros.patient.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.beans.LoginModel
import com.numeroeins.arthros.patient.beans.RegisterModel
import com.numeroeins.arthros.patient.databinding.ActivityLoginBinding
import com.numeroeins.arthros.patient.servermanager.APIClient
import com.numeroeins.arthros.patient.servermanager.UrlManager
import com.numeroeins.arthros.patient.servermanager.request.CommonValueModel
import com.numeroeins.arthros.patient.servermanager.request.PostRequestModel
import com.numeroeins.arthros.patient.utility.*
import io.reactivex.disposables.Disposable

class LoginActivity : BaseActivity() , View.OnClickListener{

    private var userPreference: UserPreference? = null
    private lateinit var activityLoginBinding: ActivityLoginBinding
    private var deviceToken = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        if (null != intent.extras && intent.extras!!.getBoolean(PARAM_IS_COMING_BACK,false)) {
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
        }else{
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }
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



        activityLoginBinding.loginTxt.setOnClickListener(this)
        activityLoginBinding.registerTxt.setOnClickListener(this)

        activityLoginBinding.forgotPassword.setOnClickListener(this)



    }
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.forgotPassword -> {
                val intent = Intent(this, ForgotActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }
            R.id.loginTxt -> {
              //  checkValidation()
                val intent = Intent(this, MainActivity::class.java)
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

    private fun checkValidation() {
        val validate = Validate()
        if (TextUtils.isEmpty(activityLoginBinding.emailEdt.text.toString().trim())) {
            showSnackBar(activityLoginBinding.emailEdt, resources.getString(R.string.error_email_address))
        } else if (!validate.isEmailValid(activityLoginBinding.emailEdt.text.toString().trim())) {
            showSnackBar(activityLoginBinding.emailEdt, resources.getString(R.string.error_email_validation))
        } else if (TextUtils.isEmpty(activityLoginBinding.passwordEdt.text.toString().trim())) {
            showSnackBar(activityLoginBinding.passwordEdt, resources.getString(R.string.error_password))
        } else if (activityLoginBinding.passwordEdt.text.toString().trim().length < 6) {
            showSnackBar(activityLoginBinding.passwordEdt, resources.getString(R.string.error_6_digit))
        }else{
            val postRequestModel = PostRequestModel()
            postRequestModel.email = activityLoginBinding.emailEdt.text.toString().trim()
            postRequestModel.device_token = deviceToken
            postRequestModel.password = activityLoginBinding.passwordEdt.text.toString().trim()
           // postRequestModel.type = "user"
            showLoader(resources.getString(R.string.please_wait))
            val commonModel = CommonValueModel()
            postApiCall(applicationContext, UrlManager.LOGIN_API, postRequestModel, commonModel)

        }

    }


    override fun onSuccess(result: String?, apiName: String?, disposable: Disposable?, commonModel: CommonValueModel?) {
        closeLoader()
        Log.d("resultresult", result.toString())
        when (apiName) {
            UrlManager.LOGIN_API-> {
               val responseLoginModel: LoginModel? = APIClient.gsonAsConvert.fromJson<LoginModel>(result, LoginModel::class.java)
                if (responseLoginModel != null) {
                    if (responseLoginModel.status == STATUS_SUCCESS) {
                        userPreference?.departmentId = responseLoginModel.data!!.departmentId
                        userPreference?.deviceToken = deviceToken
                        userPreference?.firsName = responseLoginModel.data!!.firstName
                        userPreference?.lastName = responseLoginModel.data!!.lastName
                        userPreference?.email = responseLoginModel.data!!.email
                        userPreference?.phone = responseLoginModel.data!!.phone
                        userPreference?.gender = responseLoginModel.data!!.gender
                        userPreference?.status = responseLoginModel.data!!.status
                        userPreference?.imageUrl = responseLoginModel.data!!.imageUrl
                        userPreference?.fullName = responseLoginModel.data!!.fullName
                        userPreference?.token = responseLoginModel.data!!.token
                        userPreference?.loginStatus = LOGIN_SESSION
                        userPreference?.save(this)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                        finish()
                    } else {
                        showSnackBar(activityLoginBinding.emailEdt, responseLoginModel.error)
                    }
                }
            }
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