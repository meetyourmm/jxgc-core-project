package com.micolor.commoncore.datatables.mehod;

import com.micolor.commoncore.datatables.domain.Columns;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.datatables.domain.Order;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.subject.WebSubject;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.datatables.mehod
 *
 * @Author: Evangoe
 * @Description: 从请求中获得dataTables对象
 * @Date:12/05/17
 * @Modified:
 */
public class Http2DataTables {
    private Http2DataTables(){}
    /**
     * 从请求中获得dataTables对象
     * @return 返回dataTables对象
     * @throws Exception
     */
    public static DataTables getHttpDataTables() {
        ServletRequest request = ((WebSubject) SecurityUtils.getSubject()).getServletRequest();
        DataTables dataTables;
        Map map = request.getParameterMap();
        //dtJson是前端请求中，自定的方法，请参见『webapp/resources/js/common/commmon.js中dataTables的ajax请求的data配置项
        String json = "";
        if(((String[]) map.get("dtJson")).length > 0 ){
            json =((String[]) map.get("dtJson"))[0];
        }else{
            json = "";
        }
        Map<String, Class> clsMap = new HashMap();
        clsMap.put("columns", Columns.class);
        clsMap.put("order", Order.class);
        dataTables = (DataTables) JSONObject.toBean(JSONObject.fromObject(json), DataTables.class, clsMap);
        return dataTables;
    }
}