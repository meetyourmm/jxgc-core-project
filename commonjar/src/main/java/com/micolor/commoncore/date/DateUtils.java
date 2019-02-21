package com.micolor.commoncore.date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Evangoe on 12/01/17.
 * 用户前台日期控件的日期转换
 */
public class DateUtils {

    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /**
     * string转date
     * @param dateString
     * @return date
     */
    public static Date convertToDateFromStr(String dateString) throws ParseException{
        Date date = null;
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            date = sdf.parse(dateString);
        }
        catch (ParseException e)
        {
            logger.error("转化日期格式失败!",e);
        }
        return date;
    }

    /**
     * 前台日期控件转换
     * @param dateTime
     * @return
     */
    public static String reportDateTrans(String dateTime){
        String transDateTime ="";

        if(dateTime.equals("2ds")){
            transDateTime = "  Stat_Day between '"+getOtherDayDate(-1)+"' and '"+getCurrDate()+"' ";
        }else if(dateTime.equals("7ds")){
            transDateTime = "  Stat_Day between '"+getOtherDayDate(-8)+"' and '"+getOtherDayDate(-1)+"' ";
        }else if(dateTime.equals("30ds")){
            transDateTime = "  Stat_Day between '"+getOtherDayDate(-31)+"' and '"+getOtherDayDate(-1)+"' ";
        }else {
            transDateTime = "  Stat_Day = '"+dateTime+"'";
        }
        return transDateTime;
    }
    
    /**
     * 前台日期控件转换（时间转换）
     * @param dateTime
     * @return
     */
    public static String reportDatetime(String dateTime){
    	//开始时间
    	String start_time = dateTime.substring(0,10);
    	//结束时间
    	String end_time = dateTime.substring(10,dateTime.length());
        String transDateTime =" Stat_Day between '"+start_time+"' and '"+end_time+"'";
        return transDateTime;
    }

    public static String getCurrDate() {
        String dateStr = "";
        Calendar cal = Calendar.getInstance();// 使用日历类
        int year = cal.get(Calendar.YEAR);// 得到年
        int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
        String monthStr = "";
        if (month < 10) {
            monthStr = "0" + month;
        } else {
            monthStr = String.valueOf(month);
        }
        int day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
        String dayStr = "";
        if (day < 10) {
            dayStr = "0" + day;
        } else {
            dayStr = String.valueOf(day);
        }
        dateStr = year + "-" + monthStr + "-" + dayStr;
        return dateStr;
    }
    /**
     * 日期转换，去掉空格
     * @return
     */
    public static String getCurrDates() {
        String dateStr = "";
        Calendar cal = Calendar.getInstance();// 使用日历类
        int year = cal.get(Calendar.YEAR);// 得到年
        int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
        String monthStr = "";
        if (month < 10) {
            monthStr = "0" + month;
        } else {
            monthStr = String.valueOf(month);
        }
        int day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
        String dayStr = "";
        if (day < 10) {
            dayStr = "0" + day;
        } else {
            dayStr = String.valueOf(day);
        }
        dateStr = year+"-"+monthStr+"-"+dayStr;
        return dateStr;
    }

    /**
     * 获取前后多少天的日期
     * @param datecount
     * @return
     */
    public static String getOtherDayDate(int datecount) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DATE, datecount);
        String dateTime = sdf.format(calendar.getTime());
        return dateTime;
    }

    public static String getStatTimeBucketBySysTime(){
        String StatTimeBucket = "";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(date);
        // 截取当前时间时分秒
        int strDateH = Integer.parseInt(strDate.substring(11, 13));
        if(strDateH >=0 &&  strDateH<=8){
            StatTimeBucket ="1";
        }
        if(strDateH >8 &&  strDateH<=10){
            StatTimeBucket ="2";
        }
        if(strDateH >10 &&  strDateH<=16){
            StatTimeBucket ="3";
        }
        if(strDateH >16 &&  strDateH<=24){
            StatTimeBucket ="4";
        }
        return StatTimeBucket;
    }
    /**
     *判断选择时间区间
     * @param startTime
     * @param endTime
     * @return
     */
    public static String compaerDay(String startTime,String endTime){
        String days = "";
        String nowDay = getCurrDate().toString();
        String towday = getOtherDayDate(-1).toString();
        String sevenday = getOtherDayDate(-7).toString();
        String thirty = getOtherDayDate(-30).toString();
        if (startTime.equals(towday) && endTime.equals(nowDay)){
            days="2ds";
        }else if (startTime.equals(sevenday) && endTime.equals(towday)){
            days="7ds";
        }else if (startTime.equals(thirty) && endTime.equals(towday)){
            days="30ds";
        }else{
            days="otherds";
        }
        return days;
    }
}
