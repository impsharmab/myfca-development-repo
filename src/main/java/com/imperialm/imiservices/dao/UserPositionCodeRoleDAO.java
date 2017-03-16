package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.UserPositionCodeRoleDTO;

public interface UserPositionCodeRoleDAO {
	public static String SELECT_DEALERCODE_PC_ROLE_BY_SID = "SELECT [DealerCode] ,[SID] ,[PositionCode] ,[isPrimaryPosition] ,[IsPrimaryDealer], x.[RoleId] FROM [mser2].[dbo].[DealerPersonnel] INNER JOIN [mser2].[dbo].[DealerPersonnelPositions] as x on x.Code = [PositionCode] where [SID] = ?0";
	public static String SELECT_USER_BC_BY_DEALERCODE = "SELECT bc.BCCode FROM [dbo].[DealerInfo] di inner join [dbo].[BCCODES] bc on di.[BC] = bc.[BCName] where di.[DealerCode] = ?0 AND di.DelFlag = 'N'";
	public static String SELECT_USER_DISTRICT_BY_DEALERCODE = "SELECT CONCAT(bc.BCCode , '-' , di.[District]) FROM [dbo].[DealerInfo] di inner join [dbo].[BCCODES] bc on di.[BC] = bc.[BCName] where di.[DealerCode] = ?0 AND di.DelFlag = 'N'";
	
	public static String SELECT_USER_TERRITORY = "select ut.Territory from users u join UserTerritory ut on ut.UserID = u.UserId where u.userid = ?0";
	
	List<UserPositionCodeRoleDTO> getDealerCodePCRoleBySid(String sid);
	List<String> getBCByDealerCode(String dealerCode);
	List<String> getDistrictByDealerCode(String dealerCode);
	List<String> getUserTerritoyById(String sid);
	
}
