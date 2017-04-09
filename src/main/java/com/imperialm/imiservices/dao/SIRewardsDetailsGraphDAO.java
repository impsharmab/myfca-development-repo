package com.imperialm.imiservices.dao;

import java.util.List;
import com.imperialm.imiservices.dto.SIRewardsDetailsGraphDTO;
import com.imperialm.imiservices.dto.SIRewardsYOYGraphDTO;


public interface SIRewardsDetailsGraphDAO {
	
	public static String SELECT_BY_PARENT_TERRITORY_AND_TOGGLE = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [Toggle] 'toggle', [AvgSurveyScore] 'avgSurveyScore', [ProjectedEarnings] 'projectedEarnings', [BCDlearRankEarnings] bCDlearRankEarnings FROM [dbo].[SIRewardsDetailsGraph] where [Parent] LIKE ?0 AND [Toggle] LIKE ?1";
	public static String SELECT_BY_PARENT_TERRITORY_LIST_AND_TOGGLE = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [Toggle] 'toggle', [AvgSurveyScore] 'avgSurveyScore', [ProjectedEarnings] 'projectedEarnings', [BCDlearRankEarnings] bCDlearRankEarnings FROM [dbo].[SIRewardsDetailsGraph] where [Parent] IN (?0) AND [Toggle] LIKE ?1";
	public static String SELECT_BY_CHILD_TERRITORY_LIST_AND_TOGGLE = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [Toggle] 'toggle', [AvgSurveyScore] 'avgSurveyScore', [ProjectedEarnings] 'projectedEarnings', [BCDlearRankEarnings] bCDlearRankEarnings FROM [dbo].[SIRewardsDetailsGraph] where [Child] IN (?0) AND [Toggle] LIKE ?1";
	public static String SELECT_BY_PARENT_TERRITORY_LIST_AND_TOGGLE_DISTICT_PARENT = "SELECT [Parent] 'parentTerritory', '' as 'childTerritory', '' as 'toggle', SUM([AvgSurveyScore]) 'avgSurveyScore', SUM([ProjectedEarnings]) 'projectedEarnings', SUM([BCDlearRankEarnings]) 'bCDlearRankEarnings' FROM [mser2].[dbo].[SIRewardsDetailsGraph] where [Parent] IN (?0) AND [Toggle] LIKE ?1 GROUP BY [Parent]";
	public List<SIRewardsDetailsGraphDTO> getSIRewardsDetailsGraphByTerritoryAndToggle(String territory, String toggle);
	public List<SIRewardsDetailsGraphDTO> getSIRewardsDetailsGraphByTerritoryAndToggle(List<String> territory, String toggle);
	public List<SIRewardsDetailsGraphDTO> getSIRewardsDetailsGraphByChildTerritoryAndToggle(List<String> territory, String toggle);
	public List<SIRewardsDetailsGraphDTO> getSIRewardsDetailsGraphByTerritoryAndToggleFilterParent(List<String> territory, String toggle);
}
