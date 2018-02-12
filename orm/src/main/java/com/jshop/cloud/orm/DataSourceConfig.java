package com.jshop.cloud.orm;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.mchange.v2.c3p0.ComboPooledDataSource;


@Configuration
public class DataSourceConfig
{
    @Autowired
    private Environment env;

    @Bean(name = "dataSource")
    public DataSource dataSource() throws Exception
    {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setJdbcUrl(env.getProperty("db.url"));
        dataSource.setDriverClass(env.getProperty("db.driver.class"));
        dataSource.setUser(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        dataSource.setAcquireIncrement(Integer.parseInt(env.getProperty("db.pool.acquireIncrement")));
        dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("db.pool.minPoolSize")));
        dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("db.pool.maxPoolSize")));
        dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("db.pool.maxIdleTime")));

        return dataSource;
    }
}
