package com.imperialm.imiservices.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@PropertySource(value = { "classpath:application.properties"})
@EnableJpaRepositories(
	    entityManagerFactoryRef = "MSEREntityManager", 
	    transactionManagerRef = "MSERTransactionManager",
	    		basePackages = {"com.imperialm.imiservices.mser.dao"}
	)
public class MSERJpaConfiguration {

	@Autowired
	private Environment environment;

	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(this.environment.getRequiredProperty("spring.datasource.driverClassName"));
		dataSource.setUrl(this.environment.getRequiredProperty("spring.datasource.mser.url"));
		dataSource.setUsername(this.environment.getRequiredProperty("spring.datasource.username"));
		dataSource.setPassword(this.environment.getRequiredProperty("spring.datasource.password"));
		dataSource.setCatalog(this.environment.getRequiredProperty("spring.datasource.mser.db"));
		return dataSource;
	}
	
	@Bean(name="MSEREntityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
		final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(this.dataSource());
		factoryBean.setPackagesToScan(
				new String[] { "com.imperialm.imiservices.mser.dao"});
		factoryBean.setJpaVendorAdapter(this.jpaVendorAdapter());
		factoryBean.setJpaProperties(this.jpaProperties());
		return factoryBean;
	}

	
	public JpaVendorAdapter jpaVendorAdapter() {
		final HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		return hibernateJpaVendorAdapter;
	}


	private Properties jpaProperties() {
		final Properties properties = new Properties();
		properties.put("hibernate.show_sql", this.environment.getRequiredProperty("spring.jpa.show-sql"));
		properties.put("hibernate.dialect", this.environment.getRequiredProperty("spring.jpa.hibernate.dialect"));
		return properties;
	}

	@Bean(name="MSERTransactionManager")
	public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
		final JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}
	

}
