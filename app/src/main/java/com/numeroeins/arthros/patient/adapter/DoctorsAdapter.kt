package com.numeroeins.arthros.patient.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.beans.ResponseDoctorListModel
import com.numeroeins.arthros.patient.databinding.OurDoctorsAdapterBinding
import com.numeroeins.arthros.patient.databinding.OurSpecialitiesAdapterBinding
import com.numeroeins.arthros.patient.utility.CLICK_TYPE_BOOK
import com.numeroeins.arthros.patient.utility.CLICK_TYPE_CALL
import com.numeroeins.arthros.patient.utility.CLICK_TYPE_PARENT
import com.numeroeins.arthros.patient.utility.UserPreference
import java.util.ArrayList

class DoctorsAdapter(var activity: Context, orderList: ArrayList<ResponseDoctorListModel.Datum>) : RecyclerView.Adapter<DoctorsAdapter.ListViewHolder>() {
    private var orderList: ArrayList<ResponseDoctorListModel.Datum>? = null
    private var userPreference: UserPreference? = null

    init {
        this.orderList = orderList
        userPreference = UserPreference.getInstance(activity)
    }

    class ListViewHolder(var listBinding: OurDoctorsAdapterBinding) : RecyclerView.ViewHolder(listBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val listBinding = DataBindingUtil.inflate<OurDoctorsAdapterBinding>(LayoutInflater.from(parent.context), R.layout.our_doctors_adapter, parent, false)
        return ListViewHolder(listBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        /* holder.listBinding.countryNameTxt.text= contactList!![position].name
         holder.listBinding. countryCodeTxt.text= contactList!![position].code+" "+ contactList!![position].dialCode
         */
        Glide.with(activity).load( orderList!![position].user!!.imageUrl)
                .placeholder(R.drawable.user_dummy_profile_grey)
                .into(holder.listBinding.doctorImg)

        holder.listBinding.thisRatingBar.score = 2.5f

        holder.listBinding.parentPanelRelLay.setOnClickListener{
            if (mItemClickListener != null) {
                mItemClickListener?.onDoctorsListItemClickListener(position, CLICK_TYPE_PARENT)
            }
        }
        holder.listBinding.callBtn.setOnClickListener{
            if (mItemClickListener != null) {
                mItemClickListener?.onDoctorsListItemClickListener(position, CLICK_TYPE_CALL)
            }
        }
        holder.listBinding.bookBtn.setOnClickListener{
            if (mItemClickListener != null) {
                mItemClickListener?.onDoctorsListItemClickListener(position, CLICK_TYPE_BOOK)
            }
        }
    }
    private var mItemClickListener: onRecyclerViewItemClickListener? = null

    fun setOnItemClickListener(mItemClickListener: onRecyclerViewItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface onRecyclerViewItemClickListener {
        fun onDoctorsListItemClickListener(position: Int, type:String)

    }


    override fun getItemCount(): Int {
        return if (null != orderList) orderList!!.size else 0
    }
}