package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.TTTAEnrollmentsSummaryDTO;
import com.imperialm.imiservices.model.response.TotalName;

import java.util.List;

public interface TTTAEnrollmentsSummaryDAO {
	
	public static final String BE = "SELECT [Parent] 'parentTerritory',[Child] 'childTerritory' ,[TotalEnrollments] 'totalEnrollments' ,[PositionCode] 'positionCode' ,[IncentiveEligible] 'incentiveEligible' ,[AvgSurveyScore] 'avgSurveyScore' ,[SurveyCount] 'surveyCount' ,[Level3Techs] 'level3Techs' ,ISNULL([TTTARank],0) 'tTTARank' ,[YearsOfService] 'yearsOfService', [PercentEnrolled] 'percentEnrolled' FROM [TTTAEnrollmentsSummary] where [Parent] IN ('CA', 'SE', 'NE', 'WE', 'GL', 'MA', 'SW', 'MW', 'DN') order by [Parent], [Child]";
	public static final String SELECT_BY_CHILD_TERRITORY_AND_POSITIONCODE = "SELECT [Parent] 'parentTerritory',[Child] 'childTerritory' ,[TotalEnrollments] 'totalEnrollments' ,[PositionCode] 'positionCode' ,[IncentiveEligible] 'incentiveEligible' ,[AvgSurveyScore] 'avgSurveyScore' ,[SurveyCount] 'surveyCount' ,[Level3Techs] 'level3Techs' ,ISNULL([TTTARank],0) 'tTTARank' ,[YearsOfService] 'yearsOfService', [PercentEnrolled] 'percentEnrolled' FROM [TTTAEnrollmentsSummary] where [Child] LIKE ?0 AND [PositionCode] = ?1 order by [Parent], [Child]";
	public static final String SELECT_BY_PARENT_TERRITORY_AND_POSITIONCODE = "SELECT [Parent] 'parentTerritory',[Child] 'childTerritory' ,[TotalEnrollments] 'totalEnrollments' ,[PositionCode] 'positionCode' ,[IncentiveEligible] 'incentiveEligible' ,[AvgSurveyScore] 'avgSurveyScore' ,[SurveyCount] 'surveyCount' ,[Level3Techs] 'level3Techs' ,ISNULL([TTTARank],0) 'tTTARank' ,[YearsOfService] 'yearsOfService', [PercentEnrolled] 'percentEnrolled' FROM [TTTAEnrollmentsSummary] where [Parent] LIKE ?0 AND [PositionCode] = ?1 order by [Parent], [Child]";
	public static final String SELECT_SUM_BY_PARENT_TERRITORY_AND_POSITIONCODE = "SELECT [Parent] 'parentTerritory', '' as 'childTerritory' , SUM([TotalEnrollments]) 'totalEnrollments' ,[PositionCode] 'positionCode' , SUM([IncentiveEligible]) 'incentiveEligible' , avg([AvgSurveyScore]) 'avgSurveyScore' , SUM([SurveyCount]) 'surveyCount' , SUM([Level3Techs]) 'level3Techs' ,ISNULL(SUM([TTTARank]),0) 'tTTARank' , avg([YearsOfService]) 'yearsOfService', AVG([PercentEnrolled]) 'percentEnrolled' FROM [TTTAEnrollmentsSummary] where [Parent] LIKE ?0 AND [PositionCode] = ?1 group by [Parent], [PositionCode]";
	public static final String SELECT_NAT_TOP_TECH_ENROLLED_DEALERCOUNT = "SELECT CAST(COUNT(distinct [DealerCode]) as varchar(20)) 'total', 'Total Dealers Enrolled' as 'name', '' as error FROM [TTTAEnrollments] where Enrollment = 'E'";
	public static final String SELECT_NAT_TOP_ADVISOR_ENROLLED_DEALERCOUNT = "SELECT CAST(COUNT(distinct [DealerCode]) as varchar(20)) 'total', 'Total Dealers Enrolled' as 'name', '' as error FROM [TTTAEnrollments] where Enrollment = 'E'";
	public static final String SELECT_NAT_AVERAGE_SURVEY_SCORE = "Select ((CAST(Sum(ScoreQualified) as decimal(8,2))/ (CAST(Sum(TotalQualified) as decimal(8,2))))*100 ) 'Avg.SurveyScore'  FROM TTTAEnrollmentsSummary Where PositionCode=?0 and parent = 'nat'";
	public static final String SELECT_NAT_TOP_ADVISOR_IncentiveEligible = "SELECT CAST(SUM([IncentiveEligible]) as varchar(20)) 'total', 'Total Advisors Incentive Eligible' as 'name', '' as error FROM [TTTAEnrollmentsSummary] where PositionCode = '13' and [Parent] = 'NAT' group by [Parent]";
	public static final String SELECT_NAT_TOP_TECH_IncentiveEligible = "SELECT CAST(SUM([IncentiveEligible]) as varchar(20)) 'total', 'Total Technicians Incentive Eligible' as 'name', '' as error FROM [TTTAEnrollmentsSummary] where PositionCode = '23' and [Parent] = 'NAT' group by [Parent]";
	
	//take the size
	//public static String SELECT_NUMBER_OF_DEALERS_ENROLLED_BY_BC_DISTRICT_AND_POSITIONCODE = "SELECT [Child] FROM [TTTAEnrollmentsSummary] INNER JOIN [TTTAEnrollments] on [Child] = DealerCode where [Parent] like ?0 and [TTTAEnrollments].PositionCode = ?1 and Enrollment = 'E' group by Child";
	
	public static String SELECT_NUMBER_OF_DEALERS_ENROLLED_BY_BC_DISTRICT = "Select IsNull(CAST(COUNT([Child]) as varchar(20)),'0') 'total', 'Total Dealers Enrolled' as 'name', '' as error From (SELECT [Child] FROM [TTTAEnrollmentsSummary] INNER JOIN [TTTAEnrollments] on [Child] = DealerCode where [Parent] like ?0 and Enrollment = 'E' group by Child) A";
	
	public List<TTTAEnrollmentsSummaryDTO> getTTTAEnrollmentsSummaryByChildAndPositionCode(String territories, String positionCode);
	public List<TTTAEnrollmentsSummaryDTO> getTTTAEnrollmentsSummaryByParentAndPositionCode(List<String> territories, String positionCode);
	public List<TTTAEnrollmentsSummaryDTO> getTTTAEnrollmentsSummarySUMByParentAndPositionCode(List<String> territories, String positionCode);
	public TotalName getTTTANATTopTechEnrolledDealerCount();
	public List<Double> getTTTANATAverageSurveyScoreByPositionCode(String positionCode);
	public TotalName getTTTANATTopAdvisorEnrolledDealerCount();
	public TotalName getTTTANATTopTechEnrolledIncentiveEligible();
	public TotalName getTTTANATTopAdvisorEnrolledIncentiveEligible();
	public TotalName getTTTANATTopEnrolledDealerCountByBCDistrictAndPositionCode(String territory);
	
	
}
