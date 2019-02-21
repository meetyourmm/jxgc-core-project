package com.micolor.commoncore.database.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Project:root
 * Package:com.micolor.commoncore.database.typehandler
 *
 * @Author: Evangoe
 * @Description:
 * @Date:30/07/17
 * @Modified:
 */
public class NullValueHandler implements TypeHandler<String>{
    @Override
    public void setParameter(PreparedStatement pstmt, int index, String value, JdbcType jdbcType) throws SQLException {
        if(value == null && jdbcType == JdbcType.VARCHAR){//判断传入的参数值是否为null
            pstmt.setString(index,"");//设置当前参数的值为空字符串
        }else{
            pstmt.setString(index,value);//如果不为null，则直接设置参数的值为value
        }
    }

    @Override
    public String getResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getString(s);
    }

    @Override
    public String getResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString(i);
    }

    @Override
    public String getResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getString(i);
    }
}
