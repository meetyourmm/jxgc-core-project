package com.micolor.commoncore.shiro.service;

import com.micolor.commoncore.commonmapper.shiro.MineShiroUserMapper;
import com.micolor.commoncore.shiro.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("mineShiroUserService")
public class MineShiroUserServiceImpl implements MineShiroUserService {

	@Autowired
	private MineShiroUserMapper mineShiroUserMapper;
	/**
	 * 根据账号Account查询当前用户
	 * @param account
	 * @return
	 */
	public UserInfo findByAccount(String account) {
		return mineShiroUserMapper.findByAccount(account);
	}

	/**
	 * 通过手机号码获得用户信息
	 *
	 * @param mobilePhone 用户手机号码
	 * @return
	 */
	@Override
	public UserInfo findByMobile(String mobilePhone) {
		return null;
	}

	/**
	 * 通过凭证验证登录信息
	 *
	 * @param certificateType  凭证类型 ﻿凭证类型（1 用户名 2 邮箱 3 手机）
	 * @param certificateValue 凭证值
	 * @return
	 */
	@Override
	public Map findByCertificate(String certificateType, String certificateValue) {
		return mineShiroUserMapper.findByCertificate(certificateType,certificateValue);
	}
}