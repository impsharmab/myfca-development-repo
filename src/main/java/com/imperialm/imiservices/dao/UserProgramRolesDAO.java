package com.imperialm.imiservices.dao;

import java.util.List;

public interface UserProgramRolesDAO {

	public static String SELECT_PROGRAMCODE_BY_ID = "SELECT [ProgramCode] FROM [dbo].[UserProgramRoles] where [UserId] LIKE ?0";
	public static String SELECT_ROLE_BY_ID = "SELECT [RoleId] FROM [dbo].[UserProgramRoles] where [UserId] LIKE ?0";
	public static String SELECT_ROLE_BY_ID_AND_POSITIONCODE = "SELECT TOP 1 [RoleId] FROM [dbo].[UserProgramRoles] where [UserId] LIKE ?0 AND [ProgramCode] LIKE ?1";
	public List<String> getUserRoleById(String userId);
	public List<String> getUserProgramCodeById(String userId);
	public String getUserRoleByIdAndPositionCode(String userId, String positionCode);
}
