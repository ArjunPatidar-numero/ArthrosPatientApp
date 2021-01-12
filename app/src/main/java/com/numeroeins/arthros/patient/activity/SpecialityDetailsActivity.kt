package com.numeroeins.arthros.patient.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.adapter.OurSpecialitiesAdapter
import com.numeroeins.arthros.patient.adapter.OurSpecialitiesAllAdapter
import com.numeroeins.arthros.patient.databinding.ActivityLoginBinding
import com.numeroeins.arthros.patient.databinding.ActivitySpecialitiesBinding
import com.numeroeins.arthros.patient.databinding.ActivitySpecialityDetailsBinding
import com.numeroeins.arthros.patient.utility.UserPreference

class SpecialityDetailsActivity : BaseActivity() , View.OnClickListener{

    private var userPreference: UserPreference? = null
    lateinit var activitySpecialityDetailsBinding: ActivitySpecialityDetailsBinding
    private lateinit var ourSpecialitiesAllAdapter: OurSpecialitiesAllAdapter
    private val ourSpecialitiesAllArrayList:ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySpecialityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_speciality_details)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        userPreference = UserPreference.getInstance(applicationContext)
        init()
    }

    private fun init() {
        activitySpecialityDetailsBinding.topHeader.navTitle.text = "Our Specialities"
        activitySpecialityDetailsBinding.topHeader.backIcon.setOnClickListener(this)
        val pullToRefresh: SwipeRefreshLayout = activitySpecialityDetailsBinding!!.pullToRefresh
        pullToRefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                init() // your code
                pullToRefresh.setRefreshing(false)
            }
        })
    }
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.backIcon -> {
                finish()
            }
        }
    }
}