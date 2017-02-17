package com.imperialm.imiservices.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.entities.User;
import com.imperialm.imiservices.security.JwtAuthenticationRequest;
import com.imperialm.imiservices.security.JwtTokenUtil;
import com.imperialm.imiservices.security.service.JwtAuthenticationResponse;
import com.imperialm.imiservices.services.UserService;
import com.imperialm.imiservices.services.UserServiceImpl;

import java.security.MessageDigest;

import javax.crypto.Mac;
import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserServiceImpl userDetailsService;

    @RequestMapping(value = "/login/token", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        // Reload password post-security so we can generate token
    	final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    	
        final UserDetailsImpl user = (UserDetailsImpl) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        /*
        if(!(this.get_SHA_512_SecurePassword(authenticationRequest.getPassword(), user.getSalt()).toUpperCase().equals(user.getPassword()))){
        	throw new AuthenticationException("Failed to login");
        }*/
        final String token = jwtTokenUtil.generateToken(user);

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "/login/tokenrefresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserDetailsImpl user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    
    public String get_SHA_512_SecurePassword(String passwordToHash, String   salt){
    	String generatedPassword = null;
    	    try {
    	         MessageDigest md = MessageDigest.getInstance("SHA-512");
    	         passwordToHash= passwordToHash + salt;
    	         md.update(passwordToHash.getBytes("UTF-8"));
    	         byte[] bytes = md.digest();
    	         StringBuilder sb = new StringBuilder();
    	         for(int i=0; i< bytes.length ;i++){
    	            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
    	         }
    	         generatedPassword = sb.toString();
    	        } 
    	       catch (Exception e){
    	        return null;
    	       }
    	    return generatedPassword;
    	}
    
}
