package com.imperialm.imiservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperialm.imiservices.dao.BannerDAO;
import com.imperialm.imiservices.dao.DashBoardBannersDAO;
import com.imperialm.imiservices.dto.BannersDTO;
import com.imperialm.imiservices.dto.ImagesDTO;
import com.imperialm.imiservices.dto.request.InputRequest;

@Service
public class BannerServiceImpl implements BannerService {

	@Autowired
	private BannerDAO bannerdDAO;

	@Autowired
	private DashBoardBannersDAO dashBoardBannersDAO;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.imperialm.imiservices.services.BannerService#findBannersByRole(com.
	 * imperialm.imiservices.dto.request.UserRoleRequest)
	 */
	@Override
	public List<BannersDTO> findBannersByRole(final InputRequest userRoleReq) {
		return this.bannerdDAO.getBannersByRole(userRoleReq);
	}

	public List<ImagesDTO> getBannersByRole(int roleId, String territory){
		return this.dashBoardBannersDAO.getBannersByRole(roleId, territory);
	}
	public List<ImagesDTO> getAllBanners(){
		return this.dashBoardBannersDAO.getAllBanners();
	}
}
