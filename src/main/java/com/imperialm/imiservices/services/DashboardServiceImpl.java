package com.imperialm.imiservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperialm.imiservices.dao.DashboardDAO;
import com.imperialm.imiservices.dto.DashboardDTO;
import com.imperialm.imiservices.dto.request.UserRoleRequest;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private DashboardDAO dashboardDAO;

	@Override
	public List<DashboardDTO> findTilesListByRole(final UserRoleRequest userRoleReq) {
		return dashboardDAO.findTilesListByRole(userRoleReq);
	}
}
