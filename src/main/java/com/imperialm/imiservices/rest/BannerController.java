package com.imperialm.imiservices.rest;

import java.util.List;

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
import com.imperialm.imiservices.dao.UserPositionCodeRoleDAO;
import com.imperialm.imiservices.dao.DealerPersonnelPositionsDAO;
import com.imperialm.imiservices.security.JwtTokenUtil;
import com.imperialm.imiservices.services.BannerService;
import com.imperialm.imiservices.services.UserServiceImpl;

@RestController
public class BannerController {
	@Autowired
	private BannerService bannerService;

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserServiceImpl userDetailsService;

	@Autowired
	private UserPositionCodeRoleDAO UserPositionCodeRoleDAO;

	@Autowired
	private DealerPersonnelPositionsDAO DealerPersonnelPositionsDAO;

	@RequestMapping(value = "/services/banners/{positionCode}/{dealerCode}/", method = RequestMethod.GET)
	public @ResponseBody Object getBanner(@PathVariable(value="dealerCode") String dealerCode,@PathVariable(value="positionCode") String positionCode, HttpServletRequest request) {
		UserDetailsImpl user = null;
		//get token extract user info and use for the calls
		try{
			String token = request.getHeader(tokenHeader);
			String username = jwtTokenUtil.getUsernameFromToken(token);
			user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
			if(!jwtTokenUtil.validateToken(token, user)){
				//token is expired/invalid token
				return "Invalid Token";
			}
		}catch(Exception e){
			//token is expired/invalid token
			return "Failed to check Token";
		}

		int testa = DealerPersonnelPositionsDAO.getRoleByPositionCode(positionCode);
		//String type = "";
		String BC = "NO BC";
		if( testa == 1 || testa == 3 || testa == 13){
			//type = "Executive";
			BC = "NAT";
			//banner service get all
			return this.bannerService.getAllBanners();
		}else if( testa == 12){
			//type = "BC";
			List<String> bcSet = this.UserPositionCodeRoleDAO.getUserTerritoyById(user.getUserId());
			if(bcSet.size() > 0){
				BC = bcSet.get(0);
			}
			return this.bannerService.getBannersByRole(testa, BC);
		}else if( testa == 11){
			//type = "District";
			List<String> bcSet = this.UserPositionCodeRoleDAO.getUserTerritoyById(user.getUserId());
			if(bcSet.size() > 0){
				BC = bcSet.get(0).substring(0,2);
			}
			return this.bannerService.getBannersByRole(testa, BC);
		}else if( testa == 10 || testa == 9 || testa == 5){
			//type = "Dealer";
			List<String> userBC = this.UserPositionCodeRoleDAO.getBCByDealerCode(dealerCode);
			if(userBC.size()>0){
				BC = userBC.get(0);
			}
			return this.bannerService.getBannersByRole(testa, BC);
		}
		return ResponseEntity.noContent();
	}
}
