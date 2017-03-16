/**
 *
 */
package com.imperialm.imiservices.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imperialm.imiservices.dao.DealerPersonnelPositionsDAO;
import com.imperialm.imiservices.dao.UserPositionCodeRoleDAO;
import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.dto.UserProfileDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.model.NoTile;
import com.imperialm.imiservices.security.JwtTokenUtil;
import com.imperialm.imiservices.services.DashboardServiceImpl;
import com.imperialm.imiservices.services.UserProfileService;
import com.imperialm.imiservices.services.UserService;
import com.imperialm.imiservices.services.UserServiceImpl;

/**
 * @author Dheerajr
 *
 */
@RestController
public class UserProfileController {
	
	@Value("${jwt.header}")
    private String tokenHeader;
	
	@Autowired
	private UserServiceImpl userService;
	
	  @Autowired
	    private JwtTokenUtil jwtTokenUtil;

	    @Autowired
	    private UserServiceImpl userDetailsService;
	    
	    @Autowired
	    private UserPositionCodeRoleDAO userPositionCodeRoleDAO;
	    
	    @Autowired
	    private DealerPersonnelPositionsDAO dealerPersonnelPositionsDAO;

	public UserProfileController(UserServiceImpl userService){
		this.userService = userService;
	}
	private static Logger logger = LoggerFactory.getLogger(UserProfileController.class);
	@Autowired
	private UserProfileService userprofileService;

	@RequestMapping(value = "/services/userprofiletest", method = RequestMethod.GET)
	public @ResponseBody UserProfileDTO getUserProfileTest(@RequestParam("id") final String userID,
			@RequestParam("key") final String password) {
		final InputRequest userRoleReq = new InputRequest(userID, password);
		return this.userprofileService.getUserProfile(userRoleReq);
	}

	@RequestMapping(value = "/services/userprofile", method = RequestMethod.POST)
	public @ResponseBody UserProfileDTO getUserProfile(@RequestParam("id") final String userID,
			@RequestParam("key") final String password) {
		final InputRequest userRoleReq = new InputRequest(userID, password);
		return this.userprofileService.getUserProfile(userRoleReq);
	}
	

	@RequestMapping(value = "/services/notile/{positionCode}/{dealerCode}", method = RequestMethod.GET)
	public @ResponseBody Object getNoTile(@PathVariable(value="positionCode") String positionCode, @PathVariable(value="dealerCode") String dealerCode, HttpServletRequest request) {
		
		
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
		
		return this.userprofileService.getuserTiles(positionCode, dealerCode, user);
		
	}

}
