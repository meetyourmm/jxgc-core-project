package com.micolor.commoncore.logs.service;

import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.logs.domain.OperatorLogEntity;

import java.util.List;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.logs.service
 *
 * @Author: Evangoe
 * @Description: 保存操作日志的接口
 * @Date:11/05/17
 * @Modified:
 */
public interface OptLogService {
    /**
     * 保存操作日志到数据库
     * @param operatorLogEntity 操作日志实体类
     */
    void saveLog(OperatorLogEntity operatorLogEntity);

    /**
     * 获得日志列表
     * @return
     */
    List<OperatorLogEntity> getOperatorLog(DataTables dataTables);
}
