package com.imperialm.imiservices.config;

import javax.servlet.ServletContext;

import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import com.imperialm.imiservices.filters.IMIServicesFilter;

/*
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
	
	public SecurityWebApplicationInitializer() {
		super(IMIServiceSecutiryConfig.class);
	}
	
	@Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        insertFilters(servletContext, new IMIServicesFilter());
    }
	
	
}*/