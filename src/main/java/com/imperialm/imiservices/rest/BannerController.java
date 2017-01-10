package com.imperialm.imiservices.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imperialm.imiservices.dto.BannersDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.services.BannerService;

@RestController
public class BannerController {
	private static Logger logger = LoggerFactory.getLogger(BannerController.class);
	@Autowired
	private BannerService bannerService;

	@RequestMapping(value = "/services/bannersbyrole", method = RequestMethod.GET)
	public @ResponseBody List<BannersDTO> findBannersByRole(@RequestParam("role") final Long roleId) {
		final InputRequest userRoleReq = new InputRequest(roleId);
		return this.bannerService.findBannersByRole(userRoleReq);
	}

}
