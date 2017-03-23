package com.imperialm.imiservices.rest;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.imperialm.imiservices.dao.DealerPersonnelPositionsDAO;
import com.imperialm.imiservices.dao.UserPositionCodeRoleDAO;
import com.imperialm.imiservices.dto.BrainBoostWinndersGraphDTO;
import com.imperialm.imiservices.dto.BrainBoostWinnersDetailsDTO;
import com.imperialm.imiservices.dto.CertProfsExpertDetailsDTO;
import com.imperialm.imiservices.dto.CertProfsExpertGraphDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersDetailsDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersGraphDTO;
import com.imperialm.imiservices.dto.CustomerFirstGraphDTO;
import com.imperialm.imiservices.dto.DashboardDTO;
import com.imperialm.imiservices.dto.MSERGraphDTO;
import com.imperialm.imiservices.dto.MSERTopNDTO;
import com.imperialm.imiservices.dto.RetentionGraphDTO;
import com.imperialm.imiservices.dto.RewardRedemptionDetailsDTO;
import com.imperialm.imiservices.dto.RewardRedemptionGraphDTO;
import com.imperialm.imiservices.dto.SIRewardsDetailsDTO;
import com.imperialm.imiservices.dto.SIRewardsDetailsGraphDTO;
import com.imperialm.imiservices.dto.SIRewardsYOYGraphDTO;
import com.imperialm.imiservices.dto.TTTAEnrolledDTO;
import com.imperialm.imiservices.dto.TTTAEnrolledGraphDTO;
import com.imperialm.imiservices.dto.TTTAEnrollmentsDTO;
import com.imperialm.imiservices.dto.TTTAEnrollmentsSummaryDTO;
import com.imperialm.imiservices.dto.TTTATopNDTO;
import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.model.Chart;
import com.imperialm.imiservices.model.ChartData;
import com.imperialm.imiservices.model.TileAttribute1;
import com.imperialm.imiservices.model.TopTenChart;
import com.imperialm.imiservices.model.TopTenDataTable;
import com.imperialm.imiservices.model.TopTenTableData;
import com.imperialm.imiservices.model.response.TotalName;
import com.imperialm.imiservices.security.JwtTokenUtil;
import com.imperialm.imiservices.services.DashboardServiceImpl;
import com.imperialm.imiservices.services.MappingServiceImpl;
import com.imperialm.imiservices.services.UserServiceImpl;

/**
 *
 * @author Dheerajr
 *
 */
@RestController
public class DashboardController {

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
	    
	    @Autowired
	    private UserPositionCodeRoleDAO userPositionCodeRoleDAO;
	    
	    @Autowired
	    private DealerPersonnelPositionsDAO dealerPersonnelPositionsDAO;

	

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
	
	@RequestMapping(value ="/services/tile/{chartId}/{positionCode}/{dealerCode}", method = RequestMethod.GET)
	public @ResponseBody Object findTilesListByRole(@PathVariable(value="chartId") String id, @PathVariable(value="positionCode") String positionCode, @PathVariable(value="dealerCode") String dealerCode, HttpServletRequest request) {
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
		
		int testa = dashService.getRoleByPositionCode(positionCode);
		String type = "";
		String BC = "";
		if(user.getUserId().equals("Dave")){
			type = "Executive";
		}else if (user.getUserId().toLowerCase().equals("Mike".toLowerCase())){
			return this.findTilesManager(id, "2D", "26550", user, "Manager");
		}else{
		if( testa == 1 || testa == 3 || testa == 13){
			type = "Executive";
		}else if( testa == 12){
			type = "BC";
			List<String> bcSet = this.dashService.getUserTerritoyById(user.getUserId());
			if(bcSet.size() > 0){
				BC = bcSet.get(0);
			}
		}else if( testa == 11){
			type = "District";
			 return this.findTilesManager(id, positionCode, dealerCode, user, type);
		}else if( testa == 10){
			type = "Dealer";
			 return this.findTilesManager(id, positionCode, dealerCode, user, type);
		}else if( testa == 9){
			type = "Participant";
			 return this.findTilesManager(id, positionCode, dealerCode, user, type);
		}else if( testa == 5){
			type = "Manager";
			 return this.findTilesManager(id, positionCode, dealerCode, user, type);
		}
		}
		//divide the switch statement to functions
		switch(id){
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
			
			TotalName years = new TotalName();
			TotalName cartificationLevel = new TotalName();
			TotalName totalCertifiedParticipants = new TotalName();
			TotalName totalMasterCertifiedParticipants = new TotalName();
			TotalName totalCertifiedSpecialistParticipants = new TotalName();
			TotalName totalCertifiedLevelParticipants = new TotalName();
			TotalName dealershipMasterCertifiedRankWithinBC = new TotalName();


				
			
			if(type.equals("Executive")){
				List<MSERGraphDTO> MSERGraphDTOListYTD = this.dashService.getMSERGraphProgramsSUMByParentTerritoryAndToggle("NAT", "YTD");
				List<MSERGraphDTO> MSERGraphDTOListMTD = this.dashService.getMSERGraphProgramsSUMByParentTerritoryAndToggle("NAT", "MTD");
				
				if(MSERGraphDTOListYTD.size()>0){
					MSERGraphDTO MSERGraphDTO = MSERGraphDTOListYTD.get(0);
					
					cartificationLevel.setName("Excellence Card Awards YTD");
					int total = (int)MSERGraphDTO.getAmount();
					cartificationLevel.setTotal("$" + this.formatCurrency(total));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevel));
			}
				if(MSERGraphDTOListMTD.size()>0){
					MSERGraphDTO MSERGraphDTO = MSERGraphDTOListMTD.get(0);
					
					totalCertifiedParticipants.setName("Excellence Card Awards MTD");
					int total = (int)MSERGraphDTO.getAmount();
					totalCertifiedParticipants.setTotal("$" + this.formatCurrency(total));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
			}
				
				TotalName dealerscount = this.dashService.getMSEREnrolledDealersCount();
				dealerscount.setTotal(this.formatCurrency(Integer.parseInt(dealerscount.getTotal())));
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
				
			}
			
			
			if(type.equals("BC")){
				
				List<MSERGraphDTO> MSERGraphDTOListYTD = this.dashService.getMSERGraphProgramsSUMByChildTerritoryAndToggle(BC, "YTD");
				List<MSERGraphDTO> MSERGraphDTOListMTD = this.dashService.getMSERGraphProgramsSUMByChildTerritoryAndToggle(BC, "MTD");
				
				if(MSERGraphDTOListYTD.size()>0){
					MSERGraphDTO MSERGraphDTO = MSERGraphDTOListYTD.get(0);
					
					cartificationLevel.setName("Excellence Card Awards YTD");
					int total = (int)MSERGraphDTO.getAmount();
					cartificationLevel.setTotal("$" + this.formatCurrency(total));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevel));
			}
				if(MSERGraphDTOListMTD.size()>0){
					MSERGraphDTO MSERGraphDTO = MSERGraphDTOListMTD.get(0);
					
					totalCertifiedParticipants.setName("Excellence Card Awards MTD");
					int total = (int)MSERGraphDTO.getAmount();
					totalCertifiedParticipants.setTotal("$" + this.formatCurrency(total));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
			}
				
				TotalName dealerscount = this.dashService.getMSERDealersCountByBCOrDistrict(BC+"%");
				dealerscount.setTotal(this.formatCurrency(Integer.parseInt(dealerscount.getTotal())));
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
				
			}
			
			
			
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
			
			int moneymtd =  (int)EarningsMTD.getEarnings();
			int moneyytd =  (int)EarningsYTD.getEarnings();
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			String moneyStringMTD = formatter.format(moneymtd);
			String moneyStringYTD = formatter.format(moneyytd);
			if (moneyStringMTD.endsWith(".00")) {
			    int centsIndex = moneyStringMTD.lastIndexOf(".00");
			    if (centsIndex != -1) {
			    	moneyStringMTD = moneyStringMTD.substring(1, centsIndex);
			    }
			}
			
			if (moneyStringYTD.endsWith(".00")) {
			    int centsIndex = moneyStringYTD.lastIndexOf(".00");
			    if (centsIndex != -1) {
			    	moneyStringYTD = moneyStringYTD.substring(1, centsIndex);
			    }
			}
			
			mtd.setName("Total Parts Earnings - MTD");
			ytd.setName("Total Parts Earnings - YTD");
			mtd.setTotal("$" + moneyStringMTD);
			ytd.setTotal("$" + moneyStringYTD);
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
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMVPMTD, "Top 3 MVP Plans Sold MTD", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMVPYTD, "Top 3 MVP Plans Sold YTD", tableheaders));
			
			datatable.setData(tabledata);
			
			//TotalName mtd = this.dashService.getMTDByProgramAndProgramgroup("Total Parts Earnings - MTD", "MVP Parts Sold", "MVP Parts Sold");
			//TotalName ytd = this.dashService.getYTDByProgramAndProgramgroup("Total Parts Earnings - YTD", "MVP Parts Sold", "MVP Parts Sold");
			
			
			TotalName mtd = new TotalName();
			TotalName ytd = new TotalName();
			MSERTopNDTO EarningsMTD = this.dashService.getMSERTopTen("Top 3 Earnings", 1, "MVP", "MTD").get(0);
			MSERTopNDTO EarningsYTD = this.dashService.getMSERTopTen("Top 3 Earnings", 1, "MVP", "YTD").get(0);
			
			int moneymtd =  (int)EarningsMTD.getEarnings();
			int moneyytd =  (int)EarningsYTD.getEarnings();
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			String moneyStringMTD = formatter.format(moneymtd);
			String moneyStringYTD = formatter.format(moneyytd);
			if (moneyStringMTD.endsWith(".00")) {
			    int centsIndex = moneyStringMTD.lastIndexOf(".00");
			    if (centsIndex != -1) {
			    	moneyStringMTD = moneyStringMTD.substring(1, centsIndex);
			    }
			}
			
			if (moneyStringYTD.endsWith(".00")) {
			    int centsIndex = moneyStringYTD.lastIndexOf(".00");
			    if (centsIndex != -1) {
			    	moneyStringYTD = moneyStringYTD.substring(1, centsIndex);
			    }
			}
			
			mtd.setName("Total Parts Earnings - MTD");
			ytd.setName("Total Parts Earnings - YTD");
			mtd.setTotal("$" + moneyStringMTD);
			ytd.setTotal("$" + moneyStringYTD);
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
			
			
			int moneymtd =  (int)EarningsMTD.getEarnings();
			int moneyytd =  (int)EarningsYTD.getEarnings();
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			String moneyStringMTD = formatter.format(moneymtd);
			String moneyStringYTD = formatter.format(moneyytd);
			if (moneyStringMTD.endsWith(".00")) {
			    int centsIndex = moneyStringMTD.lastIndexOf(".00");
			    if (centsIndex != -1) {
			    	moneyStringMTD = moneyStringMTD.substring(1, centsIndex);
			    }
			}
			
			if (moneyStringYTD.endsWith(".00")) {
			    int centsIndex = moneyStringYTD.lastIndexOf(".00");
			    if (centsIndex != -1) {
			    	moneyStringYTD = moneyStringYTD.substring(1, centsIndex);
			    }
			}
			
			mtd.setName("Total Parts Earnings - MTD");
			ytd.setName("Total Parts Earnings - YTD");
			mtd.setTotal("$" + moneyStringMTD);
			ytd.setTotal("$" + moneyStringYTD);
			
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
			
			
			int moneymtd =  (int)EarningsMTD.getEarnings();
			int moneyytd =  (int)EarningsYTD.getEarnings();
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			String moneyStringMTD = formatter.format(moneymtd);
			String moneyStringYTD = formatter.format(moneyytd);
			if (moneyStringMTD.endsWith(".00")) {
			    int centsIndex = moneyStringMTD.lastIndexOf(".00");
			    if (centsIndex != -1) {
			    	moneyStringMTD = moneyStringMTD.substring(1, centsIndex);
			    }
			}
			
			if (moneyStringYTD.endsWith(".00")) {
			    int centsIndex = moneyStringYTD.lastIndexOf(".00");
			    if (centsIndex != -1) {
			    	moneyStringYTD = moneyStringYTD.substring(1, centsIndex);
			    }
			}
			
			mtd.setName("Total Parts Earnings - MTD");
			ytd.setName("Total Parts Earnings - YTD");
			mtd.setTotal("$" + moneyStringMTD);
			ytd.setTotal("$" + moneyStringYTD);
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
						
			topTenChart.setTop3(datatable.getData());
			
			return topTenChart;
		}
		case "8":
		{
			TopTenChart topTenChart = new TopTenChart();
			//TopTenDataTable datatable = new TopTenDataTable("");
			
			/*List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();
			
			datatable.setData(tabledata);
			
			topTenChart.setDatatable(datatable);*/
			
			TotalName dealerscount = this.dashService.getMSEREnrolledDealersCount();
			dealerscount.setTotal(this.formatCurrency(Integer.parseInt(dealerscount.getTotal())));
			
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
			
			Chart chart = this.mappingService.BrainBoostWinndersGraphDTOtoChart(listBC, "YTD Winners", "", "Business Center", "Total Winners", "column", "Winners");
			
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			for(BrainBoostWinndersGraphDTO item: listBC_unfiltered){
				if(type.equals("BC")){
					//get users BC
					if(item.getChildTerritory().contains(BC)){
						filters.add(item.getChildTerritory());
					}
				}else{
					filters.add(item.getChildTerritory());
				}
				if(!map.containsKey(item.getParentTerritory())){
					map.put(item.getParentTerritory(), new ArrayList<String>());
					chartsMap.put(item.getParentTerritory(), new ArrayList<ChartData>());
				}
				List<String> temp = map.get(item.getParentTerritory());
				if(type.equals("BC")){
					//get users BC
					if(item.getChildTerritory().contains(BC)){
						temp.add(item.getChildTerritory());
					}
				}else{
					temp.add(item.getChildTerritory());
				}
				
				map.put(item.getParentTerritory(), temp);
			}
			
			List<BrainBoostWinndersGraphDTO> sublist = this.dashService.getBrainBoostGraphAllDistricData(filters);
			
			for(BrainBoostWinndersGraphDTO item: listBC){
				List<ChartData> list = new ArrayList<ChartData>();
				for(BrainBoostWinndersGraphDTO object: sublist){
					if(map.get(item.getParentTerritory()).contains(object.getParentTerritory())){
						ChartData data = new ChartData(object.getParentTerritory(), object.getWinners());
						list.add(data);
					}
					chartsMap.put(item.getParentTerritory(), list);
				}
			}
			

			
			
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
			//List<CertProfsExpertGraphDTO> list = this.dashService.getExpertPointsEarned();
			//return this.mappingService.CertProfsExpertGraphDTOtoChartTotalPoints(list, "Expert Points Earned YTD", "", "Business Center", "Total Points", "column");
			
			List<String> filters = new ArrayList<String>();
			
			filters.add("NAT");
			
			List<CertProfsExpertGraphDTO> list1st = this.dashService.getExpertPointsEarnedByParentTerritory(filters);
			
			filters = new ArrayList<String>();
			
			for(CertProfsExpertGraphDTO item: list1st){
				if(!filters.contains(item.getChildTerritory()))
					filters.add(item.getChildTerritory());
			}
			
			List<CertProfsExpertGraphDTO> list1st_Filtered = this.dashService.getExpertPointsEarnedByChildTerritoryAsParent(filters);

			Chart chart = this.mappingService.CertProfsExpertGraphDTOtoChartTotalPoints(list1st_Filtered, "Expert Points Earned YTD", "", "Business Center", "Total Points", "column");

			filters = new ArrayList<String>();
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap2 = new HashMap<String, List<ChartData>>();
					for(CertProfsExpertGraphDTO item: list1st){	
						if(type.equals("BC")){
							//get users BC
							if(item.getChildTerritory().contains(BC)){
								filters.add(item.getChildTerritory());
							}
						}else{
							filters.add(item.getChildTerritory());
						}
						if(!map.containsKey(item.getChildTerritory())){
							map.put(item.getChildTerritory(), new ArrayList<String>());
							chartsMap.put(item.getChildTerritory(), new ArrayList<ChartData>());
							chartsMap2.put(item.getChildTerritory(), new ArrayList<ChartData>());
						}
						List<String> temp = map.get(item.getChildTerritory());
						if(type.equals("BC")){
							//get users BC
							if(item.getChildTerritory().contains(BC)){
								temp.add(item.getChildTerritory());
							}
						}else{
							temp.add(item.getChildTerritory());
						}
						map.put(item.getParentTerritory(), temp);
						
					}
			List<CertProfsExpertGraphDTO> sublist = this.dashService.getExpertPointsEarnedByParentTerritorySum(filters);
			
			for(CertProfsExpertGraphDTO item: list1st_Filtered){
				List<ChartData> list = new ArrayList<ChartData>();
				for(CertProfsExpertGraphDTO object: sublist){
					if(map.get(item.getParentTerritory()).contains(object.getParentTerritory())){
						ChartData data = new ChartData(object.getChildTerritory(), object.getPoints());
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
		case "11":
		{
			// check for role, to know what data to display
			List<CertProfsExpertGraphDTO> list = this.dashService.getParticipantCompletedByProgram();
			
			List<String> filters = new ArrayList<String>(Arrays.asList("NAT"));
			List<CertProfsExpertGraphDTO> unfiltereList = this.dashService.getExpertPointsEarnedByParentTerritory(filters);
			
			filters = new ArrayList<String>();
			for(CertProfsExpertGraphDTO item: unfiltereList){
				if(!filters.contains(item.getChildTerritory())){
					filters.add(item.getChildTerritory());
				}
			}
			
			unfiltereList = this.dashService.getExpertPointsEarnedByParentTerritory(filters);
			
			Chart chart = this.mappingService.CertProfsExpertGraphDTOtoChartCert(list, "Participants Completed By Program", "", "", "Total Numbers", "column_stack");
			Chart chart2 = this.mappingService.CertProfsExpertGraphDTOtoChartCert(list, "Participants Completed By Program", "", "", "Total Numbers", "column_stack");
			
			filters = new ArrayList<String>();
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			for(CertProfsExpertGraphDTO item: unfiltereList){
				if(!filters.contains(item.getChildTerritory())){
					if(type.equals("BC")){
						//get users BC
						if(item.getChildTerritory().contains(BC)){
							filters.add(item.getChildTerritory());
						}
					}else{
						filters.add(item.getChildTerritory());
					}
				}
				if(!map.containsKey(item.getParentTerritory())){
					map.put(item.getParentTerritory(), new ArrayList<String>());
				}
				List<String> temp = map.get(item.getParentTerritory());
				
				if(type.equals("BC")){
					//get users BC
					if(item.getChildTerritory().contains(BC)){
						temp.add(item.getChildTerritory());
					}
				}else{
					temp.add(item.getChildTerritory());
				}
				map.put(item.getParentTerritory(), temp);
			}
			
			
			//List<CertProfsExpertGraphDTO> sublist = this.dashService.getExpertPointsEarnedByParentTerritory(filters);
			
			Map<String, Double> mapValues = new HashMap<String, Double>();
			Map<String, Double> mapValues2 = new HashMap<String, Double>();
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap2 = new HashMap<String, List<ChartData>>();
	        for(CertProfsExpertGraphDTO item: list){
	        	
	        	List<ChartData> list2 = new ArrayList<ChartData>();
				List<ChartData> list3 = new ArrayList<ChartData>();
				for(CertProfsExpertGraphDTO object: unfiltereList){
					if(map.get(item.getParentTerritory()).contains(object.getChildTerritory())){
						if(object.getCertType().contains("JEEP") && item.getCertType().contains("JEEP")){
							ChartData temp = new ChartData();
							temp.setName(object.getChildTerritory());
							temp.setValue(object.getCert());
							list2.add(temp);
						}else if(object.getCertType().contains("RAM") && item.getCertType().contains("RAM")){
							ChartData temp = new ChartData();
							temp.setName(object.getChildTerritory());
							temp.setValue(object.getCert());
							list3.add(temp);
			        	}
					}
					if(item.getCertType().contains("JEEP")){
						chartsMap.put(item.getParentTerritory(), list2);
						mapValues.put(item.getParentTerritory(), (double)item.getCert());
					}else if(item.getCertType().contains("RAM")){
						chartsMap2.put(item.getParentTerritory(), list3);
						mapValues2.put(item.getParentTerritory(), (double)item.getCert());
					}
				}
	        	
				
				
	        	/*if(item.getCertType().contains("JEEP")){
					mapValues.put(item.getParentTerritory(), (double)item.getCert());
	        	}else if(item.getCertType().contains("RAM")){
					mapValues2.put(item.getParentTerritory(), (double)item.getCert());
	        	}*/
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
				item.addDataList(chartsMap.get(item.getName()));
			}
			
			for(ChartData item: b){
				for(ChartData var: item.getData()){
					if(var.getName().contains("RAM"))
						tempb += var.getValue();
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
				if(type.equals("BC")){
					//get users BC
					if(item.getChildTerritory().contains(BC)){
						filters.add(item.getChildTerritory());
					}
				}else{
					filters.add(item.getChildTerritory());
				}
				if(!map.containsKey(item.getParentTerritory())){
					map.put(item.getParentTerritory(), new ArrayList<String>());
					chartsMap.put(item.getParentTerritory(), new ArrayList<ChartData>());
					chartsMap2.put(item.getParentTerritory(), new ArrayList<ChartData>());
				}
				List<String> temp = map.get(item.getParentTerritory());
				if(type.equals("BC")){
					//get users BC
					if(item.getChildTerritory().contains(BC)){
						temp.add(item.getChildTerritory());
					}
				}else{
					temp.add(item.getChildTerritory());
				}
				map.put(item.getParentTerritory(), temp);
			}
			List<BrainBoostWinndersGraphDTO> sublist = this.dashService.getBrainBoostGraphAllDistricData(filters);
			
			Chart chart = this.mappingService.BrainBoostWinndersGraphDTOtoChart(listBC, "Exellence Card Award And Award Points YTD", "", "", "", "column_compound", "Awards");
			Chart chart2 = this.mappingService.BrainBoostWinndersGraphDTOtoChart(listBC, "Exellence Card Award And Award Points YTD", "", "", "", "column_compound", "Awards");
			
		        
				Map<String, Double> mapValues = new HashMap<String, Double>();
				Map<String, Double> mapValues2 = new HashMap<String, Double>();
		        for(BrainBoostWinndersGraphDTO item: listBC){
		        	List<ChartData> list = new ArrayList<ChartData>();
		        	List<ChartData> list2 = new ArrayList<ChartData>();
					for(BrainBoostWinndersGraphDTO object: sublist){
						if(map.get(item.getParentTerritory()).contains(object.getParentTerritory())){

							ChartData temp = new ChartData();
							temp.setName(object.getParentTerritory());
							temp.setValue(object.getEarnings());

							list.add(temp);
							temp = new ChartData();
							temp.setName(object.getParentTerritory());
							temp.setValue(object.getPoints());
							list2.add(temp);
							
						}
						chartsMap.put(item.getParentTerritory(), list);
						chartsMap2.put(item.getParentTerritory(), list2);
						mapValues.put(item.getParentTerritory(), (double)item.getEarnings());
						mapValues2.put(item.getParentTerritory(), (double)item.getPoints());
					}
				}
		        
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
			
			
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap2 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap3 = new HashMap<String, List<ChartData>>();
			for(CertProfsWinnersGraphDTO item: listBC_unfiltered){
				if(type.equals("BC")){
					//get users BC
					if(item.getChildTerritory().contains(BC)){
						filters.add(item.getChildTerritory());
					}
				}else{
					filters.add(item.getChildTerritory());
				}
				if(!map.containsKey(item.getParentTerritory())){
					map.put(item.getParentTerritory(), new ArrayList<String>());
				}
				List<String> temp = map.get(item.getParentTerritory());
				if(type.equals("BC")){
					//get users BC
					if(item.getChildTerritory().contains(BC)){
						temp.add(item.getChildTerritory());
					}
				}else{
					temp.add(item.getChildTerritory());
				}
				map.put(item.getParentTerritory(), temp);
			}
			List<CertProfsWinnersGraphDTO> sublist = this.dashService.getCertProfsWinnersGraphAllDistricData(filters);
			
			
			Chart chart = this.mappingService.CertProfsWinnersGraphDTOtoChart(listBC, "Certified Proffessionals - YTD Certifications", "", "", "Total Certification", "column_stack");
			Chart chart2 = this.mappingService.CertProfsWinnersGraphDTOtoChart(listBC, "Certified Proffessionals - YTD Certifications", "", "", "Total Certification", "column_stack");
			Chart chart3 = this.mappingService.CertProfsWinnersGraphDTOtoChart(listBC, "Certified Proffessionals - YTD Certifications", "", "", "Total Certification", "column_stack");

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
			if(type.equals("Executive")){
				
				List<String> filters = new ArrayList<String>(Arrays.asList("NAT"));
				List<TTTAEnrollmentsSummaryDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsSummarySUMByParentAndPositionCode(filters, "13");
				
				TotalName dealerscount =  new TotalName();
				dealerscount.setName("Total Dealers Enrolled");
				dealerscount.setTotal("0");
				
				TotalName incentiveEligible  = new TotalName();
				incentiveEligible.setName("Total Advisors Incentive Eligible");
				incentiveEligible.setTotal("0");
				
				TotalName qtdSurveyCount  = new TotalName();
				qtdSurveyCount.setName("QTD Survey Count");
				qtdSurveyCount.setTotal("0");
				
				TotalName avgSurveyCount  = new TotalName();
				avgSurveyCount.setName("Average Quarterly survey score");
				avgSurveyCount.setTotal("0");
				
				if(incentiveEligibleList.size()>0){
					dealerscount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getTotalEnrollments()));
					incentiveEligible.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getIncentiveEligible()));
					qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
					avgSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getAvgSurveyScore()));
				}
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgSurveyCount));
				
				
			}else if(type.equals("BC")){
				/*TotalName dealerscount = this.dashService.getTTTANATTopEnrolledDealerCountByBCDistrictAndPositionCode(BC,"13");
				dealerscount.setTotal(this.formatNumbers(Integer.parseInt(dealerscount.getTotal())));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));*/
				
				List<String> filters = new ArrayList<String>(Arrays.asList(BC+"%"));
				List<TTTAEnrollmentsSummaryDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsSummaryByChildAndPositionCode(filters, "13");
				
				TotalName dealerscount =  new TotalName();
				dealerscount.setName("Total Dealers Enrolled");
				dealerscount.setTotal("0");
				
				TotalName incentiveEligible  = new TotalName();
				incentiveEligible.setName("Total Advisors Incentive Eligible");
				incentiveEligible.setTotal("0");
				
				TotalName qtdSurveyCount  = new TotalName();
				qtdSurveyCount.setName("QTD Survey Count");
				qtdSurveyCount.setTotal("0");
				
				TotalName avgSurveyCount  = new TotalName();
				avgSurveyCount.setName("Average Quarterly survey score");
				avgSurveyCount.setTotal("0");
				
				
				if(incentiveEligibleList.size()>0){
					dealerscount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getTotalEnrollments()));
					incentiveEligible.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getIncentiveEligible()));
					qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
					avgSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getAvgSurveyScore()));
				}
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgSurveyCount));
				
			}
			
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
			if(type.equals("Executive")){
				
				List<String> filters = new ArrayList<String>(Arrays.asList("NAT"));
				List<TTTAEnrollmentsSummaryDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsSummarySUMByParentAndPositionCode(filters, "23");
				
				TotalName dealerscount = this.dashService.getTTTANATTopTechEnrolledDealerCount();
				dealerscount.setTotal(this.formatNumbers(Integer.parseInt(dealerscount.getTotal())));
				
				
				TotalName incentiveEligible  = new TotalName();
				incentiveEligible.setName("Total Technicians Incentive Eligible");
				incentiveEligible.setTotal("0");
				
				TotalName qtdSurveyCount  = new TotalName();
				qtdSurveyCount.setName("QTD Survey Count");
				qtdSurveyCount.setTotal("0");
				
				TotalName avgSurveyCount  = new TotalName();
				avgSurveyCount.setName("Average Quarterly survey score");
				avgSurveyCount.setTotal("0");
				
				if(incentiveEligibleList.size()>0){
					incentiveEligible.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getIncentiveEligible()));
					qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
					avgSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getAvgSurveyScore()));
				}
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgSurveyCount));
				
				
			}else if(type.equals("BC")){
				TotalName dealerscount = this.dashService.getTTTANATTopEnrolledDealerCountByBCDistrictAndPositionCode(BC+"%","13");
				dealerscount.setTotal(this.formatNumbers(Integer.parseInt(dealerscount.getTotal())));
				
				List<String> filters = new ArrayList<String>(Arrays.asList("BC"));
				List<TTTAEnrollmentsSummaryDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsSummaryByChildAndPositionCode(filters, "23");
				
				TotalName incentiveEligible  = new TotalName();
				incentiveEligible.setName("Total Technicians Incentive Eligible");
				incentiveEligible.setTotal("0");
				
				TotalName qtdSurveyCount  = new TotalName();
				qtdSurveyCount.setName("QTD Survey Count");
				qtdSurveyCount.setTotal("0");
				
				TotalName avgSurveyCount  = new TotalName();
				avgSurveyCount.setName("Average Quarterly survey score");
				avgSurveyCount.setTotal("0");
				
				
				if(incentiveEligibleList.size()>0){
					incentiveEligible.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getIncentiveEligible()));
					qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
					avgSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getAvgSurveyScore()));
				}
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgSurveyCount));
				
			}
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
		case "19":
		{
			List<MSERGraphDTO> listOfFirstLevel = this.dashService.getMSERGraphByTerritoryAndToggle("NAT", "YTD");
			
			
			List<String> filters = new ArrayList<String>();
			for(MSERGraphDTO item: listOfFirstLevel){
				if(item.getChild() != null && !filters.contains(item.getChild()))
					filters.add(item.getChild());
			}
			
			//main chart model
			Chart chart = new Chart("Total Earnings YTD", "", "Total Earnings", "", "bar_compound");
			
			//Create first level of the chart
			//Get Distinct program names
			List<String> programs = this.dashService.getMSERGraphDistinctProgramsByParentTerritoryAndToggle("NAT", "YTD");

			List<ChartData> attributes = new ArrayList<ChartData>();
			
			for(String programName: programs){
				attributes.add(new ChartData(programName, 0));
			}
			
			
			for(ChartData item: attributes){
				double total = 0;
				for(String filter: filters){
					List<MSERGraphDTO> innerList = this.dashService.getMSERGraphByTerritoryAndToggleAndProgram(filter, "YTD", item.getName());
					ChartData chartD = new ChartData(filter, total);
					for(MSERGraphDTO a: innerList){
						total += a.getAmount();
						chartD.addData(new ChartData(a.getChild(), a.getAmount()));
					}
					chartD.setValue(total);
					total = 0;
					// with out b =0; java skips the next line
					int b = 0;
					if(type.equals("BC") && filter.equals(BC)){
						item.addData(chartD);
					}else if(type.equals("Executive")){
						item.addData(chartD);
					}
					b = 1;
				}
			}
			
			chart.setData(attributes);
			chart.setUnit("$");
			return chart;
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
						if(type.equals("BC")){
							//get users BC
							if(item.getChildTerritory().contains(BC)){
								temp.add(item.getChildTerritory());
							}
						}else{
							temp.add(item.getChildTerritory());
						}
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
					        chart.setAvarage(true);
					        chart.setUnit("$");
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
						if(type.equals("BC")){
							//get users BC
							if(item.getChildTerritory().contains(BC)){
								temp.add(item.getChildTerritory());
							}
						}else{
							temp.add(item.getChildTerritory());
						}
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
			chart.setAvarage(true);
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
						if(type.equals("BC")){
							//get users BC
							if(item.getChildTerritory().contains(BC)){
								temp.add(item.getChildTerritory());
							}
						}else{
							temp.add(item.getChildTerritory());
						}
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
			chart.setUnit("$");
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
			
			
			Chart chart = this.mappingService.RewardRedemptionGraphDTOtoChart(list1st_Filtered, "Total Program Points Earned YTD", "", "Points Earned", "Points Earned", "column", "EarnedPoints");
						
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
						if(type.equals("BC")){
							//get users BC
							if(item.getChildTerritory().contains(BC)){
								temp.add(item.getChildTerritory());
							}
						}else{
							temp.add(item.getChildTerritory());
						}
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
			TopTenChart topTenChart = new TopTenChart();
			
			if(type.equals("Participant")){
			TotalName bal = new TotalName();
			TotalName redeemed = new TotalName();
			TotalName earned = new TotalName();
			List<RewardRedemptionDetailsDTO> RewardRedemptionDetails = this.dashService.getRewardRedemptionDetailsBySid(user.getUserId());
			if(RewardRedemptionDetails.size() > 0){
			bal.setName("Balance");
			redeemed.setName("Redeemed Points");
			earned.setName("Earned Points");
			bal.setTotal(this.formatCurrency(RewardRedemptionDetails.get(0).getBalancePoints()) + "pt");
			earned.setTotal(this.formatCurrency(RewardRedemptionDetails.get(0).getEarnedPoints()) + "pt");
			redeemed.setTotal(this.formatCurrency(RewardRedemptionDetails.get(0).getRedeemedPoints()) + "pt");
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(earned));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(bal));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(earned));
			}
			}
			return topTenChart;
		}
		case "25":
		{
			//check if dealer or not if dealer use dealer if not use sid // need a case for manager
			// nothing for upper management
			TopTenChart topTenChart = new TopTenChart();
			/*
			TotalName years = new TotalName();
			TotalName cartificationLevel = new TotalName();
			CertProfsWinnersDetailsDTO CertProfsWinnersDetails = this.dashService.getCertProfsWinnersDetailsBySID(sid).get(0);			
			years.setName("Years Certified");
			years.setTotal(CertProfsWinnersDetails.getYearsOfCertified()+ "");
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
			if(CertProfsWinnersDetails.getMasterCertified() > 0){
				cartificationLevel.setName("Certified Master");
				cartificationLevel.setTotal(CertProfsWinnersDetails.getMasterCertified() + "");
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevel));
			}else if(CertProfsWinnersDetails.getCertifiedSpecalist() > 0){
				cartificationLevel.setName("Certified Master");
				cartificationLevel.setTotal(CertProfsWinnersDetails.getCertifiedSpecalist() + "");
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevel));
			}else if(CertProfsWinnersDetails.getCertified() > 0){
				cartificationLevel.setName("Certified");
				cartificationLevel.setTotal(CertProfsWinnersDetails.getCertified() + "");
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevel));
			}else{
				cartificationLevel.setName("Not Certified, This is showing for the demo");
				cartificationLevel.setTotal("No Certification");
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevel));
			}*/
			
			return topTenChart;
		}case "26":
		{
			//nothing for upper levels
			return new TopTenChart();
		}
		case "27":
		{
			//nothing for upper levels
			return new TopTenChart();
		}
		case "28":
		{
			//nothing for upper levels
			return new TopTenChart();
		}
		case "29":
		{
			String sid = user.getUserId();
			TopTenChart topTenChart = new TopTenChart();
			
			TotalName years = new TotalName();
			TotalName cartificationLevel = new TotalName();
			TotalName totalCertifiedParticipants = new TotalName();
			TotalName totalMasterCertifiedParticipants = new TotalName();
			TotalName totalCertifiedSpecialistParticipants = new TotalName();
			TotalName totalCertifiedLevelParticipants = new TotalName();
			TotalName dealershipMasterCertifiedRankWithinBC = new TotalName();
			
			List<SIRewardsDetailsDTO> SIRewardsDetailsList = this.dashService.getSIRewardsDetailsBySIDAndToggle(sid, "QTD");
			List<SIRewardsDetailsDTO> SIRewardsDetailsListYTD = this.dashService.getSIRewardsDetailsBySIDAndToggle(sid, "YTD");

			if(type.equals("Participant")){
				if(SIRewardsDetailsList.size()>0){
					SIRewardsDetailsDTO SIRewardsDetailsDTO = SIRewardsDetailsList.get(0);
					
					cartificationLevel.setName("Level 0 training completed");
					cartificationLevel.setTotal("No");
					dealershipMasterCertifiedRankWithinBC.setName("Level 1 training completed");
					dealershipMasterCertifiedRankWithinBC.setTotal("No");
					years.setName("Incentive Eligible");
					cartificationLevel.setTotal("$0");
					
						if(SIRewardsDetailsDTO.getLevel0() > 0){
						cartificationLevel.setTotal("Yes");
						}
						if(SIRewardsDetailsDTO.getLevel1() > 0){
							dealershipMasterCertifiedRankWithinBC.setTotal("Yes");
							}
						if(SIRewardsDetailsDTO.getIncentiveQualified() > 0){
							int total = SIRewardsDetailsDTO.getEligibleSurveys() * 10;
							
							NumberFormat formatter = NumberFormat.getCurrencyInstance();
							String moneyStringMTD = formatter.format(total);
							if (moneyStringMTD.endsWith(".00")) {
							    int centsIndex = moneyStringMTD.lastIndexOf(".00");
							    if (centsIndex != -1) {
							    	moneyStringMTD = moneyStringMTD.substring(1, centsIndex);
							    }
							}
							
							years.setTotal("$" + moneyStringMTD);
							}
						
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevel));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedRankWithinBC));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
					
					totalCertifiedSpecialistParticipants.setName("QTD Survey Score");
					totalCertifiedSpecialistParticipants.setTotal((int)SIRewardsDetailsDTO.getSurveyScore() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
					
					
				}
				
				if(SIRewardsDetailsListYTD.size()>0){
					SIRewardsDetailsDTO SIRewardsDetailsDTO = SIRewardsDetailsListYTD.get(0);
					
					totalCertifiedParticipants.setName("Total Earnings YTD");
					int total = (int)SIRewardsDetailsDTO.getProjectedEarnings();
					NumberFormat formatter = NumberFormat.getCurrencyInstance();
					String moneyStringMTD = formatter.format(total);
					if (moneyStringMTD.endsWith(".00")) {
					    int centsIndex = moneyStringMTD.lastIndexOf(".00");
					    if (centsIndex != -1) {
					    	moneyStringMTD = moneyStringMTD.substring(1, centsIndex);
					    }
					}
					
					totalCertifiedParticipants.setTotal("$" + moneyStringMTD);
					
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
					
					
					totalMasterCertifiedParticipants.setName(sid + " BC Rank");
					totalMasterCertifiedParticipants.setTotal(SIRewardsDetailsDTO.getBCAdvisorRankEarnings() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
					
				}
				
				
				
			}
			
			if(type.equals("Dealer")){
				List<String> filters = new ArrayList<String>();
				filters.add(dealerCode);
				List<SIRewardsDetailsGraphDTO> SIRewardsDetailsGraphList = this.dashService.getSIRewardsDetailsGraphByChildTerritoryAndToggle(filters, "QTD");
				if(SIRewardsDetailsGraphList.size() > 0){
					SIRewardsDetailsGraphDTO SIRewardsDetailsGraphDTO = SIRewardsDetailsGraphList.get(0);
					totalCertifiedSpecialistParticipants.setName("Avg. Quarterly Survey Score");
					totalCertifiedSpecialistParticipants.setTotal((int)SIRewardsDetailsGraphDTO.getAvgSurveyScore() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
					
					
				}
				SIRewardsDetailsGraphList = this.dashService.getSIRewardsDetailsGraphByChildTerritoryAndToggle(filters, "YTD");
				if(SIRewardsDetailsGraphList.size() > 0){
					SIRewardsDetailsGraphDTO SIRewardsDetailsGraphDTO = SIRewardsDetailsGraphList.get(0);
					totalCertifiedParticipants.setName("Total Earnings YTD");
					int total = (int)SIRewardsDetailsGraphDTO.getProjectedEarnings();
					NumberFormat formatter = NumberFormat.getCurrencyInstance();
					String moneyStringMTD = formatter.format(total);
					if (moneyStringMTD.endsWith(".00")) {
					    int centsIndex = moneyStringMTD.lastIndexOf(".00");
					    if (centsIndex != -1) {
					    	moneyStringMTD = moneyStringMTD.substring(1, centsIndex);
					    }
					}
					
					totalCertifiedParticipants.setTotal("$" + moneyStringMTD);
					
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
					
					totalMasterCertifiedParticipants.setName("Dealership BC Rank");
					totalMasterCertifiedParticipants.setTotal(SIRewardsDetailsGraphDTO.getBCDlearRankEarnings() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
					
				}
			}
			
			if(type.equals("Manager")){
				
				List<String> filters = new ArrayList<String>();
				filters.add(dealerCode);
				List<SIRewardsDetailsGraphDTO> SIRewardsDetailsGraphList = this.dashService.getSIRewardsDetailsGraphByChildTerritoryAndToggle(filters, "QTD");
				if(SIRewardsDetailsGraphList.size() > 0){
					SIRewardsDetailsGraphDTO SIRewardsDetailsGraphDTO = SIRewardsDetailsGraphList.get(0);
					totalCertifiedSpecialistParticipants.setName("Avg. Quarterly Survey Score");
					totalCertifiedSpecialistParticipants.setTotal((int)SIRewardsDetailsGraphDTO.getAvgSurveyScore() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
					
					
				}
				
				if(SIRewardsDetailsListYTD.size()>0){
					SIRewardsDetailsDTO SIRewardsDetailsDTO = SIRewardsDetailsListYTD.get(0);
					
					totalCertifiedParticipants.setName("Total Earnings YTD");
					int total = (int)SIRewardsDetailsDTO.getProjectedEarnings();
					NumberFormat formatter = NumberFormat.getCurrencyInstance();
					String moneyStringMTD = formatter.format(total);
					if (moneyStringMTD.endsWith(".00")) {
					    int centsIndex = moneyStringMTD.lastIndexOf(".00");
					    if (centsIndex != -1) {
					    	moneyStringMTD = moneyStringMTD.substring(1, centsIndex);
					    }
					}
					
					totalCertifiedParticipants.setTotal("$" + moneyStringMTD);
					
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
				}
				
				if(SIRewardsDetailsList.size()>0){
					SIRewardsDetailsDTO SIRewardsDetailsDTO = SIRewardsDetailsList.get(0);
					
					totalMasterCertifiedParticipants.setName(sid + " BC Rank");
					totalMasterCertifiedParticipants.setTotal(SIRewardsDetailsDTO.getBCAdvisorRankEarnings() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
					
				}
				
			}
		
			
			return topTenChart;
		}
		case "30":
		{
			TopTenChart topTenChart = new TopTenChart();
			
			TotalName years = new TotalName();
			TotalName cartificationLevel = new TotalName();
			TotalName totalCertifiedParticipants = new TotalName();
			TotalName totalMasterCertifiedParticipants = new TotalName();
			TotalName totalCertifiedSpecialistParticipants = new TotalName();
			TotalName totalCertifiedLevelParticipants = new TotalName();
			TotalName dealershipMasterCertifiedRankWithinBC = new TotalName();
			if(type.equals("Executive")){
				List<RetentionGraphDTO> RetentionGraphServiceManagerList = this.dashService.getRetentionGraphNATByPositionCode("09");
				List<RetentionGraphDTO> RetentionGraphPartsAdvisorList = this.dashService.getRetentionGraphNATByPositionCode("14");
				List<RetentionGraphDTO> RetentionGraphServiceTechnicianList = this.dashService.getRetentionGraphNATByPositionCode("23");
				List<RetentionGraphDTO> RetentionGraphServiceAdvisorList = this.dashService.getRetentionGraphNATByPositionCode("13");
				List<RetentionGraphDTO> RetentionGraphBLSCList = this.dashService.getRetentionGraphNATByPositionCode("42");
				List<RetentionGraphDTO> RetentionGraphSalesManagerList = this.dashService.getRetentionGraphNATByPositionCode("04");
				List<RetentionGraphDTO> RetentionGraphPartsManagerList = this.dashService.getRetentionGraphNATByPositionCode("08");
				
				DecimalFormat df = new DecimalFormat("#.0");
				if(RetentionGraphServiceManagerList.size()>0){
					cartificationLevel.setName("YTD Rolling 12 Month Retention % for Service Managers");
					cartificationLevel.setTotal(df.format(RetentionGraphServiceManagerList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevel));
				}
				
				if(RetentionGraphServiceAdvisorList.size()>0){
					years.setName("YTD Rolling 12 Month Retention % for Service Advisors");
					years.setTotal(df.format(RetentionGraphServiceAdvisorList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
				}
				
				if(RetentionGraphPartsManagerList.size()>0){
					totalCertifiedParticipants.setName("YTD Rolling 12 Month Retention % for Parts Managers");
					totalCertifiedParticipants.setTotal(df.format(RetentionGraphPartsManagerList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
				}
				
				if(RetentionGraphPartsAdvisorList.size()>0){
					totalMasterCertifiedParticipants.setName("YTD Rolling 12 Month Retention % for Parts Advisors");
					totalMasterCertifiedParticipants.setTotal(df.format(RetentionGraphPartsAdvisorList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
				}
				
				if(RetentionGraphServiceTechnicianList.size()>0){
					totalCertifiedSpecialistParticipants.setName("YTD Rolling 12 Month Retention % for Technicians");
					totalCertifiedSpecialistParticipants.setTotal(df.format(RetentionGraphServiceTechnicianList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
				}
				if(RetentionGraphSalesManagerList.size()>0){
					totalCertifiedLevelParticipants.setName("YTD Rolling 12 Month Retention % for for Sales Managers");
					totalCertifiedLevelParticipants.setTotal(df.format(RetentionGraphSalesManagerList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedLevelParticipants));
				}
				if(RetentionGraphBLSCList.size()>0){
					dealershipMasterCertifiedRankWithinBC.setName("YTD Rolling 12 Month Retention % for for Sales Consultants");
					dealershipMasterCertifiedRankWithinBC.setTotal(df.format(RetentionGraphBLSCList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedRankWithinBC));
				}
			}else if(type.equals("BC")){
				String territory = BC;
				List<RetentionGraphDTO> RetentionGraphServiceManagerList = this.dashService.getRetentionGraphBCByBcAndPositionCodeByPositionCode(territory, "09");
				List<RetentionGraphDTO> RetentionGraphPartsAdvisorList = this.dashService.getRetentionGraphBCByBcAndPositionCodeByPositionCode(territory, "14");
				List<RetentionGraphDTO> RetentionGraphServiceTechnicianList = this.dashService.getRetentionGraphBCByBcAndPositionCodeByPositionCode(territory, "23");
				List<RetentionGraphDTO> RetentionGraphServiceAdvisorList = this.dashService.getRetentionGraphBCByBcAndPositionCodeByPositionCode(territory, "13");
				List<RetentionGraphDTO> RetentionGraphBLSCList = this.dashService.getRetentionGraphBCByBcAndPositionCodeByPositionCode(territory, "42");
				List<RetentionGraphDTO> RetentionGraphSalesManagerList = this.dashService.getRetentionGraphBCByBcAndPositionCodeByPositionCode(territory, "04");
				List<RetentionGraphDTO> RetentionGraphPartsManagerList = this.dashService.getRetentionGraphBCByBcAndPositionCodeByPositionCode(territory, "08");
				
				DecimalFormat df = new DecimalFormat("#.0");
				if(RetentionGraphServiceManagerList.size()>0){
					cartificationLevel.setName("YTD Rolling 12 Month Retention % for Service Managers");
					cartificationLevel.setTotal(df.format(RetentionGraphServiceManagerList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevel));
				}
				
				if(RetentionGraphServiceAdvisorList.size()>0){
					years.setName("YTD Rolling 12 Month Retention % for Service Advisors");
					years.setTotal(df.format(RetentionGraphServiceAdvisorList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
				}
				
				if(RetentionGraphPartsManagerList.size()>0){
					totalCertifiedParticipants.setName("YTD Rolling 12 Month Retention % for Parts Managers");
					totalCertifiedParticipants.setTotal(df.format(RetentionGraphPartsManagerList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
				}
				
				if(RetentionGraphPartsAdvisorList.size()>0){
					totalMasterCertifiedParticipants.setName("YTD Rolling 12 Month Retention % for Parts Advisors");
					totalMasterCertifiedParticipants.setTotal(df.format(RetentionGraphPartsAdvisorList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
				}
				
				if(RetentionGraphServiceTechnicianList.size()>0){
					totalCertifiedSpecialistParticipants.setName("YTD Rolling 12 Month Retention % for Technicians");
					totalCertifiedSpecialistParticipants.setTotal(df.format(RetentionGraphServiceTechnicianList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
				}
				if(RetentionGraphSalesManagerList.size()>0){
					totalCertifiedLevelParticipants.setName("YTD Rolling 12 Month Retention % for for Sales Managers");
					totalCertifiedLevelParticipants.setTotal(df.format(RetentionGraphSalesManagerList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedLevelParticipants));
				}
				if(RetentionGraphBLSCList.size()>0){
					dealershipMasterCertifiedRankWithinBC.setName("YTD Rolling 12 Month Retention % for for Sales Consultants");
					dealershipMasterCertifiedRankWithinBC.setTotal(df.format(RetentionGraphBLSCList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedRankWithinBC));
				}
				
			}
			return topTenChart;
		}
		case "31":
		{
			//List<RetentionGraphDTO> listOfFirstLevel = this.dashService.getRetentionGraphNAT();
			
			
			/*List<String> filters = new ArrayList<String>();
			for(RetentionGraphDTO item: listOfFirstLevel){
				if(item.getChildTerritory() != null && !filters.contains(item.getChildTerritory()))
					filters.add(item.getChildTerritory());
			}*/
			List<String> filters = new ArrayList<String>(Arrays.asList("CA","DN","GL","MA","MW","NE","SE","SW","WE"));
			//main chart model
			Chart chart = new Chart("Retention % By Employee Type", "", "Retention Rate", "", "column_stack");
			
			//Create first level of the chart
			//Get Distinct program names
			List<String> programs = new ArrayList<String>(Arrays.asList("09","14","23","13","42","04","08"));

			List<ChartData> attributes = new ArrayList<ChartData>();
			
			for(String programName: programs){
				attributes.add(new ChartData(programName, 0));
			}
			
			
			for(ChartData item: attributes){
				double total = 0;
				for(String filter: filters){
					List<String> in = new ArrayList<String>(Arrays.asList(filter));
					List<RetentionGraphDTO> innerList = this.dashService.getRetentionGraphByParentTerritoryListAndPositionCode(in, item.getName());
					ChartData chartD = new ChartData(filter, total);
					for(RetentionGraphDTO a: innerList){
						total += a.getPercentage();
						chartD.addData(new ChartData(a.getChildTerritory(), a.getPercentage()));
					}
					total = total/innerList.size();
					chartD.setValue(total);
					total = 0;
					// with out b =0; java skips the next line
					int b = 0;
					//IF BC Level, ADD BC, IF NOT DON"T
					if(type.equals("BC") && filter.equals(BC)){
						item.addData(chartD);
					}else if(type.equals("Executive")){
						item.addData(chartD);
					}
					b = 1;
				}
			}
			
			chart.setData(attributes);
			chart.setUnit("%");
			chart.setRetention(true);
			return chart;
		}
		case "32":
		{
			List<String> filters = new ArrayList<String>(Arrays.asList("NAT"));
			List<CustomerFirstGraphDTO> listBC = this.dashService.getCustomerFirstGraphByParentTerritoryAndToggle(filters, "Total");
			
			filters = new ArrayList<String>();
			
			for(CustomerFirstGraphDTO item: listBC){
				filters.add(item.getChildTerritory());
				}
			
			List<CustomerFirstGraphDTO> listBC_unfiltered = this.dashService.getCustomerFirstGraphByParentTerritoryAndToggle(filters, "Total");
			
			
			
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap2 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap3 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap4 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap5 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap6 = new HashMap<String, List<ChartData>>();
			//Map<String, List<ChartData>> chartsMap7 = new HashMap<String, List<ChartData>>();
			for(CustomerFirstGraphDTO item: listBC_unfiltered){
				filters.add(item.getChildTerritory());
				if(!map.containsKey(item.getParentTerritory())){
					map.put(item.getParentTerritory(), new ArrayList<String>());
					//chartsMap.put(item.getParentTerritory(), new ArrayList<ChartData>());
					//chartsMap2.put(item.getParentTerritory(), new ArrayList<ChartData>());
					//chartsMap3.put(item.getParentTerritory(), new ArrayList<ChartData>());
				}
				List<String> temp = map.get(item.getParentTerritory());
				if(type.equals("BC")){
					//get users BC
					if(item.getChildTerritory().contains(BC)){
						temp.add(item.getChildTerritory());
					}
				}else{
					temp.add(item.getChildTerritory());
				}
				map.put(item.getParentTerritory(), temp);
			}
			//List<CustomerFirstGraphDTO> sublist = this.dashService.getCustomerFirstGraphByParentTerritoryAndToggle(filters, "Total");
			
			
			Chart chart = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completed QTD", "", "", "Pillars QTD", "column_stack");
			Chart chart2 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completed QTD", "", "", "Pillars QTD", "column_stack");
			Chart chart3 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completed QTD", "", "", "Pillars QTD", "column_stack");
			Chart chart4 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completed QTD", "", "", "Pillars QTD", "column_stack");
			Chart chart5 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completed QTD", "", "", "Pillars QTD", "column_stack");
			Chart chart6 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completed QTD", "", "", "Pillars QTD", "column_stack");
			//Chart chart7 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completed QTD", "", "", "Total", "column_stack");

			Map<String, Double> mapValues = new HashMap<String, Double>();
			Map<String, Double> mapValues2 = new HashMap<String, Double>();
			Map<String, Double> mapValues3 = new HashMap<String, Double>();
			Map<String, Double> mapValues4 = new HashMap<String, Double>();
			Map<String, Double> mapValues5 = new HashMap<String, Double>();
			Map<String, Double> mapValues6 = new HashMap<String, Double>();
			//Map<String, Double> mapValues7 = new HashMap<String, Double>();
			for(CustomerFirstGraphDTO item: listBC){
				List<ChartData> list = new ArrayList<ChartData>();
				List<ChartData> list2 = new ArrayList<ChartData>();
				List<ChartData> list3 = new ArrayList<ChartData>();
				List<ChartData> list4 = new ArrayList<ChartData>();
				List<ChartData> list5 = new ArrayList<ChartData>();
				List<ChartData> list6 = new ArrayList<ChartData>();
				//List<ChartData> list7 = new ArrayList<ChartData>();
				for(CustomerFirstGraphDTO object: listBC_unfiltered){
					if(item.getChildTerritory().equals(object.getParentTerritory())){
						ChartData temp = new ChartData();
						temp.setName(object.getChildTerritory());
						temp.setValue(object.getNoCertification());
						list.add(temp);
						
						temp = new ChartData();
						temp.setName(object.getChildTerritory());
						temp.setValue(object.getPerformance());
						list2.add(temp);
						
						temp = new ChartData();
						temp.setName(object.getChildTerritory());
						temp.setValue(object.getProcess());
						list3.add(temp);
						
						temp = new ChartData();
						temp.setName(object.getChildTerritory());
						temp.setValue(object.getVoiceofEmployee());
						list4.add(temp);
						
						temp = new ChartData();
						temp.setName(object.getChildTerritory());
						temp.setValue(object.getTraining());
						list5.add(temp);
						
						temp = new ChartData();
						temp.setName(object.getChildTerritory());
						temp.setValue(object.getFacility());
						list6.add(temp);
						
					/*	temp = new ChartData();
						temp.setName(object.getParentTerritory());
						temp.setValue(object.getCFAFEAwardCertification());
						list7.add(temp);*/
						
					}
					chartsMap.put(item.getChildTerritory(), list);
					chartsMap2.put(item.getChildTerritory(), list2);
					chartsMap3.put(item.getChildTerritory(), list3);
					chartsMap4.put(item.getChildTerritory(), list4);
					chartsMap5.put(item.getChildTerritory(), list5);
					chartsMap6.put(item.getChildTerritory(), list6);
					//chartsMap7.put(item.getParentTerritory(), list7);
					mapValues.put(item.getChildTerritory(), item.getNoCertification());
					mapValues2.put(item.getChildTerritory(), item.getPerformance());
					mapValues3.put(item.getChildTerritory(), item.getProcess());
					mapValues4.put(item.getChildTerritory(), item.getVoiceofEmployee());
					mapValues5.put(item.getChildTerritory(), item.getTraining());
					mapValues6.put(item.getChildTerritory(), item.getFacility());
					//mapValues7.put(item.getParentTerritory(), item.getCFAFEAwardCertification());
				}
			}
			
			
			List<ChartData> list1 = new ArrayList<ChartData>();
			list1.add(new ChartData("Level 0", 0));
			list1.add(new ChartData("Performance", 0));
			list1.add(new ChartData("Process", 0));
			list1.add(new ChartData("Voice of Employee", 0));
			list1.add(new ChartData("Training", 0));
			list1.add(new ChartData("Facility", 0));
			//list1.add(new ChartData("CFAFE Award Certification", 0));
			
			List<ChartData> a = new ArrayList<ChartData>(chart.getData());
			List<ChartData> b = new ArrayList<ChartData>(chart2.getData());
			List<ChartData> c = new ArrayList<ChartData>(chart3.getData());
			List<ChartData> d = new ArrayList<ChartData>(chart4.getData());
			List<ChartData> e = new ArrayList<ChartData>(chart5.getData());
			List<ChartData> f = new ArrayList<ChartData>(chart6.getData());
			//List<ChartData> g = new ArrayList<ChartData>(chart7.getData());
			
			double tempa =0;
			double tempb =0;
			double tempc =0;
			double tempd =0;
			double tempe =0;
			double tempf =0;
			//double tempg =0;
			
			
			for(ChartData item: a){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Level 0"))
						tempa += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues.get(item.getName()));
				item.addDataList(chartsMap.get(item.getName()));
			}

			for(ChartData item: b){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Performance"))
						tempb += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues2.get(item.getName()));
				item.addDataList(chartsMap2.get(item.getName()));
			}

				
			for(ChartData item: c){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Process"))
						tempc += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues3.get(item.getName()));
				item.addDataList( chartsMap3.get(item.getName()));
			}
			
			
			for(ChartData item: d){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Voice of Employee"))
						tempd+= var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues2.get(item.getName()));
				item.addDataList(chartsMap2.get(item.getName()));
			}

				
			for(ChartData item: e){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Training"))
						tempe += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues3.get(item.getName()));
				item.addDataList( chartsMap3.get(item.getName()));
			}
			
			for(ChartData item: f){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Facility"))
						tempf += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues3.get(item.getName()));
				item.addDataList( chartsMap3.get(item.getName()));
			}
			
			/*
			for(ChartData item: g){
				for(ChartData var: item.getData()){
					if(var.getName().equals("CFAFE Award Certification"))
						tempc = var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues3.get(item.getName()));
				item.addDataList( chartsMap3.get(item.getName()));
			}*/

			
			list1.get(0).setValue(tempa);
			list1.get(1).setValue(tempb);
			list1.get(2).setValue(tempc);
			list1.get(3).setValue(tempd);
			list1.get(4).setValue(tempe);
			list1.get(5).setValue(tempf);
			//list1.get(6).setValue(tempg);
			list1.get(0).setData(a);
			list1.get(1).setData(b);
			list1.get(2).setData(c);
			list1.get(3).setData(d);
			list1.get(4).setData(e);
			list1.get(5).setData(f);
			//list1.get(6).setData(g);
			
			chart.setData(list1);
			chart.setCustomer_first(true);
			chart.setType("pie");
			return chart;
			
			//special mapping for stacked column
		}
		case "33":
		{
			List<String> filters = new ArrayList<String>(Arrays.asList("NAT"));
			List<CustomerFirstGraphDTO> listBC = this.dashService.getCustomerFirstGraphByParentTerritoryAndToggle(filters, "Percentage");
			
			filters = new ArrayList<String>();
			
			for(CustomerFirstGraphDTO item: listBC){
				filters.add(item.getChildTerritory());
				}
			
			List<CustomerFirstGraphDTO> listBC_unfiltered = this.dashService.getCustomerFirstGraphByParentTerritoryAndToggle(filters, "Total");
			
			
			
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap2 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap3 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap4 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap5 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap6 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap7 = new HashMap<String, List<ChartData>>();
			for(CustomerFirstGraphDTO item: listBC_unfiltered){
				filters.add(item.getChildTerritory());
				if(!map.containsKey(item.getParentTerritory())){
					map.put(item.getParentTerritory(), new ArrayList<String>());
					//chartsMap.put(item.getParentTerritory(), new ArrayList<ChartData>());
					//chartsMap2.put(item.getParentTerritory(), new ArrayList<ChartData>());
					//chartsMap3.put(item.getParentTerritory(), new ArrayList<ChartData>());
				}
				List<String> temp = map.get(item.getParentTerritory());
				if(type.equals("BC")){
					//get users BC
					if(item.getChildTerritory().contains(BC)){
						temp.add(item.getChildTerritory());
					}
				}else{
					temp.add(item.getChildTerritory());
				}
				map.put(item.getParentTerritory(), temp);
			}
			//List<CustomerFirstGraphDTO> sublist = this.dashService.getCustomerFirstGraphByParentTerritoryAndToggle(filters, "Total");
			
			
			Chart chart = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars  Completion", "", "", "Completion Percentage", "column_stack");
			Chart chart2 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completion", "", "", "Completion Percentage", "column_stack");
			Chart chart3 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completion", "", "", "Completion Percentage", "column_stack");
			Chart chart4 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completion", "", "", "Completion Percentage", "column_stack");
			Chart chart5 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completion", "", "", "Completion Percentage", "column_stack");
			Chart chart6 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completion", "", "", "Completion Percentage", "column_stack");
			Chart chart7 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completion", "", "", "Completion Percentage", "column_stack");

			Map<String, Double> mapValues = new HashMap<String, Double>();
			Map<String, Double> mapValues2 = new HashMap<String, Double>();
			Map<String, Double> mapValues3 = new HashMap<String, Double>();
			Map<String, Double> mapValues4 = new HashMap<String, Double>();
			Map<String, Double> mapValues5 = new HashMap<String, Double>();
			Map<String, Double> mapValues6 = new HashMap<String, Double>();
			Map<String, Double> mapValues7 = new HashMap<String, Double>();
			for(CustomerFirstGraphDTO item: listBC){
				List<ChartData> list = new ArrayList<ChartData>();
				List<ChartData> list2 = new ArrayList<ChartData>();
				List<ChartData> list3 = new ArrayList<ChartData>();
				List<ChartData> list4 = new ArrayList<ChartData>();
				List<ChartData> list5 = new ArrayList<ChartData>();
				List<ChartData> list6 = new ArrayList<ChartData>();
				List<ChartData> list7 = new ArrayList<ChartData>();
				for(CustomerFirstGraphDTO object: listBC_unfiltered){
					if(item.getChildTerritory().equals(object.getParentTerritory())){
						ChartData temp = new ChartData();
						temp.setName(object.getChildTerritory());
						temp.setValue(object.getNoCertification());
						list.add(temp);
						
						temp = new ChartData();
						temp.setName(object.getChildTerritory());
						temp.setValue(object.getPerformance());
						list2.add(temp);
						
						temp = new ChartData();
						temp.setName(object.getChildTerritory());
						temp.setValue(object.getProcess());
						list3.add(temp);
						
						temp = new ChartData();
						temp.setName(object.getChildTerritory());
						temp.setValue(object.getVoiceofEmployee());
						list4.add(temp);
						
						temp = new ChartData();
						temp.setName(object.getChildTerritory());
						temp.setValue(object.getTraining());
						list5.add(temp);
						
						temp = new ChartData();
						temp.setName(object.getChildTerritory());
						temp.setValue(object.getFacility());
						list6.add(temp);
						
						temp = new ChartData();
						temp.setName(object.getParentTerritory());
						temp.setValue(object.getCFAFEAwardCertification());
						list7.add(temp);
						
					}
					chartsMap.put(item.getChildTerritory(), list);
					chartsMap2.put(item.getChildTerritory(), list2);
					chartsMap3.put(item.getChildTerritory(), list3);
					chartsMap4.put(item.getChildTerritory(), list4);
					chartsMap5.put(item.getChildTerritory(), list5);
					chartsMap6.put(item.getChildTerritory(), list6);
					chartsMap7.put(item.getParentTerritory(), list7);
					mapValues.put(item.getChildTerritory(), item.getNoCertification());
					mapValues2.put(item.getChildTerritory(), item.getPerformance());
					mapValues3.put(item.getChildTerritory(), item.getProcess());
					mapValues4.put(item.getChildTerritory(), item.getVoiceofEmployee());
					mapValues5.put(item.getChildTerritory(), item.getTraining());
					mapValues6.put(item.getChildTerritory(), item.getFacility());
					mapValues7.put(item.getParentTerritory(), item.getCFAFEAwardCertification());
				}
			}
			
			
			List<ChartData> list1 = new ArrayList<ChartData>();
			list1.add(new ChartData("Level 0", 0));
			list1.add(new ChartData("Performance", 0));
			list1.add(new ChartData("Process", 0));
			list1.add(new ChartData("Voice of Employee", 0));
			list1.add(new ChartData("Training", 0));
			list1.add(new ChartData("Facility", 0));
			list1.add(new ChartData("CFAFE Award Certification", 0));
			
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
					if(var.getName().equals("Level 0"))
						tempa += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues.get(item.getName()));
				item.addDataList(chartsMap.get(item.getName()));
			}

			for(ChartData item: b){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Performance"))
						tempb += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues2.get(item.getName()));
				item.addDataList(chartsMap2.get(item.getName()));
			}

				
			for(ChartData item: c){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Process"))
						tempc += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues3.get(item.getName()));
				item.addDataList( chartsMap3.get(item.getName()));
			}
			
			
			for(ChartData item: d){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Voice of Employee"))
						tempd += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues2.get(item.getName()));
				item.addDataList(chartsMap2.get(item.getName()));
			}

				
			for(ChartData item: e){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Training"))
						tempe += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues3.get(item.getName()));
				item.addDataList( chartsMap3.get(item.getName()));
			}
			
			for(ChartData item: f){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Facility"))
						tempf += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues3.get(item.getName()));
				item.addDataList( chartsMap3.get(item.getName()));
			}
			
			
			for(ChartData item: g){
				for(ChartData var: item.getData()){
					if(var.getName().equals("CFAFE Award Certification"))
						tempg += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues3.get(item.getName()));
				item.addDataList( chartsMap3.get(item.getName()));
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
			
			//special mapping for stacked column
		}
		case "34":
		{
			List<String> filters = new ArrayList<String>(Arrays.asList("NAT"));
			List<TTTAEnrolledGraphDTO> listBC = this.dashService.getTTTAEnrolledByParentTerritory(filters);
			filters = new ArrayList<String>();
			for(TTTAEnrolledGraphDTO item: listBC){
				if(type.equals("BC")){
					//get users BC
					if(item.getChild().contains(BC)){
						filters.add(item.getChild());
					}
				}else{
					filters.add(item.getChild());
				}
			}
			
			List<TTTAEnrolledGraphDTO> sublist = this.dashService.getTTTAEnrolledByParentTerritory(filters);
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap2 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap3 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap4 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap5 = new HashMap<String, List<ChartData>>();
		/*	for(TTTAEnrolledGraphDTO item: sublist){
				if(!map.containsKey(item.getParent())){
					map.put(item.getParent(), new ArrayList<String>());
				}
				List<String> temp = map.get(item.getParent());
				if(type.equals("BC")){
					//get users BC
					if(item.getChild().contains(BC)){
						temp.add(item.getChild());
					}
				}else{
					temp.add(item.getChild());
				}
				map.put(item.getParent(), temp);
			}
			*/
			
			Chart chart = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Dealers Enrolled YTD", "", "", "Total", "column_stack");
			Chart chart2 = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Dealers Enrolled YTD", "", "", "Total", "column_stack");
			Chart chart3 = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Dealers Enrolled YTD", "", "", "Total", "column_stack");
			Chart chart4 = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Dealers Enrolled YTD", "", "", "Total", "column_stack");
			Chart chart5 = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Dealers Enrolled YTD", "", "", "Total", "column_stack");

			Map<String, Double> mapValues = new HashMap<String, Double>();
			Map<String, Double> mapValues2 = new HashMap<String, Double>();
			Map<String, Double> mapValues3 = new HashMap<String, Double>();
			Map<String, Double> mapValues4 = new HashMap<String, Double>();
			Map<String, Double> mapValues5 = new HashMap<String, Double>();
			for(TTTAEnrolledGraphDTO item: listBC){
				List<ChartData> list = new ArrayList<ChartData>();
				List<ChartData> list2 = new ArrayList<ChartData>();
				List<ChartData> list3 = new ArrayList<ChartData>();
				List<ChartData> list4 = new ArrayList<ChartData>();
				List<ChartData> list5 = new ArrayList<ChartData>();
				for(TTTAEnrolledGraphDTO object: sublist){
					if(item.getChild().equals(object.getParent())){
						ChartData temp = new ChartData();
						temp.setName(object.getChild());
						temp.setValue(object.getTotalGroupA());
						list.add(temp);
						temp = new ChartData();
						temp.setName(object.getChild());
						temp.setValue(object.getTotalGroupB());
						list2.add(temp);
						
						
						temp = new ChartData();
						temp.setName(object.getChild());
						temp.setValue(object.getTotalGroupC());
						list3.add(temp);
						
						temp = new ChartData();
						temp.setName(object.getChild());
						temp.setValue(object.getTotalGroupD());
						list4.add(temp);
						
						temp = new ChartData();
						temp.setName(object.getChild());
						temp.setValue(object.getTotalGroupE());
						list5.add(temp);
						
						
					}
					chartsMap.put(item.getChild(), list);
					chartsMap2.put(item.getChild(), list2);
					chartsMap3.put(item.getChild(), list3);
					chartsMap4.put(item.getChild(), list4);
					chartsMap5.put(item.getChild(), list5);
					mapValues.put(item.getChild(), (double)item.getTotalGroupA());
					mapValues2.put(item.getChild(), (double)item.getTotalGroupB());
					mapValues3.put(item.getChild(), (double)item.getTotalGroupC());
					mapValues4.put(item.getChild(), (double)item.getTotalGroupD());
					mapValues5.put(item.getChild(), (double)item.getTotalGroupE());
				}
			}
			
			
			List<ChartData> list1 = new ArrayList<ChartData>();
			list1.add(new ChartData("Total Group A", 0));
			list1.add(new ChartData("Total Group B", 0));
			list1.add(new ChartData("Total Group C", 0)); 
			list1.add(new ChartData("Total Group D", 0)); 
			list1.add(new ChartData("Total Group E", 0));
			
			List<ChartData> a = new ArrayList<ChartData>(chart.getData());
			List<ChartData> b = new ArrayList<ChartData>(chart2.getData());
			List<ChartData> c = new ArrayList<ChartData>(chart3.getData());
			List<ChartData> d = new ArrayList<ChartData>(chart4.getData());
			List<ChartData> e = new ArrayList<ChartData>(chart5.getData());
			
			double tempa =0;
			double tempb =0;
			double tempc =0;
			double tempd =0;
			double tempe =0;
			
			for(ChartData item: a){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Total Group A"))
						tempa += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues.get(item.getName()));
				item.addDataList(chartsMap.get(item.getName()));
			}

			for(ChartData item: b){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Total Group B"))
						tempb += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues2.get(item.getName()));
				item.addDataList(chartsMap2.get(item.getName()));
			}

				
			for(ChartData item: c){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Total Group C"))
						tempc += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues3.get(item.getName()));
				item.addDataList( chartsMap3.get(item.getName()));
			}

			for(ChartData item: d){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Total Group D"))
						tempd += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues4.get(item.getName()));
				item.addDataList( chartsMap4.get(item.getName()));
			}

			for(ChartData item: e){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Total Group E"))
						tempe += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues5.get(item.getName()));
				item.addDataList( chartsMap5.get(item.getName()));
			}

			
			list1.get(0).setValue(tempa);
			list1.get(1).setValue(tempb);
			list1.get(2).setValue(tempc);
			list1.get(3).setValue(tempd);
			list1.get(4).setValue(tempe);
			list1.get(0).setData(a);
			list1.get(1).setData(b);
			list1.get(2).setData(c);
			list1.get(3).setData(d);
			list1.get(4).setData(e);
			
			chart.setData(list1);
			return chart;
			
			//special mapping for stacked column
		}
		default:
			return "No such service call exists.";
		}
	}
	
	
	
	
	public @ResponseBody Object findTilesManager(String id, String positionCode, String dealerCode, UserDetailsImpl user, String type) {
		String territory = "";
		if(type.equals("Participant")){
			territory = user.getUserId().trim();
		}else if( type.equals("Manager") || type.equals("Dealer")){
			territory = dealerCode;
		}else if(type.equals("District")){
			
			List<String> bcSet = this.dashService.getUserTerritoyById(user.getUserId());
			if(bcSet.size() > 0){
				territory = bcSet.get(0);
			}
			/*List<String> a= this.dashService.getDistrictByDealerCode(dealerCode);
			if(a.size() > 0){
				territory = a.get(0); 
			}else{
				return null;
			}*/
		}
		
		//divide the switch statement to functions
		switch(id){
		case "2":
		{
			TopTenChart topTenChart = new TopTenChart();
			if(type.equals("Participant")){
				
				TotalName mtd = this.dashService.getParticipantExcellanceCardAwardMTD(territory);
				mtd.setTotal("$" + this.formatCurrency(Double.parseDouble(mtd.getTotal())));
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
				
				
				TotalName ytd = this.dashService.getParticipantExcellanceCardAwardYTD(territory);
				ytd.setTotal("$" + this.formatCurrency(Double.parseDouble(ytd.getTotal())));
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
				
			}else if(type.equals("Dealer")){
				
				//MAKE ONEFO DEALER?
				TotalName mtd = this.dashService.getParticipantExcellanceCardAwardMTD(user.getUserId().trim());
				mtd.setTotal(this.formatCurrency(Double.parseDouble(mtd.getTotal())));
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
				
				
				TotalName ytd = this.dashService.getParticipantExcellanceCardAwardYTD(user.getUserId().trim());
				ytd.setTotal(this.formatCurrency(Double.parseDouble(ytd.getTotal())));
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
				
				TotalName dealerscount = this.dashService.getMSERParticipantEnrolledByDealerCode(territory);
				dealerscount.setTotal(this.formatCurrency(Integer.parseInt(dealerscount.getTotal())));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
				
			}else if(type.equals("Manager")){
				TotalName mtd = this.dashService.getParticipantExcellanceCardAwardMTD(user.getUserId().trim());
				mtd.setTotal(this.formatCurrency(Double.parseDouble(mtd.getTotal())));
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
				
				
				TotalName ytd = this.dashService.getParticipantExcellanceCardAwardYTD(user.getUserId().trim());
				ytd.setTotal(this.formatCurrency(Double.parseDouble(ytd.getTotal())));
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
				
				
				TotalName dealerscount = this.dashService.getMSERParticipantEnrolledByDealerCode(territory);
				dealerscount.setTotal(this.formatCurrency(Integer.parseInt(dealerscount.getTotal())));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
				
			}else if(type.equals("District")){
				
				TotalName mtd = this.dashService.getMSEREarningsMTDByBCOrDistrict(territory);
				mtd.setTotal(this.formatCurrency(Double.parseDouble(mtd.getTotal())));
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
				
				
				TotalName ytd = this.dashService.getMSEREarningsYTDByBCOrDistrict(territory);
				ytd.setTotal(this.formatCurrency(Double.parseDouble(ytd.getTotal())));
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
				
				TotalName dealerscount = this.dashService.getMSERDealersCountByBCOrDistrict(territory);
				dealerscount.setTotal(this.formatCurrency(Integer.parseInt(dealerscount.getTotal())));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
			}
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
			
			int moneymtd =  (int)EarningsMTD.getEarnings();
			int moneyytd =  (int)EarningsYTD.getEarnings();
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			String moneyStringMTD = formatter.format(moneymtd);
			String moneyStringYTD = formatter.format(moneyytd);
			if (moneyStringMTD.endsWith(".00")) {
			    int centsIndex = moneyStringMTD.lastIndexOf(".00");
			    if (centsIndex != -1) {
			    	moneyStringMTD = moneyStringMTD.substring(1, centsIndex);
			    }
			}
			
			if (moneyStringYTD.endsWith(".00")) {
			    int centsIndex = moneyStringYTD.lastIndexOf(".00");
			    if (centsIndex != -1) {
			    	moneyStringYTD = moneyStringYTD.substring(1, centsIndex);
			    }
			}
			
			mtd.setName("Total Parts Earnings - MTD");
			ytd.setName("Total Parts Earnings - YTD");
			mtd.setTotal("$" + moneyStringMTD);
			ytd.setTotal("$" + moneyStringYTD);
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
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMVPMTD, "Top 3 MVP Plans Sold MTD", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMVPYTD, "Top 3 MVP Plans Sold YTD", tableheaders));
			
			datatable.setData(tabledata);
			
			//TotalName mtd = this.dashService.getMTDByProgramAndProgramgroup("Total Parts Earnings - MTD", "MVP Parts Sold", "MVP Parts Sold");
			//TotalName ytd = this.dashService.getYTDByProgramAndProgramgroup("Total Parts Earnings - YTD", "MVP Parts Sold", "MVP Parts Sold");
			
			
			TotalName mtd = new TotalName();
			TotalName ytd = new TotalName();
			MSERTopNDTO EarningsMTD = this.dashService.getMSERTopTen("Top 3 Earnings", 1, "MVP", "MTD").get(0);
			MSERTopNDTO EarningsYTD = this.dashService.getMSERTopTen("Top 3 Earnings", 1, "MVP", "YTD").get(0);
			
			int moneymtd =  (int)EarningsMTD.getEarnings();
			int moneyytd =  (int)EarningsYTD.getEarnings();
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			String moneyStringMTD = formatter.format(moneymtd);
			String moneyStringYTD = formatter.format(moneyytd);
			if (moneyStringMTD.endsWith(".00")) {
			    int centsIndex = moneyStringMTD.lastIndexOf(".00");
			    if (centsIndex != -1) {
			    	moneyStringMTD = moneyStringMTD.substring(1, centsIndex);
			    }
			}
			
			if (moneyStringYTD.endsWith(".00")) {
			    int centsIndex = moneyStringYTD.lastIndexOf(".00");
			    if (centsIndex != -1) {
			    	moneyStringYTD = moneyStringYTD.substring(1, centsIndex);
			    }
			}
			
			mtd.setName("Total Parts Earnings - MTD");
			ytd.setName("Total Parts Earnings - YTD");
			mtd.setTotal("$" + moneyStringMTD);
			ytd.setTotal("$" + moneyStringYTD);
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
			
			
			int moneymtd =  (int)EarningsMTD.getEarnings();
			int moneyytd =  (int)EarningsYTD.getEarnings();
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			String moneyStringMTD = formatter.format(moneymtd);
			String moneyStringYTD = formatter.format(moneyytd);
			if (moneyStringMTD.endsWith(".00")) {
			    int centsIndex = moneyStringMTD.lastIndexOf(".00");
			    if (centsIndex != -1) {
			    	moneyStringMTD = moneyStringMTD.substring(1, centsIndex);
			    }
			}
			
			if (moneyStringYTD.endsWith(".00")) {
			    int centsIndex = moneyStringYTD.lastIndexOf(".00");
			    if (centsIndex != -1) {
			    	moneyStringYTD = moneyStringYTD.substring(1, centsIndex);
			    }
			}
			
			mtd.setName("Total Parts Earnings - MTD");
			ytd.setName("Total Parts Earnings - YTD");
			mtd.setTotal("$" + moneyStringMTD);
			ytd.setTotal("$" + moneyStringYTD);
			
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
			
			
			int moneymtd =  (int)EarningsMTD.getEarnings();
			int moneyytd =  (int)EarningsYTD.getEarnings();
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			String moneyStringMTD = formatter.format(moneymtd);
			String moneyStringYTD = formatter.format(moneyytd);
			if (moneyStringMTD.endsWith(".00")) {
			    int centsIndex = moneyStringMTD.lastIndexOf(".00");
			    if (centsIndex != -1) {
			    	moneyStringMTD = moneyStringMTD.substring(1, centsIndex);
			    }
			}
			
			if (moneyStringYTD.endsWith(".00")) {
			    int centsIndex = moneyStringYTD.lastIndexOf(".00");
			    if (centsIndex != -1) {
			    	moneyStringYTD = moneyStringYTD.substring(1, centsIndex);
			    }
			}
			
			mtd.setName("Total Parts Earnings - MTD");
			ytd.setName("Total Parts Earnings - YTD");
			mtd.setTotal("$" + moneyStringMTD);
			ytd.setTotal("$" + moneyStringYTD);
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
						
			topTenChart.setTop3(datatable.getData());
			
			return topTenChart;
		}
		case "8":
		{
			TopTenChart topTenChart = new TopTenChart();
			//TopTenDataTable datatable = new TopTenDataTable("");
			
			/*List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();
			
			datatable.setData(tabledata);
			
			topTenChart.setDatatable(datatable);*/
			
			TotalName dealerscount = this.dashService.getDealersCountWithPercentage();
			
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
			
			return topTenChart;
		}
		case "9":
		{
			List<String> filters = new ArrayList<String>();
			filters.add(territory);
			List<BrainBoostWinndersGraphDTO> sublist = this.dashService.getBrainBoostWinndersGraphgetByChildTerritory(filters);
			
			Chart chart = new Chart();
			chart.setTitle("YTD Winners");
			chart.setSubTitle("");
			chart.setType("column");
			if(type.equals("Manager") || type.equals("Dealer")){
				chart.setXaxisTitle("Dealer");
			}else if(type.equals("District")){
				chart.setXaxisTitle("District");
			}
			chart.setYaxisTitle("Total Winners");
			if(sublist.size() > 0){
				List<ChartData> list = new ArrayList<ChartData>();
				ChartData chartData = new ChartData();	
				chartData.setName(sublist.get(0).getChildTerritory());
				chartData.setValue(sublist.get(0).getWinners());
				list.add(chartData);
				chart.setData(list);
			}
			return chart;
		}
		case "10":
		{
			
			List<String> filters = new ArrayList<String>();
			//if type = distric get distric
			filters.add(territory);
			List<CertProfsExpertGraphDTO> sublist = this.dashService.getExpertPointsEarnedByChildTerritory(filters);
			
			Chart chart = new Chart();
			chart.setTitle("Expert Points Earned YTD");
			chart.setSubTitle("");
			chart.setType("column");
			chart.setXaxisTitle("Dealer");
			chart.setYaxisTitle("Total Points");
			if(sublist.size() > 0){
				List<ChartData> list = new ArrayList<ChartData>();
				ChartData chartData = new ChartData();	
				chartData.setName(sublist.get(0).getChildTerritory());
				chartData.setValue(sublist.get(0).getTotalPoints());
				list.add(chartData);
				chart.setData(list);
			}
			return chart;
		}
		case "11":
		{
			List<String> filters = new ArrayList<String>();
			filters.add(territory);
			// check for role, to know what data to display
			List<CertProfsExpertGraphDTO> CertProfsExpertGraphDTOJEEP = this.dashService.getParticipantCompletedByProgramByChildTerritoryAndCertType(filters, "JEEP%");
			List<CertProfsExpertGraphDTO> CertProfsExpertGraphDTORAM = this.dashService.getParticipantCompletedByProgramByChildTerritoryAndCertType(filters, "RAM%");
			Chart chart = new Chart();
			chart.setTitle("Participants Completed By Program");
			chart.setSubTitle("");
			chart.setType("column_stack");
			chart.setXaxisTitle("Total Numbers");
			chart.setYaxisTitle("");
			
			List<ChartData> list = new ArrayList<ChartData>();
			list.add(new ChartData("JEEP", 0));
			list.add(new ChartData("RAM", 0));
			
			if(CertProfsExpertGraphDTOJEEP.size() > 0){
				ChartData data = new ChartData();
				data.setName(CertProfsExpertGraphDTOJEEP.get(0).getChildTerritory());
				data.setValue(CertProfsExpertGraphDTOJEEP.get(0).getCert());
				list.get(0).setValue(data.getValue());
				list.get(0).addData(data);
			}
			
			if(CertProfsExpertGraphDTORAM.size() > 0){
				ChartData data = new ChartData();
				data.setName(CertProfsExpertGraphDTORAM.get(0).getChildTerritory());
				data.setValue(CertProfsExpertGraphDTORAM.get(0).getCert());
				list.get(1).setValue(data.getValue());
				list.get(1).addData(data);
			}
		
			chart.setData(list);
			
			return chart;
			
		}
		case "12":
		{
			List<String> filters = new ArrayList<String>();
			filters.add(territory);
			// check for role, to know what data to display
			List<BrainBoostWinndersGraphDTO> BrainBoostWinndersGraphDTO = this.dashService.getBrainBoostWinndersGraphgetByChildTerritory(filters);
			
			Chart chart = new Chart();
			chart.setTitle("Exellence Card Award And Award Points YTD");
			chart.setSubTitle("");
			chart.setType("column_compound");
			chart.setXaxisTitle("");
			chart.setYaxisTitle("");
			
			List<ChartData> list = new ArrayList<ChartData>();
			
			ChartData chartData = new ChartData();
			ChartData excellenceCard = new ChartData();
			ChartData awardPoints = new ChartData();
			
			if(BrainBoostWinndersGraphDTO.size()>0){
			chartData.setName(BrainBoostWinndersGraphDTO.get(0).getChildTerritory());
			
			excellenceCard.setName("Excellence Card");
			excellenceCard.setValue(BrainBoostWinndersGraphDTO.get(0).getEarnings());
			
			awardPoints.setName("Award Points");
			awardPoints.setValue(BrainBoostWinndersGraphDTO.get(0).getPoints());
			
			chartData.setValue(excellenceCard.getValue() + awardPoints.getValue());
			chartData.addData(excellenceCard);
			chartData.addData(awardPoints);
			list.add(chartData);
			}
			chart.setData(list);
		    
			return chart;
		}
		case "13":
		{
			
			Chart chart = new Chart();
			chart.setTitle("Certified Proffessionals - YTD Certifications");
			chart.setSubTitle("");
			chart.setType("column_stack");
			chart.setXaxisTitle("Total Certification");
			chart.setYaxisTitle("");
			
			List<String> filters = new ArrayList<String>();
			filters.add(territory);
			// check for role, to know what data to display
			List<CertProfsWinnersGraphDTO> CertProfsWinnersGraphDTO = this.dashService.getCertProfsWinnersGraphByChildTerritory(filters);
			
			
			
			List<ChartData> list = new ArrayList<ChartData>();
			
				ChartData chartData = new ChartData();
				ChartData certified = new ChartData();
				ChartData certifiedSpacialist = new ChartData();
				ChartData masterCertified = new ChartData();
				if(CertProfsWinnersGraphDTO.size() > 0){	
				chartData.setName(CertProfsWinnersGraphDTO.get(0).getChildTerritory());
				
				certified.setName("Certified");
				certified.setValue(Double.parseDouble(CertProfsWinnersGraphDTO.get(0).getCertified()));
				
				certifiedSpacialist.setName("Certified Spacialist");
				certifiedSpacialist.setValue(Double.parseDouble(CertProfsWinnersGraphDTO.get(0).getCertifiedSpecalist()));
				
				masterCertified.setName("Master Certified");
				masterCertified.setValue(Double.parseDouble(CertProfsWinnersGraphDTO.get(0).getMasterCertified()));
				
				chartData.addData(certified);
				chartData.addData(certifiedSpacialist);
				chartData.addData(masterCertified);
				chartData.setValue(certified.getValue() + certifiedSpacialist.getValue() + masterCertified.getValue());
				chartData.setName(CertProfsWinnersGraphDTO.get(0).getChildTerritory());
				list.add(chartData);
				chart.setData(list);
			}
			
			chart.setData(list);		
			
			return chart;
		}
		case "14":
		{
			TopTenChart topTenChart = new TopTenChart();
			if(type.equals("District")){
			//set datatables
			List<TTTATopNDTO> listAdvisorsQTD = this.dashService.getTTTATopN("TOP TA", 10);
			List<TTTATopNDTO> listAdvisorsYTD = this.dashService.getTTTATopN("TOP TA", 10);
			
			
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
			
			TotalName dealerscount = this.dashService.getTTTANATTopEnrolledDealerCountByBCDistrictAndPositionCode(territory,"13");
			dealerscount.setTotal(this.formatNumbers(Integer.parseInt(dealerscount.getTotal())));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
			
			
			
			List<String> filters = new ArrayList<String>(Arrays.asList(territory));
			List<TTTAEnrollmentsSummaryDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsSummaryByChildAndPositionCode(filters, "13");
			if(incentiveEligibleList.size()>0){
				TotalName incentiveEligible  = new TotalName();
				incentiveEligible.setName("Total Technicians Incentive Eligible");
				incentiveEligible.setTotal("0");
			
				TotalName qtdSurveyCount  = new TotalName();
				qtdSurveyCount.setName("QTD Survey Count");
				qtdSurveyCount.setTotal("0");
			
				TotalName avgSurveyCount  = new TotalName();
				avgSurveyCount.setName("Average Quarterly survey score");
				avgSurveyCount.setTotal("0");
			
			
				incentiveEligible.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getIncentiveEligible()));
				qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
				avgSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getAvgSurveyScore()));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgSurveyCount));
			}
			
			
			}
			
			if(type.equals("Manager") || type.equals("Dealer")){
				
				List<String> filters = new ArrayList<String>(Arrays.asList(territory));
				List<TTTAEnrollmentsSummaryDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsSummaryByChildAndPositionCode(filters, "13");
				
				if(incentiveEligibleList.size()>0){
					
				TotalName enrolled  = new TotalName();
				enrolled.setName("Total Advisor Enrolled");
				enrolled.setTotal("0");
				
				
				TotalName incentiveEligible  = new TotalName();
				incentiveEligible.setName("Total Advisor Incentive Eligible");
				incentiveEligible.setTotal("0");
				
				TotalName qtdSurveyCount  = new TotalName();
				qtdSurveyCount.setName("QTD Survey Count");
				qtdSurveyCount.setTotal("0");
				
				TotalName avgSurveyCount  = new TotalName();
				avgSurveyCount.setName("Average Quarterly survey score");
				avgSurveyCount.setTotal("0");
				
				TotalName rank  = new TotalName();
				rank.setName("Dealership Rank");
				rank.setTotal("0");
				
				
					enrolled.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getTotalEnrollments()));
					incentiveEligible.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getIncentiveEligible()));
					qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
					avgSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getAvgSurveyScore()));
					rank.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getTTTARank()));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(enrolled));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(rank));
			}
				
			}
			else if (type.equals("Participant")){
				
				List<TTTAEnrollmentsDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsBySID(territory, "23");
				//survey score
				//survey count
				
				if(incentiveEligibleList.size() > 0){
					
					
					TotalName incentiveEligible  = new TotalName();
					incentiveEligible.setName("Incentive Eligible");
					incentiveEligible.setTotal("N");
					
					TotalName qtdSurveyCount  = new TotalName();
					qtdSurveyCount.setName("QTD Survey Count");
					qtdSurveyCount.setTotal("0");
					
					TotalName avgSurveyCount  = new TotalName();
					avgSurveyCount.setName("Average Quarterly survey score");
					avgSurveyCount.setTotal("0");
					
					TotalName rank  = new TotalName();
					rank.setName("Rank");
					rank.setTotal("0");
					
					if(incentiveEligibleList.get(0).getIncentiveEligible()> 0){
						incentiveEligible.setTotal("Y");
					}
					
					qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
					avgSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getAvgSurveyScore()));
					rank.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getPartcipantRank()));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSurveyCount));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgSurveyCount));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(rank));
					
				}
				
			}
			return topTenChart;
		}
		case "15":
		{
			
			//set datatables
			List<TTTATopNDTO> listAdvisorsQTD = this.dashService.getTTTATopN("TOP TT", 10);
			List<TTTATopNDTO> listAdvisorsYTD = this.dashService.getTTTATopN("TOP TT", 10);
			
			TopTenChart topTenChart = new TopTenChart();
			
			if(type.equals("District")){
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
			
			
			TotalName dealerscount = this.dashService.getTTTANATTopEnrolledDealerCountByBCDistrictAndPositionCode(territory,"23");
			dealerscount.setTotal(this.formatNumbers(Integer.parseInt(dealerscount.getTotal())));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
			
			
			
			List<String> filters = new ArrayList<String>(Arrays.asList(territory));
			List<TTTAEnrollmentsSummaryDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsSummaryByChildAndPositionCode(filters, "23");
			if(incentiveEligibleList.size()>0){
				TotalName incentiveEligible  = new TotalName();
				incentiveEligible.setName("Total Technicians Incentive Eligible");
				incentiveEligible.setTotal("0");
			
				TotalName qtdSurveyCount  = new TotalName();
				qtdSurveyCount.setName("QTD Survey Count");
				qtdSurveyCount.setTotal("0");
			
				TotalName avgSurveyCount  = new TotalName();
				avgSurveyCount.setName("Average Quarterly survey score");
				avgSurveyCount.setTotal("0");
			
			
				incentiveEligible.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getIncentiveEligible()));
				qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
				avgSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getAvgSurveyScore()));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgSurveyCount));
			}
			
			
			}
			
			if(type.equals("Manager") || type.equals("Dealer")){
				
				List<String> filters = new ArrayList<String>(Arrays.asList(territory));
				List<TTTAEnrollmentsSummaryDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsSummaryByChildAndPositionCode(filters, "23");
				if(incentiveEligibleList.size()>0){
					TotalName incentiveEligible  = new TotalName();
					incentiveEligible.setName("Total Technicians Incentive Eligible");
					incentiveEligible.setTotal("0");
				
					TotalName qtdSurveyCount  = new TotalName();
					qtdSurveyCount.setName("QTD Survey Count");
					qtdSurveyCount.setTotal("0");
				
					TotalName avgSurveyCount  = new TotalName();
					avgSurveyCount.setName("Average Quarterly survey score");
					avgSurveyCount.setTotal("0");
					
					TotalName rank  = new TotalName();
					rank.setName("Dealership Rank");
					rank.setTotal("0");
					
					TotalName enrolled  = new TotalName();
					rank.setName("Total Participants Enrolled");
					rank.setTotal("0");
				
					incentiveEligible.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getIncentiveEligible()));
					qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
					avgSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getAvgSurveyScore()));
					rank.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getTTTARank()));
					enrolled.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getTotalEnrollments()));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSurveyCount));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgSurveyCount));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(rank));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(enrolled));
				}
				
			}
			else if (type.equals("Participant")){
				 
				List<TTTAEnrollmentsDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsBySID(territory, "23");
				//survey score
				//survey count
				
				if(incentiveEligibleList.size() > 0){
					
					
					TotalName incentiveEligible  = new TotalName();
					incentiveEligible.setName("# of level 3");
					incentiveEligible.setTotal("0");
					
					TotalName qtdSurveyCount  = new TotalName();
					qtdSurveyCount.setName("QTD Survey Count");
					qtdSurveyCount.setTotal("0");
					
					TotalName avgSurveyCount  = new TotalName();
					avgSurveyCount.setName("Average Quarterly survey score");
					avgSurveyCount.setTotal("0");
					
					TotalName rank  = new TotalName();
					rank.setName("Rank");
					rank.setTotal("0");
					
				
					incentiveEligible.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getLevel3Techs()));
					qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
					avgSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getAvgSurveyScore()));
					rank.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getPartcipantRank()));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSurveyCount));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgSurveyCount));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(rank));
					
				}
				
				
			}
			
			
			
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
		case "19":
		{
			if(type.equals("Manager") || type.equals("Dealer")){
				territory = dealerCode;
			}else if(type.equals("Participant")){
				territory = user.getUserId().trim();
			}else if(type.equals("District")){
			}
			List<MSERGraphDTO> listOfFirstLevel = this.dashService.getMSERGraphByChildTerritoryAndToggle(territory, "YTD");
			
			List<ChartData> attributes = new ArrayList<ChartData>();
			
			for(MSERGraphDTO item: listOfFirstLevel){
				ChartData temp = new ChartData(item.getProgram(), item.getAmount());
				temp.addData(new ChartData(territory, item.getAmount()));
				attributes.add(temp);
			}
			//main chart model
			Chart chart = new Chart("Total Earnings YTD", "", "Total Earnings", "", "bar_compound");
			chart.setData(attributes);
			chart.setUnit("$");
			return chart;
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
							chart.setUnit("$");
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
			chart.setAvarage(true);
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
			chart.setUnit("$");
			return chart;
		}
		case "23":
		{
			// check for role, to know what data to display //TODO: update this to sum
			List<String> filters = new ArrayList<String>(Arrays.asList(territory));
			List<RewardRedemptionGraphDTO> listOfFirstLevel = this.dashService.getRewardRedemptionGraphByChildTerritoryList(filters);
			
			List<ChartData> attributes = new ArrayList<ChartData>();
			
			if(listOfFirstLevel.size() >0){
				ChartData temp = new ChartData(territory, listOfFirstLevel.get(0).getEarnedPoints());
				attributes.add(temp);
			}
			//main chart model
			Chart chart = new Chart("Total Program Points Earned YTD", "Points Earned", "Points Earned", "", "column");
			chart.setData(attributes);
			return chart;
		}
		case "24":
		{
			TopTenChart topTenChart = new TopTenChart();
			
			if(type.equals("Participant") || type.equals("Manager") || type.equals("Dealer")){
			TotalName bal = new TotalName();
			TotalName redeemed = new TotalName();
			TotalName earned = new TotalName();
			List<RewardRedemptionDetailsDTO> RewardRedemptionDetails = this.dashService.getRewardRedemptionDetailsBySid(user.getUserId().trim());
			if(RewardRedemptionDetails.size() > 0){
			bal.setName("Balance");
			redeemed.setName("Redeemed Points");
			earned.setName("Earned Points");
			bal.setTotal(RewardRedemptionDetails.get(0).getBalancePoints() + "pt");
			earned.setTotal(RewardRedemptionDetails.get(0).getEarnedPoints() + "pt");
			redeemed.setTotal(RewardRedemptionDetails.get(0).getRedeemedPoints() + "pt");
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(earned));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(bal));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(earned));
			}
			}
			return topTenChart;
		}
		case "25":
		{
			//check if dealer or not if dealer use dealer if not use sid // need a case for manager
			String sid = user.getUserId().trim();
			
			TopTenChart topTenChart = new TopTenChart();
			
			TotalName years = new TotalName();
			TotalName cartificationLevel = new TotalName();
			TotalName totalCertifiedParticipants = new TotalName();
			TotalName totalMasterCertifiedParticipants = new TotalName();
			TotalName totalCertifiedSpecialistParticipants = new TotalName();
			TotalName totalCertifiedLevelParticipants = new TotalName();
			TotalName dealershipRankWithinBC = new TotalName();
			TotalName dealershipNationalRank = new TotalName();
			TotalName dealershipMasterCertifiedRankWithinBC = new TotalName();
			TotalName dealershipMasterCertifiedNationalRank = new TotalName();
			
			List<CertProfsWinnersDetailsDTO> CertProfsWinnersDetailsList = this.dashService.getCertProfsWinnersDetailsBySID(sid);
			
			if(CertProfsWinnersDetailsList.size() > 0){
			CertProfsWinnersDetailsDTO CertProfsWinnersDetails = CertProfsWinnersDetailsList.get(0);
			
			if(type.equals("Participant") || type.equals("Manager")){
				years.setName("Years Certified");
				years.setTotal(CertProfsWinnersDetails.getYearsOfCertified()+ "");
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
			}
			if(CertProfsWinnersDetails.getMasterCertified() > 0){
				cartificationLevel.setName("Certified Master");
				cartificationLevel.setTotal(CertProfsWinnersDetails.getMasterCertified() + "");
				TileAttribute1 tile = this.mappingService.MapTotalNameToTileAttribute(cartificationLevel);
				tile.setBadgeTitle(tile.getName());
				tile.setBadgeUrl("mastercertified.jpg");
				topTenChart.addAttribute(tile);
			}else if(CertProfsWinnersDetails.getCertifiedSpecalist() > 0){
				cartificationLevel.setName("Certified Specialist");
				cartificationLevel.setTotal(CertProfsWinnersDetails.getCertifiedSpecalist() + "");
				TileAttribute1 tile = this.mappingService.MapTotalNameToTileAttribute(cartificationLevel);
				tile.setBadgeTitle(tile.getName());
				tile.setBadgeUrl("certifiedspecialist.jpg");
				topTenChart.addAttribute(tile);
			}else if(CertProfsWinnersDetails.getCertified() > 0){
				cartificationLevel.setName("Certified");
				cartificationLevel.setTotal(CertProfsWinnersDetails.getCertified() + "");
				TileAttribute1 tile = this.mappingService.MapTotalNameToTileAttribute(cartificationLevel);
				tile.setBadgeTitle(tile.getName());
				tile.setBadgeUrl("cert-pro-badges.jpg");
				topTenChart.addAttribute(tile);
			}else{
				cartificationLevel.setName("Not Certified");
				cartificationLevel.setTotal("No Certification");
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevel));
			}
			}
			
			if(type.equals("Dealer") || type.equals("Manager")){
				List<String> list = new ArrayList<String>();
				list.add(dealerCode);
				List<CertProfsWinnersGraphDTO> CertProfsWinnersGraphDTOList = this.dashService.getCertProfsWinnersGraphByChildTerritory(list);
				
				if(CertProfsWinnersGraphDTOList.size() >0){
					CertProfsWinnersGraphDTO CertProfsWinnersGraphDTO = CertProfsWinnersGraphDTOList.get(0);
					totalCertifiedParticipants.setName("Total Certified Participants");
					totalCertifiedParticipants.setTotal(CertProfsWinnersGraphDTO.getTotalCertified() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
					
					totalMasterCertifiedParticipants.setName("Total Master Certified Participants");
					totalMasterCertifiedParticipants.setTotal(CertProfsWinnersGraphDTO.getMasterCertified() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
					
					totalCertifiedSpecialistParticipants.setName("Total Certified Specialist Participants");
					totalCertifiedSpecialistParticipants.setTotal(CertProfsWinnersGraphDTO.getMasterCertified() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
					
					totalCertifiedLevelParticipants.setName("Total Certified Level Participants");
					totalCertifiedLevelParticipants.setTotal(CertProfsWinnersGraphDTO.getCertified() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedLevelParticipants));
					
					if(type.equals("Dealer")){
						
						dealershipMasterCertifiedRankWithinBC.setName("Dealership Master Certified BC Rank");
						dealershipMasterCertifiedRankWithinBC.setTotal(CertProfsWinnersGraphDTO.getMasterCertBCCertRank() + "");
						topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedRankWithinBC));
						
						dealershipMasterCertifiedNationalRank.setName("Dealership Master Certified National Rank");
						dealershipMasterCertifiedNationalRank.setTotal(CertProfsWinnersGraphDTO.getMasterCertBCCertRank() + "");
						topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedNationalRank));
						
						dealershipRankWithinBC.setName("Dealership BC Rank");
						dealershipRankWithinBC.setTotal(CertProfsWinnersGraphDTO.getAllBCCertRank() + "");
						topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipRankWithinBC));
						
						dealershipNationalRank.setName("Dealership National Rank");
						dealershipNationalRank.setTotal(CertProfsWinnersGraphDTO.getAllNATCertRank() + "");
						topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipNationalRank));
						
					}
					
				}
				
			}
			
			
			return topTenChart;
		}
		case "26":
		{
			//check if dealer or not if dealer use dealer if not use sid // need a case for manager
			String sid = user.getUserId();
			TopTenChart topTenChart = new TopTenChart();
			
			TotalName years = new TotalName();
			TotalName cartificationLevel = new TotalName();
			TotalName totalCertifiedParticipants = new TotalName();
			TotalName totalMasterCertifiedParticipants = new TotalName();
			TotalName totalCertifiedSpecialistParticipants = new TotalName();
			TotalName totalCertifiedLevelParticipants = new TotalName();
			TotalName dealershipMasterCertifiedRankWithinBC = new TotalName();
			
			List<BrainBoostWinnersDetailsDTO> BrainBoostWinnersDetailsList = this.dashService.getBrainBoostWinnersDetailsDTOBySID(sid, "YTD");
			
			if(type.equals("Dealer") || type.equals("Manager")){
				List<String> list = new ArrayList<String>();
				list.add(dealerCode);
				List<BrainBoostWinndersGraphDTO> BrainBoostWinndersGraphList = this.dashService.getBrainBoostWinndersGraphgetByChildTerritory(list);
				List<BrainBoostWinnersDetailsDTO> BrainBoostWinnersDetailsDTOListMTD = this.dashService.getBrainBoostWinnersDetailsDTOSUMByDealerCode(dealerCode, "MTD");
				
				if(BrainBoostWinndersGraphList.size() >0){
					
					BrainBoostWinndersGraphDTO BrainBoostWinndersGraphDTO = BrainBoostWinndersGraphList.get(0);
					
					if(BrainBoostWinnersDetailsDTOListMTD.size()>0){
					
					BrainBoostWinnersDetailsDTO BrainBoostWinnersDetailsDTO = BrainBoostWinnersDetailsDTOListMTD.get(0);
					totalCertifiedParticipants.setName("Total Participants MTD");
					totalCertifiedParticipants.setTotal(BrainBoostWinnersDetailsDTO.getPartcipants() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
					
					totalMasterCertifiedParticipants.setName("Total Winners MTD");
					totalMasterCertifiedParticipants.setTotal(BrainBoostWinnersDetailsDTO.getWinners() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
					
					}
					totalCertifiedSpecialistParticipants.setName("Total Winners YTD");
					totalCertifiedSpecialistParticipants.setTotal(BrainBoostWinndersGraphDTO.getWinners() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
					
					totalCertifiedLevelParticipants.setName("Total Excellence Card Dealership Awards YTD");
					totalCertifiedLevelParticipants.setTotal(BrainBoostWinndersGraphDTO.getEarnings() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedLevelParticipants));
					
					dealershipMasterCertifiedRankWithinBC.setName("Total Award Points Dealership Earned YTD ");
					dealershipMasterCertifiedRankWithinBC.setTotal(BrainBoostWinndersGraphDTO.getPoints() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedRankWithinBC));
					
				}
				
			}
			
			if(BrainBoostWinnersDetailsList.size() > 0){
				BrainBoostWinnersDetailsDTO BrainBoostWinnersDetails = BrainBoostWinnersDetailsList.get(0);
			
			if(type.equals("Participant")){
				years.setName("Total Excellence Card Awards YTD");
				years.setTotal(BrainBoostWinnersDetails.getEarnings()+ "");
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
				
				cartificationLevel.setName("Total Excellence Card Awards YTD");
				cartificationLevel.setTotal(BrainBoostWinnersDetails.getEarnings()+ "");
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
				}
			}
			
			return topTenChart;
		}
		case "27":
		{
			//check if dealer or not if dealer use dealer if not use sid // need a case for manager
			String sid = user.getUserId();
			TopTenChart topTenChart = new TopTenChart();
			
			TotalName years = new TotalName();
			TotalName cartificationLevel = new TotalName();
			TotalName totalCertifiedParticipants = new TotalName();
			TotalName totalMasterCertifiedParticipants = new TotalName();
			TotalName totalCertifiedSpecialistParticipants = new TotalName();
			TotalName totalCertifiedLevelParticipants = new TotalName();
			TotalName dealershipMasterCertifiedRankWithinBC = new TotalName();
			
			List<BrainBoostWinnersDetailsDTO> BrainBoostWinnersDetailsList = this.dashService.getBrainBoostWinnersDetailsDTOBySID(sid, "YTD");
			
			if(type.equals("Dealer") || type.equals("Manager")){
				List<String> list = new ArrayList<String>();
				list.add(dealerCode);
				List<BrainBoostWinndersGraphDTO> BrainBoostWinndersGraphList = this.dashService.getBrainBoostWinndersGraphgetByChildTerritory(list);
				List<BrainBoostWinnersDetailsDTO> BrainBoostWinnersDetailsDTOListMTD = this.dashService.getBrainBoostWinnersDetailsDTOSUMByDealerCode(dealerCode, "MTD");
				
				if(BrainBoostWinndersGraphList.size() >0){
					
					BrainBoostWinndersGraphDTO BrainBoostWinndersGraphDTO = BrainBoostWinndersGraphList.get(0);
					
					if(BrainBoostWinnersDetailsDTOListMTD.size()>0){
					
					BrainBoostWinnersDetailsDTO BrainBoostWinnersDetailsDTO = BrainBoostWinnersDetailsDTOListMTD.get(0);
					totalCertifiedParticipants.setName("Total Participants MTD");
					totalCertifiedParticipants.setTotal(BrainBoostWinnersDetailsDTO.getPartcipants() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
					
					totalMasterCertifiedParticipants.setName("Total Winners MTD");
					totalMasterCertifiedParticipants.setTotal(BrainBoostWinnersDetailsDTO.getWinners() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
					
					}
					totalCertifiedSpecialistParticipants.setName("Total Winners YTD");
					totalCertifiedSpecialistParticipants.setTotal(BrainBoostWinndersGraphDTO.getWinners() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
					
					totalCertifiedLevelParticipants.setName("Total Excellence Card Dealership Awards YTD");
					totalCertifiedLevelParticipants.setTotal(BrainBoostWinndersGraphDTO.getEarnings() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedLevelParticipants));
					
					dealershipMasterCertifiedRankWithinBC.setName("Total Award Points Dealership Earned YTD ");
					dealershipMasterCertifiedRankWithinBC.setTotal(BrainBoostWinndersGraphDTO.getPoints() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedRankWithinBC));
					
				}
				
			}
			
			if(BrainBoostWinnersDetailsList.size() > 0){
				BrainBoostWinnersDetailsDTO BrainBoostWinnersDetails = BrainBoostWinnersDetailsList.get(0);
			
			if(type.equals("Participant")){
				years.setName("Total Excellence Card Awards YTD");
				years.setTotal(BrainBoostWinnersDetails.getEarnings()+ "");
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
				
				cartificationLevel.setName("Total Excellence Card Awards YTD");
				cartificationLevel.setTotal(BrainBoostWinnersDetails.getEarnings()+ "");
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
				}
			}
			
			return topTenChart;
		}
		case "28":
		{
			//check if dealer or not if dealer use dealer if not use sid // need a case for manager
			String sid = user.getUserId();
			TopTenChart topTenChart = new TopTenChart();
			
			TotalName years = new TotalName();
			TotalName cartificationLevel = new TotalName();
			TotalName totalCertifiedParticipants = new TotalName();
			TotalName totalMasterCertifiedParticipants = new TotalName();
			TotalName totalCertifiedSpecialistParticipants = new TotalName();
			TotalName totalCertifiedLevelParticipants = new TotalName();
			TotalName dealershipMasterCertifiedRankWithinBC = new TotalName();
			
			List<CertProfsExpertDetailsDTO> CertProfsExpertDetailsList = this.dashService.getCertProfsExpertDetailsSUMBySID(sid);
			
			if(type.equals("Dealer") || type.equals("Manager")){
				List<String> list = new ArrayList<String>();
				list.add(dealerCode);
				List<CertProfsExpertGraphDTO> CertProfsExpertGraphList = this.dashService.getExpertPointsEarnedByChildTerritory(list);
				List<CertProfsExpertGraphDTO> CertProfsExpertGraphJEEPList = this.dashService.getParticipantCompletedByProgramByChildTerritoryAndCertType(list, "JEEP%");
				List<CertProfsExpertGraphDTO> CertProfsExpertGraphRAMList = this.dashService.getParticipantCompletedByProgramByChildTerritoryAndCertType(list, "RAM%");
				
				if(CertProfsExpertGraphJEEPList.size() >0){
					CertProfsExpertGraphDTO CertProfsExpertGraphDTO = CertProfsExpertGraphJEEPList.get(0);
					totalCertifiedParticipants.setName("Total Participants Completed - JEEP");
					totalCertifiedParticipants.setTotal(CertProfsExpertGraphDTO.getCert() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
				}
				if(CertProfsExpertGraphRAMList.size() >0){
					CertProfsExpertGraphDTO CertProfsExpertGraphDTO = CertProfsExpertGraphRAMList.get(0);
					totalMasterCertifiedParticipants.setName("Total Participants Completed - RAM");
					totalMasterCertifiedParticipants.setTotal(CertProfsExpertGraphDTO.getCert() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
				}
				if(type.equals("Dealer")){
				if(CertProfsExpertGraphList.size() >0){	
					CertProfsExpertGraphDTO CertProfsExpertGraphDTO = CertProfsExpertGraphList.get(0);
					totalCertifiedSpecialistParticipants.setName("Total Experts points earned YTD");
					totalCertifiedSpecialistParticipants.setTotal(CertProfsExpertGraphDTO.getPoints() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
				}
				}else if(type.equals("Manager")){
					if(CertProfsExpertDetailsList.size()>0){
						CertProfsExpertDetailsDTO CertProfsExpertDetailsDTO = CertProfsExpertDetailsList.get(0);
						totalCertifiedSpecialistParticipants.setName("Total Experts points earned YTD");
						totalCertifiedSpecialistParticipants.setTotal(CertProfsExpertDetailsDTO.getPoints() + "");
						topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
					}
				}
				
				if(CertProfsExpertGraphRAMList.size() > 0){
					CertProfsExpertGraphDTO CertProfsExpertGraphDTO = CertProfsExpertGraphRAMList.get(0);
					totalCertifiedLevelParticipants.setName("Dealership rank in Business Center");
					totalCertifiedLevelParticipants.setTotal(CertProfsExpertGraphDTO.getBCPointRank() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedLevelParticipants));
				}else if (CertProfsExpertGraphJEEPList.size() > 0){
					CertProfsExpertGraphDTO CertProfsExpertGraphDTO = CertProfsExpertGraphJEEPList.get(0);
					totalCertifiedLevelParticipants.setName("Dealership rank in Business Center");
					totalCertifiedLevelParticipants.setTotal(CertProfsExpertGraphDTO.getBCPointRank() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedLevelParticipants));
				}
				
				
			}
			
		if(type.equals("Participant")){
			if(CertProfsExpertDetailsList.size()>0){
				CertProfsExpertDetailsDTO CertProfsExpertDetailsDTO = CertProfsExpertDetailsList.get(0);
				totalCertifiedSpecialistParticipants.setName("Total Experts points earned YTD");
				totalCertifiedSpecialistParticipants.setTotal(CertProfsExpertDetailsDTO.getPoints() + "");
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
			}
			
			List<CertProfsExpertDetailsDTO> CertProfsExpertDetailsRAMList = this.dashService.getCertProfsExpertDetailsBySIDANDCertType(sid, "RAM%");
			List<CertProfsExpertDetailsDTO> CertProfsExpertDetailsJEEPList = this.dashService.getCertProfsExpertDetailsBySIDANDCertType(sid, "JEEP%");
			
			cartificationLevel.setName("JEEP EXPERT");
			cartificationLevel.setTotal("N");
			dealershipMasterCertifiedRankWithinBC.setName("RAM EXPERT");
			dealershipMasterCertifiedRankWithinBC.setTotal("N");
			
			//set badge
			if(CertProfsExpertDetailsRAMList.size()>0){
				CertProfsExpertDetailsDTO CertProfsExpertDetailsDTO = CertProfsExpertDetailsRAMList.get(0);
				if(CertProfsExpertDetailsDTO.getCert() > 0){
				cartificationLevel.setTotal("Y");
				}
			}
			
			if(CertProfsExpertDetailsJEEPList.size()>0){
				CertProfsExpertDetailsDTO CertProfsExpertDetailsDTO = CertProfsExpertDetailsJEEPList.get(0);
				if(CertProfsExpertDetailsDTO.getCert() > 0){
					dealershipMasterCertifiedRankWithinBC.setTotal("Y");
					}
			}
			
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevel));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedRankWithinBC));
			
		}
		
			
			return topTenChart;
		}
		case "29":
		{
			String sid = user.getUserId();
			TopTenChart topTenChart = new TopTenChart();
			
			TotalName years = new TotalName();
			TotalName cartificationLevel = new TotalName();
			TotalName totalCertifiedParticipants = new TotalName();
			TotalName totalMasterCertifiedParticipants = new TotalName();
			TotalName totalCertifiedSpecialistParticipants = new TotalName();
			TotalName totalCertifiedLevelParticipants = new TotalName();
			TotalName dealershipMasterCertifiedRankWithinBC = new TotalName();
			
			List<SIRewardsDetailsDTO> SIRewardsDetailsList = this.dashService.getSIRewardsDetailsBySIDAndToggle(sid, "QTD");
			List<SIRewardsDetailsDTO> SIRewardsDetailsListYTD = this.dashService.getSIRewardsDetailsBySIDAndToggle(sid, "YTD");

			if(type.equals("Participant")){
				if(SIRewardsDetailsList.size()>0){
					SIRewardsDetailsDTO SIRewardsDetailsDTO = SIRewardsDetailsList.get(0);
					
					cartificationLevel.setName("Level 0 training completed");
					cartificationLevel.setTotal("No");
					dealershipMasterCertifiedRankWithinBC.setName("Level 1 training completed");
					dealershipMasterCertifiedRankWithinBC.setTotal("No");
					years.setName("Incentive Eligible");
					cartificationLevel.setTotal("$0");
					
						if(SIRewardsDetailsDTO.getLevel0() > 0){
						cartificationLevel.setTotal("Yes");
						}
						if(SIRewardsDetailsDTO.getLevel1() > 0){
							dealershipMasterCertifiedRankWithinBC.setTotal("Yes");
							}
						if(SIRewardsDetailsDTO.getIncentiveQualified() > 0){
							int total = SIRewardsDetailsDTO.getEligibleSurveys() * 10;
							
							NumberFormat formatter = NumberFormat.getCurrencyInstance();
							String moneyStringMTD = formatter.format(total);
							if (moneyStringMTD.endsWith(".00")) {
							    int centsIndex = moneyStringMTD.lastIndexOf(".00");
							    if (centsIndex != -1) {
							    	moneyStringMTD = moneyStringMTD.substring(1, centsIndex);
							    }
							}
							
							years.setTotal("$" + moneyStringMTD);
							}
						
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevel));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedRankWithinBC));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
					
					totalCertifiedSpecialistParticipants.setName("QTD Survey Score");
					totalCertifiedSpecialistParticipants.setTotal((int)SIRewardsDetailsDTO.getSurveyScore() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
					
					
				}
				
				if(SIRewardsDetailsListYTD.size()>0){
					SIRewardsDetailsDTO SIRewardsDetailsDTO = SIRewardsDetailsListYTD.get(0);
					
					totalCertifiedParticipants.setName("Total Earnings YTD");
					int total = (int)SIRewardsDetailsDTO.getProjectedEarnings();
					NumberFormat formatter = NumberFormat.getCurrencyInstance();
					String moneyStringMTD = formatter.format(total);
					if (moneyStringMTD.endsWith(".00")) {
					    int centsIndex = moneyStringMTD.lastIndexOf(".00");
					    if (centsIndex != -1) {
					    	moneyStringMTD = moneyStringMTD.substring(1, centsIndex);
					    }
					}
					
					totalCertifiedParticipants.setTotal("$" + moneyStringMTD);
					
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
					
					
					totalMasterCertifiedParticipants.setName(sid + " BC Rank");
					totalMasterCertifiedParticipants.setTotal(SIRewardsDetailsDTO.getBCAdvisorRankEarnings() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
					
				}
				
				
				
			}
			
			if(type.equals("Dealer")){
				List<String> filters = new ArrayList<String>();
				filters.add(dealerCode);
				List<SIRewardsDetailsGraphDTO> SIRewardsDetailsGraphList = this.dashService.getSIRewardsDetailsGraphByChildTerritoryAndToggle(filters, "QTD");
				if(SIRewardsDetailsGraphList.size() > 0){
					SIRewardsDetailsGraphDTO SIRewardsDetailsGraphDTO = SIRewardsDetailsGraphList.get(0);
					totalCertifiedSpecialistParticipants.setName("Avg. Quarterly Survey Score");
					totalCertifiedSpecialistParticipants.setTotal((int)SIRewardsDetailsGraphDTO.getAvgSurveyScore() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
					
					
				}
				SIRewardsDetailsGraphList = this.dashService.getSIRewardsDetailsGraphByChildTerritoryAndToggle(filters, "YTD");
				if(SIRewardsDetailsGraphList.size() > 0){
					SIRewardsDetailsGraphDTO SIRewardsDetailsGraphDTO = SIRewardsDetailsGraphList.get(0);
					totalCertifiedParticipants.setName("Total Earnings YTD");
					int total = (int)SIRewardsDetailsGraphDTO.getProjectedEarnings();
					NumberFormat formatter = NumberFormat.getCurrencyInstance();
					String moneyStringMTD = formatter.format(total);
					if (moneyStringMTD.endsWith(".00")) {
					    int centsIndex = moneyStringMTD.lastIndexOf(".00");
					    if (centsIndex != -1) {
					    	moneyStringMTD = moneyStringMTD.substring(1, centsIndex);
					    }
					}
					
					totalCertifiedParticipants.setTotal("$" + moneyStringMTD);
					
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
					
					totalMasterCertifiedParticipants.setName("Dealership BC Rank");
					totalMasterCertifiedParticipants.setTotal(SIRewardsDetailsGraphDTO.getBCDlearRankEarnings() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
					
				}
			}
			
			if(type.equals("Manager")){
				
				List<String> filters = new ArrayList<String>();
				filters.add(dealerCode);
				List<SIRewardsDetailsGraphDTO> SIRewardsDetailsGraphList = this.dashService.getSIRewardsDetailsGraphByChildTerritoryAndToggle(filters, "QTD");
				if(SIRewardsDetailsGraphList.size() > 0){
					SIRewardsDetailsGraphDTO SIRewardsDetailsGraphDTO = SIRewardsDetailsGraphList.get(0);
					totalCertifiedSpecialistParticipants.setName("Avg. Quarterly Survey Score");
					totalCertifiedSpecialistParticipants.setTotal((int)SIRewardsDetailsGraphDTO.getAvgSurveyScore() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
					
					
				}
				
				if(SIRewardsDetailsListYTD.size()>0){
					SIRewardsDetailsDTO SIRewardsDetailsDTO = SIRewardsDetailsListYTD.get(0);
					
					totalCertifiedParticipants.setName("Total Earnings YTD");
					int total = (int)SIRewardsDetailsDTO.getProjectedEarnings();
					NumberFormat formatter = NumberFormat.getCurrencyInstance();
					String moneyStringMTD = formatter.format(total);
					if (moneyStringMTD.endsWith(".00")) {
					    int centsIndex = moneyStringMTD.lastIndexOf(".00");
					    if (centsIndex != -1) {
					    	moneyStringMTD = moneyStringMTD.substring(1, centsIndex);
					    }
					}
					
					totalCertifiedParticipants.setTotal("$" + moneyStringMTD);
					
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
				}
				
				if(SIRewardsDetailsList.size()>0){
					SIRewardsDetailsDTO SIRewardsDetailsDTO = SIRewardsDetailsList.get(0);
					
					totalMasterCertifiedParticipants.setName(sid + " BC Rank");
					totalMasterCertifiedParticipants.setTotal(SIRewardsDetailsDTO.getBCAdvisorRankEarnings() + "");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
					
				}
				
			}
		
			
			return topTenChart;
		}
		case "30":
		{
			TopTenChart topTenChart = new TopTenChart();
			
			TotalName years = new TotalName();
			TotalName cartificationLevel = new TotalName();
			TotalName totalCertifiedParticipants = new TotalName();
			TotalName totalMasterCertifiedParticipants = new TotalName();
			TotalName totalCertifiedSpecialistParticipants = new TotalName();
			TotalName totalCertifiedLevelParticipants = new TotalName();
			TotalName dealershipMasterCertifiedRankWithinBC = new TotalName();
			List<String> list = new ArrayList<String>();
			list.add(territory);
			
			
			List<RetentionGraphDTO> RetentionGraphServiceManagerList = this.dashService.getRetentionGraphByChildTerritoryListAndPositionCode(list,"09");
			List<RetentionGraphDTO> RetentionGraphPartsAdvisorList = this.dashService.getRetentionGraphByChildTerritoryListAndPositionCode(list,"14");
			List<RetentionGraphDTO> RetentionGraphServiceTechnicianList = this.dashService.getRetentionGraphByChildTerritoryListAndPositionCode(list,"23");
			List<RetentionGraphDTO> RetentionGraphServiceAdvisorList = this.dashService.getRetentionGraphByChildTerritoryListAndPositionCode(list,"13");
			List<RetentionGraphDTO> RetentionGraphBLSCList = this.dashService.getRetentionGraphByChildTerritoryListAndPositionCode(list,"42");
			List<RetentionGraphDTO> RetentionGraphSalesManagerList = this.dashService.getRetentionGraphByChildTerritoryListAndPositionCode(list,"04");
			List<RetentionGraphDTO> RetentionGraphPartsManagerList = this.dashService.getRetentionGraphByChildTerritoryListAndPositionCode(list,"08");
			
			
			if(type.equals("District") || type.equals("Dealer")){
				
				DecimalFormat df = new DecimalFormat("#.0");
				
				if(RetentionGraphServiceManagerList.size()>0){
					cartificationLevel.setName("Rolling 12 Month Retention % for Service Managers");
					cartificationLevel.setTotal(df.format(RetentionGraphServiceManagerList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevel));
				}
				
				if(RetentionGraphServiceAdvisorList.size()>0){
					years.setName("Rolling 12 Month Retention % for Service Advisors");
					years.setTotal(df.format(RetentionGraphServiceAdvisorList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
				}
				
				if(RetentionGraphPartsManagerList.size()>0){
					totalCertifiedParticipants.setName("Rolling 12 Month Retention % for Parts Managers");
					totalCertifiedParticipants.setTotal(df.format(RetentionGraphPartsManagerList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
				}
				
				if(RetentionGraphPartsAdvisorList.size()>0){
					totalMasterCertifiedParticipants.setName("Rolling 12 Month Retention % for Parts Advisors");
					totalMasterCertifiedParticipants.setTotal(df.format(RetentionGraphPartsAdvisorList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
				}
				
				if(RetentionGraphServiceTechnicianList.size()>0){
					totalCertifiedSpecialistParticipants.setName("Rolling 12 Month Retention % for Technicians");
					totalCertifiedSpecialistParticipants.setTotal(df.format(RetentionGraphServiceTechnicianList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
				}
				if(RetentionGraphSalesManagerList.size()>0){
					totalCertifiedLevelParticipants.setName("Rolling 12 Month Retention % for for Sales Managers");
					totalCertifiedLevelParticipants.setTotal(df.format(RetentionGraphSalesManagerList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedLevelParticipants));
				}
				if(RetentionGraphBLSCList.size()>0){
					dealershipMasterCertifiedRankWithinBC.setName("Rolling 12 Month Retention % for for Sales Consultants");
					dealershipMasterCertifiedRankWithinBC.setTotal(df.format(RetentionGraphBLSCList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedRankWithinBC));
				}
			}else if(type.equals("Manager")){
				DecimalFormat df = new DecimalFormat("#.0");
				
				if(RetentionGraphServiceAdvisorList.size()>0){
					years.setName("Rolling 12 Month Retention % for Service Advisors");
					years.setTotal(df.format(RetentionGraphServiceAdvisorList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
				}
				
				if(RetentionGraphPartsAdvisorList.size()>0){
					totalMasterCertifiedParticipants.setName("Rolling 12 Month Retention % for Parts Advisors");
					totalMasterCertifiedParticipants.setTotal(df.format(RetentionGraphPartsAdvisorList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
				}
				
				if(RetentionGraphServiceTechnicianList.size()>0){
					totalCertifiedSpecialistParticipants.setName("Rolling 12 Month Retention % for Technicians");
					totalCertifiedSpecialistParticipants.setTotal(df.format(RetentionGraphServiceTechnicianList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
				}

				if(RetentionGraphBLSCList.size()>0){
					dealershipMasterCertifiedRankWithinBC.setName("Rolling 12 Month Retention % for for Sales Consultants");
					dealershipMasterCertifiedRankWithinBC.setTotal(df.format(RetentionGraphBLSCList.get(0).getPercentage()) + "%");
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedRankWithinBC));
				}
			}
			return topTenChart;
		}
		case "31":
		{
			
			//main chart model
			Chart chart = new Chart("Retention % By Employee Type", "", "Retention Rate", "", "column_stack");
			
			//Create first level of the chart
			//Get Distinct program names
			List<String> programs = new ArrayList<String>(Arrays.asList("09","14","23","13","42","04","08"));

			List<ChartData> attributes = new ArrayList<ChartData>();
			
			for(String programName: programs){
				attributes.add(new ChartData(programName, 0));
			}
			
			List<String> filters = new ArrayList<String>(Arrays.asList(territory));
			for(ChartData item: attributes){
					List<RetentionGraphDTO> innerList = this.dashService.getRetentionGraphByChildTerritoryListAndPositionCode(filters, item.getName());
					ChartData chartD = new ChartData(territory, 0);
					if(innerList.size() > 0){
						chartD.setName(territory);
						chartD.setValue(innerList.get(0).getPercentage());
						item.setValue(innerList.get(0).getPercentage());
					}
					item.addData(chartD);
			}
			chart.setData(attributes);
			chart.setUnit("%");
			chart.setRetention(true);
			return chart;
		}
		case "32":
		{
			List<String> filters = new ArrayList<String>();
			filters.add(territory);
			// check for role, to know what data to display
			List<CustomerFirstGraphDTO> CustomerFirstGraphDTO = this.dashService.getCustomerFirstGraphByChildTerritoryAndToggle(filters, "Total");
			Chart chart = new Chart();
			chart.setTitle("Customer First Pillars Completed QTD");
			chart.setSubTitle("");
			chart.setType("pie");
			chart.setXaxisTitle("Pillars QTD");
			chart.setYaxisTitle("");
			chart.setCustomer_first(true);
			
			List<ChartData> list = new ArrayList<ChartData>();
			list.add(new ChartData("Level 0", 0));
			list.add(new ChartData("Performance", 0));
			list.add(new ChartData("Process", 0));
			list.add(new ChartData("Voice of Employee", 0));
			list.add(new ChartData("Training", 0));
			list.add(new ChartData("Facility", 0));
			
			if(CustomerFirstGraphDTO.size() > 0){
				ChartData data = new ChartData();
				data.setName(CustomerFirstGraphDTO.get(0).getChildTerritory());
				data.setValue(CustomerFirstGraphDTO.get(0).getNoCertification());
				list.get(0).setValue(data.getValue());
				list.get(0).addData(data);
				
				data = new ChartData();
				data.setName(CustomerFirstGraphDTO.get(0).getChildTerritory());
				data.setValue(CustomerFirstGraphDTO.get(0).getPerformance());
				list.get(1).setValue(data.getValue());
				list.get(1).addData(data);
				
				data = new ChartData();
				data.setName(CustomerFirstGraphDTO.get(0).getChildTerritory());
				data.setValue(CustomerFirstGraphDTO.get(0).getProcess());
				list.get(2).setValue(data.getValue());
				list.get(2).addData(data);
				
				data = new ChartData();
				data.setName(CustomerFirstGraphDTO.get(0).getChildTerritory());
				data.setValue(CustomerFirstGraphDTO.get(0).getVoiceofEmployee());
				list.get(3).setValue(data.getValue());
				list.get(3).addData(data);
				
				data = new ChartData();
				data.setName(CustomerFirstGraphDTO.get(0).getChildTerritory());
				data.setValue(CustomerFirstGraphDTO.get(0).getTraining());
				list.get(4).setValue(data.getValue());
				list.get(4).addData(data);
				
				data = new ChartData();
				data.setName(CustomerFirstGraphDTO.get(0).getChildTerritory());
				data.setValue(CustomerFirstGraphDTO.get(0).getFacility());
				list.get(5).setValue(data.getValue());
				list.get(5).addData(data);
			}
		
			chart.setData(list);
			
			return chart;
		}
		case "33":
		{
			List<String> filters = new ArrayList<String>();
			filters.add(territory);
			// check for role, to know what data to display
			List<CustomerFirstGraphDTO> CustomerFirstGraphDTO = this.dashService.getCustomerFirstGraphByChildTerritoryAndToggle(filters, "Percentage");
			Chart chart = new Chart();
			chart.setTitle("Customer First Pillars  Completion");
			chart.setSubTitle("");
			chart.setType("column_stack");
			chart.setXaxisTitle("Completion Percentage");
			chart.setYaxisTitle("");
			
			List<ChartData> list = new ArrayList<ChartData>();
			list.add(new ChartData("Level 0", 0));
			list.add(new ChartData("Performance", 0));
			list.add(new ChartData("Process", 0));
			list.add(new ChartData("Voice of Employee", 0));
			list.add(new ChartData("Training", 0));
			list.add(new ChartData("Facility", 0));
			list.add(new ChartData("CFAFE Award Certification", 0));
			
			if(CustomerFirstGraphDTO.size() > 0){
				ChartData data = new ChartData();
				data.setName(CustomerFirstGraphDTO.get(0).getChildTerritory());
				data.setValue(CustomerFirstGraphDTO.get(0).getNoCertification());
				list.get(0).setValue(data.getValue());
				list.get(0).addData(data);
				
				data = new ChartData();
				data.setName(CustomerFirstGraphDTO.get(0).getChildTerritory());
				data.setValue(CustomerFirstGraphDTO.get(0).getPerformance());
				list.get(1).setValue(data.getValue());
				list.get(1).addData(data);
				
				data = new ChartData();
				data.setName(CustomerFirstGraphDTO.get(0).getChildTerritory());
				data.setValue(CustomerFirstGraphDTO.get(0).getProcess());
				list.get(2).setValue(data.getValue());
				list.get(2).addData(data);
				
				data = new ChartData();
				data.setName(CustomerFirstGraphDTO.get(0).getChildTerritory());
				data.setValue(CustomerFirstGraphDTO.get(0).getVoiceofEmployee());
				list.get(3).setValue(data.getValue());
				list.get(3).addData(data);
				
				data = new ChartData();
				data.setName(CustomerFirstGraphDTO.get(0).getChildTerritory());
				data.setValue(CustomerFirstGraphDTO.get(0).getTraining());
				list.get(4).setValue(data.getValue());
				list.get(4).addData(data);
				
				data = new ChartData();
				data.setName(CustomerFirstGraphDTO.get(0).getChildTerritory());
				data.setValue(CustomerFirstGraphDTO.get(0).getFacility());
				list.get(5).setValue(data.getValue());
				list.get(5).addData(data);
				
				data = new ChartData();
				data.setName(CustomerFirstGraphDTO.get(0).getChildTerritory());
				data.setValue(CustomerFirstGraphDTO.get(0).getCFAFEAwardCertification());
				list.get(6).setValue(data.getValue());
				list.get(6).addData(data);
				
			}
		
			chart.setData(list);
			
			return chart;
		}
		case "34":
		{
			List<String> filters = new ArrayList<String>(Arrays.asList("NAT"));
			List<TTTAEnrolledGraphDTO> listBC = this.dashService.getTTTAEnrolledByParentTerritory(filters);
			filters = new ArrayList<String>();
			
			List<TTTAEnrolledGraphDTO> sublist = this.dashService.getTTTAEnrolledByParentTerritory(filters);
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap2 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap3 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap4 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap5 = new HashMap<String, List<ChartData>>();
			
			Chart chart = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Dealers Enrolled YTD", "", "", "Total", "column_stack");
			Chart chart2 = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Dealers Enrolled YTD", "", "", "Total", "column_stack");
			Chart chart3 = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Dealers Enrolled YTD", "", "", "Total", "column_stack");
			Chart chart4 = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Dealers Enrolled YTD", "", "", "Total", "column_stack");
			Chart chart5 = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Dealers Enrolled YTD", "", "", "Total", "column_stack");

			Map<String, Double> mapValues = new HashMap<String, Double>();
			Map<String, Double> mapValues2 = new HashMap<String, Double>();
			Map<String, Double> mapValues3 = new HashMap<String, Double>();
			Map<String, Double> mapValues4 = new HashMap<String, Double>();
			Map<String, Double> mapValues5 = new HashMap<String, Double>();
			for(TTTAEnrolledGraphDTO item: listBC){
				List<ChartData> list = new ArrayList<ChartData>();
				List<ChartData> list2 = new ArrayList<ChartData>();
				List<ChartData> list3 = new ArrayList<ChartData>();
				List<ChartData> list4 = new ArrayList<ChartData>();
				List<ChartData> list5 = new ArrayList<ChartData>();
				for(TTTAEnrolledGraphDTO object: sublist){
					if(item.getChild().equals(object.getParent())){
						ChartData temp = new ChartData();
						temp.setName(object.getChild());
						temp.setValue(object.getTotalGroupA());
						list.add(temp);
						temp = new ChartData();
						temp.setName(object.getChild());
						temp.setValue(object.getTotalGroupB());
						list2.add(temp);
						
						
						temp = new ChartData();
						temp.setName(object.getChild());
						temp.setValue(object.getTotalGroupC());
						list3.add(temp);
						
						temp = new ChartData();
						temp.setName(object.getChild());
						temp.setValue(object.getTotalGroupD());
						list4.add(temp);
						
						temp = new ChartData();
						temp.setName(object.getChild());
						temp.setValue(object.getTotalGroupE());
						list5.add(temp);
						
						
					}
					chartsMap.put(item.getChild(), list);
					chartsMap2.put(item.getChild(), list2);
					chartsMap3.put(item.getChild(), list3);
					chartsMap4.put(item.getChild(), list4);
					chartsMap5.put(item.getChild(), list5);
					mapValues.put(item.getChild(), (double)item.getTotalGroupA());
					mapValues2.put(item.getChild(), (double)item.getTotalGroupB());
					mapValues3.put(item.getChild(), (double)item.getTotalGroupC());
					mapValues4.put(item.getChild(), (double)item.getTotalGroupD());
					mapValues5.put(item.getChild(), (double)item.getTotalGroupE());
				}
			}
			
			
			List<ChartData> list1 = new ArrayList<ChartData>();
			list1.add(new ChartData("Total Group A", 0));
			list1.add(new ChartData("Total Group B", 0));
			list1.add(new ChartData("Total Group C", 0)); 
			list1.add(new ChartData("Total Group D", 0)); 
			list1.add(new ChartData("Total Group E", 0));
			
			List<ChartData> a = new ArrayList<ChartData>(chart.getData());
			List<ChartData> b = new ArrayList<ChartData>(chart2.getData());
			List<ChartData> c = new ArrayList<ChartData>(chart3.getData());
			List<ChartData> d = new ArrayList<ChartData>(chart4.getData());
			List<ChartData> e = new ArrayList<ChartData>(chart5.getData());
			
			double tempa =0;
			double tempb =0;
			double tempc =0;
			double tempd =0;
			double tempe =0;
			
			for(ChartData item: a){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Total Group A"))
						tempa += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues.get(item.getName()));
				item.addDataList(chartsMap.get(item.getName()));
			}

			for(ChartData item: b){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Total Group B"))
						tempb += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues2.get(item.getName()));
				item.addDataList(chartsMap2.get(item.getName()));
			}

				
			for(ChartData item: c){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Total Group C"))
						tempc += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues3.get(item.getName()));
				item.addDataList( chartsMap3.get(item.getName()));
			}

			for(ChartData item: d){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Total Group D"))
						tempd += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues4.get(item.getName()));
				item.addDataList( chartsMap4.get(item.getName()));
			}

			for(ChartData item: e){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Total Group E"))
						tempe += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues5.get(item.getName()));
				item.addDataList( chartsMap5.get(item.getName()));
			}

			
			list1.get(0).setValue(tempa);
			list1.get(1).setValue(tempb);
			list1.get(2).setValue(tempc);
			list1.get(3).setValue(tempd);
			list1.get(4).setValue(tempe);
			list1.get(0).setData(a);
			list1.get(1).setData(b);
			list1.get(2).setData(c);
			list1.get(3).setData(d);
			list1.get(4).setData(e);
			
			chart.setData(list1);
			return chart;
			
			//special mapping for stacked column
		}
		default:
			return "No such service call exists.";
		}
	}
	
	
	public String formatCurrency(int number){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(number);
		if (moneyString.endsWith(".00")) {
		    int centsIndex = moneyString.lastIndexOf(".00");
		    if (centsIndex != -1) {
		    	moneyString = moneyString.substring(1, centsIndex);
		    }
		}
		
		return moneyString;
	}
	
	public String formatCurrency(double number){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(number);
		if (moneyString.endsWith(".00")) {
		    int centsIndex = moneyString.lastIndexOf(".00");
		    if (centsIndex != -1) {
		    	moneyString = moneyString.substring(1, centsIndex);
		    }
		}
		
		return moneyString;
	}
	
	public String formatNumbers(double number){
		DecimalFormat formatter = new DecimalFormat("#,###");

		return formatter.format(number);
	}
	
	public String formatNumbers(int number){
		DecimalFormat formatter = new DecimalFormat("#,###");
		
		return formatter.format(number);
	}
	
	
}