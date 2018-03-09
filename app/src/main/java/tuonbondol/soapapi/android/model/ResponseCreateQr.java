package tuonbondol.soapapi.android.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/****
 *
 * Created by TUON BONDOL on 12/4/17.
 *
 */

@Element(name = "alipay")
public class ResponseCreateQr {

    @Element (required = false)
    private String big_pic_url;

    @Element (required = false)
    private String out_trade_no;

    @Element (required = false)
    private String pic_url;

    @Element (required = false)
    private String qr_code;

    @Element (required = false)
    private String result_code;

    @Element(required = false)
    private String detail_error_code;

    @Element(required = false)
    private String small_pic_url;

    @Element(required = false)
    private String voucher_type;

    public String getBig_pic_url() {
        return big_pic_url;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public String getPic_url() {
        return pic_url;
    }

    public String getQr_code() {
        return qr_code;
    }

    public String getResult_code() {
        return result_code;
    }

    public String getDetail_error_code() {
        return detail_error_code;
    }

    public String getSmall_pic_url() {
        return small_pic_url;
    }

    public String getVoucher_type() {
        return voucher_type;
    }
}
