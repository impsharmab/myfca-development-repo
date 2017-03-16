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

import com.imperialm.imiservices.dto.RewardRedemptionGraphDTO;
import com.imperialm.imiservices.util.IMIServicesUtil;

@Repository
public class RewardRedemptionGraphDAOImpl implements RewardRedemptionGraphDAO{

	private static Logger logger = LoggerFactory.getLogger(RewardRedemptionGraphDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<RewardRedemptionGraphDTO> getRewardRedemptionGraphByParentTerritoryList(List<String> list) {
		List<RewardRedemptionGraphDTO> result = new ArrayList<RewardRedemptionGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_LIST, RewardRedemptionGraphDTO.class);
			query.setParameter(0, list);
			List<RewardRedemptionGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getRewardRedemptionGraphByParentTerritoryList", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RewardRedemptionGraphDTO> getRewardRedemptionGraphByParentTerritoryListDistinct(List<String> list) {
		List<RewardRedemptionGraphDTO> result = new ArrayList<RewardRedemptionGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_LIST_DISTICT_PARENT, RewardRedemptionGraphDTO.class);
			query.setParameter(0, list);
			List<RewardRedemptionGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getRewardRedemptionGraphByParentTerritoryListDistinct", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RewardRedemptionGraphDTO> getRewardRedemptionGraphByChildTerritoryList(List<String> list) {
		List<RewardRedemptionGraphDTO> result = new ArrayList<RewardRedemptionGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_TERRITORY_LIST, RewardRedemptionGraphDTO.class);
			query.setParameter(0, list);
			List<RewardRedemptionGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getRewardRedemptionGraphByChildTerritoryList", ex);
		}
		return result;
	}
}
