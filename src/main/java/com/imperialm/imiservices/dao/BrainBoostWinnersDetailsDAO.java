package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.BrainBoostWinnersDetailsDTO;

public interface BrainBoostWinnersDetailsDAO {
	public static String SELECT_BY_DEALER_CODE = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' ,[Toggle] 'toggle' ,[Points] 'points' ,[Partcipants] 'partcipants' ,[Winners] 'winners' ,[Earnings] 'Earnings' FROM [dbo].[BrainBoostWinnersDetails] where DealerCode = ?0 AND Toggle = ?1";
	public static String SELECT_BY_SID = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' ,[Toggle] 'toggle' ,[Points] 'points' ,[Partcipants] 'partcipants' ,[Winners] 'winners' ,[Earnings] 'Earnings' FROM [dbo].[BrainBoostWinnersDetails] where [SID] = ?0 AND Toggle = ?1 and [DealerCode] = ?2";
	public static String SELECT_BY_DEALERCODE_SUM = "SELECT [DealerCode] 'dealerCode' ,'' as 'dealerName' ,'' as 'sID' ,'' as 'name' ,'' as 'toggle' ,SUM([Points]) 'points' ,SUM([Partcipants]) 'partcipants' ,SUM([Winners]) 'winners' ,SUM([Earnings]) 'Earnings' FROM [dbo].[BrainBoostWinnersDetails] where [DealerCode] = ?0  AND Toggle = ?1 GROUP BY [DealerCode]";
	
	
	public List<BrainBoostWinnersDetailsDTO> getBrainBoostWinnersDetailsByDealerCode(String dealerCode, String toggle);
	public List<BrainBoostWinnersDetailsDTO> getBrainBoostWinnersDetailsBySID(String sID, String toggle, String dealerCode);
	public List<BrainBoostWinnersDetailsDTO> getBrainBoostWinnersDetailsSUMByDealerCode(String sID, String toggle);
}
