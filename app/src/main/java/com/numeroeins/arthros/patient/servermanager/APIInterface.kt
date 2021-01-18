package  com.numeroeins.arthros.patient.servermanager


import com.numeroeins.arthros.patient.servermanager.request.PostRequestModel
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*


interface APIInterface {
    @POST(UrlManager.FACEBOOK_LOGIN)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun social(@Path("id") id: String?,@Body body: PostRequestModel?): Observable<ResponseBody?>?

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

    @POST(UrlManager.LOGIN_API)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun loginApi(@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.FORGOT_PASSWORD)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun forgotPassword(@Body body: PostRequestModel?): Observable<ResponseBody?>?

    @POST(UrlManager.RESET_PASSWORD)
    @Headers(UrlManager.ACCEPT, UrlManager.CONTENT_TYPE)
    fun resetForgotPassword(@Body body: PostRequestModel?): Observable<ResponseBody?>?

}