package com.micolor.commoncore.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.micolor.commoncore.jwt.Audience;
import com.micolor.commoncore.jwt.JwtHelper;
import com.micolor.commoncore.message.domain.ApiMsgBean;
import com.micolor.commoncore.statics.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.interceptor
 *
 * @Author: Evangoe
 * @Description:
 * @Date:31/05/17
 * @Modified:
 */
@EnableAutoConfiguration
public class APIInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    private Audience audience;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getParameter("token");
        EnumUtil.MessageStatus status = null;
        String msg ;
        if ((auth != null) && (auth.length() > 7))
        {
            String headStr = auth.substring(0, 6).toLowerCase();
            if (headStr.compareTo("bearer") == 0)
            {

                auth = auth.substring(7, auth.length());
                status = JwtHelper.checkToken(auth, audience.getBase64Secret());
                if(status.equals(EnumUtil.MessageStatus.OK)){
                    return super.preHandle(request, response, handler);
                }
            }else{
                status = EnumUtil.MessageStatus.ERROR;
            }
        }else{
            status = EnumUtil.MessageStatus.ERROR;
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        if(status.equals(EnumUtil.MessageStatus.TIMEOUT) ){
            response.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
            msg = "token过期";
        }else{
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            msg = "token验证失败";
        }

        ObjectMapper mapper = new ObjectMapper();
        ApiMsgBean apiMsgBean = new ApiMsgBean(EnumUtil.ApiMessageStatus.Error,msg,response.getStatus());
        response.getWriter().write(mapper.writeValueAsString(apiMsgBean));
        return false;
    }



}