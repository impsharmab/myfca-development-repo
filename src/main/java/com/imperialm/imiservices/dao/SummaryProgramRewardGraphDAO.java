package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.SummaryProgramRewardGraphDTO;

public interface SummaryProgramRewardGraphDAO {

	public static String SELECT_BY_PARENT_TERRITORY_YTD = "SELECT [Parent] 'parent', [Child] 'child' ,'' as toggle , ''  as 'program' ,SUM([Earnings]) 'earnings' FROM [SummaryProgramRewardGraph] where toggle = 'ytd' and parent IN (?0) group by Parent, Child";
	public static String SELECT_BY_CHILD_TERRITORY_YTD = "SELECT [parent] 'parent', [Child] 'child' ,'' as toggle , ''  as 'program' ,SUM([Earnings]) 'earnings' FROM [SummaryProgramRewardGraph] where toggle = 'ytd' and child IN (?0) group by parent, Child";
	public static String SELECT_BY_CHILD_TERRITORY_YTD_SINGLE = "SELECT [parent] 'parent', [Child] 'child' ,'' as toggle , ''  as 'program' ,SUM([Earnings]) 'earnings' FROM [SummaryProgramRewardGraph] where toggle = 'ytd' and child like ?0 group by parent, Child";
	
	public List<SummaryProgramRewardGraphDTO> getSummaryProgramRewardGraphByParentTerritoryYTD(List<String> territory);
	public List<SummaryProgramRewardGraphDTO> getSummaryProgramRewardGraphByChildTerritoryYTD(List<String> territory);
	public List<SummaryProgramRewardGraphDTO> getSummaryProgramRewardGraphByChildTerritoryYTD(String territory);
	
	
}
