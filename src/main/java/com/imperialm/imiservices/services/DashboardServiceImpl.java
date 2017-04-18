package com.imperialm.imiservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.imperialm.imiservices.dao.MSERGraphDAO;
import com.imperialm.imiservices.dao.TTTAEnrolledDAO;
import com.imperialm.imiservices.dao.TTTAEnrollmentsDAO;
import com.imperialm.imiservices.dao.DashboardDAO;
import com.imperialm.imiservices.dao.MSEREarningsDAO;
import com.imperialm.imiservices.dao.MSERGraphDetailsDAO;
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
import com.imperialm.imiservices.dto.DashboardDTO;
import com.imperialm.imiservices.dto.MSERDetailsGraphDTO;
import com.imperialm.imiservices.dto.MSEREarningsDTO;
import com.imperialm.imiservices.dto.MSERGraphDTO;
import com.imperialm.imiservices.dto.MSERGraphDetailsDTO;
import com.imperialm.imiservices.dto.MSERTopNDTO;
import com.imperialm.imiservices.dto.RetentionDetailsDTO;
import com.imperialm.imiservices.dto.RetentionGraphDTO;
import com.imperialm.imiservices.dto.RewardRedemptionDetailsDTO;
import com.imperialm.imiservices.dto.RewardRedemptionGraphDTO;
import com.imperialm.imiservices.dto.SIRewardsDetailsDTO;
import com.imperialm.imiservices.dto.SIRewardsDetailsGraphDTO;
import com.imperialm.imiservices.dto.SIRewardsYOYGraphDTO;
import com.imperialm.imiservices.dto.SummaryProgramRewardDetailsDTO;
import com.imperialm.imiservices.dto.SummaryProgramRewardGraphDTO;
import com.imperialm.imiservices.dto.TTTAEnrolledDTO;
import com.imperialm.imiservices.dto.TTTAEnrolledGraphDTO;
import com.imperialm.imiservices.dto.TTTAEnrollmentsDTO;
import com.imperialm.imiservices.dto.TTTAEnrollmentsSummaryDTO;
import com.imperialm.imiservices.dto.TTTATopNDTO;
import com.imperialm.imiservices.dto.UserPositionCodeRoleDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.model.response.TotalName;
import com.imperialm.imiservices.dao.MSERTopNDAO;
import com.imperialm.imiservices.dao.TTTATopNDAO;

@Service // implements DashboardService
public class DashboardServiceImpl {

	@Autowired
	private DashboardDAO dashboardDAO;
	
	@Autowired
	private MSEREarningsDAO MSEREarningsDAO;
	
	@Autowired
	private MSERTopNDAO MSERTopNDAO;
	
	@Autowired
	private MSERGraphDetailsDAO MSERGraphDetailsDAO;
	
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
	private MSERGraphDAO MSERGraphDAO;
	
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
	
	
	public List<DashboardDTO> findTilesListByRole(final InputRequest userRoleReq) {
		return this.dashboardDAO.findTilesListByRole(userRoleReq);
	}

	
	public List<DashboardDTO> findTilesByRole(final InputRequest userRoleReq) {
		return this.dashboardDAO.findTilesByRole(userRoleReq);
	}
	
	public List<MSEREarningsDTO> getEarningsByRole(final InputRequest userRoleReq){
		 return this.MSEREarningsDAO.getEarningsByRole(userRoleReq);
	}
	
	public List<MSERTopNDTO> getMSERTopTen(String type, int rows, String name, String period){
		 return this.MSERTopNDAO.getTopNByType(type, rows, name, period);
	}
	
	public TotalName getMTDByProgramAndProgramgroup(String name, String program, String programgroup){
		 return this.MSERGraphDetailsDAO.getMTDByProgramAndProgramgroup(name, program, programgroup);
	}
	
	public TotalName getMSEREnrolledDealersCount(){
		 return this.MSERGraphDetailsDAO.getMSEREnrolledDealersCount();
	}
	
	public TotalName getDealersCountWithPercentage(){
		 return this.MSERGraphDetailsDAO.getDealersCountWithPercentage();
	}
	
	public TotalName getMSERDealersCountByBCOrDistrict(String territory){
		 return this.MSERGraphDetailsDAO.getMSERDealersCountByBCOrDistrict(territory);
	}
	
	public TotalName getMSEREarningsYTDByBCOrDistrict(String territory){
		 return this.MSERGraphDetailsDAO.getMSEREarningsYTDByBCOrDistrict(territory);
	}
	
	public TotalName getMSEREarningsMTDByBCOrDistrict(String territory){
		 return this.MSERGraphDetailsDAO.getMSEREarningsMTDByBCOrDistrict(territory);
	}
	
	public TotalName getMSERParticipantEnrolledByDealerCode(String dealerCode){
		 return this.MSERGraphDetailsDAO.getMSERParticipantEnrolledByDealerCode(dealerCode);
	}
	
	
	public TotalName getYTDByProgramAndProgramgroup(String name, String program, String programgroup){
		 return this.MSERGraphDetailsDAO.getYTDByProgramAndProgramgroup(name, program, programgroup);
	}
	
	public TotalName getParticipantExcellanceCardAwardMTD(String sid){
		 return this.MSERGraphDetailsDAO.getParticipantExcellanceCardAwardMTD(sid);
	}
	
	public TotalName getParticipantExcellanceCardAwardYTD(String sid){
		 return this.MSERGraphDetailsDAO.getParticipantExcellanceCardAwardYTD(sid);
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
	
	public List<BrainBoostWinnersDetailsDTO> getBrainBoostWinnersDetailsDTOBySID(String sID, String toggle){
		 return this.BrainBoostWinnersDetailsDAO.getBrainBoostWinnersDetailsBySID(sID, toggle);
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
	
	public List<MSERGraphDTO> getMSERGraphBCData(boolean filter){
		return this.MSERGraphDAO.getBCEarnings(filter);
	}
	
	public List<MSERGraphDTO> getMSERGraphAllDistricData(List<String> list){
		return this.MSERGraphDAO.getAllDistricData(list);
	}
	
	public List<MSERGraphDTO> getMSERGraphByTerritoryAndToggle(String territory, String toggle){
		return this.MSERGraphDAO.getMSERGraphByTerritoryAndToggle(territory, toggle);
	}
	public List<MSERGraphDTO> getMSERGraphByTerritoryAndToggleAndProgram(String territory, String toggle, String program){
		return this.MSERGraphDAO.getMSERGraphByTerritoryAndToggleAndProgram(territory, toggle, program);
	}
	
	public List<MSERGraphDTO> getMSERGraphByChildTerritoryAndToggleAndProgram(String territory, String toggle, String program){
		return this.MSERGraphDAO.getMSERGraphByChildTerritoryAndToggleAndProgram(territory, toggle, program);
	}
	
	public List<MSERGraphDTO> getMSERGraphByChildTerritoryAndToggle(String territory, String toggle){
		return this.MSERGraphDAO.getMSERGraphByChildTerritoryAndToggle(territory, toggle);
	}
	
	public List<String> getMSERGraphDistinctProgramsByParentTerritoryAndToggle(String territory, String toggle){
		return this.MSERGraphDAO.getMSERGraphDistinctProgramsByParentTerritoryAndToggle(territory, toggle);
	}
	
	public List<MSERGraphDTO> getMSERGraphProgramsSUMByParentTerritoryAndToggle(String territory, String toggle){
		return this.MSERGraphDAO.getMSERGraphProgramsSUMByParentTerritoryAndToggle(territory, toggle);
	}
	
	public List<MSERGraphDTO> getMSERGraphProgramsSUMByChildTerritoryAndToggle(String territory, String toggle){
		return this.MSERGraphDAO.getMSERGraphProgramsSUMByChildTerritoryAndToggle(territory, toggle);
	}
	
	public List<SIRewardsDetailsGraphDTO> getSIRewardsDetailsGraphByTerritoryAndToggle(List<String> territory, String toggle){
		return this.SIRewardsDetailsGraphDAO.getSIRewardsDetailsGraphByTerritoryAndToggle(territory, toggle);
	}
	
	public List<SIRewardsDetailsGraphDTO> getSIRewardsDetailsGraphByTerritoryAndToggle(String territory, String toggle){
		return this.SIRewardsDetailsGraphDAO.getSIRewardsDetailsGraphByTerritoryAndToggle(territory, toggle);
	}
	
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsByDealerCode(String dealerCode){
		return this.SIRewardsDetailsDAO.getSIRewardsDetailsByDealerCode(dealerCode);
	}
	
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsBySID(String sID){
		return this.SIRewardsDetailsDAO.getSIRewardsDetailsBySID(sID);
	}
	
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsByDealerCodeAndToggle(String dealerCode, String toggle){
		return this.SIRewardsDetailsDAO.getSIRewardsDetailsByDealerCodeAndToggle(dealerCode, toggle);
	}
	
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsBySIDAndToggle(String sID, String toggle){
		return this.SIRewardsDetailsDAO.getSIRewardsDetailsBySIDAndToggle(sID, toggle);
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
	
	public List<SIRewardsYOYGraphDTO> getSIRewardsYOYGraphByTerritoryAndToggleFilterParent(List<String> territory, String toggle){
		return this.SIRewardsYOYGraphDAO.getSIRewardsYOYGraphByTerritoryAndToggleFilterParent(territory, toggle);
	}
	
	public List<SIRewardsDetailsGraphDTO> getSIRewardsDetailsGraphByTerritoryAndToggleFilterParent(List<String> territory, String toggle){
		return this.SIRewardsDetailsGraphDAO.getSIRewardsDetailsGraphByTerritoryAndToggleFilterParent(territory, toggle);
	}
	
	public List<SIRewardsDetailsGraphDTO> getSIRewardsDetailsGraphByChildTerritoryAndToggle(List<String> territory, String toggle){
		return this.SIRewardsDetailsGraphDAO.getSIRewardsDetailsGraphByChildTerritoryAndToggle(territory, toggle);
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
	
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsBySid(String sid){
		return this.RewardRedemptionDetailsDAO.getRewardRedemptionDetailsBySid(sid);
	}	
	
	public List<CertProfsWinnersDetailsDTO> getCertProfsWinnersDetailsByDealerCode(String dealerCode){
		return this.CertProfsWinnersDetailsDAO.getCertProfsWinnersDetailsByDealerCode(dealerCode);
	}
	public List<CertProfsWinnersDetailsDTO> getCertProfsWinnersDetailsBySID(String sid){
		return this.CertProfsWinnersDetailsDAO.getCertProfsWinnersDetailsBySID(sid);
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
	
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsBySIDANDCertType(String sid, String certType){
		return this.CertProfsExpertDetailsDAO.getCertProfsExpertDetailsBySIDANDCertType(sid, certType);
	}
	
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsSUMBySID(String sid){
		return this.CertProfsExpertDetailsDAO.getCertProfsExpertDetailsSUMBySID(sid);
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
	
	public List<TTTAEnrollmentsSummaryDTO> getTTTAEnrollmentsSummaryByChildAndPositionCode(List<String> territories, String positionCode){
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
	
	public TotalName getTTTANATTopEnrolledDealerCountByBCDistrictAndPositionCode(String territory, String positionCode){
		return this.TTTAEnrollmentsSummaryDAO.getTTTANATTopEnrolledDealerCountByBCDistrictAndPositionCode(territory, positionCode);
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
	
	public List<TTTAEnrollmentsDTO> getTTTAEnrollmentsBySID(String sid, String positionCode){
		return this.TTTAEnrollmentsDAO.getTTTAEnrollmentsBySID(sid, positionCode);
	}
	public List<TTTAEnrollmentsDTO> getTTTAEnrollmentsByDealerCode(String dealerCode, String positionCode){
		return this.TTTAEnrollmentsDAO.getTTTAEnrollmentsBySID(dealerCode, positionCode);
	}
	
	public List<TTTAEnrollmentsDTO> getTTTAEnrollmentsByDealerCode(String dealerCode, String positionCode, String enrollement){
		return this.TTTAEnrollmentsDAO.getTTTAEnrollmentsByDealerCodeAndEnrollement(dealerCode, enrollement, positionCode);
	}
	
	public List<SummaryProgramRewardGraphDTO> getSummaryProgramRewardGraphByParentTerritoryYTD(List<String> territory){
		return this.SummaryProgramRewardGraphDAO.getSummaryProgramRewardGraphByParentTerritoryYTD(territory);
	}
	
	public List<SummaryProgramRewardGraphDTO> getSummaryProgramRewardGraphByChildTerritoryYTD(List<String> territory){
		return this.SummaryProgramRewardGraphDAO.getSummaryProgramRewardGraphByChildTerritoryYTD(territory);
	}
	
	public List<SummaryProgramRewardGraphDTO> getSummaryProgramRewardGraphByChildTerritoryYTD(String territory){
		return this.SummaryProgramRewardGraphDAO.getSummaryProgramRewardGraphByChildTerritoryYTD(territory);
	}
	
	public List<SummaryProgramRewardGraphDTO> getSummaryProgramRewardDetailsBySIDYTD(String territory){
		return this.SummaryProgramRewardDetailsDAO.getSummaryProgramRewardDetailsBySIDYTD(territory);
	}
	
	public List<SummaryProgramRewardDetailsDTO> getSummaryProgramRewardDetailsByDealerCodeYTD(String territory){
		return this.SummaryProgramRewardDetailsDAO.getSummaryProgramRewardDetailsByDealerCodeYTD(territory);
	}
	
	public List<MSERGraphDetailsDTO> getMSERGraphDetailsByDealerCode(String dealerCode){
		return this.MSERGraphDetailsDAO.getMSERGraphDetailsByDealerCode(dealerCode);
	}
	
	public List<CustomerFirstDetailsDTO> getCustomerFirstDetailsByDealerCodeAndToggle(String dealerCode, String toggle){
		return this.CustomerFirstDetailsDAO.getCustomerFirstDetailsByDealerCodeAndToggle(dealerCode, toggle);
	}
	
}
