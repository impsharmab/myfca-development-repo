/**
 *
 */
package com.imperialm.imiservices.services;

import java.util.List;

import com.imperialm.imiservices.dto.MenuDTO;
import com.imperialm.imiservices.dto.request.UserRoleRequest;

/**
 * @author Dheerajr
 *
 */
public interface MenuService {

	/**
	 * @param userRoleReq
	 * @return
	 */
	List<MenuDTO> findMenuByRole(UserRoleRequest userRoleReq);

}
