package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.MSERDetailsGraphDTO;

public interface MSERDetailsGraphDAO {
	public static String SELECT_BY_PARENT = "SELECT [Parent] 'parent' ,[Child] 'child' ,[EarningsMTD] 'earningsMTD' ,[BCRank] 'bCRank' FROM [dbo].[MSERDetailsGraph] where [Parent] = ?0 order by [Parent], [Child]";
	public static String SELECT_BY_CHILD = "SELECT [Parent] 'parent' ,[Child] 'child' ,[EarningsMTD] 'earningsMTD' ,[BCRank] 'bCRank' FROM [dbo].[MSERDetailsGraph] where [Child] like ?0 order by [Parent], [Child]";
	
	public List<MSERDetailsGraphDTO> getMSERDetailsGraphByParent(String territory);
	public List<MSERDetailsGraphDTO> getMSERDetailsGraphByChild(String territory);
	
}
