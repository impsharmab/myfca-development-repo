/**
 *
 */
package com.imperialm.imiservices.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imperialm.imiservices.dto.MenuDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.services.MenuService;

/**
 * @author Dheerajr
 *
 */
@RestController
public class MenuController {

	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/services/menubyrole", method = RequestMethod.GET)
	public @ResponseBody List<MenuDTO> findTilesListByRole(@RequestParam("role") final Long roleId) {
		final InputRequest userRoleReq = new InputRequest(roleId);
		return this.menuService.findMenuByRole(userRoleReq);
	}
}
