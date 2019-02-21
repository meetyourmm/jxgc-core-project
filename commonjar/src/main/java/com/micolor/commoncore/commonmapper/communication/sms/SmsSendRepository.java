package com.micolor.commoncore.commonmapper.communication.sms;

import com.micolor.commoncore.communication.sms.entity.SmsSendEntity;
import com.micolor.commoncore.database.provider.SqlProvider;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.statics.StringStatics;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.communication.sms.mapper
 *
 * @Author: Evangoe
 * @Description:
 * @Date:10/06/17
 * @Modified:
 */
@Mapper
@Repository
public interface SmsSendRepository {
    @Insert("insert into sms_send_log (ssid,clientid,sscontent,ssstatus,ssdate) values (#{ssId},#{clientId},#{ssContent},#{ssStatus},#{ssDate})")
    @SelectKey(keyProperty = "ssId", resultType = String.class, before = true, statement = StringStatics.GENENRTEFROMDB)
    void saveSmsSendLog(SmsSendEntity smsSendEntity);


    /**
     * 获得短信发送列表
     * @param sql
     * @param dataTables
     * @return
     */
    @SelectProvider(type=SqlProvider.class,method = "selectListForDataTable")
    List<Map> getMailSendLog(@Param("sql") String sql, @Param("dataTables") DataTables dataTables);
}
