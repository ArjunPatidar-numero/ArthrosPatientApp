package com.numeroeins.arthros.patient.beans

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class LoginModel {
    @SerializedName("data")
    @Expose
    var data: Data? = null

    @SerializedName("status")
    @Expose
    val status: String =""
    @SerializedName("error")
    @Expose
    val error: String =""



    class Data {
        @SerializedName("department_id")
        @Expose
        var departmentId: String =""

        @SerializedName("first_name")
        @Expose
        var firstName: String =""

        @SerializedName("last_name")
        @Expose
        var lastName: String =""

        @SerializedName("email")
        @Expose
        var email: String =""

        @SerializedName("phone")
        @Expose
        var phone: String =""

        @SerializedName("gender")
        @Expose
        var gender: String =""

        @SerializedName("status")
        @Expose
        var status: String =""

        @SerializedName("image_url")
        @Expose
        var imageUrl: String =""

        @SerializedName("full_name")
        @Expose
        var fullName: String =""

        @SerializedName("token")
        @Expose
        var token: String =""
    }
}