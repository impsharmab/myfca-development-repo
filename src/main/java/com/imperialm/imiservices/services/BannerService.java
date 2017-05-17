package com.imperialm.imiservices.services;

import java.util.List;

import com.imperialm.imiservices.dto.ImagesDTO;
import com.imperialm.imiservices.dto.request.InputRequest;


public interface BannerService {
	public List<ImagesDTO> getBannersByRole(int roleId, String territory);
	public List<ImagesDTO> getAllBanners();
}
