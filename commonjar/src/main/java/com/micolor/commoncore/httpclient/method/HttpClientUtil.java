package com.micolor.commoncore.httpclient.method;

import com.micolor.commoncore.httpclient.entity.HttpCustomEntity;
import com.micolor.commoncore.httpclient.entity.InterfaceLogEntity;
import com.micolor.commoncore.httpclient.service.InterfaceLogService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;


/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.httpclient
 *
 * @Author: Evangoe
 * @Description:
 * @Date:27/6/17
 * @Modified:
 */
@Component
public class HttpClientUtil {
    @Autowired
    private InterfaceLogService interfaceLogService;
    private static HttpClientUtil httpClientUtil;
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    @PostConstruct
    public void init() {
        httpClientUtil = this;
        httpClientUtil.interfaceLogService = this.interfaceLogService;
    }
    public static String sendHttpPostRequest(HttpCustomEntity httpCustomEntity){
        InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();

        logger.info("开始请求url {}",httpCustomEntity.getUri());
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(httpCustomEntity.getUri());
        interfaceLogEntity.setSendUrl(httpCustomEntity.getUri());
        String line = "";
        StringEntity stringEntity = new StringEntity(httpCustomEntity.getStringEntity(),"UTF-8");
        interfaceLogEntity.setSendParam(httpCustomEntity.getStringEntity());
        stringEntity.setContentType("application/json");
        stringEntity.setContentEncoding("UTF-8");
        httpPost.setEntity(stringEntity);

        CloseableHttpResponse response2 = null;
        try {
            response2 = httpclient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            line =EntityUtils.toString(entity2, "UTF-8");
        } catch (Exception e) {
            logger.error("执行HTTP POST请求方法错误，具体原因： {}",e.getMessage());
            e.printStackTrace();
        } finally {
            try{
                response2.close();
            }catch (Exception e){
                logger.error("执行HTTP POST请求方法错误，具体原因： {}",e.getMessage());
                e.printStackTrace();
            }
        }
        logger.info("结束请求，结果如下：{}",line);

        interfaceLogEntity.setSendType("POST,application/json");
        interfaceLogEntity.setSendResult(line);
        interfaceLogEntity.setLogTime(new Date());
        httpClientUtil.interfaceLogService.saveInterfaceLog(interfaceLogEntity);
        return line;
    }

    public static String sendHttpGetRequest(HttpCustomEntity httpCustomEntity){
        InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
        logger.info("开始请求url {}",httpCustomEntity.getUri());
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(httpCustomEntity.getUri());
        interfaceLogEntity.setSendUrl(httpCustomEntity.getUri());
        String line = "";
        interfaceLogEntity.setSendParam("");
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            line =EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            logger.error("执行HTTP GET请求方法错误，具体原因： {}",e.getMessage());
            e.printStackTrace();
        } finally {
            try{
                response.close();
            }catch (Exception e){
                logger.error("执行HTTP GET请求方法错误，具体原因： {}",e.getMessage());
                e.printStackTrace();
            }
        }
        logger.info("结束请求，结果如下：{}",line);
        interfaceLogEntity.setSendType("GET");
        interfaceLogEntity.setSendResult(line);
        interfaceLogEntity.setLogTime(new Date());
        httpClientUtil.interfaceLogService.saveInterfaceLog(interfaceLogEntity);
        return line;
    }
    public void setInterfaceLogService(InterfaceLogService interfaceLogService) {
        this.interfaceLogService = interfaceLogService;
    }
}