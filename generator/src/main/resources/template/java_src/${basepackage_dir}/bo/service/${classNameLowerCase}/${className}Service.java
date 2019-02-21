<#include "/java_copyright.include">
		<#assign sqlName = table.sqlName>
		<#assign className = table.className>
		<#assign classNameLower = className?uncap_first>
		<#assign classNameAllLower = className?lower_case>
		package ${basepackage}.bo.impl.${classNameAllLower};

import ${basepackage}.data.dao.${classNameAllLower}.${className}Repository;
import ${basepackage}.bo.service.${classNameAllLower}.${className}Service;
import ${basepackage}.data.entity.${classNameAllLower}.${className}Entity;
import com.github.pagehelper.PageHelper;
import com.micolor.commoncore.database.method.SqlUtil;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.message.domain.MsgBean;
import com.micolor.commoncore.statics.EnumUtil;
import com.micolor.commoncore.statics.StringStatics;
import com.micolor.commoncore.string.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * Project:logistics-single
 * Package:${basepackage}.bo.impl.${classNameAllLower}
 *
 * @Author:
 * @Description:
 * @Date:${.now?date}
 * @Modified:
 */
@Service("${classNameLower}Service")
public class ${className}Service {

		private static Logger logger = LoggerFactory.getLogger(${className}Impl.class);

		@Autowired
		private ${className}Repository ${classNameLower}Repository;

		@Override
		public List<Map> getAll${className}List(){
		return ${classNameLower}Repository.getAll${className}List();
		}
		@Override
		public List<${className}Entity> get${className}List(DataTables dataTables){
				String sql = "select * from ${sqlName} where 1=1";
				int pageNum = dataTables.getcurrPage();
				int pageSize= dataTables.getLength();
				PageHelper.startPage(pageNum, pageSize);
				return ${classNameLower}Repository.get${className}PageData(sql,dataTables);
				}
		@Override
		public ${className}Entity get${className}(String Id){
			return ${classNameLower}Repository.get${className}Info(Id);
		}
		@Override
		public Map get${className}4Map(String Id){
			return ${classNameLower}Repository.get${className}Info4Map(Id);
		}
		public MsgBean delete${className}(${className}Entity[] ${classNameLower}Entities){
			MsgBean msgBean = new MsgBean();
			try{
				for(${className}Entity ${classNameLower}Entity : ${classNameLower}Entities){
				${classNameLower}Repository.delete${className}(${classNameLower}Entity.get<#list table.getPkColumns() as y>${y.getColumnName()}()</#list>);
			}
				msgBean.setMsg(StringStatics.OPERATIONOK);
				msgBean.setStatus(EnumUtil.MessageStatus.OK);
			}catch (Exception e){
				msgBean.setMsg(StringStatics.OPERATIONERROR+",原因："+e.getMessage());
				msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
				logger.error(StringStatics.INNERERROR,e);
			}
		return msgBean;
		}
		@Override
		public MsgBean save${className}(${className}Entity ${classNameLower}Entity){
			MsgBean msgBean = new MsgBean();
			try{
				${classNameLower}Repository.save${className}(${classNameLower}Entity);
				msgBean.setContent(${classNameLower}Entity);
				msgBean.setMsg("操作成功！");
				msgBean.setStatus(EnumUtil.MessageStatus.OK);
			}catch (Exception e){
				msgBean.setMsg(StringStatics.OPERATIONERROR+",原因："+e.getMessage());
				msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
				logger.error("内部异常：{}",e.getMessage());
			}
			return msgBean;
		}
		@Override
		public MsgBean update${className}(${className}Entity ${classNameLower}Entity){
			MsgBean msgBean = new MsgBean();
			try{
				${classNameLower}Repository.update${className}(${classNameLower}Entity);
				msgBean.setContent(${classNameLower}Entity);
				msgBean.setMsg("操作成功！");
				msgBean.setStatus(EnumUtil.MessageStatus.OK);
			}catch (Exception e){
				msgBean.setMsg(StringStatics.OPERATIONERROR+",原因："+e.getMessage());
				msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
				logger.error("内部异常：{}",e.getMessage());
			}
			return msgBean;
		}
}
