/*
 * 上海骊蘅网络科技有限公司
 * Web Site: http://www.lihenginfo.com/
 */
package com.micolor.commoncore.communication.gexin.service.impl;


import com.github.pagehelper.PageHelper;
import com.micolor.commoncore.commonmapper.communication.gexin.GexinSendLogRepository;
import com.micolor.commoncore.communication.gexin.entity.GexinSendLogEntity;
import com.micolor.commoncore.communication.gexin.service.GexinSendLogService;
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
 * Package:com.liheng.shock.business.bo.impl.gexinsendlog
 *
 * @Author:Ann
 * @Description:个推推送记录
 * @Date:2017-7-30
 * @Modified:
 */
@Service("gexinSendLogService")
public class GexinSendLogImpl implements GexinSendLogService {

	private static Logger logger = LoggerFactory.getLogger(GexinSendLogImpl.class);

	@Autowired
	private GexinSendLogRepository gexinSendLogRepository;

	@Override
	public List<Map> getAllGexinSendLogList(){
		return gexinSendLogRepository.getAllGexinSendLogList();
	}
	@Override
	public List<GexinSendLogEntity> getGexinSendLogList(DataTables dataTables){
		String sql = "select * from GEXIN_SEND_LOG where 1=1";
		int pageNum = dataTables.getcurrPage();
		int pageSize= dataTables.getLength();
		PageHelper.startPage(pageNum, pageSize);
		return gexinSendLogRepository.getGexinSendLogPageData(sql,dataTables);
	}
	@Override
	public GexinSendLogEntity getGexinSendLog(String Id){
		return gexinSendLogRepository.getGexinSendLogInfo(Id);
	}
	@Override
	public Map getGexinSendLog4Map(String Id){
		return gexinSendLogRepository.getGexinSendLogInfo4Map(Id);
	}
	public MsgBean deleteGexinSendLog(GexinSendLogEntity[] gexinSendLogEntities){
		MsgBean msgBean = new MsgBean();
		try{
			for(GexinSendLogEntity gexinSendLogEntity : gexinSendLogEntities){
				gexinSendLogRepository.deleteGexinSendLog(gexinSendLogEntity.getGslid());
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
	public MsgBean saveGexinSendLog(GexinSendLogEntity gexinSendLogEntity){
		MsgBean msgBean = new MsgBean();
		try{
			gexinSendLogRepository.saveGexinSendLog(gexinSendLogEntity);
			msgBean.setContent(gexinSendLogEntity);
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
	public MsgBean updateGexinSendLog(GexinSendLogEntity gexinSendLogEntity){
		MsgBean msgBean = new MsgBean();
		try{
			gexinSendLogRepository.updateGexinSendLog(gexinSendLogEntity);
			msgBean.setContent(gexinSendLogEntity);
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
