package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.model.response.TotalName;

public interface MSERGraphDetailsDAO {

	public static String DEALERS_ENROLLED_COUNT = "select CAST(COUNT(DISTINCT [DealerCode]) as varchar(20)) 'total' , 'Total Dealers Enrolled' as name, '' as error from [MSERGraphDetails] where [DealersEnrolled] > 0";
	public static String DEALERS_COUNT = "select CAST(COUNT(DISTINCT [DealerCode]) as varchar(20)) 'total' , 'Total Dealers Enrolled' as name, '' as error from [MSERGraphDetails]";
	
	public static String EXCELLENCE_CARD_AWARDS_YTD_NAT = "SELECT CAST(SUM([EarningsYTD]) as varchar(20)) 'total', 'Excellence Card Awards YTD' as name, '' as error FROM [dbo].[MSERGraphDetails]";
	public static String EXCELLENCE_CARD_AWARDS_MTD_NAT = "SELECT CAST(SUM([EarningsMTD]) as varchar(20)) 'total', 'Excellence Card Awards MTD' as name, '' as error FROM [dbo].[MSERGraphDetails]";
	//Add group by
	public static String DEALERS_COUNT_WITH_PERCENTAGE = "select CONCAT(CAST(ISNULL(COUNT(ER.[DealersEnrolled]), 0) as varchar(20)), '(', CAST(ISNULL(COUNT(ER.[DealersEnrolled]) / NULLIF(COUNT(EM.[DealersEnrolled]), 0) *100, 0) as varchar(20)), '%)') 'total', 'Total Dealers Enrolled' as name, '' as error from [MSERGraphDetails] ER, [MSERGraphDetails] EM where ER.[DealersEnrolled] = 1";
	//do we need to check for dealers enrolled?
	public static String MTD_BY_PROGRAM_AND_PROGRAMGROUP = "select CONCAT('$', IsNull(CAST(SUM([EarningsMTD]) as varchar(20)),'0')) 'total', ? as 'name', '' as error from [MSERGraphDetails] where [program] LIKE ? AND [programgroup] LIKE ?";
	public static String YTD_BY_PROGRAM_AND_PROGRAMGROUP = "select CONCAT('$', IsNull(CAST(SUM([EarningsYTD]) as varchar(20)),'0')) 'total', ? as 'name', '' as error from [MSERGraphDetails] where [program] LIKE ? AND [programgroup] LIKE ?";
	
	public static String PARTICIPANT_ENROLLED_BY_DEALERCODE = "select CAST(ISNULL(COUNT(ER.[DealersEnrolled]), 0) as varchar(20)) 'total', 'Total Participants Enrolled' as name, '' as error from [MSERGraphDetails] ER, [MSERGraphDetails] EM where ER.[DealersEnrolled] = 1 AND [DealerCode] =?0";
	
	public static String MTD_EXCELLANCE_CARD_AWARD = "select IsNull(CAST(SUM([EarningsMTD]) as varchar(20)),'0') 'total', ? as 'name', '' as error from [MSERGraphDetails] where [SID] LIKE ?0";
	public static String YTD_EXCELLANCE_CARD_AWARD = "select IsNull(CAST(SUM([EarningsYTD]) as varchar(20)),'0') 'total', ? as 'name', '' as error from [MSERGraphDetails] where [SID] LIKE ?0";
	
	
	public TotalName getMSERDealersCount();
	public TotalName getMSEREnrolledDealersCount();
	public TotalName getExcellanceCardAwardYTDNAT();
	public TotalName getExcellanceCardAwardMTDNAT();
	public TotalName getDealersCountWithPercentage();
	public TotalName getMTDByProgramAndProgramgroup(String name, String program, String programgroup);
	public TotalName getYTDByProgramAndProgramgroup(String name, String program, String programgroup);
}
