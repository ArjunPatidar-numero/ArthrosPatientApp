package com.numeroeins.arthros.patient.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.adapter.OrderListAdapter
import com.numeroeins.arthros.patient.databinding.FragmentAppointmentBinding
import com.numeroeins.arthros.patient.databinding.FragmentHomeBinding
import com.numeroeins.arthros.patient.servermanager.request.CommonValueModel
import com.numeroeins.arthros.patient.utility.TYPE_ONGOING
import com.numeroeins.arthros.patient.utility.TYPE_PREVIOUS
import com.numeroeins.arthros.patient.utility.TYPE_UPCOMING
import com.numeroeins.arthros.patient.utility.UserPreference
import io.reactivex.disposables.Disposable

class AppointmentFragment  :BaseFragment(), FragmentBaseListener, View.OnClickListener{
    private lateinit var fragmentAppointmentBinding: FragmentAppointmentBinding
    private var userPreference: UserPreference? = null
    private var defaultType: Int = TYPE_ONGOING
    private var linearLayoutManager: LinearLayoutManager? = null
    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentAppointmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_appointment, container, false)
        val view: View = fragmentAppointmentBinding.root
        setOnFragmentListener(this)
        userPreference = UserPreference.getInstance(requireActivity())
        defaultType=TYPE_ONGOING
        callGetPropertyApi()
        return view
    }


    override fun onFragmentApiSuccess(result: String?, apiName: String?, disposable: Disposable?, commonModel: CommonValueModel?) {

    }

    override fun onFragmentApiFailure(message: String?, apiName: String?, disposable: Disposable?) {

    }

    override fun onReadWriteStoragePermissionAllow(medialTypes: String?) {

    }

    override fun onReadWriteStoragePermissionDeny(medialTypes: String?) {

    }
    private fun setAdapter() {
        when (defaultType) {
            TYPE_ONGOING -> {
             /*   linearLayoutManager = LinearLayoutManager(this)
                fragmentAppointmentBinding.appointmentRv.isNestedScrollingEnabled = false
                orderListAdapter = OrderListAdapter(this, completedArrayListModel, TYPE_ONGOING)
                fragmentAppointmentBinding.appointmentRv.layoutManager = linearLayoutManager
                fragmentAppointmentBinding.appointmentRv.adapter = orderListAdapter
                orderListAdapter.setOnItemClickListener(this)
                orderListAdapter.notifyDataSetChanged()*/
            }
            TYPE_PREVIOUS -> {
                /*linearLayoutManager = LinearLayoutManager(this)
                fragmentAppointmentBinding.appointmentRv.isNestedScrollingEnabled = false
                orderListAdapter = OrderListAdapter(this, completedArrayListModel, TYPE_PREVIOUS)
                fragmentAppointmentBinding.appointmentRv.layoutManager = linearLayoutManager
                fragmentAppointmentBinding.appointmentRv.adapter = orderListAdapter
                orderListAdapter.setOnItemClickListener(this)
                orderListAdapter.notifyDataSetChanged()*/
            }
            TYPE_UPCOMING -> {
               /* linearLayoutManager = LinearLayoutManager(this)
                fragmentAppointmentBinding.appointmentRv.isNestedScrollingEnabled = false
                orderListAdapter = OrderListAdapter(this, completedArrayListModel, TYPE_UPCOMING)
                fragmentAppointmentBinding.appointmentRv.layoutManager = linearLayoutManager
                fragmentAppointmentBinding.appointmentRv.adapter = orderListAdapter
                orderListAdapter.setOnItemClickListener(this)
                orderListAdapter.notifyDataSetChanged()*/
            }
        }
    }
    private fun selectTab(tabType: Int) {
        when (tabType) {
            TYPE_ONGOING -> {
                setSelected(fragmentAppointmentBinding.onGoingView, fragmentAppointmentBinding.onGoingTxt)
                defaultType=TYPE_ONGOING
                callGetPropertyApi()
            }
            TYPE_PREVIOUS -> {
                setSelected(fragmentAppointmentBinding.previousView, fragmentAppointmentBinding.previousText)
                defaultType=TYPE_PREVIOUS
                callGetPropertyApi()
            }
            TYPE_UPCOMING -> {
                setSelected(fragmentAppointmentBinding.upcomingView, fragmentAppointmentBinding.upcomingTxt)
                defaultType= TYPE_UPCOMING
                callGetPropertyApi()
            }
        }



    }

    private fun callGetPropertyApi() {

    }

    private fun setSelected(tabStatus: View, tabText: TextView) {
        fragmentAppointmentBinding.onGoingView.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.colorPrimary))
        fragmentAppointmentBinding.onGoingTxt.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
        fragmentAppointmentBinding.previousView.setBackgroundColor( ContextCompat.getColor(requireActivity(), R.color.colorPrimary))
        fragmentAppointmentBinding.previousText.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
        fragmentAppointmentBinding.upcomingView.setBackgroundColor( ContextCompat.getColor(requireActivity(), R.color.colorPrimary))
        fragmentAppointmentBinding.upcomingTxt.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))

        tabStatus.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.colorPrimary))
        tabText.setTextColor(ContextCompat.getColor(requireActivity(),R.color.colorPrimary))
    }

    override fun onClick(v: View?) {

    }
}