/*
 * 上海骊蘅网络科技有限公司
 * Web Site: http://www.lihenginfo.com/
 */
package com.micolor.commoncore.communication.gexin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.ibatis.annotations.Mapper;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.business.data.dao.gexinconfig
 *
 * @Author:Ann
 * @Description: 短信配置表
 * @Date:2017-7-30
 * @Modified:
 */
public class GexinConfigEntity{
    private String appsecret;//APPSECRET
    private String appurl;//APPURL
    private String appkey;//AppKey
    private String mastersecret;//MasterSecret
    private String appid;//应用标识id
    private String gcid;//主键

    //get
    public String getAppsecret(){
        return appsecret;
    }
    public String getAppurl(){
        return appurl;
    }
    public String getAppkey(){
        return appkey;
    }
    public String getMastersecret(){
        return mastersecret;
    }
    public String getAppid(){
        return appid;
    }
    public String getGcid(){
        return gcid;
    }

    //set
    public void setAppsecret(String appsecret){
        this.appsecret=appsecret;
    }
    public void setAppurl(String appurl){
        this.appurl=appurl;
    }
    public void setAppkey(String appkey){
        this.appkey=appkey;
    }
    public void setMastersecret(String mastersecret){
        this.mastersecret=mastersecret;
    }
    public void setAppid(String appid){
        this.appid=appid;
    }
    public void setGcid(String gcid){
        this.gcid=gcid;
    }
}