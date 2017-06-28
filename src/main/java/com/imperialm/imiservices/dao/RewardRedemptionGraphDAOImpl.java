package com.imperialm.imiservices.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.imperialm.imiservices.dto.RewardRedemptionGraphDTO;

@Repository
public class RewardRedemptionGraphDAOImpl implements RewardRedemptionGraphDAO{

	private static Logger logger = LoggerFactory.getLogger(RewardRedemptionGraphDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getRewardRedemptionGraphByParentTerritoryList")
	public List<RewardRedemptionGraphDTO> getRewardRedemptionGraphByParentTerritoryList(List<String> list) {
		List<RewardRedemptionGraphDTO> result = new ArrayList<RewardRedemptionGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_LIST, RewardRedemptionGraphDTO.class);
			query.setParameter(0, list);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getRewardRedemptionGraphByParentTerritoryList", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getRewardRedemptionGraphByParentTerritoryListDistinct")
	public List<RewardRedemptionGraphDTO> getRewardRedemptionGraphByParentTerritoryListDistinct(List<String> list) {
		List<RewardRedemptionGraphDTO> result = new ArrayList<RewardRedemptionGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_LIST_DISTICT_PARENT, RewardRedemptionGraphDTO.class);
			query.setParameter(0, list);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getRewardRedemptionGraphByParentTerritoryListDistinct", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getRewardRedemptionGraphByChildTerritoryList")
	public List<RewardRedemptionGraphDTO> getRewardRedemptionGraphByChildTerritoryList(List<String> list) {
		List<RewardRedemptionGraphDTO> result = new ArrayList<RewardRedemptionGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_TERRITORY_LIST, RewardRedemptionGraphDTO.class);
			query.setParameter(0, list);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getRewardRedemptionGraphByChildTerritoryList", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getRewardRedemptionGraphByChildTerritory")
	public List<RewardRedemptionGraphDTO> getRewardRedemptionGraphByChildTerritory(String list) {
		List<RewardRedemptionGraphDTO> result = new ArrayList<RewardRedemptionGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_TERRITORY, RewardRedemptionGraphDTO.class);
			query.setParameter(0, list);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getRewardRedemptionGraphByChildTerritory", ex);
		}
		return result;
	}
}
