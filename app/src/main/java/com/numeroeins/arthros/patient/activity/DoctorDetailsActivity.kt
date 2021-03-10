package com.numeroeins.arthros.patient.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.adapter.*
import com.numeroeins.arthros.patient.beans.DateListModel
import com.numeroeins.arthros.patient.beans.TimeListModel
import com.numeroeins.arthros.patient.databinding.*
import com.numeroeins.arthros.patient.utility.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

import com.numeroeins.arthros.patient.chat.ChatActivity

class DoctorDetailsActivity : BaseActivity(), View.OnClickListener, DateListAdapter.onRecyclerViewItemClickListener, TimeListAdapter.onRecyclerViewItemClickListener{

    private var userPreference: UserPreference? = null
    lateinit var activityDoctorDetailsBinding: ActivityDoctorDetailsBinding
    private lateinit var doctorServiceAdapter: DoctorServiceAdapter
    private val doctorServiceArrayList:ArrayList<String> = ArrayList()
    private lateinit var doctorRatingReviewAdapter: DoctorRatingReviewAdapter
    private val doctorRatingReviewArrayList:ArrayList<String> = ArrayList()
    var rating = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDoctorDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_doctor_details)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        userPreference = UserPreference.getInstance(applicationContext)
        init()
    }

    private fun init() {

        activityDoctorDetailsBinding.topHeader.navTitle.text = resources.getString(R.string.doctor_details)
        activityDoctorDetailsBinding.topHeader.backIcon.setOnClickListener(this)
        activityDoctorDetailsBinding.bookAppointment.setOnClickListener(this)
        activityDoctorDetailsBinding.bookBtn.setOnClickListener(this)
        activityDoctorDetailsBinding.chatBtn.setOnClickListener(this)
        activityDoctorDetailsBinding.reviewBtn.setOnClickListener(this)

        val pullToRefresh: SwipeRefreshLayout = activityDoctorDetailsBinding!!.pullToRefresh
        pullToRefresh.setOnRefreshListener {
            init() // your code
            pullToRefresh.isRefreshing = false
        }

        doctorServiceArrayList.add("")
        doctorServiceArrayList.add("")
        doctorServiceArrayList.add("")
        doctorServiceArrayList.add("")
        doctorServiceArrayList.add("")


        if(doctorServiceArrayList.size>0)
        {
            activityDoctorDetailsBinding.doctorServicesRecyclerView.visibility = View.VISIBLE
            activityDoctorDetailsBinding.noDataAvailableTxt.visibility = View.GONE
        }else{
            activityDoctorDetailsBinding.doctorServicesRecyclerView.visibility = View.GONE
            activityDoctorDetailsBinding.noDataAvailableTxt.visibility = View.VISIBLE
        }

        doctorServiceAdapter= DoctorServiceAdapter(this,doctorServiceArrayList)
        activityDoctorDetailsBinding.doctorServicesRecyclerView.layoutManager =   LinearLayoutManager(this,  LinearLayoutManager.VERTICAL, false)
        activityDoctorDetailsBinding.doctorServicesRecyclerView.adapter = doctorServiceAdapter
        doctorServiceAdapter.notifyDataSetChanged()



        doctorRatingReviewArrayList.add("")
        doctorRatingReviewArrayList.add("")
        doctorRatingReviewArrayList.add("")
        doctorRatingReviewArrayList.add("")
        doctorRatingReviewArrayList.add("")


        if(doctorRatingReviewArrayList.size>0)
        {
            activityDoctorDetailsBinding.ratingReviewsRecyclerView.visibility = View.VISIBLE
            activityDoctorDetailsBinding.noDataAvailableRatingReviewsTxt.visibility = View.GONE
        }else{
            activityDoctorDetailsBinding.ratingReviewsRecyclerView.visibility = View.GONE
            activityDoctorDetailsBinding.noDataAvailableRatingReviewsTxt.visibility = View.VISIBLE
        }

        doctorRatingReviewAdapter= DoctorRatingReviewAdapter(this,doctorRatingReviewArrayList)
        activityDoctorDetailsBinding.ratingReviewsRecyclerView.layoutManager =   LinearLayoutManager(this,  LinearLayoutManager.HORIZONTAL, false)
        activityDoctorDetailsBinding.ratingReviewsRecyclerView.adapter = doctorRatingReviewAdapter
        doctorRatingReviewAdapter.notifyDataSetChanged()
    }
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.backIcon -> {
                finishThis()
            }
            R.id.bookAppointment -> {
                initializeSportListBottomBar()
            }
            R.id.bookBtn -> {
                initializeChildBottomBarBottomBar()
            }
            R.id.chatBtn -> {
                val intent = Intent(this, ChatActivity::class.java)
                intent.putExtra(PARAM_RECEIVER_ID, "1")
                intent.putExtra(PARAM_RECEIVER_NAME,"Rahul")
                intent.putExtra(PARAM_RECEIVER_IMAGE, "")
                startActivity(intent)
            }
            R.id.reviewBtn -> {
                initializeChildBottomBarBottomBar()
            }
        }
    }

    private fun finishThis(){
        finish()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    var bottomSheetDialog: BottomSheetDialog? = null
    fun initializeChildBottomBarBottomBar() {
        bottomSheetDialog = BottomSheetDialog(this)
        val parentView = layoutInflater.inflate(R.layout.bottom_sheet_rating, null)
        bottomSheetDialog!!.setContentView(parentView)
        parentView.minimumHeight = 200
        (parentView.parent as View).setBackgroundColor(Color.TRANSPARENT)
        parentView.findViewById<View>(R.id.cancelImg)
                .setOnClickListener { bottomSheetDialog!!.cancel() }

        (parentView.findViewById<View>(R.id.thisRatingBar) as CustomRatingBar).setScore(rating.toFloat())


        parentView.findViewById<View>(R.id.submitTxt).setOnClickListener {
            if ((parentView.findViewById<View>(R.id.thisRatingBar) as CustomRatingBar).getScore() === 0.0f) {
                Toast.makeText(this, resources.getString(R.string.rating_msg)
                        , Toast.LENGTH_SHORT).show()
            } else {
                bottomSheetDialog!!.cancel()


            }
        }
        bottomSheetDialog!!.show()
    }

    lateinit var timeListAdapter: TimeListAdapter
    lateinit var dateListAdapter: DateListAdapter
    var timeArrayList: ArrayList<TimeListModel> = ArrayList()
    var dateArrayList: ArrayList<DateListModel> = ArrayList()
    lateinit var providerbottomSheetDialog: BottomSheetDialog
    lateinit var bookCourtBtn: RelativeLayout
    lateinit var bookTxt: TextView
    var timeSelected = ""
    var dateSelected = ""
    fun initializeSportListBottomBar() {
        timeSelected=""
        dateSelected=""
        val myDrawerView = layoutInflater.inflate(R.layout.book_appointment_bottom_sheet, null)
        val binding = BookAppointmentBottomSheetBinding.inflate(layoutInflater, myDrawerView as ViewGroup, false)
        providerbottomSheetDialog = BottomSheetDialog(this)
        providerbottomSheetDialog.setContentView(binding.layoutBottomSheet)
        providerbottomSheetDialog.window?.setBackgroundDrawableResource(R.drawable.custom_tab_unselector_transparent)
        (binding.layoutBottomSheet.parent as View).setBackgroundResource(R.drawable.custom_tab_unselector_transparent)

        calculateDate()
        calculateTime()
        bookTxt = binding.bookTxt
        bookCourtBtn = binding.bookAppointment

        bookCourtBtn.setOnClickListener(View.OnClickListener {
            if (!timeSelected.isEmpty() && !dateSelected.isEmpty()) {
                providerbottomSheetDialog.dismiss()
                val intent = Intent(this, BookAnAppointmentActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }else{
                showSnackBar(bookCourtBtn, "Please select date & time")
            }
        })

        dateListAdapter = DateListAdapter(this, dateArrayList)
        binding.selectDate.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.selectDate.adapter = dateListAdapter
        dateListAdapter.notifyDataSetChanged()
        dateListAdapter.setOnItemClickListener(this)

        timeListAdapter = TimeListAdapter(this, timeArrayList)
        binding.selectTime.layoutManager = GridLayoutManager(this,4, LinearLayoutManager.VERTICAL, false)
        binding.selectTime.adapter = timeListAdapter
        timeListAdapter.notifyDataSetChanged()
        timeListAdapter.setOnItemClickListener(this)

        providerbottomSheetDialog.show()

    }


    @SuppressLint("SimpleDateFormat")
    private fun calculateDate() {
        dateArrayList = ArrayList()
        var dateFormat: DateFormat = SimpleDateFormat(TIME_FORMAT_yyyy_MM_dd, Locale.getDefault())
        for (i in 1..7) {
            val dateLis = DateListModel()
            val currentDate = Date()
            val c = Calendar.getInstance()
            c.setTime(currentDate)
            c.add(Calendar.DATE, i - 1);
            val currentDatePlusOne = c.time
            dateLis.date = dateFormat.format(currentDatePlusOne)
            if(i==1 || i==4)
            {
                dateLis.status = false
            }else if(i==3){
                dateLis.selected = true
            }else{
                dateLis.status = true
            }
            dateArrayList.add(dateLis)

        }
    }

    private fun calculateTime() {
        timeArrayList = ArrayList()
        for (i in 1..12) {
            val timeListModel = TimeListModel()
            timeListModel.time = i.toString()+":00"
            timeListModel.timeAP = "AM"

            if(i==2 || i==7 || i==8 || i==12)
            {
                timeListModel.status = false
            }else if(i==5){
                timeListModel.selected = true
            }else{
                timeListModel.status = true
            }


            timeArrayList.add(timeListModel)
        }
    }

    override fun onDateListItemClickListener(position: Int) {
        for (i in 0..dateArrayList.size) {
            if (i < dateArrayList.size) {
                dateArrayList.get(i).selected = false
            }
        }
        dateSelected = dateArrayList.get(position).date!!
        dateArrayList.get(position).selected = true
        dateListAdapter.notifyDataSetChanged()

    }

    override fun onTimeListItemClickListener(position: Int) {
        for (i in 0..timeArrayList.size) {
            if (i < timeArrayList.size) {
                timeArrayList.get(i).selected = false
            }
        }
        timeSelected = timeArrayList.get(position).time!!
        timeArrayList.get(position).selected = true
        timeListAdapter.notifyDataSetChanged()

    }


}