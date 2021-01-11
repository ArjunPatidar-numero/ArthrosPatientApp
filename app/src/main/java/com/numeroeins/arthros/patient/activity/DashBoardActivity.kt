package com.numeroeins.arthros.patient.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.adapter.MenuSliderAdapter
import com.numeroeins.arthros.patient.utility.customslider.views.DuoDrawerLayout
import com.numeroeins.arthros.patient.utility.customslider.views.DuoMenuView
import com.numeroeins.arthros.patient.utility.customslider.views.DuoMenuView.OnMenuClickListener
import com.numeroeins.arthros.patient.utility.customslider.widgets.DuoDrawerToggle
import java.util.*


class DashBoardActivity:BaseActivity(), OnMenuClickListener {
    private var mMenuAdapter: MenuSliderAdapter? = null
    private var mViewHolder: ViewHolder? = null

    private var mTitles = ArrayList<String>()
   // lateinit var menuIconImgVw :ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = LayoutInflater.from(this)
        val myView: View = factory.inflate(R.layout.activity_main, null)
        mTitles = ArrayList(Arrays.asList(*resources.getStringArray(R.array.menuOptions)))

      /*  // Initialize the views
        mViewHolder = ViewHolder(myView)

        // Handle toolbar actions
        handleToolbar()

        // Handle menu actions
        handleMenu()

        // Handle drawer actions
        handleDrawer()

        // Show main fragment in container
        //   goToFragment(new MainFragment(), false);
        mMenuAdapter!!.setViewSelected(0, true)
        title = mTitles[0]*/
       initView()
    }

    private fun initView() {
        val factory = LayoutInflater.from(this)
        val myView: View = factory.inflate(R.layout.activity_main, null)
        mTitles = ArrayList(Arrays.asList(*resources.getStringArray(R.array.menuOptions)))
        mTitles = ArrayList(Arrays.asList(*resources.getStringArray(R.array.menuOptions)))
        // Initialize the views
        mViewHolder  = ViewHolder(myView)
        // Handle toolbar actions
        handleToolbar()
        // Handle menu actions
        handleMenu()
        // Handle drawer actions
        handleDrawer()
        // Show main fragment in container
        goToFragment(MainFragment(), false)
        // mMenuAdapter.setViewSelected(0, true);
        title = mTitles[0]
    }

    private fun handleToolbar() {
        setSupportActionBar(mViewHolder!!.mToolbar)
    }

    private fun handleDrawer() {
        val duoDrawerToggle = DuoDrawerToggle(
            this,
            mViewHolder!!.mDuoDrawerLayout,
            mViewHolder!!.mToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        mViewHolder!!.mDuoDrawerLayout.setDrawerListener(duoDrawerToggle)
        duoDrawerToggle.syncState()
    }

    private fun handleMenu() {
        //mMenuAdapter = new MenuAdapter(mTitles);

        // mViewHolder.mDuoMenuView.setOnMenuClickListener(this);
        //     mViewHolder.mDuoMenuView.setAdapter(mMenuAdapter);
    }

    override fun onFooterClicked() {
        Toast.makeText(this, "onFooterClicked", Toast.LENGTH_SHORT).show()
    }

    override fun onHeaderClicked() {
        Toast.makeText(this, "onHeaderClicked", Toast.LENGTH_SHORT).show()
    }

    private fun goToFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.add(R.id.container, fragment).commit()
    }

    override fun onOptionClicked(position: Int, objectClicked: Any?) {
        // Set the toolbar title
        title = mTitles[position]
        when (position) {
            else -> goToFragment(MainFragment(), false)
        }

        // Close the drawer
        mViewHolder!!.mDuoDrawerLayout.closeDrawer()
    }
    private class ViewHolder internal constructor(view: View) {
        var mDuoDrawerLayout: DuoDrawerLayout
        var mDuoMenuView: DuoMenuView
        var mToolbar: Toolbar



        init {

            mDuoDrawerLayout = view.findViewById(R.id.drawer) as DuoDrawerLayout
            mDuoMenuView = mDuoDrawerLayout.menuView as DuoMenuView
            mToolbar = view.findViewById<View>(R.id.toolbar) as Toolbar
        //    menuIconImgVw = view.findViewById(R.id.menuIconImgVw) as ImageView

        }
    }


}
