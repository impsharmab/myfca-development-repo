/**
 *
 */
package com.imperialm.imiservices.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

/**
 * @author Dheerajr
 *
 */
@Configuration
public class IMIServiceSecutiryConfig extends WebSecurityConfigurerAdapter {

	@Value("${rememberMe.PrivateKey}")
	private String rememberMeKey;
	
	@Resource
	private UserDetailsService userService;
	
	@Bean
	public RememberMeServices rememberMeServices(){
		TokenBasedRememberMeServices rememberMeService = new TokenBasedRememberMeServices(rememberMeKey, userService);
		return rememberMeService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/resources/**", "/login", "/services/**").permitAll().anyRequest()
				.authenticated().and().csrf().disable();
		http.formLogin().loginPage("/").permitAll().and()
		.rememberMe().key(rememberMeKey).rememberMeServices(rememberMeServices()).and()
		.logout().permitAll();
	}
	
	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws  Exception{
		authManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
}