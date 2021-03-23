package com.numeroeins.arthros.patient.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.beans.MenuItemModel
import com.numeroeins.arthros.patient.databinding.DrawerMenuItemBinding
import com.numeroeins.arthros.patient.utility.Utility

class DrawerMenuItemListAdapter(activity: Activity, var menuItemList: ArrayList<MenuItemModel>) : RecyclerView.Adapter<DrawerMenuItemListAdapter.ListViewHolder>() {

    var activity: Context = activity
    var utility: Utility = Utility()

    class ListViewHolder(var listBinding: DrawerMenuItemBinding) : RecyclerView.ViewHolder(listBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val listBinding = DataBindingUtil.
        inflate<DrawerMenuItemBinding>(LayoutInflater.from(parent.context), R.layout.drawer_menu_item, parent, false)
        return ListViewHolder(listBinding)
    }

    override fun getItemCount(): Int {
        return menuItemList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val menuItem : MenuItemModel = menuItemList[position]

        holder.listBinding.drawerMenuIcon.setImageResource(menuItem.menuIcon)
        holder.listBinding.drawerMenuText.text = menuItem.menuName

        holder.listBinding.root.setOnClickListener {
            if (mItemClickListener != null) {
                mItemClickListener?.onMenuItemClickListener(position);
            }
        }
    }

    private var mItemClickListener: OnMenuItemItemClickListener? = null

    fun setOnItemClickListener(mItemClickListener: OnMenuItemItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface OnMenuItemItemClickListener {
        fun onMenuItemClickListener(position: Int)

    }

}