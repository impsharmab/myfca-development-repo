package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.MyfcaMSERTotalEarningsDTO;

public interface MyfcaMSERTotalEarningsDAO {

	public static String SELECT_BC_FILTERED = "SELECT '' 'parent', [Child] 'child', '' as 'program', [toggle] 'toggle', SUM([Amount]) 'amount', '' as error FROM [dbo].[MyfcaMSERTotalEarnings] where Parent LIKE 'NAT' AND toggle LIKE 'YTD' group by Child, toggle order by child";
	public static String SELECT_BY_PARENT_TERRITORY_AND_TOGGLE = "SELECT [Parent] 'parent', [Child] 'child', [Program] 'program', [toggle] 'toggle', [Amount] 'amount', '' as error FROM [dbo].[MyfcaMSERTotalEarnings] where Parent LIKE ?0 AND toggle LIKE ?1 order by child";
	public static String SELECT_BY_CHILD_TERRITORY_AND_TOGGLE = "SELECT [Parent] 'parent', [Child] 'child', [Program] 'program', [toggle] 'toggle', [Amount] 'amount', '' as error FROM [dbo].[MyfcaMSERTotalEarnings] where Child LIKE ?0 AND toggle LIKE ?1 order by child";
	public static String SELECT_DISTINCT_PROGRAM_BY_PARENT_TERRITORY_AND_TOGGLE = "SELECT DISTINCT [Program] 'program' FROM [dbo].[MyfcaMSERTotalEarnings] where Parent LIKE ?0 AND toggle LIKE ?1";
	public static String SELECT_BY_PARENT_TERRITORY_AND_TOGGLE_AND_PROGRAM = "SELECT [Parent] 'parent', [Child] 'child', [Program] 'program', [toggle] 'toggle', [Amount] 'amount', '' as error FROM [dbo].[MyfcaMSERTotalEarnings] where Parent LIKE ?0 AND toggle LIKE ?1 AND Program LIKE ?2 order by parent";
	public static String SELECT_BC = "SELECT [Parent] 'parent', [Child] 'child', [Program] 'program', [toggle] 'toggle', [Amount] 'amount', '' as error FROM [dbo].[MyfcaMSERTotalEarnings] where Parent LIKE 'NAT' AND toggle LIKE 'YTD'";
	public static String SELECT_DISTRIC_BY_BC = "SELECT [Parent] 'parent', [Child] 'child', [Program] 'program', [toggle] 'toggle', [Amount] 'amount', '' as error FROM [dbo].[MyfcaMSERTotalEarnings] where Parent IN (?0) AND toggle LIKE 'YTD'";
	public static String SELECT_BY_PROGRAM_AND_TERRITORY_AND_TOGGLE = "SELECT [Parent] 'parent', [Child] 'child', [Program] 'program', [toggle] 'toggle', [Amount] 'amount', '' as error FROM [dbo].[MyfcaMSERTotalEarnings] where Parent LIKE ?0 AND toggle LIKE ?1 AND Program LIKE ?2 order by parent";
	
	public static String SELECT_BY_CHILD_TERRITORY_PROGRAM_AND_TOGGLE = "SELECT [Parent] 'parent', [Child] 'child', [Program] 'program', [toggle] 'toggle', [Amount] 'amount', '' as error FROM [dbo].[MyfcaMSERTotalEarnings] where Child LIKE ?0 AND toggle LIKE ?1 AND Program LIKE ?2 order by child";
	
	public static String SELECT_BY_CHILD_TERRITORY_TOGGLE_SUM = "SELECT '' 'parent', [Child] 'child', '' 'program', '' 'toggle', SUM([Amount]) 'amount', '' as error FROM [dbo].[MyfcaMSERTotalEarnings] where Child LIKE ?0 AND toggle LIKE ?1 GROUP BY [Child] order by child";
	public static String SELECT_BY_PARENT_TERRITORY_AND_TOGGLE_SUM = "SELECT [Parent] 'parent', '' 'child', '' 'program', '' 'toggle', SUM([Amount]) 'amount', '' as error FROM [dbo].[MyfcaMSERTotalEarnings] where Parent LIKE ?0 AND toggle LIKE ?1 GROUP BY [Parent] order by parent";
	
	public static String SELECT_NUMBER_OF_DEALERS_ENROLLED_BY_BC_DISTRICT_AND_TOGGLE = "SELECT [CHILD] FROM [dbo].[MyfcaMSERTotalEarnings] INNER JOIN [dbo].MyfcaMSERTotalEarningsDetails on Child = DealerCode where Parent like ?0 and toggle = ?1 and DealersEnrolled > 0 group by [Child]";
	public List<MyfcaMSERTotalEarningsDTO> getBCEarnings(boolean filter);
	public List<MyfcaMSERTotalEarningsDTO>  getAllDistricData(List<String> list);
	
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphByTerritoryAndToggle(String territory, String toggle);
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphByChildTerritoryAndToggle(String territory, String toggle);
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphByTerritoryAndToggleAndProgram(String territory, String toggle, String Program);
	public List<String> getMSERGraphDistinctProgramsByParentTerritoryAndToggle(String territory, String toggle);
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphByChildTerritoryAndToggleAndProgram(String territory, String toggle, String program);
	
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphProgramsSUMByParentTerritoryAndToggle(String territory, String toggle);
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphProgramsSUMByChildTerritoryAndToggle(String territory, String toggle);
	
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphNumberOfDealersEnrolledByBC_DistrictAndToggle(String territory, String toggle);
	
	
}
