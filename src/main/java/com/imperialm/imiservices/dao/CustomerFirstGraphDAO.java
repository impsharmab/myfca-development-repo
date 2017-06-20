package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.CustomerFirstGraphDTO;

import java.util.List;

public interface CustomerFirstGraphDAO {
	public static String SELECT_BY_PARENT_TERRITORY = "SELECT [Parent] 'parentTerritory' ,[Child] 'childTerritory' ,[NoCertification] 'noCertification' ,[Performance] 'performance' ,[Process] 'process' ,[VoiceofEmployee] 'voiceofEmployee' ,[Training] 'training' ,[Facility] 'facility' ,[CFAFEAwardCertification] 'cFAFEAwardCertification' ,[toggle] 'toggle' FROM [CustomerFirstGraph] where Parent IN (?0) AND toggle = ?1";
	public static String SELECT_BY_CHILD_TERRITORY = "SELECT [Parent] 'parentTerritory' ,[Child] 'childTerritory' ,[NoCertification] 'noCertification' ,[Performance] 'performance' ,[Process] 'process' ,[VoiceofEmployee] 'voiceofEmployee' ,[Training] 'training' ,[Facility] 'facility' ,[CFAFEAwardCertification] 'cFAFEAwardCertification' ,[toggle] 'toggle' FROM [CustomerFirstGraph] where Child IN (?0) AND toggle = ?1";
	public static String SELECT_BY_CHILD_TERRITORY_SINGLE = "SELECT [Parent] 'parentTerritory' ,[Child] 'childTerritory' ,[NoCertification] 'noCertification' ,[Performance] 'performance' ,[Process] 'process' ,[VoiceofEmployee] 'voiceofEmployee' ,[Training] 'training' ,[Facility] 'facility' ,[CFAFEAwardCertification] 'cFAFEAwardCertification' ,[toggle] 'toggle' FROM [CustomerFirstGraph] where Child like ?0 AND toggle = ?1";
	
	public List<CustomerFirstGraphDTO> getCustomerFirstByParentTerritoryAndToggle(List<String> territory, String toggle);
	public List<CustomerFirstGraphDTO> getCustomerFirstByChildTerritoryAndToggle(List<String> territory, String toggle);
	public List<CustomerFirstGraphDTO> getCustomerFirstByChildTerritoryAndToggle(String territory, String toggle);
}
