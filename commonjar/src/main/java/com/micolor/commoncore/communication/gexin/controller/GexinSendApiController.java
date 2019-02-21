package com.micolor.commoncore.communication.gexin.controller;

import com.micolor.commoncore.communication.gexin.service.GexinSendSerivce;
import com.micolor.commoncore.message.domain.ApiMsgBean;
import com.micolor.commoncore.message.domain.MsgBean;
import com.micolor.commoncore.statics.EnumUtil;
import com.micolor.commoncore.statics.StringStatics;
import com.micolor.commoncore.string.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
 * Created by Ann on 2017/7/30.
 */
@EnableAutoConfiguration
@Controller
@ApiIgnore
@Api(value="个信发送控制器",tags = "个信发送控制器")
public class GexinSendApiController {
    private static Logger logger = LoggerFactory.getLogger(GexinSendLogController.class);
    @Autowired
    private GexinSendSerivce gexinSendSerivce;

    @RequestMapping(value = "/interface/communication/gexin/pushmsg", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value="个信发送控制器",tags = "个信发送控制器")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name="title",value="标题",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name="content",value="内容",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name="cidstr",value="发送对象逗号隔开",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name="jsonStr",value="透传参数",required = true,dataType = "String")
    })
    public ApiMsgBean pushApiMsg(HttpServletRequest request) {
        ApiMsgBean apiMsgBean = new ApiMsgBean();
        try {
            String title = request.getParameter("title"); //标题
            String content = request.getParameter("content");//内容
            String cidStr = request.getParameter("cidstr");//发送对象逗号隔开
            String jsonStr = request.getParameter("jsonStr");//透传参数
            if (StringUtil.isNotEmpty(title) && StringUtil.isNotEmpty(content) && StringUtil.isNotEmpty(cidStr)) {
                gexinSendSerivce.sendMsgList(title, content, cidStr, jsonStr);
                apiMsgBean.setMsg(StringStatics.OPERATIONOK);
                apiMsgBean.setFlag(EnumUtil.ApiMessageStatus.Success);
            } else {
                apiMsgBean.setMsg(StringStatics.OPERATIONERROR);
                apiMsgBean.setFlag(EnumUtil.ApiMessageStatus.Error);
            }
        } catch (Exception e) {
            logger.error("内部异常:{}", e.getMessage());
            apiMsgBean.setMsg(StringStatics.OPERATIONERROR);
            apiMsgBean.setFlag(EnumUtil.ApiMessageStatus.Error);
        }
        return apiMsgBean;
    }

    @RequestMapping(value = "/interface/communication/gexin/getaliasbyclientid", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ApiMsgBean getAliasByClientId(HttpServletRequest request) {
        ApiMsgBean apiMsgBean = new ApiMsgBean();
        try {
            String cid = request.getParameter("cid"); //设备cid
            if (StringUtil.isNotEmpty(cid)) {
                MsgBean msgBean = gexinSendSerivce.getAliasByClientId(cid);
                apiMsgBean = new ApiMsgBean(msgBean);
            } else {
                apiMsgBean.setMsg(StringStatics.OPERATIONERROR);
                apiMsgBean.setFlag(EnumUtil.ApiMessageStatus.Error);
            }
        } catch (Exception e) {
            logger.error("内部异常:{}", e.getMessage());
            apiMsgBean.setMsg(StringStatics.OPERATIONERROR);
            apiMsgBean.setFlag(EnumUtil.ApiMessageStatus.Error);
        }
        return apiMsgBean;
    }

    @RequestMapping(value = "/interface/communication/gexin/getusertagsbyclientid", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ApiMsgBean getUserTagsByClientId(HttpServletRequest request) {
        ApiMsgBean apiMsgBean = new ApiMsgBean();
        try {
            String cid = request.getParameter("cid"); //设备cid
            if (StringUtil.isNotEmpty(cid)) {
                MsgBean msgBean = gexinSendSerivce.getUserTagsByClientId(cid);
                apiMsgBean = new ApiMsgBean(msgBean);
            } else {
                apiMsgBean.setMsg(StringStatics.OPERATIONERROR);
                apiMsgBean.setFlag(EnumUtil.ApiMessageStatus.Error);
            }
        } catch (Exception e) {
            logger.error("内部异常:{}", e.getMessage());
            apiMsgBean.setMsg(StringStatics.OPERATIONERROR);
            apiMsgBean.setFlag(EnumUtil.ApiMessageStatus.Error);
        }
        return apiMsgBean;
    }

    @RequestMapping(value = "/interface/communication/gexin/getclientidbyalias", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ApiMsgBean getClientIdByAlias(HttpServletRequest request) {
        ApiMsgBean apiMsgBean = new ApiMsgBean();
        try {
            String alias = request.getParameter("alias"); //设备别名
            if (StringUtil.isNotEmpty(alias)) {
                MsgBean msgBean = gexinSendSerivce.getClientIdByAlias(alias);
                apiMsgBean = new ApiMsgBean(msgBean);
            } else {
                apiMsgBean.setMsg(StringStatics.OPERATIONERROR);
                apiMsgBean.setFlag(EnumUtil.ApiMessageStatus.Error);
            }
        } catch (Exception e) {
            logger.error("内部异常:{}", e.getMessage());
            apiMsgBean.setMsg(StringStatics.OPERATIONERROR);
            apiMsgBean.setFlag(EnumUtil.ApiMessageStatus.Error);
        }
        return apiMsgBean;
    }

}
