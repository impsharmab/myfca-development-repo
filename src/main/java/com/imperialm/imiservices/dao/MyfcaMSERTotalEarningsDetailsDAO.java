package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.MyfcaMSERTotalEarningsDetailsDTO;
import com.imperialm.imiservices.model.response.TotalName;

public interface MyfcaMSERTotalEarningsDetailsDAO {

	public static String DEALERS_ENROLLED_COUNT = "select CAST(COUNT(DISTINCT [DealerCode]) as varchar(20)) 'total' , 'Total Dealers Enrolled' as name, '' as error from [MyfcaMSERTotalEarningsDetails] where [DealersEnrolled] > 0";
	public static String DEALERS_COUNT = "select CAST(COUNT(DISTINCT [DealerCode]) as varchar(20)) 'total' , 'Total Dealers Enrolled' as name, '' as error from [MyfcaMSERTotalEarningsDetails]";
	
	public static String EXCELLENCE_CARD_AWARDS_YTD_NAT = "SELECT CAST(SUM([EarningsYTD]) as varchar(20)) 'total', 'Excellence Card Awards YTD' as name, '' as error FROM [dbo].[MyfcaMSERTotalEarningsDetails]";
	public static String EXCELLENCE_CARD_AWARDS_MTD_NAT = "SELECT CAST(SUM([EarningsMTD]) as varchar(20)) 'total', 'Excellence Card Awards MTD' as name, '' as error FROM [dbo].[MyfcaMSERTotalEarningsDetails]";
	//Add group by
	public static String DEALERS_COUNT_WITH_PERCENTAGE = "select CONCAT(CAST(ISNULL(COUNT(ER.[DealersEnrolled]), '0') as varchar(20)), '(', CAST(ISNULL(COUNT(ER.[DealersEnrolled]) / NULLIF(COUNT(EM.[DealersEnrolled]), 0) *100, 0) as varchar(20)), '%)') 'total', 'Total Dealers Enrolled' as name, '' as error from [MyfcaMSERTotalEarningsDetails] ER, [MyfcaMSERTotalEarningsDetails] EM where ER.[DealersEnrolled] = 1";
	//do we need to check for dealers enrolled?
	public static String MTD_BY_PROGRAM_AND_PROGRAMGROUP = "select IsNull(CAST(SUM([EarningsMTD]) as varchar(20)),'0') 'total', ? as 'name', '' as error from [MyfcaMSERTotalEarningsDetails] where [program] LIKE ? AND [programgroup] LIKE ?";
	public static String YTD_BY_PROGRAM_AND_PROGRAMGROUP = "select IsNull(CAST(SUM([EarningsYTD]) as varchar(20)),'0') 'total', ? as 'name', '' as error from [MyfcaMSERTotalEarningsDetails] where [program] LIKE ? AND [programgroup] LIKE ?";
	
	public static String PARTICIPANT_ENROLLED_BY_DEALERCODE = "select CAST(ISNULL(COUNT( DISTINCT ER.[SID]), '0') as varchar(20)) 'total', 'Total Participants Enrolled' as name, '' as error from [MyfcaMSERTotalEarningsDetails] ER, [MyfcaMSERTotalEarningsDetails] EM where ER.[DealersEnrolled] = 1 AND ER.[DealerCode] = ?0";
	
	public static String MTD_EXCELLANCE_CARD_AWARD = "select IsNull(CAST(SUM([EarningsMTD]) as varchar(20)),'0') 'total', 'Excellence Card Awards MTD' as 'name', '' as error from [MyfcaMSERTotalEarningsDetails] where [SID] LIKE ?0";
	public static String YTD_EXCELLANCE_CARD_AWARD = "select IsNull(CAST(SUM([EarningsYTD]) as varchar(20)),'0') 'total', 'Excellence Card Awards YTD' as 'name', '' as error from [MyfcaMSERTotalEarningsDetails] where [SID] LIKE ?0";
	
	public static String SELECT_DEALERCOUNT_BY_BC_OR_DISTRICT = "Select '0' as 'total', 'Total Dealers Enrolled' as 'name', ?0 as error";
	public static String SELECT_PARTICIPANT_ENROLLED_BY_DEALERCODE = "SELECT CAST(ISNULL(COUNT(DISTINCT SID),0) as varchar(20)) total , 'Total Participants Enrolled' name, '' as error FROM [dbo].[MyfcaMSERTotalEarningsDetails] where ParticipantsEnrolled > 0 AND DealerCode = ?0";
	public static String SELECT_TOTAL_EARNINGS_YTD_BY_BC_OR_DISTRICT = "Select IsNull(CAST(SUM([Amount]) as varchar(20)),'0') 'total', 'Excellence Card Awards YTD' as 'name', '' as error From (SELECT SUM([Amount]) Amount FROM [dbo].[MyfcaMSERTotalEarnings] where child = ?0 and toggle = 'ytd' group by Child) A";
	public static String SELECT_TOTAL_EARNINGS_MTD_BY_BC_OR_DISTRICT = "Select IsNull(CAST(SUM([Amount]) as varchar(20)),'0') 'total', 'Excellence Card Awards MTD' as 'name', '' as error From (SELECT SUM([Amount]) Amount FROM [dbo].[MyfcaMSERTotalEarnings] where child = ?0 and toggle = 'mtd' group by Child) A";
	
	public static String SELECT_BY_DEALER_CODE = "SELECT [DealerCode] 'dealerCode' ,[DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' ,[PositionCode] 'positionCode' ,[Program] 'program' ,[ProgramGroup]'programGroup' ,[EarningsMTD] 'earningsMTD' ,[EarningsYTD] 'earningsYTD' ,[DealersEnrolled] 'dealersEnrolled' ,[ParticipantsEnrolled] 'participantsEnrolled', '' as error FROM [dbo].[MyfcaMSERTotalEarningsDetails] where DealerCode = ?0";
	public List<MyfcaMSERTotalEarningsDetailsDTO> getMSERGraphDetailsByDealerCode(String dealerCode);
	
	
	public TotalName getMSERParticipantEnrolledByDealerCode(String dealerCode);
	
	public TotalName getMSEREarningsYTDByBCOrDistrict(String territory);
	public TotalName getMSEREarningsMTDByBCOrDistrict(String territory);
	//excellance card award mtd, for dealer and participand and district and cs// get by child
	public TotalName getMSERDealersCount();
	public TotalName getMSERDealersCountByBCOrDistrict(String territory);
	public TotalName getMSEREnrolledDealersCount();
	public TotalName getExcellanceCardAwardYTDNAT();
	public TotalName getExcellanceCardAwardMTDNAT();
	public TotalName getParticipantExcellanceCardAwardMTD(String sid);
	public TotalName getParticipantExcellanceCardAwardYTD(String sid);
	public TotalName getDealersCountWithPercentage();
	public TotalName getMTDByProgramAndProgramgroup(String name, String program, String programgroup);
	public TotalName getYTDByProgramAndProgramgroup(String name, String program, String programgroup);
}
