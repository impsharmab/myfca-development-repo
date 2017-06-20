package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.UserInfoDTO;

import java.util.List;

public interface UserInfoDAO {
	public static String GET_USERINFO = "select u.UserId, u.Name, u.Email, upr.ProgramCode, upr.RoleId, ut.Territory from users u inner join UserProgramRoles upr ON u.UserId = upr.UserId AND u.UserId = ? AND u.DelFlag LIKE 'N' left join UserTerritory ut on ut.UserID = ?";
	public List<UserInfoDTO> getUserInfo(String userId);
}
