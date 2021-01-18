package  com.numeroeins.arthros.patient.servermanager

interface UrlManager {
 companion object {
//   https://www.ushousingexchange.com/

  const val MAIN_URL = "https://www.ushousingexchange.com:5000/"
  const val AUTH = "Authorization"
  const val ACCEPT = "Accept: application/json"
  const val CONTENT_TYPE = "Content-type: application/json"

  const val RESET_PASSWORD = "user/reset-password"

  const val VERIFY_EMAIL= "api/v2/user/verify-email"
  const val FORGOT_PASSWORD= "api/v2/user/forgot-password"
  const val LOGIN_API= "api/v2/user/login"
  const val SENT_OTP= "api/v2/user/verify-email"
  const val VERIFY_EMAIL_OTP= "api/v2/user/verify-email-otp"
  const val VERIFY_OTP= "api/v2/user/verify-otp"
  const val FACEBOOK_LOGIN= "api/v2/user/socialAuth/{id}"
  const val VERIFY_OTP_FORGOT = "user/verify-otp"

  const val TOKEN = "token"






 }
}
