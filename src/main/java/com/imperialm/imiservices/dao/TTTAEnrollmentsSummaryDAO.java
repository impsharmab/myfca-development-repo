package com.imperialm.imiservices.dao;

public interface TTTAEnrollmentsSummaryDAO {
	
	public static final String BE = "SELECT [ParentTerritory] 'parentTerritory',[ChildTerritory] 'childTerritory' ,[TotalEnrollments] 'totalEnrollments' ,[PositionCode] '[positionCode]' ,[IncentiveEligible] 'incentiveEligible' ,[AvgSurveyScore] 'avgSurveyScore' ,[SurveyCount] 'surveyCount' ,[Level3Techs] 'level3Techs' ,[TTTARank] 'tTTARank' ,[YearsOfService] 'yearsOfService', [PercentEnrolled] 'percentEnrolled', '' as error FROM [TTTAEnrollmentsSummary] where [ParentTerritory] IN ('CA', 'SE', 'NE', 'WE', 'GL', 'MA', 'SW', 'MW', 'DN')";

}
