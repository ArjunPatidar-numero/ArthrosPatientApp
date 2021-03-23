package com.numeroeins.arthros.patient.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.databinding.AdapterSpinnerItemBinding


class SpinnerItemAdapter (activity: Context, arrayList:ArrayList<String>) : RecyclerView.Adapter<SpinnerItemAdapter.ListViewHolder>() {

    var activity: Context
    var arrayList:ArrayList<String>

   // private var userPreference: UserPreference? = null
    init {
        this.activity = activity
        this.arrayList = arrayList
    }

    class ListViewHolder(listBinding: AdapterSpinnerItemBinding) : RecyclerView.ViewHolder(listBinding.root)
    {
        var listBinding: AdapterSpinnerItemBinding
        init {
            this.listBinding = listBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val listBinding = DataBindingUtil.inflate<AdapterSpinnerItemBinding>(LayoutInflater.from(parent.context), R.layout.adapter_spinner_item ,parent ,false  )
        return ListViewHolder(
            listBinding
        )
    }

    override fun getItemCount(): Int {
        return if (null != arrayList) arrayList.size else 0
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.listBinding.thisText.text = arrayList[position]
        holder.listBinding.parentPanel.setOnClickListener{
            if (mItemClickListener != null) {
                mItemClickListener?.onSpinnerItemClickListener( position);
            }
        }
    }
    private var mItemClickListener: onRecyclerViewItemClickListener? = null

    fun setOnItemClickListener(mItemClickListener: onRecyclerViewItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface onRecyclerViewItemClickListener {
        fun onSpinnerItemClickListener(position: Int)
    }
}