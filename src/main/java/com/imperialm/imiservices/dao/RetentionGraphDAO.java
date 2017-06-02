package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.RetentionGraphDTO;

public interface RetentionGraphDAO {
	public static String SELECT_BY_PARENT_TERRITORY_LIST = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [PositionCode] 'positionCode', [Percentage] 'percentage', '' as error FROM [RetentionGraph] where [Parent] IN (?0)";
	public static String SELECT_BY_CHILD_TERRITORY_LIST = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [PositionCode] 'positionCode', [Percentage] 'percentage', '' as error FROM [RetentionGraph] where [Child] IN (?0)";
	public static String SELECT_BY_PARENT_TERRITORY_LIST_AND_POSITIONCODE = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [PositionCode] 'positionCode', [Percentage] 'percentage', '' as error FROM [RetentionGraph] where [Parent] IN (?0) AND PositionCode = ?1";
	public static String SELECT_BY_CHILD_TERRITORY_LIST_AND_POSITIONCODE = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [PositionCode] 'positionCode', [Percentage] 'percentage', '' as error FROM [RetentionGraph] where [Child] IN (?0) AND PositionCode = ?1";
	public static String SELECT_BY_CHILD_TERRITORY_AND_POSITIONCODE = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [PositionCode] 'positionCode', [Percentage] 'percentage', '' as error FROM [RetentionGraph] where [Child] like ?0 AND PositionCode = ?1";
	public static String SELECT_BY_PARENT_TERRITORY_LIST_DISTICT_PARENT = "SELECT [Parent] 'parentTerritory', '' as 'childTerritory', '' as 'positionCode', '' as 'percentage', '' as error FROM [RetentionGraph] where [Parent] IN (?0) GROUP BY [Parent]";
	public static String SELECT_BY_BC_AND_POSITIONCODE = "select [Parent] as parentTerritory, [Child] childTerritory , [PositionCode] positionCode, [Percentage] percentage, '' as error from RetentionGraph where PositionCode = ?0 and [Parent] = ?1 group by parent , PositionCode";
	public static String SELECT_NAT_BY_POSITIONCODE = "select 'NAT' as parentTerritory,'NAT' as childTerritory , PositionCode 'positionCode', ((CAST(sum(EndingPopulation) as DECIMAL)/(CAST(sum(BeginingPopulation) as DECIMAL)))*100) 'percentage', '' as error from RetentionGraph where parent = 'nat' and PositionCode = ?0 group by PositionCode";
	public static String SELECT_NAT = "select 'NAT' as parent,'NAT' as child , PositionCode 'positionCode', ((CAST(sum(EndingPopulation) as DECIMAL)/(CAST(sum(BeginingPopulation) as DECIMAL)))*100) 'percentage', '' as error from RetentionGraph where parent = 'nat' group by PositionCode";
	
	public List<RetentionGraphDTO> getRetentionGraphByParentTerritoryList(List<String> list);
	public List<RetentionGraphDTO> getRetentionGraphByChildTerritoryList(List<String> list);
	public List<RetentionGraphDTO> getRetentionGraphNATByPositionCode(String positionCode);
	public List<RetentionGraphDTO> getRetentionGraphNAT();
	public List<RetentionGraphDTO> getRetentionGraphBCByBcAndPositionCodeByPositionCode(String bc, String positionCode);
	public List<RetentionGraphDTO> getRetentionGraphByParentTerritoryListAndPositionCode(List<String> list, String positionCode);
	public List<RetentionGraphDTO> getRetentionGraphByChildTerritoryListAndPositionCode(List<String> list, String positionCode);
	public List<RetentionGraphDTO> getRetentionGraphByChildTerritoryListAndPositionCode(String list, String positionCode);
}
