package tuonbondol.soapapi.android.model

import android.databinding.BaseObservable
import tuonbondol.soapapi.android.webservice.WebServiceSoap
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/****
 *
 * Created by TUON BONDOL on 11/20/17.
 *
 */

class CreateQrCodeRequest : BaseObservable() {

    var mCreateQRCode: CreateQRCode? = null

    fun requestCreateQrCode(createQrCode: CreateQRCode, extend_params:String, outTradeNo: String, notifyUrl: String, sign: String, totalFee: String) {
        mCreateQRCode = createQrCode

        WebServiceSoap.requestLive().requestCreateQrCode(extend_params = extend_params, outTradeNo = outTradeNo, notifyUrl = notifyUrl, sign = sign, totalFee = totalFee)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onQrCodeResponseSuccess, this::onQrCodeResponseError)
    }

    interface CreateQRCode {
        fun onQrCodeResponseSuccess(qrData: Alipay)
        fun onQrCodeResponseError(errorMessage: Throwable)
    }

    private fun onQrCodeResponseSuccess(qrData: Alipay) {
        mCreateQRCode?.onQrCodeResponseSuccess(qrData)
    }

    private fun onQrCodeResponseError(errorMessage: Throwable) {
        mCreateQRCode?.onQrCodeResponseError(errorMessage)
    }
}