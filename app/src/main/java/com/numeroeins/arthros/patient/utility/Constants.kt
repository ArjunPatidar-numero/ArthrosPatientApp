package com.numeroeins.arthros.patient.utility

interface Constants




/*driver_license
recent_paycheck_stub
_2_years_w2
_2_years_tax_return
bank_statement

experian_attach_file_here
transunion_attach_file_here
equifax_attach_file_here
*/


const val TYPE_ONGOING: Int = 1
const val TYPE_PAST: Int = 2
const val TYPE_UPCOMING: Int = 3

const val ON_GOING_ORDER: String = "ongoing"
const val UPCOMING_ORDER: String = "upcoming"
const val PAST_ORDER: String = "past"

const val CAPTURE_AFTER_IMAGE: Int = 1
const val CAPTURE_DURING_IMAGE: Int = 2
const val CAPTURE_BEFORE_IMAGE: Int = 3


const val ATTACHMENT_BEFORE_IMAGE: Int = 1
const val ATTACHMENT_AFTER_IMAGE: Int = 2

const val ATTACHMENT_DRIVER_LICENCE: Int = 1
const val ATTACHMENT_RECENT_PAYCHECK_STUB: Int = 2
const val ATTACHMENT_2_YEARS_W2: Int = 3
const val ATTACHMENT_2_YEARS_TAX_RETURN: Int = 4
const val ATTACHMENT_BANK_STATEMENT: Int = 5
const val ATTACHMENT_EXPERIAN: Int = 6
const val ATTACHMENT_TRANSUNION: Int = 7
const val ATTACHMENT_EQUIFAX: Int = 8

const val TAB_HOME: Int = 1
const val TAB_DOCTOR: Int = 2
const val TAB_APPOINTMENT: Int = 3
const val TAB_PROFILE: Int = 4




const val TAB_PROPERTY: Int = 5
const val TAB_VISIT: Int = 6
const val TAB_DASH: Int = 7
const val TAB_APPLICANTS: Int = 8
const val PARAM_REHAB_NEEDED = "PARAM_REHAB_NEEDED"
const val PARAM_ID = "id"
const val PARAM_BEFORE_IMAGE = "PARAM_BEFORE_IMAGE"
const val PARAM_DURING_IMAGE = "PARAM_DURING_IMAGE"
const val PARAM_AFTER_IMAGE = "PARAM_AFTER_IMAGE"
const val PARAM_IS_OVER_3_DAYS = "PARAM_IS_OVER_3_DAYS"
const val PARAM_NAME = "name"
const val PARAM_EMAIL = "email"
const val PARAM_OTP = "otp"
const val PARAM_DESC = "description"
const val PARAM_COMPLETE_DATA = "complete_data"
const val PARAM_PROPERTY_ID = "PARAM_PROPERTY_ID"
const val CHAT_TYPE_IMAGE = 1
const val CHAT_TYPE_TEXT = 0

const val DEFAULT: Int = 0
const val BUYER_REGISTERATION: Int = 1
const val SELLER_REGISTERATION: Int = 2
const val CONSTRACTOR_REGISTERATION: Int = 3
const val AFFILIATE_REGISTERATION: Int = 4



const val CONTRACTOR_JOB_STATUS_NEW: String = "new"
const val CONTRACTOR_JOB_STATUS_ACCEPTED: String = "accepted"
const val CONTRACTOR_JOB_STATUS_COMPLETED: String = "completed"
const val CONTRACTOR_JOB_STATUS_DECLINED: String = "declined"

const val COMING_FROM: String = "coming_from"
const val OTHER_TAG: String = "Other"

const val PERMISSION_REQUEST_LOCATION: Int = 123
 const val PERMISSION_REQUEST_READ_WRITE = 125
const val STATUS_SUCCESS: String = "success"

const val TIME_FORMAT_SPLASH_YYYY_MM_DD_HH_MM_AA: String  = "yyyy/MM/dd hh:mmaa"

const val PARAM_COMING_FROM: String = "coming_from"
const val PARAM_USER_NAME: String = "PARAM_USER_NAME"
const val PARAM_MOBILE_NUMBER: String = "PARAM_MOBILE_NUMBER"
const val PARAM_ISD_CODE: String = "PARAM_ISD_CODE"






const val PARAM_IS_COMING_BACK: String = "PARAM_IS_COMING_BACK"



const val  REQUEST_FILE_CALLBACK = 2073
const val  REQUEST_FILE_GALLERY_CALLBACK = 2072
const val REQUEST_CAMERA_CODE = 6600
const val FILEPATH = "FILEPATH"

const val SELECT_COUNT: String = "SELECT_COUNT"



const val TIME_FORMAT_DD_MMM_yyyy = "dd MMM, yyyy"
const val TIME_FORMAT_SLASH_YYYY_MM_DD: String  = "yyyy/MM/dd"
const val TIME_DATE_FORMAT_dd_MM_yyyy_with_TZ: String = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'"
const val TIME_FORMAT_YYYY_MM_DD: String  = "yyyy-MM-dd"
//

const val FORGOT_PASSWORD: String = "FORGOT_PASSWORD"





const val THIS_CHAT_STATUS_ACTIVE = "active"
const val THIS_CHAT_STATUS_OFFLINE = "Offline"

const val CLICK_TYPE_PARENT = "parent"
const val CLICK_TYPE_CALL = "call"
const val CLICK_TYPE_BOOK = "book"

const val TIME_FORMAT_yyyy_MM_dd: String  = "yyyy-MM-dd"
const val TIME_DATE_FORMAT_date: String = "dd"
const val TIME_DATE_FORMAT_DAY: String = "EEE"
const val TIME_DATE_FORMAT_MONTH: String = "MMM"
const val TIME_DATE_FORMAT_date_month: String = "dd MMM"


// chat params
const val USER_ID = "id"
const val USER_NAME = "name"
const val USER_PHOTO = "photo"
const val USER_EMAIL = "email"
const val USER_LAST_SEEN = "userLastSeen"
const val USER_CHAT_STATUS = "userChatStatus"

const val USERS_CHILD = "users"
const val JOB_LIST = "jobs"

const val NODE_JOB_ID = "id"
const val NODE_JOB_NAME = "name"
const val NODE_JOB_ADDRESS = "address"
const val NODE_JOB_IMAGE = "image"
const val NODE_JOB_OWNER = "owner"
const val NODE_JOB_OWNER_N = "owner"

const val SENDER_ID = "SENDER_ID"
const val SENDER_NAME = "SENDER_NAME"
const val SENDER_IMAGE = "SENDER_IMAGE"
const val PARAM_RECEIVER_ID = "PARAM_RECEIVER_ID"
const val PARAM_RECEIVER_NAME = "PARAM_RECEIVER_NAME"
const val PARAM_RECEIVER_IMAGE = "PARAM_RECEIVER_IMAGE"
const val PARAM_JOB_ID: String = "PARAM_JOB_ID"
const val PARAM_OWNER_ID: String = "PARAM_OWNER_ID"
const val PARAM_OWNER_NAME: String = "PARAM_OWNER_NAME"
const val PREF_MY_ID = "myid"
const val LOG_TAG = "fchat"
const val MESSAGE_CHILD = "messages"
// //// send mess
const val NODE_IMAGE = "uploadImage"
const val NODE_TEXT = "text"
const val NODE_TIMESTAMP = "timestamp"
const val NODE_RECEIVER_ID = "receiverId"
const val NODE_RECEIVER_NAME = "receiverName"
const val NODE_RECEIVER_PHOTO = "receiverPhoto"
const val NODE_SENDER_ID = "senderId"
const val NODE_SENDER_NAME = "senderName"
const val NODE_SENDER_PHOTO = "senderPhoto"
const val NODE_IS_READ = "isread"
const val NODE_PRICE = "price"
const val NODE_DESCRIPTION = "description"
const val NODE_OFFER_STATUS = "offerStatus"
const val NODE_OFFER_ID = "offerId"
const val NODE_OWNER_ID = "ownerId"
const val NODE_JOB_IDS = "jobId"
const val NODE_OWNER_NAME = "ownerName"


const val ACCEPT_STATUS: String = "1"
const val REJECT_STATUS: String = "0"
