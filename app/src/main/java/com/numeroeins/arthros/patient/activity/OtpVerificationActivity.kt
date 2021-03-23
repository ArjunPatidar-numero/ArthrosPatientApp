package com.numeroeins.arthros.patient.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.databinding.DataBindingUtil
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.beans.ResponseForgotOtpModel
import com.numeroeins.arthros.patient.beans.ResponseSentOtpModel
import com.numeroeins.arthros.patient.databinding.ActivityOtpVerificationBinding
import com.numeroeins.arthros.patient.databinding.ActivityRegisterBinding
import com.numeroeins.arthros.patient.servermanager.APIClient
import com.numeroeins.arthros.patient.servermanager.UrlManager
import com.numeroeins.arthros.patient.servermanager.request.CommonValueModel
import com.numeroeins.arthros.patient.servermanager.request.PostRequestModel
import com.numeroeins.arthros.patient.utility.*
import io.reactivex.disposables.Disposable


class OtpVerificationActivity : BaseActivity(), View.OnClickListener {
    var mobileNo: String = ""
    var isdCode: String = ""
    var comingFrom: String = ""
    var email: String = ""
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
        activityOtpVerificationBinding.verificationTxt.setOnClickListener(this)
        activityOtpVerificationBinding.resendTxt.setOnClickListener(this)
        getBundleData()

    }

    private fun getBundleData() {
        val extras = intent.extras
        if (null != extras) {
            if (extras.getString(PARAM_COMING_FROM) != null) {
                comingFrom = extras.getString(PARAM_COMING_FROM)!!
                email= extras.getString(PARAM_USER_NAME)!!
            } else {
                mobileNo = extras.getString(PARAM_MOBILE_NUMBER)!!
                isdCode= extras.getString(PARAM_ISD_CODE)!!
             //   activityOtpVerificationBinding.subHeaderTxt.setText(resources.getString(R.string.verification_code) + " " +isdCode+ mobileNo)
                val postRequestModel = PostRequestModel()
                postRequestModel.phone = mobileNo
              //  postRequestModel.isdCode =isdCode
                var commonModel = CommonValueModel()
           //     postApiCall(applicationContext, UrlManager.SENT_OTP, postRequestModel, commonModel)
            }
        }
    }
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.verificationTxt -> {
                checkValidation()
            }
            R.id.resendTxt -> {
                if (comingFrom != null&& comingFrom.equals(FORGOT_PASSWORD)) {
                    val postRequestModel = PostRequestModel()
                    postRequestModel.email = email
                    showLoader(resources.getString(R.string.please_wait))
                    var commonModel= CommonValueModel()
                  //  postApiCall(applicationContext, UrlManager.FORGOT_PASSWORD, postRequestModel, commonModel)
                }else{
                    val postRequestModel = PostRequestModel()
                    postRequestModel.phone = mobileNo
                   // postRequestModel.isdCode = isdCode

                    showLoader(resources.getString(R.string.please_wait))
                    var commonModel = CommonValueModel()
                  //  postApiCall(applicationContext, UrlManager.SENT_OTP, postRequestModel, commonModel)
                }
            }

        }
    }
    override fun onSuccess(result: String?, apiName: String?, disposable: Disposable?, commonModel: CommonValueModel?) {
        closeLoader()
        when (apiName) {
           /* UrlManager.SENT_OTP -> {
                val sentOtpModel: ResponseSentOtpModel? = APIClient.gsonAsConvert.fromJson(result, ResponseSentOtpModel::class.java)
                if (sentOtpModel != null) {
                    if (sentOtpModel.status.equals(STATUS_SUCCESS)) {
                        showSnackBar(activityOtpVerificationBinding.resendTxt, resources.getString(R.string.otp_sent_successfully))
                    }
                }
            }
            UrlManager.FORGOT_PASSWORD -> {
                val sentOtpModel: ResponseSentOtpModel? = APIClient.gsonAsConvert.fromJson(result, ResponseSentOtpModel::class.java)
                if (sentOtpModel != null) {
                    if (sentOtpModel.status.equals(STATUS_SUCCESS)) {
                        // showSnackBar(activityOtpVerificationBinding.resendTxt, resources.getString(R.string.otp_sent_successfully))
                        showSnackBar(activityOtpVerificationBinding.resendTxt, resources.getString(R.string.otp_sent_successfully))

                    }
                }
            }
            UrlManager.VERIFY_OTP_FORGOT -> {
                val sentOtpModel: ResponseForgotOtpModel? = APIClient.gsonAsConvert.fromJson(result, ResponseForgotOtpModel::class.java)
                if (sentOtpModel != null) {
                    if (sentOtpModel.status.equals(STATUS_SUCCESS)) {
                        val intent = Intent(this, ChangePasswordActivity::class.java)
                        intent.putExtra(PARAM_USER_NAME, email);
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                        finish()
                    }
                }
            }*/

            /* UrlManager.VERIFY_OTP -> {
                 val sentOtpModel: ResponseSentOtpModel? = APIClient.gsonAsConvert.fromJson<ResponseSentOtpModel>(result, ResponseSentOtpModel::class.java)

                 if (sentOtpModel != null) {
                     if (sentOtpModel.status.equals(STATUS_SUCCESS)) {
                         if (comingFrom != null && comingFrom.equals(FORGOT_PASSWORD)) {
                            *//* userPreference?.loginStatus = LOGIN_CHECK
                            userPreference?.save(this)*//*
                            val intent = Intent(this, ChangePasswordActivity::class.java)
                            startActivity(intent)
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                            finish()
                        } else {
                            userPreference?.loginStatus = LOGIN_CHECK
                            userPreference?.save(this)
                            val intent = Intent(this, DashboardActivity::class.java)
                            startActivity(intent)
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                            finish()
                        }

                    }


                }
            }*/
        }
    }

    override fun onFailure(message: String?, apiName: String?, disposable: Disposable?, commonModel: CommonValueModel?) {
        closeLoader()
        showSnackBar(activityOtpVerificationBinding.resendTxt, message)

    }



    private fun checkValidation() {
        if (TextUtils.isEmpty(activityOtpVerificationBinding.pinOneEdt.text.toString().trim())) {
            showSnackBar(activityOtpVerificationBinding.resendTxt, resources.getString(R.string.please_enter_otp))
        } else if (TextUtils.isEmpty(activityOtpVerificationBinding.pinTwoEdt.text.toString().trim())) {
            showSnackBar(activityOtpVerificationBinding.resendTxt, resources.getString(R.string.please_enter_otp))
        } else if (TextUtils.isEmpty(activityOtpVerificationBinding.pinThreeEdt.text.toString().trim())) {
            showSnackBar(activityOtpVerificationBinding.resendTxt, resources.getString(R.string.please_enter_otp))
        } else if (TextUtils.isEmpty(activityOtpVerificationBinding.pinFourEdt.text.toString().trim())) {
            showSnackBar(activityOtpVerificationBinding.resendTxt, resources.getString(R.string.please_enter_otp))
        } else if (TextUtils.isEmpty(activityOtpVerificationBinding.pinFiveEdt.text.toString().trim())) {
            showSnackBar(activityOtpVerificationBinding.resendTxt, resources.getString(R.string.please_enter_otp))
        } else if (TextUtils.isEmpty(activityOtpVerificationBinding.pinSixEdt.text.toString().trim())) {
            showSnackBar(activityOtpVerificationBinding.resendTxt, resources.getString(R.string.please_enter_otp))
        } else {
            val postRequestModel = PostRequestModel()
            val otp = activityOtpVerificationBinding.pinOneEdt.text.toString() +
                    activityOtpVerificationBinding.pinTwoEdt.text.toString() +
                    activityOtpVerificationBinding.pinThreeEdt.text.toString() +
                    activityOtpVerificationBinding.pinFourEdt.text.toString() +
                    activityOtpVerificationBinding.pinFiveEdt.text.toString() +
                    activityOtpVerificationBinding.pinSixEdt.text.toString()

            if ( comingFrom.equals(FORGOT_PASSWORD)) {
               // postRequestModel.otp = otp
                postRequestModel.email = email
//                showLoader(resources.getString(R.string.please_wait))

                var commonModel = CommonValueModel()

                val intent = Intent(this, ResetPasswordActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            //    postApiCall(applicationContext, UrlManager.VERIFY_OTP_FORGOT, postRequestModel, commonModel)
            }else{
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                //  postRequestModel.otp = otp
                //  var commonModel = CommonValueModel()
                // showLoader(resources.getString(R.string.please_wait))

                //   postApiCall(applicationContext, UrlManager.VERIFY_OTP, postRequestModel, commonModel)
            }

        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

}