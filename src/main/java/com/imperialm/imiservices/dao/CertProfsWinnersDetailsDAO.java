package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.CertProfsWinnersDetailsDTO;

public interface CertProfsWinnersDetailsDAO {
	public static String SELECT_BY_DEALER_CODE = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' ,[CertType] 'certType' ,[Points] 'points' ,[Certified] 'certified' ,[CertifiedSpecialist] 'certifiedSpecialist' ,[MasterCertified] 'masterCertified' ,[TotalCertified] 'totalCertified' ,ISNULL([YearsOfCertified], 0) 'yearsOfCertified' , [PriorYearCertLevel] priorYearCertLevel,'' as error FROM [CertProfsWinnersDetails] where DealerCode = ?0";
	public static String SELECT_BY_DEALER_CODE_GROUP_BY_SID = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' , '' as 'certType' , SUM([Points]) 'points' ,SUM([Certified]) 'certified' ,SUM([CertifiedSpecialist]) 'certifiedSpecialist' ,SUM([MasterCertified]) 'masterCertified' ,SUM([TotalCertified]) 'totalCertified' ,ISNULL(SUM([YearsOfCertified]), 0) 'yearsOfCertified' , '' as priorYearCertLevel , '' as error FROM [CertProfsWinnersDetails] where DealerCode = ?0 group by [DealerCode] ,[DealerName] ,[SID] ,[Name]";
	public static String SELECT_BY_SID = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' ,[CertType] 'certType' ,[Points] 'points' ,[Certified] 'certified' ,[CertifiedSpecialist] 'certifiedSpecialist' ,[MasterCertified] 'masterCertified' ,[TotalCertified] 'totalCertified' , ISNULL([YearsOfCertified], 0) 'yearsOfCertified' , [PriorYearCertLevel] priorYearCertLevel, '' as error FROM [CertProfsWinnersDetails] where [SID] = ?0 AND [DealerCode] = ?1";
	public static String SELECT_BY_SID_SUM = "SELECT '' as 'dealerCode' , '' as 'dealerName' ,'' as 'sID' , '' as 'name' ,'' as 'certType' ,SUM([Points]) 'points' ,SUM([Certified]) as 'certified' ,SUM([CertifiedSpecialist]) 'certifiedSpecialist' ,SUM([MasterCertified]) 'masterCertified' ,SUM([TotalCertified]) 'totalCertified' , ISNULL(SUM([YearsOfCertified]), 0) 'yearsOfCertified' , '' as priorYearCertLevel, '' as error FROM [CertProfsWinnersDetails] where [SID] = ?0";
	
	public List<CertProfsWinnersDetailsDTO> getCertProfsWinnersDetailsByDealerCode(String dealerCode);
	public List<CertProfsWinnersDetailsDTO> getCertProfsWinnersDetailsByDealerCodeGroupBySID(String dealerCode);
	public List<CertProfsWinnersDetailsDTO> getCertProfsWinnersDetailsBySID(String sID, String dealerCode);
	public List<CertProfsWinnersDetailsDTO> getCertProfsWinnersDetailsSUMBySID(String sID);
}
