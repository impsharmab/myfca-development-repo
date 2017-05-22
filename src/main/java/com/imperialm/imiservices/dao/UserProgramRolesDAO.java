package com.imperialm.imiservices.dao;

import java.util.List;

public interface UserProgramRolesDAO {

	public static String SELECT_PROGRAMCODE_BY_ID = "SELECT [ProgramCode] FROM [UserProgramRoles] where [UserId] = ?0";
	public static String SELECT_ROLE_BY_ID = "SELECT [RoleId] FROM [UserProgramRoles] where [UserId] = ?0";
	public static String SELECT_ROLE_BY_ID_AND_POSITIONCODE = "SELECT TOP 1 [RoleId] FROM [UserProgramRoles] where [UserId] = ?0 AND [ProgramCode] = ?1";
	public static String CHECK_IF_ADMIN = "SELECT TOP 1 [UserId] FROM [UserProgramRoles] where [UserId] = ?0 AND [ProgramID] = ?1 and RoleID = ?2 and DelFlag = 'N'";
	public List<String> getUserRoleById(String userId);
	public List<String> getUserProgramCodeById(String userId);
	public String getUserRoleByIdAndPositionCode(String userId, String positionCode);
	public boolean isAdmin(String userId);
}
