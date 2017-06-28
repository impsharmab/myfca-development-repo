package com.imperialm.imiservices.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.services.TileServiceImpl;
import com.imperialm.imiservices.util.IMIServicesUtil;


@RestController
public class DashboardController {
	
	@Autowired
	private TileServiceImpl tileService;
	
	@Autowired
	private IMIServicesUtil IMIServicesUtil;

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
		
		UserDetailsImpl user = IMIServicesUtil.checkToken(request);
		
		return tileService.findTilesListByRole(id, positionCode, dealerCode, user.getUserId().trim());
	}

}