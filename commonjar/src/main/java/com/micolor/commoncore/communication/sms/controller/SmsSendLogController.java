package com.micolor.commoncore.communication.sms.controller;

import com.micolor.commoncore.communication.sms.service.SmsSendService;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.datatables.mehod.DataTablesFromPage;
import com.micolor.commoncore.datatables.mehod.Http2DataTables;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.communication.sms.controller
 *
 * @Author: Evangoe
 * @Description: 短信发送日志controller
 * @Date:18/06/17
 * @Modified:
 */
@EnableAutoConfiguration
@Controller
@ApiIgnore
public class SmsSendLogController {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(SmsSendLogController.class);
    @Autowired
    SmsSendService smsSendService;

    @RequestMapping("/system/sms/smslogpage")
    public String operatorLogListPage() {
        return "/system/sms/smslogpage";
    }

    @RequestMapping("/system/sms/smslogdata")
    @ResponseBody()
    public DataTables getOperatorLogData(){
        DataTables dataTables = null;
        List<Map> list = null;
        //从web请求中获得dataTables对象
        try{
            dataTables = Http2DataTables.getHttpDataTables();
        }catch (Exception e){
            logger.warn("参数异常{}",e.getMessage());
        }
        //从数据库获得数据
        if(dataTables!=null){
            list = smsSendService.getSmsSendLog(dataTables);
        }
        //将结果集再次封装成dataTables返回给前端
        return DataTablesFromPage.formatPage2DataTables(dataTables,list);
    }
}
