package com.micolor.commoncore.string;


import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: STRINGUTIL
 * @Description: 处理字符串的工具类
 * @author 钰鹏
 * @date 2013年10月9日 下午1:48:49
 * 
 */
public class StringUtil {
	private static Logger logger = LoggerFactory.getLogger(StringUtil.class);
	/**
	 * @Title: getPatternStr
	 * @Description: 根据自定义的正则表达式处理字符串。
	 * @param input
	 *            待处理的字符串
	 * @param reg
	 *            正则表达式
	 * @return String 处理完成的字符串
	 */
	public static String getPatternStr(String input, String reg) {
		String temp = null;
		Pattern pattern = Pattern.compile(reg);
		Matcher m = pattern.matcher(input);
		if (m.find()) {
			temp = m.group(1);
		}
		return temp;
	}

	/**
	 * 
	 * @Title: isNotEmpty
	 * @Description: 判断对象是否不为空或不为空字符串
	 * @param obj
	 *            待判断的对象
	 * @return boolean 不空返回true，空返回false
	 */
	public static boolean isNotEmpty(Object obj) {
		return (obj != null) && (!"".equals(obj.toString()));
	}

	/**
	 * 
	 * @Title: trimLeft
	 * @Description: 去除字符串左边空白
	 * @param value
	 *            待处理的字符串
	 * @return String 处理完毕的字符串
	 */
	public static String trimLeft(String value) {
		if (value == null) {
			return "";
		}
		String result = value;
		char[] ch = result.toCharArray();
		int index = -1;
		for (int i = 0; i < ch.length; i++) {
			if (!Character.isWhitespace(ch[i]))
				break;
			index = i;
		}

		if (index != -1) {
			result = result.substring(index + 1);
		}
		return result;
	}

	/**
	 *
	 * @Title:
	 * @Description: 多个参数非空判断
	 * @param args
	 *            待处理的字符串
	 * @return String 处理完毕的字符串
	 */
	public static boolean isNotEmptyArray(String[] args){
		boolean isNotNull = true;
		for (String param:args) {
			if(isNotEmpty(param)){
				continue;
			}else {
				isNotNull = false;
				break;
			}
		}
		return isNotNull;
	}

	/**
	 * 
	 * @Title: trimRight
	 * @Description: 去除字符串右边空白
	 * @param value
	 *            待处理的字符串
	 * @return String 处理完毕的字符串
	 */
	public static String trimRight(String value) {
		if (value == null) {
			return "";
		}

		String result = value;
		char[] ch = result.toCharArray();
		int endIndex = -1;
		for (int i = ch.length - 1; i > -1; i--) {
			if (!Character.isWhitespace(ch[i]))
				break;
			endIndex = i;
		}

		if (endIndex != -1) {
			result = result.substring(0, endIndex);
		}
		return result;
	}

	/**
	 * 
	 * @Title: isEmpty
	 * @Description: 判断对象是否为空或空字符串
	 * @param strObj
	 *            待处理的对象
	 * @return boolean 空或空字符串返回true，否则返回false
	 */
	public static boolean isEmpty(Object strObj) {
		boolean rv = false;
		if ((strObj == null) || (strObj.toString().trim().length() < 1))
			rv = true;
		else {
			rv = false;
		}
		return rv;
	}
	/**
	 *
	 * @Title: isEmpty4Obj
	 * @Description: 当对象是为空或空字符串时，返回空字符串否者返回该对象。
	 * @param strObj
	 *            待处理的对象
	 * @return Object 处理完成的对象
	 */
	public static Object isEmpty4Obj(Object strObj) {
		Object rv;
		if ((strObj == null) || (strObj.toString().trim().length() < 1))
			rv = new String("");
		else {
			rv = strObj;
		}
		return rv;
	}
	/**
	 * 
	 * @Title: isEmpty4Str
	 * @Description: 当对象是为空或空字符串时，返回空字符串否者返回该对象的字符串。
	 * @param strObj
	 *            待处理的对象
	 * @return String 处理完成的字符串
	 */
	public static String isEmpty4Str(Object strObj) {
		String rv = "";
		if ((strObj == null) || (strObj.toString().trim().length() < 1))
			rv = "";
		else {
			rv = strObj.toString();
		}
		return rv;
	}

	/**
	 * 判断日期字符串是否为空，同时转换为字符串
	 * @param strObj
	 * @return
	 */
	public static Date isEmpty4Date(Object strObj) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		String dateObj = StringUtil.isEmpty4Str(strObj).replace("T"," ");
		Date date = null;
		if(!"null".equals(dateObj) && !"".equals(dateObj)){
			try{
				date = sdf.parse(dateObj);
			}catch (Exception e){
				logger.error("isEmpty4Date method has a exception ,infomation : {}",e.getMessage());
			}
		}
		return date;
	}

	/**
	 * 
	 * @Title: isEmpty4Number
	 * @Description: 当对象是为空或空字符串时，返回空0.0否则返回该对象的字符串。
	 * @param strObj
	 *            待处理的对象
	 * @return String 处理完成的字符串
	 */
	public static String isEmpty4Number(Object strObj) {
		String rv = "";
		if ((strObj == null) || (strObj.toString().trim().length() < 1))
			rv = "0.0";
		else {
			rv = strObj.toString();
		}
		return rv;
	}

	/**
	 * 
	 * @Title: isEmpty4Null
	 * @Description: 当对象是为空或空字符串时，返回空NULL否则返回该对象的字符串。
	 * @param strObj
	 *            待处理的对象
	 * @return String 处理完成的字符串
	 */
	public static String isEmpty4Null(Object strObj) {
		String rv = "";
		if ((strObj == null) || (strObj.toString().trim().length() < 1))
			rv = null;
		else {
			rv = strObj.toString();
		}
		return rv;
	}

	/**
	 * @Title: currentDate
	 * @Description: 获得当前日期
	 * @return String 日期字符串
	 */
	public static String currentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	/**
	 * @Title: currentDate
	 * @Description: 根据格式化内容获得当前日期
	 * @param format
	 *            格式化参数（例如 yyyy-MM-dd，yyyy/MM/dd等）
	 * @return String 日期字符串
	 */
	public static String currentDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	/**
	 * 
	 * @Title: removeEmpty
	 * @Description: 去除String数组中的空白字符串
	 * @param items
	 *            待处理的String数组
	 * @return String[] 处理完成的String数组
	 */
	public static String[] removeEmpty(String[] items) {
		List list = new ArrayList();
		String[] arrayOfString = items;
		int j = items.length;
		for (int i = 0; i < j; i++) {
			String s = arrayOfString[i];
			if (!"".equals(s)) {
				list.add(s);
			}
		}
		return (String[]) list.toArray(new String[0]);
	}
	
	public static String getCurrDate() {
		String dateStr = "";
		Calendar cal = Calendar.getInstance();// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
		String monthStr = "";
		if (month < 10) {
			monthStr = "0" + month;
		}else{
			monthStr = String.valueOf(month);
		}
		int day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
		dateStr = year + "-" + monthStr + "-" + day;
		return dateStr;
	}

	/**
	 * 获取登录用户远程主机ip地址
	 *
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}


	/**
	 * 请求参数拼装
	 *
	 * @param paramsArray
	 * @return
	 */
	public static String argsArrayToString(Object[] paramsArray) {
		String params = "";
		List list = new ArrayList();
		if (paramsArray != null && paramsArray.length > 0) {
			for(int index = 0 ;index < paramsArray.length;index++){
				if(paramsArray[index]!=null){
					Object object = paramsArray[index];
					list.add(object.toString());
				}

			}
			JSONArray result = JSONArray.fromObject(list);
			params += result.toString() + " ";
		}
		return params.trim();
	}

	/**
	 * 方法名称:transMapToString
	 * 传入参数:map
	 * 返回值:String 形如 username'chenziwen^password'1234
	 */
	public static String transMapToString(Map map){
		StringBuffer sb = new StringBuffer();
		HashMap newMap = new HashMap();
		for(Iterator iter = map.entrySet().iterator();iter.hasNext();){
			Map.Entry element = (Map.Entry)iter.next();
			Object strKey = element.getKey();
			String[] value=(String[])element.getValue();
			StringBuffer valueStr= new StringBuffer();
			for(int i=0;i <value.length;i++){
				valueStr.append(value[i] +",");
			}
			newMap.put(strKey,valueStr);
		}
		sb.append(newMap.toString());
		return sb.toString();
	}

	public static String getRandNum(int charCount) {
		String charValue = "";
		for (int i = 0; i < charCount; i++) {
			char c = (char) (randomInt(0, 10) + '0');
			charValue += String.valueOf(c);
		}
		return charValue;
	}
	public static int randomInt(int from, int to) {
		Random r = new Random();
		return from + r.nextInt(to - from);
	}
}
