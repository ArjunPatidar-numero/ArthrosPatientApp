package com.numeroeins.arthros.patient.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.beans.SliderItem
import com.smarteist.autoimageslider.SliderViewAdapter
import java.util.ArrayList

class ImageSliderAdapter(private val context: Context, private val activity: Activity) :
    SliderViewAdapter<ImageSliderAdapter.SliderAdapterVH>() {
    private var mSliderItems: MutableList<SliderItem> =
        ArrayList()

    fun renewItems(sliderItems: MutableList<SliderItem>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mSliderItems.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(sliderItem: SliderItem) {
        mSliderItems.add(sliderItem)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_slider_layout_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val sliderItem = mSliderItems[position]
        viewHolder.textViewDescription.text = sliderItem.description
/*        viewHolder.textViewDescription.textSize = 16f
        viewHolder.textViewDescription.setTextColor(Color.RED)*/

        viewHolder.textViewTitle.text = sliderItem.title
   /*     viewHolder.textViewTitle.textSize = 16f
        viewHolder.textViewTitle.setTextColor(Color.RED)*/

        Glide.with(viewHolder.itemView)
            .load(sliderItem.imageUrl)
            .fitCenter()
            .into(viewHolder.imageViewBackground)
        viewHolder.itemView.setOnClickListener(View.OnClickListener {
            Toast.makeText(context, "This is item in position $position", Toast.LENGTH_SHORT)
                .show()
          /*  var intent = Intent(activity , BookOfferActivity::class.java)
            intent.putExtra("id",sliderItem.imageInt)
            activity.startActivity(intent)*/

        })
    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return mSliderItems.size
    }

    class SliderAdapterVH(itemView: View) :
        ViewHolder(itemView) {
        var thisitemView: View
        var imageViewBackground: ImageView
        var textViewDescription: TextView
        var textViewTitle: TextView

        init {
            imageViewBackground =
                itemView.findViewById(R.id.iv_auto_image_slider)
            textViewDescription = itemView.findViewById(R.id.textDescription)
            textViewTitle = itemView.findViewById(R.id.textTitle)
            this.thisitemView = itemView
        }
    }

}