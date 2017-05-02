package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.SummaryProgramRewardDetailsDTO;
import com.imperialm.imiservices.dto.SummaryProgramRewardGraphDTO;

public interface SummaryProgramRewardDetailsDAO {

	public static String SELECT_BY_SID_YTD = "SELECT '' as 'parent', [SID] 'child' ,'YTD' as toggle , ''  as 'program' ,SUM([Earnings]) 'earnings' FROM [dbo].[SummaryProgramRewardDetails] where toggle = 'ytd' and SID = ?0 AND DealerCode = ?1 group by SID, DealerCode";
	public static String SELECT_BY_DEALERCODE_YTD = "SELECT [DealerCode] 'dealerCode', [DealerName] 'dealerName' ,[SID] 'sID' ,[Name] 'name' ,'' 'toggle' ,'' 'program' ,SUM([Earnings]) 'earnings' FROM [dbo].[SummaryProgramRewardDetails] where DealerCode = ?0 and toggle = 'ytd' group by SID, DealerCode, Name, DealerName";
	
	public List<SummaryProgramRewardGraphDTO> getSummaryProgramRewardDetailsBySIDYTD(String territory, String dealerCode);
	public List<SummaryProgramRewardDetailsDTO> getSummaryProgramRewardDetailsByDealerCodeYTD(String dealerCode);
}
