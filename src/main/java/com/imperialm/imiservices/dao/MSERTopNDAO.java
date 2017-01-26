package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.MSERTopNDTO;
import com.imperialm.imiservices.dto.request.InputRequest;

public interface MSERTopNDAO {
public static String TOP_N_BY_ROLE = "select TOP 5 [TopNType] 'topNType', [ParentTerritory] 'parentTerritory', [DealerCode] 'dealerCode', [DealerName] 'dealerName', [SID] 'sID', [Name] 'name', [Earnings] 'earnings', [TopNRank] 'topNRank', '' as error from [MSERTopN] ORDER BY [TopNRank]";
public static String TOP_N_BY_TYPE = "select TOP (?) [TopNType] 'topNType', [ParentTerritory] 'parentTerritory', [DealerCode] 'dealerCode', [DealerName] 'dealerName', [SID] 'sID', [Name] 'name', [Earnings] 'earnings', [TopNRank] 'topNRank', '' as error from [MSERTopN] WHERE [TopNType] LIKE ? ORDER BY [TopNRank]";

	public List<MSERTopNDTO> getTopNByRole(InputRequest userRoleReq);
	public List<MSERTopNDTO> getTopNByType(String type, int rows);

}
