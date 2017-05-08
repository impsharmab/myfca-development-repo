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

import com.imperialm.imiservices.dto.DashBoardBannersDTO;
import com.imperialm.imiservices.dto.ImagesDTO;

@Repository
public class DashBoardBannersDAOImpl implements DashBoardBannersDAO {

	private static Logger logger = LoggerFactory.getLogger(DashBoardBannersDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<ImagesDTO> getBannersByRole(int roleId, String territory) {
		List<ImagesDTO> result = new ArrayList<ImagesDTO>();
		try {
			final Query query = this.em.createNativeQuery(IMAGES_BY_ROLE_AND_TERRITORY, ImagesDTO.class);
			query.setParameter(0, roleId);
			query.setParameter(1, territory);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getBannersByRole", ex);
		}
		return result;
	}

	@Override
	public List<DashBoardBannersDTO> getAllBannersForAdmin() {
		List<DashBoardBannersDTO> result = new ArrayList<DashBoardBannersDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BANNER_FOR_ADMIN, DashBoardBannersDTO.class);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getAllBannersForAdmin", ex);
		}
		return result;
	}

	@Override
	public List<ImagesDTO> getAllBanners() {
		List<ImagesDTO> result = new ArrayList<ImagesDTO>();
		try {
			final Query query = this.em.createNativeQuery(IMAGES_EXECUTIVE, ImagesDTO.class);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getAllBanners", ex);
		}
		return result;
	}

	@Override
	public List<DashBoardBannersDTO> updateBanner(DashBoardBannersDTO banner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DashBoardBannersDTO> deleteBanner(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DashBoardBannersDTO> addBanner(DashBoardBannersDTO banner) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
