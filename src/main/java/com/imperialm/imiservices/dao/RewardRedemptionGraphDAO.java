package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.RewardRedemptionGraphDTO;

public interface RewardRedemptionGraphDAO {

	public static String SELECT_BY_PARENT_TERRITORY_LIST = "SELECT [ParentTerritory] 'parentTerritory', [ChildTerritory] 'childTerritory', [EarnedPoints] 'earnedPoints', [RedeemedPoints] 'redeemedPoints', [BalancePoints] 'balancePoints', [Program] 'program' FROM [dbo].[RewardRedemptionGraph] where [ParentTerritory] IN (?0) AND Program = 'CCP'";
	public static String SELECT_BY_CHILD_TERRITORY_LIST = "SELECT [ParentTerritory] 'parentTerritory', [ChildTerritory] 'childTerritory', [EarnedPoints] 'earnedPoints', [RedeemedPoints] 'redeemedPoints', [BalancePoints] 'balancePoints', [Program] 'program' FROM [dbo].[RewardRedemptionGraph] where [ChildTerritory] IN (?0) AND Program = 'CCP'";
	public static String SELECT_BY_PARENT_TERRITORY_LIST_DISTICT_PARENT = "SELECT [ParentTerritory] 'parentTerritory', '' as 'childTerritory', SUM([EarnedPoints]) 'earnedPoints', SUM([RedeemedPoints]) 'redeemedPoints', SUM([BalancePoints]) 'balancePoints', [Program] 'program' FROM [dbo].[RewardRedemptionGraph] where [ParentTerritory] IN (?0)  AND Program = 'CCP' GROUP BY [ParentTerritory], [Program]";
	public List<RewardRedemptionGraphDTO> getRewardRedemptionGraphByParentTerritoryList(List<String> list);
	public List<RewardRedemptionGraphDTO> getRewardRedemptionGraphByChildTerritoryList(List<String> list);
	public List<RewardRedemptionGraphDTO> getRewardRedemptionGraphByParentTerritoryListDistinct(List<String> list);
}
