/*
 * 上海骊蘅网络科技有限公司
 * Web Site: http://www.lihenginfo.com/
 */
package com.micolor.commoncore.communication.websocket.service;

import com.micolor.commoncore.communication.websocket.entity.MessagesSocketsEntity;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.message.domain.MsgBean;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Project:logistics-
 * Package:com.liheng.shock.admin.data.dao.messagesSockets
 *
 * @Author:Ann
 * @Description:站内消息记录
 * @Date: 2017-9-29
 * @Modified:
 */
@Resource
public interface MessagesSocketsService{
	List<Map> getAllMessagesSocketsList();
	MessagesSocketsEntity getMessagesSockets(String Id);
	Map getMessagesSockets4Map(String Id);
	MsgBean deleteMessagesSockets(MessagesSocketsEntity[] messagesSocketsEntities);
	MsgBean saveMessagesSockets(MessagesSocketsEntity messagesSocketsEntity);
	MsgBean updateMessagesSockets(MessagesSocketsEntity messagesSocketsEntity);
	/**
	 * 获取用户消息
	 * @param userId
	 * @return
	 */
	List<MessagesSocketsEntity> getMessagesSocketsListByUserId(String userId);

	/**
	 * 更改阅读状态
	 */
	MsgBean updateShowStatus(String msId);

	/**
	 * 消息列表
	 * @param dataTables
	 * @return
	 */
	List<MessagesSocketsEntity> getMessagesSocketsList(DataTables dataTables, String userId);
}
