package com.imperialm.imiservices.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

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
	@Transactional
	public boolean updateBanner(DashBoardBannersDTO banner, String userId) {
		boolean result = false;
		try {
			final Query query = this.em.createNativeQuery("update DashBoardBanners set [Image] = ?0, [RoleID] = ?1, [OrderBy]= ?2, [BusinessCenter] = ?3, [Link] = ?4, [UpdatedBy] = ?5, [UpdatedDate] = GETDATE() where [DashBoardBannersID] = ?6");
			query.setParameter(0, banner.getImage());
			query.setParameter(1, banner.getRoleID());
			query.setParameter(2, banner.getOrderBy() );
			query.setParameter(3, banner.getBusinessCenter());
			query.setParameter(4, banner.getLink());
			query.setParameter(5, userId);
			query.setParameter(6, banner.getDashBoardBannersID());
			if(query.executeUpdate() > 0){
				result = true;
			}
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in deleteBanner", ex);
		}
		return result;
	}

	@Override
	@Transactional
	public boolean deleteBanner(int id, String userId) {
		boolean result = false;
		try {
			final Query query = this.em.createNativeQuery("update DashBoardBanners set [DelFlag] = 'Y', [UpdatedBy] = ?1, [UpdatedDate] = GETDATE() where [DashBoardBannersID] = (?0)");
			query.setParameter(0, id);
			query.setParameter(1, userId);
			if(query.executeUpdate() > 0){
				result = true;
			}
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in deleteBanner", ex);
		}
		return result;
	}

	@Override
	@Transactional
	public boolean addBanner(DashBoardBannersDTO banner, String userId) {
		boolean result = false;
		try {
			final Query query = this.em.createNativeQuery("INSERT INTO dbo.[DashBoardBanners] ([Image],[RoleID],[OrderBy],[BusinessCenter],[Link],[CreatedDate],[CreatedBy],[UpdatedDate],[UpdatedBy],[DelFlag])"
					+ "VALUES(?0, (?1), (?2), ?3, ?4, GETDATE(), ?5, GETDATE(), ?5, 'N')");
			query.setParameter(0, banner.getImage());
			query.setParameter(1, banner.getRoleID());
			query.setParameter(2, banner.getOrderBy() );
			query.setParameter(3, banner.getBusinessCenter());
			query.setParameter(4, banner.getLink());
			query.setParameter(5, userId);
			if(query.executeUpdate() > 0){
				result = true;
			}
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in addBanner", ex);
		}
		return result;
	}
	
	
}
