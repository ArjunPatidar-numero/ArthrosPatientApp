package com.numeroeins.arthros.patient.beans

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class RegisterModel {
    @SerializedName("status")
    @Expose
    var status: String?  = ""

    @SerializedName("data")
    @Expose
    var data: String? = ""

}