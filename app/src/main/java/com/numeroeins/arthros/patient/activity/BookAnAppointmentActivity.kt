package com.numeroeins.arthros.patient.activity

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.databinding.ActivityBookAnAppointmentBinding
import com.numeroeins.arthros.patient.utility.CustomRatingBar
import com.numeroeins.arthros.patient.utility.UserPreference

class BookAnAppointmentActivity : AppCompatActivity(), View.OnClickListener {
    private var userPreference: UserPreference? = null
    private lateinit var activityBookAnAppointmentBinding: ActivityBookAnAppointmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBookAnAppointmentBinding = DataBindingUtil.setContentView(this, R.layout.activity_book_an_appointment)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        userPreference = UserPreference.getInstance(applicationContext)
        init()
    }
    private fun init() {
        activityBookAnAppointmentBinding.topHeader.navTitle.text = "Book An Appointment"
        activityBookAnAppointmentBinding.confirmAppointBtn.setOnClickListener(this)
        activityBookAnAppointmentBinding.topHeader.backIcon.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.confirmAppointBtn ->{
                initializeCustomMsgDialog()
            }

            R.id.backIcon ->{
                finishThis()
            }
        }
    }
    private fun finishThis(){
        finish()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }
    var dialog: Dialog? = null
    private fun initializeCustomMsgDialog() {
        dialog = Dialog(this)
        val parentView = layoutInflater.inflate(R.layout.custom_dialog, null)
        dialog!!.setContentView(parentView)
        parentView.minimumHeight = 200
        (parentView.parent as View).setBackgroundColor(Color.TRANSPARENT)

        parentView.findViewById<View>(R.id.doneDialogRelLay).setOnClickListener {
                dialog!!.cancel()
            finishThis()
        }
        dialog!!.show()
    }
}