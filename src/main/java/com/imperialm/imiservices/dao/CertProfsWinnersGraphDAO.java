package com.imperialm.imiservices.dao;

import java.util.List;
import com.imperialm.imiservices.dto.CertProfsWinnersGraphDTO;

public interface CertProfsWinnersGraphDAO {

	public static String SELECT_BC = "SELECT [ParentTerritory] 'parentTerritory' , '' as 'childTerritory' , '' as certType , SUM([Points]) 'points' , SUM([Certified]) 'certified', SUM([CertifiedSpecalist]) 'certifiedSpecalist', SUM([MasterCertified]) 'masterCertified', SUM([TotalCertified]) 'totalCertified', SUM([MasterCertBCCertRank]) 'masterCertBCCertRank', SUM([MasterCertNATCertRank]) 'masterCertNATCertRank', SUM([AllBCCertRank]) 'allBCCertRank', SUM([AllNATCertRank]) 'allNATCertRank',  '' as error FROM [dbo].[CertProfsWinnersGraph] WHERE [ParentTerritory] = 'CA' OR [ParentTerritory] = 'DN' OR [ParentTerritory] = 'GL' OR [ParentTerritory] = 'MA' OR [ParentTerritory] = 'MW' OR [ParentTerritory] = 'NE' OR [ParentTerritory] = 'SE' OR [ParentTerritory] = 'SW' OR [ParentTerritory] = 'WE' GROUP BY [ParentTerritory]";
	
	public List<CertProfsWinnersGraphDTO> getBCCertifications();
	
}
