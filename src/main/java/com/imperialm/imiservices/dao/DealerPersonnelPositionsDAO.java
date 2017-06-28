package com.imperialm.imiservices.dao;

import java.util.List;

public interface DealerPersonnelPositionsDAO {

	public final String GET_ROLE_BY_POSITIONCODE = "SELECT [RoleID] FROM [DealerPersonnelPositions] where [PositionCode] = ?0 AND DelFlag = 'N'";
	public final String CHECK_POSITIONCODE = "SELECT [PositionCode] FROM [DealerPersonnelPositions] where [PositionCode] = ?0 AND DelFlag = 'N'";
	public final String POSITIONCODES = "SELECT [PositionCode] FROM [DealerPersonnelPositions] where DelFlag = 'N'";
	//THIS IS FROM DEALERINFO
	public final String CHECK_DEALERCODE = "SELECT [DealerCode] FROM [DealerInfo] where [DealerCode] = ?0 and DelFlag = 'N'";
	public final String GET_DEALER_PRINCIPAL = "SELECT [SID] FROM [DealerPersonnel] where [DealerCode] = ?0 and [PositionCode] = '01'";
	public int getRoleByPositionCode(String positionCode);
	public boolean checkPositionCode(String positionCode);
	public List<String> getAllPositionCodes();
	public boolean isValidDealer(String dealerCode);
	public List<String> getDealerPricipal(String dealerCode);
}
