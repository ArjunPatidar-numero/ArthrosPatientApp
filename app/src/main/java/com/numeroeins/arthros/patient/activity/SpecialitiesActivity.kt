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
import com.numeroeins.arthros.patient.utility.UserPreference

class SpecialitiesActivity : BaseActivity() , View.OnClickListener, OurSpecialitiesAllAdapter.onRecyclerViewItemClickListener{

    private var userPreference: UserPreference? = null
    lateinit var activitySpecialitiesBinding: ActivitySpecialitiesBinding
    private lateinit var ourSpecialitiesAllAdapter: OurSpecialitiesAllAdapter
    private val ourSpecialitiesAllArrayList:ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySpecialitiesBinding = DataBindingUtil.setContentView(this, R.layout.activity_specialities)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        userPreference = UserPreference.getInstance(applicationContext)
        init()
    }

    private fun init() {
        activitySpecialitiesBinding.topHeader.navTitle.text = "Our Specialities"
        activitySpecialitiesBinding.topHeader.backIcon.setOnClickListener(this)
        val pullToRefresh: SwipeRefreshLayout = activitySpecialitiesBinding!!.pullToRefresh
        pullToRefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                init() // your code
                pullToRefresh.setRefreshing(false)
            }
        })


        ourSpecialitiesAllArrayList.add("")
        ourSpecialitiesAllArrayList.add("")
        ourSpecialitiesAllArrayList.add("")
        ourSpecialitiesAllArrayList.add("")
        ourSpecialitiesAllArrayList.add("")

        if(ourSpecialitiesAllArrayList.size>0)
        {
            activitySpecialitiesBinding.ourSpecialitiesRv.visibility = View.VISIBLE
            activitySpecialitiesBinding.noDataAvailableTxt.visibility = View.GONE
        }else{
            activitySpecialitiesBinding.ourSpecialitiesRv.visibility = View.GONE
            activitySpecialitiesBinding.noDataAvailableTxt.visibility = View.VISIBLE
        }

        ourSpecialitiesAllAdapter= OurSpecialitiesAllAdapter(this,ourSpecialitiesAllArrayList)
        activitySpecialitiesBinding.ourSpecialitiesRv.layoutManager =   GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        activitySpecialitiesBinding.ourSpecialitiesRv.adapter = ourSpecialitiesAllAdapter
        ourSpecialitiesAllAdapter.setOnItemClickListener(this)
        ourSpecialitiesAllAdapter.notifyDataSetChanged()



    }
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.backIcon -> {
               finish()
            }
        }
    }

    override fun onOurSpecialitiesListItemClickListener(position: Int) {
        val intent = Intent(this, SpecialityDetailsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }

}