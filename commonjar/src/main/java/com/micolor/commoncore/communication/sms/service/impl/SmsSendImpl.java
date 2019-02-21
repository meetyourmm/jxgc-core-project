package com.micolor.commoncore.communication.sms.service.impl;

import com.github.pagehelper.PageHelper;
import com.micolor.commoncore.commonmapper.communication.sms.SmsConfigRepository;
import com.micolor.commoncore.commonmapper.communication.sms.SmsSendRepository;
import com.micolor.commoncore.communication.sms.entity.SmsConfigEntity;
import com.micolor.commoncore.communication.sms.entity.SmsSendEntity;
import com.micolor.commoncore.communication.sms.service.SmsSendService;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.message.domain.MsgBean;
import com.micolor.commoncore.statics.EnumUtil;
import com.micolor.commoncore.statics.SmsTypeEnum;
import com.micolor.commoncore.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.communication.sms.service.impl
 *
 * @Author: Evangoe
 * @Description:
 * @Date:09/06/17
 * @Modified:
 */
@Service("smsSendService")
public class SmsSendImpl implements SmsSendService{
    private static Logger logger = LoggerFactory.getLogger(SmsSendImpl.class);

    @Autowired
    private SmsSendRepository smsSendRepository;
    @Autowired
    SmsConfigRepository smsConfigRepository;

    /**
     * 发送验证码
     * @param target
     * @param content
     * @return
     * @throws Exception
     */
    public MsgBean sendVC(String target,String content,String company,String type){
        SmsSendEntity smsSendEntity = new SmsSendEntity();
        smsSendEntity.setClientId(target);
        smsSendEntity.setSsContent(content);
        smsSendEntity.setSsDate(new Date());
        return commonSender(smsSendEntity,company, type);
    }
    /**
     * 发送短消息
     * @param targets
     * @param content
     * @return
     * @throws Exception
     */
    public MsgBean sendMsg(StringBuilder targets ,String content,String company,String type){
        SmsSendEntity smsSendEntity = new SmsSendEntity();
        smsSendEntity.setClientId(targets.toString());
        smsSendEntity.setSsContent(content);
        smsSendEntity.setSsDate(new Date());
        return commonSender(smsSendEntity,company,type);
    }
    private MsgBean commonSender(SmsSendEntity smsSendEntity,String smsCompany,String smsType){
        MsgBean msgBean = new MsgBean();
        String content;
        String company = StringUtil.isEmpty(smsCompany)? SmsTypeEnum.SmsCompany.JZKJ.toString():smsCompany;
        String type = StringUtil.isEmpty(smsType)? SmsTypeEnum.SmsType.MESSAGE.toString():smsType;
        SmsConfigEntity smsConfigEntity = smsConfigRepository.getSmsConfig(company,type);
        try{
            if(smsConfigEntity!= null){
                URL postUrl = new URL(smsConfigEntity.getSmsUrl());
                HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod("POST");
                connection.setUseCaches(false);
                connection.setInstanceFollowRedirects(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.connect();
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                //下面为http发送短信模式--------
                content = "account=" + smsConfigEntity.getSmsAccount() + "&" + "password=" + smsConfigEntity.getSmsPassword() + "&" + "sendDateTime=" + "" + "&" + "destmobile=" + smsSendEntity.getClientId() + "&"
                        + "msgText=" + URLEncoder.encode(smsConfigEntity.getSmsSuffix()+smsSendEntity.getSsContent(), "UTF-8");
                out.writeBytes(content);
                out.flush();
                out.close(); // flush and close
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder result = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    result.append(line+",");
                }
                reader.close();
                connection.disconnect();
                smsSendEntity.setSsStatus(result.toString());
                smsSendRepository.saveSmsSendLog(smsSendEntity);
                msgBean.setMsg("发送消息成功");
                msgBean.setStatus(EnumUtil.MessageStatus.OK);
            }else{
                msgBean.setMsg("获取短信配置信息失败");
                msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
            }
        }catch (Exception e){
            logger.error("发送短信配置信息失败，原因：{}",e.getMessage());
            msgBean.setMsg("发送短信配置信息失败");
            msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
        }
        return msgBean;
    }


    /**
     * 获得短信发送激励
     *
     * @param dataTables
     * @return
     */
    @Override
    public List<Map> getSmsSendLog(DataTables dataTables) {
        String sql = "select * from sms_send_log where 1=1 ";
        int pageNum = dataTables.getcurrPage();
        int pageSize= dataTables.getLength();
        PageHelper.startPage(pageNum, pageSize);
        return smsSendRepository.getMailSendLog(sql,dataTables);
    }
}