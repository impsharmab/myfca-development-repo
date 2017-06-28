/**
 *
 */
package com.imperialm.imiservices.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Dheerajr
 *
 */
@RestController
@RequestMapping("/error")
public class IMIServicesErrorController implements ErrorController {

	private static Logger logger = LoggerFactory.getLogger(IMIServicesErrorController.class);

	private final ErrorAttributes errorAttributes;

	@Autowired
	public IMIServicesErrorController(final ErrorAttributes errorAttributes) {
		Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
		this.errorAttributes = errorAttributes;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@RequestMapping
	public Map<String, Object> error(final HttpServletRequest aRequest) {
		final Map<String, Object> body = this.getErrorAttributes(aRequest, this.getTraceParameter(aRequest));
		final String trace = (String) body.get("trace");
		if (trace != null) {
			final String[] lines = trace.split("\n\t");
			body.put("trace", lines);
		}
		return body;
	}

	private boolean getTraceParameter(final HttpServletRequest request) {
		final String parameter = request.getParameter("trace");
		if (parameter == null) {
			return false;
		}
		return !"false".equals(parameter.toLowerCase());
	}

	private Map<String, Object> getErrorAttributes(final HttpServletRequest aRequest, final boolean includeStackTrace) {
		final RequestAttributes requestAttributes = new ServletRequestAttributes(aRequest);
		return this.errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
	}
}