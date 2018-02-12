
package com.jshop.cloud.orm;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(MultiTenancyJpaConfig.BASE_PACKAGES)
@EnableConfigurationProperties(JpaProperties.class)
@EnableJpaRepositories(
        entityManagerFactoryRef = "defaultEntityManager", 
        transactionManagerRef = "defaultTransactionManager",
        basePackages = { MultiTenancyJpaConfig.BASE_PACKAGES })
@EnableTransactionManagement
public class MultiTenancyJpaConfig
{

    @Autowired
    private Environment env;

    public static final String BASE_PACKAGES = "com.jshop";

    @Bean
    public JpaVendorAdapter jpaVendorAdapter()
    {
        return new HibernateJpaVendorAdapter();
    }

    @Bean(name = "defaultEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
    			DataSource dataSource, MultiTenantConnectionProvider connectionProvider, CurrentTenantIdentifierResolver tenantResolver)
    {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform(env.getProperty("db.dialect"));
        vendorAdapter.setDatabase(Database.valueOf(env.getProperty("db.jpa.vendor.database")));

        LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();
        emfBean.setJpaVendorAdapter(vendorAdapter);
        emfBean.setDataSource(dataSource);
        emfBean.setPackagesToScan(BASE_PACKAGES);
        emfBean.setJpaVendorAdapter(jpaVendorAdapter());

        Map<String, Object> properties = new HashMap<>();
        properties.put(org.hibernate.cfg.Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
        properties.put(org.hibernate.cfg.Environment.MULTI_TENANT_CONNECTION_PROVIDER, connectionProvider);
        properties.put(org.hibernate.cfg.Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantResolver);
        properties.put(org.hibernate.cfg.Environment.SHOW_SQL, env.getProperty("hibernate.show_sql"));

        emfBean.setJpaPropertyMap(properties);
        return emfBean;
    }

    @Bean(name = "defaultTransactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManager)
    {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManager);

        return transactionManager;
    }
}