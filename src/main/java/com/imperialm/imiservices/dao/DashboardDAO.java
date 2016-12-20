package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.DashboardDTO;
import com.imperialm.imiservices.dto.request.InputRequest;

/**
 *
 * @author Dheerajr
 *
 */

public interface DashboardDAO {

	public static final String DASH_TILE_LIST_BY_ROLE = "SELECT DISTINCT ROW_NUMBER() OVER(ORDER BY d.programcode ASC) as Rno,"
			+ " d.programcode,  tile.name 'TileName', "
			+ " d.TileOrder,   d.TileHeaderImage,  d.TileImage,  attribute.name 'AttributeName', d.url,"
			+ " tileattribute.value 'AttributeValue',  tileattribute.AttributeOrder, "
			+ " attribute.type 'Format'  FROM dashboard d  JOIN tileattributes tileattribute "
			+ " ON tileattribute.tileid = d.tileid  JOIN attributes attribute "
			+ " ON tileattribute.attributeid = attribute.id  JOIN tiles tile  ON tile.id = d.tileid "
			+ " AND tile.id = tileattribute.tileid  AND tileattribute.roleid = d.roleid "
			+ " AND tileattribute.attributeid = attribute.id  WHERE d.roleid = ? and d.UserID = ? "
			+ " AND tileattribute.delFlag = 'N'  AND attribute.delFlag = 'N'  AND d.delFlag = 'N' "
			+ " AND tileattribute.delFlag = 'N' order by d.programcode, d.TileOrder, tileattribute.AttributeOrder ";

	public static final String DASH_TILE_BY_ROLE_USER = "SELECT DISTINCT ROW_NUMBER() OVER(ORDER BY d.programcode ASC) as Rno, "
			+ " d.programcode, d.territory,  tile.name 'TileName',  "
			+ " d.TileOrder,   d.TileHeaderImage,  d.TileImage, d.url, d.TileID "
			+ " FROM dashboard d JOIN tiles tile ON tile.id = d.tileid " + " WHERE d.roleid = ? and d.UserID = ? "
			+ " AND d.delFlag = 'N' and tile.DelFlag = 'N' " + " order by d.programcode, d.TileOrder ";

	public List<DashboardDTO> findTilesListByRole(InputRequest userRoleReq);

	/**
	 * @param userRoleReq
	 * @return
	 */
	public List<DashboardDTO> findTilesByRole(InputRequest userRoleReq);
}
