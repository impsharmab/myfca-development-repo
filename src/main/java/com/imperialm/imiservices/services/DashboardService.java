package com.imperialm.imiservices.services;

import java.util.List;

import com.imperialm.imiservices.dto.DashboardDTO;
import com.imperialm.imiservices.dto.request.UserRoleRequest;

/**
 *
 * @author Dheerajr
 *
 */
public interface DashboardService {
	public List<DashboardDTO> findTilesListByRole(UserRoleRequest userRoleReq);

}
