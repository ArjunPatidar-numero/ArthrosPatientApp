package com.numeroeins.arthros.patient.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.utility.UserPreference

class SplashActivity : BaseActivity() {
    private val SPLASH_TIME_OUT = 2000
    private var userPreference: UserPreference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        userPreference = UserPreference.getInstance(applicationContext)


        splashUsingHandler()
    }

    private fun splashUsingHandler() {
        Handler().postDelayed({
          /*  if (userPreference!!.LOGIN_STATUS!!) {
                if (userPreference!!.accessToken != null) {
                    if (userPreference!!.role.equals("buyer")) {
                        val intent = Intent(applicationContext, BuyerDashboardActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                        finish()
                    }else  if (userPreference!!.role.equals("seller")) {
                        val intent = Intent(applicationContext, SellerDashboardActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                        finish()
                    }else  if (userPreference!!.role.equals("contractor")) {
                        val intent = Intent(applicationContext, ContractorDashboardActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                        finish()
                    }else  if (userPreference!!.role.equals("affiliate")) {
                        val intent = Intent(applicationContext, AffiliateDashboardActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                        finish()
                    }else  if (userPreference!!.role.equals(USER_ADMIN)) {
                        val intent = Intent(applicationContext, AdminDashboardActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                        finish()
                    }

                }
            } else {
                if (userPreference!!.accessToken != null && userPreference!!.accessToken!!.isNotEmpty()) {

                    if (userPreference!!.role.equals("buyer")) {
                        if (userPreference!!.REGISTER_STATUS==DEFAULT) {
                            val intent = Intent(applicationContext, IntroductionActivity::class.java)
                            startActivity(intent)
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                            finish()
                        }else if (userPreference!!.REGISTER_STATUS==BUYER_REGISTERATION) {
                            val intent = Intent(applicationContext, BuyerForm1Activity::class.java)
                            startActivity(intent)
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                            finish()
                        }

                    }else  if (userPreference!!.role.equals("seller")) {
                        if (userPreference!!.REGISTER_STATUS==DEFAULT) {
                            val intent = Intent(applicationContext, IntroductionActivity::class.java)
                            startActivity(intent)
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                            finish()
                        }else if (userPreference!!.REGISTER_STATUS== SELLER_REGISTERATION) {
                            val intent = Intent(applicationContext, SellerForm1Activity::class.java)
                            startActivity(intent)
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                            finish()
                        }

                    }else  if (userPreference!!.role.equals("contractor")) {
                        if (userPreference!!.REGISTER_STATUS==DEFAULT) {
                            val intent = Intent(applicationContext, IntroductionActivity::class.java)
                            startActivity(intent)
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                            finish()
                        }else if (userPreference!!.REGISTER_STATUS== CONSTRACTOR_REGISTERATION) {
                            val intent = Intent(applicationContext, ContractorsForm1Activity::class.java)
                            startActivity(intent)
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                            finish()
                        }
                    }else  if (userPreference!!.role.equals("affiliate")) {
                        if (userPreference!!.REGISTER_STATUS==DEFAULT) {
                            val intent = Intent(applicationContext, IntroductionActivity::class.java)
                            startActivity(intent)
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                            finish()
                        }else if (userPreference!!.REGISTER_STATUS== AFFILIATE_REGISTERATION) {
                            val intent = Intent(applicationContext, ContractorDashboardActivity::class.java)
                            startActivity(intent)
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                            finish()
                        }
                    }else  if (userPreference!!.role.equals(USER_ADMIN)) {

                        val intent = Intent(applicationContext, AdminDashboardActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                        finish()

                    }else {
                        val intent = Intent(applicationContext, IntroductionActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                        finish()
                    }

                } else {
                    val intent = Intent(applicationContext, IntroductionActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
                    finish()
                }
            }*/

            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

}