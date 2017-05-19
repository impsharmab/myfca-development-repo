package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.TTTAEnrollmentsDTO;
import com.imperialm.imiservices.model.response.TotalName;

public interface TTTAEnrollmentsDAO {
	
	public static String ENROLLMENT_COUNT = "select CAST(COUNT([Enrollment]) as varchar(20)) 'total' , 'Total Dealers Enrolled' as name, '' as error from [TTTAEnrollments] WHERE [Enrollment] = 'E'";
	public static String INCENTIVE_ELIGIBLE_SUM = "select CAST(SUM([IncentiveEligible]) as varchar(20)) 'total' , 'Incentive Eligible Advisors' as name, '' as error from [TTTAEnrollments] WHERE [IncentiveEligible] != 0  AND [Enrollment]='E'";
	public static String AVERAGE_SCORE_ADVISOR = "select CAST(AVG([AvgSurveyScore]) as varchar(20)) 'total' , 'Average Quarterly Survey Score' as name, '' as error from [TTTAEnrollments] WHERE [AvgSurveyScore] !=0 AND [PositionCode] = 13  AND [Enrollment]='E'";
	public static String AVERAGE_SCORE_TECHNICIAN = "select CAST(AVG([AvgSurveyScore]) as varchar(20)) 'total' , 'Average Quarterly Survey Score' as name, '' as error from [TTTAEnrollments] WHERE [AvgSurveyScore] !=0 AND [PositionCode] = 23  AND [Enrollment]='E'";
	public static String COUNT_OF_SURVEY_ADVISOR = "select CAST(SUM([SurveyCount]) as varchar(20)) 'total' , 'Count of Surveys QTD' as name, '' as error from [TTTAEnrollments] WHERE [PositionCode] = 13  AND [Enrollment]='E'";
	public static String COUNT_OF_SURVEY_TECHNICIAN = "select CAST(SUM([SurveyCount]) as varchar(20)) 'total' , 'Count of Surveys QTD' as name, '' as error from [TTTAEnrollments] WHERE [PositionCode] = 23  AND [Enrollment]='E'";
	public static String SELECT_BY_SID = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' ,[Enrollment] 'enrollment' ,[PositionCode] 'positionCode' ,[IncentiveEligible] 'incentiveEligible', [AvgSurveyScore] 'avgSurveyScore' ,[SurveyCount] 'surveyCount', [Level3Techs] 'level3Techs', [PartcipantRank] 'partcipantRank' ,[YearsOfService] 'yearsOfService' FROM [TTTAEnrollments] where SID = ?0 and PositionCode = ?1 AND [Enrollment]='E' AND [DealerCode] = ?2";
	public static String SELECT_BY_DEALERCODE = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' ,[Enrollment] 'enrollment' ,[PositionCode] 'positionCode' ,[IncentiveEligible] 'incentiveEligible', [AvgSurveyScore] 'avgSurveyScore' ,[SurveyCount] 'surveyCount', [Level3Techs] 'level3Techs', [PartcipantRank] 'partcipantRank' ,[YearsOfService] 'yearsOfService' FROM [TTTAEnrollments] where DealerCode = ?0 and PositionCode = ?1";
	public static String SELECT_BY_DEALERCODE_AND_ENROLLEMENT= "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' ,[Enrollment] 'enrollment' ,[PositionCode] 'positionCode' ,[IncentiveEligible] 'incentiveEligible', [AvgSurveyScore] 'avgSurveyScore' ,[SurveyCount] 'surveyCount', [Level3Techs] 'level3Techs', [PartcipantRank] 'partcipantRank' ,[YearsOfService] 'yearsOfService' FROM [TTTAEnrollments] where DealerCode = ?0 and Enrollment = ?1 and PositionCode = ?2 ";
	public TotalName getTTTAEnrollmentCount();
	public TotalName getTTTAIncentiveEligibleSUM();
	public TotalName getTTTAAdvisorScoreAVG();
	public TotalName getTTTATechnicianScoreAVG();
	public TotalName getTTTATechnicianSurveyCount();
	public TotalName getTTTAAdvisorSurveyCount();
	public List<TTTAEnrollmentsDTO> getTTTAEnrollmentsBySID(String sid, String positionCode, String dealerCode);
	public List<TTTAEnrollmentsDTO> getTTTAEnrollmentsByDealerCode(String dealerCode, String positionCode);
	public List<TTTAEnrollmentsDTO> getTTTAEnrollmentsByDealerCodeAndEnrollement(String dealerCode, String enrollement, String positionCode);
}
