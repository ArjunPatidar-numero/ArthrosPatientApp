package com.numeroeins.arthros.patient.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.beans.NotificationModel
import com.numeroeins.arthros.patient.databinding.AdapterNotificationBinding
import com.numeroeins.arthros.patient.utility.UserPreference
import com.numeroeins.arthros.patient.utility.Utility
import java.util.*

class NotificationListAdapter(var activity: Context, notificationList: ArrayList<NotificationModel>) : RecyclerView.Adapter<NotificationListAdapter.ListViewHolder>() {
    private var notificationList: ArrayList<NotificationModel>? = null
    private var userPreference: UserPreference? = null
    var utility: Utility

    init {
        this.notificationList = notificationList
        userPreference = UserPreference.getInstance(activity)
        utility = Utility()
    }


    class ListViewHolder(var listBinding: AdapterNotificationBinding) :
        RecyclerView.ViewHolder(listBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val listBinding = DataBindingUtil.inflate<AdapterNotificationBinding>(
            LayoutInflater.from(parent.context), R.layout.adapter_notification, parent, false
        )
        return ListViewHolder(listBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val notificationModel:NotificationModel = notificationList!![position]
        holder.listBinding.notNameTxt.text = notificationModel.name
        holder.listBinding.notMessageTxt.text = notificationModel.notText
        holder.listBinding.notDateTxt.text = notificationModel.notDate
        holder.listBinding.notTimeTxt.text = notificationModel.notTime

        holder.listBinding.root.setOnClickListener(View.OnClickListener {
            mItemClickListener!!.onNotificationClickListener(position)
        })
    }

    override fun getItemCount(): Int {
        return notificationList!!.size
    }

    private var mItemClickListener: OnNotificationItemClickListener? = null

    fun setOnItemClickListener(mItemClickListener: OnNotificationItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface OnNotificationItemClickListener {
        fun onNotificationClickListener(position: Int)

    }
}