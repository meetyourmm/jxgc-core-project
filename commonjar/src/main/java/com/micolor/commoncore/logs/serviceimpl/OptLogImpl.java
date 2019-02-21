package com.micolor.commoncore.logs.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.micolor.commoncore.commonmapper.logs.OperatorLogMapper;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.logs.domain.OperatorLogEntity;
import com.micolor.commoncore.logs.service.OptLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.logs.serviceimpl
 *
 * @Author: Evangoe
 * @Description: 保存操作日志的实现类
 * @Date:11/05/17
 * @Modified:
 */
@Service("optLogService")
public class OptLogImpl implements OptLogService{

    @Resource
    private OperatorLogMapper operatorLogMapper;
    /**
     * 保存操作日志到数据库
     *
     * @param operatorLogEntity 操作日志实体类
     */
    @Override
    public void saveLog(OperatorLogEntity operatorLogEntity) {
        operatorLogMapper.saveLog(operatorLogEntity);
    }

    /**
     * 获得日志列表
     *
     * @return
     */
    @Override
    public List<OperatorLogEntity> getOperatorLog(DataTables dataTables) {
        String sql = "select * from operator_log where 1=1 ";
        int pageNum = dataTables.getcurrPage();
        int pageSize= dataTables.getLength();
        PageHelper.startPage(pageNum, pageSize);
        return operatorLogMapper.getOperatorLog(sql,dataTables);
    }
}