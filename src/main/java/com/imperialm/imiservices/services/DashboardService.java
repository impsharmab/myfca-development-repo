package com.imperialm.imiservices.services;

import java.util.List;

import com.imperialm.imiservices.dto.DashboardDTO;
import com.imperialm.imiservices.dto.request.InputRequest;

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

}
