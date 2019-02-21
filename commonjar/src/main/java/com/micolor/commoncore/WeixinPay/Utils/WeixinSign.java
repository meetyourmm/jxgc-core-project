package com.micolor.commoncore.WeixinPay.Utils;

import com.github.wxpay.sdk.WXPayConstants;

import java.util.*;

import static com.github.wxpay.sdk.WXPayUtil.MD5;

/**
 * Created by Ann on 2017/8/29.
 */
public class WeixinSign {
    //http://mch.weixin.qq.com/wiki/doc/api/index.php?chapter=4_3
    //商户Key：改成公司申请的即可
    //32位密码设置地址：http://www.sexauth.com/  jdex1hvufnm1sdcb0e81t36k0d0f15nc
    /**
     * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
     *
     * @param data 待签名数据
     * @param key API密钥
     * @return 签名
     */
    public static String generateSignature(final Map<String, String> data, String key) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(WXPayConstants.FIELD_SIGN)) {
                continue;
            }
            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        sb.append("key=").append(key);
        String sign = MD5(sb.toString()).toUpperCase();
        return sign;
    }
}
