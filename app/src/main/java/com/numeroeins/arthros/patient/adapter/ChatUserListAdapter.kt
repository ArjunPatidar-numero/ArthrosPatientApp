package com.numeroeins.arthros.patient.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.beans.ChatUserListModel
import com.numeroeins.arthros.patient.databinding.AdapterChatUserListBinding
import com.numeroeins.arthros.patient.utility.Utility

class ChatUserListAdapter(activity: Activity, var arrayList: ArrayList<ChatUserListModel>) : RecyclerView.Adapter<ChatUserListAdapter.ListViewHolder>() {

    var activity: Context = activity
    var utility: Utility = Utility()

    class ListViewHolder(var listBinding: AdapterChatUserListBinding) : RecyclerView.ViewHolder(listBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val listBinding = DataBindingUtil.
        inflate<AdapterChatUserListBinding>(LayoutInflater.from(parent.context), R.layout.adapter_chat_user_list, parent, false)
        return ListViewHolder(listBinding)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        holder.listBinding.yardNameTxt.text = arrayList[position].name
        holder.listBinding.locationTxt.text = arrayList[position].email

        Glide.with(activity).load( arrayList[position].photo)
                .placeholder(R.drawable.user_dummy_profile_grey)
                .into(holder.listBinding.jobImg)
        holder.listBinding.parentViewCardView.setOnClickListener {
            if (mItemClickListener != null) {
                mItemClickListener?.onItemClickListener(position);
            }
        }
    }

    private var mItemClickListener: onRecyclerViewItemClickListener? = null

    fun setOnItemClickListener(mItemClickListener: onRecyclerViewItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface onRecyclerViewItemClickListener {
        fun onItemClickListener(position: Int)

    }

}