package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.TTTATopNDTO;

public interface TTTATopNDAO {
	public static String TOP_N_BY_TYPE = "select TOP (?) [TopNType] 'topNType', [Parent] 'parentTerritory', [DealerCode] 'dealerCode', [DealerName] 'dealerName', [SID] 'sID', [Name] 'name', [TotalSurveys] 'totalSurveys', [AvgSurveyScore] 'avgSurveyScore', '' as error from [TTTATopN] WHERE [TopNType] LIKE ? ORDER BY [TopNRank],[TotalSurveys], [SID]";

		public List<TTTATopNDTO> getTopNByType(String type, int rows);
}
