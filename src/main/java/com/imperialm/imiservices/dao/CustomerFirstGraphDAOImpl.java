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

import com.imperialm.imiservices.dto.CustomerFirstGraphDTO;

@Repository
public class CustomerFirstGraphDAOImpl implements CustomerFirstGraphDAO{
	
	private static Logger logger = LoggerFactory.getLogger(CustomerFirstGraphDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getCustomerFirstByParentTerritoryAndToggleList")
	public List<CustomerFirstGraphDTO> getCustomerFirstByParentTerritoryAndToggle(List<String> territory,
			String toggle) {
		List<CustomerFirstGraphDTO> result = new ArrayList<CustomerFirstGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY, CustomerFirstGraphDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getCustomerFirstByParentTerritoryAndToggle", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getCustomerFirstByChildTerritoryAndToggleList")
	public List<CustomerFirstGraphDTO> getCustomerFirstByChildTerritoryAndToggle(List<String> territory,
			String toggle) {
		List<CustomerFirstGraphDTO> result = new ArrayList<CustomerFirstGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_TERRITORY, CustomerFirstGraphDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getCustomerFirstByChildTerritoryAndToggle", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getCustomerFirstByChildTerritoryAndToggle")
	public List<CustomerFirstGraphDTO> getCustomerFirstByChildTerritoryAndToggle(String territory, String toggle) {
		List<CustomerFirstGraphDTO> result = new ArrayList<CustomerFirstGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_TERRITORY_SINGLE, CustomerFirstGraphDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getCustomerFirstByChildTerritoryAndToggle", ex);
		}
		return result;
	}

}
