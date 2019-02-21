/*
 * 上海骊蘅网络科技有限公司
 * Web Site: http://www.lihenginfo.com/
 */
package com.micolor.commoncore.communication.gexin.service;

import com.micolor.commoncore.communication.gexin.entity.GexinConfigEntity;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.message.domain.MsgBean;

import java.util.List;
import java.util.Map;

/**
 * Project:logistics-
 * Package:com.liheng.shock.admin.data.dao.gexinConfig
 *
 * @Author:Ann
 * @Description:个推信息配置
 * @Date: 2017 -7-30
 * @Modified:
 */
public interface GexinConfigService{
	/**
	 * Gets all gexin config list.
	 *
	 * @return the all gexin config list
	 */
	List<Map> getAllGexinConfigList();

	/**
	 * Gets gexin config list.
	 *
	 * @param dataTables the data tables
	 * @return the gexin config list
	 */
	List<GexinConfigEntity> getGexinConfigList(DataTables dataTables);

	/**
	 * Gets gexin config.
	 *
	 * @param Id the id
	 * @return the gexin config
	 */
	GexinConfigEntity getGexinConfig(String Id);

	/**
	 * Gets gexin config 4 map.
	 *
	 * @param Id the id
	 * @return the gexin config 4 map
	 */
	Map getGexinConfig4Map(String Id);

	/**
	 * Delete gexin config msg bean.
	 *
	 * @param gexinConfigEntities the gexin config entities
	 * @return the msg bean
	 */
	MsgBean deleteGexinConfig(GexinConfigEntity[] gexinConfigEntities);

	/**
	 * Save gexin config msg bean.
	 *
	 * @param gexinConfigEntity the gexin config entity
	 * @return the msg bean
	 */
	MsgBean saveGexinConfig(GexinConfigEntity gexinConfigEntity);

	/**
	 * Update gexin config msg bean.
	 *
	 * @param gexinConfigEntity the gexin config entity
	 * @return the msg bean
	 */
	MsgBean updateGexinConfig(GexinConfigEntity gexinConfigEntity);
}
