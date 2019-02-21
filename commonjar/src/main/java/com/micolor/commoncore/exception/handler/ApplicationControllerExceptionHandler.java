package com.micolor.commoncore.exception.handler;

import com.micolor.commoncore.message.domain.MsgBean;
import com.micolor.commoncore.statics.EnumUtil;
import com.micolor.commoncore.statics.StringStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.handler
 *
 * @Author: Evangoe
 * @Description: 用于捕捉全局Controller中所有异常的处理类。
 * @Date:11/05/17
 * @Modified:
 */
@ControllerAdvice
@EnableAutoConfiguration
public class ApplicationControllerExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(ApplicationControllerExceptionHandler.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public MsgBean defaultErrorHandler(HttpServletRequest req, Exception e) {
        logger.error(StringStatics.INNERERROR,e);
        MsgBean msgBean = new MsgBean();
        msgBean.setMsg("操作失败！原因："+e.getMessage());
        msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
        return msgBean;
//        e.printStackTrace();
//        //打印异常信息：
//        logger.error("##################################################################");
//        logger.error("e.getLocalizedMessage():"+e.getLocalizedMessage());
//        logger.error("e.getMessage():"+e.getMessage());
//        logger.error("e.getCause():"+e.getCause());
//        logger.error("e.getClass():"+e.getClass());
//        logger.error("##################################################################");
    }
}