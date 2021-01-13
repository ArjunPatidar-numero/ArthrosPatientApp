package com.numeroeins.arthros.patient.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.beans.TimeListModel
import com.numeroeins.arthros.patient.databinding.SelectTimeListItemBinding
import com.numeroeins.arthros.patient.utility.Utilities


class TimeListAdapter(activity: Activity, arrayList: ArrayList<TimeListModel>) : RecyclerView.Adapter<TimeListAdapter.ListViewHolder>() {

    var activity: Activity
    var arrayList: ArrayList<TimeListModel>
    var utility: Utilities

    init {
        this.activity = activity
        this.arrayList = arrayList
        utility = Utilities()
    }


    class ListViewHolder(listBinding: SelectTimeListItemBinding) : RecyclerView.ViewHolder(listBinding.root) {
        var listBinding: SelectTimeListItemBinding

        init {
            this.listBinding = listBinding
        }

    }

    override fun getItemCount(): Int {
        return if (null != arrayList) arrayList.size else 0
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        val listBinding = DataBindingUtil.inflate<SelectTimeListItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.select_time_list_item, parent, false)
        return ListViewHolder(listBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        if (arrayList[position].selected!!)
        {
            holder.listBinding.timeLinLay.setBackgroundResource(R.drawable.custom_border_accent_small_capsule)
            holder.listBinding.thisTime.setTextColor(activity.resources.getColor(R.color.colorAccent))
            holder.listBinding.thisTimeAPPM.setTextColor(activity.resources.getColor(R.color.colorAccent))
        } else if (arrayList[position].status!!)
        {
            holder.listBinding.timeLinLay.setBackgroundResource(R.drawable.custom_border_pri_small_capsule)
            holder.listBinding.thisTime.setTextColor(activity.resources.getColor(R.color.colorPrimary))
            holder.listBinding.thisTimeAPPM.setTextColor(activity.resources.getColor(R.color.colorPrimary))
        } else {
            holder.listBinding.timeLinLay.setBackgroundResource(R.drawable.custom_bg_grey_small_capsule)
            holder.listBinding.thisTime.setTextColor(activity.resources.getColor(R.color.color_card_border))
            holder.listBinding.thisTimeAPPM.setTextColor(activity.resources.getColor(R.color.color_card_border))
        }

        holder.listBinding.thisTime.text = arrayList[position].time
        holder.listBinding.thisTimeAPPM.text = arrayList[position].timeAP

        holder.listBinding.timeLinLay.setOnClickListener {
            if (mItemClickListener != null) {
                mItemClickListener?.onTimeListItemClickListener(position);
            }
        }

    }

    private var mItemClickListener: onRecyclerViewItemClickListener? = null

    fun setOnItemClickListener(mItemClickListener: onRecyclerViewItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface onRecyclerViewItemClickListener {
        fun onTimeListItemClickListener(position: Int)

    }
}
