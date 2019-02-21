package com.micolor.commoncore.WeixinPay.Utils;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Zhuxd on 2017/11/9.
 */
public class NeipaiWeixinConfig implements WXPayConfig {
    private byte[] certData;

    public NeipaiWeixinConfig() {
    }

    public String getAppID() {
        return "wx396a8189bb90c5d8";
    }

    public String getMchID() {
        return "1488371142";
    }

    public String getKey() {
        return "DCFB6703326FA6D1183C8CCA1D141A31";
    }

    public InputStream getCertStream() {
        return new ByteArrayInputStream(this.certData);
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    public String getTimeStamp() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());
        Date date = sdf.parse(strDate);
        return String.valueOf(date.getTime());
    }
}
