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

import com.imperialm.imiservices.dto.TTTAEnrolledGraphDTO;

@Repository
public class TTTAEnrolledGraphDAOImpl implements TTTAEnrolledGraphDAO {

	private static Logger logger = LoggerFactory.getLogger(TTTAEnrolledGraphDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getTTTAEnrolledByParentTerritoryList")
	public List<TTTAEnrolledGraphDTO> getTTTAEnrolledByParentTerritory(List<String> territory) {
		List<TTTAEnrolledGraphDTO> result = new ArrayList<TTTAEnrolledGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT, TTTAEnrolledGraphDTO.class);
			query.setParameter(0, territory);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getTTTAEnrolledByParentTerritory", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getTTTAEnrolledByChildTerritory")
	public List<TTTAEnrolledGraphDTO> getTTTAEnrolledByChildTerritory(String list) {
		List<TTTAEnrolledGraphDTO> result = new ArrayList<TTTAEnrolledGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_TERRITORY, TTTAEnrolledGraphDTO.class);
			query.setParameter(0, list);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getTTTAEnrolledByChildTerritory", ex);
		}
		return result;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public int getTTTAEnrolledDealersNAT() {
		int result = 0;

		try {
			final Query query = this.em.createNativeQuery(SELECT_DEALERCOUNT_NAT, TTTAEnrolledGraphDTO.class);
			List<Integer> rows = (List<Integer>) query.getResultList();
			result = rows.get(0);
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getTTTAEnrolledDealersNAT", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getTTTAEnrolledBCORDistrict")
	public int getTTTAEnrolledBCORDistrict(String territory) {
		int result = 0;

		try {
			final Query query = this.em.createNativeQuery(SELECT_DEALERCOUNT_BY_CHILD, TTTAEnrolledGraphDTO.class);
			List<Integer> rows = (List<Integer>) query.getResultList();
			query.setParameter(0, territory);
			result = rows.get(0);
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getTTTAEnrolledBCORDistrict", ex);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getTTTAEnrolledByParentTerritoryNotEnrolledList")
	public List<TTTAEnrolledGraphDTO> getTTTAEnrolledByParentTerritoryNotEnrolled(List<String> territory) {
		List<TTTAEnrolledGraphDTO> result = new ArrayList<TTTAEnrolledGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_NOT_ENROLLED, TTTAEnrolledGraphDTO.class);
			query.setParameter(0, territory);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getTTTAEnrolledByParentTerritoryNotEnrolled", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TTTAEnrolledGraphDTO> getTTTAEnrolledByChildTerritoryNotEnrolled(String list) {
		List<TTTAEnrolledGraphDTO> result = new ArrayList<TTTAEnrolledGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_TERRITORY_NOT_ENROLLED, TTTAEnrolledGraphDTO.class);
			query.setParameter(0, list);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getTTTAEnrolledByChildTerritoryNotEnrolled", ex);
		}
		return result;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public int getTTTAEnrolledDealersNATNotEnrolled() {
		int result = 0;

		try {
			final Query query = this.em.createNativeQuery(SELECT_DEALERCOUNT_NAT_NOT_ENROLLED, TTTAEnrolledGraphDTO.class);
			List<Integer> rows = (List<Integer>) query.getResultList();
			result = rows.get(0);
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getTTTAEnrolledDealersNATNotEnrolled", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getTTTAEnrolledBCORDistrictNotEnrolled(String territory) {
		int result = 0;

		try {
			final Query query = this.em.createNativeQuery(SELECT_DEALERCOUNT_BY_CHILD_NOT_ENROLLED, TTTAEnrolledGraphDTO.class);
			List<Integer> rows = (List<Integer>) query.getResultList();
			query.setParameter(0, territory);
			result = rows.get(0);
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getTTTAEnrolledBCORDistrictNotEnrolled", ex);
		}
		return result;
	}
	

}
