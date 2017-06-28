package com.imperialm.imiservices.security;

import java.security.MessageDigest;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.imperialm.imiservices.dto.UserDetailsImpl;
public class JwtDaoAuthenticationProvider extends DaoAuthenticationProvider {

	@Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		UserDetailsImpl user = (UserDetailsImpl) userDetails;
		if (authentication.getCredentials() == null) {
			logger.debug("Authentication failed: no credentials provided");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}

		if(!(this.get_SHA_512_SecurePassword(authentication.getCredentials().toString(), user.getSalt()).toUpperCase().equals(user.getPassword()))){
			logger.debug("Authentication failed: password does not match stored value");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
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
