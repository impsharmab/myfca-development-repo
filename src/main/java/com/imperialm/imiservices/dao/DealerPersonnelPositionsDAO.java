package com.imperialm.imiservices.dao;

public interface DealerPersonnelPositionsDAO {

	public final String GET_ROLE_BY_POSITIONCODE = "SELECT[RoleID] FROM [mser2].[dbo].[DealerPersonnelPositions] where [Code] Like ?0 AND DelFlag LIKE 'N'";
	public int getRoleByPositionCode(String positionCode);
}
