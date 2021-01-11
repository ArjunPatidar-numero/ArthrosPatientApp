package com.numeroeins.arthros.patient.utility

import android.telephony.PhoneNumberUtils
import java.util.regex.Matcher
import java.util.regex.Pattern


class Validate {
    /**
     * Utility function for validate the email address.
     *
     * @param email email address
     * @return True if given email is valid.
     * @link https://howtodoinjava.com/regex/java-regex-validate-email-address/
     */
    fun isEmailValid(email: String?): Boolean {
        var isValidEmail = false
        if (email == null) return isValidEmail
        val expression =
            "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"
        val inputStr: CharSequence = email
        val patternSimplestRegex: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcherSimplestRegex: Matcher = patternSimplestRegex.matcher(inputStr)
        if (matcherSimplestRegex.matches()) {
            isValidEmail = true
        }
        return isValidEmail
    }

    fun isChatValid(chat: String?): Boolean {
        if (chat == null || chat == "") return false
        return true
    }

    //
    fun isValidPhone(target: String): Boolean {
        return if (target == "") {
            false
        } else {
            if (PhoneNumberUtils.isGlobalPhoneNumber(target)) {
                if (target.length >= 10 && target.length <= 14) {
                    true
                } else {
                    false
                }
            } else {
                false
            }
        }
    }

    //
    fun isValidPhoneNumber(target: String?): Boolean {
        val sPhoneNumber = "605-888-9999"
        //String sPhoneNumber = "605-88899991";
        //String sPhoneNumber = "605-888999A";
        val pattern: Pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}")
        val matcher: Matcher = pattern.matcher(sPhoneNumber)
        if (matcher.matches()) {
            println("Phone Number Valid")
        } else {
            println("Phone Number must be in the form XXX-XXX-XXXX")
        }
        return false
    }
}