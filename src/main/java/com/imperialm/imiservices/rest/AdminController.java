package com.imperialm.imiservices.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imperialm.imiservices.dao.DashBoardBannersDAO;
import com.imperialm.imiservices.dao.DealerPersonnelPositionsDAO;
import com.imperialm.imiservices.dto.DashBoardBannersDTO;
import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.model.OneItem;
import com.imperialm.imiservices.security.JwtTokenUtil;
import com.imperialm.imiservices.services.UserServiceImpl;
import com.imperialm.imiservices.util.IMIServicesUtil;

@RestController
public class AdminController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserServiceImpl userDetailsService;

	@Autowired
	private DashBoardBannersDAO dashBoardBannersDAO;
	
	@Autowired
	private com.imperialm.imiservices.config.IMIServiceSecutiryConfig IMIServiceSecutiryConfig;
	
	@Autowired
	private DealerPersonnelPositionsDAO dealerPersonnelPositionsDAO;	
	
	@Autowired
	private IMIServicesUtil IMIServicesUtil;


	@RequestMapping(value = "/services/admin/emulate/{id}", method = RequestMethod.GET)
	public @ResponseBody Object getNoTile(@PathVariable(value="id") String id, HttpServletRequest request) {

		OneItem tokenToPass = new OneItem();
		@SuppressWarnings("unused")
		UserDetailsImpl user = IMIServicesUtil.checkAdminToken(request);
		
		UserDetailsImpl emulatedUser = (UserDetailsImpl) userDetailsService.loadUserByUsername(id);

		if(emulatedUser != null){
			tokenToPass.setItem(jwtTokenUtil.generateToken(emulatedUser));
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
		UserDetailsImpl user = IMIServicesUtil.checkAdminToken(request);
		try{
			if(dashBoardBannersDAO.deleteBanner(id, user.getUserId())){
				IMIServiceSecutiryConfig.resetCache("getBannersByRole", null);
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
		UserDetailsImpl user = IMIServicesUtil.checkAdminToken(request);
		try{
			if(dashBoardBannersDAO.addBanner(banner, user.getUserId())){
				IMIServiceSecutiryConfig.resetCache("getBannersByRole", null);
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
		UserDetailsImpl user = IMIServicesUtil.checkAdminToken(request);
		try{
			if(dashBoardBannersDAO.updateBanner(banner, user.getUserId())){
				IMIServiceSecutiryConfig.resetCache("getBannersByRole", null);
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
		@SuppressWarnings("unused")
		UserDetailsImpl user = IMIServicesUtil.checkToken(request);
		try{
			return dashBoardBannersDAO.getAllBannersForAdmin();
		} catch(Exception e){
			return ResponseEntity.status(500).body("error fetching all banners");
		}
	}
	
	@RequestMapping(value = "/services/admin/getAllPositionCodes/", method = RequestMethod.GET)
	public @ResponseBody Object getAllPositionCodes(HttpServletRequest request) {
		try{
			return dealerPersonnelPositionsDAO.getAllPositionCodes();
		} catch(Exception e){
			return ResponseEntity.status(500).body("error fetching all positioncodes");
		}
	}
	
	@RequestMapping(value = "/services/admin/checkDealerCode/{dealerCode}", method = RequestMethod.GET)
	public @ResponseBody Object isValidDealer(@PathVariable(value="dealerCode") String dealerCode, HttpServletRequest request) {
		try{
			if(dealerPersonnelPositionsDAO.isValidDealer(dealerCode)){
				return true;
			}else{
				return ResponseEntity.status(500).body("Not a Valid Dealer Code");
			}
		} catch(Exception e){
			return ResponseEntity.status(500).body("error checking dealer code");
		}
	}
	
	@RequestMapping(value = "/services/admin/checkDealerCode2/{dealerCode}", method = RequestMethod.GET)
	public @ResponseBody Object emulateDealer(@PathVariable(value="dealerCode") String dealerCode, HttpServletRequest request) {
			return dealerPersonnelPositionsDAO.getDealerPricipal(dealerCode);
	}
	
	

}