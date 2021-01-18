package com.numeroeins.arthros.patient.beans

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class RegisterModel {
    @SerializedName("status")
    @Expose
    var status: String? = ""
    @SerializedName("error")
    @Expose
    var error: String? = ""


    @SerializedName("data")
    @Expose
    var data: Data? = null

    class Data {
        @SerializedName("fullName")
        @Expose
        var fullName: String? =""

        @SerializedName("email")
        @Expose
        var email: String? =""

        @SerializedName("phone")
        @Expose
        var phone: String? =""

        @SerializedName("phoneVerified")
        @Expose
        var phoneVerified: Boolean? = null

        @SerializedName("token")
        @Expose
        var token: String? =""

        @SerializedName("id")
        @Expose
        var id: String? =""

        @SerializedName("type")
        @Expose
        var type: String? =""

        @SerializedName("image")
        @Expose
        var image: String? =""
    }

}