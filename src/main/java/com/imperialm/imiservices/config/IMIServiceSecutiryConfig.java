/**
 *
 */
package com.imperialm.imiservices.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Dheerajr
 *
 */
@Configuration
public class IMIServiceSecutiryConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/resources/**","/login", "/services/**").permitAll().anyRequest().authenticated()
				.and().csrf().disable();
	}
}