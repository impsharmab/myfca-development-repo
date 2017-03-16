package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.BrainBoostWinndersGraphDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersGraphDTO;

public interface CertProfsWinnersGraphDAO {

	public static String SELECT_BC_FILTERED = "SELECT [ParentTerritory] 'parentTerritory' , '' as 'childTerritory' , '' as certType , SUM([Points]) 'points' , SUM([Certified]) 'certified', SUM([CertifiedSpecalist]) 'certifiedSpecalist', SUM([MasterCertified]) 'masterCertified', SUM([TotalCertified]) 'totalCertified', SUM([MasterCertBCCertRank]) 'masterCertBCCertRank', SUM([MasterCertNATCertRank]) 'masterCertNATCertRank', SUM([AllBCCertRank]) 'allBCCertRank', SUM([AllNATCertRank]) 'allNATCertRank',  '' as error FROM [dbo].[CertProfsWinnersGraph] WHERE [ParentTerritory] = 'CA' OR [ParentTerritory] = 'DN' OR [ParentTerritory] = 'GL' OR [ParentTerritory] = 'MA' OR [ParentTerritory] = 'MW' OR [ParentTerritory] = 'NE' OR [ParentTerritory] = 'SE' OR [ParentTerritory] = 'SW' OR [ParentTerritory] = 'WE' GROUP BY [ParentTerritory]";
	public static String SELECT_BC = "SELECT [ParentTerritory] 'parentTerritory', [ChildTerritory] 'childTerritory', [CertType] 'certType', [Points] 'points', [Certified] 'certified', [CertifiedSpecalist] 'certifiedSpecalist', [MasterCertified] 'masterCertified', [TotalCertified] 'totalCertified', [MasterCertBCCertRank] 'masterCertBCCertRank', [MasterCertNATCertRank] 'masterCertNATCertRank', [AllBCCertRank] 'allBCCertRank', [AllNATCertRank] 'allNATCertRank', '' as error FROM [dbo].[CertProfsWinnersGraph] WHERE [ParentTerritory] = 'CA' OR [ParentTerritory] = 'DN' OR [ParentTerritory] = 'GL' OR [ParentTerritory] = 'MA' OR [ParentTerritory] = 'MW' OR [ParentTerritory] = 'NE' OR [ParentTerritory] = 'SE' OR [ParentTerritory] = 'SW' OR [ParentTerritory] = 'WE'";
	public static String SELECT_DISTRIC_BY_BC = "SELECT [ParentTerritory] 'parentTerritory' , '' as 'childTerritory' , '' as certType , SUM([Points]) 'points' , SUM([Certified]) 'certified', SUM([CertifiedSpecalist]) 'certifiedSpecalist', SUM([MasterCertified]) 'masterCertified', SUM([TotalCertified]) 'totalCertified', SUM([MasterCertBCCertRank]) 'masterCertBCCertRank', SUM([MasterCertNATCertRank]) 'masterCertNATCertRank', SUM([AllBCCertRank]) 'allBCCertRank', SUM([AllNATCertRank]) 'allNATCertRank',  '' as error FROM [dbo].[CertProfsWinnersGraph] WHERE [ParentTerritory] IN (?0) GROUP BY [ParentTerritory]";
	public static String SELECT_BY_TERRITORY = "SELECT [ParentTerritory] 'parentTerritory' , [ChildTerritory] as 'childTerritory' , [CertType] as certType , [Points] 'points' , [Certified] 'certified', [CertifiedSpecalist] 'certifiedSpecalist', [MasterCertified] 'masterCertified', [TotalCertified] 'totalCertified', [MasterCertBCCertRank] 'masterCertBCCertRank', [MasterCertNATCertRank] 'masterCertNATCertRank', [AllBCCertRank] 'allBCCertRank', [AllNATCertRank] 'allNATCertRank',  '' as error FROM [dbo].[CertProfsWinnersGraph] WHERE [ParentTerritory] IN (?0)";
	
	public static String SELECT_BY_CHILD_TERRITORY = "SELECT [ParentTerritory] 'parentTerritory', [ChildTerritory] 'childTerritory' , [CertType] certType , [Points] 'points' , [Certified] 'certified', [CertifiedSpecalist] 'certifiedSpecalist', [MasterCertified] 'masterCertified', [TotalCertified] 'totalCertified', [MasterCertBCCertRank] 'masterCertBCCertRank', [MasterCertNATCertRank] 'masterCertNATCertRank', [AllBCCertRank] 'allBCCertRank', [AllNATCertRank] 'allNATCertRank',  '' as error FROM [dbo].[CertProfsWinnersGraph] WHERE [ChildTerritory] IN (?0)";
	
	public List<CertProfsWinnersGraphDTO> getBCCertifications(boolean filter);
	public List<CertProfsWinnersGraphDTO>  getAllDistricData(List<String> list);
	public List<CertProfsWinnersGraphDTO>  getByTerritory(List<String> list);
	public List<CertProfsWinnersGraphDTO>  getByChildTerritory(List<String> list);
	
}
