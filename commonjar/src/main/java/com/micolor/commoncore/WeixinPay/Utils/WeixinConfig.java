package com.micolor.commoncore.WeixinPay.Utils;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dell on 2017/8/26.
 */
public class WeixinConfig implements WXPayConfig {
    private byte[] certData;
  /*  public WeixinConfig() throws Exception {
        String certPath = "./apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }*/

    public String getAppID() {
        return "wx5b5698781a274247";
    }

    public String getMchID() {
        return "1484648992";
    }

    public String getKey() {
        return "DCFB6703326FA6D1183C8CCA1D141A31";
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    /**
     * 获取当前时间的10位时间戳
     * @return
     * @throws ParseException
     */
    public String getTimeStamp() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());
        Date date = sdf.parse(strDate);
        String time_stamp = String.valueOf(date.getTime());
        return time_stamp;
    }
}
