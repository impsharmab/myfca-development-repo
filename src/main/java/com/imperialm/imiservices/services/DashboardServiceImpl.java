package com.imperialm.imiservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperialm.imiservices.dao.TTTAEnrolledDAO;
import com.imperialm.imiservices.dao.TTTAEnrollmentsDAO;
import com.imperialm.imiservices.dao.DashboardDAO;
import com.imperialm.imiservices.dao.MSEREarningsDAO;
import com.imperialm.imiservices.dao.MSERGraphDetailsDAO;
import com.imperialm.imiservices.dao.BrainBoostWinndersGraphDAO;	
import com.imperialm.imiservices.dao.CertProfsExpertGraphDAO;
import com.imperialm.imiservices.dao.CertProfsWinnersGraphDAO;
import com.imperialm.imiservices.dto.BrainBoostWinndersGraphDTO;
import com.imperialm.imiservices.dto.CertProfsExpertGraphDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersGraphDTO;
import com.imperialm.imiservices.dto.DashboardDTO;
import com.imperialm.imiservices.dto.MSEREarningsDTO;
import com.imperialm.imiservices.dto.MSERTopNDTO;
import com.imperialm.imiservices.dto.TTTAEnrolledDTO;
import com.imperialm.imiservices.dto.TTTATopNDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.model.response.TotalName;
import com.imperialm.imiservices.dao.MSERTopNDAO;
import com.imperialm.imiservices.dao.TTTATopNDAO;

@Service
public class DashboardServiceImpl implements DashboardService {

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
	private CertProfsWinnersGraphDAO CertProfsWinnersGraphDAO;
	
	@Autowired
	private TTTATopNDAO TTTATopNDAO;
	
	@Autowired
	private TTTAEnrollmentsDAO TTTAEnrollmentsDAO;
	
	@Autowired
	private TTTAEnrolledDAO TTTAEnrolledDAO;
	

	@Override
	public List<DashboardDTO> findTilesListByRole(final InputRequest userRoleReq) {
		return this.dashboardDAO.findTilesListByRole(userRoleReq);
	}

	@Override
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
	
	public TotalName getDealersCount(){
		 return this.MSERGraphDetailsDAO.getDealersCount();
	}
	
	public TotalName getDealersCountWithPercentage(){
		 return this.MSERGraphDetailsDAO.getDealersCountWithPercentage();
	}
	
	public TotalName getYTDByProgramAndProgramgroup(String name, String program, String programgroup){
		 return this.MSERGraphDetailsDAO.getYTDByProgramAndProgramgroup(name, program, programgroup);
	}
	
	public List<BrainBoostWinndersGraphDTO> getBrainBoostGraphBCData(boolean filter){
		 return this.BrainBoostWinndersGraphDAO.getBCData(filter);
	}
	
	public List<BrainBoostWinndersGraphDTO> getBrainBoostGraphAllDistricData(List<String> list){
		 return this.BrainBoostWinndersGraphDAO.getAllDistricData(list);
	}
	
	public List<CertProfsWinnersGraphDTO> getCertProfsWinnersGraphAllDistricData(List<String> list)
	{
		return this.CertProfsWinnersGraphDAO.getAllDistricData(list);
	}
	
	public List<CertProfsExpertGraphDTO> getExpertPointsEarned(){
		return this.CertProfsExpertGraphDAO.getExpertPointsEarned();
	}
	
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgram(){
		return this.CertProfsExpertGraphDAO.getParticipantCompletedByProgram();
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
	
}
