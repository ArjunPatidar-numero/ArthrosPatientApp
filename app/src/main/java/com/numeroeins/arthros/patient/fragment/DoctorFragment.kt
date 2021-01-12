package com.numeroeins.arthros.patient.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.activity.DoctorDetailsActivity
import com.numeroeins.arthros.patient.activity.ServiceDetailsActivity
import com.numeroeins.arthros.patient.adapter.DoctorsAdapter
import com.numeroeins.arthros.patient.adapter.ImageSliderAdapter
import com.numeroeins.arthros.patient.adapter.OurSpecialitiesAdapter
import com.numeroeins.arthros.patient.databinding.FragmentDoctorBinding
import com.numeroeins.arthros.patient.databinding.FragmentHomeBinding
import com.numeroeins.arthros.patient.servermanager.request.CommonValueModel
import com.numeroeins.arthros.patient.utility.CLICK_TYPE_BOOK
import com.numeroeins.arthros.patient.utility.CLICK_TYPE_CALL
import com.numeroeins.arthros.patient.utility.CLICK_TYPE_PARENT
import com.numeroeins.arthros.patient.utility.UserPreference
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController
import com.smarteist.autoimageslider.SliderAnimations
import io.reactivex.disposables.Disposable

class DoctorFragment :BaseFragment(), FragmentBaseListener, View.OnClickListener, DoctorsAdapter.onRecyclerViewItemClickListener{
    private lateinit var fragmentDoctorBinding: FragmentDoctorBinding
    private var userPreference: UserPreference? = null
    private lateinit var doctorsAdapter: DoctorsAdapter
    private val doctorsArrayList:ArrayList<String> = ArrayList()
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



        val pullToRefresh: SwipeRefreshLayout = fragmentDoctorBinding!!.pullToRefresh
        pullToRefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                initView() // your code
                pullToRefresh.setRefreshing(false)
            }
        })


        doctorsArrayList.add("")
        doctorsArrayList.add("")
        doctorsArrayList.add("")
        doctorsArrayList.add("")
        doctorsArrayList.add("")


        if(doctorsArrayList.size>0)
        {
            fragmentDoctorBinding.doctorRecyclerView.visibility = View.VISIBLE
            fragmentDoctorBinding.noDataAvailableTxt.visibility = View.GONE
        }else{
            fragmentDoctorBinding.doctorRecyclerView.visibility = View.GONE
            fragmentDoctorBinding.noDataAvailableTxt.visibility = View.VISIBLE
        }

        doctorsAdapter= DoctorsAdapter(requireActivity(),doctorsArrayList)
        fragmentDoctorBinding.doctorRecyclerView.layoutManager =   LinearLayoutManager(activity,  LinearLayoutManager.VERTICAL, false)
        fragmentDoctorBinding.doctorRecyclerView.adapter = doctorsAdapter
        doctorsAdapter.setOnItemClickListener(this)
        doctorsAdapter.notifyDataSetChanged()




    }

    override fun onFragmentApiSuccess(result: String?, apiName: String?, disposable: Disposable?, commonModel: CommonValueModel?) {

    }

    override fun onFragmentApiFailure(message: String?, apiName: String?, disposable: Disposable?) {

    }

    override fun onReadWriteStoragePermissionAllow(medialTypes: String?) {

    }

    override fun onReadWriteStoragePermissionDeny(medialTypes: String?) {

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    override fun onDoctorsListItemClickListener(position: Int, type:String) {
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
        TODO("Not yet implemented")
    }

    private fun callAction() {
        TODO("Not yet implemented")
    }
}