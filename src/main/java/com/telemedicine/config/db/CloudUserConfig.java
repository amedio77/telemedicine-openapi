package com.telemedicine.config.db;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.config.java.ServiceScan;
import org.springframework.cloud.service.PooledServiceConnectorConfig;
import org.springframework.cloud.service.relational.DataSourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@ServiceScan
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(
        basePackages = {"com.telemedicine.user.repositoty"},
        entityManagerFactoryRef = "userEntityManager",
        transactionManagerRef = "userTransactionManager"
)
@Profile("cloud")
public class CloudUserConfig extends AbstractCloudConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean userEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(userDataSource());
        em.setPackagesToScan(new String[]{"com.telemedicine.user.entity"});
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        //properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public PlatformTransactionManager userTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(userEntityManager().getObject());
        return transactionManager;
    }

    @Bean
    public DataSource userDataSource() {
        //PooledServiceConnectorConfig.PoolConfig poolConfig = new PooledServiceConnectorConfig.PoolConfig(5, 30, 3000);
        //DataSourceConfig.ConnectionConfig connConfig = new DataSourceConfig.ConnectionConfig("useUnicode=yes;characterEncoding=UTF-8");
        //DataSourceConfig dbConfig = new DataSourceConfig(poolConfig, null);
        //return connectionFactory().dataSource("my-spring-db",dbConfig);
        //return connectionFactory().dataSource("my-spring-db");
        return connectionFactory().dataSource();
    }
}
