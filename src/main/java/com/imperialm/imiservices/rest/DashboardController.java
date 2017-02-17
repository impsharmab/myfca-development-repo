package com.imperialm.imiservices.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
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
import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.model.Chart;
import com.imperialm.imiservices.model.ChartData;
import com.imperialm.imiservices.model.TopTenChart;
import com.imperialm.imiservices.model.TopTenDataTable;
import com.imperialm.imiservices.model.TopTenTableData;
import com.imperialm.imiservices.model.response.TotalName;
import com.imperialm.imiservices.security.JwtTokenUtil;
import com.imperialm.imiservices.services.DashboardService;
import com.imperialm.imiservices.services.MappingServiceImpl;
import com.imperialm.imiservices.services.UserServiceImpl;
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
	
	  @Value("${jwt.header}")
	    private String tokenHeader;

	    @Autowired
	    private JwtTokenUtil jwtTokenUtil;

	    @Autowired
	    private UserServiceImpl userDetailsService;

	

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
	public @ResponseBody Object findTilesListByRole(@PathVariable(value="chartId") String id, HttpServletRequest request) {
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
	    	 return "Filed to check Token";
		}

		
		//divide the switch statement to functions
		switch(id){
		case "1":
		{
			List<MSEREarningsDTO> list = this.dashService.getEarningsByRole(userRoleReq);
			
			
			Chart chart = this.mappingService.MapMSEREarningsDTOtoChart(list, "BC Total Earnings YTD", "", "Totsl Earnings", "", "bar_compound");
			Chart chart2 = this.mappingService.MapMSEREarningsDTOtoChart(list, "BC Total Earnings YTD", "", "Totsl Earnings", "", "bar_compound");
			Chart chart3 = this.mappingService.MapMSEREarningsDTOtoChart(list, "BC Total Earnings YTD", "", "Totsl Earnings", "", "bar_compound");
			Chart chart4 = this.mappingService.MapMSEREarningsDTOtoChart(list, "BC Total Earnings YTD", "", "Totsl Earnings", "", "bar_compound");
			Chart chart5 = this.mappingService.MapMSEREarningsDTOtoChart(list, "BC Total Earnings YTD", "", "Totsl Earnings", "", "bar_compound");
			Chart chart6 = this.mappingService.MapMSEREarningsDTOtoChart(list, "BC Total Earnings YTD", "", "Totsl Earnings", "", "bar_compound");
			Chart chart7 = this.mappingService.MapMSEREarningsDTOtoChart(list, "BC Total Earnings YTD", "", "Totsl Earnings", "", "bar_compound");
			
			Map<String, Double> mapValues = new HashMap<String, Double>();
			Map<String, Double> mapValues2 = new HashMap<String, Double>();
			Map<String, Double> mapValues3 = new HashMap<String, Double>();
			Map<String, Double> mapValues4 = new HashMap<String, Double>();
			Map<String, Double> mapValues5 = new HashMap<String, Double>();
			Map<String, Double> mapValues6 = new HashMap<String, Double>();
			Map<String, Double> mapValues7 = new HashMap<String, Double>();
	        for(MSEREarningsDTO item: list){
					mapValues.put(item.getTerritory(), item.getExpressLane());
					mapValues2.put(item.getTerritory(), item.getMagnetiMarelli());
					mapValues3.put(item.getTerritory(), item.getMoparParts());
					mapValues4.put(item.getTerritory(), item.getMvp());
					mapValues5.put(item.getTerritory(), item.getPartsCounter());
					mapValues6.put(item.getTerritory(), item.getuConnect());
					mapValues7.put(item.getTerritory(), item.getWiAdvisor());
				}
	        
	        List<ChartData> list1 = new ArrayList<ChartData>();
			list1.add(new ChartData("Express Lane", 0));
			list1.add(new ChartData("Magneti Marelli", 0));
			list1.add(new ChartData("Mopar Parts", 0));
			list1.add(new ChartData("MVP", 0));
			list1.add(new ChartData("Parts Counter", 0));
			list1.add(new ChartData("uConnect", 0));
			list1.add(new ChartData("WiAdvisor", 0));
			
			
			List<ChartData> a = new ArrayList<ChartData>(chart.getData());
			List<ChartData> b = new ArrayList<ChartData>(chart2.getData());
			List<ChartData> c = new ArrayList<ChartData>(chart3.getData());
			List<ChartData> d = new ArrayList<ChartData>(chart4.getData());
			List<ChartData> e = new ArrayList<ChartData>(chart5.getData());
			List<ChartData> f = new ArrayList<ChartData>(chart6.getData());
			List<ChartData> g = new ArrayList<ChartData>(chart7.getData());
			
			double tempa =0;
			double tempb =0;
			double tempc =0;
			double tempd =0;
			double tempe =0;
			double tempf =0;
			double tempg =0;
			for(ChartData item: a){
				for(ChartData var: item.getData()){
					if(var.getName().contains("Express"))
						tempa += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues.get(item.getName()));
				//item.addDataList(chartsMap.get(item.getName()));
			}
			
			for(ChartData item: b){
				for(ChartData var: item.getData()){
					if(var.getName().contains("Magneti"))
						tempb += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues2.get(item.getName()));
				//item.addDataList(chartsMap2.get(item.getName()));
			}
			
			for(ChartData item: c){
				for(ChartData var: item.getData()){
					if(var.getName().contains("Mopar"))
						tempc += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues2.get(item.getName()));
				//item.addDataList(chartsMap2.get(item.getName()));
			}
			
			for(ChartData item: d){
				for(ChartData var: item.getData()){
					if(var.getName().contains("MVP"))
						tempd += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues2.get(item.getName()));
				//item.addDataList(chartsMap2.get(item.getName()));
			}
			
			for(ChartData item: e){
				for(ChartData var: item.getData()){
					if(var.getName().contains("Counter"))
						tempe += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues2.get(item.getName()));
				//item.addDataList(chartsMap2.get(item.getName()));
			}
			
			for(ChartData item: f){
				for(ChartData var: item.getData()){
					if(var.getName().contains("uConnect"))
						tempf += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues2.get(item.getName()));
				//item.addDataList(chartsMap2.get(item.getName()));
			}
			
			for(ChartData item: g){
				for(ChartData var: item.getData()){
					if(var.getName().contains("WiAdvisor"))
						tempg += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues2.get(item.getName()));
				//item.addDataList(chartsMap2.get(item.getName()));
			}
			
			

			list1.get(0).setValue(tempa);
			list1.get(1).setValue(tempb);
			list1.get(2).setValue(tempc);
			list1.get(3).setValue(tempd);
			list1.get(4).setValue(tempe);
			list1.get(5).setValue(tempf);
			list1.get(6).setValue(tempg);
			list1.get(0).setData(a);
			list1.get(1).setData(b);
			list1.get(2).setData(c);
			list1.get(3).setData(d);
			list1.get(4).setData(e);
			list1.get(5).setData(f);
			list1.get(6).setData(g);
			
			chart.setData(list1);
			
			
			return chart;
			
			
			//return this.mappingService.MapMSEREarningsDTOtoChart(list, "BC Total Earnings YTD", "", "Totsl Earnings", "", "bar_compound");
		}
		case "2":
		{
			//set datatables
			List<MSERTopNDTO> listAdvisors = this.dashService.getMSERTopTen("TOP SA", 10, null, null);
			List<MSERTopNDTO> listTechnicians = this.dashService.getMSERTopTen("TOP ST", 10, null, null);
			
			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatableT = new TopTenDataTable("TOP 10 Technicians Excellence Card Awards"); //, "Excellence Card Awards - Top 5 Technicians"
			TopTenDataTable datatableA = new TopTenDataTable("TOP 10 Advisors Excellence Card Awards");
			List<TopTenTableData> tabledataA = new ArrayList<TopTenTableData>();
			List<TopTenTableData> tabledataT = new ArrayList<TopTenTableData>();
			
			List<String> tableheaders = new ArrayList<String>();
			//keeps this as example
			tableheaders.add("Name");
			tableheaders.add("Dealership");
			tableheaders.add("BusinessCenter");
			tableheaders.add("Total Awards");
			
			tabledataT.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listTechnicians, "Top 10 MTD Technicians Excellence Card Awards", tableheaders));
			tabledataT.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listTechnicians, "Top 10 YTD Technicians Excellence Card Awards", tableheaders));
			tabledataT.get(0).setTabName("MTD");
			tabledataT.get(1).setTabName("YTD");
			tabledataA.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listAdvisors, "Top 10 MTD Advisors Excellence Card Awards", tableheaders));
			tabledataA.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listAdvisors, "Top 10 YTD Advisors Excellence Card Awards", tableheaders));
			tabledataA.get(0).setTabName("MTD");
			tabledataA.get(1).setTabName("YTD");
			
			datatableT.setData(tabledataT);
			datatableA.setData(tabledataA);
			
			topTenChart.setTop10_advisors(datatableA);
			topTenChart.setTop10_technicians(datatableT);
			
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
			List<MSERTopNDTO> listMOPARMTD = this.dashService.getMSERTopTen("Top 3 Parts", 3,"Mopar Parts" , "MTD");
			List<MSERTopNDTO> listMOPARYTD = this.dashService.getMSERTopTen("Top 3 Parts", 3,"Mopar Parts" , "YTD");
			
			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("");
			
			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();
			
			List<String> tableheaders = new ArrayList<String>();
			tableheaders.add("Parts Name");
			tableheaders.add("Volume Sold");
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMOPARMTD, "Top 3 MOPAR Parts Sold MTD", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMOPARYTD, "Top 3 MOPAR Parts Sold YTD", tableheaders));
			
			datatable.setData(tabledata);
			
			//TotalName mtd = this.dashService.getMTDByProgramAndProgramgroup("Total Parts Earnings - MTD", "MOPAR Parts Sold", "MOPAR Parts Sold");
			//TotalName ytd = this.dashService.getYTDByProgramAndProgramgroup("Total Parts Earnings - YTD", "MOPAR Parts Sold", "MOPAR Parts Sold");
			
			
			TotalName mtd = new TotalName();
			TotalName ytd = new TotalName();
			MSERTopNDTO EarningsMTD = this.dashService.getMSERTopTen("Top 3 Earnings", 1, "MOPAR Parts", "MTD").get(0);
			MSERTopNDTO EarningsYTD = this.dashService.getMSERTopTen("Top 3 Earnings", 1, "MOPAR Parts", "YTD").get(0);
			mtd.setName("Total Parts Earnings - MTD");
			ytd.setName("Total Parts Earnings - YTD");
			mtd.setTotal("$" + EarningsMTD.getEarnings());
			mtd.setTotal("$" + EarningsYTD.getEarnings());
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
			
						
			topTenChart.setTop3(datatable.getData());
			
			return topTenChart;
		}
		case "4":
		{
			List<MSERTopNDTO> listMVPMTD = this.dashService.getMSERTopTen("Top 3 Parts", 3,"MVP" , "MTD");
			List<MSERTopNDTO> listMVPYTD = this.dashService.getMSERTopTen("Top 3 Parts", 3,"MVP" , "YTD");
			
			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("");
			
			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();
			
			List<String> tableheaders = new ArrayList<String>();
			tableheaders.add("Parts Name");
			tableheaders.add("Volume Sold");
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMVPMTD, "Top 3 MVP Parts Sold MTD", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMVPYTD, "Top 3 MVP Parts Sold YTD", tableheaders));
			
			datatable.setData(tabledata);
			
			//TotalName mtd = this.dashService.getMTDByProgramAndProgramgroup("Total Parts Earnings - MTD", "MVP Parts Sold", "MVP Parts Sold");
			//TotalName ytd = this.dashService.getYTDByProgramAndProgramgroup("Total Parts Earnings - YTD", "MVP Parts Sold", "MVP Parts Sold");
			
			
			TotalName mtd = new TotalName();
			TotalName ytd = new TotalName();
			MSERTopNDTO EarningsMTD = this.dashService.getMSERTopTen("Top 3 Earnings", 1, "MVP", "MTD").get(0);
			MSERTopNDTO EarningsYTD = this.dashService.getMSERTopTen("Top 3 Earnings", 1, "MVP", "YTD").get(0);
			mtd.setName("Total Parts Earnings - MTD");
			ytd.setName("Total Parts Earnings - YTD");
			mtd.setTotal("$" + EarningsMTD.getEarnings());
			mtd.setTotal("$" + EarningsYTD.getEarnings());
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
			
						
			topTenChart.setTop3(datatable.getData());
			
			return topTenChart;
		}
		case "5":{
			List<MSERTopNDTO> listMMPMTD = this.dashService.getMSERTopTen("Top 3 Parts", 3,"Magneti Marelli" , "MTD");
			List<MSERTopNDTO> listMMPYTD = this.dashService.getMSERTopTen("Top 3 Parts", 3,"Magneti Marelli" , "YTD");
			
			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("");
			
			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();
			
			List<String> tableheaders = new ArrayList<String>();
			tableheaders.add("Parts Name");
			tableheaders.add("Volume Sold");
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMMPMTD, "Top 3 Magneti Marelli Parts Sold MTD", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMMPYTD, "Top 3 Magneti Marelli Parts Sold YTD", tableheaders));
			
			datatable.setData(tabledata);
			
			//TotalName mtd = this.dashService.getMTDByProgramAndProgramgroup("Total Parts Earnings - MTD", "Magneti Marelli Parts Sold", "Magneti Marelli Parts Sold");
			//TotalName ytd = this.dashService.getYTDByProgramAndProgramgroup("Total Parts Earnings - YTD", "Magneti Marelli Parts Sold", "Magneti Marelli Parts Sold");
			
			
			TotalName mtd = new TotalName();
			TotalName ytd = new TotalName();
			MSERTopNDTO EarningsMTD = this.dashService.getMSERTopTen("Top 3 Earnings", 1, "Magneti Marelli", "MTD").get(0);
			MSERTopNDTO EarningsYTD = this.dashService.getMSERTopTen("Top 3 Earnings", 1, "Magneti Marelli", "YTD").get(0);
			mtd.setName("Total Parts Earnings - MTD");
			ytd.setName("Total Parts Earnings - YTD");
			mtd.setTotal("$" + EarningsMTD.getEarnings());
			mtd.setTotal("$" + EarningsYTD.getEarnings());
			
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
						
			topTenChart.setTop3(datatable.getData());
			
			return topTenChart;
		}
		case "6":
		{
			List<MSERTopNDTO> listPCPMTD = this.dashService.getMSERTopTen("Top 3 Parts", 3,"Part Counter" , "MTD");
			List<MSERTopNDTO> listPCPYTD = this.dashService.getMSERTopTen("Top 3 Parts", 3,"Part Counter" , "YTD");
			
			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("");
			
			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();
			
			List<String> tableheaders = new ArrayList<String>();
			tableheaders.add("Parts Name");
			tableheaders.add("Volume Sold");
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listPCPMTD, "Top 3 Parts Counter Parts Sold MTD", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listPCPYTD, "Top 3 Parts Counter Parts Sold YTD", tableheaders));
			
			datatable.setData(tabledata);
			
			TotalName mtd = this.dashService.getMTDByProgramAndProgramgroup("Total Parts Earnings - MTD", "Parts Counter Parts Sold", "Parts Counter Parts Sold");
			TotalName ytd = this.dashService.getYTDByProgramAndProgramgroup("Total Parts Earnings - YTD", "Parts Counter Parts Sold", "Parts Counter Parts Sold");
			
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
						
			topTenChart.setTop3(datatable.getData());
			
			return topTenChart;
		}
		case "7":
		{
			List<MSERTopNDTO> listELPMTD = this.dashService.getMSERTopTen("Top 3 Parts", 3, "Express Lane", "MTD");
			List<MSERTopNDTO> listELPYTD = this.dashService.getMSERTopTen("Top 3 Parts", 3, "Express Lane", "YTD");
			
			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("");
			
			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();
			
			List<String> tableheaders = new ArrayList<String>();
			tableheaders.add("Parts Name");
			tableheaders.add("Volume Sold");
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listELPMTD, "Top 3 Express Lane Parts Sold MTD", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listELPYTD, "Top 3 Express Lane Parts Sold YTD", tableheaders));
			
			datatable.setData(tabledata);
			
			//TotalName mtd = this.dashService.getMTDByProgramAndProgramgroup("Total Parts Earnings - MTD", "Express Lane Parts Sold", "Express Lane Parts Sold");
			//TotalName ytd = this.dashService.getYTDByProgramAndProgramgroup("Total Parts Earnings - YTD", "Express Lane Parts Sold", "Express Lane Parts Sold");
			
			
			TotalName mtd = new TotalName();
			TotalName ytd = new TotalName();
			MSERTopNDTO EarningsMTD = this.dashService.getMSERTopTen("Top 3 Earnings", 1, "Express Lane", "MTD").get(0);
			MSERTopNDTO EarningsYTD = this.dashService.getMSERTopTen("Top 3 Earnings", 1, "Express Lane", "YTD").get(0);
			mtd.setName("Total Parts Earnings - MTD");
			ytd.setName("Total Parts Earnings - YTD");
			mtd.setTotal("$" + EarningsMTD.getEarnings());
			mtd.setTotal("$" + EarningsYTD.getEarnings());
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
						
			topTenChart.setTop3(datatable.getData());
			
			return topTenChart;
		}
		case "8":
		{
			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("");
			
			/*List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();
			
			datatable.setData(tabledata);
			
			topTenChart.setDatatable(datatable);*/
			
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
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			for(BrainBoostWinndersGraphDTO item: listBC_unfiltered){
				filters.add(item.getChildTerritory());
				if(!map.containsKey(item.getParentTerritory())){
					map.put(item.getParentTerritory(), new ArrayList<String>());
					chartsMap.put(item.getParentTerritory(), new ArrayList<ChartData>());
				}
				List<String> temp = map.get(item.getParentTerritory());
				temp.add(item.getChildTerritory());
				map.put(item.getParentTerritory(), temp);
			}
			List<BrainBoostWinndersGraphDTO> sublist = this.dashService.getBrainBoostGraphAllDistricData(filters);
			
			for(BrainBoostWinndersGraphDTO item: listBC){
				List<ChartData> list = new ArrayList<ChartData>();
				for(BrainBoostWinndersGraphDTO object: sublist){
					if(map.get(item.getParentTerritory()).contains(object.getParentTerritory())){
						ChartData data = new ChartData(object.getParentTerritory(), Double.parseDouble(object.getWinners()));
						list.add(data);
					}
					chartsMap.put(item.getParentTerritory(), list);
				}
			}
			
			/*
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
				}*/
			
			Chart chart = this.mappingService.BrainBoostWinndersGraphDTOtoChart(listBC, "BrainBoost - YTD Winners", "", "Business Center", "Total Winners", "column", "Winners");
			List<ChartData> a = chart.getData();
			for(ChartData item: a){
				item.addDataList(chartsMap.get(item.getName()));
			}
			
			//chart.setDrilldownData(obj);
			return chart;
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
					mapValues.put(item.getParentTerritory(), Double.parseDouble(item.getCert()));
	        	}else if(item.getCertType().contains("RAM")){
					mapValues2.put(item.getParentTerritory(), Double.parseDouble(item.getCert()));
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
			// check for role, to know what data to display
			List<BrainBoostWinndersGraphDTO> listBC = this.dashService.getBrainBoostGraphBCData(true);
			List<BrainBoostWinndersGraphDTO> listBC_unfiltered = this.dashService.getBrainBoostGraphBCData(false);
			List<String> filters = new ArrayList<String>();
			
			for(BrainBoostWinndersGraphDTO item: listBC_unfiltered){
				filters.add(item.getChildTerritory());
			}
			
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap2 = new HashMap<String, List<ChartData>>();
			for(BrainBoostWinndersGraphDTO item: listBC_unfiltered){
				filters.add(item.getChildTerritory());
				if(!map.containsKey(item.getParentTerritory())){
					map.put(item.getParentTerritory(), new ArrayList<String>());
					chartsMap.put(item.getParentTerritory(), new ArrayList<ChartData>());
					chartsMap2.put(item.getParentTerritory(), new ArrayList<ChartData>());
				}
				List<String> temp = map.get(item.getParentTerritory());
				temp.add(item.getChildTerritory());
				map.put(item.getParentTerritory(), temp);
			}
			List<BrainBoostWinndersGraphDTO> sublist = this.dashService.getBrainBoostGraphAllDistricData(filters);
			
			Chart chart = this.mappingService.BrainBoostWinndersGraphDTOtoChart(listBC, "Exellence Card Award And Award Points YTD", "", "", "", "column_compound", "Awards");
			Chart chart2 = this.mappingService.BrainBoostWinndersGraphDTOtoChart(listBC, "Exellence Card Award And Award Points YTD", "", "", "", "column_compound", "Awards");
			
			
			/*
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
		        */
		        
				Map<String, Double> mapValues = new HashMap<String, Double>();
				Map<String, Double> mapValues2 = new HashMap<String, Double>();
		        for(BrainBoostWinndersGraphDTO item: listBC){
		        	List<ChartData> list = new ArrayList<ChartData>();
		        	List<ChartData> list2 = new ArrayList<ChartData>();
					for(BrainBoostWinndersGraphDTO object: sublist){
						if(map.get(item.getParentTerritory()).contains(object.getParentTerritory())){
							/*ChartData temp = new ChartData();
							temp.setName(object.getParentTerritory());
							temp.addData((new ChartData("Excellence Card", Double.parseDouble(object.getEarnings()))));
							temp.addData(new ChartData("Award Points", Double.parseDouble(object.getEarnings())));*/
							
							ChartData temp = new ChartData();
							temp.setName(object.getParentTerritory());
							temp.setValue(Double.parseDouble(object.getEarnings()));
							//temp.addData((new ChartData("Certified", Double.parseDouble(object.getCertified()))));
							list.add(temp);
							temp = new ChartData();
							temp.setName(object.getParentTerritory());
							temp.setValue(Double.parseDouble(object.getPoints()));
							list2.add(temp);
							
							//list.add(temp);
						}
						chartsMap.put(item.getParentTerritory(), list);
						chartsMap2.put(item.getParentTerritory(), list2);
						mapValues.put(item.getParentTerritory(), Double.parseDouble(item.getEarnings()));
						mapValues2.put(item.getParentTerritory(), Double.parseDouble(item.getPoints()));
					}
				}
		        
		      
			
			//Chart chart = this.mappingService.BrainBoostWinndersGraphDTOtoChart(listBC, "Exellence Card Award And Award Points YTD", "", "", "", "column_compound", "Awards");
			//chart.setDrilldownData(obj);
			/*List<ChartData> a = chart.getData();
			for(ChartData item: a){
				item.setData(new ArrayList<ChartData>());
			}
			for(ChartData item: a){
				item.addDataList(chartsMap.get(item.getName()));
			}*/
		        List<ChartData> list1 = new ArrayList<ChartData>();
				list1.add(new ChartData("Excellence Card", 0));
				list1.add(new ChartData("Award Points", 0));
				
				
				List<ChartData> a = new ArrayList<ChartData>(chart.getData());
				List<ChartData> b = new ArrayList<ChartData>(chart2.getData());
				
				double tempa =0;
				double tempb =0;
				for(ChartData item: a){
					for(ChartData var: item.getData()){
						if(var.getName().equals("Excellence Card"))
							tempa = var.getValue();
					}
					item.setData(new ArrayList<ChartData>());
					item.setValue(mapValues.get(item.getName()));
					item.addDataList(chartsMap.get(item.getName()));
				}
				
				for(ChartData item: b){
					for(ChartData var: item.getData()){
						if(var.getName().equals("Award Points"))
							tempb = var.getValue();
					}
					item.setData(new ArrayList<ChartData>());
					item.setValue(mapValues2.get(item.getName()));
					item.addDataList(chartsMap2.get(item.getName()));
				}
				/*
				for(ChartData item: a){
					item.setValue(mapValues.get(item.getName()));
					item.addDataList(chartsMap.get(item.getName()));
				}
				for(ChartData item: b){
					item.setValue(mapValues2.get(item.getName()));
					item.addDataList(chartsMap2.get(item.getName()));
				}
				*/
				list1.get(0).setValue(tempa);
				list1.get(1).setValue(tempb);
				list1.get(0).setData(a);
				list1.get(1).setData(b);
				
				chart.setData(list1);
		        
			return chart;
		}
		case "13":
		{
			List<CertProfsWinnersGraphDTO> listBC = this.dashService.getBCCertifications(true);
			List<CertProfsWinnersGraphDTO> listBC_unfiltered = this.dashService.getBCCertifications(false);
			List<String> filters = new ArrayList<String>();
			
			for(CertProfsWinnersGraphDTO item: listBC_unfiltered){
				filters.add(item.getChildTerritory());
			}
			
			
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap2 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap3 = new HashMap<String, List<ChartData>>();
			for(CertProfsWinnersGraphDTO item: listBC_unfiltered){
				filters.add(item.getChildTerritory());
				if(!map.containsKey(item.getParentTerritory())){
					map.put(item.getParentTerritory(), new ArrayList<String>());
					//chartsMap.put(item.getParentTerritory(), new ArrayList<ChartData>());
					//chartsMap2.put(item.getParentTerritory(), new ArrayList<ChartData>());
					//chartsMap3.put(item.getParentTerritory(), new ArrayList<ChartData>());
				}
				List<String> temp = map.get(item.getParentTerritory());
				temp.add(item.getChildTerritory());
				map.put(item.getParentTerritory(), temp);
			}
			List<CertProfsWinnersGraphDTO> sublist = this.dashService.getCertProfsWinnersGraphAllDistricData(filters);
			
			
			Chart chart = this.mappingService.CertProfsWinnersGraphDTOtoChart(listBC, "Certified Proffessionals - YTD Certifications", "", "", "Total Certification", "column_stack");
			Chart chart2 = this.mappingService.CertProfsWinnersGraphDTOtoChart(listBC, "Certified Proffessionals - YTD Certifications", "", "", "Total Certification", "column_stack");
			Chart chart3 = this.mappingService.CertProfsWinnersGraphDTOtoChart(listBC, "Certified Proffessionals - YTD Certifications", "", "", "Total Certification", "column_stack");
			/*for(CertProfsWinnersGraphDTO item: listBC){
				List<ChartData> list = new ArrayList<ChartData>();
				for(CertProfsWinnersGraphDTO object: sublist){
					if(map.get(item.getParentTerritory()).contains(object.getParentTerritory())){
						ChartData temp = new ChartData();
						temp.setName(object.getParentTerritory());
						temp.addData((new ChartData("Certified", Double.parseDouble(object.getCertified()))));
						temp.addData(new ChartData("Certified Spacialist", Double.parseDouble(object.getCertifiedSpecalist())));
						temp.addData((new ChartData("Master Certified", Double.parseDouble(object.getMasterCertified()))));
						list.add(temp);
					}
					chartsMap.put(item.getParentTerritory(), list);
				}
			}*/
			Map<String, Double> mapValues = new HashMap<String, Double>();
			Map<String, Double> mapValues2 = new HashMap<String, Double>();
			Map<String, Double> mapValues3 = new HashMap<String, Double>();
			for(CertProfsWinnersGraphDTO item: listBC){
				List<ChartData> list = new ArrayList<ChartData>();
				List<ChartData> list2 = new ArrayList<ChartData>();
				List<ChartData> list3 = new ArrayList<ChartData>();
				for(CertProfsWinnersGraphDTO object: sublist){
					if(map.get(item.getParentTerritory()).contains(object.getParentTerritory())){
						ChartData temp = new ChartData();
						temp.setName(object.getParentTerritory());
						temp.setValue(Double.parseDouble(object.getCertified()));
						//temp.addData((new ChartData("Certified", Double.parseDouble(object.getCertified()))));
						list.add(temp);
						temp = new ChartData();
						temp.setName(object.getParentTerritory());
						temp.setValue(Double.parseDouble(object.getCertifiedSpecalist()));
						list2.add(temp);
						
						
						temp = new ChartData();
						temp.setName(object.getParentTerritory());
						temp.setValue(Double.parseDouble(object.getMasterCertified()));
						list3.add(temp);
						
					}
					chartsMap.put(item.getParentTerritory(), list);
					chartsMap2.put(item.getParentTerritory(), list2);
					chartsMap3.put(item.getParentTerritory(), list3);
					mapValues.put(item.getParentTerritory(), Double.parseDouble(item.getCertified()));
					mapValues2.put(item.getParentTerritory(), Double.parseDouble(item.getCertifiedSpecalist()));
					mapValues3.put(item.getParentTerritory(), Double.parseDouble(item.getMasterCertified()));
				}
			}
			
			
			List<ChartData> list1 = new ArrayList<ChartData>();
			list1.add(new ChartData("Certified", 0));
			list1.add(new ChartData("Certified Specialist", 0));
			list1.add(new ChartData("Master Certified", 0));
			
			List<ChartData> a = new ArrayList<ChartData>(chart.getData());
			List<ChartData> b = new ArrayList<ChartData>(chart2.getData());
			List<ChartData> c = new ArrayList<ChartData>(chart3.getData());
			
			double tempa =0;
			double tempb =0;
			double tempc =0;
			
			for(ChartData item: a){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Certified"))
						tempa = var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues.get(item.getName()));
				item.addDataList(chartsMap.get(item.getName()));
			}

			for(ChartData item: b){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Certified Spacialist"))
						tempb = var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues2.get(item.getName()));
				item.addDataList(chartsMap2.get(item.getName()));
			}

				
			for(ChartData item: c){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Master Certified"))
						tempc = var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues3.get(item.getName()));
				item.addDataList( chartsMap3.get(item.getName()));
			}

			
			list1.get(0).setValue(tempa);
			list1.get(1).setValue(tempb);
			list1.get(2).setValue(tempc);
			list1.get(0).setData(a);
			list1.get(1).setData(b);
			list1.get(2).setData(c);
			
			chart.setData(list1);
			return chart;
			
			//special mapping for stacked column
		}
		case "14":
		{
			//set datatables
			List<TTTATopNDTO> listAdvisorsQTD = this.dashService.getTTTATopN("TOP TA", 10);
			List<TTTATopNDTO> listAdvisorsYTD = this.dashService.getTTTATopN("TOP TA", 10);
			
			TopTenChart topTenChart = new TopTenChart();
			//TopTenDataTable datatableT = new TopTenDataTable("TOP 10 Technicians"); //, "Excellence Card Awards - Top 5 Technicians"
			TopTenDataTable datatableA = new TopTenDataTable("TOP 10 Advisors");
			List<TopTenTableData> tabledataA = new ArrayList<TopTenTableData>();
			//List<TopTenTableData> tabledataT = new ArrayList<TopTenTableData>();
			
			List<String> tableheaders = new ArrayList<String>();
			//keeps this as example
			tableheaders.add("Name");
			tableheaders.add("Dealership");
			tableheaders.add("BusinessCenter");
			tableheaders.add("Total Surveys");
			tableheaders.add("Average Score");
			
			
			tabledataA.add(this.mappingService.MapTTTATopNDTOtoTopTenTableData(listAdvisorsQTD, "Top 10 Advisors QTD Highest average survey scores", tableheaders));	
			tabledataA.add(this.mappingService.MapTTTATopNDTOtoTopTenTableData(listAdvisorsYTD, "Top 10 Advisors YTD Highest average survey scores", tableheaders));
			tabledataA.get(0).setTabName("QTD");
			tabledataA.get(1).setTabName("YTD");
			datatableA.setData(tabledataA);
			
			topTenChart.setTop10_advisors(datatableA);
			
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
			List<TTTATopNDTO> listAdvisorsQTD = this.dashService.getTTTATopN("TOP TT", 10);
			List<TTTATopNDTO> listAdvisorsYTD = this.dashService.getTTTATopN("TOP TT", 10);
			
			TopTenChart topTenChart = new TopTenChart();
			
			TopTenDataTable datatableT = new TopTenDataTable("TOP 10 Technicians"); //, "Excellence Card Awards - Top 5 Technicians"
			List<TopTenTableData> tabledataT = new ArrayList<TopTenTableData>();
			
			List<String> tableheaders = new ArrayList<String>();
			tableheaders.add("Name");
			tableheaders.add("Dealership");
			tableheaders.add("BusinessCenter");
			tableheaders.add("Total Awards");
			
			//tabledataT.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listTechnicians, "Top 10 MTD Technicians Excellence Card Awards", tableheaders));
			//tabledataT.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listTechnicians, "Top 10 YTD Technicians Excellence Card Awards", tableheaders));
			
			//datatableT.setData(tabledataT);
			
			tabledataT.add(this.mappingService.MapTTTATopNDTOtoTopTenTableData(listAdvisorsQTD, "Top 10 Technicians QTD Highest average survey scores", tableheaders));	
			tabledataT.add(this.mappingService.MapTTTATopNDTOtoTopTenTableData(listAdvisorsYTD, "Top 10 Technicians YTD Highest average survey scores", tableheaders));
			tabledataT.get(0).setTabName("QTD");
			tabledataT.get(1).setTabName("YTD");
			datatableT.setData(tabledataT);
			
			topTenChart.setTop10_technicians(datatableT);
			
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
		case "18":
		{
			List<MSERTopNDTO> listMTD = this.dashService.getMSERTopTen("Top 3 Parts", 3, "wiAdvisor", "MTD");
			List<MSERTopNDTO> listYTD = this.dashService.getMSERTopTen("Top 3 Parts", 3, "wiAdvisor", "YTD");
			
			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("");
			
			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();
			
			List<String> tableheaders = new ArrayList<String>();
			tableheaders.add("Parts Name");
			tableheaders.add("Volume Sold");
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMTD, "Top 3 wiAdvisor Parts Sold MTD", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listYTD, "Top 3 wiAdvisor Parts Sold YTD", tableheaders));
			
			datatable.setData(tabledata);
			
			TotalName mtd = this.dashService.getMTDByProgramAndProgramgroup("Total Parts Earnings - MTD", "wiAdvisor Parts Sold", "wiAdvisor Parts Sold");
			TotalName ytd = this.dashService.getYTDByProgramAndProgramgroup("Total Parts Earnings - YTD", "wiAdvisor Parts Sold", "wiAdvisor Parts Sold");
			
			/*TotalName mtd = new TotalName();
			TotalName ytd = new TotalName();
			MSERTopNDTO EarningsMTD = this.dashService.getMSERTopTen("Top 3 Earnings", 1, "wiAdvisor", "MTD").get(0);
			MSERTopNDTO EarningsYTD = this.dashService.getMSERTopTen("Top 3 Earnings", 1, "wiAdvisor", "YTD").get(0);
			mtd.setName("Total Parts Earnings - MTD");
			ytd.setName("Total Parts Earnings - YTD");
			mtd.setTotal("$" + EarningsMTD.getEarnings());
			mtd.setTotal("$" + EarningsYTD.getEarnings());
			*/
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
						
			topTenChart.setTop3(datatable.getData());
			
			return topTenChart;
		}
		default:
			return "No such service call exists.";
		}
	}

	
}
