package com.imperialm.imiservices.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.imperialm.imiservices.dto.BrainBoostWinndersGraphDTO;
import com.imperialm.imiservices.dto.CertProfsExpertGraphDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersGraphDTO;
import com.imperialm.imiservices.dto.DashboardDTO;
import com.imperialm.imiservices.dto.MSEREarningsDTO;
import com.imperialm.imiservices.dto.MSERTopNDTO;
import com.imperialm.imiservices.dto.TTTAEnrolledDTO;
import com.imperialm.imiservices.dto.TTTATopNDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.model.Chart;
import com.imperialm.imiservices.model.ChartData;
import com.imperialm.imiservices.model.TopTenChart;
import com.imperialm.imiservices.model.TopTenDataTable;
import com.imperialm.imiservices.model.TopTenTableData;
import com.imperialm.imiservices.model.response.TotalName;
import com.imperialm.imiservices.services.DashboardService;
import com.imperialm.imiservices.services.MappingServiceImpl;
import com.imperialm.imiservices.util.IMIServicesConstants;

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
	
	@RequestMapping(value = "/myfcadashboard", method = RequestMethod.GET)
	public RedirectView myfcadashboard() {
		    RedirectView redirectView = new RedirectView("/", true);
		    return redirectView;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public RedirectView login() {
		    RedirectView redirectView = new RedirectView("/", true);
		    return redirectView;
	}
	
	@RequestMapping(value = "/services/tile/{chartId}", method = RequestMethod.GET)
	public @ResponseBody Object findTilesListByRole(@PathVariable(value="chartId") String id) {
		final InputRequest userRoleReq = new InputRequest("", "");
		
		//get token extract user info and use for the calls
		//divide the switch statement to functions
		switch(id){
		case "1":
		{
			List<MSEREarningsDTO> list = this.dashService.getEarningsByRole(userRoleReq);
			return this.mappingService.MapMSEREarningsDTOtoChart(list, "BC Total Earnings YTD", "", "Totsl Earnings", "", "bar_compound");
		}
		case "2":
		{
			//set datatables
			List<MSERTopNDTO> listAdvisors = this.dashService.getMSERTopTen("TOP SA", 5);
			List<MSERTopNDTO> listTechnicians = this.dashService.getMSERTopTen("TOP ST", 5);
			
			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("Excellence Card Awards", "Excellence Card Awards - Top 5 Technicians");
			
			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();
			
			List<String> tableheaders = new ArrayList<String>();
			//keeps this as example
			/*tableheaders.add("Order");
			tableheaders.add("Advisor BC, District, Dealer Code");
			tableheaders.add("Reward amount");*/
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listTechnicians, "Top 5 MTD Technicians Excellence Card Awards", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listAdvisors, "Top 5 MTD Advisors Excellence Card Awards", tableheaders));
			
			datatable.setTableData(tabledata);
			
			topTenChart.setDatatable(datatable);
			
			//set attributes
			TotalName dealerscount = this.dashService.getDealersCount();
			TotalName mtd = this.dashService.getMTDByProgramAndProgramgroup("Excellence Cards Awards MTD", "", "");
			TotalName ytd = this.dashService.getYTDByProgramAndProgramgroup("Excellence Cards Awards YTD", "", "");
			
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
			
			return topTenChart;
		}
		case "3":
		{
			List<MSERTopNDTO> listMOPARMTD = this.dashService.getMSERTopTen("MOPAR Parts Sold MTD", 3);
			List<MSERTopNDTO> listMOPARYTD = this.dashService.getMSERTopTen("MOPAR Parts Sold YTD", 3);
			
			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("","");
			
			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();
			
			List<String> tableheaders = new ArrayList<String>();
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMOPARMTD, "Top 3 MOPAR Parts Sold MTD", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMOPARYTD, "Top 3 MOPAR Parts Sold YTD", tableheaders));
			
			datatable.setTableData(tabledata);
			
			TotalName mtd = this.dashService.getMTDByProgramAndProgramgroup("Total Parts Earnings - MTD", "MOPAR Parts Sold", "MOPAR Parts Sold");
			TotalName ytd = this.dashService.getYTDByProgramAndProgramgroup("Total Parts Earnings - YTD", "MOPAR Parts Sold", "MOPAR Parts Sold");
			
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
			
						
			topTenChart.setDatatable(datatable);
			
			return topTenChart;
		}
		case "4":
		{
			List<MSERTopNDTO> listMVPMTD = this.dashService.getMSERTopTen("MVP Parts Sold MTD", 3);
			List<MSERTopNDTO> listMVPYTD = this.dashService.getMSERTopTen("MVP Parts Sold YTD", 3);
			
			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("","");
			
			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();
			
			List<String> tableheaders = new ArrayList<String>();
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMVPMTD, "Top 3 MVP Parts Sold MTD", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMVPYTD, "Top 3 MVP Parts Sold YTD", tableheaders));
			
			datatable.setTableData(tabledata);
			
			TotalName mtd = this.dashService.getMTDByProgramAndProgramgroup("Total Parts Earnings - MTD", "MVP Parts Sold", "MVP Parts Sold");
			TotalName ytd = this.dashService.getYTDByProgramAndProgramgroup("Total Parts Earnings - YTD", "MVP Parts Sold", "MVP Parts Sold");
			
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
						
			topTenChart.setDatatable(datatable);
			
			return topTenChart;
		}
		case "5":{
			List<MSERTopNDTO> listMMPMTD = this.dashService.getMSERTopTen("Magneti Marelli Parts Sold MTD", 3);
			List<MSERTopNDTO> listMMPYTD = this.dashService.getMSERTopTen("Magneti Marelli Parts Sold YTD", 3);
			
			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("","");
			
			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();
			
			List<String> tableheaders = new ArrayList<String>();
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMMPMTD, "Top 3 Magneti Marelli Parts Sold MTD", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMMPYTD, "Top 3 Magneti Marelli Parts Sold YTD", tableheaders));
			
			datatable.setTableData(tabledata);
			
			TotalName mtd = this.dashService.getMTDByProgramAndProgramgroup("Total Parts Earnings - MTD", "Magneti Marelli Parts Sold", "Magneti Marelli Parts Sold");
			TotalName ytd = this.dashService.getYTDByProgramAndProgramgroup("Total Parts Earnings - YTD", "Magneti Marelli Parts Sold", "Magneti Marelli Parts Sold");
			
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
						
			topTenChart.setDatatable(datatable);
			
			return topTenChart;
		}
		case "6":
		{
			List<MSERTopNDTO> listPCPMTD = this.dashService.getMSERTopTen("Parts Counter Parts Sold MTD", 3);
			List<MSERTopNDTO> listPCPYTD = this.dashService.getMSERTopTen("Parts Counter Parts Sold YTD", 3);
			
			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("","");
			
			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();
			
			List<String> tableheaders = new ArrayList<String>();
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listPCPMTD, "Top 3 Parts Counter Parts Sold MTD", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listPCPYTD, "Top 3 Parts Counter Parts Sold YTD", tableheaders));
			
			datatable.setTableData(tabledata);
			
			TotalName mtd = this.dashService.getMTDByProgramAndProgramgroup("Total Parts Earnings - MTD", "Parts Counter Parts Sold", "Parts Counter Parts Sold");
			TotalName ytd = this.dashService.getYTDByProgramAndProgramgroup("Total Parts Earnings - YTD", "Parts Counter Parts Sold", "Parts Counter Parts Sold");
			
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
						
			topTenChart.setDatatable(datatable);
			
			return topTenChart;
		}
		case "7":
		{
			List<MSERTopNDTO> listELPMTD = this.dashService.getMSERTopTen("Express Lane Parts Sold MTD", 3);
			List<MSERTopNDTO> listELPYTD = this.dashService.getMSERTopTen("Express Lane Parts Sold YTD", 3);
			
			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("","");
			
			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();
			
			List<String> tableheaders = new ArrayList<String>();
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listELPMTD, "Top 3 Express Lane Parts Sold MTD", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listELPYTD, "Top 3 Express Lane Parts Sold YTD", tableheaders));
			
			datatable.setTableData(tabledata);
			
			TotalName mtd = this.dashService.getMTDByProgramAndProgramgroup("Total Parts Earnings - MTD", "Express Lane Parts Sold", "Express Lane Parts Sold");
			TotalName ytd = this.dashService.getYTDByProgramAndProgramgroup("Total Parts Earnings - YTD", "Express Lane Parts Sold", "Express Lane Parts Sold");
			
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
						
			topTenChart.setDatatable(datatable);
			
			return topTenChart;
		}
		case "8":
		{
			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("","");
			
			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();
			
			datatable.setTableData(tabledata);
			
			topTenChart.setDatatable(datatable);
			
			TotalName dealerscount = this.dashService.getDealersCountWithPercentage();
			
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
			
			return topTenChart;
		}
		case "9":
		{
			// check for role, to know what data to display
			List<BrainBoostWinndersGraphDTO> listBC = this.dashService.getBrainBoostGraphBCData(true);
			List<BrainBoostWinndersGraphDTO> listBC_unfiltered = this.dashService.getBrainBoostGraphBCData(false);
			List<String> filters = new ArrayList<String>();
			
			for(BrainBoostWinndersGraphDTO item: listBC_unfiltered){
				filters.add(item.getChildTerritory());
			}
			
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			for(BrainBoostWinndersGraphDTO item: listBC_unfiltered){
				filters.add(item.getChildTerritory());
				if(!map.containsKey(item.getParentTerritory())){
					map.put(item.getParentTerritory(), new ArrayList<String>());
				}
				List<String> temp = map.get(item.getParentTerritory());
				temp.add(item.getChildTerritory());
				map.put(item.getParentTerritory(), temp);
			}
			List<BrainBoostWinndersGraphDTO> sublist = this.dashService.getBrainBoostGraphAllDistricData(filters);
			
			
			 ObjectMapper mapper = new ObjectMapper();
			 JsonNode obj = mapper.createObjectNode();
		        for(BrainBoostWinndersGraphDTO item: listBC){
		        	JsonNode BC = mapper.createObjectNode();
		        	ArrayNode dataArray = mapper.createArrayNode();
					for(BrainBoostWinndersGraphDTO object: sublist){
						if(map.get(item.getParentTerritory()).contains(object.getParentTerritory())){
							ChartData data = new ChartData(object.getParentTerritory(), Double.parseDouble(object.getWinners()));
							dataArray.addPOJO(data);
						}
					}
					
					((ObjectNode) BC).put("name", item.getParentTerritory());
					((ObjectNode) BC).putPOJO("data", dataArray);
					((ObjectNode) obj).putPOJO(item.getParentTerritory(), BC);
				}
			
			Chart chart = this.mappingService.BrainBoostWinndersGraphDTOtoChart(listBC, "BrainBoost - YTD Winners", "", "Business Center", "Totsl Winners", "column_drilldown", "Winners");
			chart.setDrilldownData(obj);
			return chart;
		}
		case "10":
		{
			// check for role, to know what data to display
			List<CertProfsExpertGraphDTO> list = this.dashService.getExpertPointsEarned();
			return this.mappingService.CertProfsExpertGraphDTOtoChartTotalPoints(list, "Expert Points Earned YTD", "", "", "Total Numbers", "column");
		}
		case "11":
		{
			// check for role, to know what data to display
			List<CertProfsExpertGraphDTO> list = this.dashService.getParticipantCompletedByProgram();
			return this.mappingService.CertProfsExpertGraphDTOtoChartCert(list, "Participants Completed By Program", "", "", "Total Numbers", "column_stack");
			
			//special mapping for stacked column
		}
		case "12":
		{
			// check for role, to know what data to display
			List<BrainBoostWinndersGraphDTO> listBC = this.dashService.getBrainBoostGraphBCData(true);
			List<BrainBoostWinndersGraphDTO> listBC_unfiltered = this.dashService.getBrainBoostGraphBCData(false);
			List<String> filters = new ArrayList<String>();
			
			for(BrainBoostWinndersGraphDTO item: listBC_unfiltered){
				filters.add(item.getChildTerritory());
			}
			
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			for(BrainBoostWinndersGraphDTO item: listBC_unfiltered){
				filters.add(item.getChildTerritory());
				if(!map.containsKey(item.getParentTerritory())){
					map.put(item.getParentTerritory(), new ArrayList<String>());
				}
				List<String> temp = map.get(item.getParentTerritory());
				temp.add(item.getChildTerritory());
				map.put(item.getParentTerritory(), temp);
			}
			List<BrainBoostWinndersGraphDTO> sublist = this.dashService.getBrainBoostGraphAllDistricData(filters);
			
			
			 ObjectMapper mapper = new ObjectMapper();
			 JsonNode obj = mapper.createObjectNode();
		        for(BrainBoostWinndersGraphDTO item: listBC){
		        	JsonNode BC = mapper.createObjectNode();
		        	ArrayNode dataArray = mapper.createArrayNode();
					for(BrainBoostWinndersGraphDTO object: sublist){
						if(map.get(item.getParentTerritory()).contains(object.getParentTerritory())){
							JsonNode element = mapper.createObjectNode();
							ArrayNode innerDataArray = mapper.createArrayNode();
							
							((ObjectNode) element).put("name", object.getParentTerritory());
							
							innerDataArray.addPOJO(new ChartData("Excellence Card", Double.parseDouble(object.getEarnings())));
							innerDataArray.addPOJO(new ChartData("Award Points", Double.parseDouble(object.getPoints())));
							
							((ObjectNode) element).putPOJO("data", innerDataArray);
							
							dataArray.addPOJO(element);
						}
					}
					
					((ObjectNode) BC).put("name", item.getParentTerritory());
					((ObjectNode) BC).putPOJO("data", dataArray);
					((ObjectNode) obj).putPOJO(item.getParentTerritory(), BC);
				}
			
			Chart chart = this.mappingService.BrainBoostWinndersGraphDTOtoChart(listBC, "Exellence Card Award And Award Points YTD", "", "", "", "column_compound", "Awards");
			chart.setDrilldownData(obj);
			return chart;
		}
		case "13":
		{
			// check for role, to know what data to display
			List<CertProfsWinnersGraphDTO> list = this.dashService.getBCCertifications();
			return this.mappingService.CertProfsWinnersGraphDTOtoChart(list, "Certified Proffessionals - YTD Certifications", "", "", "Total Certification", "column_stack");
			
			//special mapping for stacked column
		}
		case "14":
		{
			//set datatables
			List<TTTATopNDTO> listAdvisorsQTD = this.dashService.getTTTATopN("TOP SA QTD", 5);
			List<TTTATopNDTO> listAdvisorsYTD = this.dashService.getTTTATopN("TOP SA YTD", 5);
			
			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("View Top Advisors", "Top 5 Advisors - Highest average survey scores");
			
			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();
			
			List<String> tableheaders = new ArrayList<String>();
			//keeps this as example
			/*tableheaders.add("Order");
			tableheaders.add("Advisor BC, District, Dealer Code");
			tableheaders.add("Reward amount");*/
			
			tabledata.add(this.mappingService.MapTTTATopNDTOtoTopTenTableData(listAdvisorsQTD, "Top 5 Advisors QTD Highest average survey scores", tableheaders));	
			tabledata.add(this.mappingService.MapTTTATopNDTOtoTopTenTableData(listAdvisorsYTD, "Top 5 Advisors YTD Highest average survey scores", tableheaders));
			
			datatable.setTableData(tabledata);
			
			topTenChart.setDatatable(datatable);
			
			//set attributes
			TotalName dealerscount = this.dashService.getTTTAEnrollmentCount();
			TotalName dealerscount1 = this.dashService.getTTTAIncentiveEligibleSUM();
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount1));
			//topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			
			return topTenChart;
		}
		case "15":
		{
			//set datatables
			List<TTTATopNDTO> listAdvisorsQTD = this.dashService.getTTTATopN("TOP ST QTD", 5);
			List<TTTATopNDTO> listAdvisorsYTD = this.dashService.getTTTATopN("TOP ST YTD", 5);
			
			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("View Top Technicians", "Top 5 Technicians - Highest average survey scores");
			
			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();
			
			List<String> tableheaders = new ArrayList<String>();
			//keeps this as example
			/*tableheaders.add("Order");
			tableheaders.add("Advisor BC, District, Dealer Code");
			tableheaders.add("Reward amount");*/
			
			tabledata.add(this.mappingService.MapTTTATopNDTOtoTopTenTableData(listAdvisorsQTD, "Top 5 Technicians QTD Highest average survey scores", tableheaders));	
			tabledata.add(this.mappingService.MapTTTATopNDTOtoTopTenTableData(listAdvisorsYTD, "Top 5 Technicians YTD Highest average survey scores", tableheaders));
			
			datatable.setTableData(tabledata);
			
			topTenChart.setDatatable(datatable);
			
			//set attributes
			TotalName dealerscount = this.dashService.getTTTAEnrollmentCount();
			
			
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
			//topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			
			return topTenChart;
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
		default:
			return "No such service call exists.";
		}
	}

	
}
