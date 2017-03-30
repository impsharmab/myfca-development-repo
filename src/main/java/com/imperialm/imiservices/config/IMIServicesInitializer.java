/**
 *
 */
package com.imperialm.imiservices.config;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.imperialm.imiservices.filters.IMIServicesFilter;
import com.imperialm.imiservices.security.JwtAuthenticationTokenFilter;

/**
 * @author Dheerajr
 *
 */


public class IMIServicesInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { IMIServiceSecutiryConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	
	@Override
	protected Filter[] getServletFilters() {
		final Filter[] singleton = { new IMIServicesFilter() };
		return singleton;
	}
}