package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.DashBoardBannersDTO;
import com.imperialm.imiservices.dto.ImagesDTO;

public interface DashBoardBannersDAO {
	public static String IMAGES_BY_ROLE_AND_TERRITORY = "Select distinct image 'fileName', '' 'name', OrderBy 'orderBy' from [DashBoardBanners] DelFlag = 'N' and RoleID = (?0) and BusinessCenter IN (?1 , 'NAT') order by OrderBy";
	public static String IMAGES_EXECUTIVE = "Select distinct FileName 'fileName', Name 'name', bm.OrderBy 'orderBy' from ";
	
	public static String BANNERS_BY_ROLE_AND_TERRITORY = "Select distinct image 'fileName', '' 'name', OrderBy 'orderBy' from [DashBoardBanners] DelFlag = 'N' and RoleID = (?0) and BusinessCenter IN (?1 , 'NAT') order by OrderBy";
	public static String BANNERS_EXECUTIVE = "Select distinct FileName 'fileName', Name 'name', bm.OrderBy 'orderBy' from ";
	
	public static String SELECT_BANNER_FOR_ADMIN = "Select * from dbo.DashBoardBannersDAO";
	
	
	public List<ImagesDTO> getBannersByRole(int roleId, String territory);
	public List<DashBoardBannersDTO> getAllBannersForAdmin();
	public List<DashBoardBannersDTO> updateBanner(DashBoardBannersDTO banner);
	public List<DashBoardBannersDTO> addBanner(DashBoardBannersDTO banner);
	public List<DashBoardBannersDTO> deleteBanner(int id);
}
