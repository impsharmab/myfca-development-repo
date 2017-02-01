/**
 *
 */
package com.imperialm.imiservices.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imperialm.imiservices.dto.UserProfileDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.services.UserProfileService;
import com.imperialm.imiservices.services.UserService;

/**
 * @author Dheerajr
 *
 */
@RestController
public class UserProfileController {
	@Autowired
	private UserService userService;

	public UserProfileController(UserService userService){
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

}
