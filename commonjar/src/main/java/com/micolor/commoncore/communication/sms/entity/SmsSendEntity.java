package com.micolor.commoncore.communication.sms.entity;

import java.util.Date;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.communication.sms.entity
 *
 * @Author: Evangoe
 * @Description: 短信记录实体类
 * @Date:10/06/17
 * @Modified:
 */
public class SmsSendEntity {
    /**
     * The Ss id. 短信记录主键
     */
    private String ssId;
    /**
     * The Client id. 发送的客户对象多个以分号隔开
     */
    private String clientId;
    /**
     * The Ss date. 短信发送的日期
     */
    private Date ssDate;
    /**
     * The Ss content.短信发送内容
     */
    private String ssContent;
    /**
     * The Ss status. 短信发送的状态
     */
    private String ssStatus;

    /**
     * Gets ss id.
     *
     * @return the ss id
     */
    public String getSsId() {
        return ssId;
    }

    /**
     * Sets ss id.
     *
     * @param ssId the ss id
     */
    public void setSsId(String ssId) {
        this.ssId = ssId;
    }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets client id.
     *
     * @param clientId the client id
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets ss date.
     *
     * @return the ss date
     */
    public Date getSsDate() {
        return ssDate;
    }

    /**
     * Sets ss date.
     *
     * @param ssDate the ss date
     */
    public void setSsDate(Date ssDate) {
        this.ssDate = ssDate;
    }

    /**
     * Gets ss content.
     *
     * @return the ss content
     */
    public String getSsContent() {
        return ssContent;
    }

    /**
     * Sets ss content.
     *
     * @param ssContent the ss content
     */
    public void setSsContent(String ssContent) {
        this.ssContent = ssContent;
    }

    /**
     * Gets ss status.
     *
     * @return the ss status
     */
    public String getSsStatus() {
        return ssStatus;
    }

    /**
     * Sets ss status.
     *
     * @param ssStatus the ss status
     */
    public void setSsStatus(String ssStatus) {
        this.ssStatus = ssStatus;
    }
}
