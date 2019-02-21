package com.micolor.commoncore.communication.websocket.controller;

import com.micolor.commoncore.communication.websocket.service.SendMQMessageService;
import com.micolor.commoncore.message.domain.ApiMsgBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Project:root
 * Package:com.micolor.commoncore.communication.websocket.controller
 *
 * @Author: Evangoe
 * @Description:
 * @Date:20/10/17
 * @Modified:
 */
@Controller
@EnableAutoConfiguration
@Api(value="消息推送测试接口",tags = "消息推送测试接口")
public class WebSocketSendTestController {

    @Autowired
    SendMQMessageService sendMQMessageService;

    @RequestMapping(value = "/interface/business/websocketsend", method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value="消息推送测试接口",tags = "消息推送测试接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name="token",value="token值",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name="userId",value="用户编号",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name="comsgmpany",value="消息内容",required = true, dataType = "String")
    })
    public ApiMsgBean send2Queue(String userId , String msg){
        sendMQMessageService.sendMsg2User(userId,msg);
        return new ApiMsgBean();
    }
}
