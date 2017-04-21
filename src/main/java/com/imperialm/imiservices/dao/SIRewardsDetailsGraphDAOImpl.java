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

import com.imperialm.imiservices.dto.SIRewardsDetailsGraphDTO;

@Repository
public class SIRewardsDetailsGraphDAOImpl implements SIRewardsDetailsGraphDAO {
	
	private static Logger logger = LoggerFactory.getLogger(SIRewardsDetailsGraphDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<SIRewardsDetailsGraphDTO> getSIRewardsDetailsGraphByTerritoryAndToggle(String territory, String toggle){
		List<SIRewardsDetailsGraphDTO> result = new ArrayList<SIRewardsDetailsGraphDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_AND_TOGGLE, SIRewardsDetailsGraphDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			List<SIRewardsDetailsGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSIRewardsDetailsGraphByTerritoryAndToggle", ex);
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SIRewardsDetailsGraphDTO> getSIRewardsDetailsGraphByTerritoryAndToggle(List<String> territory, String toggle){
		List<SIRewardsDetailsGraphDTO> result = new ArrayList<SIRewardsDetailsGraphDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_LIST_AND_TOGGLE, SIRewardsDetailsGraphDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			List<SIRewardsDetailsGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSIRewardsDetailsGraphByTerritoryAndToggle", ex);
		}
		return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<SIRewardsDetailsGraphDTO> getSIRewardsDetailsGraphByTerritoryAndToggleFilterParent(List<String> territory,
			String toggle) {
		List<SIRewardsDetailsGraphDTO> result = new ArrayList<SIRewardsDetailsGraphDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_LIST_AND_TOGGLE_DISTICT_PARENT, SIRewardsDetailsGraphDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			List<SIRewardsDetailsGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSIRewardsDetailsGraphByTerritoryAndToggleFilterParent", ex);
		}
		return result;
	}


	@Override
	public List<SIRewardsDetailsGraphDTO> getSIRewardsDetailsGraphByChildTerritoryAndToggle(List<String> territory,
			String toggle) {
		List<SIRewardsDetailsGraphDTO> result = new ArrayList<SIRewardsDetailsGraphDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_TERRITORY_LIST_AND_TOGGLE, SIRewardsDetailsGraphDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			List<SIRewardsDetailsGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSIRewardsDetailsGraphByChildTerritoryAndToggle", ex);
		}
		return result;
	}


	@Override
	public List<SIRewardsDetailsGraphDTO> getSIRewardsDetailsGraphByChildTerritoryAndToggle(String territory,
			String toggle) {
		List<SIRewardsDetailsGraphDTO> result = new ArrayList<SIRewardsDetailsGraphDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_TERRITORY_LIST_AND_TOGGLE_SINGLE, SIRewardsDetailsGraphDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			List<SIRewardsDetailsGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSIRewardsDetailsGraphByChildTerritoryAndToggle", ex);
		}
		return result;
	}
	
}
