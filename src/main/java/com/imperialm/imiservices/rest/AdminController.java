package com.imperialm.imiservices.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imperialm.imiservices.dao.DashBoardBannersDAO;
import com.imperialm.imiservices.dto.DashBoardBannersDTO;
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

	@Autowired
	private DashBoardBannersDAO dashBoardBannersDAO;
	
	@Autowired
	private com.imperialm.imiservices.config.IMIServiceSecutiryConfig IMIServiceSecutiryConfig;


	@RequestMapping(value = "/services/admin/emulate/{id}", method = RequestMethod.GET)
	public @ResponseBody Object getNoTile(@PathVariable(value="id") String id, HttpServletRequest request) {

		OneItem tokenToPass = new OneItem();
		UserDetailsImpl user = null;
		try{
			String token = request.getHeader(tokenHeader);
			String username = jwtTokenUtil.getUsernameFromToken(token);
			user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
			if(!jwtTokenUtil.validateToken(token, user)){
				return ResponseEntity.status(500).body("Invalid Token");
			}
		}catch(Exception e){
			return ResponseEntity.status(500).body("Failed to check Token");
		}

		//TODO: check if admin is requesting the token
		user = (UserDetailsImpl) userDetailsService.loadUserByUsername(id);

		if(user != null){
			tokenToPass.setItem(jwtTokenUtil.generateToken(user));
		}else{
			return ResponseEntity.status(500).body("No user with ID: " + id + "was not found");
		}
		return tokenToPass;
	}
	
	@RequestMapping(value = "/services/admin/resetcache", method = RequestMethod.GET)
	public @ResponseBody Object resetcache(HttpServletRequest request) {
			IMIServiceSecutiryConfig.resetCache();
			return "Cache reset";
		}

	@RequestMapping(value = "/services/admin/banner/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteBanner(@PathVariable(value="id") int id, HttpServletRequest request) {
		UserDetailsImpl user = null;
		try{
			String token = request.getHeader(tokenHeader);
			String username = jwtTokenUtil.getUsernameFromToken(token);
			user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
			if(!jwtTokenUtil.validateToken(token, user)){
				return ResponseEntity.status(500).body("Invalid Token");
			}
		}catch(Exception e){
			return ResponseEntity.status(500).body("Failed to check Token");
		}

		//TODO: check if admin is requesting the token

		try{
			if(dashBoardBannersDAO.deleteBanner(id, user.getUserId())){
				return true;
			}else{
				throw new Exception();
			}
		} catch(Exception e){
			return ResponseEntity.status(500).body("Banner with id: " + id + "could not be deleted");
		}
	}
	
	
	@RequestMapping(value = "/services/admin/banner/add/", method = RequestMethod.POST)
	public @ResponseBody Object addBanner(@RequestBody DashBoardBannersDTO banner, HttpServletRequest request) {
		UserDetailsImpl user = null;
		try{
			String token = request.getHeader(tokenHeader);
			String username = jwtTokenUtil.getUsernameFromToken(token);
			user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
			if(!jwtTokenUtil.validateToken(token, user)){
				return ResponseEntity.status(500).body("Invalid Token");
			}
		}catch(Exception e){
			return ResponseEntity.status(500).body("Failed to check Token");
		}

		//TODO: check if admin is requesting the token

		try{
			if(dashBoardBannersDAO.addBanner(banner, user.getUserId())){
				return true;
			}else{
				throw new Exception();
			}
		} catch(Exception e){
			return ResponseEntity.status(500).body("Banner with Role Id: " + banner.getRoleID() + ", and image file named: '" + banner.getImage() + "', and BC: " + banner.getBusinessCenter() + ", could not be added");
		}
	}
	
	@RequestMapping(value = "/services/admin/banner/update/", method = RequestMethod.POST)
	public @ResponseBody Object updateBanner(@RequestBody DashBoardBannersDTO banner, HttpServletRequest request) {
		UserDetailsImpl user = null;
		try{
			String token = request.getHeader(tokenHeader);
			String username = jwtTokenUtil.getUsernameFromToken(token);
			user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
			if(!jwtTokenUtil.validateToken(token, user)){
				return ResponseEntity.status(500).body("Invalid Token");
			}
		}catch(Exception e){
			return ResponseEntity.status(500).body("Failed to check Token");
		}

		//TODO: check if admin is requesting the token

		try{
			if(dashBoardBannersDAO.updateBanner(banner, user.getUserId())){
				return true;
			}else{
				throw new Exception();
			}
		} catch(Exception e){
			return ResponseEntity.status(500).body("Banner with Id: " + banner.getDashBoardBannersID() + ", could not be updated");
		}
	}
	
	@RequestMapping(value = "/services/admin/banner/getAll/", method = RequestMethod.GET)
	public @ResponseBody Object getAllBanner(HttpServletRequest request) {
		UserDetailsImpl user = null;
		/*try{
			String token = request.getHeader(tokenHeader);
			String username = jwtTokenUtil.getUsernameFromToken(token);
			user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
			if(!jwtTokenUtil.validateToken(token, user)){
				return ResponseEntity.status(500).body("Invalid Token");
			}
		}catch(Exception e){
			return ResponseEntity.status(500).body("Failed to check Token");
		}*/

		//TODO: check if admin is requesting the token
		try{
			return dashBoardBannersDAO.getAllBannersForAdmin();
		} catch(Exception e){
			return ResponseEntity.status(500).body("error fetching all banners");
		}
	}

}