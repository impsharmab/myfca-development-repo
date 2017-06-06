package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.SIRewardsYOYGraphDTO;

import java.util.List;

public interface SIRewardsYOYGraphDAO {

	public static String SELECT_BY_PARENT_TERRITORY_AND_TOGGLE = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [Toggle] 'toggle', [LastYearEarnings] 'lastYearEarnings', [BCDealerRank] 'bCDealerRank', [CurrentYearEarnings] 'currentYearEarnings' FROM [SIRewardsYOYGraph] where [Parent] = ?0 AND [Toggle] = ?1";
	public static String SELECT_SUM_BY_PARENT_TERRITORY_AND_TOGGLE = "SELECT [Parent] 'parentTerritory', '' 'childTerritory', '' 'toggle', SUM([LastYearEarnings]) 'lastYearEarnings', 0 'bCDealerRank', SUM([CurrentYearEarnings]) 'currentYearEarnings' FROM [SIRewardsYOYGraph] where [Parent] = ?0 AND [Toggle] = ?1 group by parent";
	public static String SELECT_BY_CHILD_TERRITORY_AND_TOGGLE = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [Toggle] 'toggle', [LastYearEarnings] 'lastYearEarnings', [BCDealerRank] 'bCDealerRank', [CurrentYearEarnings] 'currentYearEarnings' FROM [SIRewardsYOYGraph] where [Child] = ?0 AND [Toggle] = ?1";
	public static String SELECT_BY_PARENT_TERRITORY_LIST_AND_TOGGLE = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [Toggle] 'toggle', [LastYearEarnings] 'lastYearEarnings', [BCDealerRank] 'bCDealerRank', [CurrentYearEarnings] 'currentYearEarnings' FROM [SIRewardsYOYGraph] where [Parent] IN (?0) AND [Toggle] = ?1";
	public static String SELECT_BY_PARENT_TERRITORY_LIST_AND_TOGGLE_DISTICT_PARENT = "SELECT [Parent] 'parentTerritory', '' as 'childTerritory', '' as 'toggle', SUM([LastYearEarnings]) 'lastYearEarnings', SUM([BCDealerRank]) 'bCDealerRank', SUM([CurrentYearEarnings]) 'currentYearEarnings' FROM [SIRewardsYOYGraph] where [Parent] IN (?0) AND [Toggle] = ?1 GROUP BY [Parent]";
	
	public List<SIRewardsYOYGraphDTO> getSIRewardsYOYGraphByTerritoryAndToggle(String territory, String toggle);
	public List<SIRewardsYOYGraphDTO> getSIRewardsYOYGraphSumByTerritoryAndToggle(String territory, String toggle);
	public List<SIRewardsYOYGraphDTO> getSIRewardsYOYGraphByChildAndToggle(String territory, String toggle);
	public List<SIRewardsYOYGraphDTO> getSIRewardsYOYGraphByTerritoryAndToggle(List<String> territory, String toggle);
	public List<SIRewardsYOYGraphDTO> getSIRewardsYOYGraphByTerritoryAndToggleFilterParent(List<String> territory, String toggle);
}
