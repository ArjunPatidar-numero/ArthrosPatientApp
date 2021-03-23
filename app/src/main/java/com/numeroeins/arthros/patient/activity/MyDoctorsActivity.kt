package com.numeroeins.arthros.patient.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.adapter.DoctorsAdapter
import com.numeroeins.arthros.patient.beans.ResponseDoctorListModel
import com.numeroeins.arthros.patient.chat.UpdateUserWorkManager
import com.numeroeins.arthros.patient.databinding.ActivityMyDoctorsBinding
import com.numeroeins.arthros.patient.servermanager.UrlManager
import com.numeroeins.arthros.patient.servermanager.request.CommonValueModel
import com.numeroeins.arthros.patient.servermanager.request.GetRequestModel
import com.numeroeins.arthros.patient.utility.CLICK_TYPE_BOOK
import com.numeroeins.arthros.patient.utility.CLICK_TYPE_CALL
import com.numeroeins.arthros.patient.utility.CLICK_TYPE_PARENT
import com.numeroeins.arthros.patient.utility.UserPreference

class MyDoctorsActivity : BaseActivity(), DoctorsAdapter.onRecyclerViewItemClickListener, View.OnClickListener {
    
    lateinit var activityMyDoctorsBinding: ActivityMyDoctorsBinding
    private var userPreference: UserPreference? = null
    private lateinit var doctorsAdapter: DoctorsAdapter
    private val doctorsArrayList:ArrayList<ResponseDoctorListModel.Datum> = ArrayList()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMyDoctorsBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_doctors)
        userPreference = UserPreference.getInstance(this)
        initView()
    }

    private fun initView() {
        activityMyDoctorsBinding.topHeader.navTitle.text = resources.getText(R.string.my_doctors)
        activityMyDoctorsBinding.topHeader.backIcon.setOnClickListener(this)
        val data = Data.Builder().build()
        val mediaUploadSingleRequest = OneTimeWorkRequest.Builder(UpdateUserWorkManager::class.java).setInputData(data).build()
        if (WorkManager.getInstance() != null) {
            WorkManager.getInstance().enqueue(mediaUploadSingleRequest)
        }

        val pullToRefresh: SwipeRefreshLayout = activityMyDoctorsBinding!!.pullToRefresh
        pullToRefresh.setOnRefreshListener {
            initView() // your code
            pullToRefresh.isRefreshing = false
        }

        addDummyEntry()
        if(doctorsArrayList.size>0)
        {
            activityMyDoctorsBinding.doctorRecyclerView.visibility = View.VISIBLE
            activityMyDoctorsBinding.noDataAvailableTxt.visibility = View.GONE
        }else{
            activityMyDoctorsBinding.doctorRecyclerView.visibility = View.GONE
            activityMyDoctorsBinding.noDataAvailableTxt.visibility = View.VISIBLE
        }


        doctorsAdapter= DoctorsAdapter(this, doctorsArrayList)
        activityMyDoctorsBinding.doctorRecyclerView.layoutManager =   LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        activityMyDoctorsBinding.doctorRecyclerView.adapter = doctorsAdapter
        doctorsAdapter.setOnItemClickListener(this)
        doctorsAdapter.notifyDataSetChanged()
        getDoctorApi()
    }

    private fun addDummyEntry() {
        var responseDoctorListModelDatum: ResponseDoctorListModel.Datum? = ResponseDoctorListModel.Datum()
        var responseDoctorListModelUser: ResponseDoctorListModel.User? = ResponseDoctorListModel.User()

        responseDoctorListModelUser!!.age = "30"
        responseDoctorListModelUser.bloodGroup = "A+"
        responseDoctorListModelUser.cardBrand = "dgfd"
        responseDoctorListModelUser.createdAt = "11"
        responseDoctorListModelUser.cardLastFour = "4566"
        responseDoctorListModelUser.departmentId = "101"
        responseDoctorListModelUser.designation = "sfdsdfsd"
        responseDoctorListModelUser.dob = "07/08/1995"
        responseDoctorListModelUser.email = "sjd@kdf.com"
        responseDoctorListModelUser.emailVerifiedAt = "sad"
        responseDoctorListModelUser.firstName = "Arjun"
        responseDoctorListModelUser.fullName = "Arjun Patidar"
        responseDoctorListModelUser.lastName = "Patidar"
        responseDoctorListModelUser.lastName = "Patidar"

        responseDoctorListModelDatum!!.id = 1
        responseDoctorListModelDatum.createdAt = "11"
        responseDoctorListModelDatum.doctorDepartmentId = 101
        responseDoctorListModelDatum.specialist = "MBBS"
        responseDoctorListModelDatum.updatedAt = "12"
        responseDoctorListModelDatum.userId = 107
        responseDoctorListModelDatum.user = responseDoctorListModelUser

        doctorsArrayList.add(responseDoctorListModelDatum)

        responseDoctorListModelDatum = ResponseDoctorListModel.Datum()
        responseDoctorListModelUser = ResponseDoctorListModel.User()
        responseDoctorListModelUser!!.age = "30"
        responseDoctorListModelUser.bloodGroup = "A+"
        responseDoctorListModelUser.cardBrand = "dgfd"
        responseDoctorListModelUser.createdAt = "11"
        responseDoctorListModelUser.cardLastFour = "4566"
        responseDoctorListModelUser.departmentId = "101"
        responseDoctorListModelUser.designation = "sfdsdfsd"
        responseDoctorListModelUser.dob = "07/08/1995"
        responseDoctorListModelUser.email = "sjd@kdf.com"
        responseDoctorListModelUser.emailVerifiedAt = "sad"
        responseDoctorListModelUser.firstName = "Arjun"
        responseDoctorListModelUser.fullName = "Arjun Patidar"
        responseDoctorListModelUser.lastName = "Patidar"
        responseDoctorListModelUser.lastName = "Patidar"

        responseDoctorListModelDatum!!.id = 1
        responseDoctorListModelDatum.createdAt = "11"
        responseDoctorListModelDatum.doctorDepartmentId = 101
        responseDoctorListModelDatum.specialist = "MBBS"
        responseDoctorListModelDatum.updatedAt = "12"
        responseDoctorListModelDatum.userId = 107
        responseDoctorListModelDatum.user = responseDoctorListModelUser

        doctorsArrayList.add(responseDoctorListModelDatum)

        responseDoctorListModelDatum = ResponseDoctorListModel.Datum()
        responseDoctorListModelUser = ResponseDoctorListModel.User()
        responseDoctorListModelUser!!.age = "30"
        responseDoctorListModelUser.bloodGroup = "A+"
        responseDoctorListModelUser.cardBrand = "dgfd"
        responseDoctorListModelUser.createdAt = "11"
        responseDoctorListModelUser.cardLastFour = "4566"
        responseDoctorListModelUser.departmentId = "101"
        responseDoctorListModelUser.designation = "sfdsdfsd"
        responseDoctorListModelUser.dob = "07/08/1995"
        responseDoctorListModelUser.email = "sjd@kdf.com"
        responseDoctorListModelUser.emailVerifiedAt = "sad"
        responseDoctorListModelUser.firstName = "Arjun"
        responseDoctorListModelUser.fullName = "Arjun Patidar"
        responseDoctorListModelUser.lastName = "Patidar"
        responseDoctorListModelUser.lastName = "Patidar"

        responseDoctorListModelDatum!!.id = 1
        responseDoctorListModelDatum.createdAt = "11"
        responseDoctorListModelDatum.doctorDepartmentId = 101
        responseDoctorListModelDatum.specialist = "MBBS"
        responseDoctorListModelDatum.updatedAt = "12"
        responseDoctorListModelDatum.userId = 107
        responseDoctorListModelDatum.user = responseDoctorListModelUser

        doctorsArrayList.add(responseDoctorListModelDatum)
    }

    private fun getDoctorApi() {
        val getRequestModel = GetRequestModel()
//        showLoader(resources.getString(R.string.please_wait))
        val commonModel = CommonValueModel()
        getApiCall(this, UrlManager.DOCTOR_LIST, getRequestModel, commonModel)
    }

    override fun onDoctorsListItemClickListener(position: Int, type: String) {
        if(type == CLICK_TYPE_PARENT)
        {
            val intent = Intent(this, DoctorDetailsActivity::class.java)
            startActivity(intent)
            this.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }else if(type == CLICK_TYPE_BOOK)
        {
            bookAction()
        }else if(type == CLICK_TYPE_CALL)
        {
            callAction()
        }

    }

    private fun bookAction() {
        val intent = Intent(this, DoctorDetailsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }

    private fun callAction() {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.backIcon -> {
                finish()
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
            }
        }
    }
}