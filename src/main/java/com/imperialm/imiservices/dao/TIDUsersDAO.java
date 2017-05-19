package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.TIDUsersDTO;

public interface TIDUsersDAO {

	public static String SELECT_BY_USERID = "SELECT [TID] 'tID' ,[name] 'name' ,[email] 'email' ,[PositionCode] 'positionCode' ,[territory] 'territory' ,[RoleId] 'roleId' FROM [TIDUsers] where [TID] = ?0";
	
	public List<TIDUsersDTO> getTIDUsersByTID(String tid);
}
