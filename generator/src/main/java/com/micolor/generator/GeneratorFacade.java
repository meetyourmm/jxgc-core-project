package com.micolor.generator;



import com.micolor.generator.provider.db.DbTableFactory;
import com.micolor.generator.provider.db.DbTableGeneratorModelProvider;
import com.micolor.generator.provider.db.model.Table;
import com.micolor.generator.provider.java.JavaClassGeneratorModelProvider;
import com.micolor.generator.provider.java.model.JavaClass;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * 
 * @author badqiu
 *
 */
public class GeneratorFacade {
	
	
	public GeneratorFacade() {
	}
	
	public void printAllTableNames() throws Exception {
		List tables = DbTableFactory.getInstance().getAllTables();
		System.out.println("\n----All TableNames BEGIN----");
		for(int i = 0; i < tables.size(); i++ ) {
			String sqlName = ((Table)tables.get(i)).getSqlName();
			System.out.println("g.generateTable(\""+sqlName+"\");");
		}
		System.out.println("----All TableNames END----");
	}
	
	public void generateByAllTable(String trd,String outRoot) throws Exception {
		List<Table> tables = DbTableFactory.getInstance().getAllTables();
		for(int i = 0; i < tables.size(); i++ ) {
			generateByTable(tables.get(i).getSqlName(), trd,outRoot);
		}
	}
	//
	public void generateByTable(String tableName,String trd,String outRoot) throws Exception {
		Generator g = createGeneratorForDbTable(trd,outRoot);
		Table table = DbTableFactory.getInstance().getTable(tableName);
		g.generateByModelProvider(new DbTableGeneratorModelProvider(table));
	}
	
	public void generateByTable(String tableName,String className,String trd,String outRoot) throws Exception {
		Generator g = createGeneratorForDbTable(trd,outRoot);
		Table table = DbTableFactory.getInstance().getTable(tableName);
		table.setClassName(className);
		g.generateByModelProvider(new DbTableGeneratorModelProvider(table));
	}
	
	public void generateByClass(Class clazz) throws Exception {
		Generator g = createGeneratorForJavaClass();
		g.generateByModelProvider(new JavaClassGeneratorModelProvider(new JavaClass(clazz)));
	}
	
	public void clean(String trd ,String outRoot) throws IOException {
		Generator g = createGeneratorForDbTable(trd,outRoot);
		g.clean();
	}
	
	public Generator createGeneratorForDbTable(String trd ,String outRoot) {
		Generator g = new Generator();
		//String trd = GeneratorProperties.getRequiredProperty("templateRootDir");
		if(trd==null || trd.equals("")){
			trd = "/src/main/resources/template";
		}
		g.setTemplateRootDir(new File(trd).getAbsoluteFile());
		g.setOutRootDir(outRoot);
		return g;
	}
	
	private Generator createGeneratorForJavaClass() {
		Generator g = new Generator();
		g.setTemplateRootDir(new File("template/javaclass").getAbsoluteFile());
		g.setOutRootDir(GeneratorProperties.getRequiredProperty("outRoot"));
		return g;
	}
}
