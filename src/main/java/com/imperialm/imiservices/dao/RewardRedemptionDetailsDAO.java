package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.RewardRedemptionDetailsDTO;

public interface RewardRedemptionDetailsDAO {
	public static String GET_DETAILS_BY_DEALER = "SELECT [DealerCode] 'dealerCode', [DealerName] 'dealerName', [SID] 'sID', [Name] 'name', SUM([EarnedPoints]) 'earnedPoints', SUM([RedeemedPoints]) 'redeemedPoints', SUM([BalancePoints]) 'balancePoints','' as error FROM [dbo].[RewardRedemptionDetails] where [DealerCode] = ?0 group by DealerCode, DealerName, SID, Name";
	//public static String GET_DETAILS_BY_SID = "SELECT [DealerCode] 'dealerCode', [DealerName] 'dealerName', [SID] 'sID', [Name] 'name', [EarnedPoints] 'earnedPoints', [RedeemedPoints] 'redeemedPoints', [BalancePoints] 'balancePoints','' as error FROM [dbo].[RewardRedemptionDetails] where [SID] = (?)";
	public static String GET_DETAILS_BY_SID = "SELECT '' as 'dealerCode', '' as 'dealerName', [SID] 'sID', '' as 'name', SUM([EarnedPoints]) 'earnedPoints', SUM([RedeemedPoints]) 'redeemedPoints', SUM([BalancePoints]) 'balancePoints','' as error FROM [dbo].[RewardRedemptionDetails] where [SID] = ?0 group by [SID]";
	public static String GET_DETAILS_BY_SID_AND_DEALERCODE = "SELECT  [DealerCode] 'dealerCode', '' as 'dealerName', [SID] 'sID', '' as 'name', SUM([EarnedPoints]) 'earnedPoints', SUM([RedeemedPoints]) 'redeemedPoints', SUM([BalancePoints]) 'balancePoints','' as error FROM [dbo].[RewardRedemptionDetails] where [SID] = ?0 and DealerCode = ?1 group by dealerCode, [SID]";
	
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsByDealer(String dealerCode);
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsBySid(String sid);
	
}
