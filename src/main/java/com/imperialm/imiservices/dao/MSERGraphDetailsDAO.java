package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.model.response.TotalName;

public interface MSERGraphDetailsDAO {

	public static String DEALERS_COUNT = "select CAST(COUNT([DealersEnrolled]) as varchar(20)) 'total' , 'Total Dealers Enrolled' as name, '' as error from [MSERGraphDetails] where [DealersEnrolled] = 1";
	public static String DEALERS_COUNT_WITH_PERCENTAGE = "select CONCAT(CAST(ISNULL(COUNT(ER.[DealersEnrolled]), 0) as varchar(20)), '(', CAST(ISNULL(COUNT(ER.[DealersEnrolled]) / NULLIF(COUNT(EM.[DealersEnrolled]), 0) *100, 0) as varchar(20)), '%)') 'total', 'Total Dealers Enrolled' as name, '' as error from [MSERGraphDetails] ER, [MSERGraphDetails] EM where ER.[DealersEnrolled] = 1";
	//do we need to check for dealers enrolled?
	public static String MTD_BY_PROGRAM_AND_PROGRAMGROUP = "select CONCAT('$', IsNull(CAST(SUM([EarningsMTD]) as varchar(20)),'0')) 'total', ? as 'name', '' as error from [MSERGraphDetails] where [program] LIKE ? AND [programgroup] LIKE ?";
	public static String YTD_BY_PROGRAM_AND_PROGRAMGROUP = "select CONCAT('$', IsNull(CAST(SUM([EarningsYTD]) as varchar(20)),'0')) 'total', ? as 'name', '' as error from [MSERGraphDetails] where [program] LIKE ? AND [programgroup] LIKE ?";
	
	public TotalName getDealersCount();
	public TotalName getDealersCountWithPercentage();
	public TotalName getMTDByProgramAndProgramgroup(String name, String program, String programgroup);
	public TotalName getYTDByProgramAndProgramgroup(String name, String program, String programgroup);
}
