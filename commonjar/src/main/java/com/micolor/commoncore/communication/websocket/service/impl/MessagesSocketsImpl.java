/*
 * 上海骊蘅网络科技有限公司
 * Web Site: http://www.lihenginfo.com/
 */package com.micolor.commoncore.communication.websocket.service.impl;


import com.github.pagehelper.PageHelper;
import com.micolor.commoncore.commonmapper.communication.websocket.MessagesSocketsRepository;
import com.micolor.commoncore.communication.websocket.entity.MessagesSocketsEntity;
import com.micolor.commoncore.communication.websocket.service.MessagesSocketsService;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.message.domain.MsgBean;
import com.micolor.commoncore.statics.EnumUtil;
import com.micolor.commoncore.statics.StringStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * Project:logistics-single
 * Package:com.liheng.shock.business.bo.impl.messagessockets
 *
 * @Author:
 * @Description:
 * @Date:2017-9-29
 * @Modified:
 */
@Service("messagesSocketsService")
public class MessagesSocketsImpl implements MessagesSocketsService {

	private static Logger logger = LoggerFactory.getLogger(MessagesSocketsImpl.class);

	@Autowired
	private MessagesSocketsRepository messagesSocketsRepository;

	@Override
	public List<Map> getAllMessagesSocketsList(){
		return messagesSocketsRepository.getAllMessagesSocketsList();
	}
	@Override
	public List<MessagesSocketsEntity> getMessagesSocketsList(DataTables dataTables,String userId){
		String sql = "select * from MESSAGES_SOCKETS where delflag = '1' and userid = '"+userId+"'";
		int pageNum = dataTables.getcurrPage();
		int pageSize= dataTables.getLength();
		PageHelper.startPage(pageNum, pageSize);
		return messagesSocketsRepository.getMessagesSocketsPageData(sql,dataTables);
	}
	@Override
	public MessagesSocketsEntity getMessagesSockets(String Id){
		return messagesSocketsRepository.getMessagesSocketsInfo(Id);
	}
	@Override
	public Map getMessagesSockets4Map(String Id){
		return messagesSocketsRepository.getMessagesSocketsInfo4Map(Id);
	}
	public MsgBean deleteMessagesSockets(MessagesSocketsEntity[] messagesSocketsEntities){
		MsgBean msgBean = new MsgBean();
		try{
			for(MessagesSocketsEntity messagesSocketsEntity : messagesSocketsEntities){
				messagesSocketsRepository.deleteMessagesSockets(messagesSocketsEntity.getMsid());
			}
			msgBean.setMsg(StringStatics.OPERATIONOK);
			msgBean.setStatus(EnumUtil.MessageStatus.OK);
		}catch (Exception e){
			msgBean.setMsg(StringStatics.OPERATIONERROR+",原因："+e.getMessage());
			msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
			logger.error(StringStatics.INNERERROR,e);
		}
		return msgBean;
	}
	@Override
	public MsgBean saveMessagesSockets(MessagesSocketsEntity messagesSocketsEntity){
		MsgBean msgBean = new MsgBean();
		try{
			messagesSocketsRepository.saveMessagesSockets(messagesSocketsEntity);
			msgBean.setContent(messagesSocketsEntity);
			msgBean.setMsg("操作成功！");
			msgBean.setStatus(EnumUtil.MessageStatus.OK);
		}catch (Exception e){
			msgBean.setMsg(StringStatics.OPERATIONERROR+",原因："+e.getMessage());
			msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
			logger.error("内部异常：{}",e.getMessage());
		}
		return msgBean;
	}
	@Override
	public MsgBean updateMessagesSockets(MessagesSocketsEntity messagesSocketsEntity){
		MsgBean msgBean = new MsgBean();
		try{
			messagesSocketsRepository.updateMessagesSockets(messagesSocketsEntity);
			msgBean.setContent(messagesSocketsEntity);
			msgBean.setMsg("操作成功！");
			msgBean.setStatus(EnumUtil.MessageStatus.OK);
		}catch (Exception e){
			msgBean.setMsg(StringStatics.OPERATIONERROR+",原因："+e.getMessage());
			msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
			logger.error("内部异常：{}",e.getMessage());
		}
		return msgBean;
	}

	@Override
	public List<MessagesSocketsEntity> getMessagesSocketsListByUserId(String userId) {
		return messagesSocketsRepository.getMessagesSocketsListByUserId(userId);
	}

	@Override
	public MsgBean updateShowStatus(String msId) {
		MsgBean msgBean = new MsgBean();
		try{
			messagesSocketsRepository.updateShowStatus(msId);
			msgBean.setMsg("操作成功！");
			msgBean.setStatus(EnumUtil.MessageStatus.OK);
		}catch (Exception e){
			msgBean.setMsg(StringStatics.OPERATIONERROR+",原因："+e.getMessage());
			msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
			logger.error("内部异常：{}",e.getMessage());
		}
		return msgBean;
	}
}
