package com.micolor.commoncore.commonmapper.communication.sms;

import com.micolor.commoncore.communication.sms.entity.SmsVerificationCodeEntity;
import com.micolor.commoncore.statics.StringStatics;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.communication.sms.mapper
 *
 * @Author: Evangoe
 * @Description: 短信验证码mapper类
 * @Date:15/06/17
 * @Modified:
 */
@Mapper
@Repository
public interface SmsVerificationCodeRepository {

    /**
     *  保存短信验证码
     * @param smsVerificationCodeEntity 短信验证码实体
     */
    @Insert("insert into sms_verification_code (csid,mobile,yzcode,sendtime,ip) values (#{csId},#{mobile},#{yzCode},#{sendTime},#{ip})")
    @SelectKey(keyProperty = "csId",resultType = String.class,before = true,statement = StringStatics.GENENRTEFROMDB)
    void saveSmsVerificationCode(SmsVerificationCodeEntity smsVerificationCodeEntity);

    /**
     * 判断验证码的有效性
     * @param mobile
     * @param yzcode
     * @return
     */
    @Select("SELECT * FROM `sms_verification_code` where mobile =#{mobile}  and yzcode = #{yzcode} ORDER BY sendtime desc LIMIT 1")
    SmsVerificationCodeEntity checkVerificationCode(@Param("mobile") String mobile, @Param("yzcode") String yzcode);

    /**
     * 获取当前手机号当天获取验证码的条数
     * @param mobile
     * @return
     */
    @Select("SELECT count(1) FROM `sms_verification_code` where mobile = #{mobile} and day(sendtime)=day(now())")
    Integer countNumByMobile(@Param("mobile") String mobile);

    /**
     * 获取当前ip获取验证码的次数
     * @param ip
     * @return
     */
    @Select("SELECT count(1) FROM `sms_verification_code` where ip = #{ip} and day(sendtime)=day(now())")
    Integer countNumByIp(@Param("ip") String ip);

    /**
     * 获取当前ip最新的一条数据
     * @param mobile
     * @return
     */
    @Select("SELECT * FROM `sms_verification_code` where ip = #{ip} order by sendtime desc limit 1")
    SmsVerificationCodeEntity getNewByIp(@Param("ip") String mobile);
}
