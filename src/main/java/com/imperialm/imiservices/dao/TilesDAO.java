/**
 *
 */
package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.TileDTO;
import com.imperialm.imiservices.dto.request.InputRequest;

/**
 * @author Dheerajr
 *
 */

public interface TilesDAO {

	public static final String TILES_BY_ROLES_AND_PROGRAM = "SELECT row_number() over(order by userid ) 'rNo' "
			+ " , attributes, datatable FROM SERVICETILES WHERE USERID = ? "
			+ " AND Territory = ? AND TILEID = ? AND DELFLAG = 'N' ";

	public TileDTO findTilesByUserRoleAndTerritory(InputRequest roleRequest);

}
