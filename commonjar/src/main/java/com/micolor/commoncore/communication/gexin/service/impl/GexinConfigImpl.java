/*
 * 上海骊蘅网络科技有限公司
 * Web Site: http://www.lihenginfo.com/
 */
package com.micolor.commoncore.communication.gexin.service.impl;


import com.github.pagehelper.PageHelper;
import com.micolor.commoncore.commonmapper.communication.gexin.GexinConfigRepository;
import com.micolor.commoncore.communication.gexin.entity.GexinConfigEntity;
import com.micolor.commoncore.communication.gexin.service.GexinConfigService;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.message.domain.MsgBean;
import com.micolor.commoncore.statics.EnumUtil;
import com.micolor.commoncore.statics.StringStatics;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.business.bo.impl.gexinconfig
 *
 * @Author:Ann
 * @Description:个推配置信息
 * @Date:2017-7-30
 * @Modified:
 */
@Service("gexinConfigService")
public class GexinConfigImpl implements GexinConfigService {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(GexinConfigImpl.class);

	@Autowired
	private GexinConfigRepository gexinConfigRepository;

	@Override
	public List<Map> getAllGexinConfigList(){
		return gexinConfigRepository.getAllGexinConfigList();
	}
	@Override
	public List<GexinConfigEntity> getGexinConfigList(DataTables dataTables){
		String sql = "select * from GEXIN_CONFIG where 1=1";
		int pageNum = dataTables.getcurrPage();
		int pageSize= dataTables.getLength();
		PageHelper.startPage(pageNum, pageSize);
		return gexinConfigRepository.getGexinConfigPageData(sql,dataTables);
	}
	@Override
	public GexinConfigEntity getGexinConfig(String Id){
		return gexinConfigRepository.getGexinConfigInfo(Id);
	}
	@Override
	public Map getGexinConfig4Map(String Id){
		return gexinConfigRepository.getGexinConfigInfo4Map(Id);
	}
	public MsgBean deleteGexinConfig(GexinConfigEntity[] gexinConfigEntities){
		MsgBean msgBean = new MsgBean();
		try{
			for(GexinConfigEntity gexinConfigEntity : gexinConfigEntities){
				gexinConfigRepository.deleteGexinConfig(gexinConfigEntity.getGcid());
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
	public MsgBean saveGexinConfig(GexinConfigEntity gexinConfigEntity){
		MsgBean msgBean = new MsgBean();
		try{
			gexinConfigRepository.saveGexinConfig(gexinConfigEntity);
			msgBean.setContent(gexinConfigEntity);
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
	public MsgBean updateGexinConfig(GexinConfigEntity gexinConfigEntity){
		MsgBean msgBean = new MsgBean();
		try{
			gexinConfigRepository.updateGexinConfig(gexinConfigEntity);
			msgBean.setContent(gexinConfigEntity);
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
