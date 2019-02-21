package com.micolor.commoncore.orderno;

import java.util.Date;
import java.util.Random;

/**
 * 订单生成器
 * Created by 刘磊 on 2017/7/27.
 */
public class OrderNoMaker {
    public static Random r = new Random();

    /**
     * 订单号生成策略
     * 随机数(n位)+类型（2位）+时间戳后6位;
     *
     * @return Ordercode
     */
    public static String getOrdercode(String type) {
        //时间转换
        String timeCode = String.valueOf(new Date().getTime());
        Integer len = timeCode.length();
        //取后6位
        String dateCode = timeCode.substring(timeCode.length() - 6, len);
        //拼接订单号
        String orderCode = "8" + getRandom(6) + type + dateCode;
        return orderCode;
    }

    /**
     * 预录订单生成
     * 随机数(n位)+类型（2位）+时间戳后6位;
     *
     * @return
     */
    public static String getPrno() {
        // 时间转换
        String timeCode = String.valueOf(new Date().getTime());
        //取后6位
        String dateCode = timeCode.substring(timeCode.length() - 6, timeCode.length());
        //拼接订单号
        String orderCode = "8" + getRandom(5) + "02" + dateCode;
        return orderCode;
    }

    /**
     * 生成随机数
     *
     * @return
     */
    public static String getRandom(Integer len) {
        Integer ws = (int) Math.pow(10, len);
        long num = Math.abs(r.nextLong() % Long.parseLong(String.valueOf(ws)));
        String s = String.valueOf(num);
        for (int i = 0; i < len - s.length(); i++) {
            s = "0" + s;
        }
        return s;
    }
}
