package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.RetentionDetailsDTO;

import java.util.List;

public interface RetentionDetailsDAO {

	public static String GET_RETENTION_DETAILS_BY_DEALER = "SELECT [DealerCode] 'dealerCode', [DealerName] 'dealerName', [PositionCode] 'positionCode', [Percentage] 'percentage' FROM [RetentionDetails] where [DealerCode] = ?0";
	
	public List<RetentionDetailsDTO> getRetentionDetails(String dealersCode);
}
