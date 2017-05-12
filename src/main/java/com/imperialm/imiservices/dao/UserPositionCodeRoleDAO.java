package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.UserPositionCodeRoleDTO;

public interface UserPositionCodeRoleDAO {

    public static String SELECT_DEALERCODE_PC_ROLE_BY_SID = "SELECT d.[DealerCode] ,d.[SID] ,d.[PositionCode] ,d.[isPrimaryPosition] ,d.[IsPrimaryDealer], x.[RoleId] FROM [dbo].[DealerPersonnel] d INNER JOIN [dbo].[DealerPersonnelPositions] as x on x.PositionCode = d.[PositionCode] where d.[SID] = ?0";
    public static String SELECT_USER_BC_BY_DEALERCODE = "SELECT bc.BCCode FROM [dbo].[DealerInfo] di inner join [dbo].[BCCODES] bc on di.[BC] = bc.[BCName] where di.[DealerCode] = ?0 AND di.DelFlag = 'N'";
    public static String SELECT_USER_DISTRICT_BY_DEALERCODE = "SELECT CONCAT(bc.BCCode , '-' , di.[District]) FROM [dbo].[DealerInfo] di inner join [dbo].[BCCODES] bc on di.[BC] = bc.[BCName] where di.[DealerCode] = ?0 AND di.DelFlag = 'N'";
    public static String SELECT_USER_TERRITORY = "select ut.Territory from users u join UserTerritory ut on ut.UserID = u.UserId where u.userid = ?0";
    public static String SELECT_DEALERCODE_PC_ROLE_BY_UID = "SELECT DealerCode , d.SID , PositionCode , isPrimaryPosition , IsPrimaryDealer, x.RoleId \n" +
                                                            "  FROM Users U \n" +
                                                            " INNER JOIN DealerPersonnel d \n" +
                                                            "    ON U.UserId = d.SID \n" +
                                                            " INNER JOIN DealerPersonnelPositions x \n" +
                                                            "    ON x.PositionCode = PositionCode \n" +
                                                            " WHERE [SID] = ?0 \n" +
                                                            "   AND 1 = CASE WHEN d.DealerCode = ?1 THEN 1 \n" +
                                                            "                WHEN ?1 = '' THEN 1 \n" +
                                                            "                ELSE 0 END \n" +
                                                            "   AND 1 = CASE WHEN d.PositionCode = ?2 THEN 1 \n" +
                                                            "                WHEN ?2 = '' THEN 1 \n" +
                                                            "		     ELSE 0 END";
        
    List<UserPositionCodeRoleDTO> getDealerCodePCRoleBySid(String sid);

    List<String> getBCByDealerCode(String dealerCode);

    List<String> getDistrictByDealerCode(String dealerCode);

    List<String> getUserTerritoyById(String sid);
    
    List<UserPositionCodeRoleDTO> getDealerCodePCRoleByUID(String uid, String dealerCode, String positionCode);
}
