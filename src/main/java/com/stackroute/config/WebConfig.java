package com.stackroute.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;



@Configuration
@ComponentScan(basePackages="com.stackroute")
@EnableWebMvc
@EnableTransactionManagement(proxyTargetClass=true)

public class WebConfig {

	@Bean
	public ViewResolver viewResolver() {
		return new InternalResourceViewResolver("/WEB-INF/", ".jsp");
	}
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/HibernateDB");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	
	@Bean 
	@Autowired
	public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("com.stackroute.model");
		Properties prop=new Properties();
		prop.setProperty("hibernate.show_sql","true");
		prop.setProperty("hibernate.hbm2ddl.auto", "create");
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		
		sessionFactory.setHibernateProperties(prop);
		return sessionFactory;
	}
	
	
	@Bean
	@Autowired
	public HibernateTransactionManager getTxnManager(SessionFactory sessionFactory) {
		
		HibernateTransactionManager txnManager=new HibernateTransactionManager();
		txnManager.setSessionFactory(sessionFactory);
		return txnManager;
		
	}
}
