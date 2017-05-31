package com.imperialm.imiservices.dao;

public interface DealerPersonnelPositionsDAO {

	public final String GET_ROLE_BY_POSITIONCODE = "SELECT [RoleID] FROM [DealerPersonnelPositions] where [PositionCode] = ?0 AND DelFlag = 'N'";
	public final String CHECK_POSITIONCODE = "SELECT [PositionCode] FROM [DealerPersonnelPositions] where [PositionCode] = ?0 AND DelFlag = 'N'";
	public int getRoleByPositionCode(String positionCode);
	public boolean checkPositionCode(String positionCode);
}
