package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.BrainBoostWinndersGraphDTO;
import com.imperialm.imiservices.model.response.TotalName;

public interface BrainBoostWinndersGraphDAO {

	public static String SELECT_BC = "SELECT [ParentTerritory] 'parentTerritory' ,[ChildTerritory] 'childTerritory', [Points] 'points' , [Partcipants] 'partcipants', [Winners] 'winners', [Earnings] 'earnings', '' as error FROM [dbo].[BrainBoostWinnersGraph] WHERE [ParentTerritory] = 'CA' OR [ParentTerritory] = 'DN' OR [ParentTerritory] = 'GL' OR [ParentTerritory] = 'MA' OR [ParentTerritory] = 'MW' OR [ParentTerritory] = 'NE' OR [ParentTerritory] = 'SE' OR [ParentTerritory] = 'SW' OR [ParentTerritory] = 'WE'";
	public static String SELECT_BC_FILTERED = "SELECT [ParentTerritory] 'parentTerritory' ,'' 'childTerritory' , SUM([Points]) 'points' , SUM([Partcipants]) 'partcipants', SUM([Winners]) 'winners', SUM([Earnings]) 'earnings', '' as error FROM [dbo].[BrainBoostWinnersGraph] WHERE [ParentTerritory] = 'CA' OR [ParentTerritory] = 'DN' OR [ParentTerritory] = 'GL' OR [ParentTerritory] = 'MA' OR [ParentTerritory] = 'MW' OR [ParentTerritory] = 'NE' OR [ParentTerritory] = 'SE' OR [ParentTerritory] = 'SW' OR [ParentTerritory] = 'WE' GROUP BY [ParentTerritory]";
	public static String SELECT_DISTRIC_BY_BC = "SELECT [ParentTerritory] 'parentTerritory' ,'' 'childTerritory' , SUM([Points]) 'points' , SUM([Partcipants]) 'partcipants', SUM([Winners]) 'winners', SUM([Earnings]) 'earnings', '' as error FROM [dbo].[BrainBoostWinnersGraph] WHERE [ParentTerritory] IN (?0) GROUP BY [ParentTerritory]";
	public static String SELECT_BY_TERRITORY = "SELECT [ParentTerritory] 'parentTerritory' ,[ChildTerritory] 'childTerritory' , [Points] 'points' , [Partcipants] 'partcipants', [Winners] 'winners', [Earnings] 'earnings', '' as error FROM [dbo].[BrainBoostWinnersGraph] WHERE [ParentTerritory] IN (?0)";
	public static String SELECT_BY_CHILD_TERRITORY = "SELECT [ParentTerritory] 'parentTerritory' ,[ChildTerritory] 'childTerritory' , [Points] 'points' , [Partcipants] 'partcipants', [Winners] 'winners', [Earnings] 'earnings', '' as error FROM [dbo].[BrainBoostWinnersGraph] WHERE [ChildTerritory] IN (?0)";
	public List<BrainBoostWinndersGraphDTO>  getBCData(boolean filter);
	public List<BrainBoostWinndersGraphDTO>  getAllDistricData(List<String> list);
	public List<BrainBoostWinndersGraphDTO>  getByTerritory(List<String> list);
	public List<BrainBoostWinndersGraphDTO>  getByChildTerritory(List<String> list);
}
