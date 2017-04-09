/**
 *
 */
package com.imperialm.imiservices.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.imperialm.imiservices.util.IMIServicesConstants;

/**
 * @author Dheerajr
 *
 */
@Controller

public class IMIServiceWebController {

	private static final Logger logger = LoggerFactory.getLogger(IMIServiceWebController.class);
	
	@RequestMapping(value= "/", method=RequestMethod.GET)
	//@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return IMIServicesConstants.INDEX_PAGE;
	}
	
	@RequestMapping(value= "/datatable", method=RequestMethod.GET)
	//@RequestMapping(method = RequestMethod.GET)
	public String datatable(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
		model.addAttribute("data", name);
		return IMIServicesConstants.DATA_TABLE;
	}	
}
