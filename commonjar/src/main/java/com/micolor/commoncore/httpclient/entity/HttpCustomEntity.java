package com.micolor.commoncore.httpclient.entity;

import java.util.List;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.httpclient
 *
 * @Author: Evangoe
 * @Description: http请求记录信息
 * @Date:27/6/17
 * @Modified:
 */
public class HttpCustomEntity {
    private String uri; //请求地址
    private List<Map> urlParams; //列表请求参数
    private String stringEntity; //字符串请求参数

    public HttpCustomEntity(String uri) {
        this.uri = uri;
    }

    public HttpCustomEntity() {
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<Map> getUrlParams() {
        return urlParams;
    }

    public void setUrlParams(List<Map> urlParams) {
        this.urlParams = urlParams;
    }

    public String getStringEntity() {
        return stringEntity;
    }

    public void setStringEntity(String stringEntity) {
        this.stringEntity = stringEntity;
    }
}
