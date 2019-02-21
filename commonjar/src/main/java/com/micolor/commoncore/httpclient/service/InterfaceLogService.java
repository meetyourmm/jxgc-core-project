package com.micolor.commoncore.httpclient.service;

import com.micolor.commoncore.httpclient.entity.InterfaceLogEntity;
import com.micolor.commoncore.message.domain.MsgBean;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.httpclient.service
 *
 * @Author: Evangoe
 * @Description: http请求日志操作接口类
 * @Date:28/6/17
 * @Modified:
 */
public interface InterfaceLogService {
    /**
     * 保存http请求日志
     * @param interfaceLogEntity http请求日志实体类
     * @return
     */
    MsgBean saveInterfaceLog(InterfaceLogEntity interfaceLogEntity);
}
