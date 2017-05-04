package com.imperialm.imiservices.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.imperialm.imiservices.dto.BrainBoostWinndersGraphDTO;
import com.imperialm.imiservices.dto.BrainBoostWinnersDetailsDTO;
import com.imperialm.imiservices.dto.CertProfsExpertDetailsDTO;
import com.imperialm.imiservices.dto.CertProfsExpertGraphDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersDetailsDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersGraphDTO;
import com.imperialm.imiservices.dto.CustomerFirstDetailsDTO;
import com.imperialm.imiservices.dto.CustomerFirstGraphDTO;
import com.imperialm.imiservices.dto.MyfcaMSERTotalEarningsDTO;
import com.imperialm.imiservices.dto.MyfcaMSERTotalEarningsDetailsDTO;
import com.imperialm.imiservices.dto.RetentionDetailsDTO;
import com.imperialm.imiservices.dto.RetentionGraphDTO;
import com.imperialm.imiservices.dto.RewardRedemptionDetailsDTO;
import com.imperialm.imiservices.dto.RewardRedemptionGraphDTO;
import com.imperialm.imiservices.dto.SIRewardsDetailsDTO;
import com.imperialm.imiservices.dto.SIRewardsDetailsGraphDTO;
import com.imperialm.imiservices.dto.SIRewardsYOYDetailsDTO;
import com.imperialm.imiservices.dto.SIRewardsYOYGraphDTO;
import com.imperialm.imiservices.dto.SummaryProgramRewardDetailsDTO;
import com.imperialm.imiservices.dto.SummaryProgramRewardGraphDTO;
import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.security.JwtTokenUtil;
import com.imperialm.imiservices.services.DashboardServiceImpl;
import com.imperialm.imiservices.services.UserServiceImpl;

@RestController
public class TablesController {

	@Autowired
	private DashboardServiceImpl dashService;
	
	  @Value("${jwt.header}")
	    private String tokenHeader;

	    @Autowired
	    private JwtTokenUtil jwtTokenUtil;

	    @Autowired
	    private UserServiceImpl userDetailsService;

	
	@RequestMapping(value ="/services/data/{chartId}/{territory}", method = RequestMethod.GET)
	public @ResponseBody Object findTilesListByRole(@PathVariable(value="chartId") String id, @PathVariable(value="territory") String territory, HttpServletRequest request) {
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

		
		//divide the switch statement to functions
		switch(id){
		case "9":
		{
			List<BrainBoostWinnersDetailsDTO> result = new ArrayList<BrainBoostWinnersDetailsDTO>();
			if(territory.length() >= 4 && territory.length() <= 5 && territory.contains("-")){
				List<String> filters = new ArrayList<String>();
				filters.add(territory);
				List<BrainBoostWinndersGraphDTO> sublist = this.dashService.getBrainBoostWinddersGraphByTerritory(filters);
				for(BrainBoostWinndersGraphDTO item: sublist){
						List<BrainBoostWinnersDetailsDTO> participants = this.dashService.getBrainBoostWinnersDetailsDTOByDealerCode(item.getChildTerritory(), "YTD");
						result.addAll(participants);
				}
			}else if (territory.length() > 4 && !territory.contains("-")){
				 return this.dashService.getBrainBoostWinnersDetailsDTOByDealerCode(territory, "YTD");
			}
			return result;
		}
		case "10":
		{
			List<CertProfsExpertDetailsDTO> result = new ArrayList<CertProfsExpertDetailsDTO>();
			if(territory.length() >= 4 && territory.length() <= 5 && territory.contains("-")){
				//District info get dealers from graph and participants
				List<String> filters = new ArrayList<String>(Arrays.asList(territory));
				List<CertProfsExpertGraphDTO> sublist = this.dashService.getExpertPointsEarnedByParentTerritory(filters);
				for(CertProfsExpertGraphDTO item: sublist){
						result.addAll(this.dashService.getCertProfsExpertDetailsByDealerCodeANDCertType(item.getChildTerritory(), "JEEP%"));
						result.addAll(this.dashService.getCertProfsExpertDetailsByDealerCodeANDCertType(item.getChildTerritory(), "RAM%"));
						result.addAll(this.dashService.getCertProfsExpertDetailsByDealerCodeANDCertType(item.getChildTerritory(), "TECH%"));
				}
			}else if (territory.length() > 4 && !territory.contains("-")){
				result.addAll(this.dashService.getCertProfsExpertDetailsByDealerCodeANDCertType(territory, "JEEP%"));
				result.addAll(this.dashService.getCertProfsExpertDetailsByDealerCodeANDCertType(territory, "RAM%"));
				result.addAll(this.dashService.getCertProfsExpertDetailsByDealerCodeANDCertType(territory, "TECH%"));
			}
			return result;
		}
		case "11":
		{
			List<CertProfsExpertDetailsDTO> result = new ArrayList<CertProfsExpertDetailsDTO>();
			if(territory.length() >= 4 && territory.length() <= 5 && territory.contains("-")){
				//District info get dealers from graph and participants
				List<String> filters = new ArrayList<String>(Arrays.asList(territory));
				List<CertProfsExpertGraphDTO> sublist = this.dashService.getExpertPointsEarnedByParentTerritory(filters);
				for(CertProfsExpertGraphDTO item: sublist){
					if(item.getCertType().contains("JEEP")){
						result.addAll(this.dashService.getCertProfsExpertDetailsByDealerCodeANDCertType(item.getChildTerritory(), "JEEP%"));
					}else if (item.getCertType().contains("RAM")){
						result.addAll(this.dashService.getCertProfsExpertDetailsByDealerCodeANDCertType(item.getChildTerritory(), "RAM%"));
					}else if (item.getCertType().contains("TECH")){
						result.addAll(this.dashService.getCertProfsExpertDetailsByDealerCodeANDCertType(item.getChildTerritory(), "TECH%"));
					}
				}
			}else if (territory.length() > 4 && !territory.contains("-")){
				result.addAll(this.dashService.getCertProfsExpertDetailsByDealerCodeANDCertType(territory, "JEEP%"));
				result.addAll(this.dashService.getCertProfsExpertDetailsByDealerCodeANDCertType(territory, "RAM%"));
				result.addAll(this.dashService.getCertProfsExpertDetailsByDealerCodeANDCertType(territory, "TECH%"));
			}
			return result;
		}
		case "12":
		{
			List<BrainBoostWinnersDetailsDTO> result = new ArrayList<BrainBoostWinnersDetailsDTO>();
			if(territory.length() >= 4 && territory.length() <= 5 && territory.contains("-")){
				List<String> filters = new ArrayList<String>();
				filters.add(territory);
				List<BrainBoostWinndersGraphDTO> sublist = this.dashService.getBrainBoostWinddersGraphByTerritory(filters);
				for(BrainBoostWinndersGraphDTO item: sublist){
						List<BrainBoostWinnersDetailsDTO> participants = this.dashService.getBrainBoostWinnersDetailsDTOByDealerCode(item.getChildTerritory(), "YTD");
						result.addAll(participants);
				}
			}else if (territory.length() > 4 && !territory.contains("-")){
				 return this.dashService.getBrainBoostWinnersDetailsDTOByDealerCode(territory, "YTD");
			}
			return result;
		}
		case "13":
		{
			List<CertProfsWinnersDetailsDTO> result = new ArrayList<CertProfsWinnersDetailsDTO>();
			if(territory.length() >= 4 && territory.length() <= 5 && territory.contains("-")){
				List<String> filters = new ArrayList<String>();
				filters.add(territory);
				List<CertProfsWinnersGraphDTO> sublist = this.dashService.getCertProfsWinnersGraphByTerritory(filters);
				for(CertProfsWinnersGraphDTO item: sublist){
						List<CertProfsWinnersDetailsDTO> participants = this.dashService.getCertProfsWinnersDetailsByDealerCodeGroupBySID(item.getChildTerritory());
						result.addAll(participants);
				}
			}else if (territory.length() > 4 && !territory.contains("-")){
				 return this.dashService.getCertProfsWinnersDetailsByDealerCodeGroupBySID(territory);
			}
			return result;
		}
		case "19":
		{
			List<MyfcaMSERTotalEarningsDetailsDTO> result = new ArrayList<MyfcaMSERTotalEarningsDetailsDTO>();
			if(territory.length() >= 4 && territory.length() <= 5 && territory.contains("-")){
				List<String> filters = new ArrayList<String>();
				filters.add(territory);
				List<MyfcaMSERTotalEarningsDTO> sublist = this.dashService.getMSERGraphByTerritoryAndToggle(territory, "YTD");
				Set<String> p = new LinkedHashSet<>();
				
				for(MyfcaMSERTotalEarningsDTO item: sublist){
					p.add(item.getChild());
			}
				
				for(String item: p){
						List<MyfcaMSERTotalEarningsDetailsDTO> participants = this.dashService.getMSERGraphDetailsByDealerCode(item);
						result.addAll(participants);
				}
			}else if (territory.length() > 4 && !territory.contains("-")){
				 return this.dashService.getMSERGraphDetailsByDealerCode(territory);
			}
			return result;
		}
		case "20":
		{
			List<SIRewardsYOYDetailsDTO> result = new ArrayList<SIRewardsYOYDetailsDTO>();
			if(territory.length() >= 4 && territory.length() <= 5 && territory.contains("-")){
				List<String> filters = new ArrayList<String>();
				filters.add(territory);
				List<SIRewardsYOYGraphDTO> sublist = this.dashService.getSIRewardsYOYGraphByTerritoryAndToggle(territory, "YTD");
				for(SIRewardsYOYGraphDTO item: sublist){
						//List<SIRewardsYOYDetailsDTO> participants = this.dashService.getSIre(item.getChildTerritory(), "YTD");
						//result.addAll(participants);
				}
			}else if (territory.length() > 4 && !territory.contains("-")){
				 return this.dashService.getRewardRedemptionDetailsByDealer(territory);
			}
			return result;
		}
		case "22":
		{
			List<SIRewardsDetailsDTO> result = new ArrayList<SIRewardsDetailsDTO>();
			if(territory.length() >= 4 && territory.length() <= 5 && territory.contains("-")){
				List<String> filters = new ArrayList<String>();
				filters.add(territory);
				List<SIRewardsDetailsGraphDTO> sublist = this.dashService.getSIRewardsDetailsGraphByTerritoryAndToggle(territory, "QTD");
				for(SIRewardsDetailsGraphDTO item: sublist){
						List<SIRewardsDetailsDTO> participants = this.dashService.getSIRewardsDetailsByDealerCodeAndToggle(item.getChildTerritory(), "QTD", this.getCurrentQuarter());
						result.addAll(participants);
				}
			}else if (territory.length() > 4 && !territory.contains("-")){
				 return this.dashService.getSIRewardsDetailsByDealerCodeAndToggle(territory, "QTD", this.getCurrentQuarter());
			}
			return result;
		}
		case "23":
		{	
			List<RewardRedemptionDetailsDTO> result = new ArrayList<RewardRedemptionDetailsDTO>();
			if(territory.length() >= 4 && territory.length() <= 5 && territory.contains("-")){
				List<String> filters = new ArrayList<String>();
				filters.add(territory);
				List<RewardRedemptionGraphDTO> sublist = this.dashService.getRewardRedemptionGraphByParentTerritoryList(Arrays.asList(territory));
				for(RewardRedemptionGraphDTO item: sublist){
						List<RewardRedemptionDetailsDTO> participants = this.dashService.getRewardRedemptionDetailsByDealer(item.getChildTerritory());
						result.addAll(participants);
				}
			}else if (territory.length() > 4 && !territory.contains("-")){
				 return this.dashService.getRewardRedemptionDetailsByDealer(territory);
			}
			return result;
		}
		case "31":
		{
			List<RetentionDetailsDTO> result = new ArrayList<RetentionDetailsDTO>();
			if(territory.length() >= 4 && territory.length() <= 5 && territory.contains("-")){
				List<String> filters = new ArrayList<String>();
				filters.add(territory);
				List<RetentionGraphDTO> sublist = this.dashService.getRetentionGraphByParentTerritoryList(filters);
				for(RetentionGraphDTO item: sublist){
						List<RetentionDetailsDTO> participants = this.dashService.getRetentionDetailsByDealerCode(item.getChildTerritory());
						result.addAll(participants);
				}
			}else if (territory.length() > 4 && !territory.contains("-")){
				 return this.dashService.getRetentionDetailsByDealerCode(territory);
			}
			return result;
		}
		case "32":
		{
			List<CustomerFirstDetailsDTO> result = new ArrayList<CustomerFirstDetailsDTO>();
			if(territory.length() >= 4 && territory.length() <= 5 && territory.contains("-")){
				List<String> filters = new ArrayList<String>();
				filters.add(territory);
				List<CustomerFirstGraphDTO> sublist = this.dashService.getCustomerFirstGraphByParentTerritoryAndToggle(filters, "Total");
				for(CustomerFirstGraphDTO item: sublist){
						List<CustomerFirstDetailsDTO> participants = this.dashService.getCustomerFirstDetailsByDealerCodeAndToggle(item.getChildTerritory(), "Total");
						result.addAll(participants);
				}
			}else if (territory.length() > 4 && !territory.contains("-")){
				 return this.dashService.getCustomerFirstDetailsByDealerCodeAndToggle(territory, "Total");
			}
			return result;
		}
		case "33":
		{
			List<CustomerFirstDetailsDTO> result = new ArrayList<CustomerFirstDetailsDTO>();
			if(territory.length() >= 4 && territory.length() <= 5 && territory.contains("-")){
				List<String> filters = new ArrayList<String>();
				filters.add(territory);
				List<CustomerFirstGraphDTO> sublist = this.dashService.getCustomerFirstGraphByParentTerritoryAndToggle(filters, "Percentage");
				for(CustomerFirstGraphDTO item: sublist){
						List<CustomerFirstDetailsDTO> participants = this.dashService.getCustomerFirstDetailsByDealerCodeAndToggle(item.getChildTerritory(), "Percentage");
						result.addAll(participants);
				}
			}else if (territory.length() > 4 && !territory.contains("-")){
				 return this.dashService.getCustomerFirstDetailsByDealerCodeAndToggle(territory, "Percentage");
			}
			return result;
		}	
		case "36":
		{
			List<SummaryProgramRewardDetailsDTO> result = new ArrayList<SummaryProgramRewardDetailsDTO>();
			if(territory.length() >= 4 && territory.length() <= 5 && territory.contains("-")){
				List<String> filters = new ArrayList<String>();
				filters.add(territory);
				List<SummaryProgramRewardGraphDTO> sublist = this.dashService.getSummaryProgramRewardGraphByParentTerritoryYTD(filters);
				for(SummaryProgramRewardGraphDTO item: sublist){
						List<SummaryProgramRewardDetailsDTO> participants = this.dashService.getSummaryProgramRewardDetailsByDealerCodeYTD(item.getChild());
						result.addAll(participants);
				}
			}else if (territory.length() > 4 && !territory.contains("-")){
				 return this.dashService.getSummaryProgramRewardDetailsByDealerCodeYTD(territory);
			}
			return result;
		}
		default:
			return "No such service call exists.";
		}
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
	
}
