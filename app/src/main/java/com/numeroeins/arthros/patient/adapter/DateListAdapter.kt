package com.numeroeins.arthros.patient.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.beans.DateListModel
import com.numeroeins.arthros.patient.databinding.SelectDateListItemBinding
import com.numeroeins.arthros.patient.utility.*

class DateListAdapter(activity: Activity, arrayList: ArrayList<DateListModel>) : RecyclerView.Adapter<DateListAdapter.ListViewHolder>() {

    var activity: Activity
    var arrayList: ArrayList<DateListModel>
    var utility: Utility

    init {
        this.activity = activity
        this.arrayList = arrayList
        utility = Utility()
    }


    class ListViewHolder(listBinding: SelectDateListItemBinding) : RecyclerView.ViewHolder(listBinding.root) {
        var listBinding: SelectDateListItemBinding

        init {
            this.listBinding = listBinding
        }

    }

    override fun getItemCount(): Int {
        return if (null != arrayList) arrayList.size else 0
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        val listBinding = DataBindingUtil.inflate<SelectDateListItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.select_date_list_item, parent, false)
        return ListViewHolder(listBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.listBinding.thisDate.text = utility.getChangeDateFormat(arrayList[position].date, TIME_FORMAT_yyyy_MM_dd, TIME_DATE_FORMAT_date)
        holder.listBinding.thisDay.text = utility.getChangeDateFormat(arrayList[position].date, TIME_FORMAT_yyyy_MM_dd, TIME_DATE_FORMAT_DAY)

        if (arrayList[position].selected!!)
        {
            holder.listBinding.dateLinLay.setBackgroundResource(R.drawable.custom_border_accent_small_capsule)
            holder.listBinding.thisDate.setTextColor(activity.resources.getColor(R.color.colorAccent))
            holder.listBinding.thisDay.setTextColor(activity.resources.getColor(R.color.colorAccent))
        } else if (arrayList[position].status!!)
        {
            holder.listBinding.dateLinLay.setBackgroundResource(R.drawable.custom_border_pri_small_capsule)
            holder.listBinding.thisDate.setTextColor(activity.resources.getColor(R.color.colorPrimary))
            holder.listBinding.thisDay.setTextColor(activity.resources.getColor(R.color.colorPrimary))
        } else {
            holder.listBinding.dateLinLay.setBackgroundResource(R.drawable.custom_bg_grey_small_capsule)
            holder.listBinding.thisDate.setTextColor(activity.resources.getColor(R.color.color_card_border))
            holder.listBinding.thisDay.setTextColor(activity.resources.getColor(R.color.color_card_border))
        }

        holder.listBinding.dateLinLay.setOnClickListener {
            if (mItemClickListener != null) {
                mItemClickListener?.onDateListItemClickListener(position);
            }
        }
    }

    private var mItemClickListener: onRecyclerViewItemClickListener? = null

    fun setOnItemClickListener(mItemClickListener: onRecyclerViewItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface onRecyclerViewItemClickListener {
        fun onDateListItemClickListener(position: Int)
    }
}