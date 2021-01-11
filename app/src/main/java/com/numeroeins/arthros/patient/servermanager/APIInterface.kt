package  com.numeroeins.arthros.patient.servermanager


import com.numeroeins.arthros.patient.servermanager.request.PostRequestModel
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*


interface APIInterface {
    /*const val BUYER_REGISTER= "api/v1/user/buyer/register"
    const val SELLER_REGISTER= "api/v1/user/seller/register"
    const val LOGIN_API= "api/v1/user/login"
    const val VERIFY_EMAIL= "api/v1/user/verify-email"
    const val VERIFY_EMAIL_OTP= "api/v1/user/verify-email-otp"
    const val VERIFY_RESET_MY_PASSWORD= "api/v1/user/reset-my-password"
  */


    @POST(UrlManager.GET_APPLICATION_STATUS)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getApplicationStatus(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @POST(UrlManager.MAKE_FAVORITES)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun makeFavorites(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @POST(UrlManager.FACEBOOK_LOGIN)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun social(@Path("id") id: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    /* @GET(UrlManager.GET_WISH_LIST)
     @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
     fun getFavoritesList(@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?*/



    @GET(UrlManager.GET_BUYER_PROFILE_DATA)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getBuyerProfile(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.GET_SELLER_PROFILE_DATA)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getSellerProfile(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.GET_WISH_LIST)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getFavoriteList(@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.GET_VISIT_LIST)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getVisitScheduleList(@Path("usertype") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?


    @GET(UrlManager.SELLER_DASHBOARD_PROPERTY_DETAIL)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getBuyerPropertyDetails(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?


    /// post api
    @Multipart
    @POST(UrlManager.uploadProfileImage)
    fun uploadProfileApi(@Header(UrlManager.TOKEN) token: String?, @Part file: MultipartBody.Part): Observable<ResponseBody?>?


    @Multipart
    @POST(UrlManager.GET_MY_GROUP_CHAT_SEND_MESSAGE_WITH_IMAGE)
    fun uploadChatImageWithMessage(@Header(UrlManager.TOKEN) token: String?, @Part file: MultipartBody.Part
                                   ,@Part("text") text: RequestBody?
                                   ,@Part("projectId") projectId: RequestBody?): Observable<ResponseBody?>?


    @Multipart
    @POST(UrlManager.SELLER_DASHBOARD_STEP_SEVEN)
    fun sellerPropertyStep7Api(@Header(UrlManager.TOKEN) token: String?
                               , @Part file: MultipartBody.Part

                               ,@Part("video_url") askingPrice: RequestBody?): Observable<ResponseBody?>?

    @Multipart
    @POST(UrlManager.SELLER_DASHBOARD_STEP_SEVEN)
    fun sellerPropertyStep71Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?
                               ,  @Part files: ArrayList<MultipartBody.Part>
                                ,  @Part files2: ArrayList<MultipartBody.Part>
                               ,@Part("video_url") askingPrice: RequestBody?): Observable<ResponseBody?>?







    @Multipart
    @POST(UrlManager.CONTRACTOR_JOB_COMMENT_CREATE)
    fun uploadCommentWithImage(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?
                               , @Part files: ArrayList<MultipartBody.Part>
                               ,@Part("text") text: RequestBody?): Observable<ResponseBody?>?



    @Multipart
    @POST(UrlManager.WORKER_JOB_COMMENT_CREATE)
    fun uploadWorkerCommentWithImage(@Path("id") id: String?
                               , @Part files: ArrayList<MultipartBody.Part>
                               ,@Part("text") text: RequestBody?): Observable<ResponseBody?>?



  /*  @POST(UrlManager.RESET_PASSWORD)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sentResetPasswordApi(@Body body: PostRequestModel?): Observable<ResponseBody?>?*/

    @POST(UrlManager.VERIFY_EMAIL_OTP)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sentVerifyEmailApi(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.VERIFY_OTP)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sentVerifyOTPApi(@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.VERIFY_EMAIL)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun verifyEmailApi(@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.SENT_OTP)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sentOtpOnEmailApi(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?


    @POST(UrlManager.ADD_BUYER_SCHEDULE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun addScheduleTime(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.LOGIN_API)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun loginApi(@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.FORGOT_PASSWORD)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun forgotPassword(@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.RESET_PASSWORD)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun resetforgotPassword(@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.BUYER_REGISTER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun buyerRegisterApi(@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.SELLER_REGISTER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sellerRegisterApi(@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.CONTRACTOR_REGISTER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun contractorRegisterApi(@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.AFFILIATE_REGISTER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun affiliateRegisterApi(@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.STEP_ONE_BORROWER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun borrowerStep1Api(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.STEP_TWO_BORROWER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun borrowerStep2Api(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.STEP_THREE_BORROWER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun borrowerStep3Api(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.STEP_FOUR_BORROWER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun borrowerStep4Api(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.STEP_FIVE_BORROWER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun borrowerStep5Api(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.STEP_SIX_BORROWER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun borrowerStep6Api(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.STEP_SEVEN_BORROWER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun borrowerStep7Api(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.STEP_EIGHT_BORROWER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun borrowerStep8Api(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?






    //// Start affiliator online as buyer
    @POST(UrlManager.STEP_ONE_AFFILIATE_BORROWER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun borrowerAffiliateStep1Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.STEP_TWO_AFFILIATE_BORROWER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun borrowerAffiliateStep2Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.STEP_THREE_AFFILIATE_BORROWER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun borrowerAffiliateStep3Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.STEP_FOUR_AFFILIATE_BORROWER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun borrowerAffiliateStep4Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.STEP_FIVE_AFFILIATE_BORROWER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun borrowerAffiliateStep5Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.STEP_SIX_AFFILIATE_BORROWER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun borrowerAffiliateStep6Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.STEP_SEVEN_AFFILIATE_BORROWER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun borrowerAffiliateStep7Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.STEP_EIGHT_AFFILIATE_BORROWER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun borrowerAffiliateStep8Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

//// end affiliator online as buyer


    @POST(UrlManager.SELLER_STEP_ONE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sellerStep1Api(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.SELLER_STEP_TWO)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sellerStep2Api(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @Multipart
    @POST(UrlManager.SELLER_STEP_THREE)
    fun sellerStep3Api(@Header(UrlManager.TOKEN) token: String?, @Part file: MultipartBody.Part
                       ,@Part("askingPrice") askingPrice: RequestBody?
                       ,@Part("knowPropertyVal") knowPropertyVal: Boolean?
                       ,@Part("propertyValue") propertyValue: RequestBody?
                       ,@Part("propertyAppraisal") propertyAppraisal: Boolean?
                       ,@Part("leftAtStep") leftAtStep: RequestBody?
                       ,@Part("allStepsCompleted") allStepsCompleted: Boolean?
        /*      ,@Part("appraisalFile") address: RequestBody?*/
                       ,@Part("propertyDetails") propertyDetails: RequestBody?
    ): Observable<ResponseBody?>?


    @POST(UrlManager.SELLER_STEP_FOUR)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sellerStep4Api(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?


    //// Start affiliator online as buyer
    @POST(UrlManager.SELLER_AFFILIATE_STEP_ONE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sellerAffiliateStep1Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.SELLER_AFFILIATE_STEP_TWO)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sellerAffiliateStep2Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @Multipart
    @POST(UrlManager.SELLER_AFFILIATE_STEP_THREE)
    fun sellerAffiliateStep3Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?, @Part file: MultipartBody.Part
                       ,@Part("askingPrice") askingPrice: RequestBody?
                       ,@Part("knowPropertyVal") knowPropertyVal: Boolean?
                       ,@Part("propertyValue") propertyValue: RequestBody?
                       ,@Part("propertyAppraisal") propertyAppraisal: Boolean?
                       ,@Part("leftAtStep") leftAtStep: RequestBody?
                       ,@Part("allStepsCompleted") allStepsCompleted: Boolean?
        /*      ,@Part("appraisalFile") address: RequestBody?*/
                       ,@Part("propertyDetails") propertyDetails: RequestBody?
    ): Observable<ResponseBody?>?


    @POST(UrlManager.SELLER_AFFILIATE_STEP_FOUR)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sellerAffiliateStep4Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?



    //// end affiliator online as buyer
    ///
    @POST(UrlManager.SELLER_DASHBOARD_STEP_ONE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sellerDashboardStep1Api(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.SELLER_DASHBOARD_STEP_TWO)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sellerDashboardStep2Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?


    @POST(UrlManager.SELLER_RENEW_PROPERTY)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sellerRenewPropertyApi(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?



    @POST(UrlManager.SELLER_DASHBOARD_STEP_THREE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sellerDashboardStep3Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.SELLER_DASHBOARD_STEP_FOUR)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sellerDashboardStep4Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.SELLER_DASHBOARD_STEP_FIVE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sellerDashboardStep5Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.SELLER_DASHBOARD_STEP_SIX)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sellerDashboardStep6Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.SELLER_DASHBOARD_STEP_EIGHT)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sellerDashboardStep8Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.SELLER_DASHBOARD_STEP_NINE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sellerDashboardStep9Api(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?


    @POST(UrlManager.GET_BUYER_PROPERTY)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getBuyerProperty(@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?


    @GET(UrlManager.REHAB_PACKAGE_LIST)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getRehabPackageList(@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.REHAB_PACKAGE_UPDATE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getRehabPackageUpdate(@Header(UrlManager.TOKEN) token: String?,@Path("id") id: String?): Observable<ResponseBody?>?


    @POST(UrlManager.SELLER_DASHBOARD_PROPERTY_LIST_SEARCH)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sellerDashboardPropertyList(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.SELLER_DASHBOARD_UPDATE_PROFILE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sellerUpdateProfile(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.SELLER_PROFILE_RESET_PASSWORD)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun sellerResetPassword(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?



    /*postRequestModel.leftAtStep = "2"
    postRequestModel.allStepsCompleted =false*/
  /*  mUri: String, firstName: String,  lastName: String
    , email: String,  address: String  , city: String,  stateProvince: String
    , zipCodeContractor: String,  mobileContractor: String
*/
    @Multipart
    @POST(UrlManager.CONTRACTOR_STEP_ONE)
    fun contractorStepOneApi(@Header(UrlManager.TOKEN) token: String?, @Part file: MultipartBody.Part
                       ,@Part("firstName") askingPrice: RequestBody?  ,@Part("lastName") knowPropertyVal: RequestBody?
                       ,@Part("email") email: RequestBody?            ,@Part("street_address") street_address: RequestBody?
                       ,@Part("city") city: RequestBody?              ,@Part("state") state: RequestBody?
                       ,@Part("zip") zip: RequestBody?
                             ,@Part("phone") phone: RequestBody?
                             ,@Part("leftAtStep") leftAtStep: RequestBody?
                             ,@Part("allStepsCompleted") allStepsCompleted: Boolean?
                       ): Observable<ResponseBody?>?

    @Multipart
    @POST(UrlManager.CONTRACTOR__STEP_TWO)
    fun contractorStepTwoApi(@Header(UrlManager.TOKEN) token: String?, @Part file: MultipartBody.Part
                             ,@Part("trades") skills: RequestBody?
                             ,@Part("yrsExp") yrsExp: RequestBody?
                             ,@Part("leftAtStep") leftAtStep: RequestBody?
                             ,@Part("allStepsCompleted") allStepsCompleted: Boolean?): Observable<ResponseBody?>?


    @Multipart
    @POST(UrlManager.UPLOAD_AFTER_BEFORE_DURING_IMAGE)
    fun contractorBeforeAfterDuringUploadApi(@Path("id") id: String?,
                                             @Header(UrlManager.TOKEN) token: String?
                                             ,@Part after: ArrayList<MultipartBody.Part>
                                             ,@Part before: ArrayList<MultipartBody.Part>
                                             ,@Part during: ArrayList<MultipartBody.Part>
                                             ): Observable<ResponseBody?>?


    @Multipart
    @POST(UrlManager.WORKER_UPLOAD_AFTER_BEFORE_DURING_IMAGE)
    fun workerBeforeAfterDuringUploadApi(@Path("id") id: String?
                                             ,@Part after: ArrayList<MultipartBody.Part>
                                             ,@Part before: ArrayList<MultipartBody.Part>
                                             ,@Part during: ArrayList<MultipartBody.Part>
                                             ): Observable<ResponseBody?>?





    @POST(UrlManager.WORKER_JOB_MARK_COMPLETE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun setWorkerJobMarkComplete(@Path("id") id: String?): Observable<ResponseBody?>?


    @DELETE(UrlManager.CONTRACTOR_JOB_MARK_COMPLETE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun setContractorJobMarkComplete(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @DELETE(UrlManager.CONTRACTOR_JOB_TASK_COMPLETE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun setContractorJobTaskComplete(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @POST(UrlManager.WORKER_JOB_TASK_COMPLETE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun setWorkerJobTaskComplete(@Path("id") id: String?): Observable<ResponseBody?>?

    @POST(UrlManager.CHECK_IN_CHECKOUT)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun checkInCheckOut(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @GET(UrlManager.CONTRACTOR_JOB_COMMENT_GET)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getContractorJobComment(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @POST(UrlManager.CONTRACTOR_JOB_COMMENT_DELETE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun setContractorCommentDelete(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @DELETE(UrlManager.CONTRACTOR_JOB_ACCEPT_DECLINED)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun setContractorAcceptedDecline(@Path("id") id: String?, @Path("job_status") job_status: Boolean?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.CONTRACTOR_JOB_GET_ALL_ACCEPTED)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getContractorAllAccepted(@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.CONTRACTOR_JOB_GET_ALL_COMPLETED)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getContractorComplitedJobs(@Path("id") id: String?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.CONTRACTOR_JOB_GET_ALL_ASSIGNED)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getContractorAllJobs(@Path("tradeType") tradeType: String?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.CONTRACTOR_PROFILE_FULL_DETAIL)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getContractorFullDetails(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.CONTRACTOR_JOB_DETAIL)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getContractorJobDetails(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.CONTRACTOR_PROJECT_DETAIL)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getProjectDetails(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.WORKER_JOB_DETAIL)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getWorkerJobDetails(@Path("id") id: String?): Observable<ResponseBody?>?

    @POST(UrlManager.CONTRACTOR_JOB_COMMENT_CREATE)
    @FormUrlEncoded
    fun setContractorCommentOnJob(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?,@Field("text") text: String?): Observable<ResponseBody?>?

    @POST(UrlManager.WORKER_JOB_COMMENT_CREATE)
    @FormUrlEncoded
    fun setWorkerCommentOnJob(@Path("id") id: String?,@Field("text") text: String?): Observable<ResponseBody?>?

    @GET(UrlManager.GET_CONTRACTOR_PROFILE_DATA)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getContractorProfileData(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.GET_MY_GROUP_LIST)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getMyGroupChat(@Query("page") page: Int?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?



    @GET(UrlManager.GET_MY_GROUP_CHAT_LIST)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getMyGroupChatList(@Path("id") id: String?,@Query("page") page: Int?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.GET_REHAB_LIST_DATA)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getRehabList(@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @POST(UrlManager.GET_MY_GROUP_CHAT_SEND_MESSAGE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getMyGroupSendChat(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @GET(UrlManager.TIME_LOG_LIST)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getTimeLog(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.WORKER_TIME_LOG_LIST)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getWorkerTimeLog(@Path("id") id: String?): Observable<ResponseBody?>?

    @GET(UrlManager.WORKER_JOB_COMMENT_GET)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getWorkerJobComment(@Path("id") id: String?): Observable<ResponseBody?>?

    @POST(UrlManager.AFFILIATE_GET_ALL_PROPERTIES)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getAffiliateAllProperties(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.AFFILIATE_GET_MY_SELLER_BUYER)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getAffiliateMyUserList(@Header(UrlManager.TOKEN) token: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.CHECK_IN_CHECKOUT)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun setCheckInOut(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @POST(UrlManager.WORKER_CHECK_IN_CHECKOUT)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun setWorkerCheckInOut(@Path("id") id: String?): Observable<ResponseBody?>?


    @POST(UrlManager.GET_VISIT_SELLER_RESCHEDULE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun setVisitSellerReschedule(@Path("id") id: String?,@Body body: PostRequestModel?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?



    @POST(UrlManager.GET_VISIT_BUYER_REJECT)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun setVisitBuyerReject(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?


    @POST(UrlManager.GET_VISIT_SELLER_REJECT)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun setVisitSellerReject(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?


    @POST(UrlManager.GET_VISIT_SELLER_ACCEPT)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun setVisitSellerAccept(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?



    @POST(UrlManager.GET_VISIT_BUYER_ACCEPT)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun setVisitBuyerAccept(@Path("id") id: String?,@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

////---------------------------------------------------------------------
  //  PreQualifyUpload file

    @Multipart
    @POST(UrlManager.UPLOAD_PRE_QUALIFICATION)
    fun preQualificationFullApi(@Path("id") id: String?
                                ,@Header(UrlManager.TOKEN) token: String?
                                , @Part driverLicense: ArrayList<MultipartBody.Part>
                                , @Part recentPaycheck: ArrayList<MultipartBody.Part>
                                ,  @Part twoYearW2: ArrayList<MultipartBody.Part>
                                ,  @Part twoYearReturn: ArrayList<MultipartBody.Part>
                                , @Part bankStatement: ArrayList<MultipartBody.Part>
                                /*three optional*/
                                , @Part experianCD:ArrayList<MultipartBody.Part>
                                , @Part transunionCD: ArrayList<MultipartBody.Part>
                                , @Part equifaxCD: ArrayList<MultipartBody.Part>
                                 /*three optional*/
                                , @Part("iiqUsername") iiqUsername: RequestBody?
                                , @Part("iiqPassword") iiqPassword: RequestBody?
                                , @Part("iiqSSN4Digit") iiqSSN4Digit: RequestBody?
                                , @Part("iiqSecurityQuestion") iiqSecurityQuestion: RequestBody?
                                , @Part("iiqSecurityAnswer") iiqSecurityAnswer: RequestBody?
                                , @Part("device_token") device_token: RequestBody?
                               ): Observable<ResponseBody?>?

    @Multipart
    @POST(UrlManager.ADMIN_HOME_DESIGN_PACKAGE_ADD)
    fun addHomeDesignPackageApi(
                                @Header(UrlManager.TOKEN) token: String?
                                , @Part pdfFile: ArrayList<MultipartBody.Part>
                                , @Part("name") name: RequestBody?
                               ): Observable<ResponseBody?>?

    @Multipart
    @POST(UrlManager.ADMIN_BEFORE_AFTER_ADD)
    fun addBeforeAfterImageApi(
                                @Header(UrlManager.TOKEN) token: String?
                                , @Part before: ArrayList<MultipartBody.Part>
                                , @Part after: ArrayList<MultipartBody.Part>
                               ): Observable<ResponseBody?>?


    @POST(UrlManager.ADMIN_LOGIN_API)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun adminLoginApi(@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @GET(UrlManager.ADMIN_DASHBOARD_API)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun adminDashboardApi(@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?


    @GET(UrlManager.ADMIN_PROJECT_LIST)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getAdminProjectList(@Query("page") page: Int?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?


    @GET(UrlManager.ADMIN_APPLICATION_LIST)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getAdminApplicantsList(@Path("type") type: String?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?


    @POST(UrlManager.ADMIN_NOTIFICATION_LIST)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getAdminNotificationList(@Query("page") page: Int?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?


    @POST(UrlManager.ADMIN_APPLICATION_STATUS_CHANGE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun adminChangeApplicantStatus(@Body body: PostRequestModel?, @Path("id") id: String?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?


    @DELETE(UrlManager.ADMIN_APPLICATION_STATUS_DELETE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun adminDeleteApplicantStatus(@Path("id") id: String?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.ADMIN_USER_DETAILS)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getAdminUserDetails(@Path("id") id: String?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @POST(UrlManager.ADMIN_PROPERTY_LIST)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun adminGetPropertyList(@Body body: PostRequestModel?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.ADMIN_HOME_DESIGN_PACKAGE_LIST)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getAdminHomePackageList(@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @DELETE(UrlManager.ADMIN_HOME_DESIGN_PACKAGE_DELETE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun adminDeleteHomePackageStatus(@Path("id") id: String?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.ADMIN_BEFORE_AFTER_GET_ALL)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getAdminAfterBeforeList(@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @DELETE(UrlManager.ADMIN_BEFORE_AFTER_DELETE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun adminDeleteAfterBeforeStatus(@Path("id") id: String?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.ADMIN_SCA_LIST)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getAdminSCAList(@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @DELETE(UrlManager.ADMIN_SCA_DELETE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun adminDeleteSCAStatus(@Path("id") id: String?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @POST(UrlManager.ADMIN_SCA_ADD)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun adminAddSCA(@Body body: PostRequestModel?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.ADMINS_LIST)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getAdminsList(@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @POST(UrlManager.ADMIN_ADD)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun adminAdd(@Body body: PostRequestModel?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @DELETE(UrlManager.ADMIN_DELETE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun adminDelete(@Path("id") id: String?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.ADMIN_ROLES_LIST)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getAdminRolesList(@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @GET(UrlManager.ADMIN_PERMISSION_LIST)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun getAdminPermissionList(@Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @POST(UrlManager.ADMIN_ROLE_ADD)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun adminRolesAdd(@Body body: PostRequestModel?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

    @DELETE(UrlManager.ADMIN_ROLE_DELETE)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun adminRoleDelete(@Path("id") id: String?, @Header(UrlManager.TOKEN) token: String?): Observable<ResponseBody?>?

}