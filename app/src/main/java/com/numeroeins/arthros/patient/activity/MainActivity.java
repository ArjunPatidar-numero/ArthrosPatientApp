package com.numeroeins.arthros.patient.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.numeroeins.arthros.patient.R;
import com.numeroeins.arthros.patient.chat.ChatActivity;
import com.numeroeins.arthros.patient.chat.UpdateUserWorkManager;
import com.numeroeins.arthros.patient.fragment.AppointmentFragment;
import com.numeroeins.arthros.patient.fragment.DoctorFragment;
import com.numeroeins.arthros.patient.fragment.HomeFragment;
import com.numeroeins.arthros.patient.fragment.ProfileFragment;
import com.numeroeins.arthros.patient.utility.Constants;
import com.numeroeins.arthros.patient.utility.customslider.views.DuoDrawerLayout;
import com.numeroeins.arthros.patient.utility.customslider.views.DuoMenuView;
import com.numeroeins.arthros.patient.utility.customslider.widgets.DuoDrawerToggle;

import java.util.ArrayList;
import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements DuoMenuView.OnMenuClickListener {
    // private MenuAdapter mMenuAdapter;
    private ViewHolder mViewHolder;
    private TextView titleTxt;
    private ImageView titleImg;
    private ArrayList<String> mTitles = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        titleImg = findViewById(R.id.titleImg);
        titleTxt = findViewById(R.id.titleTxt);

        mTitles = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.menuOptions)));
        // Initialize the views
        mViewHolder = new ViewHolder();
        // Handle toolbar actions
        handleToolbar();
        // Handle menu actions
        handleMenu();
        // Handle drawer actions
        handleDrawer();
        // Show main fragment in container
        changeTab(1);
        // mMenuAdapter.setViewSelected(0, true);
        setTitle(mTitles.get(0));
    }

    private void handleToolbar() {
        setSupportActionBar(mViewHolder.mToolbar);
    }

    private void handleDrawer() {
        DuoDrawerToggle duoDrawerToggle = new DuoDrawerToggle(this,
                mViewHolder.mDuoDrawerLayout,
                mViewHolder.mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        mViewHolder.mDuoDrawerLayout.setDrawerListener(duoDrawerToggle);
        duoDrawerToggle.syncState();

    }

    private void handleMenu() {
        //mMenuAdapter = new MenuAdapter(mTitles);

        // mViewHolder.mDuoMenuView.setOnMenuClickListener(this);
        //     mViewHolder.mDuoMenuView.setAdapter(mMenuAdapter);
    }

    @Override
    public void onFooterClicked() {
        Toast.makeText(this, "onFooterClicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHeaderClicked() {
        Toast.makeText(this, "onHeaderClicked", Toast.LENGTH_SHORT).show();
    }

  /*  private void goToFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.add(R.id.container, fragment).commit();
    }*/

    @Override
    public void onOptionClicked(int position, Object objectClicked) {
        // Set the toolbar title
        setTitle(mTitles.get(position));

        // Set the right options selected
        //   mMenuAdapter.setViewSelected(position, true);

        // Navigate to the right fragment
       /* switch (position) {
            default:
                goToFragment(new MainFragment(), false);
                break;
        }*/

        // Close the drawer
        mViewHolder.mDuoDrawerLayout.closeDrawer();
    }



    public void changeTab(int pos){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment;
        switch (pos){
            case 1:

                titleImg.setVisibility(View.VISIBLE);
                titleTxt.setVisibility(View.GONE);
                homeTabImg.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), android.graphics.PorterDuff.Mode.MULTIPLY);
                doctorTabImg.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
                appointmentsTabImg.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
                profileTabImg.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);

                setBgWithOutStrokeColor(profileTabLinLayBack, ContextCompat.getColor(this, R.color.white));
                setBgWithOutStrokeColor(appointmentsTabLinLayBack, ContextCompat.getColor(this, R.color.white));
                setBgWithOutStrokeColor(doctorTabLinLayBack, ContextCompat.getColor(this, R.color.white));
                setBgWithOutStrokeColor(homeTabLinLayBack, ContextCompat.getColor(this, R.color.color_FF5119_20));

                fragment = new HomeFragment();
                fragmentTransaction.replace(R.id.container, fragment, "HomeFragment");
                fragmentTransaction.commitAllowingStateLoss();
                break;
            case 2:

                titleImg.setVisibility(View.GONE);
                titleTxt.setVisibility(View.VISIBLE);
                titleTxt.setText("Doctors");
                doctorTabImg.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), android.graphics.PorterDuff.Mode.MULTIPLY);
                homeTabImg  .setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
                appointmentsTabImg.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
                profileTabImg.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);


                setBgWithOutStrokeColor(profileTabLinLayBack, ContextCompat.getColor(this, R.color.white));
                setBgWithOutStrokeColor(appointmentsTabLinLayBack, ContextCompat.getColor(this, R.color.white));
                setBgWithOutStrokeColor(doctorTabLinLayBack, ContextCompat.getColor(this, R.color.color_FF5119_20));
                setBgWithOutStrokeColor(homeTabLinLayBack, ContextCompat.getColor(this, R.color.white));

                fragment = new DoctorFragment();
                fragmentTransaction.replace(R.id.container, fragment, "DoctorFragment");
                fragmentTransaction.commitAllowingStateLoss();
                break;
            case 3:
                titleImg.setVisibility(View.GONE);
                titleTxt.setVisibility(View.VISIBLE);
                titleTxt.setText("Appointment");
                appointmentsTabImg .setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), android.graphics.PorterDuff.Mode.MULTIPLY);
                homeTabImg  .setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
                doctorTabImg .setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
                profileTabImg.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);


                setBgWithOutStrokeColor(profileTabLinLayBack, ContextCompat.getColor(this, R.color.white));
                setBgWithOutStrokeColor(appointmentsTabLinLayBack, ContextCompat.getColor(this, R.color.color_FF5119_20));
                setBgWithOutStrokeColor(doctorTabLinLayBack, ContextCompat.getColor(this, R.color.white));
                setBgWithOutStrokeColor(homeTabLinLayBack, ContextCompat.getColor(this, R.color.white));


                fragment = new AppointmentFragment();
                fragmentTransaction.replace(R.id.container, fragment, "AppointmentFragment");
                fragmentTransaction.commitAllowingStateLoss();
                break;
            case 4:
                titleImg.setVisibility(View.GONE);
                titleTxt.setVisibility(View.VISIBLE);
                titleTxt.setText("Profile");
                profileTabImg.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), android.graphics.PorterDuff.Mode.MULTIPLY);
                homeTabImg  .setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
                doctorTabImg .setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
                appointmentsTabImg.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);


                setBgWithOutStrokeColor(profileTabLinLayBack, ContextCompat.getColor(this, R.color.color_FF5119_20));
                setBgWithOutStrokeColor(appointmentsTabLinLayBack, ContextCompat.getColor(this, R.color.white));
                setBgWithOutStrokeColor(doctorTabLinLayBack, ContextCompat.getColor(this, R.color.white));
                setBgWithOutStrokeColor(homeTabLinLayBack, ContextCompat.getColor(this, R.color.white));






                fragment = new ProfileFragment();
                fragmentTransaction.replace(R.id.container, fragment, "ProfileFragment");
                fragmentTransaction.commitAllowingStateLoss();
                break;
        }
    }


    public  void setBgWithOutStrokeColor(RelativeLayout school, int colors) {
        GradientDrawable bgShape = (GradientDrawable) school.getBackground();
        bgShape.setColor(colors);
        //  bgShape.setStroke(1, colors);
    }

  /*  @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.homeLinLay:
                mViewHolder.mDuoDrawerLayout.closeDrawer();
                break;
            case R.id.doctorLinLay:
                mViewHolder.mDuoDrawerLayout.closeDrawer();
                break;
            case R.id.appointmentLinLay:
                mViewHolder.mDuoDrawerLayout.closeDrawer();
                break;
            case R.id.profileLinLay:
                mViewHolder.mDuoDrawerLayout.closeDrawer();
                break;
            case R.id.settingLinLay:
                mViewHolder.mDuoDrawerLayout.closeDrawer();
                break;
            case R.id.notificationsLinLay:
                mViewHolder.mDuoDrawerLayout.closeDrawer();
                break;
            case R.id.logoutLinLay:
                break;
        }

    }
*/

    ImageView homeTabImg,doctorTabImg,appointmentsTabImg,profileTabImg;
    RelativeLayout homeTabLinLayBack,doctorTabLinLayBack,profileTabLinLayBack,appointmentsTabLinLayBack;

    private class ViewHolder implements View.OnClickListener {
        private DuoDrawerLayout mDuoDrawerLayout;
        private DuoMenuView mDuoMenuView;
        private Toolbar mToolbar;
        CircleImageView venuesImg;
        TextView nameTxt,emailTxt;
        ImageView closeImg;

        LinearLayout homeLinLay,doctorLinLay,appointmentLinLay,profileLinLay
                ,settingLinLay,notificationsLinLay,logoutLinLay
                ,profileTabLinLay,appointmentsTabLinLay,homeTabLinLay,doctorTabLinLay;
        ViewHolder() {
            mDuoDrawerLayout = (DuoDrawerLayout) findViewById(R.id.drawer);
            mDuoMenuView = (DuoMenuView) mDuoDrawerLayout.getMenuView();

            venuesImg  = mDuoDrawerLayout.getMenuView().findViewById(R.id.venuesImg);
            nameTxt  = mDuoDrawerLayout.getMenuView().findViewById(R.id.nameTxt);
            emailTxt  = mDuoDrawerLayout.getMenuView().findViewById(R.id.emailTxt);
            homeLinLay  = mDuoDrawerLayout.getMenuView().findViewById(R.id.homeLinLay);
            doctorLinLay  = mDuoDrawerLayout.getMenuView().findViewById(R.id.doctorLinLay);
            appointmentLinLay  = mDuoDrawerLayout.getMenuView().findViewById(R.id.appointmentLinLay);
            profileLinLay  = mDuoDrawerLayout.getMenuView().findViewById(R.id.profileLinLay);

            settingLinLay  = mDuoDrawerLayout.getMenuView().findViewById(R.id.settingLinLay);

            notificationsLinLay  = mDuoDrawerLayout.getMenuView().findViewById(R.id.notificationsLinLay);
            logoutLinLay  = mDuoDrawerLayout.getMenuView().findViewById(R.id.logoutLinLay);
            closeImg = mDuoDrawerLayout.getMenuView().findViewById(R.id.closeImg);

            homeLinLay.setOnClickListener(this);
            doctorLinLay.setOnClickListener(this);
            appointmentLinLay.setOnClickListener(this);
            profileLinLay.setOnClickListener(this);
            settingLinLay.setOnClickListener(this);
            notificationsLinLay.setOnClickListener(this);
            logoutLinLay.setOnClickListener(this);
            closeImg.setOnClickListener(this);

            homeTabLinLay = mDuoDrawerLayout.findViewById(R.id.homeTabLinLay);
            doctorTabLinLay = mDuoDrawerLayout.findViewById(R.id.doctorTabLinLay);
            appointmentsTabLinLay = mDuoDrawerLayout.findViewById(R.id.appointmentsTabLinLay);
            profileTabLinLay = mDuoDrawerLayout.findViewById(R.id.profileTabLinLay);

            homeTabLinLayBack = mDuoDrawerLayout.findViewById(R.id.homeTabLinLayBack);
            doctorTabLinLayBack = mDuoDrawerLayout.findViewById(R.id.doctorTabLinLayBack);
            appointmentsTabLinLayBack = mDuoDrawerLayout.findViewById(R.id.appointmentsTabLinLayBack);
            profileTabLinLayBack = mDuoDrawerLayout.findViewById(R.id.profileTabLinLayBack);



            homeTabImg = mDuoDrawerLayout.findViewById(R.id.homeTabImg);
            doctorTabImg = mDuoDrawerLayout.findViewById(R.id.doctorTabImg);
            appointmentsTabImg = mDuoDrawerLayout.findViewById(R.id.appointmentsTabImg);
            profileTabImg = mDuoDrawerLayout.findViewById(R.id.profileTabImg);


            homeTabLinLay.setOnClickListener(this);
            doctorTabLinLay.setOnClickListener(this);
            appointmentsTabLinLay.setOnClickListener(this);
            profileTabLinLay.setOnClickListener(this);

            mToolbar = (Toolbar) findViewById(R.id.toolbar);

        }

      @Override
      public void onClick(View view) {
          Intent intent;
          switch (view.getId()){
              case R.id.homeTabLinLay:
                  changeTab(1);
                  break;
              case R.id.doctorTabLinLay:
                  changeTab(2);
                  break;
              case R.id.appointmentsTabLinLay:
                  changeTab(3);
                  break;
              case R.id.profileTabLinLay:
                  changeTab(4);
                  break;
                  case R.id.homeLinLay:
                  mViewHolder.mDuoDrawerLayout.closeDrawer();
                      changeTab(1);
                  break;
              case R.id.doctorLinLay:
                  mViewHolder.mDuoDrawerLayout.closeDrawer();
                  changeTab(2);
                  break;
              case R.id.appointmentLinLay:
                  mViewHolder.mDuoDrawerLayout.closeDrawer();
                  changeTab(3);
                  break;
              case R.id.profileLinLay:
                  mViewHolder.mDuoDrawerLayout.closeDrawer();
                  changeTab(4);
                  break;
              case R.id.settingLinLay:
                  mViewHolder.mDuoDrawerLayout.closeDrawer();

                  intent= new Intent(MainActivity.this,SettingActivity.class);
                  startActivity(intent);
                  break;
              case R.id.notificationsLinLay:
                  mViewHolder.mDuoDrawerLayout.closeDrawer();
                  intent= new Intent(MainActivity.this,NotificationActivity.class);
                  startActivity(intent);
                  break;
              case R.id.logoutLinLay:
                  mViewHolder.mDuoDrawerLayout.closeDrawer();
                  logOutPrompt(MainActivity.this);
                  break;
              case R.id.closeImg:
                  mViewHolder.mDuoDrawerLayout.closeDrawer();
                  break;

          }
      }
  }



}
