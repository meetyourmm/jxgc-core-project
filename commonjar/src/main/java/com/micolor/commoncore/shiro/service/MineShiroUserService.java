package com.micolor.commoncore.shiro.service;

import com.micolor.commoncore.shiro.domain.UserInfo;

import java.util.Map;


public interface MineShiroUserService {
	
	/**
	 * 根据账号Account查询当前用户
	 * @param account
	 * @return
	 */
	public UserInfo findByAccount(String account);

	/**
	 * 通过手机号码获得用户信息
	 * @param mobilePhone 用户手机号码
	 * @return
	 */
	public UserInfo findByMobile(String mobilePhone);

	/**
	 * 通过凭证验证登录信息
	 * @param certificateType 凭证类型 凭证类型（1 用户名 2 邮箱 3 手机）
	 * @param certificateValue 凭证值
	 * @return
	 */
	public Map findByCertificate(String certificateType, String certificateValue);
}
