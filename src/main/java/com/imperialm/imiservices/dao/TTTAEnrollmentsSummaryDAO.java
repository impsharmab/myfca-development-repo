package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.TTTAEnrollmentsSummaryDTO;
import com.imperialm.imiservices.model.response.TotalName;

public interface TTTAEnrollmentsSummaryDAO {
	
	public static final String BE = "SELECT [ParentTerritory] 'parentTerritory',[ChildTerritory] 'childTerritory' ,[TotalEnrollments] 'totalEnrollments' ,[PositionCode] 'positionCode' ,[IncentiveEligible] 'incentiveEligible' ,[AvgSurveyScore] 'avgSurveyScore' ,[SurveyCount] 'surveyCount' ,[Level3Techs] 'level3Techs' ,ISNULL([TTTARank],0) 'tTTARank' ,[YearsOfService] 'yearsOfService', [PercentEnrolled] 'percentEnrolled' FROM [TTTAEnrollmentsSummary] where [ParentTerritory] IN ('CA', 'SE', 'NE', 'WE', 'GL', 'MA', 'SW', 'MW', 'DN')";
	public static final String SELECT_BY_CHILD_TERRITORY_AND_POSITIONCODE = "SELECT [ParentTerritory] 'parentTerritory',[ChildTerritory] 'childTerritory' ,[TotalEnrollments] 'totalEnrollments' ,[PositionCode] 'positionCode' ,[IncentiveEligible] 'incentiveEligible' ,[AvgSurveyScore] 'avgSurveyScore' ,[SurveyCount] 'surveyCount' ,[Level3Techs] 'level3Techs' ,ISNULL([TTTARank],0) 'tTTARank' ,[YearsOfService] 'yearsOfService', [PercentEnrolled] 'percentEnrolled' FROM [TTTAEnrollmentsSummary] where [ChildTerritory] LIKE ?0 AND [PositionCode] = ?1";
	public static final String SELECT_BY_PARENT_TERRITORY_AND_POSITIONCODE = "SELECT [ParentTerritory] 'parentTerritory',[ChildTerritory] 'childTerritory' ,[TotalEnrollments] 'totalEnrollments' ,[PositionCode] 'positionCode' ,[IncentiveEligible] 'incentiveEligible' ,[AvgSurveyScore] 'avgSurveyScore' ,[SurveyCount] 'surveyCount' ,[Level3Techs] 'level3Techs' ,ISNULL([TTTARank],0) 'tTTARank' ,[YearsOfService] 'yearsOfService', [PercentEnrolled] 'percentEnrolled' FROM [TTTAEnrollmentsSummary] where [ParentTerritory] LIKE ?0 AND [PositionCode] = ?1";
	public static final String SELECT_SUM_BY_PARENT_TERRITORY_AND_POSITIONCODE = "SELECT [ParentTerritory] 'parentTerritory', '' as 'childTerritory' , SUM([TotalEnrollments]) 'totalEnrollments' ,[PositionCode] 'positionCode' , SUM([IncentiveEligible]) 'incentiveEligible' , avg([AvgSurveyScore]) 'avgSurveyScore' , SUM([SurveyCount]) 'surveyCount' , SUM([Level3Techs]) 'level3Techs' ,ISNULL(SUM([TTTARank]),0) 'tTTARank' , avg([YearsOfService]) 'yearsOfService', AVG([PercentEnrolled]) 'percentEnrolled' FROM [TTTAEnrollmentsSummary] where [ParentTerritory] LIKE ?0 AND [PositionCode] = ?1 group by [ParentTerritory], [PositionCode]";
	public static final String SELECT_NAT_TOP_TECH_ENROLLED_DEALERCOUNT = "SELECT CAST(COUNT(distinct [DealerCode]) as varchar(20)) 'total', 'Total Dealers Enrolled' as 'name', '' as error FROM [dbo].[TTTAEnrollments] where Enrollment = 'E' AND PositionCode = '23'";
	public static final String SELECT_NAT_TOP_ADVISOR_ENROLLED_DEALERCOUNT = "SELECT CAST(COUNT(distinct [DealerCode]) as varchar(20)) 'total', 'Total Dealers Enrolled' as 'name', '' as error FROM [dbo].[TTTAEnrollments] where Enrollment = 'E' AND PositionCode = '13'";
	
	public static final String SELECT_NAT_TOP_ADVISOR_IncentiveEligible = "SELECT CAST(SUM([IncentiveEligible]) as varchar(20)) 'total', 'Total Advisors Incentive Eligible' as 'name', '' as error FROM [dbo].[TTTAEnrollmentsSummary] where PositionCode = '13' and [ParentTerritory] = 'NAT' group by [ParentTerritory]";
	public static final String SELECT_NAT_TOP_TECH_IncentiveEligible = "SELECT CAST(SUM([IncentiveEligible]) as varchar(20)) 'total', 'Total Technicians Incentive Eligible' as 'name', '' as error FROM [dbo].[TTTAEnrollmentsSummary] where PositionCode = '23' and [ParentTerritory] = 'NAT' group by [ParentTerritory]";
	
	//take the size
	//public static String SELECT_NUMBER_OF_DEALERS_ENROLLED_BY_BC_DISTRICT_AND_POSITIONCODE = "SELECT [ChildTerritory] FROM [dbo].[TTTAEnrollmentsSummary] INNER JOIN [dbo].[TTTAEnrollments] on [ChildTerritory] = DealerCode where [ParentTerritory] like ?0 and [TTTAEnrollments].PositionCode = ?1 and Enrollment = 'E' group by ChildTerritory";
	
	public static String SELECT_NUMBER_OF_DEALERS_ENROLLED_BY_BC_DISTRICT_AND_POSITIONCODE = "Select IsNull(CAST(COUNT([ChildTerritory]) as varchar(20)),'0') 'total', 'Total Dealers Enrolled' as 'name', '' as error From (SELECT [ChildTerritory] FROM [dbo].[TTTAEnrollmentsSummary] INNER JOIN [dbo].[TTTAEnrollments] on [ChildTerritory] = DealerCode where [ParentTerritory] like ?0 and [TTTAEnrollments].PositionCode = ?1 and Enrollment = 'E' group by ChildTerritory) A";
	
	public List<TTTAEnrollmentsSummaryDTO> getTTTAEnrollmentsSummaryByChildAndPositionCode(List<String> territories, String positionCode);
	public List<TTTAEnrollmentsSummaryDTO> getTTTAEnrollmentsSummaryByParentAndPositionCode(List<String> territories, String positionCode);
	public List<TTTAEnrollmentsSummaryDTO> getTTTAEnrollmentsSummarySUMByParentAndPositionCode(List<String> territories, String positionCode);
	public TotalName getTTTANATTopTechEnrolledDealerCount();
	public TotalName getTTTANATTopAdvisorEnrolledDealerCount();
	public TotalName getTTTANATTopTechEnrolledIncentiveEligible();
	public TotalName getTTTANATTopAdvisorEnrolledIncentiveEligible();
	public TotalName getTTTANATTopEnrolledDealerCountByBCDistrictAndPositionCode(String territory, String positionCode);
	
	
}
