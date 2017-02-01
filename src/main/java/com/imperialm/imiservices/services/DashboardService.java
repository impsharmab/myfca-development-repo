package com.imperialm.imiservices.services;

import java.util.List;

import com.imperialm.imiservices.dto.BrainBoostWinndersGraphDTO;
import com.imperialm.imiservices.dto.CertProfsExpertGraphDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersGraphDTO;
import com.imperialm.imiservices.dto.DashboardDTO;
import com.imperialm.imiservices.dto.MSEREarningsDTO;
import com.imperialm.imiservices.dto.MSERTopNDTO;
import com.imperialm.imiservices.dto.TTTATopNDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.model.response.TotalName;

/**
 *
 * @author Dheerajr
 *
 */
public interface DashboardService {
	public List<DashboardDTO> findTilesListByRole(InputRequest userRoleReq);

	/**
	 * @param userRoleReq
	 * @return
	 */
	public List<DashboardDTO> findTilesByRole(InputRequest userRoleReq);
	
	public List<MSEREarningsDTO> getEarningsByRole(final InputRequest userRoleReq);
	
	public List<MSERTopNDTO> getMSERTopTen(String type, int rows);
	
	public TotalName getMTDByProgramAndProgramgroup(String name, String program, String programgroup);
	
	public TotalName getDealersCount();
	
	public TotalName getDealersCountWithPercentage();
	
	public TotalName getYTDByProgramAndProgramgroup(String name, String program, String programgroup);
	
	public List<BrainBoostWinndersGraphDTO> getBrainBoostGraphBCData(boolean filter);
	
	public List<BrainBoostWinndersGraphDTO> getBrainBoostGraphAllDistricData(List<String> filter);
	
	public List<CertProfsExpertGraphDTO> getExpertPointsEarned();
	
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgram();
	
	public List<CertProfsWinnersGraphDTO> getBCCertifications();
	
	public List<TTTATopNDTO> getTTTATopN(String type, int rows);
	
	public TotalName getTTTAEnrollmentCount();
	
	public TotalName getTTTAIncentiveEligibleSUM();

}
