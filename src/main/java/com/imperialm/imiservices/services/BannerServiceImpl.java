package com.imperialm.imiservices.services;

import com.imperialm.imiservices.dao.DashBoardBannersDAO;
import com.imperialm.imiservices.dto.ImagesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
