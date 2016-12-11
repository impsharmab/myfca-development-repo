package com.imperialm.imiservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperialm.imiservices.dao.BannerDAO;
import com.imperialm.imiservices.dto.BannersDTO;
import com.imperialm.imiservices.dto.request.UserRoleRequest;

@Service
public class BannerServiceImpl implements BannerService {

	@Autowired
	private BannerDAO bannerdDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.imperialm.imiservices.services.BannerService#findBannersByRole(com.
	 * imperialm.imiservices.dto.request.UserRoleRequest)
	 */
	@Override
	public List<BannersDTO> findBannersByRole(final UserRoleRequest userRoleReq) {
		return bannerdDAO.getBannersByRole(userRoleReq);
	}
}
