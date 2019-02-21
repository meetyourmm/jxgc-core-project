package com.micolor.commoncore.shiro.config;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @Title: root
 * @Package com.micolor.commoncore.shiro.config
 * @Author: GeYupeng
 * @Date: 10:36 2018/7/30
 * @Modified:
 * @Description: TODO 填写描述。
 */
public class CustomizedToken extends UsernamePasswordToken {
    private  String certificateType;//凭证类型 凭证类型（1 用户名 2 邮箱 3 手机）

    public CustomizedToken(final String userName,final String password,String certificateType){
        super(userName,password);
        this.certificateType = certificateType;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }
}
