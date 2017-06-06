package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.RewardRedemptionDetailsDTO;

import java.util.List;

public interface RewardRedemptionDetailsDAO {
	public static String GET_DETAILS_BY_DEALER = "SELECT [DealerCode] 'dealerCode', [DealerName] 'dealerName', [SID] 'sID', [Name] 'name', SUM([EarnedPoints]) 'earnedPoints', SUM([RedeemedPoints]) 'redeemedPoints', SUM([BalancePoints]) 'balancePoints','' as error FROM [RewardRedemptionDetails] where [DealerCode] = ?0 group by DealerCode, DealerName, SID, Name";
	//public static String GET_DETAILS_BY_SID = "SELECT [DealerCode] 'dealerCode', [DealerName] 'dealerName', [SID] 'sID', [Name] 'name', [EarnedPoints] 'earnedPoints', [RedeemedPoints] 'redeemedPoints', [BalancePoints] 'balancePoints','' as error FROM [RewardRedemptionDetails] where [SID] = (?)";
	//public static String GET_DETAILS_BY_SID = "SELECT '' as 'dealerCode', '' as 'dealerName', [SID] 'sID', '' as 'name', SUM([EarnedPoints]) 'earnedPoints', SUM([RedeemedPoints]) 'redeemedPoints', SUM([BalancePoints]) 'balancePoints','' as error FROM [RewardRedemptionDetails] where [SID] = ?0 AND [DealerCode] = ?1 group by dealerCode, [SID]";
	public static String GET_DETAILS_BY_SID_AND_DEALERCODE = "SELECT  [DealerCode] 'dealerCode', '' as 'dealerName', [SID] 'sID', '' as 'name', SUM([EarnedPoints]) 'earnedPoints', SUM([RedeemedPoints]) 'redeemedPoints', SUM([BalancePoints]) 'balancePoints','' as error FROM [RewardRedemptionDetails] where [SID] = ?0 and DealerCode = ?1 group by dealerCode, [SID]";
	public static String GET_DETAILS_BY_SID_AND_DEALERCODE_CCP_BB = "SELECT  [DealerCode] 'dealerCode', '' as 'dealerName', [SID] 'sID', '' as 'name', SUM([EarnedPoints]) 'earnedPoints', SUM([RedeemedPoints]) 'redeemedPoints', SUM([BalancePoints]) 'balancePoints','' as error FROM [RewardRedemptionDetails] where [SID] = ?0 and DealerCode = ?1 and (Program = 'CCP' OR Program = 'BRAINBOOST') group by dealerCode, [SID]";
	public static String GET_DETAILS_BY_DEALERCODE_CCP_BB = "SELECT  [DealerCode] 'dealerCode', '' as 'dealerName', '' as 'sID', '' as 'name', SUM([EarnedPoints]) 'earnedPoints', SUM([RedeemedPoints]) 'redeemedPoints', SUM([BalancePoints]) 'balancePoints','' as error FROM [RewardRedemptionDetails] where DealerCode = ?0 and (Program = 'CCP' OR Program = 'BRAINBOOST') group by dealerCode";
	public static String GET_DETAILS_BY_SID_AND_DEALERCODE_TTTA = "SELECT  [DealerCode] 'dealerCode', '' as 'dealerName', [SID] 'sID', '' as 'name', SUM([EarnedPoints]) 'earnedPoints', SUM([RedeemedPoints]) 'redeemedPoints', SUM([BalancePoints]) 'balancePoints','' as error FROM [RewardRedemptionDetails] where [SID] = ?0 and DealerCode = ?1 and (Program = 'TOPTECH' OR Program = 'TOPADVISOR') group by dealerCode, [SID]";
	public static String GET_DETAILS_BY_DEALERCODE_TTTA = "SELECT  [DealerCode] 'dealerCode', '' as 'dealerName', '' as 'sID', '' as 'name', SUM([EarnedPoints]) 'earnedPoints', SUM([RedeemedPoints]) 'redeemedPoints', SUM([BalancePoints]) 'balancePoints','' as error FROM [RewardRedemptionDetails] where DealerCode = ?0 and (Program = 'TOPTECH' OR Program = 'TOPADVISOR') group by dealerCode";
	
	
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsByDealer(String dealerCode);
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsBySid(String sid, String dealerCode);
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsCCPByDealer(String dealerCode);
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsCCPBySid(String sid, String dealerCode);
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsTTTAByDealer(String dealerCode);
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsTTTABySid(String sid, String dealerCode);
	
}
