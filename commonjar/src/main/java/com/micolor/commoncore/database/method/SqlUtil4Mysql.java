/**   
 * @Title: SqlUtil4Mysql.java 
 * @Package common.method 
 * @author 钰鹏  
 * @date 2014年4月2日 下午5:22:07 
 * @version V1.0   
 */
package com.micolor.commoncore.database.method;

import com.micolor.commoncore.string.StringUtil;

/**
 * @ClassName: SqlUtil4Mysql
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 钰鹏
 * @date 2014年4月2日 下午5:22:07
 * 
 */
public class SqlUtil4Mysql {
	/**
	 * 
	 * @Title: createPageingSql
	 * @Description: 创建分页的sql
	 * @param @param sql
	 * @param @param start
	 * @param @param limit
	 * @param @param sort
	 * @param @param dir
	 * @param @param params
	 * @param @return
	 */
	public static StringBuffer createPageingSql(StringBuffer sql, int start,
			int limit, String sort, String dir) {
		StringBuffer finishsql = new StringBuffer();
		if (StringUtil.isNotEmpty(sql)) {
			finishsql.append(sql);
			// 排序
			if (StringUtil.isNotEmpty(sort)) {
				finishsql.append(" order by " + sort + " " + dir);
			}
			// 分页
			int begin = ((start-1)*limit);
			int end = limit;
			finishsql.append(" limit " + begin + "," + end);
		}
		return finishsql;
	}
}
