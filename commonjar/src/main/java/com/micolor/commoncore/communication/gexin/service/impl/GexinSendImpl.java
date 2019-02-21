package com.micolor.commoncore.communication.gexin.service.impl;

import com.gexin.rp.sdk.base.IAliasResult;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.AbstractTemplate;
import com.micolor.commoncore.commonmapper.communication.gexin.GexinConfigRepository;
import com.micolor.commoncore.commonmapper.communication.gexin.GexinSendLogRepository;
import com.micolor.commoncore.communication.gexin.entity.GexinConfigEntity;
import com.micolor.commoncore.communication.gexin.entity.GexinSendLogEntity;
import com.micolor.commoncore.communication.gexin.service.GexinSendSerivce;
import com.micolor.commoncore.communication.gexin.util.AppPush;
import com.micolor.commoncore.message.domain.MsgBean;
import com.micolor.commoncore.statics.EnumUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.micolor.commoncore.communication.gexin.util.AppPush.appId;

/**
 * Created by Ann on 2017/7/31.
 */
@Service("gexinSendSerivce")
public class GexinSendImpl implements GexinSendSerivce {
    private static Logger logger = LoggerFactory.getLogger(AppPush.class);
    @Autowired
    private GexinSendLogRepository gexinSendLogRepository;
    @Autowired
    private GexinConfigRepository gexinConfigRepository;
    @Override

    public MsgBean sendMsgList(String title, String content, String aliasStr,String josnStr) {
        MsgBean msgBean = new MsgBean();
        String status = null;
        try{
            //获取配置文件
            GexinConfigEntity gexinConfigEntity = gexinConfigRepository.getGexinConfigInfo("001");
            AppPush appPush = new AppPush();
            appId = gexinConfigEntity.getAppid();
            appPush.appKey = gexinConfigEntity.getAppkey();
            appPush.masterSecret = gexinConfigEntity.getMastersecret();
            appPush.url = gexinConfigEntity.getAppurl();
            //推送信息
            AbstractTemplate transmissionTemplate = AppPush.transmissionTemplate(title,content,josnStr);
            AppPush.sendMsgList(transmissionTemplate,aliasStr);
            status = "1";
            msgBean.setMsg("推送成功");
        }catch (Exception e){
            status = "2";
            logger.error("推送配置信息失败，原因：{}",e.getMessage());
            msgBean.setMsg("推送信配置信息失败");
        }
        //写入日志
        this.saveLog(title,content,aliasStr,status);
        return msgBean;
    }

    @Override
    public MsgBean pushtoSingle(String title, String content, String alias, String jsonStr, String appSign) {
        MsgBean msgBean = new MsgBean();
        String status = null;
        try{
            String ck;
            //获取配置文件
            if ("2".equals(appSign)) {
                ck = "002";
            } else {
                ck = "001";
            }
            GexinConfigEntity gexinConfigEntity = gexinConfigRepository.getGexinConfigInfo(ck);
            AppPush appPush = new AppPush();
            appId = gexinConfigEntity.getAppid();
            appPush.appKey = gexinConfigEntity.getAppkey();
            appPush.masterSecret = gexinConfigEntity.getMastersecret();
            appPush.url = gexinConfigEntity.getAppurl();
            //推送信息
            AbstractTemplate transmissionTemplate = AppPush.transmissionTemplate(title,content,jsonStr);
            AppPush.pushtoSingle(transmissionTemplate,alias);
            status = "1";
            msgBean.setMsg("推送成功");
        }catch (Exception e){
            status = "2";
            logger.error("推送配置信息失败，原因：{}",e.getMessage());
            msgBean.setMsg("推送信配置信息失败");
        }
        //写入日志
        this.saveLog(title,content,alias,status);
        return msgBean;
    }

    @Override
    public MsgBean pushToApp(String title, String content, String jsonStr) {
        MsgBean msgBean = new MsgBean();
        String status = null;
        try{
            //获取配置文件
            GexinConfigEntity gexinConfigEntity = gexinConfigRepository.getGexinConfigInfo("001");
            AppPush appPush = new AppPush();
            appId = gexinConfigEntity.getAppid();
            appPush.appKey = gexinConfigEntity.getAppkey();
            appPush.masterSecret = gexinConfigEntity.getMastersecret();
            appPush.url = gexinConfigEntity.getAppurl();
            //推送信息
            AbstractTemplate transmissionTemplate = AppPush.transmissionTemplate(title,content,jsonStr);
            AppPush.pushToApp(transmissionTemplate);
            status = "1";
            msgBean.setMsg("推送成功");
        }catch (Exception e){
            status = "2";
            logger.error("推送配置信息失败，原因：{}",e.getMessage());
            msgBean.setMsg("推送信配置信息失败");
        }
        //写入日志
        this.saveLog(title,content,appId,status);
        return msgBean;
    }

    @Override
    public MsgBean getClientIdByAlias(String alias) {
        //获取配置文件
        MsgBean msgBean = new MsgBean();
        try{
            GexinConfigEntity gexinConfigEntity = gexinConfigRepository.getGexinConfigInfo("001");
            AppPush appPush = new AppPush();
            appId = gexinConfigEntity.getAppid();
            appPush.appKey = gexinConfigEntity.getAppkey();
            appPush.masterSecret = gexinConfigEntity.getMastersecret();
            appPush.url = gexinConfigEntity.getAppurl();
            IGtPush push = new IGtPush(appPush.url, appPush.appKey, appPush.masterSecret);
            IAliasResult queryClient = push.queryClientId(appId, alias);
            msgBean.setStatus(EnumUtil.MessageStatus.OK);
            msgBean.setMsg("成功");
            msgBean.setContent(queryClient.getClientIdList());
        }catch (Exception e){
            logger.error("推送配置信息失败，原因：{}",e.getMessage());
            msgBean.setMsg("推送信配置信息失败");
        }
        return msgBean;
    }

    @Override
    public MsgBean getAliasByClientId(String cid) {
        MsgBean msgBean = new MsgBean();
        try {
            GexinConfigEntity gexinConfigEntity = gexinConfigRepository.getGexinConfigInfo("001");
            AppPush appPush = new AppPush();
            appId = gexinConfigEntity.getAppid();
            appPush.appKey = gexinConfigEntity.getAppkey();
            appPush.masterSecret = gexinConfigEntity.getMastersecret();
            appPush.url = gexinConfigEntity.getAppurl();
            IGtPush push = new IGtPush(appPush.url, appPush.appKey, appPush.masterSecret);
            IAliasResult queryAlias = push.queryAlias(appId, cid);
            msgBean.setStatus(EnumUtil.MessageStatus.OK);
            msgBean.setMsg("成功");
            msgBean.setContent(queryAlias.getAlias());
        }catch (Exception e){
            logger.error("推送配置信息失败，原因：{}",e.getMessage());
            msgBean.setMsg("推送信配置信息失败");
        }
        return msgBean;
    }

    @Override
    public MsgBean getUserTagsByClientId(String cid) {
        MsgBean msgBean = new MsgBean();
        try {
            GexinConfigEntity gexinConfigEntity = gexinConfigRepository.getGexinConfigInfo("001");
            AppPush appPush = new AppPush();
            appId = gexinConfigEntity.getAppid();
            appPush.appKey = gexinConfigEntity.getAppkey();
            appPush.masterSecret = gexinConfigEntity.getMastersecret();
            appPush.url = gexinConfigEntity.getAppurl();
            IGtPush push = new IGtPush(appPush.url, appPush.appKey, appPush.masterSecret);
            IPushResult result = push.getUserTags(appId, cid);
            msgBean.setStatus(EnumUtil.MessageStatus.OK);
            msgBean.setMsg("成功");
            msgBean.setContent(result.getResponse().toString());
        }catch (Exception e){
            logger.error("推送配置信息失败，原因：{}",e.getMessage());
            msgBean.setMsg("推送信配置信息失败");
        }
        return msgBean;
    }

    /**
     * 写入日志
     * @param title
     * @param content
     * @param target
     * @param status
     */
    public void saveLog(String title,String content,String target,String status){
        GexinSendLogEntity gexinSendLogEntity = new GexinSendLogEntity();
        gexinSendLogEntity.setGxcontent(content);
        gexinSendLogEntity.setTarget(target);
        gexinSendLogEntity.setGxtitle(title);
        gexinSendLogEntity.setGxstatus("1");
        gexinSendLogEntity.setGxdate(new Date());
        gexinSendLogRepository.saveGexinSendLog(gexinSendLogEntity);
    }

}
