package com.micolor.commoncore.communication.websocket.service.impl;

import com.micolor.commoncore.commonmapper.communication.websocket.MessagesSocketsRepository;
import com.micolor.commoncore.communication.websocket.entity.MessagesSocketsEntity;
import com.micolor.commoncore.communication.websocket.service.SendMQMessageService;
import com.micolor.commoncore.message.domain.MsgBean;
import com.micolor.commoncore.statics.EnumUtil;
import net.sf.json.JSONObject;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import java.util.Date;

/**
 * Project:micolor-admin
 * Package:com.micolor.commoncore.communication.websocket.service.impl
 *
 * @Author: Evangoe
 * @Description:
 * @Date:07/09/17
 * @Modified:
 */
@Service("sendMQMessageService")
public class SendMQMessageImpl implements SendMQMessageService{
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private MessagesSocketsRepository messagesSocketsRepository;
    @Override
    public MsgBean sendMsg2User(String userId,String mqMsgJson) {
        MsgBean msgBean = new MsgBean();
        try{
            //记录发送消息
            MessagesSocketsEntity messagesSocketsEntity = new MessagesSocketsEntity();
            messagesSocketsEntity.setUserid(userId);
            messagesSocketsEntity.setMsjson(mqMsgJson);
            messagesSocketsEntity.setDelflag("1");
            messagesSocketsEntity.setCreatetime(new Date());
            JSONObject jo =  JSONObject.fromObject(mqMsgJson);
            String title = jo.getString("title");//标题
            String content = jo.getString("content");//内容
            String isShow = jo.getString("isshow");//是否显示
            messagesSocketsEntity.setMstitle(title);
            messagesSocketsEntity.setMscontent(content);
            messagesSocketsEntity.setIsshow(isShow);
            messagesSocketsRepository.saveMessagesSockets(messagesSocketsEntity);
            //发送消息
            Destination destination = new ActiveMQTopic(userId);
            jmsMessagingTemplate.convertAndSend(destination, mqMsgJson);
            msgBean.setStatus(EnumUtil.MessageStatus.OK);
            msgBean.setContent(mqMsgJson);
            msgBean.setMsg("发送成功！");
            msgBean.setSuccess();
        }catch (Exception e){
            msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
            msgBean.setContent(mqMsgJson);
            msgBean.setMsg("发送失败！");
        }
        return msgBean;
    }
}
