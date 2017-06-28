package com.imperialm.imiservices.util;

import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imperialm.imiservices.dao.TIDUsersDAO;
import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.security.JwtTokenUtil;
import com.imperialm.imiservices.services.UserServiceImpl;

@Component
public class IMIServicesUtil {

	@Value("${jwt.header}")
	private String tokenHeader;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserServiceImpl userDetailsService;
	
	/*@Autowired
	private UserProgramRolesDAO userProgramRolesDAO;*/
	
	@Autowired
	private TIDUsersDAO tIDUsersDAO;
	
	private static Logger logger = LoggerFactory.getLogger(IMIServicesUtil.class);

	public static String prepareJson(final String key, final String value) {
		final ObjectMapper json = new ObjectMapper();
		String toReturn ="";
		final Properties props = new Properties();
		props.put(key, value);
		try {
			toReturn = json.writeValueAsString(props);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/**
	 * from Clob to String
	 * @param result
	 * @return java.lang.String
	 */
	public static String buildString(final Object result) {
		String returnString = "";
		if (result != null) {
			try {
				returnString = IOUtils.toString(((Clob) result).getCharacterStream()).replaceAll("\t", "");
			} catch (final IOException e) {
				logger.error("error while building String from Clob Step 1", e);
			} catch (final SQLException e) {
				logger.error("error while building String from Clob Step 2", e);
			}
		}

		return returnString;
	}
	
	// this should include $ sign and merge with format numbers - can't change now since it is used for none curency
	public String formatCurrency(int number){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(number);
		if (moneyString.endsWith(".00")) {
			int centsIndex = moneyString.lastIndexOf(".00");
			if (centsIndex != -1) {
				moneyString = moneyString.substring(1, centsIndex);
			}
		}

		return moneyString;
	}

	public String formatCurrency(double number){
		number =  Math.round(number);
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(number);
		if (moneyString.endsWith(".00")) {
			int centsIndex = moneyString.lastIndexOf(".00");
			if (centsIndex != -1) {
				moneyString = moneyString.substring(1, centsIndex);
			}
		}

		return moneyString;
	}
	
	public String formatNumbers(double number){
		number = Math.round(number);
		DecimalFormat formatter = new DecimalFormat("#,###");
		return formatter.format(number);
	}

	public String formatNumbers(int number){
		DecimalFormat formatter = new DecimalFormat("#,###");
		return formatter.format(number);
	}

	
	public String getCurrentQuarter(){
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return (cal.get(Calendar.YEAR)) + "Q" + ((cal.get(Calendar.MONTH) / 3) + 1);
	}

	public String getCurrentYear(){
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return (cal.get(Calendar.YEAR))+"";
	}
	
	public UserDetailsImpl checkToken(HttpServletRequest request){
		UserDetailsImpl user = null;
		try{
			String token = request.getHeader(tokenHeader);
			String username = jwtTokenUtil.getUsernameFromToken(token);
			user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
			if(!jwtTokenUtil.validateToken(token, user)){
				throw new BadCredentialsException("Invalid Token");
			}
		}catch(BadCredentialsException e){
			throw new BadCredentialsException(e.getMessage());
		}catch(Exception e){
			throw new AuthenticationCredentialsNotFoundException("No credentials found in header");
		}
		return user;
	}
	
	
	public UserDetailsImpl checkAdminToken(HttpServletRequest request){
		UserDetailsImpl user = null;
		try{
			String token = request.getHeader(tokenHeader);
			String username = jwtTokenUtil.getUsernameFromToken(token);
			user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
			if(!jwtTokenUtil.validateToken(token, user)){
				throw new BadCredentialsException("Invalid Token");
			}
			if(!tIDUsersDAO.isAdmin(username)){
				throw new BadCredentialsException("User is not an Admin");
			}
		}
		catch(BadCredentialsException e){
			throw new BadCredentialsException(e.getMessage());
		}catch(Exception e){
			throw new AuthenticationCredentialsNotFoundException("No credentials found in header");
		}
		return user;
	}
	
	

}
