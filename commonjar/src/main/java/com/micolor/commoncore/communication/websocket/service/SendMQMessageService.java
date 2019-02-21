package com.micolor.commoncore.communication.websocket.service;

import com.micolor.commoncore.communication.websocket.entity.MessagesSocketsEntity;
import com.micolor.commoncore.message.domain.MsgBean;

import java.util.List;
import java.util.Map;

/**
 * Project:micolor-admin
 * Package:com.micolor.commoncore.communication.websocket.service
 *
 * @Author: Evangoe
 * @Description: 使用mq
 * @Date:07/09/17
 * @Modified:
 */
public interface SendMQMessageService {
    /**
     * 发送信息给特定用户
     * @param userId 用户编号
     * @param mqMsgJson 消息内容json
     * @return
     */
    MsgBean sendMsg2User(String userId, String mqMsgJson);
}
