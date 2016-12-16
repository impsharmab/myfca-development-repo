/**
 *
 */
package com.imperialm.imiservices.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author Dheerajr
 *
 */
@Configuration
@EnableWebSecurity
public class IMIServiceSecutiryConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
//				.antMatchers("/services/**", "/app/**", "/*").permitAll().anyRequest().fullyAuthenticated().and()
//				.httpBasic().and().csrf().disable();
//	}

	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).
	and().authorizeRequests().antMatchers().permitAll().anyRequest().
	fullyAuthenticated().and().httpBasic().and().csrf().disable();
	}
	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}
}