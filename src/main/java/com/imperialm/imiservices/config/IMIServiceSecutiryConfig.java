/**
 *
 */
package com.imperialm.imiservices.config;

import javax.annotation.Resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.imperialm.imiservices.security.JwtAuthenticationEntryPoint;
import com.imperialm.imiservices.security.JwtAuthenticationTokenFilter;
import com.imperialm.imiservices.security.JwtDaoAuthenticationProvider;
import com.imperialm.imiservices.services.UserService;
import com.imperialm.imiservices.services.UserServiceImpl;

/**
 * @author Dheerajr
 *
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.imperialm.imiservices")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class IMIServiceSecutiryConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
   
    @Autowired
    private AuthenticationEntryPoint unauthorizedHandler = new JwtAuthenticationEntryPoint();

    

   /* @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }*/
    
    @Bean @Qualifier("JwtAuthenticationTokenFilter")
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }
    
    
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userService)
                .passwordEncoder(passwordEncoder());
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider());
    }
	

	@Bean
	public AuthenticationProvider customAuthenticationProvider() {
		JwtDaoAuthenticationProvider impl = new JwtDaoAuthenticationProvider();
		impl.setUserDetailsService(userService);
		impl.setPasswordEncoder(passwordEncoder());
		return impl;
	}
	/*@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/resources/**", "/login", "/services/**").permitAll().anyRequest()
				.authenticated().and().csrf().disable();
		http.formLogin().loginPage("/").permitAll().and()
		.rememberMe().key(rememberMeKey).rememberMeServices(rememberMeServices()).and()
		.logout().permitAll();
	}*/
	
	
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()

                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeRequests().antMatchers("/*").authenticated()
                .and()
                .authorizeRequests()
                //.antMatchers("/", "/login", "imiservices/login/token", "imiservices/login/").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(authenticationTokenFilterBean(), BasicAuthenticationFilter.class);
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // allow anonymous resource requests
               
               /* .antMatchers(
                        HttpMethod.GET,
                        "/*"
                ).authenticated().and()*/

        // disable page caching
        httpSecurity.headers().cacheControl();
    }
	
	
	
}