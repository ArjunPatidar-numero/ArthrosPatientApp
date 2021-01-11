package  com.numeroeins.arthros.patient.servermanager.request

class PostRequestModel {
  /*  "email": "buyerone@numeroeins.com",
    "fullName":"Buyer One",
    "referredBy":"",
    "password": "Halo2020!",
    "consent": "I will abide by the terms and conditions",
    "gender": "male",
    "device_token": "asfkjhasdfkiw98723589fn57489qr345nki34mnmdf3h5nm3451h34534k524g53"*/



    var projectId: String? = null
    var visitId: String? = null
    var text: String? = null
    var reject_reason: String? = null
    var message: String? = null
    var max_mortgage: Int? = null
    var id: String? = null
    var approved: Boolean? = null
    var userId: String? = null
    var page: Int? = null
    var after_rehab_value: Int? = null
    var email: String? = null
    var fullName: String? = null
    var referredBy: String? = null
    var oldPwd: String? = null
    var password: String? = null
    var cnfPwd: String? = null
    var gender: String? = null
    var affiliate_type: String? = null
    var device_token: String? = null
    var sales_price: String? = null
    var rehab_price_est: String? = null
    var rehab_work_details: String? = null

    var other_contact_name: String? = null
    var other_contact_email: String? = null
    var other_contact_phone: String? = null
    var other_contact_info: String? = null

    var leftAtStep: String? = null
    var allStepsCompleted: Boolean? = null

    var otp: String? = null
    var property_type: String? = null
    var property_status: String? = null
    var rehab_needed: String? = null
    var propertyTitle: String? = null
    var propertyDescription: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var isCoBorrower: Boolean? = null

    var  co_firstName:  String? = null
    var  co_lastName:  String? = null
    //Step two
    var birthdate: String? = null
    var street_address: String? = null
    var city: String? = null
    var state: String? = null
    var zip: String? = null
    var phone: String? = null
    var  isdCode: String? = null
    var  role: String? = null
    var  type: String? = null

    var  trades: String? = null

    var dob: String? = null
   // var title: String? = null


    var areaCode: String? = null
    var homePhone: String? = null

    var co_email: String? = null
    var co_birthdate: String? = null
    var co_street_address: String? = null
    var co_city: String? = null
    var co_state: String? = null
    var co_zip: String? = null
    var co_phone: String? = null
    var co_areaCode: String? = null
    var co_homePhone: String? = null

    //Step Three


    var currently_living: String? = null
    var monthlyfee: String? = null
    var leaseEndDate: String? = null
    var available_savings: String? = null
    var marital_status: String? = null

    var co_currently_living: String? = null
    var co_monthlyfee: String? = null
    var co_leaseEndDate: String? = null
    var co_available_savings: String? = null
    var co_marital_status: String? = null


    //Step four

    var employment_status: String? = null
    var employer: String? = null
    var No_of_years_Employed: String? = null
    var net_income: String? = null
    var FICOScore: String? = null
    var former_employer: String? = null

    var incomeFreq: String? = null


    var co_employment_status: String? = null
    var co_former_employer: String? = null
    var co_employer: String? = null
    var co_No_of_years_Employed: String? = null
    var co_net_income: String? = null
    var co_incomeFreq: String? = null
    var co_FICOScore: String? = null

    //Step five
    var bills_current: Boolean? = null
    var financial_status: String? = null
    var bankruptcy: String? = null
    var dichargeDate: String? = null
    var judgementSettled: Boolean? = null
    var foreClosureDate: String? = null
    var studentLoanDate: String? = null
    var studentLoanAmount: String? = null
    var rePosDate: String? = null



 /*   "bills_current": true,
    "financial_status": "Repossession",
    "rePosDate": "2020/9/9",
    "bankruptcy": "Active",
    "judgementSettled": true,
    "dichargeDate": "2020/9/9",
    "foreClosureDate": "2020/9/9",
    "studentLoanDate": "2020/9/8",
    "studentLoanAmount": "66888888"*/

    var co_bills_current: Boolean? = null
    var co_financial_status: String? = null
    var co_bankruptcy: String? = null
    var co_dichargeDate: String? = null
    var co_judgementSettled: Boolean? = null
    var co_foreClosureDate: String? = null
    var co_studentLoanDate: String? = null
    var co_studentLoanAmount: String? = null
    var co_rePosDate: String? = null


    //Step six
    var co_federal_department: String? = null
    var co_other_department: String? = null
    var co_federal_employee: Boolean? = null
    var co_veteran: Boolean? = null
    var co_honorably_discharged: Boolean? = null

    var federal_employee: Boolean? =null
    var federal_department: String? = null
    var other_department: String? = null
    var veteran: Boolean? = null
    var honorably_discharged: Boolean? = null




    //Step seven
    var listened_about_us: String? = null

    //Step Eight
    var maxMortgagePayment: String? = null
    var zipCodes: String? = null
    var bedrooms: String? = null
    var bathrooms: String? = null
    var property_size: String? = null
    var features: String? = null
    var land_area: String? = null
    var rooms: String? = null
    var garage: String? = null
    var garage_size: String? = null
    var year_built: String? = null
    var property_id: String? = null
    var job_status: Boolean? = null
    var status: String? = null
    var title: String? = null
    var referralCode: String? = null
    var description: String? = null
    var additional_title: String? = null
    var additional_value: String? = null
    var private_note: String? = null

    var address: String? = null
    var country: String? = null
    var neighbourhood: String? = null
    var latitude: String? = null
    var longitude: String? = null



  /*  "maxMortgagePayment": "100000",
    "zipCodes": "42001,42006,42007",
    "bedrooms": "5",
    "bathrooms": "5"*/

/*    {
        "bills_current": true,
        "financial_status": "Garib",
        "bankruptcy": "active",
        "dichargeDate": "standard date format",

        "judgementSettled":  true,
        "foreClosureDate": "date",
        "studentLoanDate": "date",
        "studentLoanAmount": "100000",

        "isCoBorrower": true,
        "co_bills_current": true,

        "co_financial_status": "too garib",
        "co_bankruptcy": "active",
        "co_dichargeDate": "standard date format",

        "co_judgementSettled": true,
        "co_foreClosureDate": "date",
        "co_studentLoanDate": "date",
        "co_studentLoanAmount": "150000"
    }*/


 /*   "co_employment_status": "employed",
    "co_employer": "Numeroeins",
    "co_No_of_years_Employed": "10",
    "co_net_income": "150000",
    "co_incomeFreq": "hour",
    "co_FICOScore": "619"*/








    var isForeclose: Boolean? =null
    var mortgagePayments: String? = null
    var mortgageProperty: String? = null

    // step4
    //

    var shortTrmRntl: Boolean? = null
    var judgements: Boolean? = null
    var comments: String? = null

    var isCheckedIn: Boolean? = null

    var date: String? = null
    var time: String? = null
    var property: String? = null
    var notAvailable: Boolean? = false



  /*  "": "Employed",
    "": "Space X Inc.",
    "": "15",
    "": "6000",
    "": "609"*/

   /*
    "currently_living": "Rent",
    "monthlyfee": "600",
    "leaseEndDate": "2021/02/09",
    "available_savings": "65000",
    "marital_status": "Married"
    */



   /* "email": "buyerone@numeroeins.com",
    "birthdate": "1998/05/16",
    "street_address": "Roop Mahal, Prem Gali",
    "city": "Minassa",
    "state": "LA",
    "zip": "14587",
    "phone": "+186059632145"*/

   /* var keyword: String? = null
    var location: String? = null
    var budget: String? = null
    var jobtype: String? = null

    // Login/Register param
    var firstName: String? = null
    var lastName: String? = null
    var username: String? = null
    var password: String? = null
    var locationLable: String? = null
    var title: String? = null
    var role: String? = null
    var dob: String? = null
    var gender: String? = null
    var phone: String? = null
    var device_token: String? = null
    var email: String? = null
    var type: String? = null
    var countryCode: String? = null
    var otp: String? = null

    var name: String? = null
    var latitude: String? = null
    var longitude: String? = null
    var distance: String? = null
    var price: String? = null
    var sport: String? = null
    var sortBy: String? = null
    var comment: String? = null
    var rating: Float? = null
    var venue: String? = null
    var date: String? = null


    var timing: String? = null
    var user: String? = null
    var currency: String? = null
    var totalAmount: String? = null
    var paidAmount: String? = null
    var pendingAmount: String? = null

    var referralCode: String? = null

    var transactionId: String? = null
    var transactionStatus: Int? = null
    var transactionAmount: Double? = null
    var responseObject: String? = null
    var bookingId: String? = null


    var isdCode: String? = null
    var category: String? = null

    var venueId: String? = null*/
}
