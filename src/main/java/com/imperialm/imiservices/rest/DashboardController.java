package com.imperialm.imiservices.rest;

import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.security.JwtTokenUtil;
import com.imperialm.imiservices.services.TileServiceImpl;
import com.imperialm.imiservices.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;


@RestController
public class DashboardController {

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserServiceImpl userDetailsService;
	
	@Autowired
	private TileServiceImpl tileService;

	@RequestMapping(value = "/myfcadashboard", method = RequestMethod.GET)
	public RedirectView myfcadashboard() {
		RedirectView redirectView = new RedirectView("/", true);
		return redirectView;
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public RedirectView profile() {
		RedirectView redirectView = new RedirectView("/", true);
		return redirectView;
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public RedirectView admin() {
		RedirectView redirectView = new RedirectView("/", true);
		return redirectView;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public RedirectView login() {
		RedirectView redirectView = new RedirectView("/", true);
		return redirectView;
	}

	@RequestMapping(value ="/services/tile/{chartId}/{positionCode}/{dealerCode}", method = RequestMethod.GET)
	public @ResponseBody Object findTilesListByRole(@PathVariable(value="chartId") String id, @PathVariable(value="positionCode") String positionCode, @PathVariable(value="dealerCode") String dealerCode, HttpServletRequest request) {
		UserDetailsImpl user = null;
		//get token extract user info and use for the calls
		try{
			String token = request.getHeader(tokenHeader);
			String username = jwtTokenUtil.getUsernameFromToken(token);
			user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
			if(!jwtTokenUtil.validateToken(token, user)){
				//token is expired/invalid token
				return "Invalid Token";
			}
		}catch(Exception e){
			//token is expired/invalid token
			return "Failed to check Token";
		}
		return tileService.findTilesListByRole(id, positionCode, dealerCode, user.getUserId().trim());
	}

}