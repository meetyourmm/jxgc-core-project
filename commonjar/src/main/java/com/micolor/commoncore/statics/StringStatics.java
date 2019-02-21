package com.micolor.commoncore.statics;

import com.micolor.commoncore.idgenerator.UUIDGenerator;

import java.util.Date;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.statics
 *
 * @Author: Evangoe
 * @Description:
 * @Date:29/05/17
 * @Modified:
 */
public class StringStatics {
    //工具类中不该存在public的函数构造器，故在此需要定义一个private的默认构造器

    public static final String OPERATIONOK = "操作成功！";
    public static final String OPERATIONERROR = "操作失败！";
    public static final String INNERERROR = "内部异常";


    public static final String POINT = ".";
    public static final String SLASH = "/";
    public static final String SLASH_SLASH = "//";
    public static final String BACKSLASH ="\\";
    public static final String SINGLEQUOTES ="'";

    //public static final String GENENRTEFROMDB = " select sys_guid() from dual ";
    public static final String GENENRTEFROMDB = " SELECT UPPER(REPLACE(UUID(),'-','')) ";
    private StringStatics() {}
}
