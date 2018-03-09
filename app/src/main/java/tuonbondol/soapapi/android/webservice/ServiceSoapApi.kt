package tuonbondol.soapapi.android.webservice

import tuonbondol.soapapi.android.model.Alipay
import tuonbondol.soapapi.android.util.Constant
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/****
 *
 * Created by TUON BONDOL on 12/4/17.
 *
 */

interface ServiceSoapApi {

    @GET("gateway.do")
    fun requestCreateQrCode(@Query("_input_charset") inputCharset: String = Constant.INPUT_CHARSET,
                            @Query("alipay_seller_id") alipay_seller_id: String = Constant.PARTNER_ID,
                            @Query("body") body: String = "iphone cellphone",
                            @Query("currency") currency: String = Constant.CURRENCY_USD,
                            @Query("extend_params") extend_params: String = "{\"secondary_merchant_name\":\"Lotte\",\"secondary_merchant_id\":\"123\",\"secondary_merchant_industry\":\"5812\",\"store_id\":\"A101\",\"store_name\":\"McDonald in 966 3rd Ave, New York\"}",
                            @Query("it_b_pay") itBPay: String = Constant.IT_B_PAY,
                            @Query("notify_url") notifyUrl: String,
                            @Query("out_trade_no") outTradeNo: String,
                            @Query("partner") partner: String = Constant.PARTNER_ID,
                            @Query("passback_parameters") passbackParameters: String = "test",
                            @Query("product_code") productCode: String = Constant.OVERSEAS_MBARCODE_PAY,
                            @Query("sendFormat") sendFormat: String = Constant.ALIPAY_SEND_FORMAT,
                            @Query("service") service: String = "alipay.acquire.precreate",
                            @Query("show_url") showUrl: String = "http://www.taobao.com/product/113714.html",
                            @Query("subject") subject: String = Constant.ALIPAY_SUBJECT,
                            @Query("total_fee") totalFee: String,
                            @Query("trans_currency") transCurrency: String = Constant.CURRENCY_USD,
                            @Query("sign") sign: String): Observable<Alipay>
}