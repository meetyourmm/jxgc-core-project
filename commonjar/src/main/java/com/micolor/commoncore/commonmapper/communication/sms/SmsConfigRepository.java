package com.micolor.commoncore.commonmapper.communication.sms;

import com.micolor.commoncore.communication.sms.entity.SmsConfigEntity;
import com.micolor.commoncore.statics.StringStatics;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.communication.sms.mapper
 *
 * @Author: Evangoe
 * @Description:短信配置类的mapper
 * @Date:06/06/17
 * @Modified:
 */
@Mapper
@Repository
public interface SmsConfigRepository {
    /**
     * 获得一条短信配置
     * @param smsCompany 短信提供公司
     * @param smsType 短信类型
     * @return
     */
    @Select("select * from sms_config where smstype = #{smsType} and smscompany =#{smsCompany}")
    SmsConfigEntity getSmsConfig(@Param("smsCompany") String smsCompany, @Param("smsType") String smsType);

    /**
     * 添加一条短信信息到数据库
     * @param smsConfigEntity 短信配置实体
     */
    @Insert("insert into sms_config(smsconfigid,smscompany,smsurl,smsaccount,smspassword,smstype,smssuffix)values(#{smsConfigId},#{smsCompany},#{smsUrl},#{smsAccount},#{smsPassword},#{smsType},#{smsSuffix})")
    @SelectKey(keyProperty = "smsConfigId" ,before = true,resultType = String.class,statement = StringStatics.GENENRTEFROMDB)
    void saveSmsConfig(SmsConfigEntity smsConfigEntity);

    /**
     * 更新一条短信配置信息到数据库
     * @param smsConfigEntity 短信配置实体
     */
    @Update("update sms_config set smscompany =#{smsCompany},smsurl=#{smsUrl},smsaccount=#{smsAccount},smspassword=#{smsPassword},smstype=#{smsType},smssuffix=#{smsSuffix} where smsconfigid = #{smsConfigId}")
    void updateSmsConfig(SmsConfigEntity smsConfigEntity);

    /**
     * 删除一条短信配置
     * @param scId 短信
     */
    @Delete("delete from sms_config where smsconfigid = #{scId}")
    void deleteSmsConfig(@Param("scId") String scId);
}
