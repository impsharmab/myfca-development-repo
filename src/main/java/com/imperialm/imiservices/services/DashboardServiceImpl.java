package com.imperialm.imiservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperialm.imiservices.dao.ProgramCountDAOImpl;
import com.imperialm.imiservices.dao.SIRewardsYOYDetailsDAO;
import com.imperialm.imiservices.dao.MyFCAMserRankingDetailsDAO;
import com.imperialm.imiservices.dao.MyFCAMserRankingDAO;
import com.imperialm.imiservices.dao.SummaryProgramRewardDetailsDAO;
import com.imperialm.imiservices.dao.SummaryProgramRewardGraphDAO;
import com.imperialm.imiservices.dao.TTTAEnrollmentsSummaryDAO;
import com.imperialm.imiservices.dao.TTTAEnrolledGraphDAO;
import com.imperialm.imiservices.dao.CustomerFirstGraphDAO;
import com.imperialm.imiservices.dao.CustomerFirstDetailsDAO;
import com.imperialm.imiservices.dao.CertProfsExpertDetailsDAO;
import com.imperialm.imiservices.dao.BrainBoostWinnersDetailsDAO;
import com.imperialm.imiservices.dao.DealerPersonnelPositionsDAO;
import com.imperialm.imiservices.dao.UserPositionCodeRoleDAO;
import com.imperialm.imiservices.dao.CertProfsWinnersDetailsDAO;
import com.imperialm.imiservices.dao.RewardRedemptionDetailsDAO;
import com.imperialm.imiservices.dao.RewardRedemptionGraphDAO;
import com.imperialm.imiservices.dao.SIRewardsYOYGraphDAO;
import com.imperialm.imiservices.dao.RetentionDetailsDAO;
import com.imperialm.imiservices.dao.RetentionGraphDAO;
import com.imperialm.imiservices.dao.SIRewardsDetailsDAO;
import com.imperialm.imiservices.dao.SIRewardsDetailsGraphDAO;
import com.imperialm.imiservices.dao.MyfcaMSERTotalEarningsDAO;
import com.imperialm.imiservices.dao.TTTAEnrolledDAO;
import com.imperialm.imiservices.dao.TTTAEnrollmentsDAO;
import com.imperialm.imiservices.dao.MyfcaMSERTotalEarningsDetailsDAO;
import com.imperialm.imiservices.dao.BrainBoostWinndersGraphDAO;	
import com.imperialm.imiservices.dao.CertProfsExpertGraphDAO;
import com.imperialm.imiservices.dao.CertProfsWinnersGraphDAO;
import com.imperialm.imiservices.dto.BrainBoostWinndersGraphDTO;
import com.imperialm.imiservices.dto.BrainBoostWinnersDetailsDTO;
import com.imperialm.imiservices.dto.CertProfsExpertDetailsDTO;
import com.imperialm.imiservices.dto.CertProfsExpertGraphDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersDetailsDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersGraphDTO;
import com.imperialm.imiservices.dto.CustomerFirstDetailsDTO;
import com.imperialm.imiservices.dto.CustomerFirstGraphDTO;
import com.imperialm.imiservices.dto.MyFCAMserRankingDetailsDTO;
import com.imperialm.imiservices.dto.MyFCAMserRankingDTO;
import com.imperialm.imiservices.dto.MyfcaMSERTotalEarningsDTO;
import com.imperialm.imiservices.dto.MyfcaMSERTotalEarningsDetailsDTO;
import com.imperialm.imiservices.dto.MyfcaMSERTopNDTO;
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
import com.imperialm.imiservices.dto.TTTAEnrolledDTO;
import com.imperialm.imiservices.dto.TTTAEnrolledGraphDTO;
import com.imperialm.imiservices.dto.TTTAEnrollmentsDTO;
import com.imperialm.imiservices.dto.TTTAEnrollmentsSummaryDTO;
import com.imperialm.imiservices.dto.TTTATopNDTO;
import com.imperialm.imiservices.dto.UserPositionCodeRoleDTO;
import com.imperialm.imiservices.model.response.TotalName;
import com.imperialm.imiservices.dao.MyfcaMSERTopNDAO;
import com.imperialm.imiservices.dao.TTTATopNDAO;

@Service // implements DashboardService
public class DashboardServiceImpl {

	@Autowired
	private ProgramCountDAOImpl ProgramCountDAOImpl;
	
	@Autowired
	private SIRewardsYOYDetailsDAO SIRewardsYOYDetailsDAO;
	
	@Autowired
	private MyfcaMSERTopNDAO MyfcaMSERTopNDAO;
	
	@Autowired
	private MyfcaMSERTotalEarningsDetailsDAO MyfcaMSERTotalEarningsDetailsDAO;
	
	@Autowired
	private BrainBoostWinndersGraphDAO BrainBoostWinndersGraphDAO;
	
	@Autowired
	private CertProfsExpertGraphDAO CertProfsExpertGraphDAO;
	
	@Autowired
	private BrainBoostWinnersDetailsDAO BrainBoostWinnersDetailsDAO;
	
	@Autowired
	private CertProfsWinnersGraphDAO CertProfsWinnersGraphDAO;
	
	@Autowired
	private TTTATopNDAO TTTATopNDAO;
	
	@Autowired
	private TTTAEnrollmentsDAO TTTAEnrollmentsDAO;
	
	@Autowired
	private TTTAEnrolledDAO TTTAEnrolledDAO;
	
	@Autowired
	private MyfcaMSERTotalEarningsDAO MyfcaMSERTotalEarningsDAO;
	
	@Autowired
	private SIRewardsDetailsGraphDAO SIRewardsDetailsGraphDAO;
	
	@Autowired
	private SIRewardsDetailsDAO SIRewardsDetailsDAO;

	@Autowired
	private RetentionGraphDAO RetentionGraphDAO;
	
	@Autowired
	private RetentionDetailsDAO RetentionDetailsDAO;
	
	@Autowired
	private SIRewardsYOYGraphDAO SIRewardsYOYGraphDAO;
	
	@Autowired
	private RewardRedemptionGraphDAO RewardRedemptionGraphDAO;
	
	@Autowired
	private RewardRedemptionDetailsDAO RewardRedemptionDetailsDAO;

	@Autowired
	private CertProfsWinnersDetailsDAO CertProfsWinnersDetailsDAO;

	@Autowired
	private UserPositionCodeRoleDAO UserPositionCodeRoleDAO;

	@Autowired
	private DealerPersonnelPositionsDAO DealerPersonnelPositionsDAO;
	
	@Autowired
	private CertProfsExpertDetailsDAO CertProfsExpertDetailsDAO;
	
	@Autowired
	private CustomerFirstDetailsDAO CustomerFirstDetailsDAO;
	
	@Autowired
	private CustomerFirstGraphDAO CustomerFirstGraphDAO;
	
	@Autowired
	private TTTAEnrolledGraphDAO TTTAEnrolledGraphDAO;
	
	@Autowired
	private TTTAEnrollmentsSummaryDAO TTTAEnrollmentsSummaryDAO;
	
	@Autowired
	private SummaryProgramRewardGraphDAO SummaryProgramRewardGraphDAO;
	
	@Autowired
	private SummaryProgramRewardDetailsDAO SummaryProgramRewardDetailsDAO;
	
	@Autowired
	private MyFCAMserRankingDAO MyFCAMserRankingDAO;
	
	@Autowired
	private MyFCAMserRankingDetailsDAO MyFCAMserRankingDetailsDAO;
	
	public List<MyfcaMSERTopNDTO> getMSERTopTen(String type, int rows, String name, String period){
		 return this.MyfcaMSERTopNDAO.getTopNByType(type, rows, name, period);
	}
	
	public List<MyfcaMSERTopNDTO> getTopNByTypeINMSERTABLE(String type, int rows, String name, String period){
		 return this.MyfcaMSERTopNDAO.getTopNByTypeINMSERTABLE(type, rows, name, period);
	}
	
	public TotalName getMTDByProgramAndProgramgroup(String name, String program, String programgroup){
		 return this.MyfcaMSERTotalEarningsDetailsDAO.getMTDByProgramAndProgramgroup(name, program, programgroup);
	}
	
	public TotalName getMSEREnrolledDealersCount(){
		 return this.MyfcaMSERTotalEarningsDetailsDAO.getMSEREnrolledDealersCount();
	}
	
	public TotalName getDealersCountWithPercentage(){
		 return this.MyfcaMSERTotalEarningsDetailsDAO.getDealersCountWithPercentage();
	}
	
	public TotalName getMSERDealersCountByBCOrDistrict(String territory){
		 return this.MyfcaMSERTotalEarningsDetailsDAO.getMSERDealersCountByBCOrDistrict(territory);
	}
	
	public TotalName getMSEREarningsYTDByBCOrDistrict(String territory){
		 return this.MyfcaMSERTotalEarningsDetailsDAO.getMSEREarningsYTDByBCOrDistrict(territory);
	}
	
	public TotalName getMSEREarningsMTDByBCOrDistrict(String territory){
		 return this.MyfcaMSERTotalEarningsDetailsDAO.getMSEREarningsMTDByBCOrDistrict(territory);
	}
	
	public TotalName getMSERParticipantEnrolledByDealerCode(String dealerCode){
		 return this.MyfcaMSERTotalEarningsDetailsDAO.getMSERParticipantEnrolledByDealerCode(dealerCode);
	}
	
	
	public TotalName getYTDByProgramAndProgramgroup(String name, String program, String programgroup){
		 return this.MyfcaMSERTotalEarningsDetailsDAO.getYTDByProgramAndProgramgroup(name, program, programgroup);
	}
	
	public TotalName getParticipantExcellanceCardAwardMTD(String sid, String dealerCode){
		 return this.MyfcaMSERTotalEarningsDetailsDAO.getParticipantExcellanceCardAwardMTD(sid, dealerCode);
	}
	
	public TotalName getParticipantExcellanceCardAwardYTD(String sid, String dealerCode){
		 return this.MyfcaMSERTotalEarningsDetailsDAO.getParticipantExcellanceCardAwardYTD(sid, dealerCode);
	}
	
	public List<BrainBoostWinndersGraphDTO> getBrainBoostGraphBCData(boolean filter){
		 return this.BrainBoostWinndersGraphDAO.getBCData(filter);
	}
	
	public List<BrainBoostWinndersGraphDTO> getBrainBoostGraphAllDistricData(List<String> list){
		 return this.BrainBoostWinndersGraphDAO.getAllDistricData(list);
	}
	
	public List<BrainBoostWinndersGraphDTO> getBrainBoostWinddersGraphByTerritory(List<String> list){
		 return this.BrainBoostWinndersGraphDAO.getByTerritory(list);
	}
	
	public List<BrainBoostWinndersGraphDTO> getBrainBoostWinndersGraphgetByChildTerritory(List<String> list){
		 return this.BrainBoostWinndersGraphDAO.getByChildTerritory(list);
	}
	
	public List<BrainBoostWinndersGraphDTO> getBrainBoostWinndersGraphgetByChildTerritory(String list){
		 return this.BrainBoostWinndersGraphDAO.getByChildTerritory(list);
	}
	
	public List<BrainBoostWinnersDetailsDTO> getBrainBoostWinnersDetailsDTOBySID(String sID, String toggle, String dealerCode){
		 return this.BrainBoostWinnersDetailsDAO.getBrainBoostWinnersDetailsBySID(sID, toggle, dealerCode);
	}
	
	public List<BrainBoostWinnersDetailsDTO> getBrainBoostWinnersDetailsDTOByDealerCode(String dealerCode, String toggle){
		 return this.BrainBoostWinnersDetailsDAO.getBrainBoostWinnersDetailsByDealerCode(dealerCode, toggle);
	}
	
	public List<BrainBoostWinnersDetailsDTO> getBrainBoostWinnersDetailsDTOSUMByDealerCode(String dealerCode, String toggle){
		 return this.BrainBoostWinnersDetailsDAO.getBrainBoostWinnersDetailsSUMByDealerCode(dealerCode, toggle);
	}
	
	public List<CertProfsWinnersGraphDTO> getCertProfsWinnersGraphAllDistricData(List<String> list)
	{
		return this.CertProfsWinnersGraphDAO.getAllDistricData(list);
	}
	
	public List<CertProfsWinnersGraphDTO> getCertProfsWinnersGraphByTerritory(List<String> list)
	{
		return this.CertProfsWinnersGraphDAO.getByTerritory(list);
	}
	
	public List<CertProfsWinnersGraphDTO> getCertProfsWinnersGraphByChildTerritory(List<String> list)
	{
		return this.CertProfsWinnersGraphDAO.getByChildTerritory(list);
	}
	
	public List<CertProfsWinnersGraphDTO> getCertProfsWinnersGraphByChildTerritory(String list)
	{
		return this.CertProfsWinnersGraphDAO.getByChildTerritory(list);
	}
	
	public List<CertProfsExpertGraphDTO> getExpertPointsEarned(){
		return this.CertProfsExpertGraphDAO.getExpertPointsEarned();
	}
	
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgram(){
		return this.CertProfsExpertGraphDAO.getParticipantCompletedByProgram();
	}
	
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgramByChildTerritory(List<String> filters){
		return this.CertProfsExpertGraphDAO.getParticipantCompletedByProgramByChildTerritory(filters);
	}
	
	public List<CertProfsExpertGraphDTO> getExpertPointsEarnedByChildTerritory(List<String> filters){
		return this.CertProfsExpertGraphDAO.getExpertPointsEarnedByChildTerritory(filters);
	}
	
	public List<CertProfsExpertGraphDTO> getExpertPointsEarnedByChildTerritory(String filters){
		return this.CertProfsExpertGraphDAO.getExpertPointsEarnedByChildTerritory(filters);
	}
	
	public List<CertProfsExpertGraphDTO> getExpertPointsEarnedByChildTerritoryAsParent(List<String> filters){
		return this.CertProfsExpertGraphDAO.getExpertPointsEarnedByChildTerritoryAsParent(filters);
	}
	
	public List<CertProfsExpertGraphDTO> getExpertPointsEarnedByParentTerritorySum(List<String> filters){
		return this.CertProfsExpertGraphDAO.getExpertPointsEarnedByParentTerritorySum(filters);
	}
	
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgramByChildTerritoryAndCertType(List<String> filters, String certType){
		return this.CertProfsExpertGraphDAO.getParticipantCompletedByProgramByChildTerritoryAndCertType(filters, certType);
	}
	
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgramByChildTerritoryAndCertType(String filters, String certType){
		return this.CertProfsExpertGraphDAO.getParticipantCompletedByProgramByChildTerritoryAndCertType(filters, certType);
	}
	
	public List<CertProfsExpertGraphDTO> getExpertPointsEarnedByParentTerritory(List<String> filters){
		return this.CertProfsExpertGraphDAO.getExpertPointsEarnedByParentTerritory(filters);
	}
	
	public List<CertProfsWinnersGraphDTO> getBCCertifications(boolean filter){
		return this.CertProfsWinnersGraphDAO.getBCCertifications(filter);
	}
	
	public List<TTTATopNDTO> getTTTATopN(String type, int rows){
		 return this.TTTATopNDAO.getTopNByType(type, rows);
	}
	
	public TotalName getTTTAEnrollmentCount(){
		return this.TTTAEnrollmentsDAO.getTTTAEnrollmentCount();
	}
	
	public TotalName getTTTAIncentiveEligibleSUM(){
		return this.TTTAEnrollmentsDAO.getTTTAIncentiveEligibleSUM();
	}
	
	public List<TTTAEnrolledDTO> getTTTAEnrollmentsBC(boolean enrolled){
		return this.TTTAEnrolledDAO.getTTTAEnrollmentsBC(enrolled);
	}
	
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphBCData(boolean filter){
		return this.MyfcaMSERTotalEarningsDAO.getBCEarnings(filter);
	}
	
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphAllDistricData(List<String> list){
		return this.MyfcaMSERTotalEarningsDAO.getAllDistricData(list);
	}
	
	public List<MyFCAMserRankingDTO> getMSERDetailsGraphByParentAndToggle(String territory){
		return this.MyFCAMserRankingDAO.getMSERDetailsGraphByParent(territory);
	}
	
	public List<MyFCAMserRankingDTO> getMSERDetailsGraphByChildAndToggle(String territory){
		return this.MyFCAMserRankingDAO.getMSERDetailsGraphByChild(territory);
	}
	
	public List<MyFCAMserRankingDetailsDTO> getMSERDetailsBySID(String territory, String dealerCode){
		return this.MyFCAMserRankingDetailsDAO.getMSERDetailsBySID(territory, dealerCode);
	}
	
	public List<MyFCAMserRankingDetailsDTO> getMSERDetailsSUMBySID(String territory, String dealerCode){
		return this.MyFCAMserRankingDetailsDAO.getMSERDetailsSUMBySID(territory, dealerCode);
	}
	
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphByTerritoryAndToggle(String territory, String toggle){
		return this.MyfcaMSERTotalEarningsDAO.getMSERGraphByTerritoryAndToggle(territory, toggle);
	}
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphByTerritoryAndToggleAndProgram(String territory, String toggle, String program){
		return this.MyfcaMSERTotalEarningsDAO.getMSERGraphByTerritoryAndToggleAndProgram(territory, toggle, program);
	}
	
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphByChildTerritoryAndToggleAndProgram(String territory, String toggle, String program){
		return this.MyfcaMSERTotalEarningsDAO.getMSERGraphByChildTerritoryAndToggleAndProgram(territory, toggle, program);
	}
	
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphByChildTerritoryAndToggle(String territory, String toggle){
		return this.MyfcaMSERTotalEarningsDAO.getMSERGraphByChildTerritoryAndToggle(territory, toggle);
	}
	
	public List<String> getMSERGraphDistinctProgramsByParentTerritoryAndToggle(String territory, String toggle){
		return this.MyfcaMSERTotalEarningsDAO.getMSERGraphDistinctProgramsByParentTerritoryAndToggle(territory, toggle);
	}
	
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphProgramsSUMByParentTerritoryAndToggle(String territory, String toggle){
		return this.MyfcaMSERTotalEarningsDAO.getMSERGraphProgramsSUMByParentTerritoryAndToggle(territory, toggle);
	}
	
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphProgramsSUMByChildTerritoryAndToggle(String territory, String toggle){
		return this.MyfcaMSERTotalEarningsDAO.getMSERGraphProgramsSUMByChildTerritoryAndToggle(territory, toggle);
	}
	
	public List<SIRewardsDetailsGraphDTO> getSIRewardsDetailsGraphByTerritoryAndToggle(List<String> territory, String toggle){
		return this.SIRewardsDetailsGraphDAO.getSIRewardsDetailsGraphByTerritoryAndToggle(territory, toggle);
	}
	
	public List<SIRewardsDetailsGraphDTO> getSIRewardsDetailsGraphByTerritoryAndToggle(String territory, String toggle){
		return this.SIRewardsDetailsGraphDAO.getSIRewardsDetailsGraphByTerritoryAndToggle(territory, toggle);
	}
	
	public List<SIRewardsDetailsGraphDTO> getSIRewardsDetailsGraphByChildTerritoryAndToggle(String territory, String toggle){
		return this.SIRewardsDetailsGraphDAO.getSIRewardsDetailsGraphByChildTerritoryAndToggle(territory, toggle);
	}
	
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsBySID(String sID, String dealerCode, String quarter){
		return this.SIRewardsDetailsDAO.getSIRewardsDetailsBySID(sID, dealerCode, quarter);
	}
	
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsByDealerCodeAndToggle(String dealerCode, String toggle, String quarter){
		return this.SIRewardsDetailsDAO.getSIRewardsDetailsByDealerCodeAndToggle(dealerCode, toggle, quarter);
	}
	
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsBySIDAndToggle(String sID, String toggle, String dealerCode, String quarter){
		return this.SIRewardsDetailsDAO.getSIRewardsDetailsBySIDAndToggle(sID, toggle, dealerCode, quarter);
	}
	
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsByDealerCodeAndToggleAndPositionCode(String dealerCode, String toggle, String quarter, String positionCode){
		return this.SIRewardsDetailsDAO.getSIRewardsDetailsByDealerCodeAndToggleAndPositionCode(dealerCode, toggle, quarter, positionCode);
	}
	
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsByDealerCodeAndToggleAndPositionCode(String dealerCode, String toggle, String quarter, List<String> positionCode){
		return this.SIRewardsDetailsDAO.getSIRewardsDetailsByDealerCodeAndToggleAndPositionCode(dealerCode, toggle, quarter, positionCode);
	}
	
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsByDealerCodeAndToggleAndPositionCode(List<String> dealerCode, String toggle, String quarter, List<String> positionCode){
		return this.SIRewardsDetailsDAO.getSIRewardsDetailsByDealerCodeAndToggleAndPositionCode(dealerCode, toggle, quarter, positionCode);
	}
	
	public List<RetentionGraphDTO> getRetentionGraphByParentTerritoryList(List<String> list){
		return this.RetentionGraphDAO.getRetentionGraphByParentTerritoryList(list);
	}
	
	public List<RetentionGraphDTO> getRetentionGraphByChildTerritoryList(List<String> list){
		return this.RetentionGraphDAO.getRetentionGraphByChildTerritoryList(list);
	}
	
	public List<RetentionGraphDTO> getRetentionGraphByParentTerritoryListAndPositionCode(List<String> list, String positionCode){
		return this.RetentionGraphDAO.getRetentionGraphByParentTerritoryListAndPositionCode(list, positionCode);
	}
	
	public List<RetentionGraphDTO> getRetentionGraphByChildTerritoryListAndPositionCode(List<String> list, String positionCode){
		return this.RetentionGraphDAO.getRetentionGraphByChildTerritoryListAndPositionCode(list, positionCode);
	}
	
	public List<RetentionGraphDTO> getRetentionGraphByChildTerritoryListAndPositionCode(String list, String positionCode){
		return this.RetentionGraphDAO.getRetentionGraphByChildTerritoryListAndPositionCode(list, positionCode);
	}
	
	public List<RetentionGraphDTO> getRetentionGraphNATByPositionCode(String positionCode){
		return this.RetentionGraphDAO.getRetentionGraphNATByPositionCode(positionCode);
	}

	public List<RetentionGraphDTO> getRetentionGraphBCByBcAndPositionCodeByPositionCode(String bc, String positionCode){
		return this.RetentionGraphDAO.getRetentionGraphBCByBcAndPositionCodeByPositionCode(bc, positionCode);
	}
	
	public List<RetentionDetailsDTO> getRetentionDetailsByDealerCode(String dealersCode){
		return this.RetentionDetailsDAO.getRetentionDetails(dealersCode);
	}
	
	public List<SIRewardsYOYGraphDTO> getSIRewardsYOYGraphByTerritoryAndToggle(List<String> territory, String toggle){
		return this.SIRewardsYOYGraphDAO.getSIRewardsYOYGraphByTerritoryAndToggle(territory, toggle);
	}
	
	public List<SIRewardsYOYGraphDTO> getSIRewardsYOYGraphByTerritoryAndToggle(String territory, String toggle){
		return this.SIRewardsYOYGraphDAO.getSIRewardsYOYGraphByTerritoryAndToggle(territory, toggle);
	}
	
	public List<SIRewardsYOYGraphDTO> getSIRewardsYOYGraphByChildAndToggle(String territory, String toggle){
		return this.SIRewardsYOYGraphDAO.getSIRewardsYOYGraphByChildAndToggle(territory, toggle);
	}
	
	public List<SIRewardsYOYGraphDTO> getSIRewardsYOYGraphByTerritoryAndToggleFilterParent(List<String> territory, String toggle){
		return this.SIRewardsYOYGraphDAO.getSIRewardsYOYGraphByTerritoryAndToggleFilterParent(territory, toggle);
	}
	
	public List<SIRewardsDetailsGraphDTO> getSIRewardsDetailsGraphByTerritoryAndToggleFilterParent(List<String> territory, String toggle){
		return this.SIRewardsDetailsGraphDAO.getSIRewardsDetailsGraphByTerritoryAndToggleFilterParent(territory, toggle);
	}
	
	public List<SIRewardsDetailsGraphDTO> getSIRewardsDetailsGraphByChildTerritoryAndToggle(List<String> territory, String toggle){
		return this.SIRewardsDetailsGraphDAO.getSIRewardsDetailsGraphByChildTerritoryAndToggle(territory, toggle);
	}
	
	public List<SIRewardsDetailsGraphDTO> getSIRewardsDetailsGraphSUMByTerritoryAndToggle(String territory, String toggle){
		return this.SIRewardsDetailsGraphDAO.getSIRewardsDetailsGraphSUMByTerritoryAndToggle(territory, toggle);
	}
	
	public List<RewardRedemptionGraphDTO> getRewardRedemptionGraphByParentTerritoryListDistinct(List<String> territory){
		return this.RewardRedemptionGraphDAO.getRewardRedemptionGraphByParentTerritoryListDistinct(territory);
	}
	
	public List<RewardRedemptionGraphDTO> getRewardRedemptionGraphByChildTerritoryList(List<String> territory){
		return this.RewardRedemptionGraphDAO.getRewardRedemptionGraphByChildTerritoryList(territory);
	}
	
	public List<RewardRedemptionGraphDTO> getRewardRedemptionGraphByChildTerritory(String territory){
		return this.RewardRedemptionGraphDAO.getRewardRedemptionGraphByChildTerritory(territory);
	}
	
	public List<RewardRedemptionGraphDTO> getRewardRedemptionGraphByParentTerritoryList(List<String> territory){
		return this.RewardRedemptionGraphDAO.getRewardRedemptionGraphByParentTerritoryList(territory);
	}
	
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsByDealer(String dealerCode){
		return this.RewardRedemptionDetailsDAO.getRewardRedemptionDetailsByDealer(dealerCode);
	}
	
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsBySid(String sid, String dealerCode){
		return this.RewardRedemptionDetailsDAO.getRewardRedemptionDetailsBySid(sid, dealerCode);
	}	
	
	public List<CertProfsWinnersDetailsDTO> getCertProfsWinnersDetailsByDealerCode(String dealerCode){
		return this.CertProfsWinnersDetailsDAO.getCertProfsWinnersDetailsByDealerCode(dealerCode);
	}
	
	public List<CertProfsWinnersDetailsDTO> getCertProfsWinnersDetailsByDealerCodeGroupBySID(String dealerCode){
		return this.CertProfsWinnersDetailsDAO.getCertProfsWinnersDetailsByDealerCodeGroupBySID(dealerCode);
	}
	public List<CertProfsWinnersDetailsDTO> getCertProfsWinnersDetailsBySID(String sid, String dealerCode){
		return this.CertProfsWinnersDetailsDAO.getCertProfsWinnersDetailsBySID(sid, dealerCode);
	}
	
	public List<UserPositionCodeRoleDTO> getDealerCodePCRoleBySid(String sid){
		return this.UserPositionCodeRoleDAO.getDealerCodePCRoleBySid(sid);
	}
	
	public List<String> getDistrictByDealerCode(String dealerCode){
		return this.UserPositionCodeRoleDAO.getDistrictByDealerCode(dealerCode);
	}
	
	public List<String> getBCByDealerCode(String dealerCode){
		return this.UserPositionCodeRoleDAO.getBCByDealerCode(dealerCode);
	}
	
	public int getRoleByPositionCode(String positionCode){
		return this.DealerPersonnelPositionsDAO.getRoleByPositionCode(positionCode);
	}	
	
	public List<String> getUserTerritoyById(String sid){
		return this.UserPositionCodeRoleDAO.getUserTerritoyById(sid);
	}
	
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsByDealerCodeANDCertType(String dealerCode, String certType){
		return this.CertProfsExpertDetailsDAO.getCertProfsExpertDetailsByDealerCodeANDCertType(dealerCode, certType);
	}
	
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsBySIDANDCertType(String sid, String certType, String dealerCode){
		return this.CertProfsExpertDetailsDAO.getCertProfsExpertDetailsBySIDANDCertType(sid, certType, dealerCode);
	}
	
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsSUMBySID(String sid, String dealerCode){
		return this.CertProfsExpertDetailsDAO.getCertProfsExpertDetailsSUMBySID(sid, dealerCode);
	}
	
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsSUMByDealerCode(String dealerCode){
		return this.CertProfsExpertDetailsDAO.getCertProfsExpertDetailsSUMByDealerCode(dealerCode);
	}
	
	/*public List<CustomerFirstDetailsDTO> getCustomerFirstByParentAndToggle(List<String> territory, String toggle)
	{
		return this.CustomerFirstDetailsDAO.getCustomerFirstByParentAndToggle(territory, toggle);
	}
	public List<CustomerFirstDetailsDTO> getCustomerFirstByChildAndToggle(List<String> territory, String toggle){
		return this.CustomerFirstDetailsDAO.getCustomerFirstByChildAndToggle(territory, toggle);
	}*/
	
	public List<RetentionGraphDTO> getRetentionGraphNAT(){
		return this.RetentionGraphDAO.getRetentionGraphNAT();
	}
	
	public List<CustomerFirstGraphDTO> getCustomerFirstGraphByParentTerritoryAndToggle(List<String> territory, String toggle){
		return this.CustomerFirstGraphDAO.getCustomerFirstByParentTerritoryAndToggle(territory, toggle);
	}
	
	public List<CustomerFirstGraphDTO> getCustomerFirstGraphByChildTerritoryAndToggle(List<String> territory, String toggle){
		return this.CustomerFirstGraphDAO.getCustomerFirstByChildTerritoryAndToggle(territory, toggle);
	}
	
	public List<CustomerFirstGraphDTO> getCustomerFirstGraphByChildTerritoryAndToggle(String territory, String toggle){
		return this.CustomerFirstGraphDAO.getCustomerFirstByChildTerritoryAndToggle(territory, toggle);
	}
	
	public List<TTTAEnrolledGraphDTO> getTTTAEnrolledByParentTerritory(List<String> territory){
		return this.TTTAEnrolledGraphDAO.getTTTAEnrolledByParentTerritory(territory);
	}
	
	public List<TTTAEnrolledGraphDTO> getTTTAEnrolledByParentTerritoryNotEnrolled(List<String> territory){
		return this.TTTAEnrolledGraphDAO.getTTTAEnrolledByParentTerritoryNotEnrolled(territory);
	}
	
	public List<TTTAEnrolledGraphDTO> getTTTAEnrolledByChildTerritory(String territory){
		return this.TTTAEnrolledGraphDAO.getTTTAEnrolledByChildTerritory(territory);
	}
	
	public int getTTTAEnrolledDealersNAT(){
		return this.TTTAEnrolledGraphDAO.getTTTAEnrolledDealersNAT();
	}
	
	public int getTTTAEnrolledBCORDistrict(String territory){
		return this.TTTAEnrolledGraphDAO.getTTTAEnrolledBCORDistrict(territory);
	}
	
	public List<TTTAEnrolledGraphDTO> getTTTAEnrolledByChildTerritoryNotEnrolled(String territory){
		return this.TTTAEnrolledGraphDAO.getTTTAEnrolledByChildTerritoryNotEnrolled(territory);
	}
	
	public int getTTTAEnrolledDealersNATNotEnrolled(){
		return this.TTTAEnrolledGraphDAO.getTTTAEnrolledDealersNATNotEnrolled();
	}
	
	public int getTTTAEnrolledBCORDistrictNotEnrolled(String territory){
		return this.TTTAEnrolledGraphDAO.getTTTAEnrolledBCORDistrictNotEnrolled(territory);
	}
	
	public List<TTTAEnrollmentsSummaryDTO> getTTTAEnrollmentsSummaryByChildAndPositionCode(String territories, String positionCode){
		return this.TTTAEnrollmentsSummaryDAO.getTTTAEnrollmentsSummaryByChildAndPositionCode(territories, positionCode);
	}

	public List<TTTAEnrollmentsSummaryDTO> getTTTAEnrollmentsSummaryByParentAndPositionCode(List<String> territories, String positionCode){
		return this.TTTAEnrollmentsSummaryDAO.getTTTAEnrollmentsSummaryByParentAndPositionCode(territories, positionCode);
	}
	
	public TotalName getTTTANATTopTechEnrolledDealerCount(){
		return this.TTTAEnrollmentsSummaryDAO.getTTTANATTopTechEnrolledDealerCount();
	}
	
	public TotalName getTTTANATTopAdvisorEnrolledDealerCount(){
		return this.TTTAEnrollmentsSummaryDAO.getTTTANATTopAdvisorEnrolledDealerCount();
	}
	
	public TotalName getTTTANATTopEnrolledDealerCountByBCDistrict(String territory){
		return this.TTTAEnrollmentsSummaryDAO.getTTTANATTopEnrolledDealerCountByBCDistrictAndPositionCode(territory);
	}
	
	
	public TotalName getTTTANATTopTechEnrolledIncentiveEligible(){
		return this.TTTAEnrollmentsSummaryDAO.getTTTANATTopTechEnrolledIncentiveEligible();
	}
	
	public TotalName getTTTANATTopAdvisorEnrolledIncentiveEligible(){
		return this.TTTAEnrollmentsSummaryDAO.getTTTANATTopAdvisorEnrolledIncentiveEligible();
	}
	
	public List<TTTAEnrollmentsSummaryDTO> getTTTAEnrollmentsSummarySUMByParentAndPositionCode(List<String> territories, String positionCode){
		return this.TTTAEnrollmentsSummaryDAO.getTTTAEnrollmentsSummarySUMByParentAndPositionCode(territories, positionCode);
	}
	
	public List<TTTAEnrollmentsDTO> getTTTAEnrollmentsBySID(String sid, String positionCode, String  dealerCode){
		return this.TTTAEnrollmentsDAO.getTTTAEnrollmentsBySID(sid, positionCode,  dealerCode);
	}
	public List<TTTAEnrollmentsDTO> getTTTAEnrollmentsByDealerCode(String dealerCode, String positionCode){
		return this.TTTAEnrollmentsDAO.getTTTAEnrollmentsByDealerCode(dealerCode, positionCode);
	}
	
	public List<TTTAEnrollmentsDTO> getTTTAEnrollmentsByDealerCode(String dealerCode, String positionCode, String enrollement){
		return this.TTTAEnrollmentsDAO.getTTTAEnrollmentsByDealerCodeAndEnrollement(dealerCode, enrollement, positionCode);
	}
	
	public List<SummaryProgramRewardGraphDTO> getSummaryProgramRewardGraphByParentTerritoryYTD(List<String> territory){
		return this.SummaryProgramRewardGraphDAO.getSummaryProgramRewardGraphByParentTerritoryYTD(territory);
	}
	
	/*public List<SummaryProgramRewardGraphDTO> getSummaryProgramRewardGraphByChildTerritoryYTD(List<String> territory){
		return this.SummaryProgramRewardGraphDAO.getSummaryProgramRewardGraphByChildTerritoryYTD(territory);
	}*/
	
	public List<SummaryProgramRewardGraphDTO> getSummaryProgramRewardGraphByChildTerritoryYTD(String territory){
		return this.SummaryProgramRewardGraphDAO.getSummaryProgramRewardGraphByChildTerritoryYTD(territory);
	}
	
	public List<SummaryProgramRewardGraphDTO> getSummaryProgramRewardDetailsBySIDYTD(String territory, String dealerCode){
		return this.SummaryProgramRewardDetailsDAO.getSummaryProgramRewardDetailsBySIDYTD(territory, dealerCode);
	}
	
	public List<SummaryProgramRewardDetailsDTO> getSummaryProgramRewardDetailsByDealerCodeYTD(String territory){
		return this.SummaryProgramRewardDetailsDAO.getSummaryProgramRewardDetailsByDealerCodeYTD(territory);
	}
	
	public List<MyfcaMSERTotalEarningsDetailsDTO> getMSERGraphDetailsByDealerCode(String dealerCode){
		return this.MyfcaMSERTotalEarningsDetailsDAO.getMSERGraphDetailsByDealerCode(dealerCode);
	}
	
	public List<MyfcaMSERTotalEarningsDetailsDTO> getMSERGraphDetailsBySID(String sid, String dealerCode){
		return this.MyfcaMSERTotalEarningsDetailsDAO.getMSERGraphDetailsBySID(sid, dealerCode);
	}
	
	public List<MyfcaMSERTotalEarningsDetailsDTO> getMSERGraphDetailsSUMBySID(String sid, String dealerCode){
		return this.MyfcaMSERTotalEarningsDetailsDAO.getMSERGraphDetailsSUMBySID(sid, dealerCode);
	}
	
	public List<CustomerFirstDetailsDTO> getCustomerFirstDetailsByDealerCodeAndToggle(String dealerCode, String toggle){
		return this.CustomerFirstDetailsDAO.getCustomerFirstDetailsByDealerCodeAndToggle(dealerCode, toggle);
	}
	
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsCCPByDealer(String dealerCode) {
		return this.RewardRedemptionDetailsDAO.getRewardRedemptionDetailsCCPByDealer(dealerCode);
	}
	
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsCCPBySid(String sid, String dealerCode) {
		return this.RewardRedemptionDetailsDAO.getRewardRedemptionDetailsCCPBySid(sid, dealerCode);
	}
	
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsTTTAByDealer(String dealerCode) {
		return this.RewardRedemptionDetailsDAO.getRewardRedemptionDetailsTTTAByDealer(dealerCode);
	}
	
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsTTTABySid(String sid, String dealerCode) {
		return this.RewardRedemptionDetailsDAO.getRewardRedemptionDetailsTTTABySid(sid, dealerCode);
	}
	
	public List<SIRewardsYOYDetailsDTO> getSIRewardsYOYDetailsBySIDAndToggle(String sid, String dealerCode, String toggle) {
		return this.SIRewardsYOYDetailsDAO.getSIRewardsYOYDetailsBySIDAndToggle(sid, dealerCode, toggle);
	}
	
	public List<SIRewardsYOYDetailsDTO> getSIRewardsYOYDetailsByDealerCodeAndToggle(String dealerCode, String toggle) {
		return this.SIRewardsYOYDetailsDAO.getSIRewardsYOYDetailsByDealerCodeAndToggle( dealerCode, toggle);
	}
	
	public List<Integer> getTotalDealersEnrolledByProgramID(int programId) {
		return this.ProgramCountDAOImpl.totalDealersEnrolledByProgramID(programId);
	}
	
	public List<Integer> getTotalDealersByProgramID(int programId) {
		return this.ProgramCountDAOImpl.totalDealersByProgramID(programId);
	}
	
	public List<Integer> gettotalDealersEnrolledByProgramGroupID(int programId) {
		return this.ProgramCountDAOImpl.totalDealersEnrolledByProgramGroupID(programId);
	}
	
	public List<Integer> gettotalDealersByProgramGroupID(int programId) {
		return this.ProgramCountDAOImpl.totalDealersByProgramGroupID(programId);
	}
	
	public List<Integer> getTotalParticipantsEnrolledByProgramIDAndDealerCode(int programId, String dealerCode){
		return this.ProgramCountDAOImpl.totalParticipantsEnrolledByProgramIDAndDealerCode(programId, dealerCode);
	}
	
	public List<Integer> getTotalDealersEnrolledByProgramIDAndTerritory(int programId, String territory){
		return this.ProgramCountDAOImpl.totalDealersEnrolledByProgramID(programId, territory);
	}
	
	public boolean checkDealerEnrollmentByProgram(int programId, String dealerCode){
		return this.ProgramCountDAOImpl.checkDealerEnrollmentByProgram(programId, dealerCode);
	}
	
	public List<Integer> getTotalELValidated(){
		return this.ProgramCountDAOImpl.getTotalELValidated();
	}
	
	public List<Double> getTTTANATAverageSurveyScoreByPositionCode(String positionCode){
		return this.TTTAEnrollmentsSummaryDAO.getTTTANATAverageSurveyScoreByPositionCode(positionCode);
	}
	
	public List<BrainBoostWinndersGraphDTO> getBrainBoostWinndersGraphDTOByParentTerritory(String list){
		return this.BrainBoostWinndersGraphDAO.getBrainBoostWinndersGraphDTOByParentTerritory(list);
	}
	
	
}
