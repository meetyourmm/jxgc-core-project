/*
 * 上海骊蘅网络科技有限公司
 * Web Site: http://www.lihenginfo.com/
 */
package com.micolor.commoncore.communication.gexin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.business.data.dao.gexinsendlog
 *
 * @Author:Ann
 * @Description: 个推推送记录表
 * @Date:2017-7-30
 * @Modified:
 */
public class GexinSendLogEntity{
    private String gslid;//推送记录编号
    private String target;//发送对象
    private Date gxdate;//发送时间
    private String gxcontent;//发送内容
    private String gxstatus;//发送状态（1成功2失败）
    private String gxtitle;//标题
    //get
    public String getGslid(){
        return gslid;
    }
    public String getTarget(){
        return target;
    }
    public Date getGxdate(){
        return gxdate;
    }
    public String getGxcontent(){
        return gxcontent;
    }
    public String getGxstatus(){
        return gxstatus;
    }
    public String getGxtitle() {return gxtitle;}

    //set
    public void setGslid(String gslid){
        this.gslid=gslid;
    }
    public void setTarget(String target){
        this.target=target;
    }
    public void setGxdate(Date gxdate) {this.gxdate = gxdate;}
    public void setGxcontent(String gxcontent){
        this.gxcontent=gxcontent;
    }
    public void setGxstatus(String gxstatus){
        this.gxstatus=gxstatus;
    }
    public void setGxtitle(String gxtitle) {this.gxtitle = gxtitle;}
}