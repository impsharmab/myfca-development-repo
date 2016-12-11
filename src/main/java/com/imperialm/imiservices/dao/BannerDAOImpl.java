package com.imperialm.imiservices.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.imperialm.imiservices.dto.BannersDTO;
import com.imperialm.imiservices.dto.ImagesDTO;
import com.imperialm.imiservices.dto.request.UserRoleRequest;
import com.imperialm.imiservices.model.response.BannerResponse;
import com.imperialm.imiservices.util.IMIServicesUtil;

/**
 *
 * @author Dheerajr
 *
 */
@Repository
public class BannerDAOImpl implements BannerDAO {

	private static final Logger logger = LoggerFactory.getLogger(BannerDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<BannersDTO> getBannersByRole(final UserRoleRequest role) {
		final List<BannersDTO> result = new ArrayList<>();
		List<ImagesDTO> images = null;

		BannersDTO bannerDTO = null;
		ImagesDTO image = null;

		String prevProgramCode = "";
		try {
			final Query query = em.createNativeQuery(BANNERS_BY_ROLE, BannerResponse.class);
			query.setParameter(1, role.getRoleID());

			final List<BannerResponse> bannerRows = query.getResultList();
			for (final BannerResponse bannerRow : bannerRows) {

				if ("".equals(prevProgramCode)) {
					prevProgramCode = bannerRow.getProgramCode();
					bannerDTO = new BannersDTO(prevProgramCode, "");
					images = new ArrayList<>();
				} else if (!prevProgramCode.equals(bannerRow.getProgramCode())) {
					prevProgramCode = bannerRow.getProgramCode();
					bannerDTO.setBanners(images);
					result.add(bannerDTO);
					bannerDTO = new BannersDTO(prevProgramCode, "");
					images = new ArrayList<>();
				}
				image = new ImagesDTO();
				image.setImageName(bannerRow.getBannerName());
				image.setImageOrder(bannerRow.getBannerOrder());
				image.setFileName(bannerRow.getFileName());
				images.add(image);
			}
			if (!bannerRows.isEmpty()) {
				bannerDTO.setBanners(images);
				result.add(bannerDTO);
			}

		} catch (final NoResultException ex) {
			bannerDTO = new BannersDTO();
			bannerDTO.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			result.add(bannerDTO);
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getBannersByRole", ex);
			bannerDTO = new BannersDTO();
			bannerDTO.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(bannerDTO);
		}
		return result;

	}
}
