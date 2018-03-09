package tuonbondol.soapapi.android.sale

import android.content.Context
import android.databinding.ObservableField
import android.view.View
import com.tuonbondol.networkutil.isNetworkConnected
import tuonbondol.soapapi.android.BaseViewModel
import tuonbondol.soapapi.android.model.Alipay
import tuonbondol.soapapi.android.model.CreateQrCodeRequest
import tuonbondol.soapapi.android.model.ResponseCreateQr
import ig.pipayalipay.android.util.*
import tuonbondol.soapapi.android.R
import tuonbondol.soapapi.android.util.Constant
import tuonbondol.soapapi.android.util.Md5

/****
 *
 * Created by TUON BONDOL on 12/5/17.
 *
 */

var qrCodeResponse: ResponseCreateQr? = null
var totalFee: String? = ""
var outTradeNo: String? = ""

class SaleViewModel(context: Context) : BaseViewModel(context), CreateQrCodeRequest.CreateQRCode {

    val amount = ObservableField<String>("")

    val onGenerateQrClicked = View.OnClickListener {
        onRequestAlipayQrCode()
    }

    fun onRequestAlipayQrCode() {
        if (amount.get().isNullOrEmpty()) {
            onAlertError("Please enter amount to pay!")
            return
        }
        if (amount.get() == "0.00") {
            onAlertError("Please enter amount to pay!")
            return
        }

        if (!isNetworkConnected(mContext)) {
            onAlertError(mContext.resources.getString(R.string.no_network_connection))
            return
        }

        showLoading()

        totalFee = amount.get().replace(",", "")
        println("totalFee ${totalFee}")

        outTradeNo = onGenerateOutTradeNo()
        val deviceId = "9999"
        val notifyUrl = "${Constant.NOTI_URL}?hardware=$deviceId".plus(Constant.NOTI_TOKEN)
        val md5 = Md5.onGenerateMd5Code(notifyUrl = notifyUrl, outTradeNo = outTradeNo!!, totalFee = totalFee!!, merchantToken = Constant.MERCHANT_TOKEN)
        mMainModel.requestCreateQrCode(this, extend_params = Md5.onCreateExtendParams(), outTradeNo = outTradeNo!!, notifyUrl = notifyUrl, totalFee = totalFee!!, sign = md5)
    }

    override fun onQrCodeResponseSuccess(qrData: Alipay) {
        hideLoading()
        if (qrData.is_success == Constant.RESPONSE_SUCCESS_TRUE) {
            if (qrData.response.alipay.result_code == Constant.RESPONSE_SUCCESS) {
                qrCodeResponse = qrData.response.alipay
                onAlertError("Success ${qrCodeResponse.toString()}")
                return
            } else {
                onAlertError("Error Code" + qrData.response.alipay.detail_error_code.replace("_", " "))
                return
            }
        }
        onAlertError("Error Code ${qrData.is_success}")
        return
    }

    override fun onQrCodeResponseError(errorMessage: Throwable) {
        hideLoading()
        onAlertError("onResponseQrCodeError $errorMessage")
    }

    private fun onGenerateOutTradeNo() = "9999${System.currentTimeMillis()}"

}