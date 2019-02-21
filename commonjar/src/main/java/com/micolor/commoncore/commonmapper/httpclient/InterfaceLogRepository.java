package com.micolor.commoncore.commonmapper.httpclient;

import com.micolor.commoncore.httpclient.entity.InterfaceLogEntity;
import com.micolor.commoncore.statics.StringStatics;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.httpclient.mapper
 *
 * @Author: Evangoe
 * @Description: 记录http请求日志的mapper接口
 * @Date:28/6/17
 * @Modified:
 */
@Mapper
@Repository
public interface InterfaceLogRepository {
    /**
     * 保存http请求日志
     * @param interfaceLogEntity http请求日志实体类
     */
    @Insert("insert into interface_logs (logid,logtime,sendurl,sendparam,sendresult,sendtype) values " +
            "(#{logId},#{logTime},#{sendUrl},#{sendParam},#{sendResult},#{sendType}) ")
    @SelectKey(before = true,keyProperty = "logId",resultType = String.class,statement = StringStatics.GENENRTEFROMDB)
    void saveInterfaceLog(InterfaceLogEntity interfaceLogEntity);
}
