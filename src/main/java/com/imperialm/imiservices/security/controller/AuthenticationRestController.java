package com.imperialm.imiservices.security.controller;

import com.imperialm.imiservices.dao.DealerPersonnelPositionsDAO;
import com.imperialm.imiservices.dao.TIDUsersDAO;
import com.imperialm.imiservices.dao.UserPositionCodeRoleDAO;
import com.imperialm.imiservices.dao.UserProgramRolesDAO;
import com.imperialm.imiservices.dto.TIDUsersDTO;
import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.dto.UserPositionCodeRoleDTO;
import com.imperialm.imiservices.security.JwtAuthenticationRequest;
import com.imperialm.imiservices.security.JwtTokenUtil;
import com.imperialm.imiservices.security.service.JwtAuthenticationResponse;
import com.imperialm.imiservices.services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RestController
public class AuthenticationRestController {

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserPositionCodeRoleDAO userPositionCodeRoleDAO;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserServiceImpl userDetailsService;
	
	@Autowired
	private TIDUsersDAO TIDUsersDAO;
	
	@Autowired
	private UserProgramRolesDAO UserProgramRolesDAO;
	
	@Autowired
	private DealerPersonnelPositionsDAO DealerPersonnelPositionsDAO;
	
	
	
	private static Logger logger = LoggerFactory.getLogger(AuthenticationRestController.class);

	@RequestMapping(value = "/login/token", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
		// Reload password post-security so we can generate token
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authenticationRequest.getUsername().trim(),
						authenticationRequest.getPassword()
						)
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		final UserDetailsImpl user = (UserDetailsImpl) userDetailsService.loadUserByUsername(authenticationRequest.getUsername().trim());
		/*
        if(!(this.get_SHA_512_SecurePassword(authenticationRequest.getPassword(), user.getSalt()).toUpperCase().equals(user.getPassword()))){
        	throw new AuthenticationException("Failed to login");
        }*/
		final String token = jwtTokenUtil.generateToken(user);
		logger.info("User Id: " + user.getUserId() + ", signed in!");
		return finalizeToken(token,user, null , null);
	}

	@RequestMapping(value = "/login/tokenrefresh", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		UserDetailsImpl user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username.trim());

		if (jwtTokenUtil.canTokenBeRefreshed(token)) {
			String refreshedToken = jwtTokenUtil.refreshToken(token);
			return finalizeToken(refreshedToken,user, null , null);
		} else {
			return ResponseEntity.badRequest().body("Invalid Token");
		}
	}

	@RequestMapping(value = "/login/token/{token}/{positionCode}/{dealerCode}", method = RequestMethod.GET)
	public ResponseEntity<?> createAuthenticationToken(@PathVariable(value="token") String token, @PathVariable(value="positionCode") String tokenPositionCode, @PathVariable(value="dealerCode")String tokenDealerCode) throws AuthenticationException {

		String username = jwtTokenUtil.getUsernameFromToken(token);
		UserDetailsImpl user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username.trim());

		if(!jwtTokenUtil.validateToken(token, user)){
			//token is expired/invalid token
			return ResponseEntity.status(500).body("Invalid Token");
		}

		if(tokenDealerCode.length() != 5){
			tokenDealerCode = "";
		}
		
		if(!DealerPersonnelPositionsDAO.checkPositionCode(tokenPositionCode)){
			tokenPositionCode = "";
		}
		return finalizeToken(token,user, tokenPositionCode , tokenDealerCode);
	}

	public ResponseEntity<?> finalizeToken(String token, UserDetailsImpl user, String tokenPositionCode, String tokenDealerCode){
		List<UserPositionCodeRoleDTO> userCodes = userPositionCodeRoleDAO.getDealerCodePCRoleBySid(user.getUserId());
		List<String> positionCode = new ArrayList<String>();
		List<String> dealerCode = new ArrayList<String>();

		if(!(tokenPositionCode == null || tokenPositionCode.isEmpty() || tokenPositionCode.equalsIgnoreCase("undefined"))){
			positionCode.add(tokenPositionCode);
		}
		
		if(!(tokenDealerCode == null || tokenDealerCode.isEmpty() ||  tokenDealerCode.equalsIgnoreCase("undefined"))){
			dealerCode.add(tokenDealerCode);
		}

		for(UserPositionCodeRoleDTO item: userCodes){
			positionCode.add(item.getPositionCode());
			dealerCode.add(item.getDealerCode());
		}

		if(userCodes.size() == 0){
			List<TIDUsersDTO> tids = TIDUsersDAO.getTIDUsersByTID(user.getUserId());
			if(tids.size() > 0){
				for(TIDUsersDTO item: tids){
					positionCode.add(item.getPositionCode());
					if (item.getTerritory().length() == 5){
						dealerCode.add(item.getTerritory());
					}
				}
			}else{
				List<String> territoryCheck = this.userPositionCodeRoleDAO.getUserTerritoyById(user.getUserId());
				if(territoryCheck.size() > 0){
					if(territoryCheck.get(0).equalsIgnoreCase("nat")){
						positionCode.add("90");
					}else if(territoryCheck.get(0).contains("-")){
						positionCode.add("97");
					}else if(territoryCheck.get(0).trim().length() == 2){
						positionCode.add("8D");
					}
				}
			}
		}

		Set<String> p = new LinkedHashSet<>(positionCode);
		Set<String> d = new LinkedHashSet<>(dealerCode);
		positionCode.clear();
		dealerCode.clear();

		positionCode.addAll(p);
		dealerCode.addAll(d);

		JwtAuthenticationResponse response =new JwtAuthenticationResponse(token);
		response.setPositionCode(positionCode);
		response.setDealerCode(dealerCode);
		response.setName(user.getUsername());
		if(UserProgramRolesDAO.isAdmin(user.getUserId().trim())){
			response.setAdmin(true);
		}
		return ResponseEntity.ok(response);
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
