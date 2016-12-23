/**
 *
 */
package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.UserProfileDTO;
import com.imperialm.imiservices.dto.request.InputRequest;

/**
 * @author Dheerajr
 *
 */
public interface UserProfileDAO {

	public static String USER_PROFILE_DAO = " select row_number() over(order by u.userid ) 'rNo', u.UserId, u.name,"
			+ " u.email, upr.programcode,r.id 'RoleId', r.name 'RoleName', r.RoleLevel from users u join UserProgramRoles upr "
			+ " on u.userid = upr.userid  join Roles r on  r.id = upr.roleid where upr.userid = ? "
			+ " and u.HashPass = HASHBYTES('SHA2_512', cast(?  as varchar) +salt) "
			+ " and u.DelFlag = 'N' and upr.DelFlag = 'N' " + " order by upr.programcode , r.RoleLevel ";

	/**
	 * @param userRoleReq
	 * @return
	 */
	UserProfileDTO getUserProfile(InputRequest userRoleReq);

}
