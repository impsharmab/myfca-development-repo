package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.RetentionDetailsDTO;

public interface RetentionDetailsDAO {

	public static String GET_RETENTION_DETAILS_BY_DEALER = "SELECT [DealerCode] 'dealerCode', [DealerName] 'dealerName', [PositionCode] 'positionCode', [Percentage] 'percentage' FROM [mser2].[dbo].[RetentionDetails] where [DealerCode] = (?)";
	
	public List<RetentionDetailsDTO> getRetentionDetails(int dealersCode);
}
