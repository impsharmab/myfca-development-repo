/**
 *
 */
package com.imperialm.imiservices.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imperialm.imiservices.dao.DealerPersonnelPositionsDAO;
import com.imperialm.imiservices.dao.UserDAOImpl;
import com.imperialm.imiservices.dao.UserPositionCodeRoleDAO;
import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.dto.UserProfileDTO;
import com.imperialm.imiservices.dto.UsersDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.model.NoTile;
import com.imperialm.imiservices.model.OneItem;
import com.imperialm.imiservices.model.TwoStringItems;
import com.imperialm.imiservices.model.UserIdEmail;
import com.imperialm.imiservices.model.UserProfile;
import com.imperialm.imiservices.security.JwtTokenUtil;
import com.imperialm.imiservices.services.DashboardServiceImpl;
import com.imperialm.imiservices.services.UserProfileService;
import com.imperialm.imiservices.services.UserService;
import com.imperialm.imiservices.services.UserServiceImpl;
import com.imperialm.imiservices.util.EmailHandler;

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
	private UserDAOImpl userDAOImpl;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserServiceImpl userDetailsService;
	
	@Autowired
    private EmailHandler emailHandler;

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


	@RequestMapping(value = "/UserProfile/Profile", method = RequestMethod.POST)
	public @ResponseBody Object getProfile(@RequestBody UserProfile userProfile ,HttpServletRequest request) {


		UserDetailsImpl user = null;
		//get token extract user info and use for the calls
		try{
			String token = request.getHeader(tokenHeader);
			String username = jwtTokenUtil.getUsernameFromToken(token);
			user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
			if(!jwtTokenUtil.validateToken(token, user)){
				//token is expired/invalid token
				return ResponseEntity.status(500).body("Invalid Token");
			}
		}catch(Exception e){
			//token is expired/invalid token
			return ResponseEntity.status(500).body("Failed to check Token");
		}

		if(userProfile.getName().isEmpty() || userProfile.getEmail().isEmpty()){
			return false;
		}

		UsersDTO temp = new UsersDTO();
		temp.setUserId(user.getUserId());
		temp.setEmail(userProfile.getEmail());
		temp.setName(userProfile.getName());


		return userDAOImpl.setProfile(temp);

	}


	@RequestMapping(value = "/UserProfile/Profile", method = RequestMethod.GET)
	public @ResponseBody Object getProfile(HttpServletRequest request) {


		UserDetailsImpl user = null;
		//get token extract user info and use for the calls
		try{
			String token = request.getHeader(tokenHeader);
			String username = jwtTokenUtil.getUsernameFromToken(token);
			user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
			if(!jwtTokenUtil.validateToken(token, user)){
				//token is expired/invalid token
				return ResponseEntity.status(500).body("Invalid Token");
			}
		}catch(Exception e){
			//token is expired/invalid token
			return ResponseEntity.status(500).body("Failed to check Token");
		}

		List<UsersDTO> temp = userDAOImpl.getProfile(user.getUserId());

		if(temp.size() > 0){
			UserProfile profile = new UserProfile();
			profile.setEmail(temp.get(0).getEmail());
			profile.setName(temp.get(0).getName());
			return profile;
		}

		return ResponseEntity.status(500).body("No Profile Found");

	}


	@RequestMapping(value = "/UserProfile/Password", method = RequestMethod.POST)
	public @ResponseBody Object setPassword(@RequestBody OneItem password ,HttpServletRequest request) {


		UserDetailsImpl user = null;
		//get token extract user info and use for the calls
		try{
			String token = request.getHeader(tokenHeader);
			String username = jwtTokenUtil.getUsernameFromToken(token);
			user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
			if(!jwtTokenUtil.validateToken(token, user)){
				//token is expired/invalid token
				return ResponseEntity.status(500).body("Invalid Token");
			}
		}catch(Exception e){
			//token is expired/invalid token
			return ResponseEntity.status(500).body("Failed to check Token");
		}

		if(password.getItem().isEmpty() || password.getItem().contains(" ")){
			return false;
		}

		String salt = UUID.randomUUID().toString().toUpperCase();
		return userDAOImpl.setPassword(user.getUserId(), password.getItem(), salt);

	}


	@RequestMapping(value = "/UserProfile/ResetPassword", method = RequestMethod.POST)
	public @ResponseBody Object resetPassword(@RequestBody UserIdEmail object ,HttpServletRequest request) {


		UserDetailsImpl user = null;

		//get token extract user info and use for the calls
		try{
			user = (UserDetailsImpl) userDetailsService.loadUserByUsername(object.getUserId());
			List<UsersDTO> temp = userDAOImpl.getProfile(user.getUserId());
			if(temp.size() > 0){
				if(user.getUserId().equals(object.getUserId())){
					if(!temp.get(0).getEmail().toLowerCase().equals(object.getEmail().toLowerCase())){
						//return "Invalid Email";
						return ResponseEntity.status(500).body("Invalid Email");
					}
				}
			}else{
				//return "Invalid User";
				return ResponseEntity.status(500).body("Invalid User");
			}
		}catch(Exception e){
			//token is expired/invalid token
			return ResponseEntity.status(500).body("Invalid User");
		}


		String newPassword = RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(8,20));
		//send an email with the new password

		String salt = UUID.randomUUID().toString().toUpperCase();
		if(userDAOImpl.setPassword(user.getUserId(), newPassword, salt)){
			emailHandler.sendMailConfirmation(user, "ResetPassword", newPassword);
			return true;
		}else{
			return ResponseEntity.status(503).body("Could not reset password");
		}
	}

	@RequestMapping(value = "/UserProfile/TextAlerts", method = RequestMethod.POST)
	public @ResponseBody Object setTextAlerts(@RequestBody TwoStringItems object ,HttpServletRequest request) {


		UserDetailsImpl user = null;
		//get token extract user info and use for the calls
		try{
			String token = request.getHeader(tokenHeader);
			String username = jwtTokenUtil.getUsernameFromToken(token);
			user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
			if(!jwtTokenUtil.validateToken(token, user)){
				//token is expired/invalid token
				return ResponseEntity.status(500).body("Invalid Token");
			}
		}catch(Exception e){
			//token is expired/invalid token
			return ResponseEntity.status(500).body("Failed to check Token");
		}

		return userDAOImpl.setTextAlert(object, user.getUserId());

	}




}
