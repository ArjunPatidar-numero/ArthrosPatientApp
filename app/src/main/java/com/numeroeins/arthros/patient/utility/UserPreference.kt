package com.numeroeins.arthros.patient.utility

import android.content.Context
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList


class UserPreference private constructor() {



    fun save(context: Context) {
        context.getSharedPreferences(UserPreference::class.java.name, Context.MODE_PRIVATE).edit().putString("user", Gson().toJson(jUser)).apply()
    }

    fun clear(context: Context) {
        context.getSharedPreferences(UserPreference::class.java.name, Context.MODE_PRIVATE).edit().clear().apply()
        jUser = null
    }



    companion object {
        private var jUser: UserPreference? = null
        fun getInstance(context: Context): UserPreference? {
            if (jUser == null) {
                jUser = Gson().fromJson(context.getSharedPreferences(UserPreference::class.java.name, Context.MODE_PRIVATE).getString("user", null), UserPreference::class.java)
                if (jUser == null) jUser = UserPreference()
            }
            return jUser
        }

        fun getjUser(): UserPreference? {
            return jUser
        }

        fun setjUser(jUser: UserPreference?) {
            Companion.jUser = jUser
        }
    }

    var imageProfile: String? = ""
    var mobile: String? = ""
    var fullName: String? = ""
    var firstName: String? = ""
    var lastName: String? = ""
    var email: String? = ""
    var emailVerified: Boolean? = false
    var accessToken: String? = ""
    var referralCode: String? = ""
    var id: String? = ""
    var role: String? = ""
    var affiliateRole: String? = ""
    var REGISTER_STATUS: Int? = 0
    var LOGIN_STATUS: Boolean? = false
    var buyer_seller_id: String? = ""
    var isPrequalifyDone: Boolean? = false
    var applicationStatus: String? =""





    // referal person
    var ref_image: String? = ""
    var ref_mobile: String? = ""
    var ref_fullName: String? = ""
    var ref_email: String? = ""


    // buyer and affliatee data



    //------------ step Two-------------
    var buyer_email: String? = ""
    var buyer_birthdate: String? = ""
    var buyer_street_address: String? = ""
    var buyer_city: String? = ""
    var buyer_state: String? = ""
    var buyer_zip: String? = ""
    var buyer_phone: String? = ""
    var buyer_areaCode: String? = ""
    var buyer_homePhone: String? = ""



    var co_buyer_email: String? = ""
    var co_buyer_birthdate: String? = ""
    var co_buyer_street_address: String? = ""
    var co_buyer_city: String? = ""
    var co_buyer_state: String? = ""
    var co_buyer_zip: String? = ""
    var co_buyer_phone: String? = ""
    var co_buyer_areaCode: String? = ""
    var co_buyer_homePhone: String? = ""

    //--------- step Three--------------
    var buyer_currently_living: String? = ""
    var buyer_monthlyfee: String? = ""
    var buyer_leaseEndDate: String? = ""
    var buyer_marital_status: String? = ""
    var buyer_available_savings: String? = ""

    var co_buyer_currently_living: String? = ""
    var co_buyer_monthlyfee: String? = ""
    var co_buyer_leaseEndDate: String? = ""
    var co_buyer_marital_status: String? = ""
    var co_buyer_available_savings: String? = ""

    //--------- step fourth--------------
    var buyer_employment_status: String? = ""
    var buyer_employer: String? = ""
    var buyer_No_of_years_Employed: String? = ""
    var buyer_net_income: String? = ""
    var buyer_former_employer: String? = ""
    var buyer_FICOScore: String? = ""
    var buyer_incomeFreq: String? = ""

    var co_buyer_employment_status: String? = ""
    var co_buyer_employer: String? = ""
    var co_buyer_No_of_years_Employed: String? = ""
    var co_buyer_net_income: String? = ""
    var co_buyer_former_employer: String? = ""
    var co_buyer_FICOScore: String? = ""
    var co_buyer_incomeFreq: String? = ""


    //--------- step five--------------


    var buyer_bills_current: Boolean?  = false
    var buyer_financial_status: String? = ""
    var buyer_bankruptcy: String? = ""
    var buyer_dichargeDate: String? = ""

    var buyer_judgementSettled: Boolean? = false
    var buyer_foreClosureDate: String? = ""
    var buyer_studentLoanDate: String? = ""
    var buyer_studentLoanAmount: String? = ""
    var buyer_rePosDate: String? = ""

    var co_buyer_bills_current: Boolean?  = false
    var co_buyer_financial_status: String? = ""
    var co_buyer_bankruptcy: String? = ""
    var co_buyer_dichargeDate: String? = ""
    var co_buyer_judgementSettled: Boolean? = false
    var co_buyer_foreClosureDate: String? = ""
    var co_buyer_studentLoanDate: String? = ""
    var co_buyer_studentLoanAmount: String? = ""
    var co_buyer_rePosDate: String? = ""

    //--------- step six--------------
    var buyer_federal_employee: Boolean? =false
    var buyer_federal_department: String? = ""
    var buyer_other_department: String? = ""
    var veteran: Boolean? =false
    var honorably_discharged: Boolean? =false


    var co_buyer_federal_department: String? = ""
    var co_buyer_federal_employee: Boolean? = false
    var co_buyer_other_department: String? = ""
    var co_veteran: Boolean? =false
    var co_honorably_discharged: Boolean? =false


    //Step seven
    var listened_about_us: String? = ""


    //Step seven





    val tradesList: ArrayList<String> = ArrayList()



    //seller step one

    var seller_firstName: String? = ""
    var seller_lastName: String? = ""
    var seller_email: String? = ""
    var seller_areaCode: String? = ""
    var seller_phone: String? = ""
    var seller_streetAddress: String? = ""
    var seller_city: String? = ""
    var seller_state: String? = ""
    var seller_zip: String? = ""

    //seller step two
    var seller_isForeclose: Boolean? = false
    var seller_mortgagePayments: String? = ""
    var seller_mortgageProperty: String? = ""

    //seller step three

    var seller_askingPrice: String? = ""
    var seller_knowPropertyVal: Boolean? = false
    var seller_propertyValue: String? = ""
    var seller_propertyAppraisal: Boolean? = false
    var seller_propertyDetails: String? = ""
    var appraisalFile: String? = ""




    var seller_shortTrmRntl: Boolean? = false
    var seller_judgements: Boolean? = false
    var seller_comments: String? = ""
    var seller_allStepsCompleted: String? = ""
    var seller_leftAtStep: String? = ""


    var governmentFileId: String? = ""
}
