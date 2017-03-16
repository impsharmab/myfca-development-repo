package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.CertProfsExpertDetailsDTO;

public interface CertProfsExpertDetailsDAO {

	public static String SELECT_BY_DEALER_CODE_AND_CERT_TYPE = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' ,[CertType] 'certType' ,[Points] 'points' ,[Cert] 'cert' ,[TotalPoints] 'totalPoints'FROM [mser2].[dbo].[CertProfsExpertDetails] where [DealerCode] = ?0 AND CertType = ?1";
	public static String SELECT_BY_SID_AND_CERT_TYPE = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' ,[CertType] 'certType' ,[Points] 'points' ,[Cert] 'cert' ,[TotalPoints] 'totalPoints'FROM [mser2].[dbo].[CertProfsExpertDetails] where [SID] = ?0 AND CertType = ?1";
	public static String SELECT_BY_SID_SUM = "SELECT '' as 'dealerCode' ,'' as 'dealerName' ,[SID] 'sID' ,'' as 'name' ,'' as 'certType' ,SUM([Points]) 'points' ,SUM([Cert]) 'cert' ,SUM([TotalPoints]) 'totalPoints'FROM [mser2].[dbo].[CertProfsExpertDetails] where [SID] = ?0 GROUP BY [SID]";
	public static String SELECT_BY_DEALER_SUM = "SELECT [DealerCode] as 'dealerCode' ,'' as 'dealerName' , '' as 'sID' ,'' as 'name' ,'' as 'certType' ,SUM([Points]) 'points' ,SUM([Cert]) 'cert' ,SUM([TotalPoints]) 'totalPoints'FROM [mser2].[dbo].[CertProfsExpertDetails] where [DealerCode] = ?0 GROUP BY [DealerCode]";
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsByDealerCodeANDCertType(String dealerCode, String certType);
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsBySIDANDCertType(String sid, String certType);
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsSUMBySID(String sid);
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsSUMByDealerCode(String dealerCode);
	
}
