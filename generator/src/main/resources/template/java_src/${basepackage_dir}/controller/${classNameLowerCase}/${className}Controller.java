<#include "/java_copyright.include">
<#assign sqlName = table.sqlName>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameAllLower = className?lower_case>
<#assign columns=table.columns>

package ${basepackage}.controller.${classNameAllLower};
import ${basepackage}.data.entity.${classNameAllLower}.${className}Entity;
import ${basepackage}.bo.service.${classNameAllLower}.${className}Service;
import com.liheng.shock.business.data.entity.user.UserInfoEntity;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.datatables.mehod.DataTablesFromPage;
import com.micolor.commoncore.datatables.mehod.Http2DataTables;
import com.micolor.commoncore.json.JSONUtil;
import com.micolor.commoncore.message.domain.MsgBean;
import com.micolor.commoncore.string.StringUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * Project:
 * Package:${basepackage}.controller.${classNameAllLower}
 *
 * @Author:
 * @Description:
 * @Date:${.now?date}
 * @Modified:
 */
@EnableAutoConfiguration
@Controller
public class ${className}Controller {
    private static Logger logger = LoggerFactory.getLogger(${className}Controller.class);
    @Autowired
    private ${className}Service ${classNameLower}Service;
    @Autowired
    HttpServletRequest request;

    @RequestMapping("/${namespace}/${classNameAllLower}/${classNameAllLower}listpage")
    public String ${classNameAllLower}ListPage(){
        return "/${namespace}/${classNameAllLower}/${classNameAllLower}list";
    }


    @RequestMapping("/${namespace}/${classNameAllLower}/${classNameAllLower}info")
    public String ${classNameLower}Info(@RequestParam String <#list table.getPkColumns() as y>${y.getColumnNameFirstLower()}</#list>, ModelMap mode){
        //获得当前登录用户信息
        UserInfoEntity userInfoEntity = (UserInfoEntity)request .getSession().getAttribute("userinfo");

        mode.addAttribute("<#list table.getPkColumns() as y>${y.getColumnNameFirstLower()}</#list>",<#list table.getPkColumns() as y>${y.getColumnNameFirstLower()} </#list>);
        if(StringUtil.isNotEmpty(<#list table.getPkColumns() as y> ${y.getColumnNameFirstLower()} </#list>)){
            ${className}Entity ${classNameLower}Entity = ${classNameLower}Service.get${className}(<#list table.getPkColumns() as y>${y.getColumnNameFirstLower()}</#list>);
            mode.addAttribute("${classNameLower}Entity",${classNameLower}Entity);
        }
        return "/${namespace}/${classNameAllLower}/${classNameAllLower}info";
    }

    @RequestMapping("/${namespace}/${classNameAllLower}/pagedata")
    @ResponseBody()
    public DataTables get${className}Data(){
        DataTables dataTables = null;
        List<${className}Entity> list = null;
        //从web请求中获得dataTables对象
        try{
            dataTables = Http2DataTables.getHttpDataTables();
        }catch (Exception e){
            logger.warn("参数异常{}",e.getMessage());
        }
        //从数据库获得数据
        if(dataTables!=null){
            list = ${classNameLower}Service.get${className}List(dataTables);
        }
        //将结果集再次封装成dataTables返回给前端
        return DataTablesFromPage.formatPage2DataTables(dataTables,list);
    }

    @RequestMapping("/${namespace}/${classNameAllLower}/alldata")
    @ResponseBody()
    public List getAll${className}Data(){
        return ${classNameLower}Service.getAll${className}List();
    }

    @RequestMapping(value="/${namespace}/${classNameAllLower}/save${classNameAllLower}")
    @ResponseBody()
    public MsgBean save${className}(@RequestBody String jsonInString){
        JSONObject object = JSONUtil.string2JSONObject(jsonInString);
        ${className}Entity ${classNameLower}Entity = (${className}Entity)JSONObject.toBean(object,${className}Entity.class);
        return ${classNameLower}Service.save${className}(${classNameLower}Entity);
    }

    @RequestMapping(value="/${namespace}/${classNameAllLower}/update${classNameAllLower}", method = RequestMethod.POST)
    @ResponseBody()
    public MsgBean update${className}(@RequestBody String jsonInString){
        JSONObject object = JSONUtil.string2JSONObject(jsonInString);
        ${className}Entity ${classNameLower}Entity = (${className}Entity)JSONObject.toBean(object,${className}Entity.class);
        return  ${classNameLower}Service.update${className}(${classNameLower}Entity);
    }

    @RequestMapping("/${namespace}/${classNameAllLower}/delete${classNameAllLower}")
    @ResponseBody
    public MsgBean delete${className}(String <#list table.getPkColumns() as y>${y.getColumnNameFirstLower()}</#list>s){
        String [] <#list table.getPkColumns() as y>${y.getColumnNameFirstLower()}</#list>spit =<#list table.getPkColumns() as y>${y.getColumnNameFirstLower()}</#list>s.split(",");
        ${className}Entity [] ${classNameLower}Entities = new ${className}Entity[<#list table.getPkColumns() as y>${y.getColumnNameFirstLower()}</#list>spit.length];
        for(int index = 0 ;index < <#list table.getPkColumns() as y>${y.getColumnNameFirstLower()}</#list>spit.length;index ++){
            String  <#list table.getPkColumns() as y>${y.getColumnNameFirstLower()}</#list> = <#list table.getPkColumns() as y>${y.getColumnNameFirstLower()}</#list>spit[index];
            ${className}Entity ${classNameLower}Entity = new ${className}Entity();
            ${classNameLower}Entity.set<#list table.getPkColumns() as y>${y.getColumnName()}</#list> (<#list table.getPkColumns() as y>${y.getColumnNameFirstLower()}</#list>);
            ${classNameLower}Entities[index] =${classNameLower}Entity;
        }
        return   ${classNameLower}Service.delete${className}(${classNameLower}Entities);
    }
}