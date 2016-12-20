/**
*
*/
package com.imperialm.imiservices.config;
<<<<<<< HEAD
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
=======

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

>>>>>>> 34df6e780eea6adacd0b47bf48997ecbaafd159b
/**
* @author Dheerajr
*
*/
@Configuration
public class IMIServiceSecutiryConfig extends WebSecurityConfigurerAdapter {
<<<<<<< HEAD
@Override
protected void configure(HttpSecurity http) throws Exception {
http.authorizeRequests().antMatchers("/login", "/resources/**", "/services/**").permitAll().anyRequest().authenticated()
.and().csrf().disable();
}
}
=======

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/resources/**","/login", "/services/**").permitAll().anyRequest().authenticated()
				.and().csrf().disable();
	}
}
>>>>>>> 34df6e780eea6adacd0b47bf48997ecbaafd159b
