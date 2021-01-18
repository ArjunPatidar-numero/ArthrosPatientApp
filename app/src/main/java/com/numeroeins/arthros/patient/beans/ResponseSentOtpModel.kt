package com.numeroeins.arthros.patient.beans

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseSentOtpModel {
    @SerializedName("status")
    @Expose
    var status: String? = ""
    @SerializedName("error")
    @Expose
    var error: String? = ""
 /*   @SerializedName("data")
    @Expose
    var data: ResponseLoginModel.Data? = null

    class Data {
        @SerializedName("username")
        @Expose
        var username: String? = ""

        @SerializedName("email")
        @Expose
        var email: String? = ""

        @SerializedName("phone")
        @Expose
        var phone: String? = ""

        @SerializedName("phoneVerified")
        @Expose
        var phoneVerified: Boolean? = null

        @SerializedName("token")
        @Expose
        var token: String? = ""

        @SerializedName("id")
        @Expose
        var id: String? = ""

        @SerializedName("type")
        @Expose
        var type: String? = ""

        @SerializedName("image")
        @Expose
        var image: String? = ""

    }*/
}
 /*   @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public class Data {

        @SerializedName("otp")
        @Expose
        private String otp;

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

    }*/

