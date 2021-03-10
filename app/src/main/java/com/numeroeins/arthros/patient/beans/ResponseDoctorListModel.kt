package com.numeroeins.arthros.patient.beans

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ResponseDoctorListModel {
    @SerializedName("status")
    @Expose
    var status: String=""

    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null

    class  Datum {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("user_id")
        @Expose
        var userId: Int? = null

        @SerializedName("doctor_department_id")
        @Expose
        var doctorDepartmentId: Int? = null

        @SerializedName("specialist")
        @Expose
        var specialist: String=""

        @SerializedName("created_at")
        @Expose
        var createdAt: String=""

        @SerializedName("updated_at")
        @Expose
        var updatedAt: String=""

        @SerializedName("user")
        @Expose
        var user: User? = null
    }

    class  User {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("department_id")
        @Expose
        var departmentId: String=""

        @SerializedName("first_name")
        @Expose
        var firstName: String=""

        @SerializedName("last_name")
        @Expose
        var lastName: String=""

        @SerializedName("email")
        @Expose
        var email: String=""

        @SerializedName("designation")
        @Expose
        var designation: String=""

        @SerializedName("phone")
        @Expose
        var phone: String=""

        @SerializedName("gender")
        @Expose
        var gender: String=""

        @SerializedName("qualification")
        @Expose
        var qualification: String=""

       @SerializedName("blood_group")
        @Expose
        var bloodGroup: String=""

        @SerializedName("dob")
        @Expose
        var dob: String=""

        @SerializedName("email_verified_at")
        @Expose
        var emailVerifiedAt: String=""

        @SerializedName("owner_id")
        @Expose
        var ownerId: String=""

        @SerializedName("owner_type")
        @Expose
        var ownerType: String=""

        @SerializedName("status")
        @Expose
        var status: String=""

        @SerializedName("language")
        @Expose
        var language: String=""

        @SerializedName("created_at")
        @Expose
        var createdAt: String=""

        @SerializedName("updated_at")
        @Expose
        var updatedAt: String=""

        @SerializedName("stripe_id")
        @Expose
        var stripeId: String=""

        @SerializedName("card_brand")
        @Expose
        var cardBrand:String=""

        @SerializedName("card_last_four")
        @Expose
        var cardLastFour: String=""

        @SerializedName("trial_ends_at")
        @Expose
        var trialEndsAt:String=""

        @SerializedName("image_url")
        @Expose
        var imageUrl: String=""

        @SerializedName("full_name")
        @Expose
        var fullName: String=""

       @SerializedName("age")
        @Expose
        var age: String=""

        @SerializedName("media")
        @Expose
        var media: List<String>? = null

    }
}