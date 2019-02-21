package com.micolor.commoncore.communication.mail.entity;

import java.util.Date;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.communication.mail.entity
 *
 * @Author: Evangoe
 * @Description: 邮件发送日志实体类
 * @Date:15/06/17
 * @Modified:
 */
public class MailSendLogEntity {
    /**
     * The Mail send id. 发送日志编号
     */
    private String mailSendId;
    /**
     * The Mail send target. 发送对象集合
     */
    private String mailSendTarget;
    /**
     * The Mail send content.发送内容
     */
    private String mailSendContent;
    /**
     * The Mail send date. 发送日期
     */
    private Date mailSendDate;

    /**
     * Gets mail send id.
     *
     * @return the mail send id
     */
    public String getMailSendId() {
        return mailSendId;
    }

    /**
     * Sets mail send id.
     *
     * @param mailSendId the mail send id
     */
    public void setMailSendId(String mailSendId) {
        this.mailSendId = mailSendId;
    }

    /**
     * Gets mail send target.
     *
     * @return the mail send target
     */
    public String getMailSendTarget() {
        return mailSendTarget;
    }

    /**
     * Sets mail send target.
     *
     * @param mailSendTarget the mail send target
     */
    public void setMailSendTarget(String mailSendTarget) {
        this.mailSendTarget = mailSendTarget;
    }

    /**
     * Gets mail send content.
     *
     * @return the mail send content
     */
    public String getMailSendContent() {
        return mailSendContent;
    }

    /**
     * Sets mail send content.
     *
     * @param mailSendContent the mail send content
     */
    public void setMailSendContent(String mailSendContent) {
        this.mailSendContent = mailSendContent;
    }

    /**
     * Gets mail send date.
     *
     * @return the mail send date
     */
    public Date getMailSendDate() {
        return mailSendDate;
    }

    /**
     * Sets mail send date.
     *
     * @param mailSendDate the mail send date
     */
    public void setMailSendDate(Date mailSendDate) {
        this.mailSendDate = (Date) mailSendDate.clone();
    }
}
