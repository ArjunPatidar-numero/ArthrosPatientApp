package  com.numeroeins.arthros.patient.fragment

import  com.numeroeins.arthros.patient.servermanager.request.CommonValueModel
import io.reactivex.disposables.Disposable


/**
 * Created by Amit Dixit on 2/16/2019.
 */
interface FragmentBaseListener {
    fun onFragmentApiSuccess(result: String?, apiName: String?, disposable: Disposable?, commonModel: CommonValueModel?)
    fun onFragmentApiFailure(message: String?, apiName: String?, disposable: Disposable?)
    fun onReadWriteStoragePermissionAllow(medialTypes: String?)
    fun onReadWriteStoragePermissionDeny(medialTypes: String?)
}
