package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.CertProfsExpertGraphDTO;

import java.util.List;

public interface CertProfsExpertGraphDAO {

	public static String SELECT_NAT = "SELECT [Parent] 'parentTerritory' , [Child] 'childTerritory' , [Points] 'points' , [TotalPoints] 'totalPoints', [BCPointRank] 'bCPointRank', ISNULL([NATPointRank],0) 'nATPointRank', Cert 'cert' , [CertType] 'certType',  '' as error FROM [CertProfsExpertGraph] WHERE [Parent] = 'NAT'";
	public static String SELECT_BY_PARENT_TERRITORY = "SELECT [Parent] 'parentTerritory' , [Child] 'childTerritory' , [Points] 'points' , [TotalPoints] 'totalPoints', ISNULL([BCPointRank],0) 'bCPointRank', ISNULL([NATPointRank],0) 'nATPointRank', Cert 'cert' , [CertType] 'certType',  '' as error FROM [CertProfsExpertGraph] WHERE [Parent] IN (?0)";
	public static String SELECT_BY_PARENT_TERRITORY_SUM = "SELECT [Parent] 'parentTerritory' , [Child] 'childTerritory' , SUM([Points]) 'points' , SUM([TotalPoints]) 'totalPoints', ISNULL(SUM([BCPointRank]),0) 'bCPointRank', ISNULL(SUM([NATPointRank]),0) 'nATPointRank', SUM([CERT]) 'cert' , '' 'certType',  '' as error FROM [CertProfsExpertGraph] WHERE [Parent] IN (?0) GROUP BY [Parent], [Child]";
	public static String SELECT_BC1 = "SELECT [Parent] 'parentTerritory' , '' as 'childTerritory' , SUM([Points]) 'points' , SUM([TotalPoints]) 'totalPoints', ISNULL(SUM([BCPointRank]),0) 'bCPointRank', ISNULL(SUM([NATPointRank]),0) 'nATPointRank', SUM(Cert) 'cert' , '' 'certType',  '' as error FROM [CertProfsExpertGraph] WHERE [Parent] = 'CA' OR [Parent] = 'DN' OR [Parent] = 'GL' OR [Parent] = 'MA' OR [Parent] = 'MW' OR [Parent] = 'NE' OR [Parent] = 'SE' OR [Parent] = 'SW' OR [Parent] = 'WE' GROUP BY [Parent]";
	public static String SELECT_BC = "SELECT [Child] 'parentTerritory' , [Parent] as 'childTerritory' , [Points] 'points' , [TotalPoints] 'totalPoints', ISNULL([BCPointRank],0) 'bCPointRank', ISNULL([NATPointRank],0) 'nATPointRank', Cert 'cert' , [CertType] 'certType',  '' as error FROM [CertProfsExpertGraph] WHERE [Parent] = 'NAT'";
	public static String SELECT_TOTAL_POINTS_BY_CHILD_TERRITORY = "SELECT '' 'parentTerritory' , [Child] as 'childTerritory' , SUM([Points]) 'points' , SUM([TotalPoints]) 'totalPoints', ISNULL(SUM([BCPointRank]),0) 'bCPointRank', ISNULL(SUM([NATPointRank]),0) 'nATPointRank', SUM(Cert) 'cert' , '' 'certType',  '' as error FROM [CertProfsExpertGraph] WHERE [Child] IN (?0) GROUP BY [Child]";
	public static String SELECT_TOTAL_POINTS_BY_CHILD_TERRITORY_SINGLE = "SELECT '' 'parentTerritory' , [Child] as 'childTerritory' , SUM([Points]) 'points' , SUM([TotalPoints]) 'totalPoints', ISNULL(SUM([BCPointRank]),0) 'bCPointRank', ISNULL(SUM([NATPointRank]),0) 'nATPointRank', SUM(Cert) 'cert' , '' 'certType',  '' as error FROM [CertProfsExpertGraph] WHERE [Child] like ?0 GROUP BY [Child]";
	public static String SELECT_BC_TOTAL_RAM_JEEP = "SELECT [Parent] 'parentTerritory', '' as childTerritory, [CertType] 'certType', SUM([Cert]) 'cert', SUM([Points]) 'points', SUM([TotalPoints]) 'totalpoints', ISNULL(SUM([BCPointRank]),0) 'bCPointRank', SUM([NATPointRank]) 'nATPointRank', '' as error FROM [CertProfsExpertGraph] WHERE [Parent] = 'CA' OR [Parent] = 'DN' OR [Parent] = 'GL' OR [Parent] = 'MA' OR [Parent] = 'MW' OR [Parent] = 'NE' OR [Parent] = 'SE' OR [Parent] = 'SW' OR [Parent] = 'WE' GROUP BY [Parent], [CertType]";
	public static String SELECT_TOTAL_RAM_JEEP_BY_CHILD_TERRITORY = "SELECT '' as parentTerritory, [Child] as childTerritory, '' as 'certType', SUM([Cert]) 'cert', SUM([Points]) 'points', SUM([TotalPoints]) 'totalpoints', ISNULL(SUM([BCPointRank]),0) 'bCPointRank', SUM([NATPointRank]) 'nATPointRank', '' as error FROM [CertProfsExpertGraph] WHERE [Child] IN (?0) GROUP BY [Child]";
	public static String SELECT_TOTAL_RAM_JEEP_BY_CHILD_TERRITORY_AND_CERT_TYPE = "SELECT [Parent] as parentTerritory, [Child] as childTerritory, [CertType] 'certType', [Cert] 'cert', [Points] 'points', [TotalPoints] 'totalpoints', ISNULL([BCPointRank],0) 'bCPointRank', ISNULL([NATPointRank],0) 'nATPointRank', '' as error FROM [CertProfsExpertGraph] WHERE [Child] IN (?0) AND [CertType] LIKE ?1";
	public static String SELECT_TOTAL_RAM_JEEP_BY_CHILD_TERRITORY_AND_CERT_TYPE_SINGLE = "SELECT [Parent] as parentTerritory, [Child] as childTerritory, [CertType] 'certType', [Cert] 'cert', [Points] 'points', [TotalPoints] 'totalpoints', ISNULL([BCPointRank],0) 'bCPointRank', ISNULL([NATPointRank],0) 'nATPointRank', '' as error FROM [CertProfsExpertGraph] WHERE [Child] like ?0 AND [CertType] LIKE ?1";
	public static String SELECT_TOTAL_POINTS_BY_CHILD_TERRITORY_AS_PARENT = "SELECT [Child] 'parentTerritory' , '' as 'childTerritory' , SUM([Points]) 'points' , SUM([TotalPoints]) 'totalPoints', ISNULL(SUM([BCPointRank]),0) 'bCPointRank', ISNULL(SUM([NATPointRank]),0) 'nATPointRank', SUM(Cert) 'cert' , '' 'certType',  '' as error FROM [CertProfsExpertGraph] WHERE [Child] IN (?0) GROUP BY [Child]";
	public List<CertProfsExpertGraphDTO> getExpertPointsEarned();
	
	public List<CertProfsExpertGraphDTO> getExpertPointsEarnedByChildTerritory(List<String> filters);
	public List<CertProfsExpertGraphDTO> getExpertPointsEarnedByChildTerritory(String filters);
	public List<CertProfsExpertGraphDTO> getExpertPointsEarnedByChildTerritoryAsParent(List<String> filters);
	public List<CertProfsExpertGraphDTO> getExpertPointsEarnedByParentTerritory(List<String> filters);
	public List<CertProfsExpertGraphDTO> getExpertPointsEarnedByParentTerritorySum(List<String> filters);
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgram();
	
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgramByChildTerritory(List<String> filters);
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgramByChildTerritoryAndCertType(List<String> filters, String certType);
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgramByChildTerritoryAndCertType(String filters, String certType);
	
	
}
