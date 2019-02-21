package com.micolor.commoncore.database.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


/**
 * @Title: root
 * @Package com.micolor.commoncore.database.config
 * @Author: GeYupeng
 * @Date: 13:55 2018/8/5
 * @Modified:
 * @Description: TODO 填写描述。
 */
@Configuration
public class TransactionManagerConfig {
    @Bean
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
