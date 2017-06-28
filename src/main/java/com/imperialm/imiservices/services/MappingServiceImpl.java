package com.imperialm.imiservices.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperialm.imiservices.dto.BrainBoostWinndersGraphDTO;
import com.imperialm.imiservices.dto.CertProfsExpertGraphDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersGraphDTO;
import com.imperialm.imiservices.dto.CustomerFirstGraphDTO;
import com.imperialm.imiservices.dto.MyfcaMSERTopNDTO;
import com.imperialm.imiservices.dto.MyfcaMSERTotalEarningsDTO;
import com.imperialm.imiservices.dto.RewardRedemptionGraphDTO;
import com.imperialm.imiservices.dto.SIRewardsDetailsDTO;
import com.imperialm.imiservices.dto.SIRewardsDetailsGraphDTO;
import com.imperialm.imiservices.dto.SIRewardsYOYGraphDTO;
import com.imperialm.imiservices.dto.SummaryProgramRewardGraphDTO;
import com.imperialm.imiservices.dto.TTTAEnrolledDTO;
import com.imperialm.imiservices.dto.TTTAEnrolledGraphDTO;
import com.imperialm.imiservices.dto.TTTATopNDTO;
import com.imperialm.imiservices.model.Chart;
import com.imperialm.imiservices.model.ChartData;
import com.imperialm.imiservices.model.TileAttribute1;
import com.imperialm.imiservices.model.TopTenTableData;
import com.imperialm.imiservices.model.response.TotalName;
import com.imperialm.imiservices.util.IMIServicesUtil;

@Service
public class MappingServiceImpl {
	
	@Autowired
	private IMIServicesUtil IMIServicesUtil;

	public MappingServiceImpl(){}
	
	public Chart copyChart(Chart chart){
		Chart c = new Chart();
		c.setAvarage(chart.isAvarage());
		c.setAverageLine(chart.isAverageLine());
		c.setCFDealDisMan(chart.isCFDealDisMan());
		c.setCustomer_first(chart.isCustomer_first());
		c.setData(chart.getData());
		c.setRetention(chart.isRetention());
		c.setSubTitle(chart.getSubTitle());
		c.setTitle(chart.getTitle());
		c.setType(chart.getType());
		c.setUnit(chart.getUnit());
		c.setXaxisTitle(chart.getXaxisTitle());
		c.setYaxisTitle(chart.getYaxisTitle());
		return c;
	}
	

	
	public Chart MapMSERGraphDTOtoChart(List<MyfcaMSERTotalEarningsDTO> MyfcaMSERTotalEarningsDTO, String title, String subTitle, String xaxisTitle, String yaxisTitle, String type){
		Chart chart = new Chart();
		chart.setTitle(title);
		chart.setSubTitle(subTitle);
		chart.setType(type);
		chart.setXaxisTitle(xaxisTitle);
		chart.setYaxisTitle(yaxisTitle);
		
		chart.setData(this.MapMSERGraphDTOtoChartData(MyfcaMSERTotalEarningsDTO));
		
		return chart;
	}
	
	
	public Chart MapTTTAEnrolledDTOtoChart(List<TTTAEnrolledDTO> TTTAEnrolledDTO, String title, String subTitle, String xaxisTitle, String yaxisTitle, String type){
		Chart chart = new Chart();
		chart.setTitle(title);
		chart.setSubTitle(subTitle);
		chart.setType(type);
		chart.setXaxisTitle(xaxisTitle);
		chart.setYaxisTitle(yaxisTitle);
		
		chart.setData(this.MapTTTAEnrolledDTOtoChartData(TTTAEnrolledDTO));
		
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
	
	public Chart SIRewardsDetailsGraphDTOtoChart(List<SIRewardsDetailsGraphDTO> SIRewardsDetailsGraphDTO, String title, String subTitle, String xaxisTitle, String yaxisTitle, String type, String graph){
		Chart chart = new Chart();
		chart.setTitle(title);
		chart.setSubTitle(subTitle);
		chart.setType(type);
		chart.setXaxisTitle(xaxisTitle);
		chart.setYaxisTitle(yaxisTitle);
				
		chart.setData(this.SIRewardsDetailsGraphDTOtoChartData(SIRewardsDetailsGraphDTO, graph));
		
		return chart;
	}
	
	public Chart SummaryProgramRewardGraphDTOtoChart(List<SummaryProgramRewardGraphDTO> SummaryProgramRewardGraphDTO, String title, String subTitle, String xaxisTitle, String yaxisTitle, String type){
		Chart chart = new Chart();
		chart.setTitle(title);
		chart.setSubTitle(subTitle);
		chart.setType(type);
		chart.setXaxisTitle(xaxisTitle);
		chart.setYaxisTitle(yaxisTitle);
				
		chart.setData(this.SummaryProgramRewardGraphDTOtoChartData(SummaryProgramRewardGraphDTO));
		
		return chart;
	}
	
	public Chart RewardRedemptionGraphDTOtoChart(List<RewardRedemptionGraphDTO> RewardRedemptionGraphDTO, String title, String subTitle, String xaxisTitle, String yaxisTitle, String type, String graph){
		Chart chart = new Chart();
		chart.setTitle(title);
		chart.setSubTitle(subTitle);
		chart.setType(type);
		chart.setXaxisTitle(xaxisTitle);
		chart.setYaxisTitle(yaxisTitle);
				
		chart.setData(this.RewardRedemptionGraphDTOtoChartData(RewardRedemptionGraphDTO, graph));
		
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
	
	public Chart TTTAEnrolledGraphDTOtoChart(List<TTTAEnrolledGraphDTO> TTTAEnrolledGraphDTO, String title, String subTitle, String xaxisTitle, String yaxisTitle, String type){
		Chart chart = new Chart();
		chart.setTitle(title);
		chart.setSubTitle(subTitle);
		chart.setType(type);
		chart.setXaxisTitle(xaxisTitle);
		chart.setYaxisTitle(yaxisTitle);
		
		chart.setData(this.TTTAEnrolledGraphDTOtoChartData(TTTAEnrolledGraphDTO));
		
		return chart;
	}
	
	public Chart CustomerFirstGraphDTOtoChart(List<CustomerFirstGraphDTO> CustomerFirstGraphDTO, String title, String subTitle, String xaxisTitle, String yaxisTitle, String type){
		Chart chart = new Chart();
		chart.setTitle(title);
		chart.setSubTitle(subTitle);
		chart.setType(type);
		chart.setXaxisTitle(xaxisTitle);
		chart.setYaxisTitle(yaxisTitle);
		
		chart.setData(this.CustomerFirstGraphDTOtoChartData(CustomerFirstGraphDTO));
		
		return chart;
	}
	
	
	public Chart SIRewardsYOYGraphDTOtoChart(List<SIRewardsYOYGraphDTO> SIRewardsYOYGraphDTO, String title, String subTitle, String xaxisTitle, String yaxisTitle, String type){
		Chart chart = new Chart();
		chart.setTitle(title);
		chart.setSubTitle(subTitle);
		chart.setType(type);
		chart.setXaxisTitle(xaxisTitle);
		chart.setYaxisTitle(yaxisTitle);
		
		chart.setData(this.SIRewardsYOYGraphDTOtoChartData(SIRewardsYOYGraphDTO));
		
		return chart;
	}
	
	
	public List<ChartData> MapMSERGraphDTOtoChartData(List<MyfcaMSERTotalEarningsDTO> MyfcaMSERTotalEarningsDTO){
		List<ChartData> list = new ArrayList<ChartData>();
		
		for (MyfcaMSERTotalEarningsDTO Earning : MyfcaMSERTotalEarningsDTO) {
			list.add(this.MapMSERGraphDTOtoChartData(Earning));
		}
		
		return list;
	}
	
	public List<ChartData> MapTTTAEnrolledDTOtoChartData(List<TTTAEnrolledDTO> TTTAEnrolledDTO){
		List<ChartData> list = new ArrayList<ChartData>();
		
		for (TTTAEnrolledDTO Earning : TTTAEnrolledDTO) {
			list.add(this.MapTTTAEnrolledDTOtoChartData(Earning));
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
	
	public List<ChartData> SIRewardsDetailsGraphDTOtoChartData(List<SIRewardsDetailsGraphDTO> SIRewardsDetailsGraphDTO, String type){
		List<ChartData> list = new ArrayList<ChartData>();
		
		if(type.equals("Projected")){
			for (SIRewardsDetailsGraphDTO item : SIRewardsDetailsGraphDTO) {
				list.add(this.SIRewardsDetailsGraphDTOtoChartDataProjected(item));
			}
		}else if(type.equals("Average Score")){
			for (SIRewardsDetailsGraphDTO item : SIRewardsDetailsGraphDTO) {
				list.add(this.SIRewardsDetailsGraphDTOtoChartDataAverageScore(item));
			}
		}
		
		return list;
	}
	
	public List<ChartData> SummaryProgramRewardGraphDTOtoChartData(List<SummaryProgramRewardGraphDTO> SummaryProgramRewardGraphDTO){
		List<ChartData> list = new ArrayList<ChartData>();

			for (SummaryProgramRewardGraphDTO item : SummaryProgramRewardGraphDTO) {
				list.add(this.SummaryProgramRewardGraphDTOtoChartDataAverageScore(item));
			}
		return list;
	}
	
	public List<ChartData> RewardRedemptionGraphDTOtoChartData(List<RewardRedemptionGraphDTO> RewardRedemptionGraphDTO, String type){
		List<ChartData> list = new ArrayList<ChartData>();
		
		if(type.equals("EarnedPoints")){
			for (RewardRedemptionGraphDTO item : RewardRedemptionGraphDTO) {
				list.add(this.RewardRedemptionGraphDTOtoChartDataEarnedPoints(item));
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
	
	public List<ChartData> TTTAEnrolledGraphDTOtoChartData(List<TTTAEnrolledGraphDTO> TTTAEnrolledGraphDTO){
		List<ChartData> list = new ArrayList<ChartData>();
		
		for (TTTAEnrolledGraphDTO item : TTTAEnrolledGraphDTO) {
			list.add(this.TTTAEnrolledGraphDTOtoChartData(item));
		}
		
		return list;
	}
	
	public List<ChartData> CustomerFirstGraphDTOtoChartData(List<CustomerFirstGraphDTO> CustomerFirstGraphDTO){
		List<ChartData> list = new ArrayList<ChartData>();
		
		for (CustomerFirstGraphDTO item : CustomerFirstGraphDTO) {
			list.add(this.CustomerFirstGraphDTOtoChartData(item));
		}
		
		return list;
	}
	
	public List<ChartData> SIRewardsYOYGraphDTOtoChartData(List<SIRewardsYOYGraphDTO> SIRewardsYOYGraphDTO){
		List<ChartData> list = new ArrayList<ChartData>();
		
		for (SIRewardsYOYGraphDTO item : SIRewardsYOYGraphDTO) {
			list.add(this.SIRewardsYOYGraphDTOtoChartData(item));
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
	
	
	public ChartData MapMSERGraphDTOtoChartData(MyfcaMSERTotalEarningsDTO MyfcaMSERTotalEarningsDTO){
		ChartData chartData = new ChartData();
		
		chartData.setName(MyfcaMSERTotalEarningsDTO.getChild());
		
		List<ChartData> data = new ArrayList<ChartData>();
		ChartData MoparParts , mvp, MagnetiMarelli, PartsCounter, ExpressLane, wiAdvisor, uConnect;
		
		MoparParts = new ChartData("Mopar Parts", 0);
		mvp = new ChartData("MVP",0);
		MagnetiMarelli = new ChartData("Magneti Marelli",0);
		PartsCounter = new ChartData("Part Counter", 0);
		wiAdvisor = new ChartData("wiAdvisor", 0);
		ExpressLane = new ChartData("Express Lane", 0);
		uConnect = new ChartData("uConnect", 0);
		
		if(MyfcaMSERTotalEarningsDTO.getProgram().equals("Express Lane")){
			ExpressLane.setValue(MyfcaMSERTotalEarningsDTO.getAmount());
    	}else if(MyfcaMSERTotalEarningsDTO.getProgram().equals("Magneti Marelli")){
    		MagnetiMarelli.setValue(MyfcaMSERTotalEarningsDTO.getAmount());
    	}else if(MyfcaMSERTotalEarningsDTO.getProgram().equals("Mopar Parts")){
    		MoparParts.setValue(MyfcaMSERTotalEarningsDTO.getAmount());
    	}else if(MyfcaMSERTotalEarningsDTO.getProgram().equals("MVP")){
    		mvp.setValue(MyfcaMSERTotalEarningsDTO.getAmount());
    	}else if(MyfcaMSERTotalEarningsDTO.getProgram().equals("Part Counter")){
    		PartsCounter.setValue(MyfcaMSERTotalEarningsDTO.getAmount());
    	}else if(MyfcaMSERTotalEarningsDTO.getProgram().equals("wiAdvisor")){
    		wiAdvisor.setValue(MyfcaMSERTotalEarningsDTO.getAmount());
    	}else if(MyfcaMSERTotalEarningsDTO.getProgram().equals("uConnect")){
    		uConnect.setValue(MyfcaMSERTotalEarningsDTO.getAmount());
    	}
		
		
		
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
	
	public ChartData MapTTTAEnrolledDTOtoChartData(TTTAEnrolledDTO TTTAEnrolledDTO){
		ChartData chartData = new ChartData();
		
		chartData.setName(TTTAEnrolledDTO.getTerritory());
		
		List<ChartData> data = new ArrayList<ChartData>();
		ChartData groupA , groupB, groupC, groupD, groupE;
		groupA = new ChartData("Group A", TTTAEnrolledDTO.getGroupA());
		groupB = new ChartData("Group B",TTTAEnrolledDTO.getGroupB());
		groupC = new ChartData("Group C",TTTAEnrolledDTO.getGroupC());
		groupD = new ChartData("Group D", TTTAEnrolledDTO.getGroupD());
		groupE = new ChartData("Group E", TTTAEnrolledDTO.getGroupE());
		
		data.add(groupA);
		data.add(groupB);
		data.add(groupC);
		data.add(groupD);
		data.add(groupE);
		
		chartData.setData(data);
		
		return chartData;
	}
	
	public ChartData BrainBoostWinndersGraphDTOtoChartDataWinners(BrainBoostWinndersGraphDTO BrainBoostWinndersGraphDTO){
		ChartData chartData = new ChartData();		
		chartData.setName(BrainBoostWinndersGraphDTO.getChildTerritory());
		chartData.setValue(BrainBoostWinndersGraphDTO.getWinners());
		return chartData;
	}
	
	public ChartData SIRewardsDetailsGraphDTOtoChartDataProjected(SIRewardsDetailsGraphDTO SIRewardsDetailsGraphDTO){
		ChartData chartData = new ChartData();		
		chartData.setName(SIRewardsDetailsGraphDTO.getParentTerritory());
		chartData.setValue(SIRewardsDetailsGraphDTO.getProjectedEarnings());
		return chartData;
	}
	
	public ChartData SIRewardsDetailsGraphDTOtoChartDataAverageScore(SIRewardsDetailsGraphDTO SIRewardsDetailsGraphDTO){
		ChartData chartData = new ChartData();		
		chartData.setName(SIRewardsDetailsGraphDTO.getParentTerritory());
		chartData.setValue(SIRewardsDetailsGraphDTO.getAvgSurveyScore());
		return chartData;
	}
	
	public ChartData SummaryProgramRewardGraphDTOtoChartDataAverageScore(SummaryProgramRewardGraphDTO SummaryProgramRewardGraphDTO){
		ChartData chartData = new ChartData();		
		chartData.setName(SummaryProgramRewardGraphDTO.getChild());
		chartData.setValue(SummaryProgramRewardGraphDTO.getEarnings());
		return chartData;
	}
	
	public ChartData RewardRedemptionGraphDTOtoChartDataEarnedPoints(RewardRedemptionGraphDTO RewardRedemptionGraphDTO){
		ChartData chartData = new ChartData();		
		chartData.setName(RewardRedemptionGraphDTO.getParentTerritory());
		chartData.setValue(RewardRedemptionGraphDTO.getEarnedPoints());
		return chartData;
	}
	
	public ChartData BrainBoostWinndersGraphDTOtoChartDataAwards(BrainBoostWinndersGraphDTO BrainBoostWinndersGraphDTO){
		ChartData chartData = new ChartData();
		//ChartData excellenceCard = new ChartData();
		ChartData awardPoints = new ChartData();
		
		chartData.setName(BrainBoostWinndersGraphDTO.getChildTerritory());
		
		//excellenceCard.setName("Exellence Card Awards");
		//excellenceCard.setValue(BrainBoostWinndersGraphDTO.getEarnings());
		
		awardPoints.setName("Award Points");
		awardPoints.setValue(BrainBoostWinndersGraphDTO.getPoints());
		
		//chartData.addData(excellenceCard);
		chartData.addData(awardPoints);
		
		return chartData;
	}
	
	public ChartData CertProfsExpertGraphDTOtoChartDataTotalPoints(CertProfsExpertGraphDTO CertProfsExpertGraphDTO){
		ChartData chartData = new ChartData();		
		chartData.setName(CertProfsExpertGraphDTO.getParentTerritory());
		chartData.setValue(CertProfsExpertGraphDTO.getTotalPoints());
		return chartData;
	}
	
	public ChartData CertProfsExpertGraphDTOtoChartDataCert(CertProfsExpertGraphDTO CertProfsExpertGraphDTO){
		ChartData chartData = new ChartData();		
		chartData.setName(CertProfsExpertGraphDTO.getCertType());
		chartData.setValue(CertProfsExpertGraphDTO.getCert());
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
		certifiedSpacialist.setValue(Double.parseDouble(CertProfsWinnersGraphDTO.getCertifiedSpecialist()));
		
		masterCertified.setName("Master Certified");
		masterCertified.setValue(Double.parseDouble(CertProfsWinnersGraphDTO.getMasterCertified()));
		
		chartData.addData(certified);
		chartData.addData(certifiedSpacialist);
		chartData.addData(masterCertified);
		
		chartData.setName(CertProfsWinnersGraphDTO.getParentTerritory());
		return chartData;
	}
	
	public ChartData TTTAEnrolledGraphDTOtoChartData(TTTAEnrolledGraphDTO TTTAEnrolledGraphDTO){
		ChartData chartData = new ChartData();
		ChartData certified = new ChartData();
		ChartData certifiedSpacialist = new ChartData();
		ChartData masterCertified = new ChartData();
		ChartData groupd = new ChartData();
		ChartData groupe = new ChartData();
		
		chartData.setName(TTTAEnrolledGraphDTO.getChild());
		
		certified.setName("Group A");
		certified.setValue(TTTAEnrolledGraphDTO.getTotalGroupA());
		
		certifiedSpacialist.setName("Group B");
		certifiedSpacialist.setValue(TTTAEnrolledGraphDTO.getTotalGroupB());
		
		masterCertified.setName("Group C");
		masterCertified.setValue(TTTAEnrolledGraphDTO.getTotalGroupC());
		
		groupd.setName("Group D");
		groupd.setValue(TTTAEnrolledGraphDTO.getTotalGroupD());
		
		groupe.setName("Group E");
		groupe.setValue(TTTAEnrolledGraphDTO.getTotalGroupE());
		
		chartData.addData(certified);
		chartData.addData(certifiedSpacialist);
		chartData.addData(masterCertified);
		chartData.addData(groupd);
		chartData.addData(groupe);
		
		return chartData;
	}
	
	public ChartData CustomerFirstGraphDTOtoChartData(CustomerFirstGraphDTO CustomerFirstGraphDTO){
		ChartData chartData = new ChartData();
		ChartData certified = new ChartData();
		ChartData certifiedSpacialist = new ChartData();
		ChartData voe = new ChartData();
		ChartData training = new ChartData();
		ChartData facility = new ChartData();
		ChartData masterCertified = new ChartData();
		ChartData CFAFEAwardCertification = new ChartData();
		
		chartData.setName(CustomerFirstGraphDTO.getChildTerritory());
		
		certified.setName("Level 0");
		certified.setValue(CustomerFirstGraphDTO.getNoCertification());
		
		certifiedSpacialist.setName("Performance");
		certifiedSpacialist.setValue(CustomerFirstGraphDTO.getPerformance());
		
		masterCertified.setName("Process");
		masterCertified.setValue(CustomerFirstGraphDTO.getProcess());
		
		voe.setName("Voice of Employee");
		voe.setValue(CustomerFirstGraphDTO.getVoiceofEmployee());
		
		training.setName("Training");
		training.setValue(CustomerFirstGraphDTO.getTraining());
		
		facility.setName("Facility");
		facility.setValue(CustomerFirstGraphDTO.getFacility());
		
		CFAFEAwardCertification.setName("CFAFE Award Certification");
		CFAFEAwardCertification.setValue(CustomerFirstGraphDTO.getCFAFEAwardCertification());
		
		
		chartData.addData(certified);
		chartData.addData(certifiedSpacialist);
		chartData.addData(masterCertified);
		chartData.addData(voe);
		chartData.addData(training);
		chartData.addData(facility);
		chartData.addData(CFAFEAwardCertification);
		
		return chartData;
	}
	
	public ChartData SIRewardsYOYGraphDTOtoChartData(SIRewardsYOYGraphDTO SIRewardsYOYGraphDTO){
		ChartData chartData = new ChartData();
		ChartData certified = new ChartData();
		ChartData certifiedSpacialist = new ChartData();
		String currentYear = new DateTime().getYear() + "";
		String lastYear = (new DateTime().getYear() -1) + "";
		chartData.setName(SIRewardsYOYGraphDTO.getParentTerritory());
		
		certified.setName(lastYear);
		certified.setValue(SIRewardsYOYGraphDTO.getLastYearEarnings());
		
		certifiedSpacialist.setName(currentYear);
		certifiedSpacialist.setValue(SIRewardsYOYGraphDTO.getCurrentYearEarnings());
		
		chartData.addData(certified);
		chartData.addData(certifiedSpacialist);
		
		chartData.setName(SIRewardsYOYGraphDTO.getParentTerritory());
		return chartData;
	}
	
	
	public TopTenTableData MapTTTATopNDTOtoTopTenTableData(List<TTTATopNDTO> TTTATopNDTO, String tableName, List<String> tableHeader){
		TopTenTableData topTenTableData = new TopTenTableData();
		
		topTenTableData.setTableHeader(tableHeader);
		topTenTableData.setTableName(tableName);
		
		List<Object> data = new ArrayList<Object>();
		
		for(TTTATopNDTO item: TTTATopNDTO){
			List<Object> items = new ArrayList<Object>();
			if(item.getError().equals("") || item.getError().equals(null)){
				items.add(item.getName());
				items.add(item.getDealerName());
				items.add(item.getParentTerritory());
				items.add(IMIServicesUtil.formatNumbers(item.getTotalSurveys()));
				items.add(IMIServicesUtil.formatNumbers(item.getAvgSurveyScore()));
			}
			data.add(items);
		}
		
		topTenTableData.setData(data);
		
		return topTenTableData;
	}
	
	public TopTenTableData MapMSERTopNDTOtoTopTenTableData(List<MyfcaMSERTopNDTO> MyfcaMSERTopNDTO, String tableName, List<String> tableHeader, String type){
		TopTenTableData topTenTableData = new TopTenTableData();
		
		topTenTableData.setTableHeader(tableHeader);
		topTenTableData.setTableName(tableName);
		
		List<Object> data = new ArrayList<Object>();
		if(type.equalsIgnoreCase("top10")){
		for(MyfcaMSERTopNDTO item: MyfcaMSERTopNDTO){
			List<Object> items = new ArrayList<Object>();
			if(item.getError().equals("") || item.getError().equals(null)){
				items.add(item.getName());
				items.add(item.getDealerName());
				items.add(item.getParentTerritory());
				items.add("$" + IMIServicesUtil.formatCurrency(item.getEarnings()));
			}
			data.add(items);
		}
		}else{
			for(MyfcaMSERTopNDTO item: MyfcaMSERTopNDTO){
				List<Object> items = new ArrayList<Object>();
				if(item.getError().equals("") || item.getError().equals(null)){
					items.add(item.getName());
					items.add(IMIServicesUtil.formatNumbers(item.getQuantity()));
				}
				data.add(items);
			}
		}
		
		topTenTableData.setData(data);
		
		return topTenTableData;
	}
	
	public TopTenTableData MapSIRewardsDetailsDTOtoTopTenTableData(List<SIRewardsDetailsDTO> SIRewardsDetailsDTO, String tableName, List<String> tableHeader){
		TopTenTableData topTenTableData = new TopTenTableData();
		
		topTenTableData.setTableHeader(tableHeader);
		topTenTableData.setTableName(tableName);
		
		DecimalFormat df = new DecimalFormat("0.0");
		List<Object> data = new ArrayList<Object>();
		for(SIRewardsDetailsDTO item: SIRewardsDetailsDTO){
			List<Object> items = new ArrayList<Object>();
				items.add(item.getName());
				items.add(IMIServicesUtil.formatCurrency(item.getAdvisorSurveys()));
				items.add(df.format(item.getDealerTarget()) + "%");
				if(item.getTrainingQualified()>0){
					items.add("Yes");
				}else{
					items.add("No");
				}
				if(item.getIncentiveQualified()>0){
					items.add("Yes");
				}else{
					items.add("No");
				}
				items.add("$" + IMIServicesUtil.formatCurrency(item.getProjectedEarnings()));
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
	
	
	/*public String formatCurrency(int number){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format((int)number);
		if (moneyString.endsWith(".00")) {
			int centsIndex = moneyString.lastIndexOf(".00");
			if (centsIndex != -1) {
				moneyString = moneyString.substring(1, centsIndex);
			}
		}

		return moneyString;
	}

	public String formatCurrency(double number){
		number = Math.rint(number);
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format((int)number);
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
	*/
}
