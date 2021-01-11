package  com.numeroeins.arthros.patient.servermanager

interface UrlManager {
 companion object {
//   https://www.ushousingexchange.com/

  const val MAIN_URL = "https://www.ushousingexchange.com:5000/"
  const val AUTH = "Authorization"
  const val ACCEPT = "Accept: application/json"
  const val CONTENT_TYPE = "Content-type: application/json"

  //const val FACEBOOK_LOGIN_API= "api/v2user/socialAuth/{provider}"
  //api/v2/user/buyer/register
  const val BUYER_REGISTER= "api/v2/user/buyer/register"
  const val SELLER_REGISTER= "api/v2/user/seller/register"
  const val CONTRACTOR_REGISTER= "/api/v2/user/contractor/register"
  const val AFFILIATE_REGISTER= "api/v2/user/affiliate/register"

  const val VERIFY_EMAIL= "api/v2/user/verify-email"
  const val FORGOT_PASSWORD= "api/v2/user/forgot-password"
  const val LOGIN_API= "api/v2/user/login"
  const val SENT_OTP= "api/v2/user/verify-email"
  const val VERIFY_EMAIL_OTP= "api/v2/user/verify-email-otp"
  const val VERIFY_OTP= "api/v2/user/verify-otp"

  ///api/v2/user/borrower/stepOne
  ///api/v2/user/borrower/stepOne
  const val GET_BUYER_PROPERTY= "api/v2/user/buyer/properties"
  const val GET_WISH_LIST= "api/v2/user/property/get/favorite"
  const val FACEBOOK_LOGIN= "api/v2/user/socialAuth/{id}"
  const val GET_APPLICATION_STATUS= "api/v2/user/user-application-status/{id}"
  const val uploadProfileImage= "api/v2/user/userImage"
  const val MAKE_FAVORITES= "api/v2/user/property/add/favorite/{id}"


  const val GET_SELLER_PROFILE_DATA= "api/v2/user/seller/application/{id}"
  ///api/v2/user/property/add/favorite
  /* To upload a users dashboard profile Image:
   54.166.247.66:4000/api/v2/user/userImage
   Key :- image
   POST Multipart*/
  const val STEP_ONE_BORROWER= "api/v2/user/borrower/stepOne"
  const val STEP_TWO_BORROWER= "api/v2/user/borrower/stepTwo"
  const val STEP_THREE_BORROWER= "api/v2/user/borrower/stepThree"
  const val STEP_FOUR_BORROWER= "api/v2/user/borrower/stepFour"
  const val STEP_FIVE_BORROWER= "api/v2/user/borrower/stepFive"
  const val STEP_SIX_BORROWER= "api/v2/user/borrower/stepSix"
  const val STEP_SEVEN_BORROWER= "/api/v2/user/borrower/stepSeven"
  const val STEP_EIGHT_BORROWER= "/api/v2/user/borrower/stepEight"



  const val STEP_ONE_AFFILIATE_BORROWER= "api/v2/user/borrower/stepOne/{id}"
  const val STEP_TWO_AFFILIATE_BORROWER= "api/v2/user/borrower/stepTwo/{id}"
  const val STEP_THREE_AFFILIATE_BORROWER= "api/v2/user/borrower/stepThree/{id}"
  const val STEP_FOUR_AFFILIATE_BORROWER= "api/v2/user/borrower/stepFour/{id}"
  const val STEP_FIVE_AFFILIATE_BORROWER= "api/v2/user/borrower/stepFive/{id}"
  const val STEP_SIX_AFFILIATE_BORROWER= "api/v2/user/borrower/stepSix/{id}"
  const val STEP_SEVEN_AFFILIATE_BORROWER= "/api/v2/user/borrower/stepSeven/{id}"
  const val STEP_EIGHT_AFFILIATE_BORROWER= "/api/v2/user/borrower/stepEight/{id}"

  const val TOKEN = "token"

  const val SELLER_STEP_ONE= "api/v2/user/seller/stepOne"
  const val SELLER_STEP_TWO= "api/v2/user/seller/stepTwo"
  const val SELLER_STEP_THREE= "api/v2/user/seller/stepThree"
  const val SELLER_STEP_FOUR= "api/v2/user/seller/stepFour"


  const val SELLER_AFFILIATE_STEP_ONE= "api/v2/user/seller/stepOne/{id}"
  const val SELLER_AFFILIATE_STEP_TWO= "api/v2/user/seller/stepTwo/{id}"
  const val SELLER_AFFILIATE_STEP_THREE= "api/v2/user/seller/stepThree/{id}"
  const val SELLER_AFFILIATE_STEP_FOUR= "api/v2/user/seller/stepFour/{id}"

  const val CONTRACTOR_STEP_ONE= "api/v2/user/contractor/stepOne"
  const val CONTRACTOR__STEP_TWO= "api/v2/user/contractor/stepTwo"
  const val SELLER_RENEW_PROPERTY= "api/v2/user/seller/dashboard/add-property/stepOne/{id}"
  const val ADD_BUYER_SCHEDULE= "api/v2/visit/buyer/add"
  const val UPLOAD_AFTER_BEFORE_DURING_IMAGE= "api/v2/project/job/beforeafterimage/{id}"

  const val SELLER_DASHBOARD_STEP_ONE= "api/v2/user/seller/dashboard/add-property/stepOne"
  const val SELLER_DASHBOARD_STEP_TWO= "api/v2/user/seller/dashboard/add-property/stepTwo/{id}"
  const val SELLER_DASHBOARD_STEP_THREE= "api/v2/user/seller/dashboard/add-property/stepThree/{id}"
  const val SELLER_DASHBOARD_STEP_FOUR= "api/v2/user/seller/dashboard/add-property/stepFour/{id}"
  const val SELLER_DASHBOARD_STEP_FIVE= "api/v2/user/seller/dashboard/add-property/stepFive/{id}"
  const val SELLER_DASHBOARD_STEP_SIX= "api/v2/user/seller/dashboard/add-property/stepSix/{id}"
  const val SELLER_DASHBOARD_STEP_SEVEN= "/api/v2/user/seller/dashboard/add-property/stepSeven/{id}"
  const val SELLER_DASHBOARD_STEP_EIGHT= "api/v2/user/seller/dashboard/add-property/stepEight/{id}"
  const val SELLER_DASHBOARD_STEP_NINE= "api/v2/user/seller/dashboard/add-property/stepNine/{id}"

  const val UPLOAD_PRE_QUALIFICATION= "api/v2/user/borrower/prequalify/{id}"




  const val GET_REHAB_LIST_DATA= "api/v2/admin/property/before/after/getAll"

  const val SELLER_DASHBOARD_PROPERTY_LIST_SEARCH= "api/v2/user/seller/properties"
  const val SELLER_DASHBOARD_UPDATE_PROFILE= "api/v2/user/borrower/dashboard/my-profile"
  const val GET_BUYER_PROFILE_DATA= "api/v2/user/application/{id}"
  const val RESET_PASSWORD= "api/v2/user/reset-password"
  const val SELLER_PROFILE_RESET_PASSWORD= "api/v2/user/reset-my-password"
  const val SELLER_DASHBOARD_PROPERTY_DETAIL= "api/v2/user/property/details/{id}"
  const val CONTRACTOR_PROFILE_FULL_DETAIL= "api/v2/user/borrower/dashboard/my-profile/{id}"
  const val CONTRACTOR_JOB_GET_ALL_ASSIGNED = "api/v2/project/job/getAll/pending/{tradeType}"
  const val CONTRACTOR_JOB_GET_ALL_ACCEPTED = "api/v2/project/job/getAll/accepted"
  const val CONTRACTOR_JOB_ACCEPT_DECLINED = "api/v2/project/job/contractor/acceptdecline/{id}/{job_status}"
  const val CONTRACTOR_JOB_COMMENT_CREATE = "api/v2/project/job/comment/create/{id}"
  const val CONTRACTOR_JOB_COMMENT_DELETE = "api/v2/project/job/comment/delete/{id}"
  const val CONTRACTOR_JOB_DETAIL = "api/v2/project/job/get/{id}"
  const val CONTRACTOR_PROJECT_DETAIL = "api/v2/project/detail/{id}"
  const val WORKER_JOB_DETAIL = "api/v2/project/worker/job/get/{id}"
  const val CONTRACTOR_JOB_COMMENT_GET = "api/v2/project/job/comment/get/{id}"


  const val CONTRACTOR_JOB_MARK_COMPLETE = "/api/v2/project/job/mark-complete/{id}"
  const val REHAB_PACKAGE_LIST = "/api/v2/rehab/package/list"
  const val REHAB_PACKAGE_UPDATE = "/api/v2/rehab/package/set/{id}"


  const val WORKER_UPLOAD_AFTER_BEFORE_DURING_IMAGE= "api/v2/project/worker/job/beforeafterimage/{id}"
  const val WORKER_JOB_COMMENT_CREATE = "api/v2/project/worker/job/comment/create/{id}"
  const val WORKER_JOB_COMMENT_GET = "api/v2/project/worker/job/comment/get/{id}"
  const val WORKER_TIME_LOG_LIST= "/api/v2/project/worker/job/checkin/getAll/{id}"
  const val WORKER_CHECK_IN_CHECKOUT= "/api/v2/project/worker/job/checkin/checkout/{id}"
  const val WORKER_JOB_MARK_COMPLETE = "/api/v2/project/worker/job/mark-complete/{id}"
  const val WORKER_JOB_TASK_COMPLETE = "/api/v2/project/worker/job/task/complete/{id}"

  const val CONTRACTOR_JOB_TASK_COMPLETE = "/api/v2/project/job/task/complete/{id}"
  const val CONTRACTOR_JOB_GET_ALL_COMPLETED = "/api/v2/project/job/completed-list/{id}"
  const val GET_CONTRACTOR_PROFILE_DATA= "api/v2/user/contractor/application/{id}"

  const val AFFILIATE_GET_ALL_PROPERTIES = "/api/v2/user/seller/properties"
  const val AFFILIATE_GET_MY_SELLER_BUYER = "/api/v2/user/affiliate/get-my-seller-buyer-list"

  const val GET_MY_GROUP_LIST = "/api/v2/project/list/my"
  const val GET_MY_GROUP_CHAT_LIST = "/api/v2/project/chat/getAll/{id}"
  const val GET_MY_GROUP_CHAT_SEND_MESSAGE = "/api/v2/project/chat/create"


  const val GET_MY_GROUP_CHAT_SEND_MESSAGE_WITH_IMAGE = "/api/v2/project/chat/create"

  const val CHECK_IN_CHECKOUT= "/api/v2/project/job/checkin/checkout/{id}"
  const val TIME_LOG_LIST= "/api/v2/project/job/checkin/getAll/{id}"


  const val GET_VISIT_LIST= "/api/v2/visit/list/{usertype}"
  const val GET_VISIT_SELLER_RESCHEDULE= "/api/v2/visit/seller/reschedule/{id}"
  const val GET_VISIT_BUYER_RESCHEDULE= "/api/v2/visit/buyer/reschedule/{id}"
  const val GET_VISIT_SELLER_ACCEPT= "/api/v2/visit/seller/accept/{id}"
  const val GET_VISIT_BUYER_ACCEPT= "/api/v2/visit/buyer/accept/{id}"
  const val GET_VISIT_BUYER_REJECT= "/api/v2/visit/buyer/reject/{id}"
  const val GET_VISIT_SELLER_REJECT= "/api/v2/visit/seller/reject/{id}"


  ///api/v2/project/job/checkin/getAll/:jobId

  const val ADMIN_LOGIN_API= "/api/v2/admin/login"
  const val ADMIN_DASHBOARD_API= "/api/v2/admin/dashboard"
  const val ADMIN_PROJECT_LIST = "/api/v2/project/list"
  const val ADMIN_NOTIFICATION_LIST = "/api/v2/admin/notification/list/admin/all"
  const val ADMIN_APPLICATION_LIST = "/api/v2/user/getAll/{type}"
  const val ADMIN_APPLICATION_STATUS_CHANGE = "/api/v2/admin/application-approve/{id}"
  const val ADMIN_APPLICATION_STATUS_DELETE = "/api/v2/admin/application/{id}"
  const val ADMIN_USER_DETAILS = "/api/v2/user/application/{id}"
  const val ADMIN_PROPERTY_LIST = "/api/v2/admin/properties"
  const val ADMIN_PROPERTY_STATUS_CHANGE = "/api/v2/admin/property/status/{id}"
  const val ADMIN_PROPERTY_DELETE = "/api/v2/role/delete/{id}"
  const val ADMIN_HOME_DESIGN_PACKAGE_LIST = "/api/v2/rehab/package/list"
  const val ADMIN_HOME_DESIGN_PACKAGE_DELETE = "/api/v2/rehab/package/delete/{id}"
  const val ADMIN_HOME_DESIGN_PACKAGE_ADD = "/api/v2/rehab/package/add"
  const val ADMIN_BEFORE_AFTER_GET_ALL = "/api/v2/admin/property/before/after/getAll"
  const val ADMIN_BEFORE_AFTER_DELETE = "/api/v2/admin/property/before/after/{id}"
  const val ADMIN_BEFORE_AFTER_ADD = "/api/v2/admin/property/before/after"
  const val ADMIN_SCA_LIST = "/api/v2/admin/users/sca"
  const val ADMIN_SCA_DELETE = "/api/v2/admin/user/{id}"
  const val ADMIN_SCA_ADD = "/api/v2/admin/register"
  const val ADMINS_LIST = "/api/v2/admin/users"
  const val ADMIN_ADD = "/api/v2/admin/register"
  const val ADMIN_DELETE = "/api/v2/admin/user/{id}"
  const val ADMIN_ROLES_LIST = "/api/v2/role/list"
  const val ADMIN_PERMISSION_LIST = "/api/v2/permission/list"
  const val ADMIN_ROLE_ADD = "/api/v2/role/add"
  const val ADMIN_ROLE_DELETE = "/api/v2/role/delete/{id}"
/*/
*
* approved: false
max_mortgage: "as"
message: ""
reject_reason: "as"
userId: "5fd8fa79b5f2e2e6f1ab6ada"
*
*
* approved: true
max_mortgage: ""
message: ""
reject_reason: ""
userId: "5fd8fa79b5f2e2e6f1ab6ada"
* */



 }
}
