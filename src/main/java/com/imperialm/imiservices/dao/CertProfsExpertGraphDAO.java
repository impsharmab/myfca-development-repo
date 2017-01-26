package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.CertProfsExpertGraphDTO;

public interface CertProfsExpertGraphDAO {

	public static String SELECT_BC = "SELECT [ParentTerritory] 'parentTerritory' , '' as 'childTerritory' , SUM([Points]) 'points' , SUM([TotalPoints]) 'totalPoints', SUM([BCPointRank]) 'bCPointRank', SUM([NATPointRank]) 'nATPointRank', SUM(Cert) 'cert' , '' 'CertType',  '' as error FROM [dbo].[CertProfsExpertGraph] WHERE [ParentTerritory] = 'CA' OR [ParentTerritory] = 'DN' OR [ParentTerritory] = 'GL' OR [ParentTerritory] = 'MA' OR [ParentTerritory] = 'MW' OR [ParentTerritory] = 'NE' OR [ParentTerritory] = 'SE' OR [ParentTerritory] = 'SW' OR [ParentTerritory] = 'WE' GROUP BY [ParentTerritory]";
	public static String SELECT_BC_TOTAL_RAM_JEEP = "SELECT [ParentTerritory], '' as childTerritory, [CertType] 'certType', SUM([Cert]) 'cert', SUM([Points]) 'points', SUM([TotalPoints]) 'totalpoints', SUM([BCPointRank]) 'bCPointRank', SUM([NATPointRank]) 'nATPointRank', '' as error FROM [mser2].[dbo].[CertProfsExpertGraph] WHERE [ParentTerritory] = 'CA' OR [ParentTerritory] = 'DN' OR [ParentTerritory] = 'GL' OR [ParentTerritory] = 'MA' OR [ParentTerritory] = 'MW' OR [ParentTerritory] = 'NE' OR [ParentTerritory] = 'SE' OR [ParentTerritory] = 'SW' OR [ParentTerritory] = 'WE' GROUP BY [ParentTerritory], [CertType]";
	
	public List<CertProfsExpertGraphDTO> getExpertPointsEarned();
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgram();
	
}
