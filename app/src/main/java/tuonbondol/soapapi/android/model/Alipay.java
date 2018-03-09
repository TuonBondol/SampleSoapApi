package tuonbondol.soapapi.android.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/****
 *
 * Created by TUON BONDOL on 12/4/17.
 *
 */


@Root
public class Alipay {

    @Element
    private String is_success;

    @Element(required = false)
    private String sign;

    @Element(required = false)
    private String sign_type;

    @Element(required = false)
    private ResponseQrData response;

    public String getIs_success() {
        return is_success;
    }

    public ResponseQrData getResponse() {
        return response;
    }

    public String getSign() {
        return sign;
    }

    public String getSign_type() {
        return sign_type;
    }
}
