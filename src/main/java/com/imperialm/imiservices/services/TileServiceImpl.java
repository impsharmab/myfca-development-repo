package com.imperialm.imiservices.services;

import com.imperialm.imiservices.dao.TIDUsersDAO;
import com.imperialm.imiservices.dto.*;
import com.imperialm.imiservices.model.*;
import com.imperialm.imiservices.model.response.TotalName;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

@Service
public class TileServiceImpl{
	
	@Autowired
	private DashboardServiceImpl dashService;

	@Autowired
	private MappingServiceImpl mappingService;
	
	@Autowired
	private TIDUsersDAO TIDUsersDAO;
	
	private final int MSER_PROGRAM_ID = 1;
	private final int TT_PROGRAM_ID = 4;
	private final int TA_PROGRAM_ID = 5;
	
	@Cacheable("findTilesListByRole")
	public @ResponseBody Object findTilesListByRole(String id, String positionCode, String dealerCode, String user) {

		int testa = dashService.getRoleByPositionCode(positionCode);
		String type = "";
		String BC = "";
		if( testa == 1 || testa == 3 || testa == 13){
			type = "Executive";
		}else if( testa == 12){
			type = "BC";
			List<String> bcSet = this.dashService.getUserTerritoyById(user);
			if(bcSet.size() > 0){
				BC = bcSet.get(0);
			}else{
				List<TIDUsersDTO> tids = TIDUsersDAO.getTIDUsersByTID(user);
				if(tids.size() > 0){
					for(TIDUsersDTO item: tids){
						BC = item.getPositionCode();
					}
				}
			}
		}else if( testa == 11){
			type = "District";
			return this.findTilesManager(id, positionCode, dealerCode, user, type);
		}else if( testa == 10){
			type = "Dealer";
			return this.findTilesManager(id, positionCode, dealerCode, user, type);
		}else if( testa == 9 || testa == 6){
			type = "Participant";
			return this.findTilesManager(id, positionCode, dealerCode, user, type);
		}else if( testa == 5){
			type = "Manager";
			return this.findTilesManager(id, positionCode, dealerCode, user, type);
		}
		//divide the switch statement to functions
		switch(id){
		case "2":
		{
			//set datatables
			String typeSA = "NATSA10";
			String typeST = "NATST10";
			if(type.equalsIgnoreCase("bc")){
				typeSA = BC + "SA10";
				typeST = BC + "ST10";
			}
			List<MyfcaMSERTopNDTO> listAdvisors = this.dashService.getMSERTopTen(typeSA, 10, null, "MTD");
			List<MyfcaMSERTopNDTO> listAdvisorsYTD = this.dashService.getMSERTopTen(typeSA, 10, null, "YTD");
			List<MyfcaMSERTopNDTO> listTechnicians = this.dashService.getMSERTopTen(typeST, 10, null, "MTD");
			List<MyfcaMSERTopNDTO> listTechniciansYTD = this.dashService.getMSERTopTen(typeST, 10, null, "YTD");

			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatableT = new TopTenDataTable("TOP 10 Technicians By Rewarding Excellence&reg; Card Awards"); //, "Rewarding Excellence&reg; Card Awards - Top 5 Technicians"
			TopTenDataTable datatableA = new TopTenDataTable("TOP 10 Advisors By Rewarding Excellence&reg; Card Awards");
			List<TopTenTableData> tabledataA = new ArrayList<TopTenTableData>();
			List<TopTenTableData> tabledataT = new ArrayList<TopTenTableData>();

			List<String> tableheaders = new ArrayList<String>();
			//keeps this as example
			tableheaders.add("Name");
			tableheaders.add("Dealership");
			tableheaders.add("Business Center");
			tableheaders.add("Total Awards");

			tabledataT.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listTechnicians, "Top 10 Technicians By Rewarding Excellence&reg; Card Awards MTD", tableheaders, "top10"));
			tabledataT.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listTechniciansYTD, "Top 10 Technicians By Rewarding Excellence&reg; Card Awards YTD", tableheaders, "top10"));
			tabledataT.get(0).setTabName("MTD");
			tabledataT.get(1).setTabName("YTD");
			tabledataA.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listAdvisors, "Top 10 Advisors By Rewarding Excellence&reg; Card Awards MTD", tableheaders, "top10"));
			tabledataA.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listAdvisorsYTD, "Top 10 Advisors By Rewarding Excellence&reg; Card Awards YTD", tableheaders, "top10"));
			tabledataA.get(0).setTabName("MTD");
			tabledataA.get(1).setTabName("YTD");

			datatableT.setData(tabledataT);
			datatableA.setData(tabledataA);

			topTenChart.setTop10_advisors(datatableA);
			topTenChart.setTop10_technicians(datatableT);

			TotalName cartificationLevel = new TotalName("Rewarding Excellence&reg; Card Awards YTD","$0");
			TotalName totalCertifiedParticipants = new TotalName("Rewarding Excellence&reg; Card Awards MTD","$0");

			if(type.equals("Executive")){
				List<MyfcaMSERTotalEarningsDTO> MSERGraphDTOListYTD = this.dashService.getMSERGraphProgramsSUMByParentTerritoryAndToggle("NAT", "YTD");
				List<MyfcaMSERTotalEarningsDTO> MSERGraphDTOListMTD = this.dashService.getMSERGraphProgramsSUMByParentTerritoryAndToggle("NAT", "MTD");

				if(MSERGraphDTOListYTD.size()>0){
					MyfcaMSERTotalEarningsDTO MyfcaMSERTotalEarningsDTO = MSERGraphDTOListYTD.get(0);
					double total = MyfcaMSERTotalEarningsDTO.getAmount();
					cartificationLevel.setTotal("$" + this.formatCurrency(total));
				}
				
				if(MSERGraphDTOListMTD.size()>0){
					MyfcaMSERTotalEarningsDTO MyfcaMSERTotalEarningsDTO = MSERGraphDTOListMTD.get(0);
					double total = MyfcaMSERTotalEarningsDTO.getAmount();
					totalCertifiedParticipants.setTotal("$" + this.formatCurrency(total));
				}

				TotalName dealerscount = new TotalName("Total Dealers Enrolled","0");
				
				List<Integer> dealerscountlist = this.dashService.getTotalDealersEnrolledByProgramID(this.MSER_PROGRAM_ID);
				
				if(dealerscountlist.size()>0){
					dealerscount.setTotal(this.formatCurrency(dealerscountlist.get(0)));
				}
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevel));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));

			}

			if(type.equals("BC") && BC.length() ==2){
				List<MyfcaMSERTotalEarningsDTO> MSERGraphDTOListYTD = this.dashService.getMSERGraphProgramsSUMByChildTerritoryAndToggle(BC, "YTD");
				List<MyfcaMSERTotalEarningsDTO> MSERGraphDTOListMTD = this.dashService.getMSERGraphProgramsSUMByChildTerritoryAndToggle(BC, "MTD");

				if(MSERGraphDTOListYTD.size()>0){
					MyfcaMSERTotalEarningsDTO MyfcaMSERTotalEarningsDTO = MSERGraphDTOListYTD.get(0);
					double total = MyfcaMSERTotalEarningsDTO.getAmount();
					cartificationLevel.setTotal("$" + this.formatCurrency(total));
					
				}
				if(MSERGraphDTOListMTD.size()>0){
					MyfcaMSERTotalEarningsDTO MyfcaMSERTotalEarningsDTO = MSERGraphDTOListMTD.get(0);
					double total = MyfcaMSERTotalEarningsDTO.getAmount();
					totalCertifiedParticipants.setTotal("$" + this.formatCurrency(total));
				}
				TotalName dealerscount = new TotalName("Total Dealers Enrolled","0");
				
				List<Integer> dealerscountlist = this.dashService.getTotalDealersEnrolledByProgramIDAndTerritory(MSER_PROGRAM_ID, BC+"%");
				
				if(dealerscountlist.size()>0){
					dealerscount.setTotal(this.formatCurrency(dealerscountlist.get(0)));
				}
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevel));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
			}
			return topTenChart;
		}
		case "3":
		{
			List<MyfcaMSERTopNDTO> listMOPARMTD = this.dashService.getMSERTopTen("Top3Parts", 3,"Mopar Parts" , "MTD");
			//List<MSERTopNDTO> listMOPARYTD = this.dashService.getMSERTopTen("Top3Parts", 3,"Mopar Parts" , "YTD");

			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("");

			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();

			List<String> tableheaders = new ArrayList<String>();
			tableheaders.add("Parts Name");
			tableheaders.add("Volume Sold");

			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMOPARMTD, "Top 3 Mopar Parts & Engines, Mopar Accessories, FIAT and UVM Parts Sold MTD", tableheaders, "top3"));	
			//tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMOPARYTD, "Top 3 MOPAR Parts Sold YTD", tableheaders));

			datatable.setData(tabledata);

			TotalName mtd = new TotalName();
			TotalName ytd = new TotalName();
			List<MyfcaMSERTopNDTO> mtdEarnings =  this.dashService.getMSERTopTen("TotalEarnings", 1, "Mopar Parts", "MTD");
			List<MyfcaMSERTopNDTO> ytdEarnings =  this.dashService.getMSERTopTen("TotalEarnings", 1, "Mopar Parts", "YTD");
			
			
			double moneymtd = 0;
			double moneyytd = 0;
			
			if(mtdEarnings.size() > 0){
				MyfcaMSERTopNDTO EarningsMTD = mtdEarnings.get(0);
				moneymtd =  EarningsMTD.getEarnings();
			}
			
			if(ytdEarnings.size() > 0){
				MyfcaMSERTopNDTO EarningsYTD = ytdEarnings.get(0);
				moneyytd =  EarningsYTD.getEarnings();
			}
			
			mtd.setName("Total Mopar Parts Earnings - MTD");
			ytd.setName("Total Mopar Parts Earnings - YTD");
			mtd.setTotal("$" + this.formatCurrency(moneymtd));
			ytd.setTotal("$" +  this.formatCurrency(moneyytd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));

			topTenChart.setTop3(datatable.getData());

			return topTenChart;
		}
		case "4":
		{
			List<MyfcaMSERTopNDTO> listMVPMTD = this.dashService.getMSERTopTen("Top3Parts", 3,"MVP" , "MTD");
			//List<MSERTopNDTO> listMVPYTD = this.dashService.getMSERTopTen("Top3Parts", 3,"MVP" , "YTD");

			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("");

			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();

			List<String> tableheaders = new ArrayList<String>();
			tableheaders.add("Parts Name");
			tableheaders.add("Volume Sold");

			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMVPMTD, "Top 3 MVP Plans Sold MTD", tableheaders, "top3"));	
			//tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMVPYTD, "Top 3 MVP Plans Sold YTD", tableheaders));

			datatable.setData(tabledata);

			TotalName mtd = new TotalName();
			TotalName ytd = new TotalName();
			List<MyfcaMSERTopNDTO> mtdEarnings =  this.dashService.getMSERTopTen("TotalEarnings", 1, "MVP", "MTD");
			List<MyfcaMSERTopNDTO> ytdEarnings =  this.dashService.getMSERTopTen("TotalEarnings", 1, "MVP", "YTD");
			
			
			double moneymtd = 0;
			double moneyytd = 0;
			
			if(mtdEarnings.size() > 0){
				MyfcaMSERTopNDTO EarningsMTD = mtdEarnings.get(0);
				moneymtd =  EarningsMTD.getEarnings();
			}
			
			if(ytdEarnings.size() > 0){
				MyfcaMSERTopNDTO EarningsYTD = ytdEarnings.get(0);
				moneyytd =  EarningsYTD.getEarnings();
			}
			
			mtd.setName("Total MVP Plans Earnings - MTD");
			ytd.setName("Total MVP Plans Earnings - YTD");
			mtd.setTotal("$" + this.formatCurrency(moneymtd));
			ytd.setTotal("$" +  this.formatCurrency(moneyytd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));


			topTenChart.setTop3(datatable.getData());

			return topTenChart;
		}
		case "5":{
			List<MyfcaMSERTopNDTO> listMMPMTD = this.dashService.getMSERTopTen("Top3Parts", 3,"Magneti Marelli" , "MTD");
			//List<MSERTopNDTO> listMMPYTD = this.dashService.getMSERTopTen("Top3Parts", 3,"Magneti Marelli" , "YTD");

			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("");

			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();

			List<String> tableheaders = new ArrayList<String>();
			tableheaders.add("Parts Name");
			tableheaders.add("Volume Sold");

			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMMPMTD, "Top 3 Magneti Marelli Parts Sold MTD", tableheaders, "top3"));	
			//tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMMPYTD, "Top 3 Magneti Marelli Parts Sold YTD", tableheaders));

			datatable.setData(tabledata);

			TotalName mtd = new TotalName();
			TotalName ytd = new TotalName();
			List<MyfcaMSERTopNDTO> mtdEarnings =  this.dashService.getMSERTopTen("TotalEarnings", 1, "Magneti Marelli", "MTD");
			List<MyfcaMSERTopNDTO> ytdEarnings =  this.dashService.getMSERTopTen("TotalEarnings", 1, "Magneti Marelli", "YTD");
			
			double moneymtd = 0;
			double moneyytd = 0;
			
			if(mtdEarnings.size() > 0){
				MyfcaMSERTopNDTO EarningsMTD = mtdEarnings.get(0);
				moneymtd =  EarningsMTD.getEarnings();
			}
			
			if(ytdEarnings.size() > 0){
				MyfcaMSERTopNDTO EarningsYTD = ytdEarnings.get(0);
				moneyytd =  EarningsYTD.getEarnings();
			}
			

			mtd.setName("Total Magneti Marelli Earnings - MTD");
			ytd.setName("Total Magneti Marelli Earnings - YTD");
			mtd.setTotal("$" + this.formatCurrency(moneymtd));
			ytd.setTotal("$" +  this.formatCurrency(moneyytd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));

			topTenChart.setTop3(datatable.getData());

			return topTenChart;
		}
		case "6":
		{
			List<MyfcaMSERTopNDTO> listPCPMTD = this.dashService.getMSERTopTen("Top3Parts", 3,"Parts Counter" , "MTD");
			//List<MSERTopNDTO> listPCPYTD = this.dashService.getMSERTopTen("Top3Parts", 3,"Part Counter" , "YTD");

			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("");

			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();

			List<String> tableheaders = new ArrayList<String>();
			tableheaders.add("Parts Name");
			tableheaders.add("Volume Sold");

			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listPCPMTD, "Top 3 Parts Counter Parts Sold MTD", tableheaders, "top3"));	
			//tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listPCPYTD, "Top3Parts Counter Parts Sold YTD", tableheaders));

			datatable.setData(tabledata);

			TotalName mtd = new TotalName();
			TotalName ytd = new TotalName();
			List<MyfcaMSERTopNDTO> listMTD = this.dashService.getMSERTopTen("TotalEarnings", 1, "Parts Counter", "MTD");
			List<MyfcaMSERTopNDTO> listYTD = this.dashService.getMSERTopTen("TotalEarnings", 1, "Parts Counter", "YTD");
			double mtdearnings = 0;
			double ytdearnings = 0;
			if(listMTD.size()>0){
				mtdearnings = listMTD.get(0).getEarnings();
			}
			if(listYTD.size()>0){
				ytdearnings = listYTD.get(0).getEarnings();
			}




			mtd.setName("Total Parts Counter Earnings - MTD");
			ytd.setName("Total Parts Counter Earnings - YTD");
			mtd.setTotal("$" + this.formatCurrency(mtdearnings));
			ytd.setTotal("$" + this.formatCurrency(ytdearnings));

			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));

			topTenChart.setTop3(datatable.getData());

			return topTenChart;
		}
		case "7":
		{
			List<MyfcaMSERTopNDTO> listELPMTD = this.dashService.getMSERTopTen("Top3Parts", 3, "Express Lane", "MTD");
			//List<MSERTopNDTO> listELPYTD = this.dashService.getMSERTopTen("Top3Parts", 3, "Express Lane", "YTD");

			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("");

			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();

			List<String> tableheaders = new ArrayList<String>();
			tableheaders.add("Parts Name");
			tableheaders.add("Volume Sold");

			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listELPMTD, "Top 3 Express Lane Parts Sold MTD", tableheaders, "top3"));	
			//tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listELPYTD, "Top 3 Express Lane Parts Sold YTD", tableheaders));

			datatable.setData(tabledata);

			TotalName mtd = new TotalName();
			TotalName ytd = new TotalName();
			List<MyfcaMSERTopNDTO> mtdEarnings =  this.dashService.getMSERTopTen("TotalEarnings", 1, "Express Lane", "MTD");
			List<MyfcaMSERTopNDTO> ytdEarnings =  this.dashService.getMSERTopTen("TotalEarnings", 1, "Express Lane", "YTD");
			
			double moneymtd = 0;
			double moneyytd = 0;
			
			if(mtdEarnings.size() > 0){
				MyfcaMSERTopNDTO EarningsMTD = mtdEarnings.get(0);
				moneymtd =  EarningsMTD.getEarnings();
			}
			
			if(ytdEarnings.size() > 0){
				MyfcaMSERTopNDTO EarningsYTD = ytdEarnings.get(0);
				moneyytd =  EarningsYTD.getEarnings();
			}
			
			mtd.setName("Total Express Lane Parts Earnings - MTD");
			ytd.setName("Total Express Lane Parts Earnings - YTD");
			mtd.setTotal("$" + this.formatCurrency(moneymtd));
			ytd.setTotal("$" +  this.formatCurrency(moneyytd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));

			topTenChart.setTop3(datatable.getData());

			return topTenChart;
		}
		case "8":
		{
			TopTenChart topTenChart = new TopTenChart();
			TotalName mtd = new TotalName();
			TotalName ytd = new TotalName();
			List<MyfcaMSERTopNDTO> listMTD = this.dashService.getMSERTopTen("TotalEarnings", 1, "Uconnect", "MTD");
			List<MyfcaMSERTopNDTO> listYTD = this.dashService.getMSERTopTen("TotalEarnings", 1, "Uconnect", "YTD");
			double mtdearnings = 0;
			double ytdearnings = 0;
			if(listMTD.size()>0){
				mtdearnings = listMTD.get(0).getEarnings();
			}
			if(listYTD.size()>0){
				ytdearnings = listYTD.get(0).getEarnings();
			}

			mtd.setName("Total Uconnect Navigation Activation Earnings - MTD");
			ytd.setName("Total Uconnect Navigation Activation Earnings - YTD");
			mtd.setTotal("$" + this.formatCurrency(mtdearnings));
			ytd.setTotal("$" + this.formatCurrency(ytdearnings));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
			
			TotalName nav = new TotalName();
			nav.setName("Total Uconnect Navigation Activations - MTD");
			nav.setTotal("$0");
			List<MyfcaMSERTopNDTO> listELPMTD = this.dashService.getMSERTopTen("TotalCount", 3, "Uconnect-NAVACT", "MTD");
			if(listELPMTD.size()>0){
				nav.setTotal(this.formatCurrency(listELPMTD.get(0).getQuantity()));
			}
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(nav));
			

			return topTenChart;
		}
		case "9":
		{

			// check for role, to know what data to display
			List<BrainBoostWinndersGraphDTO> listBC = this.dashService.getBrainBoostWinndersGraphDTOByParentTerritory("NAT");
			List<BrainBoostWinndersGraphDTO> listBC_unfiltered = this.dashService.getBrainBoostGraphBCData(false);
			List<String> filters = new ArrayList<String>();

			for(BrainBoostWinndersGraphDTO item: listBC_unfiltered){
				filters.add(item.getChildTerritory());
			}

			Chart chart = this.mappingService.BrainBoostWinndersGraphDTOtoChart(listBC, "YTD Winners", "", "Business Center", "Total Winners", "column", "Winners");

			Map<String, List<String>> map = new HashMap<String, List<String>>();
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			for(BrainBoostWinndersGraphDTO item: listBC_unfiltered){
				if(type.equals("BC") && BC.length() ==2){
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
				if(type.equals("BC") && BC.length() ==2){
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
					if(map.get(item.getChildTerritory()).contains(object.getParentTerritory())){
						ChartData data = new ChartData(object.getParentTerritory(), object.getWinners());
						list.add(data);
					}
					chartsMap.put(item.getChildTerritory(), list);
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
			List<String> filters = new ArrayList<String>();

			filters.add("NAT");

			List<CertProfsExpertGraphDTO> list1st = this.dashService.getExpertPointsEarnedByParentTerritory(filters);
			
			filters.clear();

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
				if(type.equals("BC") && BC.length() ==2){
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
				if(type.equals("BC") && BC.length() ==2){
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

			filters.clear();
			
			for(CertProfsExpertGraphDTO item: unfiltereList){
				if(!filters.contains(item.getChildTerritory())){
					filters.add(item.getChildTerritory());
				}
			}

			unfiltereList = this.dashService.getExpertPointsEarnedByParentTerritory(filters);

			Chart chart = this.mappingService.CertProfsExpertGraphDTOtoChartCert(list, "Participants Completed By Program", "", "", "Total Participants", "column_stack");
			Chart chart2 = this.mappingService.CertProfsExpertGraphDTOtoChartCert(list, "Participants Completed By Program", "", "", "Total Participants", "column_stack");
			//Chart chart3 = this.mappingService.CertProfsExpertGraphDTOtoChartCert(list, "Participants Completed By Program", "", "", "Total Participants", "column_stack");

			filters.clear();
			
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			for(CertProfsExpertGraphDTO item: unfiltereList){
				if(!filters.contains(item.getChildTerritory())){
					if(type.equals("BC") && BC.length() ==2){
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

				if(type.equals("BC") && BC.length() ==2){
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
			Map<String, Double> mapValues3 = new HashMap<String, Double>();
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap2 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap3 = new HashMap<String, List<ChartData>>();
			for(CertProfsExpertGraphDTO item: list){

				List<ChartData> list2 = new ArrayList<ChartData>();
				List<ChartData> list3 = new ArrayList<ChartData>();
				List<ChartData> list4 = new ArrayList<ChartData>();
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
						else if(object.getCertType().contains("TECH") && item.getCertType().contains("TECH")){
							ChartData temp = new ChartData();
							temp.setName(object.getChildTerritory());
							temp.setValue(object.getCert());
							list4.add(temp);
						}
					}
					if(item.getCertType().contains("JEEP")){
						chartsMap.put(item.getParentTerritory(), list2);
						mapValues.put(item.getParentTerritory(), (double)item.getCert());
					}else if(item.getCertType().contains("RAM")){
						chartsMap2.put(item.getParentTerritory(), list3);
						mapValues2.put(item.getParentTerritory(), (double)item.getCert());
					}
					else if(item.getCertType().contains("TECH")){
						chartsMap3.put(item.getParentTerritory(), list4);
						mapValues3.put(item.getParentTerritory(), (double)item.getCert());
					}
				}



				/*if(item.getCertType().contains("JEEP")){
					mapValues.put(item.getParentTerritory(), (double)item.getCert());
	        	}else if(item.getCertType().contains("RAM")){
					mapValues2.put(item.getParentTerritory(), (double)item.getCert());
	        	}*/
			}

			List<ChartData> list1 = new ArrayList<ChartData>();
			//list1.add(new ChartData("JEEP", 0));
			list1.add(new ChartData("RAM", 0));
			//list1.add(new ChartData("TECH", 0));


			//List<ChartData> a = new ArrayList<ChartData>(chart.getData());
			List<ChartData> b = new ArrayList<ChartData>(chart2.getData());
			//List<ChartData> c = new ArrayList<ChartData>(chart3.getData());

			//double tempa =0;
			double tempb =0;
			//double tempc =0;
			/*for(ChartData item: a){
				for(ChartData var: item.getData()){
					if(var.getName().contains("JEEP"))
						tempa += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				//item.setValue(mapValues.get(item.getName()));
				//item.addDataList(chartsMap.get(item.getName()));
				if(mapValues.containsKey(item.getName()) && chartsMap.containsKey(item.getName())){
					item.setValue(mapValues.get(item.getName()));
					item.addDataList(chartsMap.get(item.getName()));
				}
			}*/

			for(ChartData item: b){
				for(ChartData var: item.getData()){
					if(var.getName().contains("RAM"))
						tempb += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				//item.setValue(mapValues2.get(item.getName()));
				//item.addDataList(chartsMap2.get(item.getName()));
				if(mapValues2.containsKey(item.getName()) && chartsMap2.containsKey(item.getName())){
					item.setValue(mapValues2.get(item.getName()));
					item.addDataList(chartsMap2.get(item.getName()));
				}
			}

			/*for(ChartData item: c){
				for(ChartData var: item.getData()){
					if(var.getName().contains("TECH"))
						tempc += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				if(mapValues3.containsKey(item.getName()) && chartsMap3.containsKey(item.getName())){
					item.setValue(mapValues3.get(item.getName()));
					item.addDataList(chartsMap3.get(item.getName()));
				}
			}*/

			list1.get(0).setValue(tempb);
			//list1.get(1).setValue(tempa);
			//list1.get(2).setValue(tempc);
			list1.get(0).setData(b);
			//list1.get(1).setData(a);
			//list1.get(2).setData(c);

			chart.setData(list1);


			return chart;
		}
		case "12":
		{
			// check for role, to know what data to display
			List<BrainBoostWinndersGraphDTO> listBC = this.dashService.getBrainBoostWinndersGraphDTOByParentTerritory("NAT");
			List<BrainBoostWinndersGraphDTO> listBC_unfiltered = this.dashService.getBrainBoostGraphBCData(false);
			List<String> filters = new ArrayList<String>();

			for(BrainBoostWinndersGraphDTO item: listBC_unfiltered){
				filters.add(item.getChildTerritory());
			}

			Map<String, List<String>> map = new HashMap<String, List<String>>();
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			for(BrainBoostWinndersGraphDTO item: listBC_unfiltered){
				if(type.equals("BC") && BC.length() ==2){
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
				if(type.equals("BC") && BC.length() ==2){
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

			Chart chart = this.mappingService.BrainBoostWinndersGraphDTOtoChart(listBC, "Award Points YTD", "", "Award Points", "Total Points", "column", "Awards");


			Map<String, Double> mapValues = new HashMap<String, Double>();
			for(BrainBoostWinndersGraphDTO item: listBC){
				List<ChartData> list = new ArrayList<ChartData>();
				for(BrainBoostWinndersGraphDTO object: sublist){
					if(map.get(item.getChildTerritory()).contains(object.getParentTerritory())){

						ChartData temp = new ChartData();
						temp.setName(object.getParentTerritory());
						temp.setValue(object.getPoints());

						list.add(temp);
					}
					chartsMap.put(item.getChildTerritory(), list);
					mapValues.put(item.getChildTerritory(), (double)item.getPoints());
				}
			}

			List<ChartData> list1 = new ArrayList<ChartData>();
			list1.add(new ChartData("Award Points", 0));


			List<ChartData> a = new ArrayList<ChartData>(chart.getData());
			

			//double tempa =0;

			for(ChartData item: a){
				/*for(ChartData var: item.getData()){
					if(var.getName().equals("Award Points"))
						tempa = var.getValue();
				}*/
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues.get(item.getName()));
				item.addDataList(chartsMap.get(item.getName()));
			}

			//list1.get(0).setValue(tempa);
			//list1.get(0).setData(a);
			
			chart.setData(a);

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
				if(type.equals("BC") && BC.length() ==2){
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
				if(type.equals("BC") && BC.length() ==2){
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


			Chart chart = this.mappingService.CertProfsWinnersGraphDTOtoChart(listBC, "Certifications in the Prior Year", "", "", "Total Certifications", "column_stack");
			Chart chart2 = this.mappingService.CertProfsWinnersGraphDTOtoChart(listBC, "Certifications in the Prior Year", "", "", "Total Certifications", "column_stack");
			Chart chart3 = this.mappingService.CertProfsWinnersGraphDTOtoChart(listBC, "Certifications in the Prior Year", "", "", "Total Certifications", "column_stack");
			//Chart chart2 = mappingService.copyChart(chart);
			//Chart chart3 = mappingService.copyChart(chart);
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
						temp.setValue(Double.parseDouble(object.getCertifiedSpecialist()));
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
					mapValues2.put(item.getParentTerritory(), Double.parseDouble(item.getCertifiedSpecialist()));
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
			String query = "NATTA10";
			if(type.equalsIgnoreCase("bc")){
				query = BC + "TA10";
			}
			//set datatables
			List<TTTATopNDTO> listAdvisorsQTD = this.dashService.getTTTATopN(query, 10);
			//List<TTTATopNDTO> listAdvisorsYTD = this.dashService.getTTTATopN(query, 10);

			TopTenChart topTenChart = new TopTenChart();
			//TopTenDataTable datatableT = new TopTenDataTable("TOP 10 Technicians"); //, "Rewarding Excellence&reg; Card Awards - Top 5 Technicians"
			TopTenDataTable datatableA = new TopTenDataTable("TOP 10 Advisors QTD by Overall Survey Score");
			List<TopTenTableData> tabledataA = new ArrayList<TopTenTableData>();
			//List<TopTenTableData> tabledataT = new ArrayList<TopTenTableData>();

			List<String> tableheaders = new ArrayList<String>();
			//keeps this as example
			tableheaders.add("Name");
			tableheaders.add("Dealership");
			tableheaders.add("Business Center");
			tableheaders.add("Total Surveys");
			tableheaders.add("Survey Score");


			tabledataA.add(this.mappingService.MapTTTATopNDTOtoTopTenTableData(listAdvisorsQTD, "Top 10 Advisors QTD by Overall Survey Score", tableheaders));	
			//tabledataA.add(this.mappingService.MapTTTATopNDTOtoTopTenTableData(listAdvisorsYTD, "Top 10 Advisors YTD Average Survey Scores", tableheaders));
			tabledataA.get(0).setTabName("QTD");
			//tabledataA.get(1).setTabName("YTD");
			datatableA.setData(tabledataA);

			topTenChart.setTop10_advisors(datatableA);

			//set attributes
			if(type.equals("Executive")){

				List<String> filters = new ArrayList<String>(Arrays.asList("NAT"));
				List<TTTAEnrollmentsSummaryDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsSummarySUMByParentAndPositionCode(filters, "13");
				List<Double> averageSurveyScoreList = this.dashService.getTTTANATAverageSurveyScoreByPositionCode("13");
				TotalName dealerscount = new TotalName("Total Dealers Enrolled","0");
				
				List<Integer> dealerscountlist = this.dashService.getTotalDealersEnrolledByProgramID(this.TA_PROGRAM_ID);
				
				if(dealerscountlist.size()>0){
					dealerscount.setTotal(this.formatCurrency(dealerscountlist.get(0)));
				}

				dashService.getTTTANATTopAdvisorEnrolledDealerCount();

				TotalName incentiveEligible  = new TotalName();
				incentiveEligible.setName("Total Advisors Incentive Eligible This Quarter");
				incentiveEligible.setTotal("0");

				TotalName qtdSurveyCount  = new TotalName();
				qtdSurveyCount.setName("QTD Survey Count");
				qtdSurveyCount.setTotal("0");

				TotalName avgSurveyCount  = new TotalName();
				avgSurveyCount.setName("Average Quarterly Survey Score");
				avgSurveyCount.setTotal("0.0%");
				DecimalFormat df = new DecimalFormat("0.0");
				if(incentiveEligibleList.size()>0){
					//dealerscount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getTotalEnrollments()));
					incentiveEligible.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getIncentiveEligible()));
					qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
					avgSurveyCount.setTotal(df.format(incentiveEligibleList.get(0).getAvgSurveyScore()) + "%");
				}
				if(averageSurveyScoreList.size()>0){
					avgSurveyCount.setTotal(df.format(averageSurveyScoreList.get(0)) + "%");
				}

				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgSurveyCount));


			}else if(type.equals("BC") && BC.length() ==2){
				TotalName dealerscount = new TotalName("Total Dealers Enrolled","0");
				List<Integer> dealerscountlist = this.dashService.getTotalDealersEnrolledByProgramIDAndTerritory(TA_PROGRAM_ID, BC+"%");
				
				if(dealerscountlist.size()>0){
					dealerscount.setTotal(this.formatCurrency(dealerscountlist.get(0)));
				}
				

				List<TTTAEnrollmentsSummaryDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsSummaryByChildAndPositionCode(BC, "13");

				/*TotalName dealerscount =  new TotalName();
				dealerscount.setName("Total Dealers Enrolled");
				dealerscount.setTotal("0");*/

				TotalName incentiveEligible  = new TotalName();
				incentiveEligible.setName("Total Advisors Incentive Eligible This Quarter");
				incentiveEligible.setTotal("0");

				TotalName qtdSurveyCount  = new TotalName();
				qtdSurveyCount.setName("QTD Survey Count");
				qtdSurveyCount.setTotal("0");

				TotalName avgSurveyCount  = new TotalName();
				avgSurveyCount.setName("Average Quarterly Survey Score");
				avgSurveyCount.setTotal("0.0%");

				DecimalFormat df = new DecimalFormat("0.0");
				if(incentiveEligibleList.size()>0){
					//dealerscount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getTotalEnrollments()));
					incentiveEligible.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getIncentiveEligible()));
					qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
					avgSurveyCount.setTotal(df.format(incentiveEligibleList.get(0).getAvgSurveyScore()) + "%");
				}

				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgSurveyCount));

			}

			return topTenChart;
		}
		case "15":
		{
			//set datatables
			String query = "NATTT10";
			if(type.equalsIgnoreCase("bc")){
				query = BC + "TT10";
			}
			List<TTTATopNDTO> listAdvisorsQTD = this.dashService.getTTTATopN(query, 10);
			//List<TTTATopNDTO> listAdvisorsYTD = this.dashService.getTTTATopN(query, 10);

			TopTenChart topTenChart = new TopTenChart();

			TopTenDataTable datatableT = new TopTenDataTable("TOP 10 Technicians QTD by Overall Survey Score"); //, "Rewarding Excellence&reg; Card Awards - Top 5 Technicians"
			List<TopTenTableData> tabledataT = new ArrayList<TopTenTableData>();

			List<String> tableheaders = new ArrayList<String>();
			tableheaders.add("Name");
			tableheaders.add("Dealership");
			tableheaders.add("Business Center");
			tableheaders.add("Total Surveys");
			tableheaders.add("Survey Score");

			//tabledataT.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listTechnicians, "Top 10 MTD Technicians Rewarding Excellence&reg; Card Awards", tableheaders));
			//tabledataT.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listTechnicians, "Top 10 YTD Technicians Rewarding Excellence&reg; Card Awards", tableheaders));

			//datatableT.setData(tabledataT);

			tabledataT.add(this.mappingService.MapTTTATopNDTOtoTopTenTableData(listAdvisorsQTD, "Top 10 Technicians QTD by Overall Survey Score", tableheaders));	
			//tabledataT.add(this.mappingService.MapTTTATopNDTOtoTopTenTableData(listAdvisorsYTD, "Top 10 Technicians YTD Average Survey Scores", tableheaders));
			tabledataT.get(0).setTabName("QTD");
			//tabledataT.get(1).setTabName("YTD");
			datatableT.setData(tabledataT);

			topTenChart.setTop10_technicians(datatableT);
			DecimalFormat df = new DecimalFormat("0.0");
			//set attributes
			if(type.equals("Executive")){

				List<String> filters = new ArrayList<String>(Arrays.asList("NAT"));
				List<TTTAEnrollmentsSummaryDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsSummarySUMByParentAndPositionCode(filters, "23");
				List<Double> averageSurveyScoreList = this.dashService.getTTTANATAverageSurveyScoreByPositionCode("23");
				TotalName dealerscount = new TotalName("Total Dealers Enrolled","0");
				
				List<Integer> dealerscountlist = this.dashService.getTotalDealersEnrolledByProgramID(this.TT_PROGRAM_ID);
				
				if(dealerscountlist.size()>0){
					dealerscount.setTotal(this.formatCurrency(dealerscountlist.get(0)));
				}

				TotalName incentiveEligible  = new TotalName();
				incentiveEligible.setName("Total Technicians Incentive Eligible This Quarter");
				incentiveEligible.setTotal("0");

				TotalName qtdSurveyCount  = new TotalName();
				qtdSurveyCount.setName("QTD Survey Count");
				qtdSurveyCount.setTotal("0");

				TotalName avgSurveyCount  = new TotalName();
				avgSurveyCount.setName("Average Quarterly Survey Score");
				avgSurveyCount.setTotal("0.0%");

				TotalName avgYearsOfService  = new TotalName();
				avgYearsOfService.setName("Average Years of Service for Technicians");
				avgYearsOfService.setTotal("0");

				if(incentiveEligibleList.size()>0){
					incentiveEligible.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getIncentiveEligible()));
					qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
					avgYearsOfService.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getYearsOfService()));
				}
				
				if(averageSurveyScoreList.size()>0){
					avgSurveyCount.setTotal(df.format(averageSurveyScoreList.get(0)) + "%");
				}

				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgYearsOfService));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));

			}else if(type.equals("BC") && BC.length() ==2){
				TotalName dealerscount = new TotalName("Total Dealers Enrolled","0");
				
				List<Integer> dealerscountlist = this.dashService.getTotalDealersEnrolledByProgramIDAndTerritory(TT_PROGRAM_ID, BC+"%");
				
				if(dealerscountlist.size()>0){
					dealerscount.setTotal(this.formatCurrency(dealerscountlist.get(0)));
				}
				
				List<TTTAEnrollmentsSummaryDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsSummaryByChildAndPositionCode(BC, "23");

				TotalName incentiveEligible  = new TotalName();
				incentiveEligible.setName("Total Technicians Incentive Eligible This Quarter");
				incentiveEligible.setTotal("0");

				TotalName qtdSurveyCount  = new TotalName();
				qtdSurveyCount.setName("QTD Survey Count");
				qtdSurveyCount.setTotal("0");

				TotalName avgSurveyCount  = new TotalName();
				avgSurveyCount.setName("Average Quarterly Survey Score");
				avgSurveyCount.setTotal("0.0%");

				TotalName avgYearsOfService  = new TotalName();
				avgYearsOfService.setName("Average Years of Service for Technicians");
				avgYearsOfService.setTotal("0");

				if(incentiveEligibleList.size()>0){
					incentiveEligible.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getIncentiveEligible()));
					qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
					avgSurveyCount.setTotal(df.format(incentiveEligibleList.get(0).getAvgSurveyScore()) + "%");
					avgYearsOfService.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getYearsOfService()));
				}

				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgYearsOfService));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));

			}
			return topTenChart;
		}
		case "18":
		{
			List<MyfcaMSERTopNDTO> listMTD = this.dashService.getMSERTopTen("Top3Parts", 3, "wiAdvisor", "MTD");
			//List<MSERTopNDTO> listYTD = this.dashService.getMSERTopTen("Top3Parts", 3, "wiAdvisor", "YTD");

			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("");

			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();

			List<String> tableheaders = new ArrayList<String>();
			tableheaders.add("Parts Name");
			tableheaders.add("Volume Sold");

			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMTD, "Top 3 wiAdvisor Parts Sold MTD", tableheaders, "top3"));	
			//tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listYTD, "Top 3 wiAdvisor Parts Sold YTD", tableheaders));

			datatable.setData(tabledata);

			//TotalName mtd = this.dashService.getMTDByProgramAndProgramgroup("Total Parts Earnings - MTD", "wiAdvisor Parts Sold", "wiAdvisor Parts Sold");
			//TotalName ytd = this.dashService.getYTDByProgramAndProgramgroup("Total Parts Earnings - YTD", "wiAdvisor Parts Sold", "wiAdvisor Parts Sold");

			TotalName mtd = new TotalName();
			TotalName ytd = new TotalName();
			List<MyfcaMSERTopNDTO> listMTD1 = this.dashService.getMSERTopTen("TotalEarnings", 1, "wiAdvisor", "MTD");
			List<MyfcaMSERTopNDTO> listYTD = this.dashService.getMSERTopTen("TotalEarnings", 1, "wiAdvisor", "YTD");
			double mtdearnings = 0;
			double ytdearnings = 0;
			if(listMTD.size()>0){
				mtdearnings = listMTD1.get(0).getEarnings();
			}
			if(listYTD.size()>0){
				ytdearnings = listYTD.get(0).getEarnings();
			}


			mtd.setName("Total wiAdvisor Parts Earnings - MTD");
			ytd.setName("Total wiAdvisor Parts Earnings - YTD");
			mtd.setTotal("$" + this.formatCurrency(mtdearnings));
			ytd.setTotal("$" + this.formatCurrency(ytdearnings));

			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));

			topTenChart.setTop3(datatable.getData());

			return topTenChart;
		}
		case "19":
		{
			List<MyfcaMSERTotalEarningsDTO> listOfFirstLevel = this.dashService.getMSERGraphByTerritoryAndToggle("NAT", "YTD");


			List<String> filters = new ArrayList<String>();
			for(MyfcaMSERTotalEarningsDTO item: listOfFirstLevel){
				if(item.getChild() != null && !filters.contains(item.getChild()))
					filters.add(item.getChild());
			}
			Collections.sort(filters);
			//main chart model
			Chart chart = new Chart("Total Earnings YTD", "", "Total Earnings", "", "bar_compound");

			//Create first level of the chart
			//Get Distinct program names
			List<String> programs = this.dashService.getMSERGraphDistinctProgramsByParentTerritoryAndToggle("NAT", "YTD");
			
			Collections.sort(programs);
			
			if(programs.contains("Mopar Parts")){
				programs.remove("Mopar Parts");
				programs.add(0, "Mopar Parts");
			}
			
			
			List<ChartData> attributes = new ArrayList<ChartData>();

			for(String programName: programs){
				attributes.add(new ChartData(programName, 0));
			}


			for(ChartData item: attributes){
				double total = 0;
				for(String filter: filters){
					List<MyfcaMSERTotalEarningsDTO> innerList = this.dashService.getMSERGraphByTerritoryAndToggleAndProgram(filter, "YTD", item.getName());
					ChartData chartD = new ChartData(filter, total);
					for(MyfcaMSERTotalEarningsDTO a: innerList){
						total += a.getAmount();
						if((type.equals("BC") && filter.equals(BC) && BC.length() ==2) || type.equals("Executive")){
							chartD.addData(new ChartData(a.getChild(), a.getAmount()));
						}
					}
					chartD.setValue(total);
					total = 0;
					// with out b =0; java skips the next line
					int b = 0;
					/*if(type.equals("BC") && filter.equals(BC)){
						item.addData(chartD);
					}else if(type.equals("Executive")){
						item.addData(chartD);
					}*/
					item.addData(chartD);
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
			String currentYear = new DateTime().getYear() + "";
			String lastYear = (new DateTime().getYear() -1) + "";

			//check if nat or not if nat pull list of childeren and continue if not start from their
			list1st = this.dashService.getSIRewardsYOYGraphByTerritoryAndToggle("NAT", "YTD");

			for(SIRewardsYOYGraphDTO item: list1st){
				if(!filters.contains(item.getChildTerritory()))
					filters.add(item.getChildTerritory());
			}

			//list1st = this.dashService.getSIRewardsYOYGraphByTerritoryAndToggle(filters, "YTD");
			list1st_Filtered = this.dashService.getSIRewardsYOYGraphByTerritoryAndToggleFilterParent(filters, "YTD");


			Chart chart = this.mappingService.SIRewardsYOYGraphDTOtoChart(list1st_Filtered, "Service Incentive Reward Earnings YTD", "", "", "Total Dollars Earned", "column_compound");
			Chart chart2 = this.mappingService.SIRewardsYOYGraphDTOtoChart(list1st_Filtered, "Service Incentive Reward Earnings YTD", "", "", "Total Dollars Earned", "column_compound");


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
				if(type.equals("BC") && BC.length() ==2){
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
						temp.setValue(object.getLastYearEarnings());


						list.add(temp);
						temp = new ChartData();
						temp.setName(object.getChildTerritory());
						temp.setValue(object.getCurrentYearEarnings());
						list2.add(temp);
					}
				}
				chartsMap.put(item.getParentTerritory(), list);
				chartsMap2.put(item.getParentTerritory(), list2);
				mapValues.put(item.getParentTerritory(), item.getLastYearEarnings());
				mapValues2.put(item.getParentTerritory(), item.getCurrentYearEarnings());
			}

			List<ChartData> list1 = new ArrayList<ChartData>();
			list1.add(new ChartData(lastYear, 0));
			list1.add(new ChartData(currentYear, 0));


			List<ChartData> a = new ArrayList<ChartData>(chart.getData());
			List<ChartData> b = new ArrayList<ChartData>(chart2.getData());

			double tempa =0;
			double tempb =0;
			for(ChartData item: a){
				for(ChartData var: item.getData()){
					if(var.getName().equals(lastYear))
						tempa = var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues.get(item.getName()));
				item.addDataList(chartsMap.get(item.getName()));
			}

			for(ChartData item: b){
				for(ChartData var: item.getData()){
					if(var.getName().equals(currentYear))
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
				if(type.equals("BC") && BC.length() ==2){
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


			Chart chart = this.mappingService.SIRewardsDetailsGraphDTOtoChart(list1st_Filtered, "Projected Service Incentive Earnings QTD", "", "Projected Earnings", "Projected Earnings", "column", "Projected");

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
				if(type.equals("BC") && BC.length() ==2){
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


			Chart chart = this.mappingService.RewardRedemptionGraphDTOtoChart(list1st_Filtered, "Total Program Points Earned YTD", "", "Total Points Earned", "Total Points Earned", "column", "EarnedPoints");

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
				if(type.equals("BC") && BC.length() ==2){
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
		case "29":
		{
			String typeSA = "NAT SIR TA 10";
			if(type.equalsIgnoreCase("bc")){
				typeSA = BC + " SIR TA 10";
			}
			List<MyfcaMSERTopNDTO> listAdvisors = this.dashService.getTopNByTypeINMSERTABLE(typeSA, 10, null, "QTD");
			List<MyfcaMSERTopNDTO> listAdvisorsYTD = this.dashService.getTopNByTypeINMSERTABLE(typeSA, 10, null, "YTD");

			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatableA = new TopTenDataTable("Top 10 Advisors Countrywide Based on Earnings");
			List<TopTenTableData> tabledataA = new ArrayList<TopTenTableData>();

			List<String> tableheaders = new ArrayList<String>();
			//keeps this as example
			tableheaders.add("Name");
			tableheaders.add("Dealership");
			tableheaders.add("Business Center");
			tableheaders.add("Earnings");

			tabledataA.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listAdvisorsYTD, "Top 10 Advisors Countrywide Based on YTD Earnings", tableheaders, "top10"));
			tabledataA.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listAdvisors, "Top 10 Advisors Countrywide Based on QTD Earnings", tableheaders, "top10"));
			tabledataA.get(0).setTabName("YTD");
			tabledataA.get(1).setTabName("QTD");

			datatableA.setData(tabledataA);

			topTenChart.setTop10_advisors(datatableA);
			
			
			TotalName eligibleSurveys = new TotalName("Eligible Surveys Returned with a Score of 9 or 10 QTD", "0");
			TotalName projectedEarnings = new TotalName("Projected Earnings QTD", "$0");
			TotalName totalEarningsYTD = new TotalName("Total Earnings YTD ","$0");
			TotalName advisorsIncentiveQualified = new TotalName("Advisors Incentive Qualified QTD", "0"); 
			TotalName managersAndDirectorsQualified = new TotalName("Managers and Directors Qualified QTD","0");
			if(type.equals("Executive")){
				List<SIRewardsDetailsGraphDTO> SIRewardsDetailsGraphList = this.dashService.getSIRewardsDetailsGraphSUMByTerritoryAndToggle("NAT", "QTD");
				List<SIRewardsYOYGraphDTO> SIRewardsYOYGraphDTO = this.dashService.getSIRewardsYOYGraphSumByTerritoryAndToggle("NAT", "YTD");
				
				if(SIRewardsDetailsGraphList.size() > 0){
					SIRewardsDetailsGraphDTO SIRewardsDetailsGraphDTO = SIRewardsDetailsGraphList.get(0);
					eligibleSurveys.setTotal(this.formatCurrency(SIRewardsDetailsGraphDTO.getEligibleSurveys()));
					projectedEarnings.setTotal("$" + this.formatCurrency(SIRewardsDetailsGraphDTO.getProjectedEarnings()));
					advisorsIncentiveQualified.setTotal(this.formatCurrency(SIRewardsDetailsGraphDTO.getAdvsiorsQualified()));
					managersAndDirectorsQualified.setTotal(this.formatCurrency(SIRewardsDetailsGraphDTO.getManagersQualified()));
				}
				
				if(SIRewardsYOYGraphDTO.size() > 0){
					totalEarningsYTD.setTotal("$" + this.formatCurrency(SIRewardsYOYGraphDTO.get(0).getCurrentYearEarnings()));
				}
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(projectedEarnings));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalEarningsYTD));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(advisorsIncentiveQualified));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(managersAndDirectorsQualified));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(eligibleSurveys));
			}else if(type.equals("BC") && BC.length() ==2){
				List<String> filters = new ArrayList<String>();
				filters.add(BC);
				List<SIRewardsDetailsGraphDTO> SIRewardsDetailsGraphList = this.dashService.getSIRewardsDetailsGraphByChildTerritoryAndToggle(filters, "QTD");
				List<SIRewardsYOYGraphDTO> SIRewardsYOYGraphDTO = this.dashService.getSIRewardsYOYGraphByChildAndToggle(BC, "YTD");
				if(SIRewardsDetailsGraphList.size() > 0){
					SIRewardsDetailsGraphDTO SIRewardsDetailsGraphDTO = SIRewardsDetailsGraphList.get(0);
					eligibleSurveys.setTotal(this.formatCurrency(SIRewardsDetailsGraphDTO.getEligibleSurveys()));
					projectedEarnings.setTotal("$" + this.formatCurrency(SIRewardsDetailsGraphDTO.getProjectedEarnings()));
					advisorsIncentiveQualified.setTotal(this.formatCurrency(SIRewardsDetailsGraphDTO.getAdvsiorsQualified()));
					managersAndDirectorsQualified.setTotal(this.formatCurrency(SIRewardsDetailsGraphDTO.getManagersQualified()));
				}
				
				if(SIRewardsYOYGraphDTO.size() > 0){
					totalEarningsYTD.setTotal("$" + this.formatCurrency(SIRewardsYOYGraphDTO.get(0).getCurrentYearEarnings()));
				}
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(projectedEarnings));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalEarningsYTD));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(advisorsIncentiveQualified));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(managersAndDirectorsQualified));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(eligibleSurveys));
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

				cartificationLevel.setName("12-Month 12-Month Retention Percentage for Service Managers");
				cartificationLevel.setTotal("0.0%");

				years.setName("Rolling 12-Month Retention Percentage for Service Advisors");
				years.setTotal("0.0%");

				totalCertifiedParticipants.setName("Rolling 12-Month Retention Percentage for Parts Managers");
				totalCertifiedParticipants.setTotal("0.0%");

				totalMasterCertifiedParticipants.setName("Rolling 12-Month Retention Percentage for Parts Advisors");
				totalMasterCertifiedParticipants.setTotal("0.0%");

				totalCertifiedSpecialistParticipants.setName("Rolling 12-Month Retention Percentage for Service Technicians");
				totalCertifiedSpecialistParticipants.setTotal("0.0%");

				totalCertifiedLevelParticipants.setName("Rolling 12-Month Retention Percentage for Sales Managers");
				totalCertifiedLevelParticipants.setTotal("0.0%");


				dealershipMasterCertifiedRankWithinBC.setName("Rolling 12-Month Retention Percentage for Sales Consultants");
				dealershipMasterCertifiedRankWithinBC.setTotal("0.0%");

				DecimalFormat df = new DecimalFormat("0.0");
				if(RetentionGraphServiceManagerList.size()>0){
					cartificationLevel.setName("Rolling 12-Month Retention Percentage for Service Managers");
					cartificationLevel.setTotal(df.format(RetentionGraphServiceManagerList.get(0).getPercentage()) + "%");
				}

				if(RetentionGraphServiceAdvisorList.size()>0){
					years.setName("Rolling 12-Month Retention Percentage for Service Advisors");
					years.setTotal(df.format(RetentionGraphServiceAdvisorList.get(0).getPercentage()) + "%");
				}

				if(RetentionGraphPartsManagerList.size()>0){
					totalCertifiedParticipants.setName("Rolling 12-Month Retention Percentage for Parts Managers");
					totalCertifiedParticipants.setTotal(df.format(RetentionGraphPartsManagerList.get(0).getPercentage()) + "%");
				}

				if(RetentionGraphPartsAdvisorList.size()>0){
					totalMasterCertifiedParticipants.setName("Rolling 12-Month Retention Percentage for Parts Advisors");
					totalMasterCertifiedParticipants.setTotal(df.format(RetentionGraphPartsAdvisorList.get(0).getPercentage()) + "%");
				}


				if(RetentionGraphServiceTechnicianList.size()>0){
					totalCertifiedSpecialistParticipants.setName("Rolling 12-Month Retention Percentage for Service Technicians");
					totalCertifiedSpecialistParticipants.setTotal(df.format(RetentionGraphServiceTechnicianList.get(0).getPercentage()) + "%");
				}


				if(RetentionGraphSalesManagerList.size()>0){
					totalCertifiedLevelParticipants.setName("Rolling 12-Month Retention Percentage for Sales Managers");
					totalCertifiedLevelParticipants.setTotal(df.format(RetentionGraphSalesManagerList.get(0).getPercentage()) + "%");
				}

				if(RetentionGraphBLSCList.size()>0){
					dealershipMasterCertifiedRankWithinBC.setName("Rolling 12-Month Retention Percentage for Sales Consultants");
					dealershipMasterCertifiedRankWithinBC.setTotal(df.format(RetentionGraphBLSCList.get(0).getPercentage()) + "%");
				}

				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevel));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedLevelParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedRankWithinBC));

			}else if(type.equals("BC") && BC.length() ==2){
				String territory = BC;
				List<RetentionGraphDTO> RetentionGraphServiceManagerList = this.dashService.getRetentionGraphByChildTerritoryListAndPositionCode(territory, "09");
				List<RetentionGraphDTO> RetentionGraphPartsAdvisorList = this.dashService.getRetentionGraphByChildTerritoryListAndPositionCode(territory, "14");
				List<RetentionGraphDTO> RetentionGraphServiceTechnicianList = this.dashService.getRetentionGraphByChildTerritoryListAndPositionCode(territory, "23");
				List<RetentionGraphDTO> RetentionGraphServiceAdvisorList = this.dashService.getRetentionGraphByChildTerritoryListAndPositionCode(territory, "13");
				List<RetentionGraphDTO> RetentionGraphBLSCList = this.dashService.getRetentionGraphByChildTerritoryListAndPositionCode(territory, "42");
				List<RetentionGraphDTO> RetentionGraphSalesManagerList = this.dashService.getRetentionGraphByChildTerritoryListAndPositionCode(territory, "04");
				List<RetentionGraphDTO> RetentionGraphPartsManagerList = this.dashService.getRetentionGraphByChildTerritoryListAndPositionCode(territory, "08");

				cartificationLevel.setName("Rolling 12-Month Retention Percentage for Service Managers");
				cartificationLevel.setTotal("0.0%");

				years.setName("Rolling 12-Month Retention Percentage for Service Advisors");
				years.setTotal("0.0%");

				totalCertifiedParticipants.setName("Rolling 12-Month Retention Percentage for Parts Managers");
				totalCertifiedParticipants.setTotal("0.0%");

				totalMasterCertifiedParticipants.setName("Rolling 12-Month Retention Percentage for Parts Advisors");
				totalMasterCertifiedParticipants.setTotal("0.0%");

				totalCertifiedSpecialistParticipants.setName("Rolling 12-Month Retention Percentage for Service Technicians");
				totalCertifiedSpecialistParticipants.setTotal("0.0%");

				totalCertifiedLevelParticipants.setName("Rolling 12-Month Retention Percentage for Sales Managers");
				totalCertifiedLevelParticipants.setTotal("0.0%");


				dealershipMasterCertifiedRankWithinBC.setName("Rolling 12-Month Retention Percentage for Sales Consultants");
				dealershipMasterCertifiedRankWithinBC.setTotal("0.0%");

				DecimalFormat df = new DecimalFormat("0.0");
				if(RetentionGraphServiceManagerList.size()>0){
					cartificationLevel.setName("Rolling 12-Month Retention Percentage for Service Managers");
					cartificationLevel.setTotal(df.format(RetentionGraphServiceManagerList.get(0).getPercentage()) + "%");
				}

				if(RetentionGraphServiceAdvisorList.size()>0){
					years.setName("Rolling 12-Month Retention Percentage for Service Advisors");
					years.setTotal(df.format(RetentionGraphServiceAdvisorList.get(0).getPercentage()) + "%");
				}

				if(RetentionGraphPartsManagerList.size()>0){
					totalCertifiedParticipants.setName("Rolling 12-Month Retention Percentage for Parts Managers");
					totalCertifiedParticipants.setTotal(df.format(RetentionGraphPartsManagerList.get(0).getPercentage()) + "%");
				}

				if(RetentionGraphPartsAdvisorList.size()>0){
					totalMasterCertifiedParticipants.setName("Rolling 12-Month Retention Percentage for Parts Advisors");
					totalMasterCertifiedParticipants.setTotal(df.format(RetentionGraphPartsAdvisorList.get(0).getPercentage()) + "%");
				}


				if(RetentionGraphServiceTechnicianList.size()>0){
					totalCertifiedSpecialistParticipants.setName("Rolling 12-Month Retention Percentage for Service Technicians");
					totalCertifiedSpecialistParticipants.setTotal(df.format(RetentionGraphServiceTechnicianList.get(0).getPercentage()) + "%");
				}


				if(RetentionGraphSalesManagerList.size()>0){
					totalCertifiedLevelParticipants.setName("Rolling 12-Month Retention Percentage for Sales Managers");
					totalCertifiedLevelParticipants.setTotal(df.format(RetentionGraphSalesManagerList.get(0).getPercentage()) + "%");
				}

				if(RetentionGraphBLSCList.size()>0){
					dealershipMasterCertifiedRankWithinBC.setName("Rolling 12-Month Retention Percentage for Sales Consultants");
					dealershipMasterCertifiedRankWithinBC.setTotal(df.format(RetentionGraphBLSCList.get(0).getPercentage()) + "%");
				}

				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevel));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedLevelParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedRankWithinBC));

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
			Chart chart = new Chart("Retention % By Employee Type", "", "Retention %", "", "column_stack");

			//Create first level of the chart
			//Get Distinct program names
			List<String> programs = new ArrayList<String>(Arrays.asList("09","23","14","13","42","04","08"));

			List<ChartData> attributes = new ArrayList<ChartData>();

			for(String programName: programs){
				attributes.add(new ChartData(programName, 0));
			}


			for(ChartData item: attributes){
				double total = 0;
				for(String filter: filters){
					List<String> in = new ArrayList<String>(Arrays.asList(filter));
					List<RetentionGraphDTO> innerListTotal = this.dashService.getRetentionGraphByChildTerritoryListAndPositionCode(in, item.getName());
					List<RetentionGraphDTO> innerList = this.dashService.getRetentionGraphByParentTerritoryListAndPositionCode(in, item.getName());
					ChartData chartD = new ChartData(filter, total);
					for(RetentionGraphDTO a: innerList){
						if((type.equals("BC") && filter.equals(BC) && BC.length() ==2) || type.equals("Executive")){
							chartD.addData(new ChartData(a.getChildTerritory(), a.getPercentage()));
						}
					}
					if(innerListTotal.size() > 0){
						total = innerListTotal.get(0).getPercentage();
					}
					chartD.setValue(total);
					total = 0;
					// with out b =0; java skips the next line
					int b = 0;
					//IF BC Level, ADD BC, IF NOT DON"T
					/*if(type.equals("BC") && filter.equals(BC)){
						item.addData(chartD);
					}else if(type.equals("Executive")){
						item.addData(chartD);
					}*/
					item.addData(chartD);
					b = 1;
				}
				if(item.getName().equals("09")){
					item.setName("Service Managers");
				}else if(item.getName().equals("14")){
					item.setName("Parts Advisors");
				}else if(item.getName().equals("23")){
					item.setName("Service Technicians");
				}else if(item.getName().equals("13")){
					item.setName("Service Advisors");
				}else if(item.getName().equals("42")){
					item.setName("Sales Consultants");
				}else if(item.getName().equals("04")){
					item.setName("Sales Managers");
				}else if(item.getName().equals("08")){
					item.setName("Parts Managers");
				}
			}

			chart.setData(attributes);
			chart.setUnit("%");
			chart.setRetention(true);
			chart.setAvarage(true);
			return chart;
		}
		case "32":
		{
			List<String> filters = new ArrayList<String>(Arrays.asList("NAT"));
			List<CustomerFirstGraphDTO> listBC = this.dashService.getCustomerFirstGraphByParentTerritoryAndToggle(filters, "Total");

			filters = new ArrayList<String>();

			for(CustomerFirstGraphDTO item: listBC){
				if(type.equals("BC") && BC.length() ==2){
					//get users BC
					if(item.getChildTerritory().contains(BC)){
						filters.add(item.getChildTerritory());
					}
				}else{
					filters.add(item.getChildTerritory());
				}
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
				if(type.equals("BC") && BC.length() ==2){
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


			Chart chart = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completed QTD", "", "Total Pillars", "Pillars QTD", "column_stack");
			Chart chart2 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completed QTD", "", "Total Pillars", "Pillars QTD", "column_stack");
			Chart chart3 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completed QTD", "", "Total Pillars", "Pillars QTD", "column_stack");
			Chart chart4 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completed QTD", "", "Total Pillars", "Pillars QTD", "column_stack");
			Chart chart5 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completed QTD", "", "Total Pillars", "Pillars QTD", "column_stack");
			Chart chart6 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completed QTD", "", "Total Pillars", "Pillars QTD", "column_stack");
			Chart chart7 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completed QTD", "", "Total Pillars", "Pillars QTD", "column_stack");

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
						temp.setName(object.getChildTerritory());
						temp.setValue(object.getCFAFEAwardCertification());
						list7.add(temp);

					}
					chartsMap.put(item.getChildTerritory(), list);
					chartsMap2.put(item.getChildTerritory(), list2);
					chartsMap3.put(item.getChildTerritory(), list3);
					chartsMap4.put(item.getChildTerritory(), list4);
					chartsMap5.put(item.getChildTerritory(), list5);
					chartsMap6.put(item.getChildTerritory(), list6);
					chartsMap7.put(item.getChildTerritory(), list7);
					mapValues.put(item.getChildTerritory(), item.getNoCertification());
					mapValues2.put(item.getChildTerritory(), item.getPerformance());
					mapValues3.put(item.getChildTerritory(), item.getProcess());
					mapValues4.put(item.getChildTerritory(), item.getVoiceofEmployee());
					mapValues5.put(item.getChildTerritory(), item.getTraining());
					mapValues6.put(item.getChildTerritory(), item.getFacility());
					mapValues7.put(item.getChildTerritory(), item.getCFAFEAwardCertification());
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
				item.addDataList(chartsMap3.get(item.getName()));
			}


			for(ChartData item: d){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Voice of Employee"))
						tempd+= var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues4.get(item.getName()));
				item.addDataList(chartsMap4.get(item.getName()));
			}


			for(ChartData item: e){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Training"))
						tempe += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues5.get(item.getName()));
				item.addDataList( chartsMap5.get(item.getName()));
			}

			for(ChartData item: f){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Facility"))
						tempf += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues6.get(item.getName()));
				item.addDataList( chartsMap6.get(item.getName()));
			}


			for(ChartData item: g){
				for(ChartData var: item.getData()){
					if(var.getName().equals("CFAFE Award Certification"))
						tempg += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues7.get(item.getName()));
				item.addDataList( chartsMap7.get(item.getName()));
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
				if(type.equals("BC") && BC.length() ==2){
					//get users BC
					if(item.getChildTerritory().contains(BC)){
						filters.add(item.getChildTerritory());
					}
				}else{
					filters.add(item.getChildTerritory());
				}
			}

			List<CustomerFirstGraphDTO> listBC_unfiltered = this.dashService.getCustomerFirstGraphByParentTerritoryAndToggle(filters, "Percentage");

			Map<String, List<String>> map = new HashMap<String, List<String>>();
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap2 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap3 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap4 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap5 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap6 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap7 = new HashMap<String, List<ChartData>>();
			for(CustomerFirstGraphDTO item: listBC_unfiltered){
				//filters.add(item.getChildTerritory());
				if(!map.containsKey(item.getParentTerritory())){
					map.put(item.getParentTerritory(), new ArrayList<String>());
				}
				List<String> temp = map.get(item.getParentTerritory());
				if(type.equals("BC") && BC.length() ==2){
					//get users BC
					if(item.getChildTerritory().contains(BC)){
						temp.add(item.getChildTerritory());
					}
				}else{
					temp.add(item.getChildTerritory());
				}
				map.put(item.getParentTerritory(), temp);
			}


			Chart chart = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completion Percentage", "", "", "Completion %", "column_stack");
			Chart chart2 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completion Percentage", "", "", "Completion %", "column_stack");
			Chart chart3 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completion Percentage", "", "", "Completion %", "column_stack");
			Chart chart4 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completion Percentage", "", "", "Completion %", "column_stack");
			Chart chart5 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completion Percentage", "", "", "Completion %", "column_stack");
			Chart chart6 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completion Percentage", "", "", "Completion %", "column_stack");
			Chart chart7 = this.mappingService.CustomerFirstGraphDTOtoChart(listBC, "Customer First Pillars Completion Percentage", "", "", "Completion %", "column_stack");

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
					chartsMap7.put(item.getChildTerritory(), list7);
					mapValues.put(item.getChildTerritory(), item.getNoCertification());
					mapValues2.put(item.getChildTerritory(), item.getPerformance());
					mapValues3.put(item.getChildTerritory(), item.getProcess());
					mapValues4.put(item.getChildTerritory(), item.getVoiceofEmployee());
					mapValues5.put(item.getChildTerritory(), item.getTraining());
					mapValues6.put(item.getChildTerritory(), item.getFacility());
					mapValues7.put(item.getChildTerritory(), item.getCFAFEAwardCertification());
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
				item.setValue(mapValues4.get(item.getName()));
				item.addDataList(chartsMap4.get(item.getName()));
			}


			for(ChartData item: e){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Training"))
						tempe += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues5.get(item.getName()));
				item.addDataList( chartsMap5.get(item.getName()));
			}

			for(ChartData item: f){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Facility"))
						tempf += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues6.get(item.getName()));
				item.addDataList( chartsMap6.get(item.getName()));
			}


			for(ChartData item: g){
				for(ChartData var: item.getData()){
					if(var.getName().equals("CFAFE Award Certification"))
						tempg += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues7.get(item.getName()));
				item.addDataList( chartsMap7.get(item.getName()));
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
			chart.setAvarage(true);
			chart.setUnit("%");
			chart.setRetention(true);
			return chart;

			//special mapping for stacked column
		}
		case "34":
		{
			List<String> filters = new ArrayList<String>(Arrays.asList("NAT"));
			List<TTTAEnrolledGraphDTO> listBC = this.dashService.getTTTAEnrolledByParentTerritory(filters);
			filters = new ArrayList<String>();
			for(TTTAEnrolledGraphDTO item: listBC){
				if(type.equals("BC") && BC.length() ==2){
					//get users BC
					if(item.getChild().contains(BC)){
						filters.add(item.getChild());
					}
				}else{
					filters.add(item.getChild());
				}
			}

			List<TTTAEnrolledGraphDTO> sublist = this.dashService.getTTTAEnrolledByParentTerritory(filters);
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap2 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap3 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap4 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap5 = new HashMap<String, List<ChartData>>();

			Chart chart = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Total Dealers Enrolled", "", "# of  Dealers", "Total Enrolled", "column_stack");
			Chart chart2 = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Total Dealers Enrolled", "", "# of  Dealers", "Total Enrolled", "column_stack");
			Chart chart3 = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Total Dealers Enrolled", "", "# of  Dealers", "Total Enrolled", "column_stack");
			Chart chart4 = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Total Dealers Enrolled", "", "# of  Dealers", "Total Enrolled", "column_stack");
			Chart chart5 = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Total Dealers Enrolled", "", "# of  Dealers", "Total Enrolled", "column_stack");

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
			list1.add(new ChartData("Group A", 0));
			list1.add(new ChartData("Group B", 0));
			list1.add(new ChartData("Group C", 0)); 
			list1.add(new ChartData("Group D", 0)); 
			list1.add(new ChartData("Group E", 0));

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
					if(var.getName().equals("Group A"))
						tempa += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues.get(item.getName()));
				item.addDataList(chartsMap.get(item.getName()));
			}

			for(ChartData item: b){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Group B"))
						tempb += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues2.get(item.getName()));
				item.addDataList(chartsMap2.get(item.getName()));
			}


			for(ChartData item: c){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Group C"))
						tempc += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues3.get(item.getName()));
				item.addDataList( chartsMap3.get(item.getName()));
			}

			for(ChartData item: d){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Group D"))
						tempd += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues4.get(item.getName()));
				item.addDataList( chartsMap4.get(item.getName()));
			}

			for(ChartData item: e){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Group E"))
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
		case "35":
		{
			List<String> filters = new ArrayList<String>(Arrays.asList("NAT"));
			List<TTTAEnrolledGraphDTO> listBC = this.dashService.getTTTAEnrolledByParentTerritoryNotEnrolled(filters);
			filters = new ArrayList<String>();
			for(TTTAEnrolledGraphDTO item: listBC){
				if(type.equals("BC") && BC.length() ==2){
					//get users BC
					if(item.getChild().contains(BC)){
						filters.add(item.getChild());
					}
				}else{
					filters.add(item.getChild());
				}
			}

			List<TTTAEnrolledGraphDTO> sublist = this.dashService.getTTTAEnrolledByParentTerritoryNotEnrolled(filters);
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap2 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap3 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap4 = new HashMap<String, List<ChartData>>();
			Map<String, List<ChartData>> chartsMap5 = new HashMap<String, List<ChartData>>();

			Chart chart = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Total Dealers Not Enrolled", "", "# of  Dealers", "Total Not Enrolled", "column_stack");
			Chart chart2 = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Total Dealers Not Enrolled", "", "# of  Dealers", "Total Not Enrolled", "column_stack");
			Chart chart3 = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Total Dealers Not Enrolled", "", "# of  Dealers", "Total Not Enrolled", "column_stack");
			Chart chart4 = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Total Dealers Not Enrolled", "", "# of  Dealers", "Total Not Enrolled", "column_stack");
			Chart chart5 = this.mappingService.TTTAEnrolledGraphDTOtoChart(listBC, "Total Dealers Not Enrolled", "", "# of  Dealers", "Total Not Enrolled", "column_stack");

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
			list1.add(new ChartData("Group A", 0));
			list1.add(new ChartData("Group B", 0));
			list1.add(new ChartData("Group C", 0)); 
			list1.add(new ChartData("Group D", 0)); 
			list1.add(new ChartData("Group E", 0));

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
					if(var.getName().equals("Group A"))
						tempa += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues.get(item.getName()));
				item.addDataList(chartsMap.get(item.getName()));
			}

			for(ChartData item: b){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Group B"))
						tempb += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues2.get(item.getName()));
				item.addDataList(chartsMap2.get(item.getName()));
			}


			for(ChartData item: c){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Group C"))
						tempc += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues3.get(item.getName()));
				item.addDataList( chartsMap3.get(item.getName()));
			}

			for(ChartData item: d){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Group D"))
						tempd += var.getValue();
				}
				item.setData(new ArrayList<ChartData>());
				item.setValue(mapValues4.get(item.getName()));
				item.addDataList( chartsMap4.get(item.getName()));
			}

			for(ChartData item: e){
				for(ChartData var: item.getData()){
					if(var.getName().equals("Group E"))
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
		case "36":
		{
			List<String> filters = new ArrayList<String>();

			filters.add("NAT");


			List<SummaryProgramRewardGraphDTO> list1st = null;
			
			List<SummaryProgramRewardQuartileGraphDTO> list1stQuartile = null;

			//check if nat or not if nat pull list of childeren and continue if not start from their
			list1st = this.dashService.getSummaryProgramRewardGraphByParentTerritoryYTD(filters);

			list1stQuartile = this.dashService.getSummaryProgramRewardQuartileGraphByParentTerritoryYTD(filters);
			
			
			
			filters = new ArrayList<String>();
			for(SummaryProgramRewardGraphDTO item: list1st){
				if(!filters.contains(item.getChild()))
					filters.add(item.getChild());
			}

			
			Chart chart = this.mappingService.SummaryProgramRewardGraphDTOtoChart(list1st, "Summary Dollar Earnings YTD", "", "", "Total Dollars Earned", "column");

			
			if(list1stQuartile.size() > 0){
				chart.setFirstLevelQuartile(list1stQuartile.get(0).getTopQuartile());
			}
			
			
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			Map<String, List<ChartData>> chartsMap = new HashMap<String, List<ChartData>>();
			for(SummaryProgramRewardGraphDTO item: list1st){
				if(!map.containsKey(item.getChild())){
					map.put(item.getChild(), new ArrayList<String>());
					chartsMap.put(item.getChild(), new ArrayList<ChartData>());
				}
				List<String> temp = map.get(item.getChild());
				if(type.equals("BC") && BC.length() ==2){
					//get users BC
					if(item.getChild().contains(BC)){
						temp.add(item.getChild());
					}
				}else{
					temp.add(item.getChild());
				}
				map.put(item.getParent(), temp);

			}
			List<SummaryProgramRewardGraphDTO> sublist = this.dashService.getSummaryProgramRewardGraphByParentTerritoryYTD(filters);
			List<SummaryProgramRewardQuartileGraphDTO> sublistQuartile = this.dashService.getSummaryProgramRewardQuartileGraphByParentTerritoryYTD(filters);
			Map<String, Double> mapQuartile = new HashMap<String, Double>();
			
			for(SummaryProgramRewardGraphDTO item: list1st){
				List<ChartData> list = new ArrayList<ChartData>();
				for(SummaryProgramRewardGraphDTO object: sublist){
					if(map.get(item.getChild()).contains(object.getParent())){
						ChartData data = new ChartData(object.getChild(), object.getEarnings());
						list.add(data);
					}
					chartsMap.put(item.getChild(), list);
				}
			}
			
			for(SummaryProgramRewardGraphDTO item: list1st){
				for(SummaryProgramRewardQuartileGraphDTO object: sublistQuartile){
					if(map.get(item.getChild()).contains(object.getParent())){
						mapQuartile.put(item.getChild(), object.getTopQuartile());
					}
				}
			}
			
			chart.setSecondLevelQuartile(mapQuartile);

			List<ChartData> a = chart.getData();
			for(ChartData item: a){
				item.addDataList(chartsMap.get(item.getName()));
			}
			chart.setUnit("$");
			//chart.setAverageLine(true);
			chart.setTopQuartile(true);
			return chart;
		}

		case "37":
		{
			TopTenChart topTenChart = new TopTenChart();
			TotalName mser = new TotalName("Total Number of Dealers Enrolled in MSER","0 (0.0%)");
			TotalName expresslane = new TotalName("Total Number of Express Lane Validated Dealers","0 (0.0%)");
			TotalName partsCounter = new TotalName("Total Number of Parts Counter Enrolled Dealers","0 (0.0%)");

			List<Integer> dealersenrolledcount = this.dashService.getTotalDealersEnrolledByProgramID(this.MSER_PROGRAM_ID);
			List<Integer> dealerscount = this.dashService.getTotalDealersByProgramID(this.MSER_PROGRAM_ID);
			DecimalFormat df = new DecimalFormat("0.0");
			if(dealerscount.size()>0 && dealersenrolledcount.size()>0){
				if(dealerscount.get(0) != 0){
					double percentage = ((double)dealersenrolledcount.get(0)/(double)dealerscount.get(0))*100;
					mser.setTotal(this.formatCurrency(dealersenrolledcount.get(0)) + " (" + df.format(percentage) +"%)");
				}
			}
			
			List<Integer> eldealersenrolledcount = this.dashService.getTotalELValidated();
			List<Integer> eldealercount = this.dashService.gettotalDealersEnrolledByProgramGroupID(1);
			if(eldealersenrolledcount.size()>0 && eldealercount.size()>0){
				if(eldealercount.get(0) != 0){
					double percentage = ((double)eldealercount.get(0)/(double)eldealersenrolledcount.get(0))*100;
					expresslane.setTotal(this.formatCurrency(eldealersenrolledcount.get(0)) + " (" + df.format(percentage) + "%)");
				}
			}

			List<Integer> partsCounterdealersenrolledcount = this.dashService.gettotalDealersEnrolledByProgramGroupID(6);
			if(dealersenrolledcount.size()>0 && partsCounterdealersenrolledcount.size()>0){
				if(dealersenrolledcount.get(0) != 0){
					double percentage = ((double)partsCounterdealersenrolledcount.get(0)/(double)dealersenrolledcount.get(0))*100;
					partsCounter.setTotal(this.formatCurrency(partsCounterdealersenrolledcount.get(0)) + " (" + df.format(percentage) +"%)");
				}
			}

			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(expresslane));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(partsCounter));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mser));

			return topTenChart;
		}

		default:
			return "No such service call exists.";
		}
	}



	@Cacheable("findTilesManager")
	public @ResponseBody Object findTilesManager(String id, String positionCode, String dealerCode, String user, String type) {
		String territory = "";
		if(type.equals("Participant")){
			territory = user.trim();
		}else if( type.equals("Manager") || type.equals("Dealer")){
			territory = dealerCode;
		}else if(type.equals("District")){
			List<String> bcSet = this.dashService.getUserTerritoyById(user);
			if(bcSet.size() > 0){
				territory = bcSet.get(0);
			}else{
				List<TIDUsersDTO> tids = TIDUsersDAO.getTIDUsersByTID(user);
				if(tids.size() > 0){
					for(TIDUsersDTO item: tids){
						territory = item.getTerritory();
					}
				}
			}
		}

		//divide the switch statement to functions
		switch(id){
		case "2":
		{
			TopTenChart topTenChart = new TopTenChart();
			if(type.equals("Participant")){

				TotalName mtd = new TotalName("Rewarding Excellence&reg; Card Awards MTD", "$0");//this.dashService.getParticipantExcellanceCardAwardMTD(territory, dealerCode);
				//mtd.setTotal("$" + this.formatCurrency(Double.parseDouble(mtd.getTotal())));
				
				TotalName ytd = new TotalName("Rewarding Excellence&reg; Card Awards YTD", "$0");
				//TotalName ytd = this.dashService.getParticipantExcellanceCardAwardYTD(territory, dealerCode);
				//ytd.setTotal("$" + this.formatCurrency(Double.parseDouble(ytd.getTotal())));

				List<MyfcaMSERTotalEarningsDetailsDTO> MyfcaMSERTotalEarningsDetailsDTO = this.dashService.getMSERGraphDetailsSUMBySID(territory, dealerCode);
				
				if(MyfcaMSERTotalEarningsDetailsDTO.size() > 0){
					mtd.setTotal("$" + this.formatCurrency(MyfcaMSERTotalEarningsDetailsDTO.get(0).getEarningsMTD()));
					ytd.setTotal("$" + this.formatCurrency(MyfcaMSERTotalEarningsDetailsDTO.get(0).getEarningsYTD()));
				}
				
				List<MyFCAMserRankingDetailsDTO> MSERDetailsDTOlist = this.dashService.getMSERDetailsBySID(territory, dealerCode);
				
				TotalName rank = new TotalName();
				rank.setName("Ranking within BC by Rewarding Excellence&reg; Card Awards MTD");
				rank.setTotal("0");
				if(MSERDetailsDTOlist.size()>0){
					rank.setTotal(this.formatNumbers(MSERDetailsDTOlist.get(0).getBCRank()));
				}
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(rank));
				

			}else if(type.equals("Dealer")){

				//MAKE ONEFO DEALER?
				TotalName mtd = new TotalName();
				mtd.setName("Rewarding Excellence&reg; Card Awards MTD");
				mtd.setTotal("$0");

				List<MyfcaMSERTotalEarningsDTO> mtdres = this.dashService.getMSERGraphProgramsSUMByChildTerritoryAndToggle(territory, "MTD");

				if(mtdres.size() > 0){
					mtd.setTotal("$" + this.formatCurrency(mtdres.get(0).getAmount()));
				}

				TotalName ytd = new TotalName();
				ytd.setName("Rewarding Excellence&reg; Card Awards YTD");
				ytd.setTotal("$0");

				List<MyfcaMSERTotalEarningsDTO> ytdres = this.dashService.getMSERGraphProgramsSUMByChildTerritoryAndToggle(territory, "YTD");

				if(ytdres.size() > 0){
					ytd.setTotal("$" + this.formatCurrency(ytdres.get(0).getAmount()));
				}

				TotalName dealerscount = new TotalName("Total Participants Enrolled","0");
				
				List<Integer> dealerscountlist = this.dashService.getTotalParticipantsEnrolledByProgramIDAndDealerCode(MSER_PROGRAM_ID, territory);
				if(dealerscountlist.size()>0){
					dealerscount.setTotal(this.formatCurrency(dealerscountlist.get(0)));
				}
				
				List<MyFCAMserRankingDTO> MSERDetailsGraphDTOlist = this.dashService.getMSERDetailsGraphByChild(territory);
				TotalName rank = new TotalName();
				rank.setName("Ranking within BC by Rewarding Excellence&reg; Card Awards MTD");
				rank.setTotal("0");
				if(MSERDetailsGraphDTOlist.size()>0){
					rank.setTotal(this.formatNumbers(MSERDetailsGraphDTOlist.get(0).getBCRank()));
				}
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(rank));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
				
			}else if(type.equals("Manager")){
				TotalName mtd = new TotalName("Rewarding Excellence&reg; Card Awards MTD", "$0");
				TotalName ytd = new TotalName("Rewarding Excellence&reg; Card Awards YTD", "$0");

				List<MyfcaMSERTotalEarningsDetailsDTO> MyfcaMSERTotalEarningsDetailsDTO = this.dashService.getMSERGraphDetailsSUMBySID(user, dealerCode);
				
				if(MyfcaMSERTotalEarningsDetailsDTO.size() > 0){
					mtd.setTotal("$" + this.formatCurrency(MyfcaMSERTotalEarningsDetailsDTO.get(0).getEarningsMTD()));
					ytd.setTotal("$" + this.formatCurrency(MyfcaMSERTotalEarningsDetailsDTO.get(0).getEarningsYTD()));
				}
				
				TotalName dealerscount = new TotalName("Total Participants Enrolled","0");
				
				List<Integer> dealerscountlist = this.dashService.getTotalParticipantsEnrolledByProgramIDAndDealerCode(MSER_PROGRAM_ID, territory);
				if(dealerscountlist.size()>0){
					dealerscount.setTotal(this.formatCurrency(dealerscountlist.get(0)));
				}
				List<MyFCAMserRankingDetailsDTO> MSERDetailsDTOlist = this.dashService.getMSERDetailsBySID(user.trim(), dealerCode);
				
				TotalName rank = new TotalName();
				rank.setName("Ranking within BC by Rewarding Excellence&reg; Card Awards MTD");
				rank.setTotal("0");
				if(MSERDetailsDTOlist.size()>0){
					rank.setTotal(this.formatNumbers(MSERDetailsDTOlist.get(0).getBCRank()));
				}

				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(rank));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
				
			}else if(type.equals("District")){

				
				TotalName mtd = new TotalName();
				mtd.setName("Rewarding Excellence&reg; Card Awards MTD");
				mtd.setTotal("$0");

				List<MyfcaMSERTotalEarningsDTO> mtdres = this.dashService.getMSERGraphProgramsSUMByChildTerritoryAndToggle(territory, "MTD");

				if(mtdres.size() > 0){
					mtd.setTotal("$" + this.formatCurrency(mtdres.get(0).getAmount()));
				}

				

				TotalName ytd = new TotalName();
				ytd.setName("Rewarding Excellence&reg; Card Awards YTD");
				ytd.setTotal("$0");

				List<MyfcaMSERTotalEarningsDTO> ytdres = this.dashService.getMSERGraphProgramsSUMByChildTerritoryAndToggle(territory, "YTD");

				if(ytdres.size() > 0){
					ytd.setTotal("$" + this.formatCurrency(ytdres.get(0).getAmount()));
				}
				
				TotalName dealerscount = new TotalName("Total Dealers Enrolled","0");
				
				List<Integer> dealerscountlist = this.dashService.getTotalDealersEnrolledByProgramIDAndTerritory(MSER_PROGRAM_ID, territory);
				if(dealerscountlist.size()>0){
					dealerscount.setTotal(this.formatCurrency(dealerscountlist.get(0)));
				}

				List<MyFCAMserRankingDTO> MSERDetailsGraphDTOlist = this.dashService.getMSERDetailsGraphByChild(territory);
				TotalName rank = new TotalName();
				rank.setName("Ranking within BC by Rewarding Excellence&reg; Card Awards MTD");
				rank.setTotal("0");
				if(MSERDetailsGraphDTOlist.size()>0){
					rank.setTotal(this.formatNumbers(MSERDetailsGraphDTOlist.get(0).getBCRank()));
				}
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(mtd));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ytd));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(rank));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
				
			}
			return topTenChart;
		}
		case "9":
		{
			if(type.equalsIgnoreCase("district")){
				territory = territory.substring(0, 3) + "%";
			}
			List<BrainBoostWinndersGraphDTO> sublist = this.dashService.getBrainBoostWinndersGraphgetByChildTerritory(territory);

			Chart chart = new Chart();
			chart.setTitle("YTD Winners");
			chart.setSubTitle("");
			chart.setType("column");
			chart.setXaxisTitle("");
			chart.setYaxisTitle("Total Winners");

			List<ChartData> list = new ArrayList<ChartData>();
			for(BrainBoostWinndersGraphDTO item: sublist){
				ChartData chartData = new ChartData();	
				chartData.setName(item.getChildTerritory());
				chartData.setValue(item.getWinners());
				list.add(chartData);
			}
			chart.setData(list);
			return chart;
		}
		case "10":
		{
			if(type.equalsIgnoreCase("district")){
				territory = territory.substring(0, 3) + "%";
			}

			List<CertProfsExpertGraphDTO> sublist = this.dashService.getExpertPointsEarnedByChildTerritory(territory);

			Chart chart = new Chart();
			chart.setTitle("Expert Points Earned YTD");
			chart.setSubTitle("");
			chart.setType("column");
			chart.setXaxisTitle("");
			chart.setYaxisTitle("Total Points");

			List<ChartData> list = new ArrayList<ChartData>();

			for(CertProfsExpertGraphDTO item: sublist){
				ChartData chartData = new ChartData();
				chartData.setName(item.getChildTerritory());
				chartData.setValue(item.getTotalPoints());
				list.add(chartData);
			}

			chart.setData(list);

			return chart;
		}
		case "11":
		{	
			if(type.equalsIgnoreCase("district")){
				territory = territory.substring(0, 3) + "%";
			}
			// check for role, to know what data to display
			//List<CertProfsExpertGraphDTO> CertProfsExpertGraphDTOJEEP = this.dashService.getParticipantCompletedByProgramByChildTerritoryAndCertType(territory, "JEEP%");
			List<CertProfsExpertGraphDTO> CertProfsExpertGraphDTORAM = this.dashService.getParticipantCompletedByProgramByChildTerritoryAndCertType(territory, "RAM%");
			//List<CertProfsExpertGraphDTO> CertProfsExpertGraphDTOTECH = this.dashService.getParticipantCompletedByProgramByChildTerritoryAndCertType(territory, "TECH%");
			Chart chart = new Chart();
			chart.setTitle("Participants Completed By Program");
			chart.setSubTitle("");
			chart.setType("column_stack");
			chart.setXaxisTitle("Total Participants");
			chart.setYaxisTitle("");

			List<ChartData> list = new ArrayList<ChartData>();
			//list.add(new ChartData("JEEP", 0));
			list.add(new ChartData("RAM", 0));
			//list.add(new ChartData("TECH", 0));

			int ramTotal = 0;
			//int jeepTotal = 0;
			// techTotal = 0;

			/*for(CertProfsExpertGraphDTO item: CertProfsExpertGraphDTOJEEP){
				ChartData data = new ChartData();
				data.setName(item.getChildTerritory());
				data.setValue(item.getCert());
				jeepTotal += data.getValue();
				list.get(0).addData(data);
			}
			list.get(0).setValue(jeepTotal);
*/
			for(CertProfsExpertGraphDTO item: CertProfsExpertGraphDTORAM){
				ChartData data = new ChartData();
				data.setName(item.getChildTerritory());
				data.setValue(item.getCert());
				ramTotal += data.getValue();
				//was 1 when jeep was in
				list.get(0).addData(data);
			}
			//was 1 when jeep was in
			list.get(0).setValue(ramTotal);

			/*for(CertProfsExpertGraphDTO item: CertProfsExpertGraphDTOTECH){
				ChartData data = new ChartData();
				data.setName(item.getChildTerritory());
				data.setValue(item.getCert());
				techTotal += data.getValue();
				list.get(2).addData(data);
			}
			list.get(2).setValue(techTotal);*/
			chart.setData(list);

			return chart;

		}
		case "12":
		{
			if(type.equalsIgnoreCase("district")){
				territory = territory.substring(0, 3) + "%";
			}

			List<BrainBoostWinndersGraphDTO> BrainBoostWinndersGraphDTO = this.dashService.getBrainBoostWinndersGraphgetByChildTerritory(territory);

			Chart chart = new Chart();
			chart.setTitle("Award Points YTD");
			chart.setSubTitle("");
			chart.setType("column");
			chart.setXaxisTitle("Award Points");
			chart.setYaxisTitle("Total Points");

			//List<ChartData> list = new ArrayList<ChartData>();

			ChartData awardPoints = new ChartData();
			awardPoints.setName("Award Points");

			//int awardPointsTotal = 0;

			for(BrainBoostWinndersGraphDTO item: BrainBoostWinndersGraphDTO){
				ChartData chartData = new ChartData();
				chartData.setName(item.getChildTerritory());
				chartData.setValue(item.getPoints());
			//	awardPointsTotal += item.getPoints();
				awardPoints.addData(chartData);
			}
			//awardPoints.setValue(awardPointsTotal);
			//list.add(awardPoints);

			chart.setData(awardPoints.getData());

			return chart;
		}
		case "13":
		{

			Chart chart = new Chart();
			chart.setTitle("Certifications in the Prior Year");
			chart.setSubTitle("");
			chart.setType("column_stack");
			chart.setXaxisTitle("Total Certifications");
			chart.setYaxisTitle("");

			if(type.equalsIgnoreCase("district")){
				territory = territory.substring(0, 3) + "%";
			}

			// check for role, to know what data to display
			List<CertProfsWinnersGraphDTO> CertProfsWinnersGraphDTO = this.dashService.getCertProfsWinnersGraphByChildTerritory(territory);


			List<ChartData> list = new ArrayList<ChartData>();

			//ChartData chartData = new ChartData();
			ChartData certified = new ChartData();
			ChartData certifiedSpacialist = new ChartData();
			ChartData masterCertified = new ChartData();

			int totalCertified = 0;
			int totalCertifiedSpacialist = 0;
			int totalMasterCertified =  0;

			certified.setName("Certified");
			certifiedSpacialist.setName("Certified Specialist");
			masterCertified.setName("Master Certified");

			for(CertProfsWinnersGraphDTO item: CertProfsWinnersGraphDTO){	
				//chartData.setName(CertProfsWinnersGraphDTO.get(0).getChildTerritory());

				ChartData certifiedInner = new ChartData();
				certifiedInner.setName(item.getChildTerritory());
				certifiedInner.setValue(Double.parseDouble(item.getCertified()));
				totalCertified += (int)Double.parseDouble(item.getCertified());
				certified.addData(certifiedInner);


				ChartData certifiedSpacialistInner = new ChartData();
				certifiedSpacialistInner.setName(item.getChildTerritory());
				certifiedSpacialistInner.setValue(Double.parseDouble(item.getCertifiedSpecialist()));
				totalCertifiedSpacialist += (int)Double.parseDouble(item.getCertifiedSpecialist());
				certifiedSpacialist.addData(certifiedSpacialistInner);


				ChartData masterCertifiedInner = new ChartData();
				masterCertifiedInner.setName(item.getChildTerritory());
				masterCertifiedInner.setValue(Double.parseDouble(item.getMasterCertified()));
				totalMasterCertified += (int)Double.parseDouble(item.getMasterCertified());
				masterCertified.addData(masterCertifiedInner);


				//chartData.addData(certified);
				//chartData.addData(certifiedSpacialist);
				//chartData.addData(masterCertified);
				//chartData.setValue(certified.getValue() + certifiedSpacialist.getValue() + masterCertified.getValue());
				//chartData.setName(CertProfsWinnersGraphDTO.get(0).getChildTerritory());

				//list.add(chartData);
				//chart.setData(list);
			}

			certified.setValue(totalCertified);
			certifiedSpacialist.setValue(totalCertifiedSpacialist);
			masterCertified.setValue(totalMasterCertified);

			list.add(certified);
			list.add(certifiedSpacialist);
			list.add(masterCertified);

			/*if(CertProfsWinnersGraphDTO.size() > 0){	
				//chartData.setName(CertProfsWinnersGraphDTO.get(0).getChildTerritory());

				certified.setName("Certified");
				certified.setValue(Double.parseDouble(CertProfsWinnersGraphDTO.get(0).getCertified()));

				ChartData certifiedInner = new ChartData();
				certifiedInner.setName(territory);
				certifiedInner.setValue(Double.parseDouble(CertProfsWinnersGraphDTO.get(0).getCertified()));
				certified.addData(certifiedInner);

				certifiedSpacialist.setName("Certified Spacialist");
				certifiedSpacialist.setValue(Double.parseDouble(CertProfsWinnersGraphDTO.get(0).getCertifiedSpecalist()));

				ChartData certifiedSpacialistInner = new ChartData();
				certifiedSpacialistInner.setName(territory);
				certifiedSpacialistInner.setValue(Double.parseDouble(CertProfsWinnersGraphDTO.get(0).getCertifiedSpecalist()));
				certifiedSpacialist.addData(certifiedSpacialistInner);

				masterCertified.setName("Master Certified");
				masterCertified.setValue(Double.parseDouble(CertProfsWinnersGraphDTO.get(0).getMasterCertified()));
				ChartData masterCertifiedInner = new ChartData();
				masterCertifiedInner.setName(territory);
				masterCertifiedInner.setValue(Double.parseDouble(CertProfsWinnersGraphDTO.get(0).getMasterCertified()));
				masterCertified.addData(masterCertifiedInner);


				//chartData.addData(certified);
				//chartData.addData(certifiedSpacialist);
				//chartData.addData(masterCertified);
				//chartData.setValue(certified.getValue() + certifiedSpacialist.getValue() + masterCertified.getValue());
				//chartData.setName(CertProfsWinnersGraphDTO.get(0).getChildTerritory());
				list.add(certified);
				list.add(certifiedSpacialist);
				list.add(masterCertified);
				//list.add(chartData);
				//chart.setData(list);
			}*/

			chart.setData(list);		

			return chart;
		}
		case "14":
		{
			TopTenChart topTenChart = new TopTenChart();
			if(type.equals("District")){
				String query = territory + "TA10";
				//set datatables
				List<TTTATopNDTO> listAdvisorsQTD = this.dashService.getTTTATopN(query, 10);
				//List<TTTATopNDTO> listAdvisorsYTD = this.dashService.getTTTATopN(query, 10);


				//TopTenDataTable datatableT = new TopTenDataTable("TOP 10 Technicians"); //, "Rewarding Excellence&reg; Card Awards - Top 5 Technicians"
				TopTenDataTable datatableA = new TopTenDataTable("TOP 10 Advisors QTD by Overall Survey Score");
				List<TopTenTableData> tabledataA = new ArrayList<TopTenTableData>();
				//List<TopTenTableData> tabledataT = new ArrayList<TopTenTableData>();

				List<String> tableheaders = new ArrayList<String>();
				//keeps this as example
				tableheaders.add("Name");
				tableheaders.add("Dealership");
				tableheaders.add("Business Center");
				tableheaders.add("Total Surveys");
				tableheaders.add("Survey Score");


				tabledataA.add(this.mappingService.MapTTTATopNDTOtoTopTenTableData(listAdvisorsQTD, "Top 10 Advisors QTD by Overall Survey Score", tableheaders));	
				//tabledataA.add(this.mappingService.MapTTTATopNDTOtoTopTenTableData(listAdvisorsYTD, "Top 10 Advisors YTD Average Survey Scores", tableheaders));
				tabledataA.get(0).setTabName("QTD");
				//tabledataA.get(1).setTabName("YTD");
				datatableA.setData(tabledataA);

				topTenChart.setTop10_advisors(datatableA);

				TotalName dealerscount = new TotalName("Total Dealers Enrolled","0");
				
				List<Integer> dealerscountlist = this.dashService.getTotalDealersEnrolledByProgramIDAndTerritory(TA_PROGRAM_ID, territory);
				
				if(dealerscountlist.size()>0){
					dealerscount.setTotal(this.formatCurrency(dealerscountlist.get(0)));
				}

				List<TTTAEnrollmentsSummaryDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsSummaryByChildAndPositionCode(territory, "13");

				TotalName incentiveEligible  = new TotalName();
				incentiveEligible.setName("Total Advisors Incentive Eligible This Quarter");
				incentiveEligible.setTotal("0");

				TotalName qtdSurveyCount  = new TotalName();
				qtdSurveyCount.setName("QTD Survey Count");
				qtdSurveyCount.setTotal("0");

				TotalName avgSurveyCount  = new TotalName();
				avgSurveyCount.setName("Average Quarterly Survey Score");
				avgSurveyCount.setTotal("0.0%");
				DecimalFormat df = new DecimalFormat("0.0");
				if(incentiveEligibleList.size()>0){
					incentiveEligible.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getIncentiveEligible()));
					qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
					avgSurveyCount.setTotal(df.format(incentiveEligibleList.get(0).getAvgSurveyScore()) + "%");
				}

				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgSurveyCount));

			}

			if(type.equals("Manager") || type.equals("Dealer")){

				//List<String> filters = new ArrayList<String>(Arrays.asList(territory));
				
				if(!this.dashService.checkDealerEnrollmentByProgram(this.TA_PROGRAM_ID, dealerCode)){
					return topTenChart;
				}
				
				List<TTTAEnrollmentsSummaryDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsSummaryByChildAndPositionCode(territory, "13");


				TotalName enrolled  = new TotalName();
				enrolled.setName("Total Advisors Enrolled");
				enrolled.setTotal("0");


				TotalName incentiveEligible  = new TotalName();
				incentiveEligible.setName("Total Advisors Incentive Eligible This Quarter");
				incentiveEligible.setTotal("0");

				TotalName qtdSurveyCount  = new TotalName();
				qtdSurveyCount.setName("QTD Survey Count");
				qtdSurveyCount.setTotal("0");

				TotalName avgSurveyCount  = new TotalName();
				avgSurveyCount.setName("Average Quarterly Survey Score");
				avgSurveyCount.setTotal("0.0%");

				TotalName rank  = new TotalName();
				rank.setName("Dealership BC Rank Based on Quarterly Survey Score");
				rank.setTotal("0");
				DecimalFormat df = new DecimalFormat("0.0");
				if(incentiveEligibleList.size()>0){
					enrolled.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getTotalEnrollments()));
					incentiveEligible.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getIncentiveEligible()));
					qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
					avgSurveyCount.setTotal(df.format(incentiveEligibleList.get(0).getAvgSurveyScore()) + "%");
					rank.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getTTTARank()));
				}
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(rank));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(enrolled));
				
			}
			else if (type.equals("Participant")){

				List<TTTAEnrollmentsDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsBySID(territory, "13", dealerCode);
				//survey score
				//survey count


				TotalName incentiveEligible  = new TotalName();
				incentiveEligible.setName("Incentive Eligible");
				incentiveEligible.setTotal("No");

				TotalName qtdSurveyCount  = new TotalName();
				qtdSurveyCount.setName("QTD Survey Count");
				qtdSurveyCount.setTotal("0");

				TotalName avgSurveyCount  = new TotalName();
				avgSurveyCount.setName("QTD Survey Score");
				avgSurveyCount.setTotal("0.0%");

				TotalName rank  = new TotalName();
				rank.setName("Advisor National Rank QTD");
				rank.setTotal("0");

				if(incentiveEligibleList.size() > 0){

					if(incentiveEligibleList.get(0).getIncentiveEligible()> 0){
						incentiveEligible.setTotal("Yes");
					}

					qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
					avgSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getAvgSurveyScore()) + "%");
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

			TopTenChart topTenChart = new TopTenChart();
			DecimalFormat df = new DecimalFormat("0.0");
			if(type.equals("District")){

				//set datatables
				String query = territory + "TT10";

				//set datatables
				List<TTTATopNDTO> listAdvisorsQTD = this.dashService.getTTTATopN(query, 10);
				//List<TTTATopNDTO> listAdvisorsYTD = this.dashService.getTTTATopN(query, 10);


				TopTenDataTable datatableT = new TopTenDataTable("TOP 10 Technicians QTD by Overall Survey Score"); //, "Rewarding Excellence&reg; Card Awards - Top 5 Technicians"
				List<TopTenTableData> tabledataT = new ArrayList<TopTenTableData>();

				List<String> tableheaders = new ArrayList<String>();
				tableheaders.add("Name");
				tableheaders.add("Dealership");
				tableheaders.add("Business Center");
				tableheaders.add("Total Surveys");
				tableheaders.add("Survey Score");

				//tabledataT.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listTechnicians, "Top 10 MTD Technicians Rewarding Excellence&reg; Card Awards", tableheaders));
				//tabledataT.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listTechnicians, "Top 10 YTD Technicians Rewarding Excellence&reg; Card Awards", tableheaders));

				//datatableT.setData(tabledataT);

				tabledataT.add(this.mappingService.MapTTTATopNDTOtoTopTenTableData(listAdvisorsQTD, "Top 10 Technicians QTD by Overall Survey Score", tableheaders));	
				//tabledataT.add(this.mappingService.MapTTTATopNDTOtoTopTenTableData(listAdvisorsYTD, "Top 10 Technicians YTD Average Survey Scores", tableheaders));
				tabledataT.get(0).setTabName("QTD");
				//tabledataT.get(1).setTabName("YTD");
				datatableT.setData(tabledataT);

				topTenChart.setTop10_technicians(datatableT);

				TotalName dealerscount = new TotalName("Total Dealers Enrolled","0");
				
				List<Integer> dealerscountlist = this.dashService.getTotalDealersEnrolledByProgramIDAndTerritory(TA_PROGRAM_ID, territory);
				
				if(dealerscountlist.size()>0){
					dealerscount.setTotal(this.formatCurrency(dealerscountlist.get(0)));
				}

				List<TTTAEnrollmentsSummaryDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsSummaryByChildAndPositionCode(territory, "23");


				TotalName incentiveEligible  = new TotalName();
				incentiveEligible.setName("Total Technicians Incentive Eligible This Quarter");
				incentiveEligible.setTotal("0");

				TotalName qtdSurveyCount  = new TotalName();
				qtdSurveyCount.setName("QTD Survey Count");
				qtdSurveyCount.setTotal("0");

				TotalName avgSurveyCount  = new TotalName();
				avgSurveyCount.setName("Average Quarterly Survey Score");
				avgSurveyCount.setTotal("0.0%");

				TotalName avgYearsOfService  = new TotalName();
				avgYearsOfService.setName("Average Years of Service for Technicians");
				avgYearsOfService.setTotal("0");


				if(incentiveEligibleList.size()>0){			
					incentiveEligible.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getIncentiveEligible()));
					qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
					avgSurveyCount.setTotal(df.format(incentiveEligibleList.get(0).getAvgSurveyScore()) + "%");
					avgYearsOfService.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getYearsOfService()));
				}

				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerscount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgYearsOfService));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));

			}

			if(type.equals("Manager") || type.equals("Dealer")){

				if(!this.dashService.checkDealerEnrollmentByProgram(this.TT_PROGRAM_ID, dealerCode)){
					return topTenChart;
				}
				//TODO: //GET DEALER ENROLLED FROM PROGRAM enrollementS
				//
				//
				//
				//
				//
				//
				List<TTTAEnrollmentsSummaryDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsSummaryByChildAndPositionCode(territory, "23");

				TotalName incentiveEligible  = new TotalName();
				incentiveEligible.setName("Total Technicians Incentive Eligible This Quarter");
				incentiveEligible.setTotal("0");

				TotalName qtdSurveyCount  = new TotalName();
				qtdSurveyCount.setName("QTD Survey Count");
				qtdSurveyCount.setTotal("0");

				TotalName avgSurveyCount  = new TotalName();
				avgSurveyCount.setName("Average Quarterly Survey Score");
				avgSurveyCount.setTotal("0.0%");

				TotalName avgYearsOfService  = new TotalName();
				avgYearsOfService.setName("Average Years of Service for Technicians");
				avgYearsOfService.setTotal("0");

				TotalName rank  = new TotalName();
				rank.setName("Dealership BC Rank Based on Quarterly Survey Score");
				rank.setTotal("0");

				TotalName enrolled  = new TotalName();
				enrolled.setName("Total Technicians Enrolled");
				enrolled.setTotal("0");
				
				if(incentiveEligibleList.size()>0){
					incentiveEligible.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getIncentiveEligible()));
					qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
					avgSurveyCount.setTotal(df.format(incentiveEligibleList.get(0).getAvgSurveyScore()) + "%");
					rank.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getTTTARank()));
					enrolled.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getTotalEnrollments()));
					avgYearsOfService.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getYearsOfService()));
				}
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(rank));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgSurveyCount));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(enrolled));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgYearsOfService));
			}
			else if (type.equals("Participant")){

				List<TTTAEnrollmentsDTO> incentiveEligibleList = this.dashService.getTTTAEnrollmentsBySID(territory, "23", dealerCode);
				//survey score
				//survey count

				TotalName incentiveEligible  = new TotalName();
				incentiveEligible.setName("Number of level 3");
				incentiveEligible.setTotal("0");

				TotalName qtdSurveyCount  = new TotalName();
				qtdSurveyCount.setName("QTD Survey Count");
				qtdSurveyCount.setTotal("0");

				TotalName avgSurveyCount  = new TotalName();
				avgSurveyCount.setName("QTD Survey Score");
				avgSurveyCount.setTotal("0.0%");

				TotalName rank  = new TotalName();
				rank.setName("Technician National Rank QTD");
				rank.setTotal("0");

				if(incentiveEligibleList.size() > 0){
					incentiveEligible.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getLevel3Techs()));
					qtdSurveyCount.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getSurveyCount()));
					avgSurveyCount.setTotal(df.format(incentiveEligibleList.get(0).getAvgSurveyScore()) + "%");
					rank.setTotal(this.formatNumbers(incentiveEligibleList.get(0).getPartcipantRank()));

					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSurveyCount));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(avgSurveyCount));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(rank));
				}
			}



			return topTenChart;
		}
		case "19":
		{
			if(type.equals("Manager") || type.equals("Dealer")){
				territory = dealerCode;
			}else if(type.equals("Participant")){
				territory = user.trim();
			}else if(type.equals("District")){
				territory = territory.substring(0, 2);
			}


			List<ChartData> attributes = new ArrayList<ChartData>();

			if(type.equals("District")){

				List<MyfcaMSERTotalEarningsDTO> listOfFirstLevel = this.dashService.getMSERGraphByChildTerritoryAndToggle(territory+ "-%", "YTD");

				List<String> programs = this.dashService.getMSERGraphDistinctProgramsByParentTerritoryAndToggle(territory, "YTD");

				for(String programName: programs){
					attributes.add(new ChartData(programName, 0));
				}

				List<String> filters = new ArrayList<String>();
				for(MyfcaMSERTotalEarningsDTO item: listOfFirstLevel){
					if(item.getChild() != null && !filters.contains(item.getChild()))
						filters.add(item.getChild());
				}

				for(ChartData item: attributes){
					double total = 0;
					double grandTotal = 0;
					for(String filter: filters){
						List<MyfcaMSERTotalEarningsDTO> innerList = this.dashService.getMSERGraphByTerritoryAndToggleAndProgram(filter, "YTD", item.getName());
						ChartData chartD = new ChartData(filter, total);
						for(MyfcaMSERTotalEarningsDTO a: innerList){
							total += a.getAmount();
						}
						chartD.setValue(total);
						grandTotal = grandTotal + total;
						total = 0;
						// with out b =0; java skips the next line
						int b = 0;
						item.addData(chartD);
						b = 1;
					}
					item.setValue(grandTotal);
				}
			}else{

				List<MyfcaMSERTotalEarningsDTO> listOfFirstLevel = this.dashService.getMSERGraphByChildTerritoryAndToggle(territory, "YTD");

				for(MyfcaMSERTotalEarningsDTO item: listOfFirstLevel){
					ChartData temp = new ChartData(item.getProgram(), item.getAmount());
					temp.addData(new ChartData(item.getChild(), item.getAmount()));
					attributes.add(temp);
				}
			}
			//main chart model
			Chart chart = new Chart("Total Earnings YTD", "", "Total Earnings", "", "bar_compound");
			chart.setData(attributes);
			chart.setUnit("$");
			return chart;
		}
		case "20":
		{
			List<SIRewardsYOYGraphDTO> SIRewardsYOYGraphDTO = new ArrayList<SIRewardsYOYGraphDTO>();
			if(type.equalsIgnoreCase("district")){
				SIRewardsYOYGraphDTO = this.dashService.getSIRewardsYOYGraphByTerritoryAndToggle(territory.substring(0, 2), "YTD");
			}else{
				SIRewardsYOYGraphDTO = this.dashService.getSIRewardsYOYGraphByChildAndToggle(territory, "YTD");
			}

			Chart chart = new Chart();
			chart.setTitle("Service Incentive Reward Earnings YTD");
			chart.setSubTitle("");
			chart.setType("column_compound");
			chart.setXaxisTitle("Total Dollars Earned");
			chart.setYaxisTitle("");

			List<ChartData> list = new ArrayList<ChartData>();

			ChartData excellenceCard = new ChartData();
			ChartData awardPoints = new ChartData();
			
			String currentYear = new DateTime().getYear() + "";
			String lastYear = (new DateTime().getYear() -1) + "";
			
			excellenceCard.setName(lastYear);
			awardPoints.setName(currentYear);
			int excellenceCardTotal = 0;
			int awardPointsTotal = 0;

			for(SIRewardsYOYGraphDTO item: SIRewardsYOYGraphDTO){
				ChartData chartData = new ChartData();
				chartData.setName(item.getChildTerritory());
				chartData.setValue(item.getLastYearEarnings());
				excellenceCardTotal += item.getLastYearEarnings();
				excellenceCard.addData(chartData);

				ChartData chartData2 = new ChartData();
				chartData2.setName(item.getChildTerritory());
				chartData2.setValue(item.getCurrentYearEarnings());
				awardPointsTotal += item.getCurrentYearEarnings();
				awardPoints.addData(chartData2);
			}
			excellenceCard.setValue(excellenceCardTotal);
			awardPoints.setValue(awardPointsTotal);
			list.add(excellenceCard);
			list.add(awardPoints);

			chart.setData(list);
			chart.setUnit("$");
			return chart;
		}
		case "21":
		{
			List<SIRewardsDetailsGraphDTO> sublist = new ArrayList<SIRewardsDetailsGraphDTO>();
			if(type.equalsIgnoreCase("district")){
				sublist = this.dashService.getSIRewardsDetailsGraphByTerritoryAndToggle(territory.substring(0, 2) , "QTD");
			}else{
				sublist = this.dashService.getSIRewardsDetailsGraphByChildTerritoryAndToggle(territory, "QTD");
			}

			Chart chart = new Chart();
			chart.setTitle("Average Quarterly Survey Scores QTD");
			chart.setSubTitle("");
			chart.setType("column");
			chart.setXaxisTitle("");
			chart.setYaxisTitle("Average Survey Scores");

			List<ChartData> list = new ArrayList<ChartData>();
			for(SIRewardsDetailsGraphDTO item: sublist){
				ChartData chartData = new ChartData();	
				chartData.setName(item.getChildTerritory());
				chartData.setValue(item.getAvgSurveyScore());
				list.add(chartData);
			}
			chart.setAvarage(true);
			chart.setData(list);
			return chart;
		}
		case "22":
		{
			List<SIRewardsDetailsGraphDTO> sublist = new ArrayList<SIRewardsDetailsGraphDTO>();
			if(type.equalsIgnoreCase("district")){
				sublist = this.dashService.getSIRewardsDetailsGraphByTerritoryAndToggle(territory.substring(0, 2), "QTD");
			}else{
				sublist = this.dashService.getSIRewardsDetailsGraphByChildTerritoryAndToggle(territory, "QTD");
			}

			Chart chart = new Chart();
			chart.setTitle("Projected Service Incentive Earnings QTD");
			chart.setSubTitle("");
			chart.setType("column");
			chart.setXaxisTitle("");
			chart.setYaxisTitle("Projected Earnings");

			List<ChartData> list = new ArrayList<ChartData>();
			for(SIRewardsDetailsGraphDTO item: sublist){
				ChartData chartData = new ChartData();	
				chartData.setName(item.getChildTerritory());
				chartData.setValue(item.getProjectedEarnings());
				list.add(chartData);
			}
			chart.setUnit("$");
			chart.setData(list);
			return chart;
		}
		case "23":
		{
			if(type.equalsIgnoreCase("district")){
				territory = territory.substring(0, 3) + "%";
			}

			List<RewardRedemptionGraphDTO> listOfFirstLevel = this.dashService.getRewardRedemptionGraphByChildTerritory(territory);

			List<ChartData> attributes = new ArrayList<ChartData>();

			for(RewardRedemptionGraphDTO item: listOfFirstLevel){
				ChartData temp = new ChartData(item.getChildTerritory(), item.getEarnedPoints());
				attributes.add(temp);
			}

			Chart chart = new Chart("Total Program Points Earned YTD", "", "Total Points Earned", "", "column");
			chart.setData(attributes);
			return chart;
		}
		case "24":
		{
			TopTenChart topTenChart = new TopTenChart();

			TotalName bal = new TotalName("Total Current Point Balance","0");
			TotalName redeemed = new TotalName("Total Points Redeemed YTD","0");
			TotalName earned = new TotalName("Total Redemption Points Awarded YTD","0");
			TotalName ccp = new TotalName("Total Certified Professionals Points YTD","0");
			TotalName ttta = new TotalName("Total Top Tech/Top Advisor Points YTD","0");
			
			if(type.equals("Participant") || type.equals("Manager")){
				List<RewardRedemptionDetailsDTO> RewardRedemptionDetails = this.dashService.getRewardRedemptionDetailsBySid(user.trim(), dealerCode);
				List<RewardRedemptionDetailsDTO> RewardRedemptionDetailsCCP = this.dashService.getRewardRedemptionDetailsCCPBySid(user.trim(), dealerCode);
				List<RewardRedemptionDetailsDTO> RewardRedemptionDetailsTTTA = this.dashService.getRewardRedemptionDetailsTTTABySid(user.trim(), dealerCode);
				
				if(RewardRedemptionDetails.size() > 0){
					double balance = RewardRedemptionDetails.get(0).getEarnedPoints() - RewardRedemptionDetails.get(0).getRedeemedPoints();
					if(balance < 0){
						balance =0;
					}
					bal.setTotal(this.formatNumbers(balance));
					earned.setTotal(this.formatNumbers(RewardRedemptionDetails.get(0).getEarnedPoints()));
					redeemed.setTotal(this.formatNumbers(RewardRedemptionDetails.get(0).getRedeemedPoints()));
				}
				if(RewardRedemptionDetailsCCP.size() > 0){
					ccp.setTotal(this.formatNumbers(RewardRedemptionDetailsCCP.get(0).getEarnedPoints()));
				}
				if(RewardRedemptionDetailsTTTA.size() > 0){
					ttta.setTotal(this.formatNumbers(RewardRedemptionDetailsTTTA.get(0).getEarnedPoints()));
				}
			}else if (type.equals("Dealer")){
				List<RewardRedemptionGraphDTO> RewardRedemptionDetails = this.dashService.getRewardRedemptionGraphByChildTerritory(dealerCode);
				List<RewardRedemptionDetailsDTO> RewardRedemptionDetailsCCP = this.dashService.getRewardRedemptionDetailsCCPByDealer(dealerCode);
				List<RewardRedemptionDetailsDTO> RewardRedemptionDetailsTTTA = this.dashService.getRewardRedemptionDetailsTTTAByDealer(dealerCode);
				if(RewardRedemptionDetails.size() > 0){
					double balance = RewardRedemptionDetails.get(0).getEarnedPoints() - RewardRedemptionDetails.get(0).getRedeemedPoints();
					if(balance < 0){
						balance =0;
					}
					bal.setTotal(this.formatNumbers(balance));
					earned.setTotal(this.formatNumbers(RewardRedemptionDetails.get(0).getEarnedPoints()));
					redeemed.setTotal(this.formatNumbers(RewardRedemptionDetails.get(0).getRedeemedPoints()));
				}
				if(RewardRedemptionDetailsCCP.size() > 0){
					ccp.setTotal(this.formatNumbers(RewardRedemptionDetailsCCP.get(0).getEarnedPoints()));
				}
				if(RewardRedemptionDetailsTTTA.size() > 0){
					ttta.setTotal(this.formatNumbers(RewardRedemptionDetailsTTTA.get(0).getEarnedPoints()));
				}
			}
			
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(earned));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(bal));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(redeemed));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ccp));
			topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(ttta));
			
			return topTenChart;
		}
		case "25":
		{
			//check if dealer or not if dealer use dealer if not use sid // need a case for manager
			String sid = user.trim();

			TopTenChart topTenChart = new TopTenChart();

			TotalName years = new TotalName();
			//TotalName cartificationLevel = new TotalName();
			TotalName cartificationLevelPrior = new TotalName();
			TotalName totalCertifiedParticipants = new TotalName();
			TotalName totalMasterCertifiedParticipants = new TotalName();
			TotalName totalCertifiedSpecialistParticipants = new TotalName();
			TotalName totalCertifiedLevelParticipants = new TotalName();
			TotalName dealershipRankWithinBC = new TotalName();
			TotalName dealershipNationalRank = new TotalName();
			TotalName dealershipMasterCertifiedRankWithinBC = new TotalName();
			TotalName dealershipMasterCertifiedNationalRank = new TotalName();
			TotalName currentYearsPoints = new TotalName();

			List<CertProfsWinnersDetailsDTO> CertProfsWinnersDetailsList = this.dashService.getCertProfsWinnersDetailsBySID(sid, dealerCode);

				if(type.equals("Participant") || type.equals("Manager")){
					years.setName("Years Certified");
					years.setTotal("0");
					
					currentYearsPoints.setName("Current Years Program Points");
					currentYearsPoints.setTotal("0");
					
					cartificationLevelPrior.setName("Not Certified");
					cartificationLevelPrior.setTotal("No Record to Display");
					boolean displayNotCertified = true;
					if(CertProfsWinnersDetailsList.size() > 0){
						CertProfsWinnersDetailsDTO CertProfsWinnersDetails = CertProfsWinnersDetailsList.get(0);
						
						if(CertProfsWinnersDetails.getPriorYearCertLevel().equalsIgnoreCase("MASTER CERTIFIED")){
							
							/*cartificationLevelPrior.setTotal(CertProfsWinnersDetails.getMasterCertified() + "");
							TileAttribute1 tile = this.mappingService.MapTotalNameToTileAttribute(cartificationLevelPrior);
							tile.setBadgeTitle("");
							tile.setBadgeUrl("mastercertified.jpg");
							topTenChart.addAttribute(tile);*/
							cartificationLevelPrior.setName(CertProfsWinnersDetails.getPriorYearCertLevel());
							cartificationLevelPrior.setTotal("Yes");
							topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevelPrior));
							displayNotCertified = false;
						}else if(CertProfsWinnersDetails.getPriorYearCertLevel().equalsIgnoreCase("CERTIFIED SPECIALIST")){
							cartificationLevelPrior.setName("Certified Specialist");
							cartificationLevelPrior.setTotal("Yes");
							//cartificationLevelPrior.setTotal(CertProfsWinnersDetails.getCertifiedSpecialist() + "");
							TileAttribute1 tile = this.mappingService.MapTotalNameToTileAttribute(cartificationLevelPrior);
							//tile.setBadgeTitle("");
							//tile.setBadgeUrl("certifiedspecialist.jpg");
							topTenChart.addAttribute(tile);
							displayNotCertified = false;
						}else if(CertProfsWinnersDetails.getPriorYearCertLevel().equalsIgnoreCase("CERTIFIED")){
							cartificationLevelPrior.setName("Certified");
							cartificationLevelPrior.setTotal("Yes");
							//cartificationLevelPrior.setTotal(CertProfsWinnersDetails.getCertified() + "");
							TileAttribute1 tile = this.mappingService.MapTotalNameToTileAttribute(cartificationLevelPrior);
							//tile.setBadgeTitle("");
							//tile.setBadgeUrl("cert-pro-badges.jpg");
							topTenChart.addAttribute(tile);
							displayNotCertified = false;
						}else{
							cartificationLevelPrior.setName("Not Certified");
							cartificationLevelPrior.setTotal("No Record to Display");
							displayNotCertified = false;
							topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevelPrior));
						}
						years.setTotal(CertProfsWinnersDetails.getYearsOfCertified()+ "");
						currentYearsPoints.setTotal(this.formatNumbers(CertProfsWinnersDetails.getPoints()));
					}
					if(displayNotCertified){
						topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevelPrior));
					}
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(currentYearsPoints));
					if(type.equals("Participant")){
						topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
					}
				}

				if(type.equals("Dealer") || type.equals("Manager")){
					//List<String> list = new ArrayList<String>();
					//list.add(dealerCode);
					List<CertProfsWinnersGraphDTO> CertProfsWinnersGraphDTOList = this.dashService.getCertProfsWinnersGraphByChildTerritory(dealerCode);

					totalCertifiedParticipants.setName("Total Certified Participants");
					totalMasterCertifiedParticipants.setName("Total Master Certified Level Participants");
					totalCertifiedSpecialistParticipants.setName("Total Certified Specialist Level Participants");
					totalCertifiedLevelParticipants.setName("Total Certified Level Participants");



					totalCertifiedParticipants.setTotal("0");
					totalMasterCertifiedParticipants.setTotal("0");
					totalCertifiedSpecialistParticipants.setTotal("0");
					totalCertifiedLevelParticipants.setTotal("0");

					if(CertProfsWinnersGraphDTOList.size() >0){
						CertProfsWinnersGraphDTO CertProfsWinnersGraphDTO = CertProfsWinnersGraphDTOList.get(0);
						totalCertifiedParticipants.setTotal(CertProfsWinnersGraphDTO.getTotalCertified() + "");
						totalMasterCertifiedParticipants.setTotal(CertProfsWinnersGraphDTO.getMasterCertified() + "");
						totalCertifiedSpecialistParticipants.setTotal(CertProfsWinnersGraphDTO.getCertifiedSpecialist() + "");
						totalCertifiedLevelParticipants.setTotal(CertProfsWinnersGraphDTO.getCertified() + "");
					}

					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedLevelParticipants));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
					
					

					if(type.equals("Dealer")){
						dealershipMasterCertifiedRankWithinBC.setName("Dealership Master Certified BC Rank");
						dealershipMasterCertifiedNationalRank.setName("Dealership Master Certified National Rank");
						dealershipRankWithinBC.setName("Dealership BC Rank for Total Participants Receiving Certification");
						dealershipNationalRank.setName("Dealership National Rank for Total Participants Receiving Certification");

						dealershipMasterCertifiedRankWithinBC.setTotal("-");
						dealershipMasterCertifiedNationalRank.setTotal("-");
						dealershipRankWithinBC.setTotal("-");
						dealershipNationalRank.setTotal("-");

						if(CertProfsWinnersGraphDTOList.size() >0){
							CertProfsWinnersGraphDTO CertProfsWinnersGraphDTO = CertProfsWinnersGraphDTOList.get(0);
							dealershipMasterCertifiedRankWithinBC.setTotal(CertProfsWinnersGraphDTO.getMasterCertBCCertRank() + "");
							dealershipMasterCertifiedNationalRank.setTotal(CertProfsWinnersGraphDTO.getMasterCertNATCertRank() + "");						
							dealershipRankWithinBC.setTotal(CertProfsWinnersGraphDTO.getAllBCCertRank() + "");
							dealershipNationalRank.setTotal(CertProfsWinnersGraphDTO.getAllNATCertRank() + "");
						}

						topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedRankWithinBC));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedNationalRank));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipRankWithinBC));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipNationalRank));
				}

			}
			return topTenChart;
		}
		case "26":
		{
			//check if dealer or not if dealer use dealer if not use sid // need a case for manager
			String sid = user;
			TopTenChart topTenChart = new TopTenChart();

			//TotalName years = new TotalName();
			//TotalName cartificationLevel = new TotalName();
			TotalName totalCertifiedParticipants = new TotalName();
			TotalName totalMasterCertifiedParticipants = new TotalName();
			TotalName totalCertifiedSpecialistParticipants = new TotalName();
			TotalName totalCertifiedLevelParticipants = new TotalName();
			TotalName dealershipMasterCertifiedRankWithinBC = new TotalName();

			List<BrainBoostWinnersDetailsDTO> BrainBoostWinnersDetailsList = this.dashService.getBrainBoostWinnersDetailsDTOBySID(sid, "YTD", dealerCode);

			if(type.equals("Dealer") || type.equals("Manager")){
				List<String> list = new ArrayList<String>();
				list.add(dealerCode);
				List<BrainBoostWinndersGraphDTO> BrainBoostWinndersGraphList = this.dashService.getBrainBoostWinndersGraphgetByChildTerritory(list);
				List<BrainBoostWinnersDetailsDTO> BrainBoostWinnersDetailsDTOListMTD = this.dashService.getBrainBoostWinnersDetailsDTOSUMByDealerCode(dealerCode, "MTD");

				totalCertifiedParticipants.setName("Total Participants Last Month");
				totalCertifiedParticipants.setTotal(0 + "");

				totalMasterCertifiedParticipants.setName("Total Winners Last Month");
				totalMasterCertifiedParticipants.setTotal(0 + "");


				totalCertifiedSpecialistParticipants.setName("Total Winners YTD");
				totalCertifiedSpecialistParticipants.setTotal(0 + "");

				totalCertifiedLevelParticipants.setName("Total Rewarding Excellence&reg; Card Awards YTD");
				totalCertifiedLevelParticipants.setTotal(0 + "");

				dealershipMasterCertifiedRankWithinBC.setName("Total Award Points Earned YTD ");
				dealershipMasterCertifiedRankWithinBC.setTotal(0 + "");

				if(BrainBoostWinndersGraphList.size() >0){

					BrainBoostWinndersGraphDTO BrainBoostWinndersGraphDTO = BrainBoostWinndersGraphList.get(0);

					if(BrainBoostWinnersDetailsDTOListMTD.size()>0){

						BrainBoostWinnersDetailsDTO BrainBoostWinnersDetailsDTO = BrainBoostWinnersDetailsDTOListMTD.get(0);
						totalCertifiedParticipants.setTotal(BrainBoostWinnersDetailsDTO.getPartcipants() + "");

						totalMasterCertifiedParticipants.setName("Total Winners Last Month");
						totalMasterCertifiedParticipants.setTotal(BrainBoostWinnersDetailsDTO.getWinners() + "");


					}

					totalCertifiedSpecialistParticipants.setName("Total Winners YTD");
					totalCertifiedSpecialistParticipants.setTotal(BrainBoostWinndersGraphDTO.getWinners() + "");


					totalCertifiedLevelParticipants.setName("Total Rewarding Excellence&reg; Card Awards YTD");
					totalCertifiedLevelParticipants.setTotal(BrainBoostWinndersGraphDTO.getEarnings() + "");


					dealershipMasterCertifiedRankWithinBC.setName("Total Award Points Earned YTD ");
					dealershipMasterCertifiedRankWithinBC.setTotal(BrainBoostWinndersGraphDTO.getPoints() + "");

				}

				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
				//topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedLevelParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedRankWithinBC));

			}



			if(type.equals("Participant")){

				/*years.setName("Total Rewarding Excellence&reg; Card Awards YTD");
				years.setTotal(0+ "");				

				cartificationLevel.setName("Total Rewarding Excellence&reg; Card Awards YTD");
				cartificationLevel.setTotal(0+ "");*/
				
				totalCertifiedParticipants.setName("Total Award Points Earned YTD");
				totalCertifiedParticipants.setTotal(0 + "");


				if(BrainBoostWinnersDetailsList.size() > 0){
					BrainBoostWinnersDetailsDTO BrainBoostWinnersDetails = BrainBoostWinnersDetailsList.get(0);
					/*years.setTotal(BrainBoostWinnersDetails.getEarnings()+ "");
					cartificationLevel.setTotal(BrainBoostWinnersDetails.getEarnings()+ "");*/
					totalCertifiedParticipants.setTotal(BrainBoostWinnersDetails.getPoints() + "");
				}

				/*topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevel));*/
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));

			}

			return topTenChart;
		}
		case "27":
		{
			//check if dealer or not if dealer use dealer if not use sid // need a case for manager
			String sid = user;
			TopTenChart topTenChart = new TopTenChart();

			TotalName years = new TotalName();
			TotalName cartificationLevel = new TotalName();
			TotalName totalCertifiedParticipants = new TotalName();
			TotalName totalMasterCertifiedParticipants = new TotalName();
			TotalName totalCertifiedSpecialistParticipants = new TotalName();
			TotalName totalCertifiedLevelParticipants = new TotalName();
			TotalName dealershipMasterCertifiedRankWithinBC = new TotalName();

			List<BrainBoostWinnersDetailsDTO> BrainBoostWinnersDetailsList = this.dashService.getBrainBoostWinnersDetailsDTOBySID(sid, "YTD", dealerCode);

			if(type.equals("Dealer") || type.equals("Manager")){
				List<String> list = new ArrayList<String>();
				list.add(dealerCode);
				List<BrainBoostWinndersGraphDTO> BrainBoostWinndersGraphList = this.dashService.getBrainBoostWinndersGraphgetByChildTerritory(list);
				List<BrainBoostWinnersDetailsDTO> BrainBoostWinnersDetailsDTOListMTD = this.dashService.getBrainBoostWinnersDetailsDTOSUMByDealerCode(dealerCode, "MTD");

				totalCertifiedParticipants.setName("Total Participants MTD");
				totalCertifiedParticipants.setTotal("0");
				totalMasterCertifiedParticipants.setName("Total Winners MTD");
				totalMasterCertifiedParticipants.setTotal("0");
				totalCertifiedSpecialistParticipants.setName("Total Winners YTD");
				totalCertifiedSpecialistParticipants.setTotal("0");
				totalCertifiedLevelParticipants.setName("Total Rewarding Excellence&reg; Card Awards YTD");
				totalCertifiedLevelParticipants.setTotal("0");
				dealershipMasterCertifiedRankWithinBC.setName("Total Award Points Earned YTD ");
				dealershipMasterCertifiedRankWithinBC.setTotal("0");

				if(BrainBoostWinndersGraphList.size() >0){
					BrainBoostWinndersGraphDTO BrainBoostWinndersGraphDTO = BrainBoostWinndersGraphList.get(0);
					if(BrainBoostWinnersDetailsDTOListMTD.size()>0){
						BrainBoostWinnersDetailsDTO BrainBoostWinnersDetailsDTO = BrainBoostWinnersDetailsDTOListMTD.get(0);
						totalCertifiedParticipants.setTotal(this.formatNumbers(BrainBoostWinnersDetailsDTO.getPartcipants()));
						totalMasterCertifiedParticipants.setTotal(this.formatNumbers(BrainBoostWinnersDetailsDTO.getWinners()));

					}
					totalCertifiedSpecialistParticipants.setTotal(this.formatNumbers(BrainBoostWinndersGraphDTO.getWinners()));
					totalCertifiedLevelParticipants.setTotal(this.formatNumbers(BrainBoostWinndersGraphDTO.getEarnings()));					
					dealershipMasterCertifiedRankWithinBC.setTotal(this.formatNumbers(BrainBoostWinndersGraphDTO.getPoints()));
				}

				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedLevelParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedRankWithinBC));

			}

			if(type.equals("Participant")){

				years.setName("Total Rewarding Excellence&reg; Card Awards YTD");
				years.setTotal("$0");

				cartificationLevel.setName("Total Rewarding Excellence&reg; Card Awards YTD");
				cartificationLevel.setTotal("$0");

				if(BrainBoostWinnersDetailsList.size() > 0){
					BrainBoostWinnersDetailsDTO BrainBoostWinnersDetails = BrainBoostWinnersDetailsList.get(0);
					years.setName("Total Rewarding Excellence&reg; Card Awards YTD");
					years.setTotal("$" + this.formatCurrency(BrainBoostWinnersDetails.getEarnings()));
					cartificationLevel.setName("Total Rewarding Excellence&reg; Card Awards YTD");
					cartificationLevel.setTotal("$" + this.formatCurrency(BrainBoostWinnersDetails.getEarnings()));
				}
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
			}

			return topTenChart;
		}
		case "28":
		{
			//check if dealer or not if dealer use dealer if not use sid // need a case for manager
			String sid = user;
			TopTenChart topTenChart = new TopTenChart();

			//TotalName years = new TotalName();
			TotalName cartificationLevel = new TotalName();
			TotalName totalCertifiedParticipants = new TotalName();
			TotalName totalMasterCertifiedParticipants = new TotalName();
			TotalName totalCertifiedSpecialistParticipants = new TotalName();
			TotalName totalCertifiedLevelParticipants = new TotalName();
			TotalName dealershipMasterCertifiedRankWithinBC = new TotalName();
			TotalName techExpert = new TotalName();

			if(type.equals("Dealer") || type.equals("Manager")){
				List<String> list = new ArrayList<String>();
				list.add(dealerCode);
				List<CertProfsExpertGraphDTO> CertProfsExpertGraphList = this.dashService.getExpertPointsEarnedByChildTerritory(list);
				List<CertProfsExpertGraphDTO> CertProfsExpertGraphJEEPList = this.dashService.getParticipantCompletedByProgramByChildTerritoryAndCertType(list, "JEEP%");
				List<CertProfsExpertGraphDTO> CertProfsExpertGraphRAMList = this.dashService.getParticipantCompletedByProgramByChildTerritoryAndCertType(list, "RAM%");
				List<CertProfsExpertGraphDTO> CertProfsExpertGraphTECHList = this.dashService.getParticipantCompletedByProgramByChildTerritoryAndCertType(list, "TECH%");

				totalCertifiedParticipants.setName("Total Participants Completed - JEEP");
				totalCertifiedParticipants.setTotal("0");

				if(CertProfsExpertGraphJEEPList.size() >0){
					CertProfsExpertGraphDTO CertProfsExpertGraphDTO = CertProfsExpertGraphJEEPList.get(0);
					totalCertifiedParticipants.setTotal(this.formatNumbers(CertProfsExpertGraphDTO.getCert()));
				}

				totalMasterCertifiedParticipants.setName("Total Participants Completed - RAM");
				totalMasterCertifiedParticipants.setTotal("0");

				if(CertProfsExpertGraphRAMList.size() >0){
					CertProfsExpertGraphDTO CertProfsExpertGraphDTO = CertProfsExpertGraphRAMList.get(0);
					totalMasterCertifiedParticipants.setTotal(this.formatNumbers(CertProfsExpertGraphDTO.getCert()));

				}

				techExpert.setName("Total Participants Completed - TECH");
				techExpert.setTotal("0");

				if(CertProfsExpertGraphTECHList.size() >0){
					CertProfsExpertGraphDTO CertProfsExpertGraphDTO = CertProfsExpertGraphTECHList.get(0);
					techExpert.setTotal(this.formatNumbers(CertProfsExpertGraphDTO.getCert()));
				}

				///topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
				//topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(techExpert));


				totalCertifiedSpecialistParticipants.setName("Total Experts Points Earned YTD");
				totalCertifiedSpecialistParticipants.setTotal("0");

				if(CertProfsExpertGraphList.size() >0){	
					CertProfsExpertGraphDTO CertProfsExpertGraphDTO = CertProfsExpertGraphList.get(0);
					totalCertifiedSpecialistParticipants.setTotal(this.formatNumbers(CertProfsExpertGraphDTO.getPoints()));
				}

				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));

				totalCertifiedLevelParticipants.setName("Dealership Rank in Business Center by Total Points Earned Across All Programs YTD");
				totalCertifiedLevelParticipants.setTotal("-");
				
				dealershipMasterCertifiedRankWithinBC.setName("Dealership National Rank by Total Points Earned Across All Programs YTD");
				dealershipMasterCertifiedRankWithinBC.setTotal("-");

				if(CertProfsExpertGraphRAMList.size() > 0){
					CertProfsExpertGraphDTO CertProfsExpertGraphDTO = CertProfsExpertGraphRAMList.get(0);
					totalCertifiedLevelParticipants.setTotal(this.formatNumbers(CertProfsExpertGraphDTO.getBCPointRank()));
					dealershipMasterCertifiedRankWithinBC.setTotal(this.formatNumbers(CertProfsExpertGraphDTO.getNATPointRank()));
				}else if (CertProfsExpertGraphJEEPList.size() > 0){
					CertProfsExpertGraphDTO CertProfsExpertGraphDTO = CertProfsExpertGraphJEEPList.get(0);
					totalCertifiedLevelParticipants.setTotal(this.formatNumbers(CertProfsExpertGraphDTO.getBCPointRank()));
					dealershipMasterCertifiedRankWithinBC.setTotal(this.formatNumbers(CertProfsExpertGraphDTO.getNATPointRank()));
				}else if (CertProfsExpertGraphTECHList.size() > 0){
					CertProfsExpertGraphDTO CertProfsExpertGraphDTO = CertProfsExpertGraphTECHList.get(0);
					totalCertifiedLevelParticipants.setTotal(this.formatNumbers(CertProfsExpertGraphDTO.getBCPointRank()));
					dealershipMasterCertifiedRankWithinBC.setTotal(this.formatNumbers(CertProfsExpertGraphDTO.getNATPointRank()));
				}
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedLevelParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedRankWithinBC));

			}

			if(type.equals("Participant")){

				List<CertProfsExpertDetailsDTO> CertProfsExpertDetailsList = this.dashService.getCertProfsExpertDetailsSUMBySID(sid, dealerCode);		
				List<CertProfsExpertDetailsDTO> CertProfsExpertDetailsRAMList = this.dashService.getCertProfsExpertDetailsBySIDANDCertType(sid, "RAM%", dealerCode);
				List<CertProfsExpertDetailsDTO> CertProfsExpertDetailsJEEPList = this.dashService.getCertProfsExpertDetailsBySIDANDCertType(sid, "JEEP%", dealerCode);
				List<CertProfsExpertDetailsDTO> CertProfsExpertDetailsTECHList = this.dashService.getCertProfsExpertDetailsBySIDANDCertType(sid, "TECH%", dealerCode);

				totalCertifiedSpecialistParticipants.setName("Total Experts Points Earned YTD");
				totalCertifiedSpecialistParticipants.setTotal(0 + "");
				cartificationLevel.setTotal("No");
				cartificationLevel.setName("Jeep Expert Completed");
				dealershipMasterCertifiedRankWithinBC.setTotal("No");
				dealershipMasterCertifiedRankWithinBC.setName("RAM Expert Completed");
				techExpert.setTotal("No");
				techExpert.setName("Technology Expert Completed");

				TileAttribute1 jeepTile = this.mappingService.MapTotalNameToTileAttribute(cartificationLevel); 
				TileAttribute1 ramTile = this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedRankWithinBC); 
				TileAttribute1 techTile = this.mappingService.MapTotalNameToTileAttribute(techExpert); 

				if(CertProfsExpertDetailsList.size()>0){
					CertProfsExpertDetailsDTO CertProfsExpertDetailsDTO = CertProfsExpertDetailsList.get(0);
					totalCertifiedSpecialistParticipants.setTotal(this.formatNumbers(CertProfsExpertDetailsDTO.getPoints()));
				}

				//set badge
				if(CertProfsExpertDetailsJEEPList.size()>0){
					CertProfsExpertDetailsDTO CertProfsExpertDetailsDTO = CertProfsExpertDetailsJEEPList.get(0);
					if(CertProfsExpertDetailsDTO.getCert() > 0){
						cartificationLevel.setTotal("Y");

						jeepTile.setBadgeTitle("Completed:Yes");
						jeepTile.setBadgeUrl("JeepExpert.jpg");

					}
				}

				if(CertProfsExpertDetailsRAMList.size()>0){
					CertProfsExpertDetailsDTO CertProfsExpertDetailsDTO = CertProfsExpertDetailsRAMList.get(0);
					if(CertProfsExpertDetailsDTO.getCert() > 0){
						dealershipMasterCertifiedRankWithinBC.setTotal("Y");

						ramTile.setBadgeTitle("Completed:Yes");
						ramTile.setBadgeUrl("RamExpert.jpg");
					}
				}

				if(CertProfsExpertDetailsTECHList.size()>0){
					CertProfsExpertDetailsDTO CertProfsExpertDetailsDTO = CertProfsExpertDetailsTECHList.get(0);
					if(CertProfsExpertDetailsDTO.getCert() > 0){
						dealershipMasterCertifiedRankWithinBC.setTotal("Y");

						techTile.setBadgeTitle("Completed:Yes");
						techTile.setBadgeUrl("TechnologyExpert.jpg");
					}
				}

				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
				//topTenChart.addAttribute(jeepTile);
				topTenChart.addAttribute(ramTile);
				//topTenChart.addAttribute(techTile);

			}
			return topTenChart;
		}
		case "29":
		{
			String sid = user.trim();
			TopTenChart topTenChart = new TopTenChart();

			TotalName eligibleSurveys = new TotalName("Eligible Surveys Returned with a Score of 9 or 10 QTD", "0");
			TotalName dealerAdvocacy = new TotalName("Dealer Target Advocacy Score","0.0%");
			TotalName trainingCompleted = new TotalName("Traning Completed", "No");
			TotalName advisorsIncentiveQualified = new TotalName("Advisors Incentive Qualified QTD", "0"); 
			TotalName incentiveEligible = new TotalName("Incentive Eligible", "No"); 
			TotalName projectedEarnings = new TotalName("Projected Earnings QTD", "$0");
			TotalName totalEarningsYTD = new TotalName("Total Earnings YTD ","$0");
			TotalName managersAndDirectorsQualified = new TotalName("Managers and Directors Qualified QTD","0");
			TotalName qtdSureveyScore = new TotalName("QTD Survey Score ", "0");
			TotalName dealerRank = new TotalName("Dealership Rank in Business Center Based on YTD Earnings", "-");
			TotalName managerRank = new TotalName("Manager Rank in Business Center Based on YTD Earnings", "-");
			TotalName advisorRank = new TotalName("Advisor Rank in Business Center Based on YTD Earnings", "-");
			

			//String qy = this.getCurrentQuarter();
			//String year = this.getCurrentYear();
			
			
			if(type.equals("Participant")){
				List<SIRewardsDetailsDTO> SIRewardsDetailsListQTD = this.dashService.getSIRewardsDetailsBySIDAndToggle(sid, "QTD", dealerCode, this.getCurrentQuarter());
				//List<SIRewardsDetailsDTO> SIRewardsDetailsListYTD = this.dashService.getSIRewardsDetailsBySIDAndToggle(sid, "YTD", dealerCode, this.getCurrentYear());
				List<SIRewardsYOYDetailsDTO> SIRewardsYOYDetailsDTOListYTD = this.dashService.getSIRewardsYOYDetailsBySIDAndToggle(sid, dealerCode, "YTD");
				
				if(SIRewardsDetailsListQTD.size()>0){
					SIRewardsDetailsDTO SIRewardsDetailsDTO = SIRewardsDetailsListQTD.get(0);

					eligibleSurveys.setTotal(this.formatCurrency(SIRewardsDetailsDTO.getEligibleSurveys()));
					if(SIRewardsDetailsDTO.getTrainingQualified()>0){
						trainingCompleted.setTotal("Yes");
					}
					if(SIRewardsDetailsDTO.getIncentiveQualified()>0){
						projectedEarnings.setTotal("$" + this.formatCurrency(SIRewardsDetailsDTO.getProjectedEarnings()));
						incentiveEligible.setTotal("Yes");
					}
					qtdSureveyScore.setTotal(this.formatCurrency(SIRewardsDetailsDTO.getSurveyScore()));
				}

				if(SIRewardsYOYDetailsDTOListYTD.size()>0){
					totalEarningsYTD.setTotal("$" + this.formatCurrency(SIRewardsYOYDetailsDTOListYTD.get(0).getCurrentYearEarnings()));
					advisorRank.setTotal(this.formatCurrency(SIRewardsYOYDetailsDTOListYTD.get(0).getBCRank()));
				}
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(eligibleSurveys));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(advisorRank));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(trainingCompleted));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(projectedEarnings));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalEarningsYTD));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(qtdSureveyScore));
			}else if(type.equals("Dealer") || type.equals("Manager")){
				DecimalFormat df = new DecimalFormat("0.0");
				List<String> filters = new ArrayList<String>();
				filters.add(dealerCode);
				List<SIRewardsDetailsGraphDTO> SIRewardsDetailsGraphList = this.dashService.getSIRewardsDetailsGraphByChildTerritoryAndToggle(filters, "QTD");
				List<SIRewardsYOYGraphDTO> SIRewardsYOYGraphDTOYTD = this.dashService.getSIRewardsYOYGraphByChildAndToggle(dealerCode, "YTD");
				List<SIRewardsDetailsDTO> SIRewardsDetailsListQTDDealer = this.dashService.getSIRewardsDetailsByDealerCodeAndToggle(dealerCode,"QTD", this.getCurrentQuarter());
				if(SIRewardsDetailsGraphList.size()>0){
					eligibleSurveys.setTotal(this.formatCurrency(SIRewardsDetailsGraphList.get(0).getEligibleSurveys()));
					advisorsIncentiveQualified.setTotal(this.formatCurrency(SIRewardsDetailsGraphList.get(0).getAdvsiorsQualified()));
					managersAndDirectorsQualified.setTotal(this.formatCurrency(SIRewardsDetailsGraphList.get(0).getManagersQualified()));
				}
				if(type.equals("Dealer")){
					if(SIRewardsDetailsGraphList.size() > 0){
						SIRewardsDetailsGraphDTO SIRewardsDetailsGraphDTO = SIRewardsDetailsGraphList.get(0);
						projectedEarnings.setTotal("$" + this.formatCurrency(SIRewardsDetailsGraphDTO.getProjectedEarnings()));
					}
					
					if(SIRewardsDetailsListQTDDealer.size() > 0){
						SIRewardsDetailsDTO SIRewardsDetailsDTO = SIRewardsDetailsListQTDDealer.get(0);
						dealerAdvocacy.setTotal(df.format(SIRewardsDetailsDTO.getDealerTarget()) + "%");
					}
					
					if(SIRewardsYOYGraphDTOYTD.size() > 0){
						SIRewardsYOYGraphDTO SIRewardsYOYGraphDTO = SIRewardsYOYGraphDTOYTD.get(0);
						totalEarningsYTD.setTotal("$" + this.formatCurrency(SIRewardsYOYGraphDTO.getCurrentYearEarnings()));
						dealerRank.setTotal(this.formatCurrency(SIRewardsYOYGraphDTO.getBCDealerRank()));
					}
					
				}else if(type.equals("Manager")){
					List<SIRewardsDetailsDTO> SIRewardsDetailsListQTD = this.dashService.getSIRewardsDetailsBySIDAndToggle(sid, "QTD", dealerCode, this.getCurrentQuarter());
					//List<SIRewardsDetailsDTO> SIRewardsDetailsListYTD = this.dashService.getSIRewardsDetailsBySIDAndToggle(sid, "YTD", dealerCode, this.getCurrentYear());
					List<SIRewardsYOYDetailsDTO> SIRewardsYOYDetailsDTOListYTD = this.dashService.getSIRewardsYOYDetailsBySIDAndToggle(sid, dealerCode, "YTD");
					if(SIRewardsDetailsListQTD.size()>0){
						SIRewardsDetailsDTO SIRewardsDetailsDTO = SIRewardsDetailsListQTD.get(0);
						if(SIRewardsDetailsDTO.getIncentiveQualified() > 0){
							incentiveEligible.setTotal("Yes");
							projectedEarnings.setTotal("$" + this.formatCurrency(SIRewardsDetailsDTO.getProjectedEarnings()));
							dealerAdvocacy.setTotal(df.format(SIRewardsDetailsDTO.getDealerTarget())+ "%");
						}
						if(SIRewardsDetailsDTO.getTrainingQualified()>0){
							trainingCompleted.setTotal("Yes");
						}
					}
					
					if(SIRewardsYOYDetailsDTOListYTD.size()>0){
						managerRank.setTotal(this.formatCurrency(SIRewardsYOYDetailsDTOListYTD.get(0).getBCRank()));
						totalEarningsYTD.setTotal("$" + this.formatCurrency(SIRewardsYOYDetailsDTOListYTD.get(0).getCurrentYearEarnings()));
					}

					if(SIRewardsDetailsListQTDDealer.size()>0){
						TopTenDataTable datatableA = new TopTenDataTable("Advisor Performance");
						List<TopTenTableData> tabledataA = new ArrayList<TopTenTableData>();
						List<String> tableheaders = new ArrayList<String>();
						//keeps this as example
						tableheaders.add("Name");
						tableheaders.add("# QTD Surveys");
						tableheaders.add("Individual Advocacy Score");
						tableheaders.add("Training Y/N");
						tableheaders.add("Incentive Eligible");
						tableheaders.add("Projected Earnings QTD");
						tabledataA.add(this.mappingService.MapSIRewardsDetailsDTOtoTopTenTableData(SIRewardsDetailsListQTDDealer, "Advisor Performance", tableheaders));
						tabledataA.get(0).setTabName("Advisor Performance");
						datatableA.setData(tabledataA);
						topTenChart.setTop10_advisors(datatableA);
					}

					
				}
				
				if(type.equals("Manager")){
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(eligibleSurveys));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(managerRank));	
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(trainingCompleted));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(incentiveEligible));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(projectedEarnings));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalEarningsYTD));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerAdvocacy));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(advisorsIncentiveQualified));
				}
				if(type.equalsIgnoreCase("dealer")){
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(eligibleSurveys));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerRank));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(managersAndDirectorsQualified));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(projectedEarnings));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalEarningsYTD));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealerAdvocacy));
					topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(advisorsIncentiveQualified));
				}
			}else if(type.equals("District")){
				String typeSA = territory + " SIR TA 10";
				List<MyfcaMSERTopNDTO> listAdvisors = this.dashService.getTopNByTypeINMSERTABLE(typeSA, 10, null, "QTD");
				List<MyfcaMSERTopNDTO> listAdvisorsYTD = this.dashService.getTopNByTypeINMSERTABLE(typeSA, 10, null, "YTD");

				TopTenDataTable datatableA = new TopTenDataTable("Top 10 Advisors Countrywide Based on Earnings");
				List<TopTenTableData> tabledataA = new ArrayList<TopTenTableData>();

				List<String> tableheaders = new ArrayList<String>();
				//keeps this as example
				tableheaders.add("Name");
				tableheaders.add("Dealership");
				tableheaders.add("Business Center");
				tableheaders.add("Earnings");

				tabledataA.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listAdvisorsYTD, "Top 10 Advisors Countrywide Based on YTD Earnings", tableheaders, "top10"));
				tabledataA.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listAdvisors, "Top 10 Advisors Countrywide Based on QTD Earnings", tableheaders, "top10"));
				tabledataA.get(0).setTabName("YTD");
				tabledataA.get(1).setTabName("QTD");

				datatableA.setData(tabledataA);

				topTenChart.setTop10_advisors(datatableA);

				List<String> filters = new ArrayList<String>();
				filters.add(territory);
				List<SIRewardsDetailsGraphDTO> SIRewardsDetailsGraphList = this.dashService.getSIRewardsDetailsGraphByChildTerritoryAndToggle(filters, "QTD");
				List<SIRewardsYOYGraphDTO> SIRewardsYOYGraphDTO = this.dashService.getSIRewardsYOYGraphByChildAndToggle(territory, "YTD");
				if(SIRewardsDetailsGraphList.size() > 0){
					SIRewardsDetailsGraphDTO SIRewardsDetailsGraphDTO = SIRewardsDetailsGraphList.get(0);
					eligibleSurveys.setTotal(this.formatCurrency(SIRewardsDetailsGraphDTO.getEligibleSurveys()));
					projectedEarnings.setTotal("$" + this.formatCurrency(SIRewardsDetailsGraphDTO.getProjectedEarnings()));
					advisorsIncentiveQualified.setTotal(this.formatCurrency(SIRewardsDetailsGraphDTO.getAdvsiorsQualified()));
					managersAndDirectorsQualified.setTotal(this.formatCurrency(SIRewardsDetailsGraphDTO.getManagersQualified()));
				}

				if(SIRewardsYOYGraphDTO.size() > 0){
					totalEarningsYTD.setTotal("$" + this.formatCurrency(SIRewardsYOYGraphDTO.get(0).getCurrentYearEarnings()));
				}
				
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(projectedEarnings));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalEarningsYTD));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(advisorsIncentiveQualified));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(managersAndDirectorsQualified));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(eligibleSurveys));
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
				cartificationLevel.setName("Rolling 12-Month Retention Percentage for Service Managers");
				cartificationLevel.setTotal("0.0%");

				years.setName("Rolling 12-Month Retention Percentage for Service Advisors");
				years.setTotal("0.0%");

				totalCertifiedParticipants.setName("Rolling 12-Month Retention Percentage for Parts Managers");
				totalCertifiedParticipants.setTotal("0.0%");

				totalMasterCertifiedParticipants.setName("Rolling 12-Month Retention Percentage for Parts Advisors");
				totalMasterCertifiedParticipants.setTotal("0.0%");

				totalCertifiedSpecialistParticipants.setName("Rolling 12-Month Retention Percentage for Service Technicians");
				totalCertifiedSpecialistParticipants.setTotal("0.0%");

				totalCertifiedLevelParticipants.setName("Rolling 12-Month Retention Percentage for Sales Managers");
				totalCertifiedLevelParticipants.setTotal("0.0%");


				dealershipMasterCertifiedRankWithinBC.setName("Rolling 12-Month Retention Percentage for Sales Consultants");
				dealershipMasterCertifiedRankWithinBC.setTotal("0.0%");

				DecimalFormat df = new DecimalFormat("0.0");
				if(RetentionGraphServiceManagerList.size()>0){
					cartificationLevel.setName("Rolling 12-Month Retention Percentage for Service Managers");
					cartificationLevel.setTotal(df.format(RetentionGraphServiceManagerList.get(0).getPercentage()) + "%");
				}

				if(RetentionGraphServiceAdvisorList.size()>0){
					years.setName("Rolling 12-Month Retention Percentage for Service Advisors");
					years.setTotal(df.format(RetentionGraphServiceAdvisorList.get(0).getPercentage()) + "%");
				}

				if(RetentionGraphPartsManagerList.size()>0){
					totalCertifiedParticipants.setName("Rolling 12-Month Retention Percentage for Parts Managers");
					totalCertifiedParticipants.setTotal(df.format(RetentionGraphPartsManagerList.get(0).getPercentage()) + "%");
				}

				if(RetentionGraphPartsAdvisorList.size()>0){
					totalMasterCertifiedParticipants.setName("Rolling 12-Month Retention Percentage for Parts Advisors");
					totalMasterCertifiedParticipants.setTotal(df.format(RetentionGraphPartsAdvisorList.get(0).getPercentage()) + "%");
				}


				if(RetentionGraphServiceTechnicianList.size()>0){
					totalCertifiedSpecialistParticipants.setName("Rolling 12-Month Retention Percentage for Service Technicians");
					totalCertifiedSpecialistParticipants.setTotal(df.format(RetentionGraphServiceTechnicianList.get(0).getPercentage()) + "%");
				}


				if(RetentionGraphSalesManagerList.size()>0){
					totalCertifiedLevelParticipants.setName("Rolling 12-Month Retention Percentage for Sales Managers");
					totalCertifiedLevelParticipants.setTotal(df.format(RetentionGraphSalesManagerList.get(0).getPercentage()) + "%");
				}

				if(RetentionGraphBLSCList.size()>0){
					dealershipMasterCertifiedRankWithinBC.setName("Rolling 12-Month Retention Percentage for Sales Consultants");
					dealershipMasterCertifiedRankWithinBC.setTotal(df.format(RetentionGraphBLSCList.get(0).getPercentage()) + "%");
				}

				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(cartificationLevel));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedLevelParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedRankWithinBC));

			}else if(type.equals("Manager")){
				years.setName("Rolling 12-Month Retention Percentage for Service Advisors");
				years.setTotal("0.0%");

				totalMasterCertifiedParticipants.setName("Rolling 12-Month Retention Percentage for Parts Advisors");
				totalMasterCertifiedParticipants.setTotal("0.0%");

				totalCertifiedSpecialistParticipants.setName("Rolling 12-Month Retention Percentage for Service Technicians");
				totalCertifiedSpecialistParticipants.setTotal("0.0%");

				dealershipMasterCertifiedRankWithinBC.setName("Rolling 12-Month Retention Percentage for Sales Consultants");
				dealershipMasterCertifiedRankWithinBC.setTotal("0.0%");

				DecimalFormat df = new DecimalFormat("0.0");

				if(RetentionGraphServiceAdvisorList.size()>0){
					years.setName("Rolling 12-Month Retention Percentage for Service Advisors");
					years.setTotal(df.format(RetentionGraphServiceAdvisorList.get(0).getPercentage()) + "%");
				}

				if(RetentionGraphPartsAdvisorList.size()>0){
					totalMasterCertifiedParticipants.setName("Rolling 12-Month Retention Percentage for Parts Advisors");
					totalMasterCertifiedParticipants.setTotal(df.format(RetentionGraphPartsAdvisorList.get(0).getPercentage()) + "%");
				}


				if(RetentionGraphServiceTechnicianList.size()>0){
					totalCertifiedSpecialistParticipants.setName("Rolling 12-Month Retention Percentage for Service Technicians");
					totalCertifiedSpecialistParticipants.setTotal(df.format(RetentionGraphServiceTechnicianList.get(0).getPercentage()) + "%");
				}



				if(RetentionGraphBLSCList.size()>0){
					dealershipMasterCertifiedRankWithinBC.setName("Rolling 12-Month Retention Percentage for Sales Consultants");
					dealershipMasterCertifiedRankWithinBC.setTotal(df.format(RetentionGraphBLSCList.get(0).getPercentage()) + "%");
				}

				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(years));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalMasterCertifiedParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(totalCertifiedSpecialistParticipants));
				topTenChart.addAttribute(this.mappingService.MapTotalNameToTileAttribute(dealershipMasterCertifiedRankWithinBC));
			}
			return topTenChart;
		}
		case "31":
		{
			if(type.equalsIgnoreCase("district")){
				territory = territory.substring(0, 3) + "%";
			}
			//main chart model
			Chart chart = new Chart("Retention % By Employee Type", "", "Retention %", "", "column_stack");

			//Create first level of the chart
			//Get Distinct program names
			List<String> programs = new ArrayList<String>(Arrays.asList("09","23","14","13","42","04","08"));

			List<ChartData> attributes = new ArrayList<ChartData>();

			for(String programName: programs){
				attributes.add(new ChartData(programName, 0));
			}

			for(ChartData item: attributes){
				List<RetentionGraphDTO> innerList = this.dashService.getRetentionGraphByChildTerritoryListAndPositionCode(territory, item.getName());

				double total = 0;
				for(RetentionGraphDTO innerItem: innerList){
					ChartData chartD = new ChartData(territory, 0);
					chartD.setName(innerItem.getChildTerritory());
					chartD.setValue(innerItem.getPercentage());
					item.addData(chartD);
					total += innerItem.getPercentage();
				}
				item.setValue(total);
				if(item.getName().equals("09")){
					item.setName("Service Managers");
				}else if(item.getName().equals("14")){
					item.setName("Parts Advisors");
				}else if(item.getName().equals("23")){
					item.setName("Service Technicians");
				}else if(item.getName().equals("13")){
					item.setName("Service Advisors");
				}else if(item.getName().equals("42")){
					item.setName("Sales Consultants");
				}else if(item.getName().equals("04")){
					item.setName("Sales Managers");
				}else if(item.getName().equals("08")){
					item.setName("Parts Managers");
				}

			}
			chart.setData(attributes);
			chart.setUnit("%");
			chart.setRetention(true);
			chart.setAvarage(true);
			return chart;
		}
		case "32":
		{
			if(type.equalsIgnoreCase("district")){
				territory = territory.substring(0, 3) + "%";
			}
			// check for role, to know what data to display
			List<CustomerFirstGraphDTO> CustomerFirstGraphDTO = this.dashService.getCustomerFirstGraphByChildTerritoryAndToggle(territory, "Total");
			Chart chart = new Chart();
			chart.setTitle("Customer First Pillars Completed QTD");
			chart.setSubTitle("");
			chart.setType("pie");
			chart.setXaxisTitle("Pillars QTD");
			chart.setYaxisTitle("Total Pillars");
			chart.setCustomer_first(true);
			chart.setCFDealDisMan(true);

			List<ChartData> list = new ArrayList<ChartData>();
			list.add(new ChartData("Level 0", 0));
			list.add(new ChartData("Performance", 0));
			list.add(new ChartData("Process", 0));
			list.add(new ChartData("Voice of Employee", 0));
			list.add(new ChartData("Training", 0));
			list.add(new ChartData("Facility", 0));
			list.add(new ChartData("CFAFE Award Certification", 0));

			for(CustomerFirstGraphDTO item :CustomerFirstGraphDTO){
				ChartData data = new ChartData();
				data.setName(item.getChildTerritory());
				data.setValue(item.getNoCertification());
				list.get(0).setValue(data.getValue());
				list.get(0).addData(data);

				data = new ChartData();
				data.setName(item.getChildTerritory());
				data.setValue(item.getPerformance());
				list.get(1).setValue(data.getValue());
				list.get(1).addData(data);

				data = new ChartData();
				data.setName(item.getChildTerritory());
				data.setValue(item.getProcess());
				list.get(2).setValue(data.getValue());
				list.get(2).addData(data);

				data = new ChartData();
				data.setName(item.getChildTerritory());
				data.setValue(item.getVoiceofEmployee());
				list.get(3).setValue(data.getValue());
				list.get(3).addData(data);

				data = new ChartData();
				data.setName(item.getChildTerritory());
				data.setValue(item.getTraining());
				list.get(4).setValue(data.getValue());
				list.get(4).addData(data);

				data = new ChartData();
				data.setName(item.getChildTerritory());
				data.setValue(item.getFacility());
				list.get(5).setValue(data.getValue());
				list.get(5).addData(data);

				data = new ChartData();
				data.setName(item.getChildTerritory());
				data.setValue(item.getCFAFEAwardCertification());
				list.get(6).setValue(data.getValue());
				list.get(6).addData(data);
			}
			chart.setData(list);

			return chart;
		}
		case "33":
		{
			if(type.equalsIgnoreCase("district")){
				territory = territory.substring(0, 3) + "%";
			}
			// check for role, to know what data to display
			List<CustomerFirstGraphDTO> CustomerFirstGraphDTO = this.dashService.getCustomerFirstGraphByChildTerritoryAndToggle(territory, "Percentage");
			Chart chart = new Chart();
			chart.setTitle("Customer First Pillars Completion Percentage");
			chart.setSubTitle("");
			chart.setType("column_stack");
			chart.setXaxisTitle("Completion %");
			chart.setYaxisTitle("");

			List<ChartData> list = new ArrayList<ChartData>();
			list.add(new ChartData("Level 0", 0));
			list.add(new ChartData("Performance", 0));
			list.add(new ChartData("Process", 0));
			list.add(new ChartData("Voice of Employee", 0));
			list.add(new ChartData("Training", 0));
			list.add(new ChartData("Facility", 0));
			list.add(new ChartData("CFAFE Award Certification", 0));

			for(CustomerFirstGraphDTO item: CustomerFirstGraphDTO){
				ChartData data = new ChartData();
				data.setName(item.getChildTerritory());
				data.setValue(item.getNoCertification());
				list.get(0).setValue(data.getValue());
				list.get(0).addData(data);

				data = new ChartData();
				data.setName(item.getChildTerritory());
				data.setValue(item.getPerformance());
				list.get(1).setValue(data.getValue());
				list.get(1).addData(data);

				data = new ChartData();
				data.setName(item.getChildTerritory());
				data.setValue(item.getProcess());
				list.get(2).setValue(data.getValue());
				list.get(2).addData(data);

				data = new ChartData();
				data.setName(item.getChildTerritory());
				data.setValue(item.getVoiceofEmployee());
				list.get(3).setValue(data.getValue());
				list.get(3).addData(data);

				data = new ChartData();
				data.setName(item.getChildTerritory());
				data.setValue(item.getTraining());
				list.get(4).setValue(data.getValue());
				list.get(4).addData(data);

				data = new ChartData();
				data.setName(item.getChildTerritory());
				data.setValue(item.getFacility());
				list.get(5).setValue(data.getValue());
				list.get(5).addData(data);

				data = new ChartData();
				data.setName(item.getChildTerritory());
				data.setValue(item.getCFAFEAwardCertification());
				list.get(6).setValue(data.getValue());
				list.get(6).addData(data);
			}
			chart.setData(list);
			chart.setAvarage(true);
			chart.setUnit("%");
			chart.setRetention(true);

			return chart;
		}
		case "34":
		{
			if(type.equalsIgnoreCase("district")){
				territory = territory.substring(0, 2);
			}
			// check for role, to know what data to display
			List<TTTAEnrolledGraphDTO> TTTAEnrolledDTO = this.dashService.getTTTAEnrolledByParentTerritory(Arrays.asList(territory));
			Chart chart = new Chart();
			chart.setTitle("Total Dealers Enrolled");
			chart.setSubTitle("");
			chart.setType("column_stack");
			chart.setXaxisTitle(" # of  Dealers");
			chart.setYaxisTitle("Total Enrolled");

			List<ChartData> list = new ArrayList<ChartData>();
			list.add(new ChartData("Group A", 0));
			list.add(new ChartData("Group B", 0));
			list.add(new ChartData("Group C", 0));
			list.add(new ChartData("Group D", 0));
			list.add(new ChartData("Group E", 0));

			for(TTTAEnrolledGraphDTO item: TTTAEnrolledDTO){
				ChartData data = new ChartData();
				data.setName(item.getChild());
				data.setValue(item.getTotalGroupA());
				list.get(0).setValue(data.getValue());
				list.get(0).addData(data);

				data = new ChartData();
				data.setName(item.getChild());
				data.setValue(item.getTotalGroupB());
				list.get(1).setValue(data.getValue());
				list.get(1).addData(data);

				data = new ChartData();
				data.setName(item.getChild());
				data.setValue(item.getTotalGroupC());
				list.get(2).setValue(data.getValue());
				list.get(2).addData(data);

				data = new ChartData();
				data.setName(item.getChild());
				data.setValue(item.getTotalGroupD());
				list.get(3).setValue(data.getValue());
				list.get(3).addData(data);

				data = new ChartData();
				data.setName(item.getChild());
				data.setValue(item.getTotalGroupE());
				list.get(4).setValue(data.getValue());
				list.get(4).addData(data);

			}

			chart.setData(list);

			return chart;

		}
		case "35":
		{
			List<TTTAEnrolledGraphDTO> TTTAEnrolledDTO = null;
			if(type.equalsIgnoreCase("district")){
				TTTAEnrolledDTO = this.dashService.getTTTAEnrolledByParentTerritoryNotEnrolled(Arrays.asList(territory.substring(0, 2)));
			}else{
				TTTAEnrolledDTO = this.dashService.getTTTAEnrolledByChildTerritoryNotEnrolled(territory);
			}
			// check for role, to know what data to display
			
			Chart chart = new Chart();
			chart.setTitle("Total Dealers Not Enrolled");
			chart.setSubTitle("");
			chart.setType("column_stack");
			chart.setXaxisTitle(" # of  Dealers");
			chart.setYaxisTitle("Total Not Enrolled");

			List<ChartData> list = new ArrayList<ChartData>();
			list.add(new ChartData("Group A", 0));
			list.add(new ChartData("Group B", 0));
			list.add(new ChartData("Group C", 0));
			list.add(new ChartData("Group D", 0));
			list.add(new ChartData("Group E", 0));
			for(TTTAEnrolledGraphDTO item : TTTAEnrolledDTO){
				ChartData data = new ChartData();
				data.setName(item.getChild());
				data.setValue(item.getTotalGroupA());
				list.get(0).setValue(data.getValue());
				list.get(0).addData(data);

				data = new ChartData();
				data.setName(item.getChild());
				data.setValue(item.getTotalGroupB());
				list.get(1).setValue(data.getValue());
				list.get(1).addData(data);

				data = new ChartData();
				data.setName(item.getChild());
				data.setValue(item.getTotalGroupC());
				list.get(2).setValue(data.getValue());
				list.get(2).addData(data);

				data = new ChartData();
				data.setName(item.getChild());
				data.setValue(item.getTotalGroupD());
				list.get(3).setValue(data.getValue());
				list.get(3).addData(data);

				data = new ChartData();
				data.setName(item.getChild());
				data.setValue(item.getTotalGroupE());
				list.get(4).setValue(data.getValue());
				list.get(4).addData(data);
			}

			chart.setData(list);

			return chart;

		}
		case "36":
		{
			List<SummaryProgramRewardGraphDTO> sublist = new ArrayList<SummaryProgramRewardGraphDTO>();
			List<SummaryProgramRewardQuartileGraphDTO> subListQuartile = new ArrayList<SummaryProgramRewardQuartileGraphDTO>();;
			
			if(type.equals("District")){
				sublist = this.dashService.getSummaryProgramRewardGraphByParentTerritoryYTD(Arrays.asList(territory.substring(0, 2)));
				subListQuartile = this.dashService.getSummaryProgramRewardQuartileGraphByChildTerritoryYTD(territory);
			}else if(type.equals("Dealer")){
				sublist = this.dashService.getSummaryProgramRewardGraphByChildTerritoryYTD(dealerCode);
				subListQuartile = this.dashService.getSummaryProgramRewardQuartileGraphByChildTerritoryYTD(dealerCode);
			}else{				
				sublist = this.dashService.getSummaryProgramRewardDetailsBySIDYTD(user.trim(), dealerCode);
				subListQuartile = this.dashService.getSummaryProgramRewardQuartileDetailBySIDYTD(user.trim(), dealerCode);
			}

			Chart chart = new Chart();
			chart.setTitle("Summary Dollar Earnings YTD");
			chart.setSubTitle("");
			chart.setType("column");
			chart.setXaxisTitle("");
			chart.setYaxisTitle("Total Dollars Earned");
			chart.setTopQuartile(true);

			if(subListQuartile.size() > 0){
				chart.setFirstLevelQuartile(subListQuartile.get(0).getTopQuartile());
			}
			
			List<ChartData> list = new ArrayList<ChartData>();
			for(SummaryProgramRewardGraphDTO item: sublist){
				ChartData chartData = new ChartData();	
				chartData.setName(item.getChild());
				chartData.setValue(item.getEarnings());
				list.add(chartData);
			}
			chart.setData(list);
			chart.setUnit("$");
			//chart.setAverageLine(true);
			return chart;
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
		number =  Math.round(number);
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
		number = Math.round(number);
		DecimalFormat formatter = new DecimalFormat("#,###");
		return formatter.format(number);
	}

	public String formatNumbers(int number){
		DecimalFormat formatter = new DecimalFormat("#,###");
		return formatter.format(number);
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
