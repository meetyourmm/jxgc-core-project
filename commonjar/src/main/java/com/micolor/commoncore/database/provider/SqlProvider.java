package com.micolor.commoncore.database.provider;

import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.datatables.mehod.DataTables4Hql;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.database.provider
 *
 * @Author: Evangoe
 * @Description: 在使用SqlProvider时，构建通用sql语句
 * @Date:17/05/17
 * @Modified:
 */
public class SqlProvider {
    /**
     * 构建通用的带分页、排序、条件过滤的sql语句
     *
     * @param param 需要使用到的参数，param[0]是sql语句，param[1]是datatables对象
     * @return 带分页、排序、条件过滤的sql语句
     */
    public String selectListForDataTable(Map<String, Object> param) {
        DataTables dataTables = (DataTables) param.get( "dataTables" );
        String baseSql = String.valueOf( param.get( "sql" ) );
        String sql = DataTables4Hql.DataTables2Hql( baseSql, dataTables );
        return sql;

    }

    public String selectListForCustom(Map<String, Object> param) {
        return String.valueOf( param.get( "sql" ) );
    }

}
