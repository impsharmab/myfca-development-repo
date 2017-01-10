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

import com.imperialm.imiservices.dao.DashboardDAO;
import com.imperialm.imiservices.dao.MSEREarningsDAOImpl;
import com.imperialm.imiservices.dto.DashboardDTO;
import com.imperialm.imiservices.dto.MSEREarningsDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.model.Chart;
import com.imperialm.imiservices.services.DashboardService;
import com.imperialm.imiservices.services.MappingServiceImpl;

/**
 *
 * @author Dheerajr
 *
 */
@RestController
public class DashboardController {

	private static Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	private DashboardService dashService;
	
	@Autowired
	private MappingServiceImpl mappingService;
	
	
	

	@RequestMapping(value = "/services/tileslistbyrole", method = RequestMethod.GET)
	public @ResponseBody List<DashboardDTO> findTilesListByRole(@RequestParam("role") final Long roleId,
			@RequestParam("id") final String userID) {
		final InputRequest userRoleReq = new InputRequest(userID, roleId);
		return this.dashService.findTilesListByRole(userRoleReq);
	}

	@RequestMapping(value = "/services/tilesdetailsbyrole", method = RequestMethod.GET)
	public @ResponseBody List<DashboardDTO> findTilesByRole(@RequestParam("role") final Long roleId,
			@RequestParam("id") final String userID) {
		final InputRequest userRoleReq = new InputRequest(userID, roleId);
		return this.dashService.findTilesByRole(userRoleReq);
	}
	
	@RequestMapping(value = "/services/tile/", method = RequestMethod.GET)
	public @ResponseBody Chart findTilesListByRole() {
		final InputRequest userRoleReq = new InputRequest("", "");
		List<MSEREarningsDTO> list = this.dashService.getEarningsByRole(userRoleReq);
		return this.mappingService.MapMSEREarningsDTOtoChart(list, "BC Total Earnings YTD", "", "Totsl Earnings", "", "bar-compond");
	}

	
}
