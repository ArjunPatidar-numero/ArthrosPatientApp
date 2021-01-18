package com.numeroeins.arthros.patient.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.material.snackbar.Snackbar
import com.numeroeins.arthros.patient.R


import com.numeroeins.arthros.patient.fragment.FragmentBaseListener
import com.numeroeins.arthros.patient.servermanager.APIClient.getClient
import com.numeroeins.arthros.patient.servermanager.APIClient.gsonAsConvert
import com.numeroeins.arthros.patient.servermanager.APIInterface
import com.numeroeins.arthros.patient.servermanager.UrlManager
import com.numeroeins.arthros.patient.servermanager.request.CommonValueModel
import com.numeroeins.arthros.patient.servermanager.request.GetRequestModel
import com.numeroeins.arthros.patient.servermanager.request.PostRequestModel
import com.numeroeins.arthros.patient.utility.*


import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.io.IOException


open class BaseActivity : AppCompatActivity() {
    private var medialType: String? = ""
    private var fragmentBaseListener: FragmentBaseListener? = null
    fun setOnFragmentListener(fragmentBaseListener: FragmentBaseListener?) {
        this.fragmentBaseListener = fragmentBaseListener
    }

    protected fun checkLocationPermission() {
        if (isBelowMarshmallow) {
            locationPermissionAllow()
        } else {
            val result = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION)
            if (result == PackageManager.PERMISSION_GRANTED) {
                locationPermissionAllow()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_LOCATION)
            }
        }

    }


    open fun checkReadWriteStoragePermission(medialTypePhoto: String) {
        this.medialType = medialTypePhoto
        if (isBelowMarshmallow) {
            readWriteStoragePermissionAllow(medialTypePhoto)
        } else {
            val result = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE)
            val result2 = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            val result3 = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)
            if (result == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED && result3 == PackageManager.PERMISSION_GRANTED) {
                if (fragmentBaseListener != null) {
                    fragmentBaseListener!!.onReadWriteStoragePermissionAllow(medialTypePhoto)
                } else {
                    readWriteStoragePermissionAllow(medialTypePhoto)
                }
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA), PERMISSION_REQUEST_READ_WRITE)
            }
        }
    }



    protected val isBelowMarshmallow: Boolean
        protected get() = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) true else false

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_LOCATION -> {
                if (grantResults.size > 0) {
                    val AccessfineLocation = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    if (AccessfineLocation) {
                        locationPermissionAllow()
                    } else {
                        locationPermissionDeny()
                    }
                }
            }
            PERMISSION_REQUEST_READ_WRITE -> {
                if (grantResults.size > 0) {
                    val readStrorage = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    val writeStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED
                    val camera = grantResults[2] == PackageManager.PERMISSION_GRANTED

                    if (fragmentBaseListener != null) {
                        if (readStrorage && writeStorage && camera) {
                            fragmentBaseListener!!. onReadWriteStoragePermissionAllow(medialType.toString());
                        } else {
                            fragmentBaseListener!!. onReadWriteStoragePermissionDeny(medialType.toString());
                        }
                    } else {
                        if (readStrorage && writeStorage && camera) {
                            readWriteStoragePermissionAllow(medialType.toString());
                        } else {
                            readWriteStoragePermissionDeny(medialType.toString());
                        }
                    }

                }
            }
        }
    }

    protected open fun readWriteStoragePermissionAllow(medialTypePhoto: String) {}
    protected open fun readWriteStoragePermissionDeny(medialTypePhoto: String) {}
    protected open fun locationPermissionDeny() {}
    protected open fun locationPermissionAllow() {}


    protected open fun onLocationPermissionSuccess() {
    }



    private var customProgress: CustomProgress? = null
    fun showLoader(vararg loadingMessage: String?): CustomProgress? {
        runOnUiThread {
            var message: String? = ""
            if (loadingMessage != null && loadingMessage.size > 0) {
                message = loadingMessage[0]
            }
            if (customProgress == null) customProgress = CustomProgress(this@BaseActivity)
            closeLoader()
            customProgress = customProgress!!.show(this@BaseActivity, message, false, false, null)
        }
        return customProgress
    }

    override fun onStop() {
        if (customProgress != null && customProgress!!.isShowing()) customProgress!!.dismiss()
        super.onStop()
    }

    fun closeLoader() {
        if (customProgress != null && customProgress!!.isShowing()) customProgress!!.dismiss()
    }

    fun getApiCall(context: Context?, pageName: String, requestObject: GetRequestModel, commonModel: CommonValueModel) {
        val jUser: UserPreference = UserPreference.getInstance(context!!)!!
        try {
            val jUser: UserPreference = UserPreference.getInstance(context)!!
            val apiInterface = getClient(context)!!.create(APIInterface::class.java)
            var call: Observable<ResponseBody?>? = null
            Log.d("JSON", " GETREQUEST" + gsonAsConvert.toJson(requestObject))
            when (pageName) {

             /*   UrlManager.REHAB_PACKAGE_LIST -> call = apiInterface.getRehabPackageList(jUser.accessToken)
                UrlManager.REHAB_PACKAGE_UPDATE -> call = apiInterface.getRehabPackageUpdate(jUser.accessToken,requestObject.id)

                UrlManager.SELLER_DASHBOARD_PROPERTY_DETAIL -> call = apiInterface.getBuyerPropertyDetails(requestObject.id,jUser.accessToken)
                UrlManager.GET_BUYER_PROFILE_DATA -> call = apiInterface.getBuyerProfile(requestObject.id,jUser.accessToken)
                UrlManager.GET_WISH_LIST -> call = apiInterface.getFavoriteList(jUser.accessToken)
                UrlManager.CONTRACTOR_JOB_DETAIL -> call = apiInterface.getContractorJobDetails(requestObject.id,jUser.accessToken)
                UrlManager.CONTRACTOR_PROJECT_DETAIL -> call = apiInterface.getProjectDetails(requestObject.id,jUser.accessToken)
                UrlManager.WORKER_JOB_DETAIL -> call = apiInterface.getWorkerJobDetails(requestObject.id)
                UrlManager.CONTRACTOR_PROFILE_FULL_DETAIL -> call = apiInterface.getContractorFullDetails(requestObject.id,jUser.accessToken)
                UrlManager.CONTRACTOR_JOB_GET_ALL_ASSIGNED -> call = apiInterface.getContractorAllJobs(requestObject.tradeType, jUser.accessToken)
                UrlManager.CONTRACTOR_JOB_GET_ALL_ACCEPTED -> call = apiInterface.getContractorAllAccepted(jUser.accessToken)
                UrlManager.CONTRACTOR_JOB_GET_ALL_COMPLETED -> call = apiInterface.getContractorComplitedJobs(requestObject.id,jUser.accessToken)
                UrlManager.GET_CONTRACTOR_PROFILE_DATA -> call = apiInterface.getContractorProfileData(requestObject.id,jUser.accessToken)


                UrlManager.WORKER_TIME_LOG_LIST -> call = apiInterface.getWorkerTimeLog(requestObject.id)
                UrlManager.WORKER_JOB_COMMENT_GET -> call = apiInterface.getWorkerJobComment(requestObject.id)
                UrlManager.CONTRACTOR_JOB_COMMENT_GET -> call = apiInterface.getContractorJobComment(requestObject.id,jUser.accessToken)


                UrlManager.GET_MY_GROUP_LIST -> call = apiInterface.getMyGroupChat(requestObject.page,jUser.accessToken)
                UrlManager.GET_MY_GROUP_CHAT_LIST -> call = apiInterface.getMyGroupChatList(requestObject.id,requestObject.page,jUser.accessToken)
                UrlManager.GET_REHAB_LIST_DATA -> call = apiInterface.getRehabList(jUser.accessToken)

                UrlManager.TIME_LOG_LIST -> call = apiInterface.getTimeLog(requestObject.id,jUser.accessToken)

                UrlManager.GET_SELLER_PROFILE_DATA -> call = apiInterface.getSellerProfile(requestObject.id,jUser.accessToken)

                UrlManager.GET_VISIT_LIST -> call = apiInterface.getVisitScheduleList(requestObject.usertype,jUser.accessToken)


                UrlManager.ADMIN_DASHBOARD_API -> call = apiInterface.adminDashboardApi(jUser.accessToken)
                UrlManager.ADMIN_PROJECT_LIST -> call = apiInterface.getAdminProjectList(requestObject.page,jUser.accessToken)
                UrlManager.ADMIN_APPLICATION_LIST -> call = apiInterface.getAdminApplicantsList(requestObject.type,jUser.accessToken)
                UrlManager.ADMIN_USER_DETAILS -> call = apiInterface.getAdminUserDetails(requestObject.id,jUser.accessToken)
                UrlManager.ADMIN_HOME_DESIGN_PACKAGE_LIST -> call = apiInterface.getAdminHomePackageList(jUser.accessToken)
                UrlManager.ADMIN_BEFORE_AFTER_GET_ALL -> call = apiInterface.getAdminAfterBeforeList(jUser.accessToken)
                UrlManager.ADMIN_SCA_LIST -> call = apiInterface.getAdminSCAList(jUser.accessToken)
                UrlManager.ADMINS_LIST -> call = apiInterface.getAdminsList(jUser.accessToken)
                UrlManager.ADMIN_ROLES_LIST -> call = apiInterface.getAdminRolesList(jUser.accessToken)
                UrlManager.ADMIN_PERMISSION_LIST -> call = apiInterface.getAdminPermissionList(jUser.accessToken)*/
            }
            if (call != null) {
                if (checkInternetConnection()) {
                    apiCall(call, pageName, commonModel)
                } else {
                    closeLoader()
                    showSnackBar(findViewById(R.id.content), resources.getString(R.string.check_internet))
                }
            }
        } catch (e: Exception) {
            closeLoader()
            showLongToast(e.message)
        }
    }

    fun postApiCall(context: Context?, pageName: String, requestObject: PostRequestModel, commonModel: CommonValueModel?) {
        val jUser: UserPreference = UserPreference.getInstance(context!!)!!
        try {
            showLog("JSON", " POSTREQUEST" + gsonAsConvert.toJson(requestObject))
            val apiInterface = getClient(context)!!.create(APIInterface::class.java)
            var call: Observable<ResponseBody?>? = null
            when (pageName) {
             /*   UrlManager.ADD_BUYER_SCHEDULE -> call = apiInterface.addScheduleTime(jUser.accessToken,requestObject)
                UrlManager.GET_BUYER_PROPERTY -> call = apiInterface.getBuyerProperty(jUser.accessToken)
                UrlManager.BUYER_REGISTER -> call = apiInterface.buyerRegisterApi(requestObject)
                UrlManager.SELLER_REGISTER -> call = apiInterface.sellerRegisterApi(requestObject)
                UrlManager.CONTRACTOR_REGISTER -> call = apiInterface.contractorRegisterApi(requestObject)
                UrlManager.AFFILIATE_REGISTER -> call = apiInterface.affiliateRegisterApi(requestObject)
                UrlManager.SENT_OTP -> call = apiInterface.sentOtpOnEmailApi(jUser.accessToken,requestObject)
                UrlManager.VERIFY_EMAIL_OTP -> call = apiInterface.sentVerifyEmailApi(jUser.accessToken,requestObject)
                UrlManager.VERIFY_OTP -> call = apiInterface.sentVerifyOTPApi(requestObject)
                UrlManager.LOGIN_API -> call = apiInterface.loginApi(requestObject)
                UrlManager.FORGOT_PASSWORD -> call = apiInterface.forgotPassword(requestObject)
                UrlManager.RESET_PASSWORD -> call = apiInterface.resetforgotPassword(requestObject)
                UrlManager.VERIFY_EMAIL -> call = apiInterface.verifyEmailApi(requestObject)
                // actual  buyer  apis
                UrlManager.STEP_ONE_BORROWER -> call = apiInterface.borrowerStep1Api(jUser.accessToken,requestObject)
                UrlManager.STEP_TWO_BORROWER -> call = apiInterface.borrowerStep2Api(jUser.accessToken,requestObject)
                UrlManager.STEP_THREE_BORROWER -> call = apiInterface.borrowerStep3Api(jUser.accessToken,requestObject)
                UrlManager.STEP_FOUR_BORROWER -> call = apiInterface.borrowerStep4Api(jUser.accessToken,requestObject)
                UrlManager.STEP_FIVE_BORROWER -> call = apiInterface.borrowerStep5Api(jUser.accessToken,requestObject)
                UrlManager.STEP_SIX_BORROWER -> call = apiInterface.borrowerStep6Api(jUser.accessToken,requestObject)
                UrlManager.STEP_SEVEN_BORROWER -> call = apiInterface.borrowerStep7Api(jUser.accessToken,requestObject)
                UrlManager.STEP_EIGHT_BORROWER -> call = apiInterface.borrowerStep8Api(jUser.accessToken,requestObject)

                //// Start affiliator online as buyer
                UrlManager.STEP_ONE_AFFILIATE_BORROWER -> call = apiInterface.borrowerAffiliateStep1Api(jUser.buyer_seller_id,jUser.accessToken,requestObject)
                UrlManager.STEP_TWO_AFFILIATE_BORROWER -> call = apiInterface.borrowerAffiliateStep2Api(jUser.buyer_seller_id,jUser.accessToken,requestObject)
                UrlManager.STEP_THREE_AFFILIATE_BORROWER -> call = apiInterface.borrowerAffiliateStep3Api(jUser.buyer_seller_id,jUser.accessToken,requestObject)
                UrlManager.STEP_FOUR_AFFILIATE_BORROWER -> call = apiInterface.borrowerAffiliateStep4Api(jUser.buyer_seller_id,jUser.accessToken,requestObject)
                UrlManager.STEP_FIVE_AFFILIATE_BORROWER -> call = apiInterface.borrowerAffiliateStep5Api(jUser.buyer_seller_id,jUser.accessToken,requestObject)
                UrlManager.STEP_SIX_AFFILIATE_BORROWER -> call = apiInterface.borrowerAffiliateStep6Api(jUser.buyer_seller_id,jUser.accessToken,requestObject)
                UrlManager.STEP_SEVEN_AFFILIATE_BORROWER -> call = apiInterface.borrowerAffiliateStep7Api(jUser.buyer_seller_id,jUser.accessToken,requestObject)
                UrlManager.STEP_EIGHT_AFFILIATE_BORROWER -> call = apiInterface.borrowerAffiliateStep8Api(jUser.buyer_seller_id,jUser.accessToken,requestObject)
                //// end affiliator online as buyer


                //Seller apis
                UrlManager.SELLER_AFFILIATE_STEP_ONE -> call = apiInterface.sellerAffiliateStep1Api(jUser.buyer_seller_id,jUser.accessToken,requestObject)
                UrlManager.SELLER_AFFILIATE_STEP_TWO -> call = apiInterface.sellerAffiliateStep2Api(jUser.buyer_seller_id,jUser.accessToken,requestObject)
                UrlManager.SELLER_AFFILIATE_STEP_FOUR -> call = apiInterface.sellerAffiliateStep4Api(jUser.buyer_seller_id,jUser.accessToken,requestObject)

                UrlManager.SELLER_STEP_ONE -> call = apiInterface.sellerStep1Api(jUser.accessToken,requestObject)
                UrlManager.SELLER_STEP_TWO -> call = apiInterface.sellerStep2Api(jUser.accessToken,requestObject)
                UrlManager.SELLER_STEP_FOUR -> call = apiInterface.sellerStep4Api(jUser.accessToken,requestObject)

                UrlManager.SELLER_RENEW_PROPERTY -> call = apiInterface.sellerRenewPropertyApi(requestObject.id,jUser.accessToken)

                UrlManager.SELLER_DASHBOARD_STEP_ONE -> call = apiInterface.sellerDashboardStep1Api(jUser.accessToken,requestObject)
                UrlManager.SELLER_DASHBOARD_STEP_TWO -> call = apiInterface.sellerDashboardStep2Api(requestObject.id,jUser.accessToken,requestObject)
                UrlManager.SELLER_DASHBOARD_STEP_THREE -> call = apiInterface.sellerDashboardStep3Api(requestObject.id,jUser.accessToken,requestObject)
                UrlManager.SELLER_DASHBOARD_STEP_FOUR -> call = apiInterface.sellerDashboardStep4Api(requestObject.id,jUser.accessToken,requestObject)
                UrlManager.SELLER_DASHBOARD_STEP_FIVE -> call = apiInterface.sellerDashboardStep5Api(requestObject.id,jUser.accessToken,requestObject)
                UrlManager.SELLER_DASHBOARD_STEP_SIX -> call = apiInterface.sellerDashboardStep6Api(requestObject.id,jUser.accessToken,requestObject)
                UrlManager.SELLER_DASHBOARD_STEP_EIGHT -> call = apiInterface.sellerDashboardStep8Api(requestObject.id,jUser.accessToken,requestObject)
                UrlManager.SELLER_DASHBOARD_STEP_NINE -> call = apiInterface.sellerDashboardStep9Api(requestObject.id,jUser.accessToken,requestObject)
                UrlManager.SELLER_DASHBOARD_PROPERTY_LIST_SEARCH -> call = apiInterface.sellerDashboardPropertyList(jUser.accessToken,requestObject)
                UrlManager.SELLER_DASHBOARD_UPDATE_PROFILE -> call = apiInterface.sellerUpdateProfile(jUser.accessToken,requestObject)
                UrlManager.SELLER_PROFILE_RESET_PASSWORD -> call = apiInterface.sellerResetPassword(jUser.accessToken,requestObject)
                UrlManager.GET_APPLICATION_STATUS -> call = apiInterface.getApplicationStatus(requestObject.id,jUser.accessToken)
                UrlManager.MAKE_FAVORITES -> call = apiInterface.makeFavorites(requestObject.id,jUser.accessToken)
                UrlManager.FACEBOOK_LOGIN -> call = apiInterface.social(requestObject.id,requestObject)

                UrlManager.CONTRACTOR_JOB_ACCEPT_DECLINED -> call = apiInterface.setContractorAcceptedDecline(requestObject.id, requestObject.job_status, jUser.accessToken)
                UrlManager.CONTRACTOR_JOB_COMMENT_DELETE -> call = apiInterface.setContractorCommentDelete(requestObject.id,jUser.accessToken)
                UrlManager.CONTRACTOR_JOB_MARK_COMPLETE -> call = apiInterface.setContractorJobMarkComplete(requestObject.id,jUser.accessToken)
                UrlManager.CONTRACTOR_JOB_COMMENT_CREATE -> call = apiInterface.setContractorCommentOnJob(requestObject.id,jUser.accessToken,requestObject.text)
                UrlManager.CONTRACTOR_JOB_TASK_COMPLETE -> call = apiInterface.setContractorJobTaskComplete(requestObject.id,jUser.accessToken)

                UrlManager.WORKER_JOB_COMMENT_CREATE -> call = apiInterface.setWorkerCommentOnJob(requestObject.id,requestObject.text)
                UrlManager.WORKER_JOB_TASK_COMPLETE -> call = apiInterface.setWorkerJobTaskComplete(requestObject.id)
                UrlManager.WORKER_JOB_MARK_COMPLETE -> call = apiInterface.setWorkerJobMarkComplete(requestObject.id)
                UrlManager.CONTRACTOR_JOB_MARK_COMPLETE -> call = apiInterface.setContractorJobMarkComplete(requestObject.id,jUser.accessToken)


                UrlManager.AFFILIATE_GET_ALL_PROPERTIES -> call = apiInterface.getAffiliateAllProperties(jUser.accessToken,requestObject)
                UrlManager.AFFILIATE_GET_MY_SELLER_BUYER -> call = apiInterface.getAffiliateMyUserList(jUser.accessToken,requestObject)

                UrlManager.GET_MY_GROUP_CHAT_SEND_MESSAGE -> call = apiInterface.getMyGroupSendChat(jUser.accessToken,requestObject)


                UrlManager.WORKER_CHECK_IN_CHECKOUT -> call = apiInterface.setWorkerCheckInOut(requestObject.id)
                UrlManager.CHECK_IN_CHECKOUT -> call = apiInterface.setCheckInOut(requestObject.id,jUser.accessToken)

                UrlManager.GET_VISIT_SELLER_RESCHEDULE -> call = apiInterface.setVisitSellerReschedule(requestObject.id,requestObject,jUser.accessToken)
                UrlManager.GET_VISIT_BUYER_ACCEPT -> call = apiInterface.setVisitBuyerAccept(requestObject.id,jUser.accessToken)
                UrlManager.GET_VISIT_SELLER_ACCEPT -> call = apiInterface.setVisitSellerAccept(requestObject.id,jUser.accessToken)
                UrlManager.GET_VISIT_SELLER_REJECT -> call = apiInterface.setVisitSellerReject(requestObject.id,jUser.accessToken)
                UrlManager.GET_VISIT_BUYER_REJECT -> call = apiInterface.setVisitBuyerReject(requestObject.id,jUser.accessToken)


                UrlManager.ADMIN_LOGIN_API -> call = apiInterface.adminLoginApi(requestObject)
                UrlManager.ADMIN_NOTIFICATION_LIST -> call = apiInterface.getAdminNotificationList(requestObject.page,jUser.accessToken)
                UrlManager.ADMIN_APPLICATION_STATUS_CHANGE -> call = apiInterface.adminChangeApplicantStatus(requestObject, requestObject.id, jUser.accessToken)
                UrlManager.ADMIN_APPLICATION_STATUS_DELETE -> call = apiInterface.adminDeleteApplicantStatus(requestObject.id, jUser.accessToken)
                UrlManager.ADMIN_PROPERTY_LIST -> call = apiInterface.adminGetPropertyList(requestObject, jUser.accessToken)
                UrlManager.ADMIN_HOME_DESIGN_PACKAGE_DELETE -> call = apiInterface.adminDeleteHomePackageStatus(requestObject.id, jUser.accessToken)
                UrlManager.ADMIN_BEFORE_AFTER_DELETE -> call = apiInterface.adminDeleteAfterBeforeStatus(requestObject.id, jUser.accessToken)
                UrlManager.ADMIN_SCA_DELETE -> call = apiInterface.adminDeleteSCAStatus(requestObject.id, jUser.accessToken)
                UrlManager.ADMIN_SCA_ADD -> call = apiInterface.adminAddSCA(requestObject, jUser.accessToken)
                UrlManager.ADMIN_ADD -> call = apiInterface.adminAdd(requestObject, jUser.accessToken)
                UrlManager.ADMIN_DELETE -> call = apiInterface.adminDelete(requestObject.id, jUser.accessToken)
                UrlManager.ADMIN_ROLE_ADD -> call = apiInterface.adminRolesAdd(requestObject, jUser.accessToken)
                UrlManager.ADMIN_ROLE_DELETE -> call = apiInterface.adminRoleDelete(requestObject.id, jUser.accessToken)
*/
                else -> {
                    closeLoader()
                    showLongToast("API post case not handled")
                }
            }
            if (call != null) {
                if (checkInternetConnection()) {
                    apiCall(call, pageName, commonModel!!)
                } else {
                    closeLoader()
                    Toast.makeText(context, resources.getString(R.string.check_internet), Toast.LENGTH_SHORT).show()
                    //   showSnackBar(findViewById(R.id.content), resources.getString(R.string.check_internet))
                }
            }
        } catch (e: Exception) {
            //   closeLoader()
            showLongToast(e.message)
        }
    }

    protected fun showSnackBar(view: View, message: String?) {
        val snackbar: Snackbar
        snackbar = Snackbar.make(view, message!!, Snackbar.LENGTH_SHORT)
        val snackBarView = snackbar.view
        snackBarView.background = view.context.resources.getDrawable(R.drawable.text_shaded)
        val textView = snackBarView.findViewById<TextView>(R.id.snackbar_text)
        textView.setTextColor(Color.WHITE)
        snackbar.show()
    }

    fun checkInternetConnection(): Boolean {
        val manager = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        val ni = manager.activeNetworkInfo
        return ni != null && ni.state == NetworkInfo.State.CONNECTED
    }

    protected fun showLongToast(message: String?) {
        val toast = Toast.makeText(applicationContext, message, Toast.LENGTH_LONG)
        if (toast != null) {
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }

    var disposable: Disposable? = null

    @SuppressLint("CheckResult")
    private fun apiCall(call: Observable<ResponseBody?>, apiName: String, commonModel: CommonValueModel) {

        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object :
            Observer<ResponseBody?> {
            override fun onSubscribe(d: Disposable) {
                disposable = d;
            }

            override fun onError(t: Throwable) {
                if (fragmentBaseListener != null) {
                    fragmentBaseListener!!.onFragmentApiFailure("Something went wrong, please try again.", apiName, disposable)
                } else {
                    this@BaseActivity.onFailure("Something went wrong, please try again.", apiName, disposable, commonModel)
                }
            }

            override fun onComplete() {
                showLog("onComplete", "onCompleted")

            }


            override fun onNext(response: ResponseBody) {
                try {
                    val result: String = response.string()
                    showLog("@@@@@@@@Response ", "apiName" + result)
                    if (fragmentBaseListener != null) {
                        fragmentBaseListener!!.onFragmentApiSuccess(result, apiName, disposable, commonModel)
                    } else {
                        onSuccess(result, apiName, disposable, commonModel)
                    }
                } catch (e: IOException) {
                    showLog("onError", e.toString())
                }
            }
        })
    }


    fun showLog(title: String?, message: String) {
        Log.e(title, "" + message)
    }

    protected open fun onSuccess(result: String?, apiName: String?, disposable: Disposable?, commonModel: CommonValueModel?) {}
    protected open fun onFailure(message: String?, apiName: String?, disposable: Disposable?, commonModel: CommonValueModel?) {}


    protected fun callVerificationApi(otp: String?) {}


    fun getKmFromLatLong(lat1: Double, lng1: Double, lat2: Float, lng2: Float): Float {
        val loc1 = Location("")
        loc1.setLatitude(lat1.toDouble())
        loc1.setLongitude(lng1.toDouble())
        val loc2 = Location("")
        loc2.setLatitude(lat2.toDouble())
        loc2.setLongitude(lng2.toDouble())
        val distanceInMeters: Float = loc1.distanceTo(loc2)
        return distanceInMeters / 1000
    }


    fun logOutPrompt(activity: Activity, userPreference:UserPreference)
    {
        val builder: AlertDialog.Builder =
            AlertDialog.Builder(activity) //Home is name of the activity

        builder.setMessage("Do you want to Logout?")
        builder.setPositiveButton("OK",
            DialogInterface.OnClickListener { dialog, id ->
                userPreference?.clear(activity)
                val intent = Intent(activity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra(PARAM_IS_COMING_BACK, true)
                startActivity(intent)
                activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                finish()
            })

        builder.setNegativeButton("CANCEL",
            DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        val alert: AlertDialog = builder.create()
        alert.show()

    }



    fun backPrompt(activity: Activity)
    {
        val builder: AlertDialog.Builder =
            AlertDialog.Builder(activity) //Home is name of the activity

        builder.setMessage("Do you want to go back Your might be lose your data.")
        builder.setPositiveButton("OK",
            DialogInterface.OnClickListener { dialog, id ->

                finish()
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
            })

        builder.setNegativeButton("CANCEL",
            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })

        val alert: AlertDialog = builder.create()
        alert.show()

    }

    fun logOutPrompt(activity: Activity/*,userPreference:UserPreference*/)
    {


        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        builder.setMessage("Do you want to Logout?")
        builder.setPositiveButton("OK",
            DialogInterface.OnClickListener { dialog, id ->
                UserPreference.getInstance(this)!!.clear(activity)
                val intent = Intent(activity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                finish()
            })

        builder.setNegativeButton("CANCEL",
            DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })

        val alert: AlertDialog = builder.create()
        alert.show()

    }

}





