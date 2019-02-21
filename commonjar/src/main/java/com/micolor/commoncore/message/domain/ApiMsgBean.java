package com.micolor.commoncore.message.domain;

import com.micolor.commoncore.statics.EnumUtil;
import com.micolor.commoncore.statics.StringStatics;

import java.util.List;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.message.domain
 *
 * @Author: Evangoe
 * @Description: 用于向接口返回参数的实体封装的实体类。
 * @Date:04/06/17
 * @Modified:
 */
public class ApiMsgBean {
    private EnumUtil.ApiMessageStatus flag;
    private String msg;
    private Object datas;

    public ApiMsgBean() {
    }

    public ApiMsgBean(EnumUtil.ApiMessageStatus flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public ApiMsgBean(EnumUtil.ApiMessageStatus flag, String msg, Object datas) {
        this.flag = flag;
        this.msg = msg;
        this.datas = datas;
    }

    /**
     * 使用msgbean构建apimsgbean
     * @param msgBean
     */
    public ApiMsgBean(MsgBean msgBean){
        if(msgBean.getStatus().equals(EnumUtil.MessageStatus.OK)){
            this.setMsg(StringStatics.OPERATIONOK);
            this.setFlag(EnumUtil.ApiMessageStatus.Success);
            if(msgBean.getContent()!= null){
                this.setDatas(msgBean.getContent());
            }else{
                msgBean.setContent(null);
            }
        }else{
            this.setMsg("内部错误");
            this.setFlag(EnumUtil.ApiMessageStatus.Error);
            if(msgBean.getContent()!= null){
                this.setDatas(msgBean.getContent());
            }else{
                msgBean.setContent(null);
            }
        }
    }
    public EnumUtil.ApiMessageStatus getFlag() {
        return flag;
    }

    public void setFlag(EnumUtil.ApiMessageStatus flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        if("java.util.HashMap".equals(datas.getClass().getName())){
            this.datas = ConvertApiMsg.convertKeyToLowerCase4Map((Map)datas);
        }else if("java.util.ArrayList".equals(datas.getClass().getName())){
            this.datas =   ConvertApiMsg.convertKeyToLowerCase4List((List)datas);
        }else{
            this.datas = datas;
        }
    }
}