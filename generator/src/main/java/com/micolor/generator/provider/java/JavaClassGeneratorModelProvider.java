package com.micolor.generator.provider.java;

import java.util.Map;

import com.micolor.generator.IGeneratorModelProvider;
import com.micolor.generator.provider.java.model.JavaClass;
import org.apache.commons.beanutils.BeanUtils;


public class JavaClassGeneratorModelProvider implements IGeneratorModelProvider {
	JavaClass clazz;
	
	public JavaClassGeneratorModelProvider(JavaClass clazz) {
		super();
		this.clazz = clazz;
	}

	public String getDisaplyText() {
		return "JavaClass:"+clazz.getClassName();
	}

	public void mergeFilePathModel(Map model) throws Exception {
		model.putAll(BeanUtils.describe(clazz));
	}

	public void mergeTemplateModel(Map model) throws Exception {
		model.put("clazz",clazz);
	}

}
