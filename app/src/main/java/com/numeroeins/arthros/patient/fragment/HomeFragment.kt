package com.numeroeins.arthros.patient.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.databinding.FragmentHomeBinding
import com.numeroeins.arthros.patient.servermanager.request.CommonValueModel
import com.numeroeins.arthros.patient.utility.UserPreference
import io.reactivex.disposables.Disposable

class HomeFragment :BaseFragment(), FragmentBaseListener, View.OnClickListener{
    private lateinit var homeBinding: FragmentHomeBinding
    private var userPreference: UserPreference? = null

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val view: View = homeBinding.root
        setOnFragmentListener(this)
        userPreference = UserPreference.getInstance(requireActivity())
        callApi()
        return view
    }

    private fun callApi() {

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
}