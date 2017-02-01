package com.imperialm.imiservices.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.imperialm.imiservices.dto.BrainBoostWinndersGraphDTO;
import com.imperialm.imiservices.dto.CertProfsExpertGraphDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersGraphDTO;
import com.imperialm.imiservices.dto.MSEREarningsDTO;
import com.imperialm.imiservices.dto.MSERTopNDTO;
import com.imperialm.imiservices.dto.TTTATopNDTO;
import com.imperialm.imiservices.model.Chart;
import com.imperialm.imiservices.model.ChartData;
import com.imperialm.imiservices.model.TileAttribute1;
import com.imperialm.imiservices.model.TopTenTableData;
import com.imperialm.imiservices.model.response.TotalName;

@Service
public class MappingServiceImpl {

	public MappingServiceImpl(){}
	
	
	public Chart MapMSEREarningsDTOtoChart(List<MSEREarningsDTO> MSEREarningDTO, String title, String subTitle, String xaxisTitle, String yaxisTitle, String type){
		Chart chart = new Chart();
		chart.setTitle(title);
		chart.setSubTitle(subTitle);
		chart.setType(type);
		chart.setXaxisTitle(xaxisTitle);
		chart.setYaxisTitle(yaxisTitle);
		
		chart.setData(this.MapMSEREarningDTOtoChartData(MSEREarningDTO));
		
		return chart;
	}
	
	public Chart BrainBoostWinndersGraphDTOtoChart(List<BrainBoostWinndersGraphDTO> BrainBoostWinndersGraphDTO, String title, String subTitle, String xaxisTitle, String yaxisTitle, String type, String graph){
		Chart chart = new Chart();
		chart.setTitle(title);
		chart.setSubTitle(subTitle);
		chart.setType(type);
		chart.setXaxisTitle(xaxisTitle);
		chart.setYaxisTitle(yaxisTitle);
				
		chart.setData(this.BrainBoostWinndersGraphDTOtoChartData(BrainBoostWinndersGraphDTO, graph));
		
		return chart;
	}
	
	public Chart CertProfsExpertGraphDTOtoChartTotalPoints(List<CertProfsExpertGraphDTO> CertProfsExpertGraphDTO, String title, String subTitle, String xaxisTitle, String yaxisTitle, String type){
		Chart chart = new Chart();
		chart.setTitle(title);
		chart.setSubTitle(subTitle);
		chart.setType(type);
		chart.setXaxisTitle(xaxisTitle);
		chart.setYaxisTitle(yaxisTitle);
		
		chart.setData(this.CertProfsExpertGraphDTOtoChartDataTotalPoints(CertProfsExpertGraphDTO));
		
		return chart;
	}
	
	public Chart CertProfsExpertGraphDTOtoChartCert(List<CertProfsExpertGraphDTO> CertProfsExpertGraphDTO, String title, String subTitle, String xaxisTitle, String yaxisTitle, String type){
		Chart chart = new Chart();
		chart.setTitle(title);
		chart.setSubTitle(subTitle);
		chart.setType(type);
		chart.setXaxisTitle(xaxisTitle);
		chart.setYaxisTitle(yaxisTitle);
		
		chart.setData(this.CertProfsExpertGraphDTOtoChartDataCert(CertProfsExpertGraphDTO));
		
		return chart;
	}
	
	public Chart CertProfsWinnersGraphDTOtoChart(List<CertProfsWinnersGraphDTO> CertProfsWinnersGraphDTO, String title, String subTitle, String xaxisTitle, String yaxisTitle, String type){
		Chart chart = new Chart();
		chart.setTitle(title);
		chart.setSubTitle(subTitle);
		chart.setType(type);
		chart.setXaxisTitle(xaxisTitle);
		chart.setYaxisTitle(yaxisTitle);
		
		chart.setData(this.CertProfsWinnersGraphDTOtoChartData(CertProfsWinnersGraphDTO));
		
		return chart;
	}
	
	public List<ChartData> MapMSEREarningDTOtoChartData(List<MSEREarningsDTO> MSEREarningDTO){
		List<ChartData> list = new ArrayList<ChartData>();
		
		for (MSEREarningsDTO Earning : MSEREarningDTO) {
			list.add(this.MapMSEREarningDTOtoChartData(Earning));
		}
		
		return list;
	}
	
	public List<ChartData> BrainBoostWinndersGraphDTOtoChartData(List<BrainBoostWinndersGraphDTO> BrainBoostWinndersGraphDTO, String type){
		List<ChartData> list = new ArrayList<ChartData>();
		
		if(type.equals("Winners")){
			for (BrainBoostWinndersGraphDTO item : BrainBoostWinndersGraphDTO) {
				list.add(this.BrainBoostWinndersGraphDTOtoChartDataWinners(item));
			}
		}else if(type.equals("Awards")){
			for (BrainBoostWinndersGraphDTO item : BrainBoostWinndersGraphDTO) {
				list.add(this.BrainBoostWinndersGraphDTOtoChartDataAwards(item));
			}
		}
		
		return list;
	}
	
	public List<ChartData> CertProfsExpertGraphDTOtoChartDataTotalPoints(List<CertProfsExpertGraphDTO> CertProfsExpertGraphDTO){
		List<ChartData> list = new ArrayList<ChartData>();
		
		for (CertProfsExpertGraphDTO item : CertProfsExpertGraphDTO) {
			list.add(this.CertProfsExpertGraphDTOtoChartDataTotalPoints(item));
		}
		
		return list;
	}
	
	public List<ChartData> CertProfsWinnersGraphDTOtoChartData(List<CertProfsWinnersGraphDTO> CertProfsWinnersGraphDTO){
		List<ChartData> list = new ArrayList<ChartData>();
		
		for (CertProfsWinnersGraphDTO item : CertProfsWinnersGraphDTO) {
			list.add(this.CertProfsWinnersGraphDTOtoChartData(item));
		}
		
		return list;
	}
	
	public List<ChartData> CertProfsExpertGraphDTOtoChartDataCert(List<CertProfsExpertGraphDTO> CertProfsExpertGraphDTO){
		List<ChartData> list = new ArrayList<ChartData>();
		for (CertProfsExpertGraphDTO item : CertProfsExpertGraphDTO) {
			boolean addNew = true;
				for(ChartData chart: list){
					if(chart.getName().equals(item.getParentTerritory())){
						chart.addData(this.CertProfsExpertGraphDTOtoChartDataCert(item));
						addNew = false;
					}
				}
				
			if(addNew){
				ChartData data = new ChartData();
				data.setName(item.getParentTerritory());
				data.addData(this.CertProfsExpertGraphDTOtoChartDataCert(item));
				list.add(data);
			}
		}
		
		return list;
	}
	
	public ChartData MapMSEREarningDTOtoChartData(MSEREarningsDTO MSEREarningDTO){
		ChartData chartData = new ChartData();
		
		chartData.setName(MSEREarningDTO.getTerritory());
		
		List<ChartData> data = new ArrayList<ChartData>();
		ChartData MoparParts , mvp, MagnetiMarelli, PartsCounter, ExpressLane, wiAdvisor, uConnect;
		MoparParts = new ChartData("Mopar Parts", MSEREarningDTO.getMoparParts());
		mvp = new ChartData("mvp",MSEREarningDTO.getMvp());
		MagnetiMarelli = new ChartData("Magneti Marelli",MSEREarningDTO.getMagnetiMarelli());
		PartsCounter = new ChartData("Parts Counter", MSEREarningDTO.getPartsCounter());
		wiAdvisor = new ChartData("wiAdvisor", MSEREarningDTO.getWiAdvisor());
		ExpressLane = new ChartData("Express Lane", MSEREarningDTO.getExpressLane());
		uConnect = new ChartData("uConnect", MSEREarningDTO.getuConnect());
		
		data.add(MoparParts);
		data.add(mvp);
		data.add(MagnetiMarelli);
		data.add(PartsCounter);
		data.add(wiAdvisor);
		data.add(ExpressLane);
		data.add(uConnect);
		
		chartData.setData(data);
		
		return chartData;
	}
	
	public ChartData BrainBoostWinndersGraphDTOtoChartDataWinners(BrainBoostWinndersGraphDTO BrainBoostWinndersGraphDTO){
		ChartData chartData = new ChartData();		
		chartData.setName(BrainBoostWinndersGraphDTO.getParentTerritory());
		chartData.setValue(Double.parseDouble(BrainBoostWinndersGraphDTO.getWinners()));
		return chartData;
	}
	
	public ChartData BrainBoostWinndersGraphDTOtoChartDataAwards(BrainBoostWinndersGraphDTO BrainBoostWinndersGraphDTO){
		ChartData chartData = new ChartData();
		ChartData excellenceCard = new ChartData();
		ChartData awardPoints = new ChartData();
		
		chartData.setName(BrainBoostWinndersGraphDTO.getParentTerritory());
		
		excellenceCard.setName("Excellence Card");
		excellenceCard.setValue(Double.parseDouble(BrainBoostWinndersGraphDTO.getEarnings()));
		
		awardPoints.setName("Award Points");
		awardPoints.setValue(Double.parseDouble(BrainBoostWinndersGraphDTO.getPoints()));
		
		chartData.addData(excellenceCard);
		chartData.addData(awardPoints);
		
		return chartData;
	}
	
	public ChartData CertProfsExpertGraphDTOtoChartDataTotalPoints(CertProfsExpertGraphDTO CertProfsExpertGraphDTO){
		ChartData chartData = new ChartData();		
		chartData.setName(CertProfsExpertGraphDTO.getParentTerritory());
		chartData.setValue(Double.parseDouble(CertProfsExpertGraphDTO.getTotalPoints()));
		return chartData;
	}
	
	public ChartData CertProfsExpertGraphDTOtoChartDataCert(CertProfsExpertGraphDTO CertProfsExpertGraphDTO){
		ChartData chartData = new ChartData();		
		chartData.setName(CertProfsExpertGraphDTO.getCertType());
		chartData.setValue(Double.parseDouble(CertProfsExpertGraphDTO.getCert()));
		return chartData;
	}
	
	public ChartData CertProfsWinnersGraphDTOtoChartData(CertProfsWinnersGraphDTO CertProfsWinnersGraphDTO){
		ChartData chartData = new ChartData();
		ChartData certified = new ChartData();
		ChartData certifiedSpacialist = new ChartData();
		ChartData masterCertified = new ChartData();
		
		chartData.setName(CertProfsWinnersGraphDTO.getParentTerritory());
		
		certified.setName("Certified");
		certified.setValue(Double.parseDouble(CertProfsWinnersGraphDTO.getCertified()));
		
		certifiedSpacialist.setName("Certified Spacialist");
		certifiedSpacialist.setValue(Double.parseDouble(CertProfsWinnersGraphDTO.getCertifiedSpecalist()));
		
		masterCertified.setName("Master Certified");
		masterCertified.setValue(Double.parseDouble(CertProfsWinnersGraphDTO.getMasterCertified()));
		
		chartData.addData(certified);
		chartData.addData(certifiedSpacialist);
		chartData.addData(masterCertified);
		
		chartData.setName(CertProfsWinnersGraphDTO.getParentTerritory());
		return chartData;
	}
	
	
	public TopTenTableData MapTTTATopNDTOtoTopTenTableData(List<TTTATopNDTO> TTTATopNDTO, String tableName, List<String> tableHeader){
		TopTenTableData topTenTableData = new TopTenTableData();
		
		topTenTableData.setTableHeader(tableHeader);
		topTenTableData.setTableName(tableName);
		
		List<Object> data = new ArrayList<Object>();
		
		int i = 1;
		for(TTTATopNDTO item: TTTATopNDTO){
			List<Object> items = new ArrayList<Object>();
			if(item.getError().equals("") || item.getError().equals(null)){
				items.add(i);
				items.add(item.getName());
				items.add(item.getAvgSurveyScore());
				i++;
			}
			data.add(items);
		}
		
		topTenTableData.setData(data);
		
		return topTenTableData;
	}
	
	public TopTenTableData MapMSERTopNDTOtoTopTenTableData(List<MSERTopNDTO> MSERTopNDTO, String tableName, List<String> tableHeader){
		TopTenTableData topTenTableData = new TopTenTableData();
		
		topTenTableData.setTableHeader(tableHeader);
		topTenTableData.setTableName(tableName);
		
		List<Object> data = new ArrayList<Object>();
		
		for(MSERTopNDTO item: MSERTopNDTO){
			List<Object> items = new ArrayList<Object>();
			if(item.getError().equals("") || item.getError().equals(null)){
				items.add(item.getTopNRank());
				items.add(item.getName());
				items.add(item.getEarnings());
			}
			data.add(items);
		}
		
		topTenTableData.setData(data);
		
		return topTenTableData;
	}
	
	public TileAttribute1 MapTotalNameToTileAttribute(TotalName totalName){
		TileAttribute1 attribute= new TileAttribute1();
		attribute.setName(totalName.getName());
		attribute.setValue(totalName.getTotal());
		return attribute;
	}
	
}
