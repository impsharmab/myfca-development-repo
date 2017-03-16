package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.SIRewardsDetailsDTO;

public interface SIRewardsDetailsDAO {
	public static String SELECT_BY_DEALER_CODE = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' ,[Toggle] 'toggle' ,[EligibleSurveys] 'eligibleSurveys' ,[IncentiveQualified] 'incentiveQualified' ,[SurveyScore] 'surveyScore' ,[ProjectedEarnings] 'projectedEarnings' ,[level0] 'level0' ,[level1] 'level1' ,[TotalSurveys] 'totalSurveys' ,[BCAdvisorRankEarnings] 'bCAdvisorRankEarnings' FROM [mser2].[dbo].[SIRewardsDetails] where DealerCode = ?0";
	public static String SELECT_BY_DEALER_CODE_AND_TOGGLE = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' ,[Toggle] 'toggle' ,[EligibleSurveys] 'eligibleSurveys' ,[IncentiveQualified] 'incentiveQualified' ,[SurveyScore] 'surveyScore' ,[ProjectedEarnings] 'projectedEarnings' ,[level0] 'level0' ,[level1] 'level1' ,[TotalSurveys] 'totalSurveys' ,[BCAdvisorRankEarnings] 'bCAdvisorRankEarnings' FROM [mser2].[dbo].[SIRewardsDetails] where DealerCode = ?0 AND [Toggle] = ?1";
	public static String SELECT_BY_SID = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' ,[Toggle] 'toggle' ,[EligibleSurveys] 'eligibleSurveys' ,[IncentiveQualified] 'incentiveQualified' ,[SurveyScore] 'surveyScore' ,[ProjectedEarnings] 'projectedEarnings' ,[level0] 'level0' ,[level1] 'level1' ,[TotalSurveys] 'totalSurveys' ,[BCAdvisorRankEarnings] 'bCAdvisorRankEarnings' FROM [mser2].[dbo].[SIRewardsDetails] where [SID] = ?0";
	public static String SELECT_BY_SID_AND_TOGGLE = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' ,[Toggle] 'toggle' ,[EligibleSurveys] 'eligibleSurveys' ,[IncentiveQualified] 'incentiveQualified' ,[SurveyScore] 'surveyScore' ,[ProjectedEarnings] 'projectedEarnings' ,[level0] 'level0' ,[level1] 'level1' ,[TotalSurveys] 'totalSurveys' ,[BCAdvisorRankEarnings] 'bCAdvisorRankEarnings' FROM [mser2].[dbo].[SIRewardsDetails] where [SID] = ?0 AND [Toggle] = ?1";
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsByDealerCode(String dealerCode);
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsBySID(String sID);
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsByDealerCodeAndToggle(String dealerCode, String toggle);
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsBySIDAndToggle(String sID, String toggle);
	
}
