package com.micolor.commoncore.datatables.mehod;


import com.micolor.commoncore.datatables.domain.Columns;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.datatables.domain.Order;
import com.micolor.commoncore.datatables.domain.Search;
import com.micolor.commoncore.string.StringUtil;

import java.util.List;

/**
 * FileName:com.micolor.common.method.datatables.DataTables4Hql.
 * Discription: 使用DataTable构建Hql的相关参数
 * Author: geyupeng.
 * DateTime 16/3/25.
 */
public class DataTables4Hql {
    /**
     * 根据dataTable的search封装参数
     * @param dataTables
     * @return
     */
    public static Object[] DataTables2Params(DataTables dataTables) {
        Search search = dataTables.getSearch();
        Object[] params = null;
        if (search.getValue() != null && !search.getValue().trim().equals("")) {
            String searchValue = search.getValue();

            List<Columns> columns = dataTables.getColumns();
            int size = columns.size();
            params = new Object[size];

            for (int index = 0; index < size; index++) {
                params[index] = searchValue;
            }
        }
        return params;
    }

    /**
     *
     * @param Hql 原始sql
     * @param dataTables dataTables对象
     * @return 带分页、排序、条件过滤的sql语句
     */
    public static String DataTables2Hql(String Hql, DataTables dataTables) {
        /**
         * 根据用户排序策略添加排序sql语句
         * */
        StringBuffer orderString = new StringBuffer();
        List<Columns> columns = dataTables.getColumns();
        /* if (Hql.indexOf("count(*)") == -1) {*/
            List<Order> orderList = dataTables.getOrder();
            for (Order order : orderList) {
                Columns column = columns.get(order.getColumn());
                if(StringUtil.isNotEmpty(column.getData())) {
                    orderString.append(column.getData() + " " + order.getDir() + ",");
                }
            }
            if (orderString.length() > 0) {
                orderString = new StringBuffer(" order by " + orderString.substring(0, orderString.length() - 1));
            }
        /*}*/

        /**
        * 设定查询参数
        * */
        StringBuffer paramString = new StringBuffer();
        //设定查询参数
        Search search = dataTables.getSearch();
        if (search.getValue() != null && !search.getValue().trim().equals("")){
            paramString.append(search.getValue());
        }
        /**
         * 当存在列有过滤时,添加对应的列
         * */
        for(Columns column : columns){
            Search searchcolumn = column.getSearch();
            if(!searchcolumn.getValue().equals("")) {
                paramString.append("and " + column.getData() + " = '"+searchcolumn.getValue().replaceAll("\"","")+"' ");
            }
        }

        if (paramString.length() > 0){
            paramString = new StringBuffer(" and "+paramString.substring(4, paramString.length()));
        }
        return Hql + paramString + orderString;
    }

    public static String DataTables2Sql(String Sql,DataTables dataTables){
        StringBuffer newSql = new StringBuffer();
        /**
         * 根据用户排序策略添加排序sql语句
         * */
        StringBuffer orderString = new StringBuffer();
        List<Columns> columns = dataTables.getColumns();
        if (Sql.indexOf("count(") == -1) {
            List<Order> orderList = dataTables.getOrder();
            for (Order order : orderList) {
                Columns column = columns.get(order.getColumn());
                orderString.append(column.getData() + " " + order.getDir() + ",");
            }
            if (orderString.length() > 0) {
                orderString = new StringBuffer(" order by " + orderString.substring(0, orderString.length() - 1));
            }
        }
        StringBuffer pageString = new StringBuffer();
        if (dataTables.getLength() != -1) {
            int start = dataTables.getStart();
            int length = dataTables.getLength();
            pageString.append(" limit " + start + "," + length);
        }

        /**
         * 设定查询参数
         * */
        StringBuffer paramString = new StringBuffer();
        //设定查询参数
        Search search = dataTables.getSearch();
        if (search.getValue() != null && !search.getValue().trim().equals("")){
            for (Columns column : columns) {
                paramString.append("and " + column.getData() + " = ? ");
            }
        }
        /**
         * 当存在列有过滤时,添加对应的列
         * */
        for(Columns column : columns){
            Search searchcolumn = column.getSearch();
            if(!searchcolumn.getValue().equals("")) {
                paramString.append("and " + column.getData() + " = '"+searchcolumn.getValue().replaceAll("\"","")+"' ");
            }
        }

        if (paramString.length() > 0){
            paramString = new StringBuffer(" where " + paramString.substring(4, paramString.length()));
        }

        return newSql.append(Sql).append(paramString).append(orderString).append(pageString).toString();
    }

    public static String DataTables2SqlCount(String Sql,DataTables dataTables){
        StringBuffer newSql = new StringBuffer();
        /**
         * 根据用户排序策略添加排序sql语句
         * */
        List<Columns> columns = dataTables.getColumns();
        /**
         * 设定查询参数
         * */
        StringBuffer paramString = new StringBuffer();
        //设定查询参数
        Search search = dataTables.getSearch();
        if (search.getValue() != null && !search.getValue().trim().equals("")){
            for (Columns column : columns) {
                paramString.append("and " + column.getData() + " = ? ");
            }
        }
        /**
         * 当存在列有过滤时,添加对应的列
         * */
        for(Columns column : columns){
            Search searchcolumn = column.getSearch();
            if(!searchcolumn.getValue().equals("")) {
                paramString.append("and " + column.getData() + " = '"+searchcolumn.getValue().replaceAll("\"","")+"' ");
            }
        }

        if (paramString.length() > 0){
            paramString = new StringBuffer(" where " + paramString.substring(4, paramString.length()));
        }

        return newSql.append(Sql).append(paramString).toString();
    }
}
