/**
 *
 */
package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.MenuDTO;
import com.imperialm.imiservices.dto.request.InputRequest;

/**
 * @author Dheerajr
 *
 */
public interface MenuDAO {
	;
	public static final String MENU_BY_ROLE = "select DISTINCT ROW_NUMBER() OVER(ORDER BY programcode ASC) as Rno,ProgramCode,"
			+ " (select name from menus where id = mm.menuid and DelFlag = 'N' ) 'MenuName', "
			+ " (select name from menus where id = mm.SubMenuID and DelFlag = 'N' ) 'SubMenuName' "
			+ " from MenuMapping mm  where mm.RoleID = ? and mm.DelFlag = 'N' ";

	/**
	 * @param userRoleReq
	 * @return
	 */
	List<MenuDTO> findMenuByRole(InputRequest userRoleReq);

}
