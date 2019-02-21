package com.micolor.commoncore.logs.controller;

import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.datatables.mehod.DataTablesFromPage;
import com.micolor.commoncore.datatables.mehod.Http2DataTables;
import com.micolor.commoncore.logs.domain.OperatorLogEntity;
import com.micolor.commoncore.logs.service.OptLogService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.logs.controller
 *
 * @Author: Evangoe
 * @Description:
 * @Date:17/06/17
 * @Modified:
 */
@EnableAutoConfiguration
@Controller
@ApiIgnore
public class OperatorLogController {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(OperatorLogController.class);
    @Autowired
    OptLogService optLogService;

    @RequestMapping("/system/operatorlog/operatorlogpage")
    public String operatorLogListPage() {
        return "/system/operatorlog/operatorlogpage";
    }

    @RequestMapping("/system/operatorlog/operatorlogdata")
    @ResponseBody()
    public DataTables getOperatorLogData(){
        DataTables dataTables = null;
        List<OperatorLogEntity> list = null;
        //从web请求中获得dataTables对象
        try{
            dataTables = Http2DataTables.getHttpDataTables();
        }catch (Exception e){
            logger.warn("参数异常{}",e.getMessage());
        }
        //从数据库获得数据
        if(dataTables!=null){
            list = optLogService.getOperatorLog(dataTables);
        }
        //将结果集再次封装成dataTables返回给前端
        return DataTablesFromPage.formatPage2DataTables(dataTables,list);
    }
}
