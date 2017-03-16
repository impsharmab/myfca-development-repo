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

import com.imperialm.imiservices.dto.SIRewardsYOYGraphDTO;
import com.imperialm.imiservices.util.IMIServicesUtil;

@Repository
public class SIRewardsYOYGraphDAOImpl implements SIRewardsYOYGraphDAO{


	private static Logger logger = LoggerFactory.getLogger(SIRewardsYOYGraphDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<SIRewardsYOYGraphDTO> getSIRewardsYOYGraphByTerritoryAndToggle(String territory, String toggle) {
		List<SIRewardsYOYGraphDTO> result = new ArrayList<SIRewardsYOYGraphDTO>();
		SIRewardsYOYGraphDTO SIRewardsYOYGraphDTO = null;
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_AND_TOGGLE, SIRewardsYOYGraphDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			List<SIRewardsYOYGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			SIRewardsYOYGraphDTO = new SIRewardsYOYGraphDTO();
			SIRewardsYOYGraphDTO.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			result.add(SIRewardsYOYGraphDTO);
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSIRewardsYOYGraphByTerritoryAndToggle", ex);
			SIRewardsYOYGraphDTO = new SIRewardsYOYGraphDTO();
			SIRewardsYOYGraphDTO.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(SIRewardsYOYGraphDTO);
		}
		return result;
	}

	@Override
	public List<SIRewardsYOYGraphDTO> getSIRewardsYOYGraphByTerritoryAndToggle(List<String> territory,
			String toggle) {
		List<SIRewardsYOYGraphDTO> result = new ArrayList<SIRewardsYOYGraphDTO>();
		SIRewardsYOYGraphDTO SIRewardsYOYGraphDTO = null;
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_LIST_AND_TOGGLE, SIRewardsYOYGraphDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			List<SIRewardsYOYGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			SIRewardsYOYGraphDTO = new SIRewardsYOYGraphDTO();
			SIRewardsYOYGraphDTO.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			result.add(SIRewardsYOYGraphDTO);
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSIRewardsYOYGraphByTerritoryAndToggle", ex);
			SIRewardsYOYGraphDTO = new SIRewardsYOYGraphDTO();
			SIRewardsYOYGraphDTO.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(SIRewardsYOYGraphDTO);
		}
		return result;
	}

	@Override
	public List<SIRewardsYOYGraphDTO> getSIRewardsYOYGraphByTerritoryAndToggleFilterParent(List<String> territory,
			String toggle) {
		List<SIRewardsYOYGraphDTO> result = new ArrayList<SIRewardsYOYGraphDTO>();
		SIRewardsYOYGraphDTO SIRewardsYOYGraphDTO = null;
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_LIST_AND_TOGGLE_DISTICT_PARENT, SIRewardsYOYGraphDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			List<SIRewardsYOYGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			SIRewardsYOYGraphDTO = new SIRewardsYOYGraphDTO();
			SIRewardsYOYGraphDTO.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			result.add(SIRewardsYOYGraphDTO);
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSIRewardsYOYGraphByTerritoryAndToggle", ex);
			SIRewardsYOYGraphDTO = new SIRewardsYOYGraphDTO();
			SIRewardsYOYGraphDTO.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(SIRewardsYOYGraphDTO);
		}
		return result;
	}

	
}
