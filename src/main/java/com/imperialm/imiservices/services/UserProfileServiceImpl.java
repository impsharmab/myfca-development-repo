package com.imperialm.imiservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperialm.imiservices.dao.UserProfileDAO;
import com.imperialm.imiservices.dto.UserProfileDTO;
import com.imperialm.imiservices.dto.request.UserRoleRequest;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private UserProfileDAO userProfileDAO;

	@Override
	public UserProfileDTO getUserProfile(final UserRoleRequest userRoleReq) {
		return userProfileDAO.getUserProfile(userRoleReq);
	}
}
