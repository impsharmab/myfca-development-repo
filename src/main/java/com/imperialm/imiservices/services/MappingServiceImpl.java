package com.imperialm.imiservices.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import com.imperialm.imiservices.dto.MSEREarningsDTO;
import com.imperialm.imiservices.model.Chart;
import com.imperialm.imiservices.model.ChartData;
import com.imperialm.imiservices.model.TileAttribute1;

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
	
	public ChartData MapMSEREarningDTOtoChartData(MSEREarningsDTO MSEREarningDTO){
		ChartData chartData = new ChartData();
		
		chartData.setName(MSEREarningDTO.getTerritory());
		
		List<ChartData> data = new ArrayList<ChartData>();
		ChartData MoparParts , mvp, MagnetiMarelli, PartsCounter, ExpressLane, wiAdvisor, uConnect;
		MoparParts = new ChartData("Mopar Parts", java.util.Objects.toString(MSEREarningDTO.getMoparParts()));
		mvp = new ChartData("mvp",java.util.Objects.toString(MSEREarningDTO.getMvp()));
		MagnetiMarelli = new ChartData("Magneti Marelli",java.util.Objects.toString(MSEREarningDTO.getMagnetiMarelli()));
		PartsCounter = new ChartData("Parts Counter", java.util.Objects.toString(MSEREarningDTO.getPartsCounter()));
		wiAdvisor = new ChartData("wiAdvisor", java.util.Objects.toString(MSEREarningDTO.getWiAdvisor()));
		ExpressLane = new ChartData("Express Lane", java.util.Objects.toString(MSEREarningDTO.getExpressLane()));
		uConnect = new ChartData("uConnect", java.util.Objects.toString(MSEREarningDTO.getuConnect()));
		
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
	
	public List<ChartData> MapMSEREarningDTOtoChartData(List<MSEREarningsDTO> MSEREarningDTO){
		List<ChartData> list = new ArrayList<ChartData>();
		
		for (MSEREarningsDTO Earning : MSEREarningDTO) {
			list.add(this.MapMSEREarningDTOtoChartData(Earning));
		}
		
		return list;
	}
	
}
