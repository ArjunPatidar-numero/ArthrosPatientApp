package com.numeroeins.arthros.patient.fragment

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.numeroeins.arthros.patient.activity.BaseActivity
import com.numeroeins.arthros.patient.R
import com.numeroeins.arthros.patient.utility.UserPreference

import com.numeroeins.arthros.patient.servermanager.request.CommonValueModel
import com.numeroeins.arthros.patient.servermanager.request.GetRequestModel
import com.numeroeins.arthros.patient.servermanager.request.PostRequestModel



open class BaseFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   String className = this.getClass().getSimpleName();
        //  Utils.sendScreenName(getActivity(), className);
    }

    protected fun getApiCall(context: Context?, pageName: String?, requestModel: GetRequestModel?, commonModel: CommonValueModel?) {
        if (activity != null) {
            (activity as BaseActivity?)!!.getApiCall(context, pageName!!, requestModel!!, commonModel!!)
        }
    }

    protected fun postApiCall(context: Context?, pageName: String?, postRequestModel: PostRequestModel?, commonModel: CommonValueModel?) {
        if (activity != null) {
            (activity as BaseActivity?)!!.postApiCall(context, pageName!!, postRequestModel!!, commonModel!!)
        }
    }
    protected fun setOnFragmentListener(fragmentBaseListener: FragmentBaseListener?) {
        if (activity != null) {
            (activity as BaseActivity?)?.setOnFragmentListener(fragmentBaseListener)
        }
    }

    protected fun showLoader(title: String?) {
        if (activity != null) {
            (activity as BaseActivity?)!!.showLoader(title)
        }
    }
    protected fun logOutPrompt(activity: Activity, userPreference: UserPreference){
        if (activity != null) {
            (activity as BaseActivity?)!!.logOutPrompt(activity,userPreference)
        }
    }

    protected fun closeLoader() {
        if (activity != null) {
            (activity as BaseActivity?)!!.closeLoader()
        }
    }

    override fun onResume() {
        super.onResume()
        // getActivity().registerReceiver(brTab, new IntentFilter(Constants.BROADCAST_SILENT_PUSH));
    }


    protected fun showSnackBar(view: View, message: String?) {
        val snackbar: Snackbar
        snackbar = Snackbar.make(view, message!!, Snackbar.LENGTH_SHORT)
        val snackBarView = snackbar.view
        snackBarView.background = view.context.resources.getDrawable(R.drawable.text_shaded)
        val textView = snackBarView.findViewById<TextView>(R.id.snackbar_text)
        textView.setTextColor(Color.WHITE)
        snackbar.show()
    }
    protected fun checkReadWriteStoragePermission( medialType: String ) {

        if (activity != null) {
            (activity as BaseActivity?)?.checkReadWriteStoragePermission(medialType)
        }
    }
    protected fun showLog(title: String?, message: String?) {
        if (activity != null) {
            (activity as BaseActivity?)!!.showLog(title, message!!)
        }
    }
    /* protected final void deleteApiCall(Context context, String pageName, DeleteRequestModel requestModel, CommonValueModel commonModel) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).deleteApiCall(context, pageName, requestModel, commonModel);
        }
    }

    protected final void putApiCall(Context context, String pageName, PostRequestModel requestModel, CommonValueModel commonModel) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).putApiCall(context, pageName, requestModel, commonModel);
        }
    }*/



    /*protected final void setResultCode(int resultCode) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).setResultCode(resultCode);
        }
    }
*/



    /* protected final void  callShareUrl(Context context, String key,String linkUrl, String fullDescription) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).shareImageUri(context,key, linkUrl,  fullDescription);
        }
    }*/
    /* protected final void checkLocationPermission() {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).checkLocationPermission();
        }
    }

    protected final void checkReadContactPermission() {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).checkReadContactPermission();
        }
    }

    protected final void checkCallPermission() {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).checkCallPermission();
        }
    }



    protected final void checkReadWriteStoragePermissionForShare(String medialType) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).checkReadWriteStoragePermissionForShare(medialType);
        }
    }


    protected final void showAlertDialogDeny(String message, String permissionType) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).alertViewDenyCameraGalleryPermission(getActivity(), getString(R.string.permission_denied)
                    , message, getString(R.string.str_negative_btn)
                    , getString(R.string.str_positive_btn), permissionType);
        }
    }*/
    /* protected void openIvitationShareDialog(Context context, String shareText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View adView = LayoutInflater.from(context).inflate(R.layout.dialog_invitation, null);
        builder.setView(adView);
        AlertDialog adCreate = builder.create();
        adCreate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        adCreate.getWindow().setGravity(Gravity.BOTTOM);
        adCreate.setCanceledOnTouchOutside(true);
        adView.findViewById(R.id.llShareWhatsApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adCreate.dismiss();
                shareOnWhatsApp(shareText);
                //onClickLlCreateReminder();
            }
        });
        adView.findViewById(R.id.llShareFB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adCreate.dismiss();
                shareOnMessenger(shareText);
                // showStoragePermissions();
                // fragmentBaseListener=null;
                //checkReadWriteStoragePermission(Constants.GALLERY);
            }
        });
        adView.findViewById(R.id.llSharePhonebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adCreate.dismiss();
                onContactShare();
                // shareOnMessenger(shareText);
                // showStoragePermissions();
                // fragmentBaseListener=null;
                //checkReadWriteStoragePermission(Constants.GALLERY);
            }
        });


        adCreate.show();
    }

  */

}
