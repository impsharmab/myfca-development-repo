package com.imperialm.imiservices.config;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.common.cache.CacheBuilder;
import com.imperialm.imiservices.security.JwtAuthenticationEntryPoint;
import com.imperialm.imiservices.security.JwtAuthenticationTokenFilter;
import com.imperialm.imiservices.security.JwtDaoAuthenticationProvider;
import com.imperialm.imiservices.services.UserServiceImpl;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.imperialm.imiservices")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class IMIServiceSecutiryConfig extends WebSecurityConfigurerAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(IMIServiceSecutiryConfig.class);
	
	@Value("${spring.cache.defaultmaxcacheentries}")
	private int CACHEMAX;
	
	@Value("${spring.cache.defaultcachetime}")
	private int CACHETIME;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
   
	@Autowired
	public CacheManager cacheManager;
	 
    @Autowired
    private AuthenticationEntryPoint unauthorizedHandler = new JwtAuthenticationEntryPoint();
    
    @Scheduled(cron = "0 0 3 * * ?")
    public void resetCache() {
    	//CacheManager cacheManager = cacheManager();
    	Collection<String> cacheNames = cacheManager.getCacheNames();
    	for(String cache: cacheNames){
    		cacheManager.getCache(cache).clear();
    	}
    	//cacheManager.getCacheNames().parallelStream().forEach(name -> cacheManager.getCache(name).clear());
    	logger.info("Cleared All Caches at: " + new Date().toString());
    }
    
    
    @Bean
    public CacheManager cacheManager(){
    	GuavaCacheManager  guavaCacheManager =  new GuavaCacheManager();
        guavaCacheManager.setCacheBuilder(CacheBuilder.newBuilder().maximumSize(CACHEMAX).expireAfterAccess(CACHETIME, TimeUnit.MINUTES));
        return guavaCacheManager;
    }
    
    public void resetCache(String cashName, String key) {
    	//CacheManager cacheManager = cacheManager();
    	try{
    		if(key != null){
    			System.out.println(cacheManager.getCache(cashName).get(key));
    			logger.info("Removed Object with Key: " + key + "from cache named: " + cashName + " at: " + new Date().toString());
    		}else{
    			cacheManager.getCache(cashName).clear();
    			logger.info("Cleared Cache named: " + cashName + " at: " + new Date().toString());
    		}
        	//cacheManager.getCacheNames().parallelStream().forEach(name -> cacheManager.getCache(name).clear());
        	
    	}catch(Exception e){
    		logger.error("Cache named: " + cashName + "Could not be cleared");
    	}
    	
    }
    
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
    public CommonsMultipartResolver multipartResolver(){

        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        Long maxFileSize = (long) 2097152;
        resolver.setMaxUploadSizePerFile(maxFileSize);
        return resolver;
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
	
	/*@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/*");
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