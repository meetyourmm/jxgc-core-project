package com.micolor.commoncore.commonmapper.communication.mail;

import com.micolor.commoncore.communication.mail.entity.MailSendLogEntity;
import com.micolor.commoncore.database.provider.SqlProvider;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.statics.StringStatics;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.communication.mail.mapper
 *
 * @Author: Evangoe
 * @Description: 邮件发送记录mapper
 * @Date:15/06/17
 * @Modified:
 */
@Mapper
@Repository
public interface MailSendLogRepository {
    /**
     * 保存邮件发送记录
     * @param mailSendLogEntity
     */
    @Insert("insert into mail_send_log (mailsendid,mailsendcontent,mailsendtargets,mailsenddate) values (#{mailSendId},#{mailSendContent},#{mailSendTarget},#{mailSendDate}) ")
    @SelectKey(keyProperty = "mailSendId",before = true,resultType = String.class,statement = StringStatics.GENENRTEFROMDB)
    void saveMailSendLog(MailSendLogEntity mailSendLogEntity);

    /**
     * 获得邮件日志清单
     * @param sql
     * @param dataTables
     * @return
     */
    @SelectProvider(type=SqlProvider.class,method = "selectListForDataTable")
    @Results({
            @Result(property = "MAILSENDCONTENT",column = "mailsendcontent",jdbcType = JdbcType.CLOB,javaType = String.class)
    })
    List<Map> getMailSendLog(@Param("sql") String sql, @Param("dataTables") DataTables dataTables);
}
