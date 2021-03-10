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





    var departmentId: String? = ""
    var deviceToken: String? = ""
    var firsName: String? = ""
    var lastName: String? = ""
    var email: String? = ""
    var phone: String? = ""
    var gender: String? = ""
    var status: String? = ""
    var imageUrl: String? = ""
    var fullName: String? = ""
    var loginStatus: Int? = null
    var token: String? = ""



}
