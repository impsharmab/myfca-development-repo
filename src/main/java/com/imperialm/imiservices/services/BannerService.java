package com.imperialm.imiservices.services;

import java.util.List;

import com.imperialm.imiservices.dto.BannersDTO;
import com.imperialm.imiservices.dto.request.UserRoleRequest;

/**
 *
 * @author Dheerajr
 *
 */
public interface BannerService {
	public List<BannersDTO> findBannersByRole(UserRoleRequest userRoleReq);
}
