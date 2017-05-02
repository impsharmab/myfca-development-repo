package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.MyFCAMserRankingDetailsDTO;

public interface MyFCAMserRankingDetailsDAO {

public static String SELECT_BY_SID = "SELECT [Dealercode] 'dealerCode' ,[DealerName] 'dealername' ,[Sid] 'sid' ,[Name] 'name' ,[EarningsMTD] 'earningsMTD' ,[BCRank] 'bCRank' FROM [dbo].[MyFCAMserRankingDetails] where [Sid] = ?0 AND [DealerCode]= ?1";
	
	public List<MyFCAMserRankingDetailsDTO> getMSERDetailsBySID(String territory, String dealerCode);
	
}
