package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.SummaryProgramRewardQuartileGraphDTO;

public interface SummaryProgramRewardQuartileGraphDAO {

	public static String SELECT_BY_PARENT_TERRITORY_YTD = "SELECT [Parent] 'parent', [Child] 'child' , [Toggle] 'toggle', [Earnings]  'earnings', ISNULL([TopQuartile],0) 'topQuartile', [Quartile] 'quartile'  FROM [SummaryProgramRewardQuartileGraph] where toggle = 'ytd' and parent IN (?0)";
	public static String SELECT_BY_CHILD_TERRITORY_YTD = "SELECT [Parent] 'parent', [Child] 'child' , [Toggle] 'toggle', [Earnings]  'earnings', ISNULL([TopQuartile],0) 'topQuartile', [Quartile] 'quartile' FROM [SummaryProgramRewardQuartileGraph] where toggle = 'ytd' and child = ?0";
	
	public List<SummaryProgramRewardQuartileGraphDTO> getSummaryProgramRewardQuartileGraphByParentTerritoryYTD(List<String> territory);
	public List<SummaryProgramRewardQuartileGraphDTO> getSummaryProgramRewardQuartileGraphByChildTerritoryYTD(String territory);
	
}
