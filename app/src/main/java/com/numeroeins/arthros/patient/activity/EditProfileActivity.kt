package com.numeroeins.arthros.patient.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bruce.pickerview.popwindow.DatePickerPopWin
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.adapter.SpinnerItemAdapter
import com.numeroeins.arthros.patient.databinding.ActivityEditProfileBinding
import com.numeroeins.arthros.patient.databinding.BottomSheetCustomDialogBinding
import com.numeroeins.arthros.patient.utility.*
import java.text.SimpleDateFormat
import java.util.*

class EditProfileActivity :BaseActivity(), View.OnClickListener,
    SpinnerItemAdapter.onRecyclerViewItemClickListener {

    private lateinit var dateOfBirth: String
    private var userPreference: UserPreference? = null
    lateinit var activityEditProfileBinding: ActivityEditProfileBinding
    private var type: Int = -1;
    private var genderType: Int = 0;
    private var bloodType: Int = 1;
    lateinit var utility: Utility
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityEditProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        userPreference = UserPreference.getInstance(applicationContext)
        init()
    }

    private fun init() {
        utility = Utility()
        activityEditProfileBinding.saveTxt.setOnClickListener(this)
        activityEditProfileBinding.ivBtnBackProfile.setOnClickListener(this)
        activityEditProfileBinding.dateOfBirthTv.setOnClickListener(this)
        activityEditProfileBinding.genderTv.setOnClickListener(this)
        activityEditProfileBinding.bloodGroupTv.setOnClickListener(this)

    }
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.saveTxt -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
            }

            R.id.ivBtnBackProfile -> {
                finish()
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
            }
            R.id.dateOfBirthTv -> {
                activityEditProfileBinding.datePickerLooper.visibility = View.VISIBLE
               val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val currentDate = sdf.format(Date())
                val pickerPopWin =
                    DatePickerPopWin.Builder(this,
                        DatePickerPopWin.OnDatePickedListener { year, month, day, dateDesc ->
                            dateOfBirth = "" + year + "/" + month + "/" + day
                            activityEditProfileBinding.dateOfBirthTv.text = utility.getChangeDateFormat(
                                dateOfBirth,
                                TIME_FORMAT_SLASH_YYYY_MM_DD,
                                TIME_FORMAT_SLASH_DD_MM_YYYY)

                            activityEditProfileBinding.datePickerLooper.visibility = View.GONE
                            //  Toast.makeText(RegisterActivity.this, dateDesc, Toast.LENGTH_SHORT).show();
                        }).textConfirm("DONE") //text of confirm button
                        .textCancel("CANCEL") //text of cancel button
                        .btnTextSize(16) // button text size
                        .viewTextSize(21) // pick view text size
                        .colorCancel(Color.parseColor("#999999")) //color of cancel button
                        .colorConfirm(Color.parseColor("#009900")) //color of confirm button
                        .minYear(1950) //min year in loop
                        .maxYear(2550) // max year in loop
                        .dateChose(currentDate) // date chose when init popwindow
                        .build()
                pickerPopWin.showPopWin(this)
            }
            R.id.genderTv -> {
                type = genderType
                initializeSpinnerItemBottomBar(type)
            }
            R.id.bloodGroupTv -> {
                type = bloodType
                initializeSpinnerItemBottomBar(type)
            }
        }
    }

    val spinnerItemArrayList: ArrayList<String> = ArrayList()
    lateinit var spinnerItemAdapter: SpinnerItemAdapter
    lateinit var providerbottomSheetDialog: BottomSheetDialog
    fun initializeSpinnerItemBottomBar(type : Int) {
        val myDrawerView = layoutInflater.inflate(R.layout.bottom_sheet_custom_dialog, null)
        val binding = BottomSheetCustomDialogBinding.inflate(
            layoutInflater,
            myDrawerView as ViewGroup,
            false
        )
        providerbottomSheetDialog = BottomSheetDialog(this)
        providerbottomSheetDialog.setContentView(binding.layoutBottomSheet)
        providerbottomSheetDialog.window?.setBackgroundDrawableResource(R.drawable.custom_tab_unselector_transparent)
        (binding.layoutBottomSheet.parent as View).setBackgroundResource(R.drawable.custom_tab_unselector_transparent)

        binding.cancelTxt.setOnClickListener {
            providerbottomSheetDialog.dismiss()

        }
        when(type){
            genderType ->{
                binding.thisTitle.text = resources.getText(R.string.select_gender)
                addGenderList()
            }
            bloodType ->{
                binding.thisTitle.text = resources.getText(R.string.select_blood_group)
                addBloodGroupList()
            }
        }
        spinnerItemAdapter = SpinnerItemAdapter(this, spinnerItemArrayList)
        binding.thisRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.thisRecyclerView.adapter = spinnerItemAdapter
        spinnerItemAdapter.notifyDataSetChanged()
        spinnerItemAdapter.setOnItemClickListener(this)
        providerbottomSheetDialog.show()
    }

    override fun onSpinnerItemClickListener(position: Int) {
        providerbottomSheetDialog.dismiss()
        when(type){
            genderType ->{
                activityEditProfileBinding.genderTv.text = spinnerItemArrayList[position]
            }
            bloodType ->{
                activityEditProfileBinding.bloodGroupTv.text = spinnerItemArrayList[position]
            }
        }
    }
    private fun addGenderList(){
        spinnerItemArrayList.clear()
        spinnerItemArrayList.add("Male")
        spinnerItemArrayList.add("Female")
        spinnerItemArrayList.add("Other")
    }

    private fun addBloodGroupList(){
        spinnerItemArrayList.clear()
        spinnerItemArrayList.add("O-")
        spinnerItemArrayList.add("O+")
        spinnerItemArrayList.add("A-")
        spinnerItemArrayList.add("A+")
        spinnerItemArrayList.add("B-")
        spinnerItemArrayList.add("B+")
        spinnerItemArrayList.add("AB-")
        spinnerItemArrayList.add("AB+")
    }
}