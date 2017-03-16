/**
 *
 */
package com.imperialm.imiservices.services;

import java.util.List;

import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.dto.UserProfileDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.model.NoTile;

/**
 * @author Dheerajr
 *
 */
public interface UserProfileService {

	/**
	 * @param userRoleReq
	 * @return
	 */
	UserProfileDTO getUserProfile(InputRequest userRoleReq);
	
//	public List<NoTile> getNoTiles();
	
	public List<NoTile> getuserTiles(String positionCode, String roleId, UserDetailsImpl user);

}
