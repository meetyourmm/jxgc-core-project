package com.micolor.commoncore.utils;

import com.micolor.commoncore.string.StringUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Ann
 * @Date 2018/9/11 9:39
 * @Description
 */
public class Tools {
    /**
     * 获取客户端ip
     *
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtil.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtil.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}
