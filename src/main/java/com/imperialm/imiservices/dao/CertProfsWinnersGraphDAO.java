package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.CertProfsWinnersGraphDTO;

import java.util.List;

public interface CertProfsWinnersGraphDAO {

	public static String SELECT_BC_FILTERED = "SELECT [Parent] 'parentTerritory' , '' as 'childTerritory' , '' as certType , SUM([Points]) 'points' , SUM([Certified]) 'certified', SUM([CertifiedSpecialist]) 'certifiedSpecialist', SUM([MasterCertified]) 'masterCertified', SUM([TotalCertified]) 'totalCertified', SUM([MasterCertBCCertRank]) 'masterCertBCCertRank', SUM([MasterCertNATCertRank]) 'masterCertNATCertRank', SUM([AllBCCertRank]) 'allBCCertRank', SUM([AllNATCertRank]) 'allNATCertRank',  '' as error FROM [CertProfsWinnersGraph] WHERE [Parent] = 'CA' OR [Parent] = 'DN' OR [Parent] = 'GL' OR [Parent] = 'MA' OR [Parent] = 'MW' OR [Parent] = 'NE' OR [Parent] = 'SE' OR [Parent] = 'SW' OR [Parent] = 'WE' GROUP BY [Parent]";
	public static String SELECT_BC = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory', [CertType] 'certType', [Points] 'points', [Certified] 'certified', [CertifiedSpecialist] 'certifiedSpecialist', [MasterCertified] 'masterCertified', [TotalCertified] 'totalCertified', [MasterCertBCCertRank] 'masterCertBCCertRank', [MasterCertNATCertRank] 'masterCertNATCertRank', [AllBCCertRank] 'allBCCertRank', [AllNATCertRank] 'allNATCertRank', '' as error FROM [CertProfsWinnersGraph] WHERE [Parent] = 'CA' OR [Parent] = 'DN' OR [Parent] = 'GL' OR [Parent] = 'MA' OR [Parent] = 'MW' OR [Parent] = 'NE' OR [Parent] = 'SE' OR [Parent] = 'SW' OR [Parent] = 'WE'";
	public static String SELECT_DISTRIC_BY_BC = "SELECT [Parent] 'parentTerritory' , '' as 'childTerritory' , '' as certType , SUM([Points]) 'points' , SUM([Certified]) 'certified', SUM([CertifiedSpecialist]) 'certifiedSpecialist', SUM([MasterCertified]) 'masterCertified', SUM([TotalCertified]) 'totalCertified', SUM([MasterCertBCCertRank]) 'masterCertBCCertRank', SUM([MasterCertNATCertRank]) 'masterCertNATCertRank', SUM([AllBCCertRank]) 'allBCCertRank', SUM([AllNATCertRank]) 'allNATCertRank',  '' as error FROM [CertProfsWinnersGraph] WHERE [Parent] IN (?0) GROUP BY [Parent]";
	public static String SELECT_BY_TERRITORY = "SELECT [Parent] 'parentTerritory' , [Child] as 'childTerritory' , [CertType] as certType , [Points] 'points' , [Certified] 'certified', [CertifiedSpecialist] 'certifiedSpecialist', [MasterCertified] 'masterCertified', [TotalCertified] 'totalCertified', [MasterCertBCCertRank] 'masterCertBCCertRank', [MasterCertNATCertRank] 'masterCertNATCertRank', [AllBCCertRank] 'allBCCertRank', [AllNATCertRank] 'allNATCertRank',  '' as error FROM [CertProfsWinnersGraph] WHERE [Parent] IN (?0)";
	
	public static String SELECT_BY_CHILD_TERRITORY = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory' , [CertType] certType , [Points] 'points' , [Certified] 'certified', [CertifiedSpecialist] 'certifiedSpecialist', [MasterCertified] 'masterCertified', [TotalCertified] 'totalCertified', [MasterCertBCCertRank] 'masterCertBCCertRank', [MasterCertNATCertRank] 'masterCertNATCertRank', [AllBCCertRank] 'allBCCertRank', [AllNATCertRank] 'allNATCertRank',  '' as error FROM [CertProfsWinnersGraph] WHERE [Child] IN (?0)";
	public static String SELECT_BY_CHILD_TERRITORY_SINGLE = "SELECT [Parent] 'parentTerritory', [Child] 'childTerritory' , [CertType] certType , [Points] 'points' , [Certified] 'certified', [CertifiedSpecialist] 'certifiedSpecialist', [MasterCertified] 'masterCertified', [TotalCertified] 'totalCertified', [MasterCertBCCertRank] 'masterCertBCCertRank', [MasterCertNATCertRank] 'masterCertNATCertRank', [AllBCCertRank] 'allBCCertRank', [AllNATCertRank] 'allNATCertRank',  '' as error FROM [CertProfsWinnersGraph] WHERE [Child] LIKE ?0";
	
	public List<CertProfsWinnersGraphDTO> getBCCertifications(boolean filter);
	public List<CertProfsWinnersGraphDTO>  getAllDistricData(List<String> list);
	public List<CertProfsWinnersGraphDTO>  getByTerritory(List<String> list);
	public List<CertProfsWinnersGraphDTO>  getByChildTerritory(List<String> list);
	public List<CertProfsWinnersGraphDTO>  getByChildTerritory(String list);
	
}
