/*
 * 上海骊蘅网络科技有限公司
 * Web Site: http://www.lihenginfo.com/
 */
package com.micolor.commoncore.commonmapper.communication.smsTemplate;


import com.micolor.commoncore.communication.smsTemplate.entity.MessageTemplateEntity;
import com.micolor.commoncore.database.provider.SqlProvider;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.statics.StringStatics;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.business.data.dao.messagetemplate
 *
 * @Author:Ann
 * @Description:信息模版配置表
 * @Date:2017-9-29
 * @Modified:
 */
@Mapper
@Repository
public interface MessageTemplateRepository {

    @Select("select * from MESSAGE_TEMPLATE where 1=1 ")
    List<Map> getAllMessageTemplateList();

    @SelectProvider(type = SqlProvider.class, method = "selectListForCustom")
    List<MessageTemplateEntity> getMessageTemplatePageData(@Param("sql") String sql, @Param("dataTables") DataTables dataTables);

    @Select("select * from MESSAGE_TEMPLATE where mtid =  #{Id}")
    MessageTemplateEntity getMessageTemplateInfo(String Id);

    @Select("select * from MESSAGE_TEMPLATE where mtid =  #{Id}")
    Map getMessageTemplateInfo4Map(String Id);

    @Delete("delete from MESSAGE_TEMPLATE   where mtid = #{Id}")
    void deleteMessageTemplate(String Id);

    @Insert("insert into MESSAGE_TEMPLATE (" +
            "Mtid ," + //主键
            "Mtemplate ," + //短信模版
            "Remark " + //备注
            ") values (" +
            "#{mtid,jdbcType=VARCHAR}," + //主键
            "#{mtemplate,jdbcType=VARCHAR}," + //短信模版
            "#{remark,jdbcType=VARCHAR}" + //备注
            ")")
    @SelectKey(keyProperty = "mtid", resultType = String.class, before = true, statement = StringStatics.GENENRTEFROMDB)
    void saveMessageTemplate(MessageTemplateEntity messageTemplateEntity);

    @Update("update MESSAGE_TEMPLATE set " +
            "Mtid =#{mtid,jdbcType=VARCHAR} ," + //主键
            "Mtemplate =#{mtemplate,jdbcType=VARCHAR} ," + //短信模版
            "Remark =#{remark,jdbcType=VARCHAR} " + //备注
            " where  mtid =#{mtid,jdbcType=VARCHAR}"
    )
    void updateMessageTemplate(MessageTemplateEntity messageTemplateEntity);

}