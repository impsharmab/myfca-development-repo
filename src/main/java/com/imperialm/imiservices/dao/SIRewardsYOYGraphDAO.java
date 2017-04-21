package com.imperialm.imiservices.dao;

import java.util.List;
import com.imperialm.imiservices.dto.SIRewardsYOYGraphDTO;

public interface SIRewardsYOYGraphDAO {

	public static String SELECT_BY_PARENT_TERRITORY_AND_TOGGLE = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [Toggle] 'toggle', [LastYearEarnings] 'lastYearEarnings', [BCDealerRank] 'bCDealerRank', [CurrentYearEarnings] 'currentYearEarnings' FROM [dbo].[SIRewardsYOYGraph] where [Parent] LIKE ?0 AND [Toggle] LIKE ?1";
	public static String SELECT_BY_CHILD_TERRITORY_AND_TOGGLE = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [Toggle] 'toggle', [LastYearEarnings] 'lastYearEarnings', [BCDealerRank] 'bCDealerRank', [CurrentYearEarnings] 'currentYearEarnings' FROM [dbo].[SIRewardsYOYGraph] where [Child] LIKE ?0 AND [Toggle] LIKE ?1";
	public static String SELECT_BY_PARENT_TERRITORY_LIST_AND_TOGGLE = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [Toggle] 'toggle', [LastYearEarnings] 'lastYearEarnings', [BCDealerRank] 'bCDealerRank', [CurrentYearEarnings] 'currentYearEarnings' FROM [dbo].[SIRewardsYOYGraph] where [Parent] IN (?0) AND [Toggle] LIKE ?1";
	public static String SELECT_BY_PARENT_TERRITORY_LIST_AND_TOGGLE_DISTICT_PARENT = "SELECT [Parent] 'parentTerritory', '' as 'childTerritory', '' as 'toggle', SUM([LastYearEarnings]) 'lastYearEarnings', SUM([BCDealerRank]) 'bCDealerRank', SUM([CurrentYearEarnings]) 'currentYearEarnings' FROM [dbo].[SIRewardsYOYGraph] where [Parent] IN (?0) AND [Toggle] LIKE ?1 GROUP BY [Parent]";
	
	public List<SIRewardsYOYGraphDTO> getSIRewardsYOYGraphByTerritoryAndToggle(String territory, String toggle);
	public List<SIRewardsYOYGraphDTO> getSIRewardsYOYGraphByChildAndToggle(String territory, String toggle);
	public List<SIRewardsYOYGraphDTO> getSIRewardsYOYGraphByTerritoryAndToggle(List<String> territory, String toggle);
	public List<SIRewardsYOYGraphDTO> getSIRewardsYOYGraphByTerritoryAndToggleFilterParent(List<String> territory, String toggle);
}
