/**
 *
 */
package com.imperialm.imiservices.services;

import com.imperialm.imiservices.dto.UserProfileDTO;
import com.imperialm.imiservices.dto.request.InputRequest;

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

}
