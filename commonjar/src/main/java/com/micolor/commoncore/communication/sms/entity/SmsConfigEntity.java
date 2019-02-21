package com.micolor.commoncore.communication.sms.entity;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.communication.sms.entity
 *
 * @Author: Evangoe
 * @Description: 短信发送基础配置实体类
 * @Date:06/06/17
 * @Modified:
 */
public class SmsConfigEntity {
    /**
     * The Sms config id. 短信配置主键
     */
    private String  smsConfigId;
    /**
     * The Sms company. 短信公司
     */
    private String smsCompany;
    /**
     * The Sms url. 提供的url
     */
    private String smsUrl;
    /**
     * The Sms account. 提供的用户名称
     */
    private String smsAccount;
    /**
     * The Sms password.提供的密码
     */
    private String smsPassword;
    /**
     * The Sms type. 提供的类型（1验证码类型，2通知类型）
     */
    private String smsType;
    /**
     * The Sms suffix. 默认的后缀名称
     */
    private String smsSuffix;

    /**
     * Gets sms config id.
     *
     * @return the sms config id
     */
    public String getSmsConfigId() {
        return smsConfigId;
    }

    /**
     * Sets sms config id.
     *
     * @param smsConfigId the sms config id
     */
    public void setSmsConfigId(String smsConfigId) {
        this.smsConfigId = smsConfigId;
    }

    /**
     * Gets sms company.
     *
     * @return the sms company
     */
    public String getSmsCompany() {
        return smsCompany;
    }

    /**
     * Sets sms company.
     *
     * @param smsCompany the sms company
     */
    public void setSmsCompany(String smsCompany) {
        this.smsCompany = smsCompany;
    }

    /**
     * Gets sms url.
     *
     * @return the sms url
     */
    public String getSmsUrl() {
        return smsUrl;
    }

    /**
     * Sets sms url.
     *
     * @param smsUrl the sms url
     */
    public void setSmsUrl(String smsUrl) {
        this.smsUrl = smsUrl;
    }

    /**
     * Gets sms account.
     *
     * @return the sms account
     */
    public String getSmsAccount() {
        return smsAccount;
    }

    /**
     * Sets sms account.
     *
     * @param smsAccount the sms account
     */
    public void setSmsAccount(String smsAccount) {
        this.smsAccount = smsAccount;
    }

    /**
     * Gets sms password.
     *
     * @return the sms password
     */
    public String getSmsPassword() {
        return smsPassword;
    }

    /**
     * Sets sms password.
     *
     * @param smsPassword the sms password
     */
    public void setSmsPassword(String smsPassword) {
        this.smsPassword = smsPassword;
    }

    /**
     * Gets sms type.
     *
     * @return the sms type
     */
    public String getSmsType() {
        return smsType;
    }

    /**
     * Sets sms type.
     *
     * @param smsType the sms type
     */
    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }


    /**
     * Gets sms suffix.
     *
     * @return the sms suffix
     */
    public String getSmsSuffix() {
        return smsSuffix;
    }

    /**
     * Sets sms suffix.
     *
     * @param smsSuffix the sms suffix
     */
    public void setSmsSuffix(String smsSuffix) {
        this.smsSuffix = smsSuffix;
    }
}
