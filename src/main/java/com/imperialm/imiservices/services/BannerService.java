package com.imperialm.imiservices.services;

import java.util.List;

import com.imperialm.imiservices.dto.BannersDTO;
import com.imperialm.imiservices.dto.ImagesDTO;
import com.imperialm.imiservices.dto.request.InputRequest;

/**
 *
 * @author Dheerajr
 *
 */
public interface BannerService {
	public List<BannersDTO> findBannersByRole(InputRequest userRoleReq);
	public List<ImagesDTO> getBannersByRole(int roleId, String territory);
	public List<ImagesDTO> getAllBanners();
}
