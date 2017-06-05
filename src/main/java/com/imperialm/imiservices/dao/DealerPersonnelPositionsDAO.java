package com.imperialm.imiservices.dao;

import java.util.List;

public interface DealerPersonnelPositionsDAO {

	public final String GET_ROLE_BY_POSITIONCODE = "SELECT [RoleID] FROM [DealerPersonnelPositions] where [PositionCode] = ?0 AND DelFlag = 'N'";
	public final String CHECK_POSITIONCODE = "SELECT [PositionCode] FROM [DealerPersonnelPositions] where [PositionCode] = ?0 AND DelFlag = 'N'";
	public final String POSITIONCODES = "SELECT [PositionCode] FROM [DealerPersonnelPositions] where DelFlag = 'N'";
	public int getRoleByPositionCode(String positionCode);
	public boolean checkPositionCode(String positionCode);
	public List<String> getAllPositionCodes();
}
