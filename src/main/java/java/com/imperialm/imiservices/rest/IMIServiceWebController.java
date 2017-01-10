/**
 *
 */
package com.imperialm.imiservices.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imperialm.imiservices.util.IMIServicesConstants;

/**
 * @author Dheerajr
 *
 */
@Controller
@RequestMapping("/")
public class IMIServiceWebController {

	private static final Logger logger = LoggerFactory.getLogger(IMIServiceWebController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return IMIServicesConstants.INDEX_PAGE;
	}
}
