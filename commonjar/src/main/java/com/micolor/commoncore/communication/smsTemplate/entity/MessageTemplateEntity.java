/*
 * 上海骊蘅网络科技有限公司
 * Web Site: http://www.lihenginfo.com/
 */
package com.micolor.commoncore.communication.smsTemplate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.ibatis.annotations.Mapper;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.business.data.dao.messagetemplate
 *
 * @Author:Ann
 * @Description: 信息模版配置表
 * @Date:2017-9-29
 * @Modified:
 */
@Mapper
@JsonIgnoreProperties(value = {"javassistProxyFactory", "handler"})
public class MessageTemplateEntity {
    private String mtid;//主键
    private String mtemplate;//短信模版
    private String remark;//备注

    //get
    public String getMtid() {
        return mtid;
    }

    public String getMtemplate() {
        return mtemplate;
    }

    public String getRemark() {
        return remark;
    }

    //set
    public void setMtid(String mtid) {
        this.mtid = mtid;
    }

    public void setMtemplate(String mtemplate) {
        this.mtemplate = mtemplate;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}