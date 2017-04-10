/**
 *
 */
package com.imperialm.imiservices.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.imperialm.imiservices.dto.BrainBoostWinndersGraphDTO;
import com.imperialm.imiservices.dto.BrainBoostWinnersDetailsDTO;
import com.imperialm.imiservices.dto.CertProfsExpertDetailsDTO;
import com.imperialm.imiservices.dto.CertProfsExpertGraphDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersDetailsDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersGraphDTO;
import com.imperialm.imiservices.dto.RewardRedemptionGraphDTO;
import com.imperialm.imiservices.dto.SIRewardsDetailsGraphDTO;
import com.imperialm.imiservices.dto.SIRewardsYOYGraphDTO;
import com.imperialm.imiservices.dto.TTTAEnrolledDTO;
import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.security.JwtTokenUtil;
import com.imperialm.imiservices.services.DashboardServiceImpl;
import com.imperialm.imiservices.services.MappingServiceImpl;
import com.imperialm.imiservices.services.UserServiceImpl;
import com.imperialm.imiservices.util.IMIServicesConstants;

/**
 * @author Dheerajr
 *
 */
@Controller

public class IMIServiceWebController {

	private static final Logger logger = LoggerFactory.getLogger(IMIServiceWebController.class);

	@Autowired
	private DashboardServiceImpl dashService;

	@Autowired
	private MappingServiceImpl mappingService;

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserServiceImpl userDetailsService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	// @RequestMapping(method = RequestMethod.GET)
	public String index() {
		return IMIServicesConstants.INDEX_PAGE;
	}
	
	@RequestMapping(value = "/dtable", method = RequestMethod.GET)
	// @RequestMapping(method = RequestMethod.GET)
	public String datatable(Model model,
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		model.addAttribute("data", name);
		System.out.println("dtable test");
		return IMIServicesConstants.DATA_TABLE;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	// @RequestMapping(method = RequestMethod.GET)
	public String test(Model model,
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		model.addAttribute("data", name);		
		return IMIServicesConstants.TEST_PAGE;
	}

	 @RequestMapping(value = "/datatable", method = RequestMethod.GET)
	//@RequestMapping(value = "/services/data/{chartId}/{territory}", method = RequestMethod.GET)
	// @RequestMapping(method = RequestMethod.GET)
	public String datatable(Model model, @RequestParam(value = "chartId") String id,
			@RequestParam(value = "territory") String territory, HttpServletRequest request) {
		UserDetailsImpl user = null;
		// get token extract user info and use for the calls
		try {
			String token = request.getHeader(tokenHeader);
			String username = jwtTokenUtil.getUsernameFromToken(token);
			user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
			if (!jwtTokenUtil.validateToken(token, user)) {
				// token is expired/invalid token
				return "Invalid Token";
			}
		} catch (Exception e) {
			// token is expired/invalid token
			return "Failed to check Token";
		}

		// divide the switch statement to functions
		switch (id) {
		case "9": {
			List<BrainBoostWinnersDetailsDTO> result = new ArrayList<BrainBoostWinnersDetailsDTO>();
			if (territory.length() >= 4 && territory.length() <= 5 && territory.contains("-")) {
				List<String> filters = new ArrayList<String>();
				filters.add(territory);
				List<BrainBoostWinndersGraphDTO> sublist = this.dashService
						.getBrainBoostWinddersGraphByTerritory(filters);
				for (BrainBoostWinndersGraphDTO item : sublist) {
					List<BrainBoostWinnersDetailsDTO> participants = this.dashService
							.getBrainBoostWinnersDetailsDTOByDealerCode(item.getChildTerritory(), "YTD");
					result.addAll(participants);
				}
			} else if (territory.length() > 4 && !territory.contains("-")) {
				// return
				// this.dashService.getBrainBoostWinnersDetailsDTOByDealerCode(territory,
				// "YTD");
				model.addAttribute("data",
						this.dashService.getBrainBoostWinnersDetailsDTOByDealerCode(territory, "YTD"));
			} else {

				// return result;
				model.addAttribute("data", result);
			}
			break;
		}
		case "10": {
			List<CertProfsExpertDetailsDTO> result = new ArrayList<CertProfsExpertDetailsDTO>();
			if (territory.length() >= 4 && territory.length() <= 5 && territory.contains("-")) {
				// District info get dealers from graph and participants
				List<String> filters = new ArrayList<String>(Arrays.asList(territory));
				List<CertProfsExpertGraphDTO> sublist = this.dashService
						.getExpertPointsEarnedByParentTerritory(filters);
				for (CertProfsExpertGraphDTO item : sublist) {
					result.addAll(this.dashService
							.getCertProfsExpertDetailsByDealerCodeANDCertType(item.getChildTerritory(), "JEEP%"));
					result.addAll(this.dashService
							.getCertProfsExpertDetailsByDealerCodeANDCertType(item.getChildTerritory(), "RAM%"));
				}
			} else if (territory.length() > 4 && !territory.contains("-")) {
				result.addAll(this.dashService.getCertProfsExpertDetailsByDealerCodeANDCertType(territory, "JEEP%"));
				result.addAll(this.dashService.getCertProfsExpertDetailsByDealerCodeANDCertType(territory, "RAM%"));
			}
			// return result;
			model.addAttribute("data", result);
			break;
		}
		case "11": {
			List<CertProfsExpertDetailsDTO> result = new ArrayList<CertProfsExpertDetailsDTO>();
			if (territory.length() >= 4 && territory.length() <= 5 && territory.contains("-")) {
				// District info get dealers from graph and participants
				List<String> filters = new ArrayList<String>(Arrays.asList(territory));
				List<CertProfsExpertGraphDTO> sublist = this.dashService
						.getExpertPointsEarnedByParentTerritory(filters);
				for (CertProfsExpertGraphDTO item : sublist) {
					result.addAll(this.dashService
							.getCertProfsExpertDetailsByDealerCodeANDCertType(item.getChildTerritory(), "JEEP%"));
					result.addAll(this.dashService
							.getCertProfsExpertDetailsByDealerCodeANDCertType(item.getChildTerritory(), "RAM%"));
				}
			} else if (territory.length() > 4 && !territory.contains("-")) {
				result.addAll(this.dashService.getCertProfsExpertDetailsByDealerCodeANDCertType(territory, "JEEP%"));
				result.addAll(this.dashService.getCertProfsExpertDetailsByDealerCodeANDCertType(territory, "RAM%"));
			}
			// return result;
			model.addAttribute("data", result);
			break;
		}
		case "12": {
			List<BrainBoostWinnersDetailsDTO> result = new ArrayList<BrainBoostWinnersDetailsDTO>();
			if (territory.length() >= 4 && territory.length() <= 5 && territory.contains("-")) {
				List<String> filters = new ArrayList<String>();
				filters.add(territory);
				List<BrainBoostWinndersGraphDTO> sublist = this.dashService
						.getBrainBoostWinddersGraphByTerritory(filters);
				for (BrainBoostWinndersGraphDTO item : sublist) {
					List<BrainBoostWinnersDetailsDTO> participants = this.dashService
							.getBrainBoostWinnersDetailsDTOByDealerCode(item.getChildTerritory(), "YTD");
					result.addAll(participants);
				}
			} else if (territory.length() > 4 && !territory.contains("-")) {
				// return
				// this.dashService.getBrainBoostWinnersDetailsDTOByDealerCode(territory,
				// "YTD");
				model.addAttribute("data",
						this.dashService.getBrainBoostWinnersDetailsDTOByDealerCode(territory, "YTD"));

			} else {
				// return result;
				model.addAttribute("data", result);
			}
			break;
		}

		case "13": {
			List<CertProfsWinnersDetailsDTO> result = new ArrayList<CertProfsWinnersDetailsDTO>();
			if (territory.length() >= 4 && territory.length() <= 5 && territory.contains("-")) {
				List<String> filters = new ArrayList<String>();
				filters.add(territory);
				List<CertProfsWinnersGraphDTO> sublist = this.dashService.getCertProfsWinnersGraphByTerritory(filters);
				for (CertProfsWinnersGraphDTO item : sublist) {
					List<CertProfsWinnersDetailsDTO> participants = this.dashService
							.getCertProfsWinnersDetailsByDealerCode(item.getChildTerritory());
					result.addAll(participants);
				}
			} else if (territory.length() > 4 && !territory.contains("-")) {
				// return
				// this.dashService.getCertProfsWinnersDetailsByDealerCode(territory);
				model.addAttribute("data", this.dashService.getCertProfsWinnersDetailsByDealerCode(territory));
				break;
			}
			// return result;
			model.addAttribute("data", result);
			break;
		}
		case "14": {
			// Normal Tile
			// return null;
			model.addAttribute("data", "");
			break;
		}
		case "15": {
			// Normal Tile
			// return null;
			model.addAttribute("data", "");
			break;
		}
		case "16": {
			List<TTTAEnrolledDTO> listEnrolled = this.dashService.getTTTAEnrollmentsBC(true);

			// return
			// this.mappingService.MapTTTAEnrolledDTOtoChart(listEnrolled, "# of
			// Dealers Enrolled YTD", "",
			// "Total Enrolled", "", "column_compound");
			model.addAttribute("data", this.mappingService.MapTTTAEnrolledDTOtoChart(listEnrolled,
					"# of Dealers Enrolled YTD", "", "Total Enrolled", "", "column_compound"));
		}
		case "17": {
			List<TTTAEnrolledDTO> listNotEnrolled = this.dashService.getTTTAEnrollmentsBC(false);

			// return
			// this.mappingService.MapTTTAEnrolledDTOtoChart(listNotEnrolled, "#
			// of Dealers Not Enrolled YTD", "",
			// "Total Enrolled", "", "column_compound");
			model.addAttribute("data", this.mappingService.MapTTTAEnrolledDTOtoChart(listNotEnrolled,
					"# of Dealers Not Enrolled YTD", "", "Total Enrolled", "", "column_compound"));
			break;

		}
		case "18": {
			// Normal Tile

			// return null;
			model.addAttribute("data", "");
			break;
		}
		case "19": {
			/*
			 * List<MSERGraphDTO> listOfFirstLevel =
			 * this.dashService.getMSERGraphByTerritoryAndToggle("CA-H", "YTD");
			 * List<MSERGraphDetailsDTO> listOfSecondLevel =
			 * this.dashService.getMSERGraphDetialsByDealerCode();
			 */
			// return null;
			model.addAttribute("data", "");
			break;
		}
		case "20": {
			// check for role, to know what data to display
			List<SIRewardsYOYGraphDTO> list1st = null;
			List<SIRewardsYOYGraphDTO> list1st_Filtered = null;
			List<String> filters = new ArrayList<String>();

			// check if nat or not if nat pull list of childeren and continue if
			// not start from their
			list1st = this.dashService.getSIRewardsYOYGraphByTerritoryAndToggle("NAT", "YTD");

			List<SIRewardsYOYGraphDTO> sublist = this.dashService.getSIRewardsYOYGraphByTerritoryAndToggle(filters,
					"YTD");

			// return filters;
			model.addAttribute("data", filters);
			break;
		}
		case "21": {
			// check for role, to know what data to display
			List<SIRewardsDetailsGraphDTO> list1st = null;
			List<SIRewardsDetailsGraphDTO> list1st_Filtered = null;
			List<String> filters = new ArrayList<String>();

			// check if nat or not if nat pull list of childeren and continue if
			// not start from their
			list1st = this.dashService.getSIRewardsDetailsGraphByTerritoryAndToggle("NAT", "QTD");

			for (SIRewardsDetailsGraphDTO item : list1st) {
				if (!filters.contains(item.getChildTerritory()))
					filters.add(item.getChildTerritory());
			}

			// list1st =
			// this.dashService.getSIRewardsYOYGraphByTerritoryAndToggle(filters,
			// "YTD");
			list1st_Filtered = this.dashService.getSIRewardsDetailsGraphByTerritoryAndToggleFilterParent(filters,
					"QTD");

			List<SIRewardsDetailsGraphDTO> sublist = this.dashService
					.getSIRewardsDetailsGraphByTerritoryAndToggle(filters, "QTD");

			// return filters;
			model.addAttribute("data", filters);
			break;
		}
		case "22": {
			// check for role, to know what data to display
			List<SIRewardsDetailsGraphDTO> list1st = null;
			List<SIRewardsDetailsGraphDTO> list1st_Filtered = null;
			List<String> filters = new ArrayList<String>();

			// check if nat or not if nat pull list of childeren and continue if
			// not start from their
			list1st = this.dashService.getSIRewardsDetailsGraphByTerritoryAndToggle("NAT", "QTD");

			// list1st =
			// this.dashService.getSIRewardsYOYGraphByTerritoryAndToggle(filters,
			// "YTD");
			list1st_Filtered = this.dashService.getSIRewardsDetailsGraphByTerritoryAndToggleFilterParent(filters,
					"QTD");

			List<SIRewardsDetailsGraphDTO> sublist = this.dashService
					.getSIRewardsDetailsGraphByTerritoryAndToggle(filters, "QTD");

			// return filters;
			model.addAttribute("data", filters);
			break;
		}
		case "23": {
			// check for role, to know what data to display
			List<RewardRedemptionGraphDTO> list1st = null;
			List<RewardRedemptionGraphDTO> list1st_Filtered = null;
			List<String> filters = new ArrayList<String>();
			// add user access territory
			filters.add("NAT");
			// check if nat or not if nat pull list of childeren and continue if
			// not start from their
			list1st = this.dashService.getRewardRedemptionGraphByParentTerritoryList(filters);
			filters = new ArrayList<String>();
			for (RewardRedemptionGraphDTO item : list1st) {
				if (!filters.contains(item.getChildTerritory()))
					filters.add(item.getChildTerritory());
			}

			list1st_Filtered = this.dashService.getRewardRedemptionGraphByParentTerritoryListDistinct(filters);

			List<RewardRedemptionGraphDTO> sublist = this.dashService
					.getRewardRedemptionGraphByParentTerritoryList(filters);
			// return filters;
			model.addAttribute("data", filters);
			break;
		}
		case "24": {
			// NOT A CHART

			// return null;
			model.addAttribute("data", "");
			break;
		}
		case "25": {
			// NOT A Graph Chart
			// return null;
			model.addAttribute("data", "");
			break;
		}
		default:
			// return "No such service call exists.";
			model.addAttribute("data", "");
			break;
		}

		return IMIServicesConstants.DATA_TABLE;
	}
}
