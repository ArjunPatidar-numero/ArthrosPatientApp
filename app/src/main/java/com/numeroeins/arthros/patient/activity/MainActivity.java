package com.numeroeins.arthros.patient.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.numeroeins.arthros.patient.R;
import com.numeroeins.arthros.patient.utility.customslider.views.DuoDrawerLayout;
import com.numeroeins.arthros.patient.utility.customslider.views.DuoMenuView;
import com.numeroeins.arthros.patient.utility.customslider.widgets.DuoDrawerToggle;

import java.util.ArrayList;
import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements DuoMenuView.OnMenuClickListener {
    // private MenuAdapter mMenuAdapter;
    private ViewHolder mViewHolder;
    private ArrayList<String> mTitles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
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
        goToFragment(new MainFragment(), false);
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

    private void goToFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.add(R.id.container, fragment).commit();
    }

    @Override
    public void onOptionClicked(int position, Object objectClicked) {
        // Set the toolbar title
        setTitle(mTitles.get(position));

        // Set the right options selected
        //   mMenuAdapter.setViewSelected(position, true);

        // Navigate to the right fragment
        switch (position) {
            default:
                goToFragment(new MainFragment(), false);
                break;
        }

        // Close the drawer
        mViewHolder.mDuoDrawerLayout.closeDrawer();
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
    private class ViewHolder implements View.OnClickListener {
        private DuoDrawerLayout mDuoDrawerLayout;
        private DuoMenuView mDuoMenuView;
        private Toolbar mToolbar;
        CircleImageView venuesImg;
        TextView nameTxt,emailTxt;
        ImageView closeImg;

        LinearLayout homeLinLay,doctorLinLay,appointmentLinLay,profileLinLay
                ,settingLinLay,notificationsLinLay,logoutLinLay;
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


            mToolbar = (Toolbar) findViewById(R.id.toolbar);
        }

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
