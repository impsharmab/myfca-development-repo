package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.RewardRedemptionDetailsDTO;

public interface RewardRedemptionDetailsDAO {
	public static String GET_DETAILS_BY_DEALER = "SELECT [DealerCode] 'dealerCode', [DealerName] 'dealerName', [SID] 'sID', [Name] 'name', [EarnedPoints] 'earnedPoints', [RedeemedPoints] 'redeemedPoints', [BalancePoints] 'balancePoints','' as error FROM [mser2].[dbo].[RewardRedemptionDetails] where [DealerCode] = (?)";
	public static String GET_DETAILS_BY_SID = "SELECT [DealerCode] 'dealerCode', [DealerName] 'dealerName', [SID] 'sID', [Name] 'name', [EarnedPoints] 'earnedPoints', [RedeemedPoints] 'redeemedPoints', [BalancePoints] 'balancePoints','' as error FROM [mser2].[dbo].[RewardRedemptionDetails] where [SID] = (?)";
	
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsByDealer(String dealerCode);
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsBySid(String sid);
	
}
