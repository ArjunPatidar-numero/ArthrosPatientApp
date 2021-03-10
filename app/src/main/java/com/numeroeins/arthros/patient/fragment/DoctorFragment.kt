package com.numeroeins.arthros.patient.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.activity.DoctorDetailsActivity
import com.numeroeins.arthros.patient.adapter.DoctorsAdapter
import com.numeroeins.arthros.patient.beans.RegisterModel
import com.numeroeins.arthros.patient.beans.ResponseDoctorListModel
import com.numeroeins.arthros.patient.chat.UpdateUserWorkManager
import com.numeroeins.arthros.patient.databinding.FragmentDoctorBinding
import com.numeroeins.arthros.patient.servermanager.APIClient
import com.numeroeins.arthros.patient.servermanager.UrlManager
import com.numeroeins.arthros.patient.servermanager.request.CommonValueModel
import com.numeroeins.arthros.patient.servermanager.request.GetRequestModel
import com.numeroeins.arthros.patient.servermanager.request.PostRequestModel
import com.numeroeins.arthros.patient.utility.*
import io.reactivex.disposables.Disposable

class DoctorFragment :BaseFragment(), FragmentBaseListener, View.OnClickListener, DoctorsAdapter.onRecyclerViewItemClickListener{
    private lateinit var fragmentDoctorBinding: FragmentDoctorBinding
    private var userPreference: UserPreference? = null
    private lateinit var doctorsAdapter: DoctorsAdapter
    private val doctorsArrayList:ArrayList<ResponseDoctorListModel.Datum> = ArrayList()
    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentDoctorBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_doctor, container, false)
        val view: View = fragmentDoctorBinding.root
        setOnFragmentListener(this)
        userPreference = UserPreference.getInstance(requireActivity())
        initView()
        return view
    }

    private fun initView() {


        val data = Data.Builder().build()
        val mediaUploadSingleRequest = OneTimeWorkRequest.Builder(UpdateUserWorkManager::class.java).setInputData(data).build()
        if (WorkManager.getInstance() != null) {
            WorkManager.getInstance().enqueue(mediaUploadSingleRequest)
        }

        val pullToRefresh: SwipeRefreshLayout = fragmentDoctorBinding!!.pullToRefresh
        pullToRefresh.setOnRefreshListener {
            initView() // your code
            pullToRefresh.isRefreshing = false
        }

        addDummyEntry()
        if(doctorsArrayList.size>0)
        {
            fragmentDoctorBinding.doctorRecyclerView.visibility = View.VISIBLE
            fragmentDoctorBinding.noDataAvailableTxt.visibility = View.GONE
        }else{
            fragmentDoctorBinding.doctorRecyclerView.visibility = View.GONE
            fragmentDoctorBinding.noDataAvailableTxt.visibility = View.VISIBLE
        }


        doctorsAdapter= DoctorsAdapter(requireActivity(), doctorsArrayList)
        fragmentDoctorBinding.doctorRecyclerView.layoutManager =   LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        fragmentDoctorBinding.doctorRecyclerView.adapter = doctorsAdapter
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
        showLoader(resources.getString(R.string.please_wait))
        val commonModel = CommonValueModel()
        getApiCall(requireActivity(), UrlManager.DOCTOR_LIST, getRequestModel, commonModel)
    }


    override fun onFragmentApiSuccess(result: String?, apiName: String?, disposable: Disposable?, commonModel: CommonValueModel?) {
        closeLoader()
        when (apiName) {
            UrlManager.DOCTOR_LIST-> {
                val responseLoginModel: ResponseDoctorListModel? = APIClient.gsonAsConvert.fromJson<ResponseDoctorListModel>(result, ResponseDoctorListModel::class.java)
                if (responseLoginModel != null) {
                    if (responseLoginModel.status == STATUS_SUCCESS) {
                        doctorsArrayList.addAll(responseLoginModel.data!!)
                        doctorsAdapter.notifyDataSetChanged()
                    }else{

                    }
                }
            }
        }
    }

    override fun onFragmentApiFailure(message: String?, apiName: String?, disposable: Disposable?) {
        closeLoader()
    }

    override fun onReadWriteStoragePermissionAllow(medialTypes: String?) {

    }

    override fun onReadWriteStoragePermissionDeny(medialTypes: String?) {

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    override fun onDoctorsListItemClickListener(position: Int, type: String) {
        if(type == CLICK_TYPE_PARENT)
        {
            val intent = Intent(requireActivity(), DoctorDetailsActivity::class.java)
            startActivity(intent)
            requireActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }else if(type == CLICK_TYPE_BOOK)
        {
            bookAction()
        }else if(type == CLICK_TYPE_CALL)
        {
            callAction()
        }

    }

    private fun bookAction() {
        val intent = Intent(requireActivity(), DoctorDetailsActivity::class.java)
        startActivity(intent)
        requireActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }

    private fun callAction() {
        TODO("Not yet implemented")
    }
}