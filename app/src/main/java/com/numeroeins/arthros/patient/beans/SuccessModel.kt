package com.numeroeins.arthros.patient.beans

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SuccessModel {
    @SerializedName("status")
    @Expose
    val status: String? = null

    @SerializedName("data")
    @Expose
    val data: String? = null

    @SerializedName("error")
    @Expose
    var error: String? = null

}