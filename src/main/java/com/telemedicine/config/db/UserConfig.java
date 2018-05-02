package com.telemedicine.config.db;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

/*
@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(
        basePackages = {"com.telemedicine.user.repositoty"},
        entityManagerFactoryRef = "userEntityManager",
        transactionManagerRef = "userTransactionManager"
)
//@Profile("default")
*/
public class UserConfig  {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean userEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(userDataSource());
        em.setPackagesToScan(new String[] { "com.telemedicine.user.entity" });
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        //properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.hbm2ddl.auto", "update");
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
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName( env.getProperty("user.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("user.datasource.url"));
        dataSource.setUsername(env.getProperty("user.datasource.username"));
        dataSource.setPassword(env.getProperty("user.datasource.password"));
        return dataSource;
    }
}