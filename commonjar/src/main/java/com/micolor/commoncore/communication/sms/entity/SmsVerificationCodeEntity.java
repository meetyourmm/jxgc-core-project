package com.micolor.commoncore.communication.sms.entity;

import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.business.data.entity.sms
 *
 * @Author: liusi
 * @Description: 短信校验实体
 * @Date:2017/6/9
 * @Modified:
 */
@Mapper
public class SmsVerificationCodeEntity {

    private String csId;//主键ID
    private String mobile;//手机号
    private String yzCode;//验证码
    private Date sendTime;//发送时间
    private String ip;//ip

    public String getCsId() {
        return csId;
    }

    public void setCsId(String csId) {
        this.csId = csId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getYzCode() {
        return yzCode;
    }

    public void setYzCode(String yzCode) {
        this.yzCode = yzCode;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
