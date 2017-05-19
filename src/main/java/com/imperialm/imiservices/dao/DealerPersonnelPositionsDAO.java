package com.imperialm.imiservices.dao;

public interface DealerPersonnelPositionsDAO {

	public final String GET_ROLE_BY_POSITIONCODE = "SELECT[RoleID] FROM [DealerPersonnelPositions] where [PositionCode] Like ?0 AND DelFlag LIKE 'N'";
	public int getRoleByPositionCode(String positionCode);
}
