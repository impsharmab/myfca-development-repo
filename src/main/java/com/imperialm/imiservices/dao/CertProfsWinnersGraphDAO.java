package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.BrainBoostWinndersGraphDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersGraphDTO;

public interface CertProfsWinnersGraphDAO {

	public static String SELECT_BC_FILTERED = "SELECT [Parent] 'parentTerritory' , '' as 'childTerritory' , '' as certType , SUM([Points]) 'points' , SUM([Certified]) 'certified', SUM([CertifiedSpecalist]) 'certifiedSpecalist', SUM([MasterCertified]) 'masterCertified', SUM([TotalCertified]) 'totalCertified', SUM([MasterCertBCCertRank]) 'masterCertBCCertRank', SUM([MasterCertNATCertRank]) 'masterCertNATCertRank', SUM([AllBCCertRank]) 'allBCCertRank', SUM([AllNATCertRank]) 'allNATCertRank',  '' as error FROM [dbo].[CertProfsWinnersGraph] WHERE [Parent] = 'CA' OR [Parent] = 'DN' OR [Parent] = 'GL' OR [Parent] = 'MA' OR [Parent] = 'MW' OR [Parent] = 'NE' OR [Parent] = 'SE' OR [Parent] = 'SW' OR [Parent] = 'WE' GROUP BY [Parent]";
	public static String SELECT_BC = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [CertType] 'certType', [Points] 'points', [Certified] 'certified', [CertifiedSpecalist] 'certifiedSpecalist', [MasterCertified] 'masterCertified', [TotalCertified] 'totalCertified', [MasterCertBCCertRank] 'masterCertBCCertRank', [MasterCertNATCertRank] 'masterCertNATCertRank', [AllBCCertRank] 'allBCCertRank', [AllNATCertRank] 'allNATCertRank', '' as error FROM [dbo].[CertProfsWinnersGraph] WHERE [Parent] = 'CA' OR [Parent] = 'DN' OR [Parent] = 'GL' OR [Parent] = 'MA' OR [Parent] = 'MW' OR [Parent] = 'NE' OR [Parent] = 'SE' OR [Parent] = 'SW' OR [Parent] = 'WE'";
	public static String SELECT_DISTRIC_BY_BC = "SELECT [Parent] 'parentTerritory' , '' as 'childTerritory' , '' as certType , SUM([Points]) 'points' , SUM([Certified]) 'certified', SUM([CertifiedSpecalist]) 'certifiedSpecalist', SUM([MasterCertified]) 'masterCertified', SUM([TotalCertified]) 'totalCertified', SUM([MasterCertBCCertRank]) 'masterCertBCCertRank', SUM([MasterCertNATCertRank]) 'masterCertNATCertRank', SUM([AllBCCertRank]) 'allBCCertRank', SUM([AllNATCertRank]) 'allNATCertRank',  '' as error FROM [dbo].[CertProfsWinnersGraph] WHERE [Parent] IN (?0) GROUP BY [Parent]";
	public static String SELECT_BY_TERRITORY = "SELECT [Parent] 'parentTerritory' , [Child] as 'childTerritory' , [CertType] as certType , [Points] 'points' , [Certified] 'certified', [CertifiedSpecalist] 'certifiedSpecalist', [MasterCertified] 'masterCertified', [TotalCertified] 'totalCertified', [MasterCertBCCertRank] 'masterCertBCCertRank', [MasterCertNATCertRank] 'masterCertNATCertRank', [AllBCCertRank] 'allBCCertRank', [AllNATCertRank] 'allNATCertRank',  '' as error FROM [dbo].[CertProfsWinnersGraph] WHERE [Parent] IN (?0)";
	
	public static String SELECT_BY_CHILD_TERRITORY = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory' , [CertType] certType , [Points] 'points' , [Certified] 'certified', [CertifiedSpecalist] 'certifiedSpecalist', [MasterCertified] 'masterCertified', [TotalCertified] 'totalCertified', [MasterCertBCCertRank] 'masterCertBCCertRank', [MasterCertNATCertRank] 'masterCertNATCertRank', [AllBCCertRank] 'allBCCertRank', [AllNATCertRank] 'allNATCertRank',  '' as error FROM [dbo].[CertProfsWinnersGraph] WHERE [Child] IN (?0)";
	
	public List<CertProfsWinnersGraphDTO> getBCCertifications(boolean filter);
	public List<CertProfsWinnersGraphDTO>  getAllDistricData(List<String> list);
	public List<CertProfsWinnersGraphDTO>  getByTerritory(List<String> list);
	public List<CertProfsWinnersGraphDTO>  getByChildTerritory(List<String> list);
	
}
