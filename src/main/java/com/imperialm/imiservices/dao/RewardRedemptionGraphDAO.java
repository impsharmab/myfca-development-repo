package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.RewardRedemptionGraphDTO;

public interface RewardRedemptionGraphDAO {

	public static String SELECT_BY_PARENT_TERRITORY_LIST = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', SUM([EarnedPoints]) 'earnedPoints', SUM([RedeemedPoints]) 'redeemedPoints', SUM([BalancePoints]) 'balancePoints', '' as 'program' FROM [RewardRedemptionGraph] where [Parent] IN (?0) group by [Child], [Parent]";
	public static String SELECT_BY_CHILD_TERRITORY_LIST = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', SUM([EarnedPoints]) 'earnedPoints', SUM([RedeemedPoints]) 'redeemedPoints', SUM([BalancePoints]) 'balancePoints', '' as 'program' FROM [RewardRedemptionGraph] where [Child] IN (?0) group by [Child], [Parent]";
	public static String SELECT_BY_CHILD_TERRITORY = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', SUM([EarnedPoints]) 'earnedPoints', SUM([RedeemedPoints]) 'redeemedPoints', SUM([BalancePoints]) 'balancePoints', '' as 'program' FROM [RewardRedemptionGraph] where [Child] like ?0 group by [Child], [Parent]";
	public static String SELECT_BY_PARENT_TERRITORY_LIST_DISTICT_PARENT = "SELECT [Parent] 'parentTerritory', '' as 'childTerritory', SUM([EarnedPoints]) 'earnedPoints', SUM([RedeemedPoints]) 'redeemedPoints', SUM([BalancePoints]) 'balancePoints', '' as 'program' FROM [RewardRedemptionGraph] where [Parent] IN (?0) GROUP BY [Parent]";
	public List<RewardRedemptionGraphDTO> getRewardRedemptionGraphByParentTerritoryList(List<String> list);
	public List<RewardRedemptionGraphDTO> getRewardRedemptionGraphByChildTerritoryList(List<String> list);
	public List<RewardRedemptionGraphDTO> getRewardRedemptionGraphByChildTerritory(String list);
	public List<RewardRedemptionGraphDTO> getRewardRedemptionGraphByParentTerritoryListDistinct(List<String> list);
}
