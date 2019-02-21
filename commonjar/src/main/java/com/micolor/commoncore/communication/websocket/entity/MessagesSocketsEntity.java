/*
 * 上海骊蘅网络科技有限公司
 * Web Site: http://www.lihenginfo.com/
 */
package com.micolor.commoncore.communication.websocket.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.business.data.dao.messagessockets
 *
 * @Author:Ann
 * @Description: 站内消息通知实体
 * @Date:2017-9-29
 * @Modified:
 */
@Mapper
@JsonIgnoreProperties(value = {"javassistProxyFactory", "handler"})
public class MessagesSocketsEntity {
    private String msid;//主键
    private String mscontent;//消息内容
    private String mstitle;//标题
    private Date createtime;//创建时间
    private String userid;//用户编号
    private String delflag;//删除状态
    private String isshow;//是否显示 1显示 2不显示
    private String msjson;//消息JSON

    //get
    public String getMsid() {
        return msid;
    }

    public String getMscontent() {
        return mscontent;
    }

    public String getMstitle() {
        return mstitle;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public String getUserid() {
        return userid;
    }

    public String getDelflag() {
        return delflag;
    }

    public String getIsshow() {
        return isshow;
    }

    public String getMsjson() {
        return msjson;
    }

    //set
    public void setMsid(String msid) {
        this.msid = msid;
    }

    public void setMscontent(String mscontent) {
        this.mscontent = mscontent;
    }

    public void setMstitle(String mstitle) {
        this.mstitle = mstitle;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setDelflag(String delflaf) {
        this.delflag = delflaf;
    }

    public void setIsshow(String isshow) {
        this.isshow = isshow;
    }

    public void setMsjson(String msjson) {
        this.msjson = msjson;
    }
}