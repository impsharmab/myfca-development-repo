package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.DashBoardBannersDTO;
import com.imperialm.imiservices.dto.ImagesDTO;

public interface DashBoardBannersDAO {
	public static String IMAGES_BY_ROLE_AND_TERRITORY = "Select distinct image 'fileName', '' 'imageName', OrderBy 'imageOrder' from [DashBoardBanners] where DelFlag = 'N' and RoleID = (?0) and BusinessCenter IN (?1 , 'NAT') order by OrderBy";
	public static String IMAGES_EXECUTIVE = "Select distinct Image 'fileName', '' 'imageName', OrderBy 'imageOrder' from [DashBoardBanners] where DelFlag = 'N' order by OrderBy";
	
	public static String BANNERS_BY_ROLE_AND_TERRITORY = "Select distinct image 'fileName', '' 'imageName', OrderBy 'imageOrder' from [DashBoardBanners] DelFlag = 'N' and RoleID = (?0) and BusinessCenter IN (?1 , 'NAT') order by OrderBy";
	public static String BANNERS_EXECUTIVE = "Select distinct FileName 'fileName', Name 'imageName', bm.OrderBy 'imageOrder' from ";
	
	public static String SELECT_BANNER_FOR_ADMIN = "Select * from DashBoardBanners where DelFlag='N'";
	
	
	public List<ImagesDTO> getBannersByRole(int roleId, String territory);
	public List<ImagesDTO> getAllBanners();
	public List<DashBoardBannersDTO> getAllBannersForAdmin();
	public boolean updateBanner(DashBoardBannersDTO banner, String userId);
	public boolean addBanner(DashBoardBannersDTO banner, String userId);
	public boolean deleteBanner(int id, String userId);
}
