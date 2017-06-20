package com.imperialm.imiservices.services;

import com.imperialm.imiservices.dto.ImagesDTO;

import java.util.List;


public interface BannerService {
	public List<ImagesDTO> getBannersByRole(int roleId, String territory);
	public List<ImagesDTO> getAllBanners();
}
