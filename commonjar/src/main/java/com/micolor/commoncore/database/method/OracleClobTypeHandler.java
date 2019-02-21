package com.micolor.commoncore.database.method;

import oracle.sql.CLOB;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.database.method
 *
 * @Author: Evangoe
 * @Description:
 * @Date:2/7/17
 * @Modified:
 */
public class OracleClobTypeHandler extends BaseTypeHandler<Object> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }

    public Object valueOf(String param) {
        return null;
    }

    @Override
    public Object getResult(ResultSet arg0, String arg1) throws SQLException {
        CLOB clob = (CLOB) arg0.getClob(arg1);
        return (clob == null || clob.length() == 0) ? null : clob.getSubString((long) 1, (int) clob.length());
    }

    @Override
    public Object getResult(ResultSet arg0, int arg1) throws SQLException {
        return null;
    }

    @Override
    public Object getResult(CallableStatement arg0, int arg1) throws SQLException {
        return null;
    }

    @Override
    public void setParameter(PreparedStatement arg0, int arg1, Object arg2, JdbcType arg3) throws SQLException {
        CLOB clob = CLOB.getEmptyCLOB();
        clob.setString(1, (String) arg2);
        arg0.setClob(arg1, clob);
    }
}
