package com.numeroeins.arthros.patient.activity

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.databinding.ActivityAboutUsBinding


class AboutUsActivity : BaseActivity(), View.OnClickListener {
    lateinit var activityAboutUsBinding: ActivityAboutUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAboutUsBinding = DataBindingUtil.setContentView(this, R.layout.activity_about_us)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        initView()
    }

    private fun initView() {
        activityAboutUsBinding.topHeader.navTitle.text = resources.getText(R.string.about_us)
        activityAboutUsBinding.topHeader.backIcon.setOnClickListener(this)

        activityAboutUsBinding.aboutUsWebView.webViewClient = MyWebViewClient(this)
        activityAboutUsBinding.aboutUsWebView.settings.loadsImagesAutomatically = true
        activityAboutUsBinding.aboutUsWebView.settings.javaScriptEnabled = true
        activityAboutUsBinding.aboutUsWebView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY

        activityAboutUsBinding.aboutUsWebView.loadUrl("https://www.arthrosclinic.com/about-us-2/")
        closeLoader()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.backIcon -> {
                finish()
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
            }
        }
    }

    class MyWebViewClient internal constructor(private val activity: Activity) : WebViewClient() {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url: String = request?.url.toString();
            view?.loadUrl(url)
            return true
        }

        override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
            webView.loadUrl(url)
            return true
        }

        override fun onReceivedError(
            view: WebView,
            request: WebResourceRequest,
            error: WebResourceError
        ) {
            Toast.makeText(activity, "Got Error! $error", Toast.LENGTH_SHORT).show()
        }
    }
}