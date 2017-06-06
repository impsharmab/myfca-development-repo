/**
 *
 */
package com.imperialm.imiservices.services;

import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.model.NoTile;

import java.util.List;

public interface UserProfileService {
	
	
	public List<NoTile> getuserTiles(String positionCode, String roleId, UserDetailsImpl user, String token, String pc, String dc);

}
