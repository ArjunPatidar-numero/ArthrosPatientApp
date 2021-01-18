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


    var deviceToken: String? = ""
    var fullName: String? = ""
    var email: String? = ""
    var accessToken: String? = ""
    var id: String? = ""
    var buyer_seller_id: String? = ""
    var firstDBUpdate: Boolean? = false
    var user_id: String? = "1"
    var image: String? = ""
    val tradesList: ArrayList<String> = ArrayList()




}
