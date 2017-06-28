package com.imperialm.imiservices.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imperialm.imiservices.dao.DealerPersonnelPositionsDAO;
import com.imperialm.imiservices.dao.UserPositionCodeRoleDAO;
import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.services.BannerService;
import com.imperialm.imiservices.util.IMIServicesUtil;

@RestController
public class BannerController {
	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private IMIServicesUtil IMIServicesUtil;

	@Autowired
	private UserPositionCodeRoleDAO UserPositionCodeRoleDAO;

	@Autowired
	private DealerPersonnelPositionsDAO DealerPersonnelPositionsDAO;

	@RequestMapping(value = "/services/banners/{positionCode}/{dealerCode}/", method = RequestMethod.GET)
	public @ResponseBody Object getBanner(@PathVariable(value="dealerCode") String dealerCode,@PathVariable(value="positionCode") String positionCode, HttpServletRequest request) {
		
		UserDetailsImpl user = IMIServicesUtil.checkToken(request);

		int roleId = DealerPersonnelPositionsDAO.getRoleByPositionCode(positionCode);
		//String type = "";
		String BC = "NO BC";
		if( roleId == 1 || roleId == 3 || roleId == 13){
			//type = "Executive";
			BC = "NAT";
			//banner service get all
			return this.bannerService.getAllBanners();
		}else if( roleId == 12){
			//type = "BC";
			List<String> bcSet = this.UserPositionCodeRoleDAO.getUserTerritoyById(user.getUserId());
			if(bcSet.size() > 0){
				BC = bcSet.get(0);
			}
			return this.bannerService.getBannersByRole(roleId, BC);
		}else if( roleId == 11){
			//type = "District";
			List<String> bcSet = this.UserPositionCodeRoleDAO.getUserTerritoyById(user.getUserId());
			if(bcSet.size() > 0){
				BC = bcSet.get(0).substring(0,2);
			}
			return this.bannerService.getBannersByRole(roleId, BC);
		}else if( roleId == 10 || roleId == 9 || roleId == 5 || roleId == 6){
			//type = "Dealer";
			List<String> userBC = this.UserPositionCodeRoleDAO.getBCByDealerCode(dealerCode);
			if(userBC.size()>0){
				BC = userBC.get(0);
			}
			return this.bannerService.getBannersByRole(roleId, BC);
		}
		return ResponseEntity.noContent();
	}
}
