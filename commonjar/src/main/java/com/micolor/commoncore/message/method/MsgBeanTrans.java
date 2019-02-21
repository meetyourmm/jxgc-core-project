/*
 * Copyright (c) 2015. MiColor
 */

/**
* @Title: MsgBeanTrans.java 
* @Package common.method 
* @author 钰鹏  
* @date May 18, 2014 10:04:57 PM 
* @version V1.0   
*/
package com.micolor.commoncore.message.method;

/** 
 * @ClassName: MsgBeanTrans 
 * @Description: 提醒信息转换类.
 * @author 钰鹏
 * @date May 18, 2014 10:04:57 PM 
 *  
 */
public class MsgBeanTrans {
	private MsgBeanTrans() {
	}

	public static String transMsg(String msg){
		String msg2return = "";
		if(msg.indexOf("Duplicate entry")!=-1){
			msg2return = "操作失败，数据重复!";
		}else if (msg.indexOf("Could not get JDBC Connection")!=-1){
			msg2return = "操作失败，数据访问异常，请联系管理员！";
		}else if(msg.indexOf("违反唯一约束条件")!=-1){
			msg2return = "操作失败，此条记录已存在。";
		}else if(msg.indexOf("a foreign key constraint fails ")!=-1){
			msg2return = "操作失败，存在与之关联的其他数据，请检查！";
		}else{
			msg2return = msg;
		}
		return msg2return;
	}
}
