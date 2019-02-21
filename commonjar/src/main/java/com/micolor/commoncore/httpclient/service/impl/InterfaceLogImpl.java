package com.micolor.commoncore.httpclient.service.impl;

import com.micolor.commoncore.commonmapper.httpclient.InterfaceLogRepository;
import com.micolor.commoncore.httpclient.entity.InterfaceLogEntity;
import com.micolor.commoncore.httpclient.service.InterfaceLogService;
import com.micolor.commoncore.message.domain.MsgBean;
import com.micolor.commoncore.statics.EnumUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.httpclient.service.impl
 *
 * @Author: Evangoe
 * @Description:
 * @Date:28/6/17
 * @Modified:
 */
@Service("interfaceLogService")
public class InterfaceLogImpl implements InterfaceLogService{
    private static Logger logger = LoggerFactory.getLogger(InterfaceLogImpl.class);
    /**
     * The Interface log repository.
     */
    @Autowired
    InterfaceLogRepository interfaceLogRepository;

    /**
     * 保存http请求日志
     * @param interfaceLogEntity http请求日志实体类
     * @return
     */
    @Override
    public MsgBean saveInterfaceLog(InterfaceLogEntity interfaceLogEntity) {
        MsgBean msgBean = new MsgBean();
        try{
            interfaceLogRepository.saveInterfaceLog(interfaceLogEntity);
            msgBean.setContent(interfaceLogEntity);
            msgBean.setMsg("操作成功！");
            msgBean.setStatus(EnumUtil.MessageStatus.OK);
        }catch (Exception e){
            msgBean.setMsg("操作失败！原因："+e.getMessage());
            msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
            logger.error("内部异常：",e);
        }
        return msgBean;
    }
}
