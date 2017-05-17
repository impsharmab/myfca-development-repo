/**
 *
 */
package com.imperialm.imiservices.services;

import java.util.List;

import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.model.NoTile;

public interface UserProfileService {
	
	
	public List<NoTile> getuserTiles(String positionCode, String roleId, UserDetailsImpl user, String token, String pc, String dc);

}
