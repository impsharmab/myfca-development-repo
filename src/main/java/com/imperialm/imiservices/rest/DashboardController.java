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

import com.imperialm.imiservices.dto.DashboardDTO;
import com.imperialm.imiservices.dto.request.UserRoleRequest;
import com.imperialm.imiservices.services.DashboardService;

/**
 *
 * @author Dheerajr
 *
 */
@RestController
public class DashboardController {

	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	private DashboardService dashService;

	@RequestMapping(value = "/services/tileslistbyrole", method = RequestMethod.GET)
	public @ResponseBody List<DashboardDTO> findTilesListByRole(@RequestParam("role") final Long roleId,
			@RequestParam("id") final String userID) {
		final UserRoleRequest userRoleReq = new UserRoleRequest(userID, roleId);
		return dashService.findTilesListByRole(userRoleReq);
	}
}
