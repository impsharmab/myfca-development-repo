package com.imperialm.imiservices.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
@EnableJpaRepositories(
	    		basePackages = {"com.imperialm.imiservices.ttta.dao","com.imperialm.imiservices.dto", "com.imperialm.imiservices.repositories" , "com.imperialm.imiservices.entities", "com.imperialm.imiservices.services", "com.imperialm.imiservices.dto"}
	)
public class JpaConfiguration {

	@Autowired
	private Environment environment;

	@Primary
	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(this.environment.getRequiredProperty("spring.datasource.driverClassName"));
		dataSource.setUrl(this.environment.getRequiredProperty("spring.datasource.url"));
		dataSource.setUsername(this.environment.getRequiredProperty("spring.datasource.username"));
		dataSource.setPassword(this.environment.getRequiredProperty("spring.datasource.password"));
		return dataSource;
	}

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
		final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(this.dataSource());
		factoryBean.setPackagesToScan(
				new String[] { "com.imperialm.imiservices.model", "com.imperialm.imiservices.model.response", "com.imperialm.imiservices.dto", "com.imperialm.imiservices.repositories" , "com.imperialm.imiservices.entities", "com.imperialm.imiservices.services" });
		factoryBean.setJpaVendorAdapter(this.jpaVendorAdapter());
		factoryBean.setJpaProperties(this.jpaProperties());
		return factoryBean;
	}

	/*
	 * Provider specific adapter.
	 */
	//@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		final HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		return hibernateJpaVendorAdapter;
	}

	/*
	 * Here you can specify any provider specific properties.
	 */
	private Properties jpaProperties() {
		final Properties properties = new Properties();
		// properties.put("hibernate.hbm2ddl.auto",
		// environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.show_sql", this.environment.getRequiredProperty("spring.jpa.show-sql"));
		properties.put("hibernate.dialect", this.environment.getRequiredProperty("spring.jpa.hibernate.dialect"));
		return properties;
	}

	@Primary
	@Bean
	public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
		final JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}
	
	
	
}
