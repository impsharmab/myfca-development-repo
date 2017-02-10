package com.imperialm.imiservices.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperialm.imiservices.dao.UserProfileDAO;
import com.imperialm.imiservices.dto.UserProfileDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.model.NoTile;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private UserProfileDAO userProfileDAO;
	
	private List<NoTile> noTileList = new ArrayList<NoTile>();
	
	public UserProfileServiceImpl(UserProfileDAO userProfileDAO){
		this.userProfileDAO = userProfileDAO;
		
		this.noTileList.add(new NoTile(2,"","tile", "","mser-logo.jpg","www.mopar.com"));
		this.noTileList.add(new NoTile(1,"","chart", "","mser.jpg","www.mopar.com"));
		this.noTileList.add(new NoTile(9,"","chart", "","brainboost.jpg","www.mopar.com"));
		this.noTileList.add(new NoTile(10,"","chart", "","cert-pro-experts.jpg","www.mopar.com"));
		this.noTileList.add(new NoTile(11,"","chart", "","cert-pro-experts.jpg","www.mopar.com"));
		this.noTileList.add(new NoTile(12,"","chart", "","brainboost.jpg","www.mopar.com"));
		this.noTileList.add(new NoTile(13,"","chart", "","cert-pro-banner.jpg","www.mopar.com"));
		this.noTileList.add(new NoTile(14,"","tile", "","topadvisor.jpg","www.mopar.com"));
		this.noTileList.add(new NoTile(15,"","tile", "","toptech.jpg","www.mopar.com"));
		this.noTileList.add(new NoTile(16,"","chart", "","toptech-topadv.jpg","www.mopar.com"));
		this.noTileList.add(new NoTile(17,"","chart", "","toptech-topadv.jpg","www.mopar.com"));
		this.noTileList.add(new NoTile(3,"","tile", "","mser-logo.jpg","www.mopar.com"));
		this.noTileList.add(new NoTile(4,"","tile", "","mser-logo.jpg","www.mopar.com"));
		this.noTileList.add(new NoTile(5,"","tile", "","mser-logo.jpg","www.mopar.com"));
		this.noTileList.add(new NoTile(6,"","tile", "","mser-logo.jpg","www.mopar.com"));
		this.noTileList.add(new NoTile(7,"","tile", "","mser-logo.jpg","www.mopar.com"));
		this.noTileList.add(new NoTile(8,"","tile", "","mser-logo.jpg","www.mopar.com"));
		
	}

	@Override
	public UserProfileDTO getUserProfile(final InputRequest userRoleReq) {
		return this.userProfileDAO.getUserProfile(userRoleReq);
	}
	
	@Override
	public List<NoTile> getNoTiles() {
		return noTileList;
	}

}
