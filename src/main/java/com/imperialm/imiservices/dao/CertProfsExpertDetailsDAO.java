package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.CertProfsExpertDetailsDTO;

import java.util.List;

public interface CertProfsExpertDetailsDAO {

	public static String SELECT_BY_DEALER_CODE_AND_CERT_TYPE = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' ,[CertType] 'certType' ,[Points] 'points' ,[Cert] 'cert' ,[TotalPoints] 'totalPoints'FROM [CertProfsExpertDetails] where [DealerCode] = ?0 AND CertType LIKE ?1";
	public static String SELECT_BY_SID_AND_CERT_TYPE = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' ,[CertType] 'certType' ,[Points] 'points' ,[Cert] 'cert' ,[TotalPoints] 'totalPoints'FROM [CertProfsExpertDetails] where [SID] = ?0 AND CertType LIKE ?1 AND [DealerCode] = ?2";
	public static String SELECT_BY_SID_SUM = "SELECT '' as 'dealerCode' ,'' as 'dealerName' ,[SID] 'sID' ,'' as 'name' ,'' as 'certType' ,SUM([Points]) 'points' ,SUM([Cert]) 'cert' ,SUM([TotalPoints]) 'totalPoints'FROM [CertProfsExpertDetails] where [SID] = ?0 AND [DealerCode] = ?1 GROUP BY [SID]";
	public static String SELECT_BY_DEALER_SUM = "SELECT [DealerCode] as 'dealerCode' ,'' as 'dealerName' , '' as 'sID' ,'' as 'name' ,'' as 'certType' ,SUM([Points]) 'points' ,SUM([Cert]) 'cert' ,SUM([TotalPoints]) 'totalPoints'FROM [CertProfsExpertDetails] where [DealerCode] = ?0 GROUP BY [DealerCode]";
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsByDealerCodeANDCertType(String dealerCode, String certType);
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsBySIDANDCertType(String sid, String certType, String dealerCode);
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsSUMBySID(String sid, String dealerCode);
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsSUMByDealerCode(String dealerCode);
	
}
