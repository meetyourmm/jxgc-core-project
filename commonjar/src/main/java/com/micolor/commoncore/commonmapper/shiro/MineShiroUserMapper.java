package com.micolor.commoncore.commonmapper.shiro;

import com.micolor.commoncore.shiro.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface MineShiroUserMapper {
    @Select("select * from user_info t where t.username= #{name}  and status !='3'")
    @Results({
			@Result(property = "userId",javaType = String.class,column = "userid"),
	})
	UserInfo findByAccount(String account);


	/**
	 * 根据凭证获得代码
	 * @param certificateType 凭证类型
	 * @param certificateValue 凭证值
	 * @return
	 */
	@Select("select * from user_info u left join certificate c on u.userid = c.uobject where  c.uotype='1'and utype= #{certificateType} and cstatus='1' and cvalue = #{certificateValue} ")
	Map findByCertificate(@Param("certificateType")String certificateType, @Param("certificateValue")  String certificateValue);
}