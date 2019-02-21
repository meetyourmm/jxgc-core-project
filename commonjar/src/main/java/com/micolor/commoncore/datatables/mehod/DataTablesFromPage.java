package com.micolor.commoncore.datatables.mehod;

import com.github.pagehelper.Page;
import com.micolor.commoncore.datatables.domain.DataTables;

import java.util.List;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.datatables.mehod
 *
 * @Author: Evangoe
 * @Description: 通过分页配置项重新配置dataTables.
 * @Date:17/05/17
 * @Modified:
 */
public class DataTablesFromPage {
    /**
     * 通过分页配置项重新配置dataTables.
     * @param dataTables 从request中获得的dataTables
     * @param list 从数据库获得结果集
     * @return 分装了分页结果的 dataTables
     */
    public static DataTables formatPage2DataTables(DataTables dataTables,List list){
        DataTables dt =  dataTables;
        dt.setData(list);//将获得结果集放到dataTables中
        Page page = (Page) list;//从List中获得页面的相关信息
        dt.setRecordsFiltered(Integer.parseInt(String.valueOf(page.getTotal())));//设置dataTables中过滤结果集个数（这个不知道有啥用）
        dt.setRecordsTotal(Integer.parseInt(String.valueOf(page.getTotal())));//设置dataTables中的结果集总条数
        return dt;
    }
}