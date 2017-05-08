package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.BannersDTO;
import com.imperialm.imiservices.dto.ImagesDTO;
import com.imperialm.imiservices.dto.request.InputRequest;

/**
 *
 * @author Dheerajr
 *
 */

public interface BannerDAO {

	public static String BANNERS_BY_ROLE = "select DISTINCT bm.id as Rno, "
			+ " ProgramCode,name 'BannerName', filename 'FileName', OrderBy BannerOrder  from "
			+ " BannerMappings BM JOIN banners banners  on banners.id = bm.BannerID "
			+ " where bm.RoleID = ? AND banners.DelFlag = 'N' and bm.DelFlag = 'N' order by Programcode, BannerName ";

	public static String BANNERS_BY_ROLE_AND_TERRITORY = "Select distinct FileName 'fileName', Name 'name', bm.OrderBy 'orderBy' from banners b join [BannerMappings] bm on b.ID = bm.bannerid where b.DelFlag = 'N' and bm.RoleID = ?0 and bm.BusinessCenter IN (?1 , 'NAT') order by bm.OrderBy";
	public static String SELECT_ALL_BANNERS = "Select distinct FileName, '' as orderBy Name from banners";
	
	public List<BannersDTO> getBannersByRole(InputRequest userRoleReq);
	//public List<ImagesDTO> getBannersByRole(int roleId, String territory);
	//public List<ImagesDTO> getAllBanners();

	
	
}
