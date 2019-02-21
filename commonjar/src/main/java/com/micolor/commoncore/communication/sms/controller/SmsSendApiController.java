package com.micolor.commoncore.communication.sms.controller;

import com.micolor.commoncore.communication.sms.entity.SmsVerificationCodeEntity;
import com.micolor.commoncore.communication.sms.service.SmsSendService;
import com.micolor.commoncore.communication.sms.service.SmsVerificationCodeService;
import com.micolor.commoncore.message.domain.ApiMsgBean;
import com.micolor.commoncore.message.domain.MsgBean;
import com.micolor.commoncore.statics.EnumUtil;
import com.micolor.commoncore.statics.SmsTypeEnum;
import com.micolor.commoncore.statics.StringStatics;
import com.micolor.commoncore.string.StringUtil;
import com.micolor.commoncore.utils.Tools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.communication.sms.controller
 *
 * @Author: Evangoe
 * @Description:
 * @Date:14/06/17
 * @Modified:
 */
@Controller
@EnableAutoConfiguration
//@Api(value="短信发送接口",tags = "短信发送接口")
public class SmsSendApiController {

    @Autowired
    private SmsSendService smsSendService;

    @Autowired
    private SmsVerificationCodeService smsVerificationCodeService;

    private static Logger logger = LoggerFactory.getLogger(SmsSendApiController.class);

    /**
     * 发送短信验证码
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "发送短信验证码", tags = "发送短信验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token值", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "target", value = "接受验证码用户的手机号码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "company", value = "运营商,如果为空默认使用JZKJ", dataType = "String", example = "JZKJ或者ALDY"),
            @ApiImplicitParam(paramType = "query", name = "type", value = "短信类型，1验证码2通知，如果为空默认使用1", dataType = "String", example = "1或2")
    })
    @RequestMapping(value = "/interface/communication/sms/sendVerificationCode", method = {RequestMethod.POST})
    @ResponseBody
    public ApiMsgBean sendVerificationCode(HttpServletRequest request) {
        ApiMsgBean apiMsgBean = new ApiMsgBean();
        try {
            String target = request.getParameter("target"); //接受验证码用户的手机号码
            String company = StringUtil.isEmpty(request.getParameter("company")) ? SmsTypeEnum.SmsCompany.JZKJ.toString() : request.getParameter("company").toString();
            String type = StringUtil.isEmpty(request.getParameter("type")) ? SmsTypeEnum.SmsType.VERIFICATIONCODE.toString() : request.getParameter("type").toString();

            String smsVerificationCode = StringUtil.getRandNum(6);
            SmsVerificationCodeEntity smsVerificationCodeEntity = new SmsVerificationCodeEntity();
            smsVerificationCodeEntity.setMobile(target);
            smsVerificationCodeEntity.setSendTime(new Date());
            smsVerificationCodeEntity.setYzCode(smsVerificationCode);

            smsVerificationCodeService.saveSmsVerificationCode(smsVerificationCodeEntity);
            String content = "您好！您的短信验证码是：" + smsVerificationCode + "，请在15分钟内使用。";
            if (StringUtil.isNotEmpty(target) && StringUtil.isNotEmpty(content)) {
                MsgBean msgBean = smsSendService.sendVC(target, content, company, type);
                apiMsgBean = new ApiMsgBean(msgBean);
                apiMsgBean.setDatas(smsVerificationCodeEntity.getCsId());
            } else {
                apiMsgBean.setMsg(StringStatics.OPERATIONERROR);
                apiMsgBean.setFlag(EnumUtil.ApiMessageStatus.Error);
            }
        } catch (Exception e) {
            logger.error("内部异常:{}", e.getMessage());
            apiMsgBean.setMsg(StringStatics.OPERATIONERROR);
            apiMsgBean.setFlag(EnumUtil.ApiMessageStatus.Error);
        }
        return apiMsgBean;
    }

    /**
     * 发送短信通知
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "发送短信通知", tags = "发送短信通知")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token值", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "target", value = "接受验证码用户的手机号码，多条使用；隔开", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "company", value = "运营商,如果为空默认使用JZKJ", dataType = "String", example = "JZKJ或者ALDY"),
            @ApiImplicitParam(paramType = "query", name = "type", value = "短信类型，1验证码2通知，如果为空默认使用2", dataType = "String", example = "1或2"),
            @ApiImplicitParam(paramType = "query", name = "content", value = "短信内容", dataType = "String", example = "短信内容，不超过60字"),
    })
    @RequestMapping(value = "/interface/communication/sms/sendMessage", method = {RequestMethod.POST})
    @ResponseBody
    public ApiMsgBean sendMessage(HttpServletRequest request) {
        ApiMsgBean apiMsgBean = new ApiMsgBean();
        try {
            String target = request.getParameter("target"); //接受验证码用户的手机号码，多条使用；隔开
            String company = StringUtil.isEmpty(request.getParameter("company")) ? SmsTypeEnum.SmsCompany.JZKJ.toString() : request.getParameter("company").toString();
            String type = StringUtil.isEmpty(request.getParameter("type")) ? SmsTypeEnum.SmsType.MESSAGE.toString() : request.getParameter("type").toString();
            String content = request.getParameter("content"); //内容通知内容
            if (StringUtil.isNotEmpty(target) && StringUtil.isNotEmpty(content)) {
                StringBuilder targets = new StringBuilder(target);
                MsgBean msgBean = smsSendService.sendMsg(targets, content, company, type);
                apiMsgBean = new ApiMsgBean(msgBean);
            } else {
                apiMsgBean.setMsg(StringStatics.OPERATIONERROR);
                apiMsgBean.setFlag(EnumUtil.ApiMessageStatus.Error);
            }
        } catch (Exception e) {
            logger.error("内部异常:{}", e.getMessage());
            apiMsgBean.setMsg(StringStatics.OPERATIONERROR);
            apiMsgBean.setFlag(EnumUtil.ApiMessageStatus.Error);
        }
        return apiMsgBean;
    }

    /**
     * 发送短信验证码
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "发送短信验证码", tags = "发送短信验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token值", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "target", value = "接受验证码用户的手机号码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "company", value = "运营商,如果为空默认使用JZKJ", dataType = "String", example = "JZKJ或者ALDY"),
            @ApiImplicitParam(paramType = "query", name = "type", value = "短信类型，1验证码2通知，如果为空默认使用1", dataType = "String", example = "1或2")
    })
    @RequestMapping(value = "/public/communication/sms/sendCode", method = {RequestMethod.POST})
    @ResponseBody
    public MsgBean sendCode(HttpServletRequest request) {
        MsgBean msgBean = new MsgBean();
        try {
            String target = request.getParameter("target"); //接受验证码用户的手机号码
            String company = StringUtil.isEmpty(request.getParameter("company")) ? SmsTypeEnum.SmsCompany.JZKJ.toString() : request.getParameter("company").toString();
            String type = StringUtil.isEmpty(request.getParameter("type")) ? SmsTypeEnum.SmsType.VERIFICATIONCODE.toString() : request.getParameter("type").toString();
            String smsVerificationCode = StringUtil.getRandNum(6);
            String content = "您好！您的短信验证码是：" + smsVerificationCode + "，请在15分钟内使用。";
            String ip = Tools.getIp(request);
            //判断是否可以发送
            msgBean = smsVerificationCodeService.canSend(target,ip);
            if (EnumUtil.MessageStatus.ERROR.equals(msgBean.getStatus())) {
                return msgBean;
            }
            if (StringUtil.isNotEmpty(target) && StringUtil.isNotEmpty(content)) {
                //添加记录
                SmsVerificationCodeEntity smsVerificationCodeEntity = new SmsVerificationCodeEntity();
                smsVerificationCodeEntity.setMobile(target);
                smsVerificationCodeEntity.setSendTime(new Date());
                smsVerificationCodeEntity.setYzCode(smsVerificationCode);
                smsVerificationCodeEntity.setIp(ip);
                smsVerificationCodeService.saveSmsVerificationCode(smsVerificationCodeEntity);
                msgBean = smsSendService.sendVC(target, content, company, type);
            } else {
                msgBean.setMsg(StringStatics.OPERATIONERROR);
                msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
            }
        } catch (Exception e) {
            logger.error("内部异常:{}", e.getMessage());
            msgBean.setMsg(StringStatics.OPERATIONERROR);
            msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
        }
        return msgBean;
    }
}
