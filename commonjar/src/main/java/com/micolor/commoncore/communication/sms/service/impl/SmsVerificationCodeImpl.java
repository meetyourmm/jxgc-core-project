package com.micolor.commoncore.communication.sms.service.impl;

import com.micolor.commoncore.commonmapper.communication.sms.SmsVerificationCodeRepository;
import com.micolor.commoncore.communication.sms.entity.SmsVerificationCodeEntity;
import com.micolor.commoncore.communication.sms.service.SmsVerificationCodeService;
import com.micolor.commoncore.message.domain.MsgBean;
import com.micolor.commoncore.statics.EnumUtil;
import com.micolor.commoncore.statics.StringStatics;
import com.micolor.commoncore.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.communication.sms.service.impl
 *
 * @Author: Evangoe
 * @Description:
 * @Date:15/06/17
 * @Modified:
 */
@Service("smsVerificationCodeService")
public class SmsVerificationCodeImpl implements SmsVerificationCodeService {

    private static Logger logger = LoggerFactory.getLogger(SmsVerificationCodeImpl.class);

    @Autowired
    SmsVerificationCodeRepository smsVerificationCodeRepository;

    /**
     * 保存短信验证码
     *
     * @param smsVerificationCodeEntity
     * @return
     */
    @Override
    public MsgBean saveSmsVerificationCode(SmsVerificationCodeEntity smsVerificationCodeEntity) {
        MsgBean msgBean = new MsgBean();
        try {
            smsVerificationCodeRepository.saveSmsVerificationCode(smsVerificationCodeEntity);
            msgBean.setContent(smsVerificationCodeEntity);
            msgBean.setMsg(StringStatics.OPERATIONOK);
            msgBean.setStatus(EnumUtil.MessageStatus.OK);
        } catch (Exception e) {
            logger.error("发送短信配置信息失败，原因：{}", e.getMessage());
            msgBean.setMsg("发送短信配置信息失败");
            msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
        }
        return msgBean;
    }

    @Override
    public MsgBean checkVerificationCode(String mobile, String yzcode) {
        MsgBean msgBean = new MsgBean();
        SmsVerificationCodeEntity smsVerificationCodeEntity = smsVerificationCodeRepository.checkVerificationCode(mobile, yzcode);
        if (StringUtil.isNotEmpty(smsVerificationCodeEntity)) {
            //15分钟以内有效
            if ((smsVerificationCodeEntity.getSendTime().getTime() + 900000) < new Date().getTime()) {
                msgBean.setMsg("验证码已过期！");
                msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
            } else {
                msgBean.setMsg("验证码正确！");
                msgBean.setStatus(EnumUtil.MessageStatus.OK);
            }
        } else {
            msgBean.setMsg("验证码不正确！");
            msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
        }
        return msgBean;
    }

    /**
     * 判断是否可以请求发送验证码
     *
     * @param mobile
     * @return
     */
    public MsgBean canSend(String mobile, String ip) {
        /**
         * 正则表达式：验证手机号
         */
        String reg = "^1\\d{10}$";
        MsgBean msgBean = new MsgBean();
        Integer times = 5;
        SmsVerificationCodeEntity smsVerificationCodeEntity = smsVerificationCodeRepository.getNewByIp(ip);
        if (!Pattern.matches(reg, mobile)) {
            msgBean.setMsg("请输入正确手机号！");
            msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
        } else if (smsVerificationCodeRepository.countNumByIp(ip) >= times) {
            msgBean.setMsg("请求次数超限！");
            msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
        } else if (smsVerificationCodeEntity != null && (smsVerificationCodeEntity.getSendTime().getTime() + 60000 > new Date().getTime())) { //同一个手机号请求时间间隔60s
            msgBean.setMsg("请求过于频繁，请稍后再试！");
            msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
        } else if (smsVerificationCodeRepository.countNumByMobile(mobile) >= times) {//同一个手机号最多一天只能请求5次
            msgBean.setMsg("该手机号请求次数超限！");
            msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
        } else {
            msgBean.setMsg("OK");
            msgBean.setStatus(EnumUtil.MessageStatus.OK);
        }
        //ip限制
        return msgBean;
    }
}
