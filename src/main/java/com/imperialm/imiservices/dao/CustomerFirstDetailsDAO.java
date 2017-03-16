package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.CustomerFirstDetailsDTO;

public interface CustomerFirstDetailsDAO {
	public static final String SELECT_BY_PARENT_AND_TOGGLE = "SELECT [ParentTerritory] 'parentTerritory' ,[ChildTerritory] 'childTerritory' ,[NoCertification] 'noCertification' ,[Performance] 'performance' ,[Process] 'process' ,[VoiceofEmployee] 'voiceofEmployee' ,[Training] 'training' ,[Facility] 'facility' ,[CFAFEAwardCertification] 'cFAFEAwardCertification' ,[toggle] 'toggle' FROM [dbo].[CustomerFirstGraph] where ParentTerritory IN (?0) AND toggle = ?1";
	public static final String SELECT_BY_CHILD_AND_TOGGLE = "SELECT [ParentTerritory] 'parentTerritory' ,[ChildTerritory] 'childTerritory' ,[NoCertification] 'noCertification' ,[Performance] 'performance' ,[Process] 'process' ,[VoiceofEmployee] 'voiceofEmployee' ,[Training] 'training' ,[Facility] 'facility' ,[CFAFEAwardCertification] 'cFAFEAwardCertification' ,[toggle] 'toggle' FROM [dbo].[CustomerFirstGraph] where ChildTerritory IN (?0) AND toggle = ?1";
	public List<CustomerFirstDetailsDTO> getCustomerFirstByParentAndToggle(List<String> territory, String toggle);
	public List<CustomerFirstDetailsDTO> getCustomerFirstByChildAndToggle(List<String> territory, String toggle);
}
