package com.numeroeins.arthros.patient.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.activity.*
import com.numeroeins.arthros.patient.databinding.FragmentProfileBinding
import com.numeroeins.arthros.patient.servermanager.request.CommonValueModel
import com.numeroeins.arthros.patient.utility.UserPreference
import io.reactivex.disposables.Disposable

class ProfileFragment :BaseFragment(), FragmentBaseListener, View.OnClickListener{
    private lateinit var fragmentProfileBinding: FragmentProfileBinding
    private var userPreference: UserPreference? = null

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentProfileBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_profile,
            container,
            false
        )
        val view: View = fragmentProfileBinding.root
        setOnFragmentListener(this)
        userPreference = UserPreference.getInstance(requireActivity())

        initView()
        callApi()
        return view
    }

    private fun initView() {
        fragmentProfileBinding.editProfileLinLay.setOnClickListener(this)
        fragmentProfileBinding.changePasswordLinLay.setOnClickListener(this)
        fragmentProfileBinding.logoutLinLay.setOnClickListener(this)
        fragmentProfileBinding.chatsLinLay.setOnClickListener(this)
        fragmentProfileBinding.myDoctorLinLay.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.editProfileLinLay -> {
                val intent = Intent(activity, EditProfileActivity::class.java)
                startActivity(intent)
                activity!!.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }
            R.id.changePasswordLinLay -> {
                val intent = Intent(activity, ChangePasswordActivity::class.java)
                startActivity(intent)
                activity!!.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }
            R.id.myDoctorLinLay -> {
                val intent = Intent(activity, MyDoctorsActivity::class.java)
                startActivity(intent)
                activity!!.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }
            R.id.chatsLinLay -> {
                val intent = Intent(activity, ChatUserListActivity::class.java)
                startActivity(intent)
                activity!!.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }
            R.id.logoutLinLay -> {
                activity?.let { logOutPrompt(it) }
            }
        }
    }

    private fun callApi() {

    }

    override fun onFragmentApiSuccess(
        result: String?,
        apiName: String?,
        disposable: Disposable?,
        commonModel: CommonValueModel?
    ) {

    }

    override fun onFragmentApiFailure(message: String?, apiName: String?, disposable: Disposable?) {

    }

    override fun onReadWriteStoragePermissionAllow(medialTypes: String?) {

    }

    override fun onReadWriteStoragePermissionDeny(medialTypes: String?) {

    }


}