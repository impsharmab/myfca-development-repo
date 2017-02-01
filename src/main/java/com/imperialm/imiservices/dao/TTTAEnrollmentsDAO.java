package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.model.response.TotalName;

public interface TTTAEnrollmentsDAO {
	
	public static String ENROLLMENT_COUNT = "select CAST(COUNT([Enrollment]) as varchar(20)) 'total' , 'Total Dealers Enrolled' as name, '' as error from [TTTAEnrollments] WHERE [Enrollment] = 'E'";
	public static String INCENTIVE_ELIGIBLE_SUM = "select CAST(SUM([IncentiveEligible]) as varchar(20)) 'total' , 'Incentive Eligible Advisors' as name, '' as error from [TTTAEnrollments] WHERE [IncentiveEligible] != 0";
	public static String AVERAGE_SCORE_ADVISOR = "select CAST(AVG([AvgSurveyScore]) as varchar(20)) 'total' , 'Average Quarterly Survey Score' as name, '' as error from [TTTAEnrollments] WHERE [AvgSurveyScore] !=0 AND [PositionCode] = 13";
	public static String AVERAGE_SCORE_TECHNICIAN = "select CAST(AVG([AvgSurveyScore]) as varchar(20)) 'total' , 'Average Quarterly Survey Score' as name, '' as error from [TTTAEnrollments] WHERE [AvgSurveyScore] !=0 AND [PositionCode] = 23";
	public static String COUNT_OF_SURVEY_ADVISOR = "select CAST(SUM([SurveyCount]) as varchar(20)) 'total' , 'Count of Surveys QTD' as name, '' as error from [TTTAEnrollments] WHERE [PositionCode] = 13";
	public static String COUNT_OF_SURVEY_TECHNICIAN = "select CAST(SUM([SurveyCount]) as varchar(20)) 'total' , 'Count of Surveys QTD' as name, '' as error from [TTTAEnrollments] WHERE [PositionCode] = 23";
	
	public TotalName getTTTAEnrollmentCount();
	public TotalName getTTTAIncentiveEligibleSUM();
	public TotalName getTTTAAdvisorScoreAVG();
	public TotalName getTTTATechnicianScoreAVG();
	public TotalName getTTTATechnicianSurveyCount();
	public TotalName getTTTAAdvisorSurveyCount();
}
