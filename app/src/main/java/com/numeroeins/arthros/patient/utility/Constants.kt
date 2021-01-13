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
const val BUYER_SCREEN: String = "BUYER_SCREEN"
const val SELLER_SCREEN: String = "SELLER_SCREEN"

const val SELLER: String = "seller"
const val BUYER: String = "buyer"
const val USER_ADMIN: String = "admin"
const val CONTRACTOR: String = "contractor"
const val AFFILIATE: String = "affiliate"


const val CONTRACTOR_SCREEN: String = "CONTRACTOR_SCREEN"
const val AFFILIATE_SCREEN: String = "AFFILIATE_SCREEN"

const val PARAM_IS_BORROWER: String = "PARAM_IS_BORROWER"
const val PARAM_AFFILIATE_TYPE: String = "PARAM_AFFILIATE_TYPE"
const val PARAM_IS_COMING_BACK: String = "PARAM_IS_COMING_BACK"



const val  REQUEST_FILE_CALLBACK = 2073
const val  REQUEST_FILE_GALLERY_CALLBACK = 2072
const val REQUEST_CAMERA_CODE = 6600
const val FILEPATH = "FILEPATH"

const val SELECT_COUNT: String = "SELECT_COUNT"

const val HOME: String = "HOME"
const val CHAT: String = "CHAT"
const val PROPERTY : String = "PROPERTY "
const val VENUE: String = "Venue"

const val TIME_FORMAT_DD_MMM_yyyy = "dd MMM, yyyy"
const val TIME_FORMAT_SLASH_YYYY_MM_DD: String  = "yyyy/MM/dd"
const val TIME_DATE_FORMAT_dd_MM_yyyy_with_TZ: String = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'"
const val TIME_FORMAT_YYYY_MM_DD: String  = "yyyy-MM-dd"
//

const val FORGOT_PASSWORD: String = "FORGOT_PASSWORD"

const val BORROWER = 1
const val CO_BORROWER = 2

const val COMING_ADAPTER = 0
const val COMING_ADAPTER_EXTERIOR = 1
const val COMING_ADAPTER_INTERIOR = 2
const val COMING_ADAPTER_LICENSED = 3
const val COMING_ADAPTER_OTHER = 4

const val SELLER_PROPERTY_TYPE = 1
const val SELLER_PROPERTY_STATUS = 2
const val SELLER_PROPERTY_REHAB_NEEDED = 3
const val SELLER_PROPERTY_FILTER_ALL = ""
const val SELLER_PROPERTY_FILTER_APPROVED = "Approved"
const val SELLER_PROPERTY_FILTER_PENDING = "Pending"
const val SELLER_PROPERTY_FILTER_EXPIRED = "Expired"

const val ACTION_APPROVE = "approve"
const val ACTION_REJECT = "reject"
const val ACTION_REAPPROVE = "reapprove"
const val ACTION_DELETE = "delete"

const val STATUS_PENDING = "pending"
const val STATUS_REJECTED = "rejected"
const val STATUS_APPROVED = "approved"

const val SELLER_RESCHEDULE_VISIT = "SELLER_RESCHEDULE_VISIT"
const val BUYER_RESCHEDULE_VISIT = "BUYER_RESCHEDULE_VISIT"
const val BUYER_ACCEPT_VISIT = "BUYER_ACCEPT_VISIT"
const val SELLER_ACCEPT_VISIT = "SELLER_ACCEPT_VISIT"
const val SELLER_REJECT_VISIT = "SELLER_REJECT_VISIT"
const val BUYER_REJECT_VISIT = "BUYER_REJECT_VISIT"

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

/* Contractors
const val LOGIN_CHECK: Int = 1
const val INIT_SEL_CHECK: Int = 2

const val REQUEST_SEARCH_LOCATION: Int = 1001
const val REQUEST_ROLE_FRAGMENT: Int = 1002
const val ROLE_SCREEN_TYPE: Int = 3
const val SELECT_SPORT: Int = 1
const val SELECT_VENUE: Int = 2
const val SELECT_QUICK: Int = 4
const val SELECT_SETTING: Int = 5
const val SELECT_MAIN: Int = 6
const val ROLE_SCREEN: Int = 7
const val PARAM_USER_NAME: String = "PARAM_USER_NAME"

const val PARAM_ADDRESS: String = "PARAM_ADDRESS"
const val PARAM_MAP_ID: String = "PARAM_MAP_ID"

const val PARAM_CURRENT_LOCATION: String = "PARAM_CURRENT_LOCATION"

const val TYPE_CURRENT_LOCATION: String = "TYPE_CURRENT_LOCATION"
const val TYPE_HOME_LOCATION: String = "TYPE_HOME_LOCATION"

const val PARAM_LATITUDE: String = "PARAM_LATITUDE"
const val PARAM_LONGITUDE: String = "PARAM_LONGITUDE"
const val PARAM_SPORT_ID: String = "PARAM_SPORT_ID"
const val PARAM_DATE: String = "PARAM_DATE"
const val PARAM_VENUE_ID: String = "PARAM_VENUE_ID"
const val PARAM_SPORT_NAME: String = "PARAM_SPORT_NAME"
const val PARAM_CURRENCY: String = "PARAM_CURRENCY"
const val PARAM_PAYMENT_COUNT: String = "PARAM_PAYMENT_COUNT"
const val PARAM_VENUE_NAME: String = "PARAM_VENUE_NAME"
const val PARAM_ID: String = "id"
const val PARAM_TIMING: String = "PARAM_TIMING"
const val PARAM_FROM_TIME: String = "PARAM_FROM_TIME"
const val PARAM_TO_TIME: String = "PARAM_TO_TIME"
const val PARAM_FEE_PER_HOUR: String = "PARAM_FEE_PER_HOUR"
const val PARAM_MINIMUM_VALUE: String = "PARAM_MINIMUM_VALUE"
const val PARAM_FEE_MONTHLY: String = "PARAM_FEE_MONTHLY"
const val PARAM_FEE_MONTHLY_PER_HOUR: String = "PARAM_FEE_MONTHLY_PER_HOUR"

const val PARAM_PAYMENT: String = "PARAM_PAYMENT"
const val PARAM_BOOKING_ID: String = "PARAM_BOOKING_ID"
const val PARAM_DATE_TIMING: String = "PARAM_DATE_TIMING"
const val PARAM_IMAGE: String = "PARAM_IMAGE"
const val PARAM_IS_MONTHLY_HOURLY: String = "PARAM_IS_MONTHLY_HOURLY"

const val PARAM_MINIMUM_TYPE: String = "PARAM_MINIMUM_TYPE"
//Our constants
const val OPERATION_CAPTURE_PHOTO = 1
const val OPERATION_CHOOSE_PHOTO = 2

 const val REQUEST_CAMERA_CODE = 6600


const val DIRECTORY_SPORT_TIME_COMPRESS_FILE: String = "/SportTime/CompressFile"

const val  REQUEST_FILE_GALLERY_CALLBACK = 2072
const val FILEPATH = "FILEPATH"

const val SELECT_COUNT: String = "SELECT_COUNT"


const val TRANSACTION_STATUS_ID_FAILED = 2
const val TRANSACTION_STATUS_ID_SUCCESS = 3

const val PAYMENT_TYPE_ONLINE = 1
const val PAYMENT_TYPE_PAYMENT_AT_VENUE = 2
const val PAYMENT_TYPE_PARTIAL_PAYMENT = 3

const val TIME_DATE_FORMAT_dd_MM_yyyy_with_TZ: String = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'"
const val TIME_DATE_FORMAT_date: String = "dd"
const val TIME_DATE_FORMAT_DAY: String = "EEE"
const val TIME_DATE_FORMAT_MONTH: String = "MMM"
const val TIME_DATE_FORMAT_date_month: String = "dd MMM"
const val TIME_DATE_FORMAT_date_month_year: String = "dd MMM.yyyy"
const val TIME_DATE_FORMAT_year: String = "yyyy"
const val TIME_DATE_FORMAT_MONTH_yyyy: String = "MMM,yyyy"
const val TIME_FORMAT_yyyy_MM_dd: String  = "yyyy-MM-dd"
const val TIME_FORMAT_yyyy_MMMM_dd: String  = "yyyy-MMMM-dd"
const val TIME_FORMAT_MM: String  = "MM"
const val TIME_FORMAT_yyyy_MMMM = "yyyy, MMMM"
const val TIME_FORMAT_DD_MMMM_yyyy = "dd MMMM,yyyy "
const val TIME_FORMAT_YYYY_MM_DD_HH_MM_AA: String  = "yyyy-MM-dd hh:mmaa"
const val TIME_FORMAT_YYYY_MM_DD_HH_MM__AA: String  = "yyyy-MM-dd hh:mm aa"

const val TIME_FORMAT_SLASH_YYYY_MM_DD: String  = "yyyy/MM/dd"
const val TIME_FORMAT_ONLY_TIME: String  = "hh:mm aa"
const val TIME_FORMAT_ONLY_TIME_HH: String  = "hh"

const val TIME_FORMAT_ONLY_TIME_MM: String  = "mm"
const val FORGOT_PASSWORD: String = "FORGOT_PASSWORD"
//Param
const val PARAM_MOBILE_NUMBER: String = "PARAM_MOBILE_NUMBER"
const val PARAM_ISD_CODE: String = "PARAM_ISD_CODE"
//isdCode
*/
