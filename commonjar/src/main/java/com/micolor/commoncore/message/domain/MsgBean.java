/*
 * Copyright (c) 2015. MiColor
 */

/**
 * @Title: MsgBean.java
 * @Package initerp.entity
 * @author 钰鹏
 * @date 2014年4月2日 下午5:16:35
 * @version V1.0
 */
package com.micolor.commoncore.message.domain;
import com.micolor.commoncore.message.method.MsgBeanTrans;
import com.micolor.commoncore.statics.EnumUtil;
import com.micolor.commoncore.statics.StringStatics;

/**
 * The type Msg bean.
 *
 * @author 钰鹏
 * @ClassName: MsgBean
 * @Description: 实现层执行增删改时返回的参数
 * @date 2014年4月2日 下午5:16:35
 */
public class MsgBean {
    private String msg; // 执行消息 （1为成功，其他为不成功以及原因）
    private Object content; // 执行主键编号
    private EnumUtil.MessageStatus status; //执行状态1为成功，其他为不成功
    private boolean success;  //状态

    public MsgBean() {//默认返回成功
        this.success = true;
        this.msg = StringStatics.OPERATIONOK;
        this.status = EnumUtil.MessageStatus.OK;
    }

    public MsgBean(String msg, Object content, EnumUtil.MessageStatus status, boolean success) {
        this.msg = msg;
        this.content = content;
        this.status = status;
        this.success = success;
    }

    public MsgBean(String msg, Object content, EnumUtil.MessageStatus status) {
        this.msg = msg;
        this.content = content;
        this.status = status;
    }

    /**
     * 获得
     *
     * @return msg msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置
     *
     * @param msg 要设置的 msg
     */
    public void setMsg(String msg) {
        this.msg = MsgBeanTrans.transMsg(msg);
    }


    /**
     * Gets status.
     *
     * @return the status
     */
    public EnumUtil.MessageStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(EnumUtil.MessageStatus status) {
        this.status = status;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public Object getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(Object content) {
        this.content = content;
    }


    /**
     * Is success boolean.
     *
     * @return the boolean
     */
    public boolean isSuccess() {
        this.success = true;
        return this.success;
    }

    /**
     * Sets success.
     *
     * @param success the success
     */
    public void setSuccess() {
        this.success = true;
    }


}
