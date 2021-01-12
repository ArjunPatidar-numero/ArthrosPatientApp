package com.numeroeins.arthros.patient.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.adapter.ServicesAllAdapter
import com.numeroeins.arthros.patient.databinding.ActivityLoginBinding
import com.numeroeins.arthros.patient.databinding.ActivityServicesBinding
import com.numeroeins.arthros.patient.utility.UserPreference

class ServicesActivity : BaseActivity() , View.OnClickListener, ServicesAllAdapter.onRecyclerViewItemClickListener{

    private var userPreference: UserPreference? = null
    lateinit var activityServicesBinding: ActivityServicesBinding
    private lateinit var servicesAllAdapter: ServicesAllAdapter
    private val servicesAllArrayList:ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityServicesBinding = DataBindingUtil.setContentView(this, R.layout.activity_services)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        userPreference = UserPreference.getInstance(applicationContext)
        init()
    }

    private fun init() {
        activityServicesBinding.topHeader.navTitle.text = "Our Services"
        activityServicesBinding.topHeader.backIcon.setOnClickListener(this)
        val pullToRefresh: SwipeRefreshLayout = activityServicesBinding!!.pullToRefresh
        pullToRefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                init() // your code
                pullToRefresh.setRefreshing(false)
            }
        })


        servicesAllArrayList.add("")
        servicesAllArrayList.add("")
        servicesAllArrayList.add("")
        servicesAllArrayList.add("")
        servicesAllArrayList.add("")

        if(servicesAllArrayList.size>0)
        {
            activityServicesBinding.servicesRv.visibility = View.VISIBLE
            activityServicesBinding.noDataAvailableTxt.visibility = View.GONE
        }else{
            activityServicesBinding.servicesRv.visibility = View.GONE
            activityServicesBinding.noDataAvailableTxt.visibility = View.VISIBLE
        }

        servicesAllAdapter= ServicesAllAdapter(this,servicesAllArrayList)
        activityServicesBinding.servicesRv.layoutManager =   GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        activityServicesBinding.servicesRv.adapter = servicesAllAdapter
        servicesAllAdapter.setOnItemClickListener(this)
        servicesAllAdapter.notifyDataSetChanged()



    }
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.backIcon -> {
                finish()
            }
        }
    }

    override fun onOurServicesListItemClickListener(position: Int) {
        val intent = Intent(this, ServiceDetailsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }


}