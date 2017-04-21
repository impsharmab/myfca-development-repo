package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.MSERDetailsDTO;

public interface MSERDetailsDAO {

public static String SELECT_BY_SID = "SELECT [Dealercode] 'dealerCode' ,[DealerName] 'dealername' ,[Sid] 'sid' ,[Name] 'name' ,[EarningsMTD] 'earningsMTD' ,[BCRank] 'cBRank' FROM [dbo].[MSERDetails] where [Sid] = ?0";
	
	public List<MSERDetailsDTO> getMSERDetailsBySID(String territory);
	
}
