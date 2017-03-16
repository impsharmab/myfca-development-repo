package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.CertProfsExpertGraphDTO;

public interface CertProfsExpertGraphDAO {

	public static String SELECT_NAT = "SELECT [ParentTerritory] 'parentTerritory' , [ChildTerritory] 'childTerritory' , [Points] 'points' , [TotalPoints] 'totalPoints', [BCPointRank] 'bCPointRank', [NATPointRank] 'nATPointRank', Cert 'cert' , [CertType] 'certType',  '' as error FROM [dbo].[CertProfsExpertGraph] WHERE [ParentTerritory] = 'NAT'";
	public static String SELECT_BY_PARENT_TERRITORY = "SELECT [ParentTerritory] 'parentTerritory' , [ChildTerritory] 'childTerritory' , [Points] 'points' , [TotalPoints] 'totalPoints', [BCPointRank] 'bCPointRank', [NATPointRank] 'nATPointRank', Cert 'cert' , [CertType] 'certType',  '' as error FROM [dbo].[CertProfsExpertGraph] WHERE [ParentTerritory] IN (?0)";
	public static String SELECT_BY_PARENT_TERRITORY_SUM = "SELECT [ParentTerritory] 'parentTerritory' , [ChildTerritory] 'childTerritory' , SUM([Points]) 'points' , SUM([TotalPoints]) 'totalPoints', SUM([BCPointRank]) 'bCPointRank', SUM([NATPointRank]) 'nATPointRank', SUM([CERT]) 'cert' , '' 'certType',  '' as error FROM [dbo].[CertProfsExpertGraph] WHERE [ParentTerritory] IN (?0) GROUP BY [ParentTerritory], [ChildTerritory]";
	public static String SELECT_BC1 = "SELECT [ParentTerritory] 'parentTerritory' , '' as 'childTerritory' , SUM([Points]) 'points' , SUM([TotalPoints]) 'totalPoints', SUM([BCPointRank]) 'bCPointRank', SUM([NATPointRank]) 'nATPointRank', SUM(Cert) 'cert' , '' 'certType',  '' as error FROM [dbo].[CertProfsExpertGraph] WHERE [ParentTerritory] = 'CA' OR [ParentTerritory] = 'DN' OR [ParentTerritory] = 'GL' OR [ParentTerritory] = 'MA' OR [ParentTerritory] = 'MW' OR [ParentTerritory] = 'NE' OR [ParentTerritory] = 'SE' OR [ParentTerritory] = 'SW' OR [ParentTerritory] = 'WE' GROUP BY [ParentTerritory]";
	public static String SELECT_BC = "SELECT [ChildTerritory] 'parentTerritory' , [ParentTerritory] as 'childTerritory' , [Points] 'points' , [TotalPoints] 'totalPoints', [BCPointRank] 'bCPointRank', [NATPointRank] 'nATPointRank', Cert 'cert' , [CertType] 'certType',  '' as error FROM [dbo].[CertProfsExpertGraph] WHERE [ParentTerritory] = 'NAT'";
	public static String SELECT_TOTAL_POINTS_BY_CHILD_TERRITORY = "SELECT '' 'parentTerritory' , [ChildTerritory] as 'childTerritory' , SUM([Points]) 'points' , SUM([TotalPoints]) 'totalPoints', SUM([BCPointRank]) 'bCPointRank', SUM([NATPointRank]) 'nATPointRank', SUM(Cert) 'cert' , '' 'certType',  '' as error FROM [dbo].[CertProfsExpertGraph] WHERE [ChildTerritory] IN (?0) GROUP BY [childTerritory]";
	public static String SELECT_BC_TOTAL_RAM_JEEP = "SELECT [ParentTerritory], '' as childTerritory, [CertType] 'certType', SUM([Cert]) 'cert', SUM([Points]) 'points', SUM([TotalPoints]) 'totalpoints', SUM([BCPointRank]) 'bCPointRank', SUM([NATPointRank]) 'nATPointRank', '' as error FROM [mser2].[dbo].[CertProfsExpertGraph] WHERE [ParentTerritory] = 'CA' OR [ParentTerritory] = 'DN' OR [ParentTerritory] = 'GL' OR [ParentTerritory] = 'MA' OR [ParentTerritory] = 'MW' OR [ParentTerritory] = 'NE' OR [ParentTerritory] = 'SE' OR [ParentTerritory] = 'SW' OR [ParentTerritory] = 'WE' GROUP BY [ParentTerritory], [CertType]";
	public static String SELECT_TOTAL_RAM_JEEP_BY_CHILD_TERRITORY = "SELECT '' as parentTerritory, [ChildTerritory] as childTerritory, '' as 'certType', SUM([Cert]) 'cert', SUM([Points]) 'points', SUM([TotalPoints]) 'totalpoints', SUM([BCPointRank]) 'bCPointRank', SUM([NATPointRank]) 'nATPointRank', '' as error FROM [mser2].[dbo].[CertProfsExpertGraph] WHERE [ChildTerritory] IN (?0) GROUP BY [ChildTerritory]";
	public static String SELECT_TOTAL_RAM_JEEP_BY_CHILD_TERRITORY_AND_CERT_TYPE = "SELECT [ParentTerritory] as parentTerritory, [ChildTerritory] as childTerritory, [CertType] 'certType', [Cert] 'cert', [Points] 'points', [TotalPoints] 'totalpoints', [BCPointRank] 'bCPointRank', [NATPointRank] 'nATPointRank', '' as error FROM [mser2].[dbo].[CertProfsExpertGraph] WHERE [ChildTerritory] IN (?0) AND [CertType] LIKE ?1";
	public static String SELECT_TOTAL_POINTS_BY_CHILD_TERRITORY_AS_PARENT = "SELECT [ChildTerritory] 'parentTerritory' , '' as 'childTerritory' , SUM([Points]) 'points' , SUM([TotalPoints]) 'totalPoints', SUM([BCPointRank]) 'bCPointRank', SUM([NATPointRank]) 'nATPointRank', SUM(Cert) 'cert' , '' 'certType',  '' as error FROM [dbo].[CertProfsExpertGraph] WHERE [ChildTerritory] IN (?0) GROUP BY [childTerritory]";
	public List<CertProfsExpertGraphDTO> getExpertPointsEarned();
	
	public List<CertProfsExpertGraphDTO> getExpertPointsEarnedByChildTerritory(List<String> filters);
	public List<CertProfsExpertGraphDTO> getExpertPointsEarnedByChildTerritoryAsParent(List<String> filters);
	public List<CertProfsExpertGraphDTO> getExpertPointsEarnedByParentTerritory(List<String> filters);
	public List<CertProfsExpertGraphDTO> getExpertPointsEarnedByParentTerritorySum(List<String> filters);
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgram();
	
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgramByChildTerritory(List<String> filters);
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgramByChildTerritoryAndCertType(List<String> filters, String certType);
	
}
