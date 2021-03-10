package  com.numeroeins.arthros.patient.servermanager

interface UrlManager {
 companion object {
//   https://www.ushousingexchange.com/

  const val MAIN_URL = "https://numeroeinstesting.com/arthros_api_and_admin/api/"


 // Postman: https://www.getpostman.com/collections/bf3d135bb82f4e3cfaf6
 // serverUrl = https://numeroeinstesting.com/arthros_api_and_admin/api
  const val AUTH = "Authorization"
  const val ACCEPT = "Accept: application/json"
  const val CONTENT_TYPE = "Content-type: application/json"


  const val LOGIN_API= "login/patient"
  const val REGISTER_API= "register/patient"
  const val DOCTOR_LIST= "patient/doctors"




 /* const val RESET_PASSWORD = "user/reset-password"

  const val VERIFY_EMAIL= "api/v2/user/verify-email"
  const val FORGOT_PASSWORD= "api/v2/user/forgot-password"

  const val SENT_OTP= "api/v2/user/verify-email"
  const val VERIFY_EMAIL_OTP= "api/v2/user/verify-email-otp"
  const val VERIFY_OTP= "api/v2/user/verify-otp"
  const val FACEBOOK_LOGIN= "api/v2/user/socialAuth/{id}"
  const val VERIFY_OTP_FORGOT = "user/verify-otp"*/

  const val TOKEN = "token"

  const val PARAM_FIRST_LAST = "first_name"
  const val PARAM_EMAIL = "email"
  const val PARAM_PASSWORD = "password"
  const val PARAM_GENDER = "gender"
  const val PARAM_PHONE = "phone"

  const val PARAM_RATINGS = "ratings"
  const val PARAM_REVIEWER = "reviewer"
  const val PARAM_REVIEW_TO = "review_to"
  const val PARAM_COMMENT = "comment"





 }
}
