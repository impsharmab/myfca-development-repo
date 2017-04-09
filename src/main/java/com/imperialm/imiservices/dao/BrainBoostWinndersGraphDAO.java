package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.BrainBoostWinndersGraphDTO;
import com.imperialm.imiservices.model.response.TotalName;

public interface BrainBoostWinndersGraphDAO {

	public static String SELECT_BC = "SELECT [Parent] 'parentTerritory' ,[Child] 'childTerritory', [Points] 'points' , [Partcipants] 'partcipants', [Winners] 'winners', [Earnings] 'earnings', '' as error FROM [dbo].[BrainBoostWinnersGraph] WHERE [Parent] = 'CA' OR [Parent] = 'DN' OR [Parent] = 'GL' OR [Parent] = 'MA' OR [Parent] = 'MW' OR [Parent] = 'NE' OR [Parent] = 'SE' OR [Parent] = 'SW' OR [Parent] = 'WE'";
	public static String SELECT_BC_FILTERED = "SELECT [Parent] 'parentTerritory' ,'' 'childTerritory' , SUM([Points]) 'points' , SUM([Partcipants]) 'partcipants', SUM([Winners]) 'winners', SUM([Earnings]) 'earnings', '' as error FROM [dbo].[BrainBoostWinnersGraph] WHERE [Parent] = 'CA' OR [Parent] = 'DN' OR [Parent] = 'GL' OR [Parent] = 'MA' OR [Parent] = 'MW' OR [Parent] = 'NE' OR [Parent] = 'SE' OR [Parent] = 'SW' OR [Parent] = 'WE' GROUP BY [Parent]";
	public static String SELECT_DISTRIC_BY_BC = "SELECT [Parent] 'parentTerritory' ,'' 'childTerritory' , SUM([Points]) 'points' , SUM([Partcipants]) 'partcipants', SUM([Winners]) 'winners', SUM([Earnings]) 'earnings', '' as error FROM [dbo].[BrainBoostWinnersGraph] WHERE [Parent] IN (?0) GROUP BY [Parent]";
	public static String SELECT_BY_TERRITORY = "SELECT [Parent] 'parentTerritory' ,[Child] 'childTerritory' , [Points] 'points' , [Partcipants] 'partcipants', [Winners] 'winners', [Earnings] 'earnings', '' as error FROM [dbo].[BrainBoostWinnersGraph] WHERE [Parent] IN (?0)";
	public static String SELECT_BY_CHILD_TERRITORY = "SELECT [Parent] 'parentTerritory' ,[Child] 'childTerritory' , [Points] 'points' , [Partcipants] 'partcipants', [Winners] 'winners', [Earnings] 'earnings', '' as error FROM [dbo].[BrainBoostWinnersGraph] WHERE [Child] IN (?0)";
	public List<BrainBoostWinndersGraphDTO>  getBCData(boolean filter);
	public List<BrainBoostWinndersGraphDTO>  getAllDistricData(List<String> list);
	public List<BrainBoostWinndersGraphDTO>  getByTerritory(List<String> list);
	public List<BrainBoostWinndersGraphDTO>  getByChildTerritory(List<String> list);
}
