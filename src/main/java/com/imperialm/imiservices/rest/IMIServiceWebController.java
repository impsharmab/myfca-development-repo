/**
 *
 */
package com.imperialm.imiservices.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.imperialm.imiservices.dto.BrainBoostWinndersGraphDTO;
import com.imperialm.imiservices.dto.BrainBoostWinnersDetailsDTO;
import com.imperialm.imiservices.dto.CertProfsExpertDetailsDTO;
import com.imperialm.imiservices.dto.CertProfsExpertGraphDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersDetailsDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersGraphDTO;
import com.imperialm.imiservices.dto.RewardRedemptionGraphDTO;
import com.imperialm.imiservices.dto.SIRewardsDetailsGraphDTO;
import com.imperialm.imiservices.dto.SIRewardsYOYGraphDTO;
import com.imperialm.imiservices.dto.TTTAEnrolledDTO;
import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.security.JwtTokenUtil;
import com.imperialm.imiservices.services.DashboardServiceImpl;
import com.imperialm.imiservices.services.MappingServiceImpl;
import com.imperialm.imiservices.services.UserServiceImpl;
import com.imperialm.imiservices.util.IMIServicesConstants;

/**
 * @author Dheerajr
 *
 */
@Controller

public class IMIServiceWebController {

	private static final Logger logger = LoggerFactory.getLogger(IMIServiceWebController.class);

	@Autowired
	private DashboardServiceImpl dashService;

	@Autowired
	private MappingServiceImpl mappingService;

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserServiceImpl userDetailsService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	// @RequestMapping(method = RequestMethod.GET)
	public String index() {
		return IMIServicesConstants.INDEX_PAGE;
	}

	@RequestMapping(value = "/dtable", method = RequestMethod.GET)
	// @RequestMapping(method = RequestMethod.GET)
	public String datatable(Model model,
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		model.addAttribute("data", name);
		System.out.println("dtable test");
		return IMIServicesConstants.DATA_TABLE;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	// @RequestMapping(method = RequestMethod.GET)
	public String test(Model model,
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		model.addAttribute("data", name);
		return IMIServicesConstants.TEST_PAGE;
	}

	@RequestMapping(value = "/datatable", method = RequestMethod.GET)
	// @RequestMapping(value = "/services/data/{chartId}/{territory}", method =
	// RequestMethod.GET)
	// @RequestMapping(method = RequestMethod.GET)
	public String datatable(Model model, @RequestParam(value = "chartId") String id,
			@RequestParam(value = "territory") String territory, HttpServletRequest request) {
		UserDetailsImpl user = null;
		try {
			String token = request.getHeader(tokenHeader);
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
}
