package com.imperialm.imiservices.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.model.OneItem;
import com.imperialm.imiservices.security.JwtTokenUtil;
import com.imperialm.imiservices.services.UserServiceImpl;

@RestController
public class AdminController {
	
	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserServiceImpl userDetailsService;


	@RequestMapping(value = "/services/Admin/{id}", method = RequestMethod.GET)
	public @ResponseBody Object getNoTile(@PathVariable(value="id") String id, HttpServletRequest request) {

		OneItem tokenToPass = new OneItem();
		UserDetailsImpl user = null;
		try{
			String token = request.getHeader(tokenHeader);
			String username = jwtTokenUtil.getUsernameFromToken(token);
			user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
			if(!jwtTokenUtil.validateToken(token, user)){
				return "Invalid Token";
			}
		}catch(Exception e){
			return "Failed to check Token";
		}
		
		//TODO: check if admin is requesting the token
		user = (UserDetailsImpl) userDetailsService.loadUserByUsername(id);
		
		if(user != null){
	        tokenToPass.setItem(jwtTokenUtil.generateToken(user));
		}else{
			return ResponseEntity.status(500).body("No user with ID: " + id + "was found");
		}
		return tokenToPass;
	}
}