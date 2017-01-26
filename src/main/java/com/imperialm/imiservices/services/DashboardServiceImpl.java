package com.imperialm.imiservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.model.response.TotalName;
import com.imperialm.imiservices.dao.MSERTopNDAO;

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
	
	public List<MSERTopNDTO> getMSERTopTen(String type, int rows){
		 return this.MSERTopNDAO.getTopNByType(type, rows);
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
	
	public List<CertProfsExpertGraphDTO> getExpertPointsEarned(){
		return this.CertProfsExpertGraphDAO.getExpertPointsEarned();
	}
	
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgram(){
		return this.CertProfsExpertGraphDAO.getParticipantCompletedByProgram();
	}
	
	public List<CertProfsWinnersGraphDTO> getBCCertifications(){
		return this.CertProfsWinnersGraphDAO.getBCCertifications();
	}
	
}
