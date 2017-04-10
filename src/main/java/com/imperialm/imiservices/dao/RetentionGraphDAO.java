package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.RetentionGraphDTO;

public interface RetentionGraphDAO {
	public static String SELECT_BY_PARENT_TERRITORY_LIST = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [PositionCode] 'positionCode', [Percentage] 'percentage', '' as error FROM [dbo].[RetentionGraph] where [Parent] IN (?0)";
	public static String SELECT_BY_CHILD_TERRITORY_LIST = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [PositionCode] 'positionCode', [Percentage] 'percentage', '' as error FROM [dbo].[RetentionGraph] where [Child] IN (?0)";
	public static String SELECT_BY_PARENT_TERRITORY_LIST_AND_POSITIONCODE = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [PositionCode] 'positionCode', [Percentage] 'percentage', '' as error FROM [dbo].[RetentionGraph] where [Parent] IN (?0) AND PositionCode = ?1";
	public static String SELECT_BY_CHILD_TERRITORY_LIST_AND_POSITIONCODE = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [PositionCode] 'positionCode', [Percentage] 'percentage', '' as error FROM [dbo].[RetentionGraph] where [Child] IN (?0) AND PositionCode = ?1";
	public static String SELECT_BY_PARENT_TERRITORY_LIST_DISTICT_PARENT = "SELECT [Parent] 'parentTerritory', '' as 'childTerritory', '' as 'positionCode', '' as 'percentage', '' as error FROM [dbo].[RetentionGraph] where [Parent] IN (?0) GROUP BY [Parent]";
	public static String SELECT_BY_BC_AND_POSITIONCODE = "select 'NAT' [Parent], [Parent] childTerritory , [PositionCode] positionCode, avg(Percentage) percentage from RetentionGraph where len(parentterritory) = 2 AND PositionCode = ?0 and Parent = ?1 group by parentterritory , PositionCode";
	public static String SELECT_NAT_BY_POSITIONCODE = "select '' as parentTerritory, 'NAT' childTerritory , positionCode, avg(percentage) 'percentage', '' as error from (select 'NAT' [Parent], [Parent] childTerritory , [PositionCode] positionCode, avg(Percentage) percentage from RetentionGraph where len(parent) = 2 AND PositionCode = ?0 group by Parent , PositionCode) as t group by positionCode";
	public static String SELECT_NAT = "select 'NAT' [Parent], [Parent] childTerritory , [PositionCode] positionCode, avg(Percentage) percentage from RetentionGraph where len(parentterritory) = 2  group by parentterritory , PositionCode";
	
	public List<RetentionGraphDTO> getRetentionGraphByParentTerritoryList(List<String> list);
	public List<RetentionGraphDTO> getRetentionGraphByChildTerritoryList(List<String> list);
	public List<RetentionGraphDTO> getRetentionGraphNATByPositionCode(String positionCode);
	public List<RetentionGraphDTO> getRetentionGraphNAT();
	public List<RetentionGraphDTO> getRetentionGraphBCByBcAndPositionCodeByPositionCode(String bc, String positionCode);
	public List<RetentionGraphDTO> getRetentionGraphByParentTerritoryListAndPositionCode(List<String> list, String positionCode);
	public List<RetentionGraphDTO> getRetentionGraphByChildTerritoryListAndPositionCode(List<String> list, String positionCode);
}
