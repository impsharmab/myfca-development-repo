package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.MSERTopNDTO;
import com.imperialm.imiservices.dto.TTTATopNDTO;
import com.imperialm.imiservices.dto.request.InputRequest;

public interface TTTATopNDAO {
	public static String TOP_N_BY_ROLE = "select TOP 5 [TopNType] 'topNType', [Parent] 'parentTerritory', [DealerCode] 'dealerCode', [DealerName] 'dealerName', [SID] 'sID', [Name] 'name', [TotalSurveys] 'totalSurveys', [AvgSurveyScore] 'avgSurveyScore', '' as error from [TTTATopN] ORDER BY [TopNRank]";
	public static String TOP_N_BY_TYPE = "select TOP (?) [TopNType] 'topNType', [Parent] 'parentTerritory', [DealerCode] 'dealerCode', [DealerName] 'dealerName', [SID] 'sID', [Name] 'name', [TotalSurveys] 'totalSurveys', [AvgSurveyScore] 'avgSurveyScore', '' as error from [TTTATopN] WHERE [TopNType] LIKE ? ORDER BY [TopNRank]";

		public List<TTTATopNDTO> getTopNByRole(InputRequest userRoleReq);
		public List<TTTATopNDTO> getTopNByType(String type, int rows);
}
