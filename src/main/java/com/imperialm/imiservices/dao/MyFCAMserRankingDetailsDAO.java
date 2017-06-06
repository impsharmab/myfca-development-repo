package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.MyFCAMserRankingDetailsDTO;

import java.util.List;

public interface MyFCAMserRankingDetailsDAO {

public static String SELECT_BY_SID = "SELECT [Dealercode] 'dealerCode' ,[DealerName] 'dealername' ,[Sid] 'sid' ,[Name] 'name' ,[EarningsMTD] 'earningsMTD' ,[BCRank] 'bCRank' FROM [MyFCAMserRankingDetails] where [Sid] = ?0 AND [DealerCode]= ?1";
public static String SELECT_SUM_BY_SID = "SELECT [Dealercode] 'dealerCode' ,'' as 'dealername' ,[Sid] 'sid' ,'' as 'name' , SUM([EarningsMTD]) 'earningsMTD' ,''as 'bCRank' FROM [MyFCAMserRankingDetails] where [Sid] = ?0 AND [DealerCode]= ?1 group by [Sid], [DealerCode]";
	
	public List<MyFCAMserRankingDetailsDTO> getMSERDetailsBySID(String territory, String dealerCode);
	public List<MyFCAMserRankingDetailsDTO> getMSERDetailsSUMBySID(String territory, String dealerCode);
	
}
