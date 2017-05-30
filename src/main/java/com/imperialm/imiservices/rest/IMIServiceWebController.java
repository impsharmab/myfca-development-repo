/**
 *
 */
package com.imperialm.imiservices.rest;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.security.JwtTokenUtil;
import com.imperialm.imiservices.services.UserServiceImpl;
import com.imperialm.imiservices.util.IMIServicesConstants;


@Controller
@RequestMapping("/")
public class IMIServiceWebController {

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserServiceImpl userDetailsService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return IMIServicesConstants.INDEX_PAGE;
	}

	
	@RequestMapping(value = "/datatable", method = RequestMethod.GET)
	public String datatable(Model model, @RequestParam(value = "chartId") String id,
			@RequestParam(value = "territory") String territory, @RequestParam(value = "token") String token, HttpServletRequest request) {
		UserDetailsImpl user = null;
		try {
			//String token = request.getHeader(tokenHeader);
			String username = jwtTokenUtil.getUsernameFromToken(token);
			user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
			model.addAttribute("token", token);
			if (!jwtTokenUtil.validateToken(token, user)) {
				// token is expired/invalid token
				return "Invalid Token";
			}
		} catch (Exception e) {
			// token is expired/invalid token
			return "Failed to check Token";
		}
		model.addAttribute("chartId", id);
		model.addAttribute("territory", territory);

		return IMIServicesConstants.DATA_TABLE;
	}
	
	@RequestMapping(value = "/cms", method = RequestMethod.GET)
	public String cms(@RequestParam(value = "page") String page, HttpServletRequest request) {
		return IMIServicesConstants.CMS;
	}
	
	
}
