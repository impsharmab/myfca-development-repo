package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.CustomerFirstGraphDTO;

public interface CustomerFirstGraphDAO {
	public static String SELECT_BY_PARENT_TERRITORY = "SELECT [Parent] 'parentTerritory' ,[Child] 'childTerritory' ,[NoCertification] 'noCertification' ,[Performance] 'performance' ,[Process] 'process' ,[VoiceofEmployee] 'voiceofEmployee' ,[Training] 'training' ,[Facility] 'facility' ,[CFAFEAwardCertification] 'cFAFEAwardCertification' ,[toggle] 'toggle' FROM [dbo].[CustomerFirstGraph] where Parent IN (?0) AND toggle = ?1";
	public static String SELECT_BY_CHILD_TERRITORY = "SELECT [Parent] 'parentTerritory' ,[Child] 'childTerritory' ,[NoCertification] 'noCertification' ,[Performance] 'performance' ,[Process] 'process' ,[VoiceofEmployee] 'voiceofEmployee' ,[Training] 'training' ,[Facility] 'facility' ,[CFAFEAwardCertification] 'cFAFEAwardCertification' ,[toggle] 'toggle' FROM [dbo].[CustomerFirstGraph] where Child IN (?0) AND toggle = ?1";
	
	public List<CustomerFirstGraphDTO> getCustomerFirstByParentTerritoryAndToggle(List<String> territory, String toggle);
	public List<CustomerFirstGraphDTO> getCustomerFirstByChildTerritoryAndToggle(List<String> territory, String toggle);
}
