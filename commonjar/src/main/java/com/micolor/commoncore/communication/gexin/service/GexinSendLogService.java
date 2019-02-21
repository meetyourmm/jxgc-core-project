/*
 * 上海骊蘅网络科技有限公司
 * Web Site: http://www.lihenginfo.com/
 */
package com.micolor.commoncore.communication.gexin.service;
import com.micolor.commoncore.communication.gexin.entity.GexinSendLogEntity;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.message.domain.MsgBean;

import java.util.List;
import java.util.Map;

/**
 * Project:logistics-
 * Package:com.liheng.shock.admin.data.dao.gexinSendLog
 *
 * @Author:Ann
 * @Description:个推信息发送记录
 * @Date: 2017 -7-30
 * @Modified:
 */
public interface GexinSendLogService{
	/**
	 * Gets all gexin send log list.
	 *
	 * @return the all gexin send log list
	 */
	List<Map> getAllGexinSendLogList();

	/**
	 * Gets gexin send log list.
	 *
	 * @param dataTables the data tables
	 * @return the gexin send log list
	 */
	List<GexinSendLogEntity> getGexinSendLogList(DataTables dataTables);

	/**
	 * Gets gexin send log.
	 *
	 * @param Id the id
	 * @return the gexin send log
	 */
	GexinSendLogEntity getGexinSendLog(String Id);

	/**
	 * Gets gexin send log 4 map.
	 *
	 * @param Id the id
	 * @return the gexin send log 4 map
	 */
	Map getGexinSendLog4Map(String Id);

	/**
	 * Delete gexin send log msg bean.
	 *
	 * @param gexinSendLogEntities the gexin send log entities
	 * @return the msg bean
	 */
	MsgBean deleteGexinSendLog(GexinSendLogEntity[] gexinSendLogEntities);

	/**
	 * Save gexin send log msg bean.
	 *
	 * @param gexinSendLogEntity the gexin send log entity
	 * @return the msg bean
	 */
	MsgBean saveGexinSendLog(GexinSendLogEntity gexinSendLogEntity);

	/**
	 * Update gexin send log msg bean.
	 *
	 * @param gexinSendLogEntity the gexin send log entity
	 * @return the msg bean
	 */
	MsgBean updateGexinSendLog(GexinSendLogEntity gexinSendLogEntity);
}
