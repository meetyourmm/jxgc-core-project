package com.micolor.commoncore.httpclient.entity;

import java.util.Date;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.httpclient.entity
 *
 * @Author: Evangoe
 * @Description: 记录http请求日志的实体类
 * @Date:28/6/17
 * @Modified:
 */
public class InterfaceLogEntity {
    private String logId; //日志编号
    private Date logTime; //日志记录时间
    private String sendUrl; //请求地址
    private String sendParam; //请求参数
    private String sendResult; //返回结果
    private String sendType; //请求类型

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = (Date)logTime.clone();
    }

    public String getSendUrl() {
        return sendUrl;
    }

    public void setSendUrl(String sendUrl) {
        this.sendUrl = sendUrl;
    }

    public String getSendParam() {
        return sendParam;
    }

    public void setSendParam(String sendParam) {
        this.sendParam = sendParam;
    }

    public String getSendResult() {
        return sendResult;
    }

    public void setSendResult(String sendResult) {
        this.sendResult = sendResult;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }
}
