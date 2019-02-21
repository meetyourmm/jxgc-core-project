package com.micolor.commoncore.communication.mail.controller;

import com.micolor.commoncore.communication.mail.service.MailSendService;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.datatables.mehod.DataTablesFromPage;
import com.micolor.commoncore.datatables.mehod.Http2DataTables;
import com.micolor.commoncore.logs.controller.OperatorLogController;
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
 * Package:com.micolor.commoncore.communication.mail.controller
 *
 * @Author: Evangoe
 * @Description:
 * @Date:17/06/17
 * @Modified:
 */
@EnableAutoConfiguration
@Controller
@ApiIgnore
public class MailSendLogController {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(OperatorLogController.class);
    @Autowired
    MailSendService mailSendService;

    @RequestMapping("/system/mail/maillogpage")
    public String operatorLogListPage() {
        return "/system/mail/maillogpage";
    }

    @RequestMapping("/system/mail/maillogdata")
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
            list = mailSendService.getMailSendLog(dataTables);
        }
        //将结果集再次封装成dataTables返回给前端
        return DataTablesFromPage.formatPage2DataTables(dataTables,list);
    }
}
