package com.micolor.commoncore.communication.sms.service;

import com.micolor.commoncore.communication.sms.entity.SmsVerificationCodeEntity;
import com.micolor.commoncore.message.domain.MsgBean;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.communication.sms.service
 *
 * @Author: Evangoe
 * @Description:
 * @Date:15/06/17
 * @Modified:
 */
public interface SmsVerificationCodeService {
    /**
     * 保存短信验证码
     * @param smsVerificationCodeEntity
     * @return
     */
    MsgBean saveSmsVerificationCode(SmsVerificationCodeEntity smsVerificationCodeEntity);

    /**
     * 验证
     * @param mobile
     * @param yzcode
     * @return
     */
    MsgBean checkVerificationCode(String mobile,String yzcode);

    /**
     * 判断是否可以请求发送验证码
     * @param mobile
     * @return
     */
    MsgBean canSend(String mobile,String ip);
}
