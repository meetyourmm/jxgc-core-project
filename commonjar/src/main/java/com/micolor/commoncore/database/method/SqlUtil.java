/**   
 * @Title: SqlUtil.java 
 * @Package common.method 
 * @author 钰鹏  
 * @date 2014年12月27日 下午10:27:08 
 * @version V1.0   
 */
package com.micolor.commoncore.database.method;

import com.micolor.commoncore.string.StringUtil;

/**
 * @ClassName: SqlUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 钰鹏
 * @date 2014年12月27日 下午10:27:08
 * 
 */
public class SqlUtil {

	private static String DBTYPE = "MYSQL";

	public static StringBuilder createPageingSql(StringBuilder sql, int start,
		int limit, String sort, String dir) {
		StringBuilder finishsql = new StringBuilder();

		if (DBTYPE.equals("MYSQL")) {
			finishsql = createPageingSql4Mysql(sql, start, limit, sort, dir);
		}

		if (DBTYPE.equals("ORA")) {
			finishsql = createPageingSql4Ora(sql, start, limit, sort, dir);
		}

		return finishsql;
	}
	/**
	 * 
	* @Title: createPageingSql4Mysql 
	* @Description: mysql分页方式
	* @param @param sql
	* @param @param start
	* @param @param limit
	* @param @param sort
	* @param @param dir
	* @param @return
	 */
	private static StringBuilder createPageingSql4Mysql(StringBuilder sql, int start,
			int limit, String sort, String dir) {
        StringBuilder finishsql = new StringBuilder();
		if (StringUtil.isNotEmpty(sql)) {
			finishsql.append(sql);
			// 排序
			if (StringUtil.isNotEmpty(sort)) {
				finishsql.append(" order by " + sort + " " + dir);
			}
			// 分页
			int begin = ((start - 1) * limit);
			int end = limit;
			finishsql.append(" limit " + begin + "," + end);
		}
		return finishsql;
	}
	/**
	 * 
	* @Title: createPageingSql4Ora 
	* @Description: oracle分页方式
	* @param @param sql
	* @param @param start
	* @param @param limit
	* @param @param sort
	* @param @param dir
	* @param @return
	 */
	private static StringBuilder createPageingSql4Ora(StringBuilder sql, int start,
			int limit, String sort, String dir) {
        StringBuilder finishsql = new StringBuilder();
		
		if (StringUtil.isNotEmpty(sql)) {
			
			// 分页
			int begin = ((start - 1) * limit)+1;
			int end = begin+limit-1;

			finishsql.append(" SELECT * FROM (SELECT A.*, ROWNUM RN FROM ( ");
			finishsql.append(sql);
			// 排序
			if (StringUtil.isNotEmpty(sort)) {
				finishsql.append(" order by " + sort + " " + dir);
			}
			finishsql.append(" ) A WHERE ROWNUM <= "+end);
			finishsql.append(" )WHERE RN >= "+begin);
		}
		return finishsql;
	}
}
