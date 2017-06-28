package com.imperialm.imiservices.security;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.imperialm.imiservices.dto.UserDetailsImpl;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final DecodedJWT jwt = getVerifiedToken(token);
            username = jwt.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }
    
    public Date getCreatedDateFromToken(String token) {
    	Date created;
    	try {
    		final DecodedJWT jwt = getVerifiedToken(token);
    		created = jwt.getIssuedAt();
    	} catch (Exception e) {
    		created = null;
    	}
    	return created;
    }
    
    public Date getExpirationDateFromToken(String token) {
    	Date expiration;
    	try {
    		final DecodedJWT jwt = getVerifiedToken(token);
    		expiration = jwt.getExpiresAt();
    	} catch (Exception e) {
    		expiration = null;
    	}
    	return expiration;
    }
    

    
    private DecodedJWT getVerifiedToken(String token) throws IllegalArgumentException, UnsupportedEncodingException{
    	DecodedJWT jwt = null;
    	
        	Algorithm algorithm = Algorithm.HMAC512(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                .build(); //Reusable verifier instance
            jwt = verifier.verify(token);
        
    	return jwt;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetailsImpl userDetails) {
        String token = "";
    	try {
    		Date created = new Date(System.currentTimeMillis());
    		Date exp = this.generateExpirationDate();
    		
    	    Algorithm algorithm = Algorithm.HMAC512(secret);
    	    token = JWT.create().withSubject(userDetails.getUserId().trim())
    	    		.withIssuedAt(created)
    	    		.withExpiresAt(exp)
    	        .sign(algorithm);
    	} catch (Exception exception){
			String e = exception.getMessage();
    		System.out.println(e);
    	}
    	return token;
    }
    

    String generateToken(String user) {
    	String token = "";
    	try {
    		Date created = new Date(System.currentTimeMillis());
    		Date exp = this.generateExpirationDate();

    		Algorithm algorithm = Algorithm.HMAC512(secret);
    		token = JWT.create().withSubject(user)
    				.withIssuedAt(created)
    				.withExpiresAt(exp)
    				.sign(algorithm);
    	} catch (Exception exception){
    		String e = exception.getMessage();
    		System.out.println(e);
    	}
    	return token;
    }


    public Boolean canTokenBeRefreshed(String token) {
        return (!isTokenExpired(token));
    }
    
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            refreshedToken = generateToken(getUsernameFromToken(token));
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
    	UserDetailsImpl user = (UserDetailsImpl) userDetails;
        final String username = getUsernameFromToken(token).trim();
        return (username.equals(user.getUserId().trim())
                        && !isTokenExpired(token));
    }
}