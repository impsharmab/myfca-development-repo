package com.imperialm.imiservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperialm.imiservices.dao.DashboardDAO;
import com.imperialm.imiservices.dao.MSEREarningsDAO;
import com.imperialm.imiservices.dto.DashboardDTO;
import com.imperialm.imiservices.dto.MSEREarningsDTO;
import com.imperialm.imiservices.dto.MSERTopNDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.dao.MSERTopNDAO;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private DashboardDAO dashboardDAO;
	
	@Autowired
	private MSEREarningsDAO MSEREarningsDAO;
	
	@Autowired
	private MSERTopNDAO MSERTopNDAO;

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
	
}
