package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.SummaryProgramRewardQuartileGraphDTO;

import java.util.List;

public interface SummaryProgramRewardQuartileDetailsDAO {

	public static String SELECT_BY_SID_YTD = "SELECT '' as 'parent', [SID] 'child' ,'YTD' as toggle , [Earnings] 'earnings', [Quartile] 'quartile', ISNULL([TopQuartile],0) 'topQuartile' FROM [SummaryProgramRewardQuartileDetails] where toggle = 'ytd' and SID = ?0 AND DealerCode = ?1";
	
	public List<SummaryProgramRewardQuartileGraphDTO> getSummaryProgramRewardQuartileDetailsBySIDYTD(String sid, String dealerCode);
	
}
