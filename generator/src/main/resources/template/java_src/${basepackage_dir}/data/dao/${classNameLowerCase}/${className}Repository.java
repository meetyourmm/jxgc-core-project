

<#include "/java_copyright.include">
<#assign sqlName = table.sqlName>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameAllLower = className?lower_case>
<#assign columns=table.columns>
package ${basepackage}.data.dao.${classNameAllLower};
import ${basepackage}.data.entity.${classNameAllLower}.${className}Entity;
import com.micolor.commoncore.database.provider.SqlProvider;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.statics.StringStatics;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;
/**
 * Project:logistics-single
 * Package:${basepackage}.data.dao.${classNameAllLower}
 *
 * @Author:
 * @Description:
 * @Date:${.now?date}
 * @Modified:
 */
@Mapper
@Repository
public interface ${className}Repository {

    @Select("select * from ${sqlName} where 1=1 ")
    List<Map> getAll${className}List();

    @SelectProvider(type=SqlProvider.class,method = "selectListForCustom")
    List<${className}Entity> get${className}PageData(@Param("sql") String sql, @Param("dataTables") DataTables dataTables);

    <#list table.getPkColumns() as y>
    @Select("select * from ${sqlName} where ${y.getColumnNameFirstLower()} =  ${r'#{Id}'}")
    </#list>
    ${className}Entity get${className}Info(String Id);

    <#list table.getPkColumns() as y>
    @Select("select * from ${sqlName} where ${y.getColumnNameFirstLower()} =  ${r'#{Id}'}")
    </#list>
    Map get${className}Info4Map(String Id);

    <#list table.getPkColumns() as y>
    @Delete("delete from ${sqlName}   where ${y.getColumnNameFirstLower()} = ${r'#{Id}'}")
    </#list>
    void delete${className}(String Id);

    @Insert("insert into ${sqlName} (" +
            <#list columns as x>
                "${x.getColumnName()} <#if x_index < columns?size-1>,</#if>"+ ${r'//'}${x.getRemarks()}
            </#list>
            ") values (" +
            <#list columns as x>
                "${r'#{'}${x.getColumnNameFirstLower()},jdbcType=${x.getJdbcSqlTypeName()}}<#if x_index < columns?size-1>,</#if>"+ ${r'//'}${x.getRemarks()}
            </#list>")")
    <#list table.getPkColumns() as y>
    @SelectKey(keyProperty = "${y.getColumnNameFirstLower()}",resultType = ${y.getJavaType()}.class,before = true,statement = StringStatics.GENENRTEFROMDB)
    </#list>
    void save${className}(${className}Entity ${classNameLower}Entity);

    @Update("update ${sqlName} set "+
            <#list columns as x>
            "${x.getColumnName()} =${r'#{'}${x.getColumnNameFirstLower()},jdbcType=${x.getJdbcSqlTypeName()}} <#if x_index < columns?size-1>,</#if>"+ ${r'//'}${x.getRemarks()}
            </#list>
            " where <#list table.getPkColumns() as y> ${y.getColumnNameFirstLower()} =${r'#{'}${y.getColumnNameFirstLower()},jdbcType=${y.getJdbcSqlTypeName()}}" </#list>
    )
    void update${className}(${className}Entity ${classNameLower}Entity);

}