package tuonbondol.soapapi.android.model;

import org.simpleframework.xml.Element;

/****
 *
 * Created by TUON BONDOL on 12/4/17.
 *
 */

@Element(name = "response")
public class ResponseQrData {

    @Element(name = "alipay", required = false)
    private ResponseCreateQr alipay;

    public ResponseCreateQr getAlipay() {
        return alipay;
    }
}
