package com.imperialm.imiservices.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imperialm.imiservices.dto.BrainBoostWinndersGraphDTO;
import com.imperialm.imiservices.dto.BrainBoostWinnersDetailsDTO;
import com.imperialm.imiservices.dto.CertProfsExpertGraphDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersGraphDTO;
import com.imperialm.imiservices.dto.MSEREarningsDTO;
import com.imperialm.imiservices.dto.MSERGraphDTO;
import com.imperialm.imiservices.dto.MSERTopNDTO;
import com.imperialm.imiservices.dto.RewardRedemptionGraphDTO;
import com.imperialm.imiservices.dto.SIRewardsDetailsGraphDTO;
import com.imperialm.imiservices.dto.SIRewardsYOYGraphDTO;
import com.imperialm.imiservices.dto.TTTAEnrolledDTO;
import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.model.Chart;
import com.imperialm.imiservices.model.ChartData;
import com.imperialm.imiservices.security.JwtTokenUtil;
import com.imperialm.imiservices.services.DashboardServiceImpl;
import com.imperialm.imiservices.services.MappingServiceImpl;
import com.imperialm.imiservices.services.UserServiceImpl;

@RestController
public class TablesController {

	private static Logger logger = LoggerFactory.getLogger(DashboardController.class);

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

	
	@RequestMapping(value ="/services/data/{chartId}/{territory}", method = RequestMethod.GET)
	public @ResponseBody Object findTilesListByRole(@PathVariable(value="chartId") String id, @PathVariable(value="territory") String territory, HttpServletRequest request) {
		final InputRequest userRoleReq = new InputRequest("", "");
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
			if(territory.length() >= 4 && territory.length() <= 5 && territory.contains("-")){
				//District info get dealers from graph and participants
				
				List<String> filters = new ArrayList<String>();
				filters.add(territory);
				List<BrainBoostWinndersGraphDTO> sublist = this.dashService.getBrainBoostWinddersGraphByTerritory(filters);
				
				Map<BrainBoostWinndersGraphDTO, List<BrainBoostWinnersDetailsDTO>> result = new HashMap<BrainBoostWinndersGraphDTO, List<BrainBoostWinnersDetailsDTO>>();
				
				for(BrainBoostWinndersGraphDTO item: sublist){
					if(!result.containsKey(item)){
						List<BrainBoostWinnersDetailsDTO> participants = this.dashService.getBrainBoostWinnersDetailsDTOByDealerCode(item.getChildTerritory(), "YTD");
						result.put(item, participants);
					}
				}
				return result;
			}else if (territory.length() > 4 && !territory.contains("-")){
				 return this.dashService.getBrainBoostWinnersDetailsDTOByDealerCode(territory, "YTD");
			}
		}
		case "10":
		{
			// check for role, to know what data to display
			List<CertProfsExpertGraphDTO> list = this.dashService.getExpertPointsEarned();
			return this.mappingService.CertProfsExpertGraphDTOtoChartTotalPoints(list, "Expert Points Earned YTD", "", "Business Center", "Total Points", "column");
		}
		case "11":
		{
			// check for role, to know what data to display
			List<CertProfsExpertGraphDTO> list = this.dashService.getParticipantCompletedByProgram();
			
			Chart chart = this.mappingService.CertProfsExpertGraphDTOtoChartCert(list, "Participants Completed By Program", "", "", "Total Numbers", "column_stack");
			Chart chart2 = this.mappingService.CertProfsExpertGraphDTOtoChartCert(list, "Participants Completed By Program", "", "", "Total Numbers", "column_stack");
			
			Map<String, Double> mapValues = new HashMap<String, Double>();
			Map<String, Double> mapValues2 = new HashMap<String, Double>();
	        for(CertProfsExpertGraphDTO item: list){
	        	if(item.getCertType().contains("JEEP")){
					mapValues.put(item.getParentTerritory(), (double)item.getCert());
	        	}else if(item.getCertType().contains("RAM")){
					mapValues2.put(item.getParentTerritory(), (double)item.getCert());
	        	}
				}
	        
	        List<ChartData> list1 = new ArrayList<ChartData>();
			list1.add(new ChartData("JEEP", 0));
			list1.add(new ChartData("RAM", 0));
			
			
			List<ChartData> a = new ArrayList<ChartData>(chart.getData());
			List<ChartData> b = new ArrayList<ChartData>(chart2.getData());
			
			double tempa =0;
			double tempb =0;
			for(ChartData item: a){
				for(ChartData var: item.getData()){
					if(var.getName().contains("JEEP"))
						tempa += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues.get(item.getName()));
				//item.addDataList(chartsMap.get(item.getName()));
			}
			
			for(ChartData item: b){
				for(ChartData var: item.getData()){
					if(var.getName().contains("RAM"))
						tempb += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues2.get(item.getName()));
				//item.addDataList(chartsMap2.get(item.getName()));
			}

			list1.get(0).setValue(tempa);
			list1.get(1).setValue(tempb);
			list1.get(0).setData(a);
			list1.get(1).setData(b);
			
			chart.setData(list1);
			
			
			return chart;
		}
		case "12":
		{
			List<BrainBoostWinndersGraphDTO> firstlevel = new ArrayList<BrainBoostWinndersGraphDTO>();
			List<BrainBoostWinndersGraphDTO> seacondlevel = new ArrayList<BrainBoostWinndersGraphDTO>();
			
			//get district -- dealers list
			List<String> filters = new ArrayList<String>();
			filters.add(territory);
			firstlevel = this.dashService.getBrainBoostWinddersGraphByTerritory(filters);
			
			//get dealer
			Set<String> set = new LinkedHashSet<String>();
			for(BrainBoostWinndersGraphDTO item: firstlevel){
				set.add(item.getChildTerritory());
			}
			
			filters = new ArrayList<String>(set);
			
			if(filters.size() > 0){
				seacondlevel = this.dashService.getBrainBoostWinddersGraphByTerritory(filters);
			}
			
			List<List<?>> result = new ArrayList<List<?>>();
			result.add(firstlevel);
			result.add(seacondlevel);
		        
			return result;
		}
		case "13":
		{
			List<CertProfsWinnersGraphDTO> firstlevel = new ArrayList<CertProfsWinnersGraphDTO>();
			List<CertProfsWinnersGraphDTO> seacondlevel = new ArrayList<CertProfsWinnersGraphDTO>();
			
			//get district -- dealers list
			List<String> filters = new ArrayList<String>();
			filters.add(territory);
			firstlevel = this.dashService.getCertProfsWinnersGraphByTerritory(filters);
			
			//get dealer
			Set<String> set = new LinkedHashSet<String>();
			for(CertProfsWinnersGraphDTO item: firstlevel){
				set.add(item.getChildTerritory());
			}
			
			filters = new ArrayList<String>(set);
			
			if(filters.size() > 0){
				seacondlevel = this.dashService.getCertProfsWinnersGraphByTerritory(filters);
			}
			
			List<List<?>> result = new ArrayList<List<?>>();
			result.add(firstlevel);
			result.add(seacondlevel);
			return result;
		}
		case "14":
		{
			//Normal Tile
			return null;
		}
		case "15":
		{
			//Normal Tile
			return null;
		}
		case "16":
		{
			List<TTTAEnrolledDTO> listEnrolled = this.dashService.getTTTAEnrollmentsBC(true);
			
			return this.mappingService.MapTTTAEnrolledDTOtoChart(listEnrolled, "# of Dealers Enrolled YTD", "", "Total Enrolled", "", "column_compound");
		}
		case "17":
		{
			List<TTTAEnrolledDTO> listNotEnrolled = this.dashService.getTTTAEnrollmentsBC(false);
			
			return this.mappingService.MapTTTAEnrolledDTOtoChart(listNotEnrolled, "# of Dealers Not Enrolled YTD", "", "Total Enrolled", "", "column_compound");
		}
		case "18":
		{
			//Normal Tile
			return null;
		}
		case "19":
		{
			/*List<MSERGraphDTO> listOfFirstLevel = this.dashService.getMSERGraphByTerritoryAndToggle("CA-H", "YTD");
			List<MSERGraphDetailsDTO> listOfSecondLevel = this.dashService.getMSERGraphDetialsByDealerCode();*/
			return null;
		}
		case "20":
		{
			// check for role, to know what data to display
			List<SIRewardsYOYGraphDTO> list1st = null;
			List<SIRewardsYOYGraphDTO> list1st_Filtered = null;
			List<String> filters = new ArrayList<String>();
			
			//check if nat or not if nat pull list of childeren and continue if not start from their
				list1st = this.dashService.getSIRewardsYOYGraphByTerritoryAndToggle("NAT", "YTD");
				
				for(SIRewardsYOYGraphDTO item: list1st){
					if(!filters.contains(item.getChildTerritory()))
						filters.add(item.getChildTerritory());
				}
				
				//list1st = this.dashService.getSIRewardsYOYGraphByTerritoryAndToggle(filters, "YTD");
				list1st_Filtered = this.dashService.getSIRewardsYOYGraphByTerritoryAndToggleFilterParent(filters, "YTD");
			
			
			Chart chart = this.mappingService.SIRewardsYOYGraphDTOtoChart(list1st_Filtered, "Service Incentive Reward Earnings YTD", "", "", "Total Total Dollars Earned", "column_compound");
			Chart chart2 = this.mappingService.SIRewardsYOYGraphDTOtoChart(list1st_Filtered, "Service Incentive Reward Earnings YTD", "", "", "Total Total Dollars Earned", "column_compound");
			
						
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap2 = new HashMap<String, List<ChartData>>();
					for(SIRewardsYOYGraphDTO item: list1st){
						if(!map.containsKey(item.getChildTerritory())){
							map.put(item.getChildTerritory(), new ArrayList<String>());
							chartsMap.put(item.getChildTerritory(), new ArrayList<ChartData>());
							chartsMap2.put(item.getChildTerritory(), new ArrayList<ChartData>());
						}
						List<String> temp = map.get(item.getChildTerritory());
						temp.add(item.getChildTerritory());
						map.put(item.getParentTerritory(), temp);
						
					}
			List<SIRewardsYOYGraphDTO> sublist = this.dashService.getSIRewardsYOYGraphByTerritoryAndToggle(filters, "YTD");

			Map<String, Double> mapValues = new HashMap<String, Double>();
			Map<String, Double> mapValues2 = new HashMap<String, Double>();
			     for(SIRewardsYOYGraphDTO item: list1st_Filtered){
					        	List<ChartData> list = new ArrayList<ChartData>();
					        	List<ChartData> list2 = new ArrayList<ChartData>();
								for(SIRewardsYOYGraphDTO object: sublist){
									if(map.get(item.getParentTerritory()).contains(object.getParentTerritory())){
										
										ChartData temp = new ChartData();
										temp.setName(object.getChildTerritory());
										temp.setValue(object.getEarnings2016YTD());

										
										list.add(temp);
										temp = new ChartData();
										temp.setName(object.getChildTerritory());
										temp.setValue(object.getEarnings2017YTD());
										list2.add(temp);
									}
								}
									chartsMap.put(item.getParentTerritory(), list);
									chartsMap2.put(item.getParentTerritory(), list2);
									mapValues.put(item.getParentTerritory(), item.getEarnings2016YTD());
									mapValues2.put(item.getParentTerritory(), item.getEarnings2017YTD());
							}
					        
					        List<ChartData> list1 = new ArrayList<ChartData>();
							list1.add(new ChartData("2016", 0));
							list1.add(new ChartData("2017", 0));
							
							
							List<ChartData> a = new ArrayList<ChartData>(chart.getData());
							List<ChartData> b = new ArrayList<ChartData>(chart2.getData());
							
							double tempa =0;
							double tempb =0;
							for(ChartData item: a){
								for(ChartData var: item.getData()){
									if(var.getName().equals("2016"))
										tempa = var.getValue();
								}
								item.setData(new ArrayList<ChartData>());
								item.setValue(mapValues.get(item.getName()));
								item.addDataList(chartsMap.get(item.getName()));
							}
							
							for(ChartData item: b){
								for(ChartData var: item.getData()){
									if(var.getName().equals("2017"))
										tempb = var.getValue();
								}
								item.setData(new ArrayList<ChartData>());
								item.setValue(mapValues2.get(item.getName()));
								item.addDataList(chartsMap2.get(item.getName()));
							}

							list1.get(0).setValue(tempa);
							list1.get(1).setValue(tempb);
							list1.get(0).setData(a);
							list1.get(1).setData(b);
							
							chart.setData(list1);
					        
						return chart;
		}
		case "21":
		{
			// check for role, to know what data to display
			List<SIRewardsDetailsGraphDTO> list1st = null;
			List<SIRewardsDetailsGraphDTO> list1st_Filtered = null;
			List<String> filters = new ArrayList<String>();
			
			//check if nat or not if nat pull list of childeren and continue if not start from their
				list1st = this.dashService.getSIRewardsDetailsGraphByTerritoryAndToggle("NAT", "QTD");
				
				for(SIRewardsDetailsGraphDTO item: list1st){
					if(!filters.contains(item.getChildTerritory()))
						filters.add(item.getChildTerritory());
				}
				
				//list1st = this.dashService.getSIRewardsYOYGraphByTerritoryAndToggle(filters, "YTD");
				list1st_Filtered = this.dashService.getSIRewardsDetailsGraphByTerritoryAndToggleFilterParent(filters, "QTD");
			
			
			Chart chart = this.mappingService.SIRewardsDetailsGraphDTOtoChart(list1st_Filtered, "Average Quarterly Survey Scores QTD", "", "", "Average Survey Scores", "column", "Average Score");
						
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap2 = new HashMap<String, List<ChartData>>();
					for(SIRewardsDetailsGraphDTO item: list1st){
						if(!map.containsKey(item.getChildTerritory())){
							map.put(item.getChildTerritory(), new ArrayList<String>());
							chartsMap.put(item.getChildTerritory(), new ArrayList<ChartData>());
							chartsMap2.put(item.getChildTerritory(), new ArrayList<ChartData>());
						}
						List<String> temp = map.get(item.getChildTerritory());
						temp.add(item.getChildTerritory());
						map.put(item.getParentTerritory(), temp);
						
					}
			List<SIRewardsDetailsGraphDTO> sublist = this.dashService.getSIRewardsDetailsGraphByTerritoryAndToggle(filters, "QTD");
			
			for(SIRewardsDetailsGraphDTO item: list1st_Filtered){
				List<ChartData> list = new ArrayList<ChartData>();
				for(SIRewardsDetailsGraphDTO object: sublist){
					if(map.get(item.getParentTerritory()).contains(object.getParentTerritory())){
						ChartData data = new ChartData(object.getChildTerritory(), object.getAvgSurveyScore());
						list.add(data);
					}
					chartsMap.put(item.getParentTerritory(), list);
				}
			}
			
			List<ChartData> a = chart.getData();
			for(ChartData item: a){
				item.addDataList(chartsMap.get(item.getName()));
			}
			
			return chart;
		}
		case "22":
		{
			// check for role, to know what data to display
			List<SIRewardsDetailsGraphDTO> list1st = null;
			List<SIRewardsDetailsGraphDTO> list1st_Filtered = null;
			List<String> filters = new ArrayList<String>();
			
			//check if nat or not if nat pull list of childeren and continue if not start from their
				list1st = this.dashService.getSIRewardsDetailsGraphByTerritoryAndToggle("NAT", "QTD");
				
				for(SIRewardsDetailsGraphDTO item: list1st){
					if(!filters.contains(item.getChildTerritory()))
						filters.add(item.getChildTerritory());
				}
				
				//list1st = this.dashService.getSIRewardsYOYGraphByTerritoryAndToggle(filters, "YTD");
				list1st_Filtered = this.dashService.getSIRewardsDetailsGraphByTerritoryAndToggleFilterParent(filters, "QTD");
			
			
			Chart chart = this.mappingService.SIRewardsDetailsGraphDTOtoChart(list1st_Filtered, "Projected Service Incentive Earnings QTD", "", "", "Projected Earnings", "column", "Projected");
						
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap2 = new HashMap<String, List<ChartData>>();
					for(SIRewardsDetailsGraphDTO item: list1st){
						if(!map.containsKey(item.getChildTerritory())){
							map.put(item.getChildTerritory(), new ArrayList<String>());
							chartsMap.put(item.getChildTerritory(), new ArrayList<ChartData>());
							chartsMap2.put(item.getChildTerritory(), new ArrayList<ChartData>());
						}
						List<String> temp = map.get(item.getChildTerritory());
						temp.add(item.getChildTerritory());
						map.put(item.getParentTerritory(), temp);
						
					}
			List<SIRewardsDetailsGraphDTO> sublist = this.dashService.getSIRewardsDetailsGraphByTerritoryAndToggle(filters, "QTD");
			
			for(SIRewardsDetailsGraphDTO item: list1st_Filtered){
				List<ChartData> list = new ArrayList<ChartData>();
				for(SIRewardsDetailsGraphDTO object: sublist){
					if(map.get(item.getParentTerritory()).contains(object.getParentTerritory())){
						ChartData data = new ChartData(object.getChildTerritory(), object.getProjectedEarnings());
						list.add(data);
					}
					chartsMap.put(item.getParentTerritory(), list);
				}
			}
			
			List<ChartData> a = chart.getData();
			for(ChartData item: a){
				item.addDataList(chartsMap.get(item.getName()));
			}
			
			return chart;
		}
		case "23":
		{
			// check for role, to know what data to display
			List<RewardRedemptionGraphDTO> list1st = null;
			List<RewardRedemptionGraphDTO> list1st_Filtered = null;
			List<String> filters = new ArrayList<String>();
			//add user access territory
			filters.add("NAT");
			//check if nat or not if nat pull list of childeren and continue if not start from their
				list1st = this.dashService.getRewardRedemptionGraphByParentTerritoryList(filters);
				filters = new ArrayList<String>();
				for(RewardRedemptionGraphDTO item: list1st){
					if(!filters.contains(item.getChildTerritory()))
						filters.add(item.getChildTerritory());
				}
				
				list1st_Filtered = this.dashService.getRewardRedemptionGraphByParentTerritoryListDistinct(filters);
			
			
			Chart chart = this.mappingService.RewardRedemptionGraphDTOtoChart(list1st_Filtered, "Total Program Points Earned YTD", "", "", "Points Earned", "column", "EarnedPoints");
						
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap2 = new HashMap<String, List<ChartData>>();
					for(RewardRedemptionGraphDTO item: list1st){
						if(!map.containsKey(item.getChildTerritory())){
							map.put(item.getChildTerritory(), new ArrayList<String>());
							chartsMap.put(item.getChildTerritory(), new ArrayList<ChartData>());
							chartsMap2.put(item.getChildTerritory(), new ArrayList<ChartData>());
						}
						List<String> temp = map.get(item.getChildTerritory());
						temp.add(item.getChildTerritory());
						map.put(item.getParentTerritory(), temp);
						
					}
			List<RewardRedemptionGraphDTO> sublist = this.dashService.getRewardRedemptionGraphByParentTerritoryList(filters);
			
			for(RewardRedemptionGraphDTO item: list1st_Filtered){
				List<ChartData> list = new ArrayList<ChartData>();
				for(RewardRedemptionGraphDTO object: sublist){
					if(map.get(item.getParentTerritory()).contains(object.getParentTerritory())){
						ChartData data = new ChartData(object.getChildTerritory(), object.getEarnedPoints());
						list.add(data);
					}
					chartsMap.put(item.getParentTerritory(), list);
				}
			}
			
			List<ChartData> a = chart.getData();
			for(ChartData item: a){
				item.addDataList(chartsMap.get(item.getName()));
			}
			
			return chart;
		}
		case "24":
		{
			//NOT A CHART
			
			return null;
		}
		case "25":
		{
			// NOT A Graph Chart
			return null;
		}
		default:
			return "No such service call exists.";
		}
	}

	
}
