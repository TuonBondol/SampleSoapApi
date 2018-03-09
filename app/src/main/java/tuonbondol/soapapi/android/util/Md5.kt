package tuonbondol.soapapi.android.util

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils

/****
 *
 * Created by TUON BONDOL on 12/7/17.
 *
 */

object Md5 {
    fun onGetMd5FromString(text: String): String {
        return String(Hex.encodeHex(DigestUtils.md5(text)))
    }

    fun onGenerateMd5Code(notifyUrl: String, outTradeNo: String, totalFee: String, merchantToken: String): String {
        val preString = onCreatePreString(onCreateExtendParams(), notifyUrl, outTradeNo, totalFee, merchantToken)
        return onGetMd5FromString(preString)
    }

    private fun onCreatePreString(extendParams: String, notifyUrl: String, outTradeNo: String, totalFee: String, merchantToken: String): String {
        return "_input_charset=${Constant.INPUT_CHARSET}&alipay_seller_id=${Constant.PARTNER_ID}&body=iphone cellphone&currency=${Constant.CURRENCY_USD}&extend_params=$extendParams&it_b_pay=${Constant.IT_B_PAY}&notify_url=$notifyUrl&out_trade_no=$outTradeNo&partner=${Constant.PARTNER_ID}&passback_parameters=test&product_code=${Constant.OVERSEAS_MBARCODE_PAY}&sendFormat=${Constant.ALIPAY_SEND_FORMAT}&service=alipay.acquire.precreate&show_url=http://www.taobao.com/product/113714.html&subject=${Constant.ALIPAY_SUBJECT}&total_fee=$totalFee&trans_currency=${Constant.CURRENCY_USD}$merchantToken"
    }

    fun onCreateExtendParams(): String {
        val extendParam: String? = "{\"secondary_merchant_name\":\"Lotte\",\"secondary_merchant_id\":\"123\",\"secondary_merchant_industry\":\"5812\",\"store_id\":\"A101\",\"store_name\":\"McDonald in 966 3rd Ave, New York\"}"
        return extendParam!!
    }
}