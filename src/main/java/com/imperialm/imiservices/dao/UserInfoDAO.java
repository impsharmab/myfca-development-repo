package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.UserInfoDTO;

public interface UserInfoDAO {
	public static String GET_USERINFO = "select u.UserId, u.Name, u.Email, upr.ProgramCode, upr.RoleId, ut.Territory from mser2.dbo.users u inner join mser2.dbo.UserProgramRoles upr ON u.UserId = upr.UserId AND u.UserId = ? AND u.DelFlag LIKE 'N' left join mser2.dbo.UserTerritory ut on ut.UserID = ?";
	public List<UserInfoDTO> getUserInfo(String userId);
}
