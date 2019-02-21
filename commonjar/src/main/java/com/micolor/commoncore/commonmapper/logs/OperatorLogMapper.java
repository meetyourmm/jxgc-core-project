package com.micolor.commoncore.commonmapper.logs;

import com.micolor.commoncore.database.provider.SqlProvider;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.logs.domain.OperatorLogEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.logs.mapper
 *
 * @Author: Evangoe
 * @Description: 保存操作日志到数据的Mapper类
 * @Date:11/05/17
 * @Modified:
 */
@Mapper
public interface OperatorLogMapper {
    /**
     * 保存用户操作日志数据到数据库
     * @param operatorLogEntity 用户操作日志实体类
     */
    @Insert("INSERT INTO operator_log " +
            " (beanname, curuser, methodname, params, remoteaddr, sessionid, uri, requesttime, result,optime) " +
            " VALUES (#{beanName}, #{curUser}, #{methodName}, #{params}, #{remoteAddr}," +
            " #{sessionId}, #{uri}, #{requestTime}, #{result},#{opTime})")
    void saveLog(OperatorLogEntity operatorLogEntity);

    /**
     * 获得日志列表
     * @return
     */
    @SelectProvider(type=SqlProvider.class,method = "selectListForDataTable")
    @Results({
            @Result(property = "params",column = "PARAMS",jdbcType = JdbcType.CLOB,javaType = String.class),
            @Result(property = "remoteAddr",column = "REMOTEADDR",jdbcType = JdbcType.CLOB,javaType = String.class),
            @Result(property = "result",column = "RESULT",jdbcType = JdbcType.CLOB,javaType = String.class)
    })
    List<OperatorLogEntity> getOperatorLog(@Param("sql") String sql, @Param("dataTables") DataTables dataTables);
}