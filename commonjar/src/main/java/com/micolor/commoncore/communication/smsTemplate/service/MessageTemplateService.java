/*
 * 上海骊蘅网络科技有限公司
 * Web Site: http://www.lihenginfo.com/
 */
package com.micolor.commoncore.communication.smsTemplate.service;

import com.micolor.commoncore.communication.smsTemplate.entity.MessageTemplateEntity;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.message.domain.MsgBean;

import java.util.List;
import java.util.Map;

/**
 * Project:logistics-
 * Package:com.liheng.shock.admin.data.dao.messageTemplate
 *
 * @Author:信息模版配置
 * @Description:Ann
 * @Date: 2017-9-29
 * @Modified:
 */
public interface MessageTemplateService{
	/**
	 * 获取模版列表
	 * @return
	 */
	List<Map> getAllMessageTemplateList();

	/**
	 * 获取模版列表 forDataTables
	 * @param dataTables
	 * @return
	 */
	List<MessageTemplateEntity> getMessageTemplateList(DataTables dataTables);

	/**
	 * 根据id获取模版
	 * @param Id
	 * @return
	 */
	MessageTemplateEntity getMessageTemplate(String Id);

	/**
	 * 获取短息模版内容
	 * @param Id
	 * @param param
	 * @return
	 */
	String getMessageTemplateCon(String Id, Map param);

	/**
	 * 删除模版
	 * @param messageTemplateEntities
	 * @return
	 */
	MsgBean deleteMessageTemplate(MessageTemplateEntity[] messageTemplateEntities);

	/**
	 * 新增模版信息
	 * @param messageTemplateEntity
	 * @return
	 */
	MsgBean saveMessageTemplate(MessageTemplateEntity messageTemplateEntity);

	/**
	 * 更新模版信息
	 * @param messageTemplateEntity
	 * @return
	 */
	MsgBean updateMessageTemplate(MessageTemplateEntity messageTemplateEntity);

	/**
	 * 根据参数格式化短信模版
	 * @param con
	 * @param param
	 * @return
	 */
	String replaceMsg(String con, Map param);
}
