package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.SummaryProgramRewardGraphDTO;

public interface SummaryProgramRewardDetailsDAO {

	public static String SELECT_BY_SID_YTD = "SELECT '' as 'parent', [SID] 'child' ,'YTD' as toggle , ''  as 'program' ,SUM([Earnings]) 'earnings' FROM [dbo].[SummaryProgramRewardDetails] where toggle = 'ytd' and SID = ?0 group by SID";
	
	public List<SummaryProgramRewardGraphDTO> getSummaryProgramRewardDetailsBySIDYTD(String territory);
}
