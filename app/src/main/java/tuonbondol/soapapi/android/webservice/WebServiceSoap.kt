package tuonbondol.soapapi.android.webservice

import okhttp3.OkHttpClient
import org.simpleframework.xml.convert.AnnotationStrategy
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit
import org.simpleframework.xml.core.Persister


/****
 *
 * Created by TUON BONDOL on 11/18/17.
 *
 */

object WebServiceSoap {

    private val baseLiveUrl: String = "https://openapi.alipaydev.com/"

    fun requestLive(): ServiceSoapApi {
        val mOkHttpClientBuilder = OkHttpClient().newBuilder()

        val mOkHttpClient = mOkHttpClientBuilder
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(baseLiveUrl)
                .addConverterFactory(
                        SimpleXmlConverterFactory.createNonStrict(Persister(AnnotationStrategy()))
                )
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build()
                .create(ServiceSoapApi::class.java)
    }
}