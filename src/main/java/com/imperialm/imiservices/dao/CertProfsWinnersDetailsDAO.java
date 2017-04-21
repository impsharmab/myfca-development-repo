package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.CertProfsWinnersDetailsDTO;

public interface CertProfsWinnersDetailsDAO {
	public static String SELECT_BY_DEALER_CODE = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' ,[CertType] 'certType' ,[Points] 'points' ,[Certified] 'certified' ,[CertifiedSpecialist] 'certifiedSpecialist' ,[MasterCertified] 'masterCertified' ,[TotalCertified] 'totalCertified' ,ISNULL([YearsOfCertified], 0) 'yearsOfCertified' , [PriorYearCertLevel] priorYearCertLevel,'' as error FROM [dbo].[CertProfsWinnersDetails] where DealerCode = ?0";
	public static String SELECT_BY_SID = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' ,[CertType] 'certType' ,[Points] 'points' ,[Certified] 'certified' ,[CertifiedSpecialist] 'certifiedSpecialist' ,[MasterCertified] 'masterCertified' ,[TotalCertified] 'totalCertified' , ISNULL([YearsOfCertified], 0) 'yearsOfCertified' , [PriorYearCertLevel] priorYearCertLevel, '' as error FROM [dbo].[CertProfsWinnersDetails] where [SID] = ?0";
	
	public List<CertProfsWinnersDetailsDTO> getCertProfsWinnersDetailsByDealerCode(String dealerCode);
	public List<CertProfsWinnersDetailsDTO> getCertProfsWinnersDetailsBySID(String sID);
}
