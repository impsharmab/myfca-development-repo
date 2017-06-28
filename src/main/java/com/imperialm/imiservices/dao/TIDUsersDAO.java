package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.TIDUsersDTO;

public interface TIDUsersDAO {

	public static String SELECT_BY_USERID = "SELECT [TID] 'tID' ,[name] 'name' ,[email] 'email' ,[PositionCode] 'positionCode' ,[territory] 'territory' ,[RoleId] 'roleId' FROM [TIDUsers] where [TID] = ?0";
	public static String CHECK_IF_ADMIN = "SELECT TOP 1 [TID] FROM [TIDUsers] where [TID] = ?0 AND [PositionCode] IN (?1)";
	
	public List<TIDUsersDTO> getTIDUsersByTID(String tid);
	public boolean isAdmin(String tid);
}
