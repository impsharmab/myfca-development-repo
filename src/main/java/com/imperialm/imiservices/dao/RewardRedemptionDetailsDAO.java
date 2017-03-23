package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.RewardRedemptionDetailsDTO;

public interface RewardRedemptionDetailsDAO {
	public static String GET_DETAILS_BY_DEALER = "SELECT [DealerCode] 'dealerCode', [DealerName] 'dealerName', [SID] 'sID', [Name] 'name', [EarnedPoints] 'earnedPoints', [RedeemedPoints] 'redeemedPoints', [BalancePoints] 'balancePoints','' as error FROM [dbo].[RewardRedemptionDetails] where [DealerCode] = (?)";
	//public static String GET_DETAILS_BY_SID = "SELECT [DealerCode] 'dealerCode', [DealerName] 'dealerName', [SID] 'sID', [Name] 'name', [EarnedPoints] 'earnedPoints', [RedeemedPoints] 'redeemedPoints', [BalancePoints] 'balancePoints','' as error FROM [dbo].[RewardRedemptionDetails] where [SID] = (?)";
	public static String GET_DETAILS_BY_SID = "SELECT '' as 'dealerCode', '' as 'dealerName', [SID] 'sID', '' as 'name', SUM([EarnedPoints]) 'earnedPoints', SUM([RedeemedPoints]) 'redeemedPoints', SUM([BalancePoints]) 'balancePoints','' as error FROM [dbo].[RewardRedemptionDetails] where [SID] = 'S07001B' group by [SID]";
	
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsByDealer(String dealerCode);
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsBySid(String sid);
	
}
