package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.SIRewardsYOYDetailsDTO;

public interface SIRewardsYOYDetailsDAO {

	public static String SELECT_BY_SID_AND_TOGGLE = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[Toggle] 'toggle' ,[SID] 'sID' ,[Name] 'name' ,[PositionCode] 'positionCode' ,[LastYearEarnings] 'lastYearEarnings' ,[CurrentYearEarnings] 'currentYearEarnings' ,[BCRank] 'bCRank' FROM [SIRewardsYOYDetails] where SID = ?0 and DealerCode = ?1 and Toggle = ?2";
	public static String SELECT_BY_DEALERCODE_AND_TOGGLE = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[Toggle] 'toggle' ,[SID] 'sID' ,[Name] 'name' ,[PositionCode] 'positionCode' ,[LastYearEarnings] 'lastYearEarnings' ,[CurrentYearEarnings] 'currentYearEarnings' ,[BCRank] 'bCRank' FROM [SIRewardsYOYDetails] where DealerCode = ?0 and Toggle = ?1";
	public List<SIRewardsYOYDetailsDTO> getSIRewardsYOYDetailsBySIDAndToggle(String sID, String dealerCode, String toggle);
	public List<SIRewardsYOYDetailsDTO> getSIRewardsYOYDetailsByDealerCodeAndToggle(String dealerCode, String toggle);
	
}
