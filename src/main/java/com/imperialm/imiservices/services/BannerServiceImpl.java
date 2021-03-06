package com.imperialm.imiservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperialm.imiservices.dao.DashBoardBannersDAO;
import com.imperialm.imiservices.dto.ImagesDTO;

@Service
public class BannerServiceImpl implements BannerService {

	@Autowired
	private DashBoardBannersDAO dashBoardBannersDAO;


	public List<ImagesDTO> getBannersByRole(int roleId, String territory){
		return this.dashBoardBannersDAO.getBannersByRole(roleId, territory);
	}
	public List<ImagesDTO> getAllBanners(){
		return this.dashBoardBannersDAO.getAllBanners();
	}
}
