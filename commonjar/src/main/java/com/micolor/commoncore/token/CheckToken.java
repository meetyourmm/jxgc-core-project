package com.micolor.commoncore.token;

import com.micolor.commoncore.jwt.Audience;
import com.micolor.commoncore.jwt.JwtHelper;
import com.micolor.commoncore.message.domain.ApiMsgBean;
import com.micolor.commoncore.statics.EnumUtil;
import com.micolor.commoncore.string.StringUtil;
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
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ann on 2017/8/30.
 */
@Controller
@EnableAutoConfiguration
@Api(value="Token验证接口",tags = "Token验证接口用于验证")
public class CheckToken {
    @Autowired
    private Audience audienceEntity;
    @ApiOperation(value="Token验证接口用于验证",tags = "Token验证接口用于验证")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name="token",value="token值",required = true,dataType = "String")
    })
    @RequestMapping(value = "/tokencheck", method = RequestMethod.POST)
    @ResponseBody()
    public ApiMsgBean checkAccessToken(HttpServletRequest request){
        ApiMsgBean apiMsgBean = new ApiMsgBean();
        String token = request.getParameter("token");
        EnumUtil.MessageStatus status = null;
        if(StringUtil.isNotEmpty(token)){
            String webToken = token.substring(7, token.length());
            status = JwtHelper.checkToken(webToken, audienceEntity.getBase64Secret());
            if(status.equals(EnumUtil.MessageStatus.OK)){
                apiMsgBean.setFlag(EnumUtil.ApiMessageStatus.Success);
                apiMsgBean.setMsg("token正常");
                apiMsgBean.setDatas(HttpServletResponse.SC_OK);
            }else if(status.equals(EnumUtil.MessageStatus.TIMEOUT) ){
                apiMsgBean.setFlag(EnumUtil.ApiMessageStatus.Error);
                apiMsgBean.setMsg("token过期");
                apiMsgBean.setDatas(HttpServletResponse.SC_REQUEST_TIMEOUT);
            }else{
                apiMsgBean.setFlag(EnumUtil.ApiMessageStatus.Error);
                apiMsgBean.setMsg("token验证失败");
                apiMsgBean.setDatas(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }else{
            apiMsgBean.setFlag(EnumUtil.ApiMessageStatus.Error);
            apiMsgBean.setMsg("token值不能为空");
            apiMsgBean.setDatas(HttpServletResponse.SC_BAD_REQUEST);
        }
        return apiMsgBean;
    }
}
