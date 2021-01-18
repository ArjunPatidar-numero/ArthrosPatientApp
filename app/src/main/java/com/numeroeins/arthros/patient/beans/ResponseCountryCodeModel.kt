package com.numeroeins.arthros.patient.beans

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseCountryCodeModel {
    @SerializedName("status")
    @Expose
    val status: String? =""
    @SerializedName("error")
    @Expose
    var error: String? = ""

    @SerializedName("data")
    @Expose
    val data: List<Data>? = null

    class Data {
        @SerializedName("name")
        @Expose
        val name: String? =""

        @SerializedName("dial_code")
        @Expose
        val dialCode: String? =""

        @SerializedName("code")
        @Expose
        val code: String? =""
    }
}
