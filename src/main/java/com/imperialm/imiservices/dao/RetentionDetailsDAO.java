package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.RetentionDetailsDTO;

public interface RetentionDetailsDAO {

	public static String GET_RETENTION_DETAILS_BY_DEALER = "SELECT [DealerCode] 'dealerCode', [DealerName] 'dealerName', [PositionCode] 'positionCode', [Percentage] 'percentage' FROM [dbo].[RetentionDetails] where [DealerCode] = ?0";
	
	public List<RetentionDetailsDTO> getRetentionDetails(String dealersCode);
}
