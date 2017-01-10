/**
 *
 */
package com.imperialm.imiservices.services;

import java.util.List;

import com.imperialm.imiservices.dto.MenuDTO;
import com.imperialm.imiservices.dto.request.InputRequest;

/**
 * @author Dheerajr
 *
 */
public interface MenuService {

	/**
	 * @param userRoleReq
	 * @return
	 */
	List<MenuDTO> findMenuByRole(InputRequest userRoleReq);

}
