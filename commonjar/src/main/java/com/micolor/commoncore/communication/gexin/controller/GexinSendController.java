package com.micolor.commoncore.communication.gexin.controller;

import com.micolor.commoncore.communication.gexin.service.GexinSendSerivce;
import com.micolor.commoncore.message.domain.MsgBean;
import com.micolor.commoncore.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * Project:root
 * Package:com.micolor.commoncore.communication.gexin.controller
 *
 * @Author: Ann
 * @Description:
 * @Date:2017/7/31
 * @Modified:
 */
@EnableAutoConfiguration
@Controller
@ApiIgnore
public class GexinSendController {
    private static Logger logger = LoggerFactory.getLogger(GexinSendLogController.class);
    @Autowired

    private GexinSendSerivce gexinSendSerivce;
    @RequestMapping(value = "/system/communication/gexin/pushmsg", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public MsgBean pushAppMsg(HttpServletRequest request){
        MsgBean msgBean = new MsgBean(  );
        try {
            String title = request.getParameter("title"); //标题
            String content = request.getParameter("content");//内容
            String aliasStr = request.getParameter("aliasStr");//发送对象逗号隔开
            String jsonStr = request.getParameter("jsonStr");//透传参数
            if(StringUtil.isNotEmpty(title) && StringUtil.isNotEmpty(content) && StringUtil.isNotEmpty(aliasStr)){
                 msgBean = gexinSendSerivce.sendMsgList(title,content,aliasStr,jsonStr);
            }else{
                 msgBean.setMsg("参数有误!");
            }
        } catch (Exception e) {
            logger.error("内部异常:{}", e.getMessage());
            msgBean.setMsg("内部异常!");
        }
        return msgBean;
    }
}
