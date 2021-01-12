package com.numeroeins.arthros.patient.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.databinding.AdapterAppointmentBinding
import com.numeroeins.arthros.patient.servermanager.UrlManager
import com.numeroeins.arthros.patient.utility.UserPreference
import com.numeroeins.arthros.patient.utility.Utilities
import java.util.ArrayList

class OrderListAdapter(var activity: Context, orderList: ArrayList<String>, var type: Int)
: RecyclerView.Adapter<OrderListAdapter.ListViewHolder>() {
    private var orderList: ArrayList<String>? = null
    private var userPreference: UserPreference? = null
    var utility:Utilities
    init {
        this.orderList = orderList
        userPreference = UserPreference.getInstance(activity)
        utility= Utilities()
    }


    class ListViewHolder(var listBinding: AdapterAppointmentBinding) : RecyclerView.ViewHolder(listBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val listBinding = DataBindingUtil.inflate<AdapterAppointmentBinding>(LayoutInflater.from(parent.context)
                , R.layout.adapter_appointment, parent, false)
        return ListViewHolder(listBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {


    /*    Glide.with(activity).load(UrlManager.IMAGE_URL +orderList!![position].venue!!.images!![0])
                .error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(holder.listBinding.venueImg)

        holder.listBinding.itemPrice.text= orderList!![position].currency+" "+orderList!![position].totalAmount.toString()

        if(orderList!![position].itemsSelected!!.size>1){
            holder.listBinding.itemCount.text= orderList!![position].itemsSelected!!.size.toString()+" "+activity.resources.getString(R.string.items)
        }else{
            holder.listBinding.itemCount.text= orderList!![position].itemsSelected!!.size.toString()+" "+activity.resources.getString(R.string.items)
        }

        holder.listBinding.venueNameTxt.text= orderList!![position].venue!!.nameEn
        holder.listBinding.dateTimeTxt.text= utility.getChangeDateFormat(orderList!![position].createdOn,TIME_DATE_FORMAT_dd_MM_yyyy_with_TZ,TIME_FORMAT_DD_MMMM_yyyy)
        holder.listBinding.parentViewCardView.setOnClickListener{
            if (mItemClickListener != null) {
                mItemClickListener?.onOrderItemClickListener(position,type)
            }
        }*/

    }
    private var mItemClickListener: onRecyclerViewItemClickListener? = null

    fun setOnItemClickListener(mItemClickListener: onRecyclerViewItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface onRecyclerViewItemClickListener {
        fun onOrderItemClickListener(position: Int,type: Int)

    }


    override fun getItemCount(): Int {
        return if (null != orderList) orderList!!.size else 0
    }
}