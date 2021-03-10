package com.numeroeins.arthros.patient.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.databinding.DataBindingUtil
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.databinding.ActivityForgotPasswordBinding
import com.numeroeins.arthros.patient.servermanager.APIClient
import com.numeroeins.arthros.patient.servermanager.UrlManager
import com.numeroeins.arthros.patient.servermanager.request.CommonValueModel
import com.numeroeins.arthros.patient.servermanager.request.PostRequestModel
import com.numeroeins.arthros.patient.utility.*
import io.reactivex.disposables.Disposable

class ForgotActivity : BaseActivity(), View.OnClickListener {
    private var activityForgotPasswordBinding: ActivityForgotPasswordBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityForgotPasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        initView()
    }

    private fun initView() {


        activityForgotPasswordBinding?.forgotPasswordTxt?.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.forgotPasswordTxt -> {
                if (checkInternetConnection()) {
                    checkValidation()
                } else {
                    showSnackBar(activityForgotPasswordBinding?.forgotPasswordTxt!!, resources.getString(R.string.check_internet))
                }
            }


        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }


    private fun checkValidation() {
        val validate = Validate()
        if (TextUtils.isEmpty(activityForgotPasswordBinding!!.emailEdt.text.toString().trim())) {
            showSnackBar(activityForgotPasswordBinding!!.emailEdt, getResources().getString(R.string.error_email_address))
        }else  if (!validate.isEmailValid(activityForgotPasswordBinding!!.emailEdt.text.toString().trim())) {
            showSnackBar(activityForgotPasswordBinding!!.emailEdt, getResources().getString(R.string.error_email_validation))
        } else {
            val postRequestModel = PostRequestModel()
            postRequestModel.email = activityForgotPasswordBinding?.emailEdt?.text.toString().trim()
//            showLoader(resources.getString(R.string.please_wait))
            val intent = Intent(this, OtpVerificationActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            val commonModel= CommonValueModel()
           // postApiCall(applicationContext, UrlManager.FORGOT_PASSWORD, postRequestModel, commonModel)
        }
    }


    override fun onSuccess(result: String?, apiName: String?, disposable: Disposable?, commonModel: CommonValueModel?) {
        closeLoader()
        when (apiName) {
          //  UrlManager.FORGOT_PASSWORD -> {
                /*val sentOtpModel: ResponseSentOtpModel? = APIClient.gsonAsConvert.fromJson<ResponseSentOtpModel>(result, ResponseSentOtpModel::class.java)
                if (sentOtpModel != null) {
                    if (sentOtpModel.status.equals(STATUS_SUCCESS)) {
                        // showSnackBar(activityOtpVerificationBinding.resendTxt, resources.getString(R.string.otp_sent_successfully))
                        val intent = Intent(this, OtpVerificationActivity::class.java)
                        intent.putExtra(PARAM_COMING_FROM,FORGOT_PASSWORD)
                        intent.putExtra(PARAM_USER_NAME, activityForgotPasswordBinding?.emailEdt?.text.toString().trim())
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                        finish()
                    }
                }*/

         //   }

        }
    }

    override fun onFailure(message: String?, apiName: String?, disposable: Disposable?, commonModel: CommonValueModel?) {
        closeLoader()
        showSnackBar(activityForgotPasswordBinding?.emailEdt!!, message)
    }


}