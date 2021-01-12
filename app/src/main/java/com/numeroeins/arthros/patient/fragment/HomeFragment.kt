package com.numeroeins.arthros.patient.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.adapter.ImageSliderAdapter
import com.numeroeins.arthros.patient.adapter.OurSpecialitiesAdapter
import com.numeroeins.arthros.patient.beans.SliderItem
import com.numeroeins.arthros.patient.databinding.FragmentHomeBinding
import com.numeroeins.arthros.patient.servermanager.request.CommonValueModel
import com.numeroeins.arthros.patient.utility.UserPreference
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController
import com.smarteist.autoimageslider.SliderAnimations
import io.reactivex.disposables.Disposable

class HomeFragment :BaseFragment(), FragmentBaseListener, View.OnClickListener, OurSpecialitiesAdapter.onRecyclerViewItemClickListener {
    private lateinit var homeBinding: FragmentHomeBinding
    private var userPreference: UserPreference? = null

    private lateinit var ourSpecialitiesAdapter: OurSpecialitiesAdapter
    private val ourSpecialitiesArrayList:ArrayList<String> = ArrayList()

    var imageArrayList: ArrayList<SliderItem> = ArrayList()
    lateinit var imageSliderAdapter : ImageSliderAdapter
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
        initView()
        return view
    }

    private fun initView() {



        imageSliderAdapter = ImageSliderAdapter(requireActivity(),requireActivity())
        homeBinding.imageSlider!!.setSliderAdapter(imageSliderAdapter)
        homeBinding.imageSlider!!.setIndicatorAnimation(
            IndicatorAnimationType.WORM) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!

        homeBinding.imageSlider!!.setSliderTransformAnimation(
            SliderAnimations.SIMPLETRANSFORMATION)
        homeBinding.imageSlider!!.scrollTimeInSec = 4
        homeBinding.imageSlider!!.isAutoCycle = false
        homeBinding.imageSlider!!.startAutoCycle()

        homeBinding.imageSlider!!.setOnIndicatorClickListener(object : DrawController.ClickListener {
            override fun onIndicatorClicked(position: Int) {
                showSnackBar(
                    homeBinding.ourSpecialitiesRv,
                    "onIndicatorClicked: " + homeBinding.imageSlider!!.currentPagePosition
                )
            }
        })


        ourSpecialitiesAdapter= OurSpecialitiesAdapter(requireActivity(),ourSpecialitiesArrayList)
        homeBinding.ourSpecialitiesRv.layoutManager =   LinearLayoutManager(activity,  LinearLayoutManager.HORIZONTAL, false)
        homeBinding.ourSpecialitiesRv.adapter = ourSpecialitiesAdapter
        ourSpecialitiesAdapter.setOnItemClickListener(this)


        val sliderItem = SliderItem()
        sliderItem.description = "description 1"
        sliderItem.title = "title 1"
        sliderItem.imageUrl = "https://i.picsum.photos/id/870/200/300.jpg?hmac=ujRymp644uYVjdKJM7kyLDSsrqNSMVRPnGU99cKl6Vs"
        imageArrayList.add(sliderItem)

        val sliderItem2 = SliderItem()
        sliderItem2.description = "description 2"
        sliderItem2.title = "title 2"
        sliderItem2.imageUrl = "https://i.picsum.photos/id/866/200/300.jpg?hmac=rcadCENKh4rD6MAp6V_ma-AyWv641M4iiOpe1RyFHeI"
        imageArrayList.add(sliderItem2)

        val sliderItem3 = SliderItem()
        sliderItem3.description = "description 3"
        sliderItem3.title = "title 3"
        sliderItem3.imageUrl = "https://i.picsum.photos/id/237/200/300.jpg?hmac=TmmQSbShHz9CdQm0NkEjx1Dyh_Y984R9LpNrpvH2D_U"
        imageArrayList.add(sliderItem3)

        imageSliderAdapter.renewItems(imageArrayList)


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

    override fun onOurSpecialitiesListItemClickListener(position: Int) {
        TODO("Not yet implemented")
    }
}