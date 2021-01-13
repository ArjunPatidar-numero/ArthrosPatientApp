package com.numeroeins.arthros.patient.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.databinding.DoctorsServicesAdapterBinding
import com.numeroeins.arthros.patient.databinding.OurDoctorsAdapterBinding
import com.numeroeins.arthros.patient.databinding.OurSpecialitiesAdapterBinding
import com.numeroeins.arthros.patient.utility.CLICK_TYPE_BOOK
import com.numeroeins.arthros.patient.utility.CLICK_TYPE_CALL
import com.numeroeins.arthros.patient.utility.CLICK_TYPE_PARENT
import com.numeroeins.arthros.patient.utility.UserPreference
import java.util.ArrayList

class DoctorServiceAdapter(activity: Context, orderList: ArrayList<String>)
    : RecyclerView.Adapter<DoctorServiceAdapter.ListViewHolder>() {
    var activity: Context
    private var orderList: ArrayList<String>? = null
    private var userPreference: UserPreference? = null
    init {
        this.activity = activity
        this.orderList = orderList
        userPreference = UserPreference.getInstance(activity);
    }


    class ListViewHolder(listBinding: DoctorsServicesAdapterBinding) : RecyclerView.ViewHolder(listBinding.root)
    {
        var listBinding: DoctorsServicesAdapterBinding
        init {
            this.listBinding = listBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val listBinding = DataBindingUtil.inflate<DoctorsServicesAdapterBinding>(
                LayoutInflater.from(parent.context)
                , R.layout.doctors_services_adapter, parent, false)
        return ListViewHolder(listBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        /* holder.listBinding.countryNameTxt.text= contactList!![position].name
         holder.listBinding. countryCodeTxt.text= contactList!![position].code+" "+ contactList!![position].dialCode

        holder.listBinding.parentPanel.setOnClickListener{
            if (mItemClickListener != null) {
                mItemClickListener?.onDoctorsListItemClickListener(position, CLICK_TYPE_PARENT);
            }
        }
        */
    }
/*
    private var mItemClickListener: onRecyclerViewItemClickListener? = null

    fun setOnItemClickListener(mItemClickListener: onRecyclerViewItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface onRecyclerViewItemClickListener {
        fun onDoctorsListItemClickListener(position: Int, type:String)

    }
*/


    override fun getItemCount(): Int {
        return if (null != orderList) orderList!!.size else 0
    }
}