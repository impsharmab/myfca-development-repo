package com.imperialm.imiservices.rest;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.security.JwtTokenUtil;
import com.imperialm.imiservices.services.CMSService;
import com.imperialm.imiservices.services.UserServiceImpl;

@RestController
public class CMSController {

	private static Logger logger = LoggerFactory.getLogger(CMSController.class);
	
	@Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserServiceImpl userDetailsService;
	
	@Autowired
	CMSService cmsService;
	
	 String pathName = "/MSER/Primary";
	 String pageName;
	 
	 
	 @RequestMapping(value ="/content/{pageName}", method = RequestMethod.GET)
		public @ResponseBody Object getContenct(@PathVariable(value="pageName") String pageName, HttpServletRequest request) {
			UserDetailsImpl user = null;
		    
			//get token extract user info and use for the calls
			/*try{
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
			}*/
			
			return cmsService.getContent(pathName, pageName);
			
	    }
	 
	
}
