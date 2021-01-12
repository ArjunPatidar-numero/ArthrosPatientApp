package com.numeroeins.arthros.patient.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.databinding.OurServicesAdapterBinding
import com.numeroeins.arthros.patient.databinding.OurSpecialitiesAdapterBinding
import com.numeroeins.arthros.patient.databinding.ServicesAllAdapterBinding
import com.numeroeins.arthros.patient.utility.UserPreference
import java.util.ArrayList

class ServicesAllAdapter(activity: Context, orderList: ArrayList<String>)
    : RecyclerView.Adapter<ServicesAllAdapter.ListViewHolder>() {
    var activity: Context
    private var orderList: ArrayList<String>? = null
    private var userPreference: UserPreference? = null
    init {
        this.activity = activity
        this.orderList = orderList
        userPreference = UserPreference.getInstance(activity);
    }


    class ListViewHolder(listBinding: ServicesAllAdapterBinding) : RecyclerView.ViewHolder(listBinding.root)
    {
        var listBinding: ServicesAllAdapterBinding
        init {
            this.listBinding = listBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val listBinding = DataBindingUtil.inflate<ServicesAllAdapterBinding>(
            LayoutInflater.from(parent.context)
            , R.layout.services_all_adapter, parent, false)
        return ListViewHolder(listBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        /* holder.listBinding.countryNameTxt.text= contactList!![position].name
         holder.listBinding. countryCodeTxt.text= contactList!![position].code+" "+ contactList!![position].dialCode
         */
        holder.listBinding.parentPanel.setOnClickListener{
            if (mItemClickListener != null) {
                mItemClickListener?.onOurServicesListItemClickListener(position);
            }
        }
    }
    private var mItemClickListener: onRecyclerViewItemClickListener? = null

    fun setOnItemClickListener(mItemClickListener: onRecyclerViewItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface onRecyclerViewItemClickListener {
        fun onOurServicesListItemClickListener(position: Int)

    }


    override fun getItemCount(): Int {
        return if (null != orderList) orderList!!.size else 0
    }
}