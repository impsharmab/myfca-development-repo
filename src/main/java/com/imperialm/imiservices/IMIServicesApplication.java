package com.imperialm.imiservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Dheerajr
 *
 */

@SpringBootApplication
@EntityScan
@ComponentScan
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableCaching
@EnableScheduling
public class IMIServicesApplication {
	public static void main(final String[] args) {
		SpringApplication.run(IMIServicesApplication.class, args);
	}
}