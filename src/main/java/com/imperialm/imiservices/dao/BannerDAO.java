package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.BannersDTO;
import com.imperialm.imiservices.dto.request.UserRoleRequest;

/**
 *
 * @author Dheerajr
 *
 */

public interface BannerDAO {

	public static final String BANNERS_BY_ROLE = "select DISTINCT ROW_NUMBER() OVER(ORDER BY programcode ASC) as Rno, "
			+ " ProgramCode,name 'BannerName', filename 'FileName', OrderBy BannerOrder  from "
			+ " BannerMappings BM JOIN banners banners  on banners.id = bm.BannerID "
			+ " where bm.RoleID = ? AND banners.DelFlag = 'N' and bm.DelFlag = 'N' order by Programcode, BannerName ";

	public List<BannersDTO> getBannersByRole(UserRoleRequest userRoleReq);
}
