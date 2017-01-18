package com.imperialm.imiservices.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imperialm.imiservices.dao.DashboardDAO;
import com.imperialm.imiservices.dao.MSEREarningsDAOImpl;
import com.imperialm.imiservices.dto.DashboardDTO;
import com.imperialm.imiservices.dto.MSEREarningsDTO;
import com.imperialm.imiservices.dto.MSERTopNDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.model.Chart;
import com.imperialm.imiservices.model.TopTenChart;
import com.imperialm.imiservices.model.TopTenDataTable;
import com.imperialm.imiservices.model.TopTenTableData;
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
	
	@RequestMapping(value = "/services/tile/{chartId}", method = RequestMethod.GET)
	public @ResponseBody Object findTilesListByRole(@PathVariable(value="chartId") String id) {
		final InputRequest userRoleReq = new InputRequest("", "");
		
		switch(id){
		case "1":
			List<MSEREarningsDTO> list = this.dashService.getEarningsByRole(userRoleReq);
			return this.mappingService.MapMSEREarningsDTOtoChart(list, "BC Total Earnings YTD", "", "Totsl Earnings", "", "bar-compond");
		case "2":
			List<MSERTopNDTO> listAdvisors = this.dashService.getMSERTopTen("Advisor", 5);
			List<MSERTopNDTO> listTechnicians = this.dashService.getMSERTopTen("Technician", 5);
			
			TopTenChart topTenChart = new TopTenChart();
			TopTenDataTable datatable = new TopTenDataTable("Excellence Card Awards", "Excellence Card Awards - Top 5 Technicians");
			
			List<TopTenTableData> tabledata = new ArrayList<TopTenTableData>();
			
			List<String> tableheaders = new ArrayList<String>();
			/*tableheaders.add("Order");
			tableheaders.add("Advisor BC, District, Dealer Code");
			tableheaders.add("Reward amount");*/
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listTechnicians, "Top 5 MTD Technicians Excellence Card Awards", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listAdvisors, "Top 5 MTD Advisors Excellence Card Awards", tableheaders));
			
			datatable.setTableData(tabledata);
						
			topTenChart.setDatatable(datatable);
			
			return topTenChart;
		case "3":
			List<MSERTopNDTO> listMOPARMTD = this.dashService.getMSERTopTen("MOPAR Parts Sold MTD", 3);
			List<MSERTopNDTO> listMOPARYTD = this.dashService.getMSERTopTen("MOPAR Parts Sold YTD", 3);
			
			topTenChart = new TopTenChart();
			datatable = new TopTenDataTable("","");
			
			tabledata = new ArrayList<TopTenTableData>();
			
			tableheaders = new ArrayList<String>();
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMOPARMTD, "Top 3 MOPAR Parts Sold MTD", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMOPARYTD, "Top 3 MOPAR Parts Sold YTD", tableheaders));
			
			datatable.setTableData(tabledata);
						
			topTenChart.setDatatable(datatable);
			
			return topTenChart;
		case "4":
			List<MSERTopNDTO> listMVPMTD = this.dashService.getMSERTopTen("MVP Parts Sold MTD", 3);
			List<MSERTopNDTO> listMVPYTD = this.dashService.getMSERTopTen("MVP Parts Sold YTD", 3);
			
			topTenChart = new TopTenChart();
			datatable = new TopTenDataTable("","");
			
			tabledata = new ArrayList<TopTenTableData>();
			
			tableheaders = new ArrayList<String>();
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMVPMTD, "Top 3 MVP Parts Sold MTD", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMVPYTD, "Top 3 MVP Parts Sold YTD", tableheaders));
			
			datatable.setTableData(tabledata);
						
			topTenChart.setDatatable(datatable);
			
			return topTenChart;
		case "5":
			List<MSERTopNDTO> listMMPMTD = this.dashService.getMSERTopTen("Magneti Marelli Parts Sold MTD", 3);
			List<MSERTopNDTO> listMMPYTD = this.dashService.getMSERTopTen("Magneti Marelli Parts Sold YTD", 3);
			
			topTenChart = new TopTenChart();
			datatable = new TopTenDataTable("","");
			
			tabledata = new ArrayList<TopTenTableData>();
			
			tableheaders = new ArrayList<String>();
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMMPMTD, "Top 3 Magneti Marelli Parts Sold MTD", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listMMPYTD, "Top 3 Magneti Marelli Parts Sold YTD", tableheaders));
			
			datatable.setTableData(tabledata);
						
			topTenChart.setDatatable(datatable);
			
			return topTenChart;
		case "6":
			List<MSERTopNDTO> listPCPMTD = this.dashService.getMSERTopTen("Parts Counter Parts Sold MTD", 3);
			List<MSERTopNDTO> listPCPYTD = this.dashService.getMSERTopTen("Parts Counter Parts Sold YTD", 3);
			
			topTenChart = new TopTenChart();
			datatable = new TopTenDataTable("","");
			
			tabledata = new ArrayList<TopTenTableData>();
			
			tableheaders = new ArrayList<String>();
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listPCPMTD, "Top 3 Parts Counter Parts Sold MTD", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listPCPYTD, "Top 3 Parts Counter Parts Sold YTD", tableheaders));
			
			datatable.setTableData(tabledata);
						
			topTenChart.setDatatable(datatable);
			
			return topTenChart;
		case "7":
			List<MSERTopNDTO> listELPMTD = this.dashService.getMSERTopTen("Express Lane Parts Sold MTD", 3);
			List<MSERTopNDTO> listELPYTD = this.dashService.getMSERTopTen("Express Lane Parts Sold YTD", 3);
			
			topTenChart = new TopTenChart();
			datatable = new TopTenDataTable("","");
			
			tabledata = new ArrayList<TopTenTableData>();
			
			tableheaders = new ArrayList<String>();
			
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listELPMTD, "Top 3 Express Lane Parts Sold MTD", tableheaders));	
			tabledata.add(this.mappingService.MapMSERTopNDTOtoTopTenTableData(listELPYTD, "Top 3 Express Lane Parts Sold YTD", tableheaders));
			
			datatable.setTableData(tabledata);
						
			topTenChart.setDatatable(datatable);
			
			return topTenChart;
		default:
			return "No such service call exists.";
		}
	}

	
}
