package tuonbondol.soapapi.android

import android.app.Activity
import android.content.Context
import android.databinding.BaseObservable
import android.view.ViewGroup
import ig.pipayalipay.android.model.*
import tuonbondol.soapapi.android.util.LoadingView
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import tuonbondol.soapapi.android.model.CreateQrCodeRequest

/****
 *
 * Created by TUON BONDOL on 11/20/17.
 *
 */

abstract class BaseViewModel(protected val mContext: Context) : BaseObservable() {

    protected val mMainModel by lazy { CreateQrCodeRequest() }

    private val mLoadingViewParent: ViewGroup? = null
    private var loadingView: LoadingView? = null

    protected fun showLoading(alpha: Float? = 0.3f) {
        loadingView = loadingView ?: LoadingView(mContext).show(mContext as Activity, alpha, mLoadingViewParent)
    }

    protected fun hideLoading() {
        loadingView?.hide()
        loadingView = null
    }

    protected fun onAlertError(message: String = "Error message") {
        mContext.alert(message) {
            okButton { }
        }.show()
    }
}