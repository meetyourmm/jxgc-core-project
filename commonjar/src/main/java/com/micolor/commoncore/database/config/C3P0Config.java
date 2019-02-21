package com.micolor.commoncore.database.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
//@Configuration
//@EnableAutoConfiguration
public class C3P0Config {
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        return sessionFactory.getObject();
    }

    @Bean(name="dataSource")
    @Qualifier(value="dataSource")
    @Primary
    @ConfigurationProperties(prefix = "c3p0")
    public DataSourceProperties primaryDataSourceProperties(){
        return new DataSourceProperties();
    }


    @Bean(name="dataSource")
    @Qualifier(value="dataSource")
    @Primary
    @ConfigurationProperties(prefix="c3p0")
    public DataSource dataSource(){
        return primaryDataSourceProperties().initializeDataSourceBuilder().build();
    }
}
