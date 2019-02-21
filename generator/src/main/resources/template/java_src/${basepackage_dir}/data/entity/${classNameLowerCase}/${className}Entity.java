<#include "/java_copyright.include">
<#assign sqlName=table.sqlName>
<#assign className=table.className>
<#assign columns=table.columns>
<#assign classNameLower=className?uncap_first>
<#assign classNameAllLower=className?lower_case>

package ${basepackage}.data.entity.${classNameAllLower};

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.ibatis.annotations.Mapper;

/**
 * Project:logistics-single
 * Package:${basepackage}.data.dao.${classNameAllLower}
 *
 * @Author:
 * @Description: ${table.getRemarks()}
 * @Date:${.now?date}
 * @Modified:
 */
@Mapper
@JsonIgnoreProperties(value = {"javassistProxyFactory", "handler"})
public class ${className}Entity{
<#list columns as x>
    private ${x.getJavaType()} ${x.getColumnNameFirstLower()};${r'//'}${x.getRemarks()}
</#list>

    //get
<#list columns as x>
    public ${x.getJavaType()} get${x.getColumnName()}(){
        return ${x.getColumnNameFirstLower()};
    }
</#list>

    //set
<#list columns as x>
    public void set${x.getColumnName()}(${x.getJavaType()} ${x.getColumnNameFirstLower()}){
        this.${x.getColumnNameFirstLower()}=${x.getColumnNameFirstLower()};
    }
</#list>
}