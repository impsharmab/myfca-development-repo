package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.MyfcaMSERTopNDTO;
import com.imperialm.imiservices.dto.request.InputRequest;

public interface MyfcaMSERTopNDAO {
public static String TOP_N_BY_ROLE = "select TOP 5 [TopNType] 'topNType', [Parent] 'parentTerritory', [DealerCode] 'dealerCode', [DealerName] 'dealerName', [SID] 'sID', [Name] 'name', [Earnings] 'earnings', [TopNRank] 'topNRank', [Toggle] 'toggle', ISNULL([Quantity],0) 'quantity', '' as error from [MyfcaMSERTopN] ORDER BY [TopNRank]";
public static String TOP_N_BY_TYPE = "select TOP (?) [TopNType] 'topNType', [Parent] 'parentTerritory', [DealerCode] 'dealerCode', [DealerName] 'dealerName', [SID] 'sID', [Name] 'name', [Earnings] 'earnings', [TopNRank] 'topNRank', [Toggle] 'toggle', ISNULL([Quantity],0) 'quantity', '' as error from [MyfcaMSERTopN] WHERE [TopNType] LIKE ? ORDER BY [TopNRank]";
public static String TOP_N_BY_TYPE_NAME_PERIOD = "select TOP (?) [TopNType] 'topNType', [Parent] 'parentTerritory', [DealerCode] 'dealerCode', [DealerName] 'dealerName', [SID] 'sID', [Name] 'name', [Earnings] 'earnings', [TopNRank] 'topNRank', [Toggle] 'toggle', ISNULL([Quantity],0) 'quantity', '' as error from [MyfcaMSERTopN] WHERE [TopNType] LIKE ? AND [DealerName] LIKE ? AND [Toggle] LIKE ? ORDER BY [TopNRank]";

	public List<MyfcaMSERTopNDTO> getTopNByRole(InputRequest userRoleReq);
	public List<MyfcaMSERTopNDTO> getTopNByType(String type, int rows, String name, String period);

}
