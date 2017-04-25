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

import com.imperialm.imiservices.dto.MSERGraphDTO;

@Repository
public class MSERGraphDAOImpl implements MSERGraphDAO {

	
	private static Logger logger = LoggerFactory.getLogger(MSERGraphDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<MSERGraphDTO> getBCEarnings(boolean filter){
		List<MSERGraphDTO> result = new ArrayList<MSERGraphDTO>();

		try {
			if(filter){
				final Query query = this.em.createNativeQuery(SELECT_BC_FILTERED, MSERGraphDTO.class);
				List<MSERGraphDTO> rows = query.getResultList();
				result = rows;
			}else{
				final Query query = this.em.createNativeQuery(SELECT_BC, MSERGraphDTO.class);
				List<MSERGraphDTO> rows = query.getResultList();
				result = rows;
			}
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getBCData", ex);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<MSERGraphDTO>  getAllDistricData(List<String> list){
		List<MSERGraphDTO> result = new ArrayList<MSERGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_DISTRIC_BY_BC, MSERGraphDTO.class);
			query.setParameter(0, list);
			List<MSERGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getAllDistricData", ex);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<MSERGraphDTO> getMSERGraphByTerritoryAndToggle(String territory, String toggle){
		List<MSERGraphDTO> result = new ArrayList<MSERGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_AND_TOGGLE, MSERGraphDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			List<MSERGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getAllDistricData", ex);
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	public List<MSERGraphDTO> getMSERGraphByTerritoryAndToggleAndProgram(String territory, String toggle, String program){
		List<MSERGraphDTO> result = new ArrayList<MSERGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_AND_TOGGLE_AND_PROGRAM, MSERGraphDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			query.setParameter(2, program);
			List<MSERGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERGraphByTerritoryAndToggleAndProgram", ex);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getMSERGraphDistinctProgramsByParentTerritoryAndToggle(String territory, String toggle){
		List<String> result = new ArrayList<String>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_DISTINCT_PROGRAM_BY_PARENT_TERRITORY_AND_TOGGLE);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			List<String> rows = (List<String>) query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERGraphDistinctProgramsByParentTerritoryAndToggle", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable("getMSERGraphByChildTerritoryAndToggleAndProgram")
	public List<MSERGraphDTO> getMSERGraphByChildTerritoryAndToggleAndProgram(String territory, String toggle,
			String program) {
		List<MSERGraphDTO> result = new ArrayList<MSERGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_TERRITORY_PROGRAM_AND_TOGGLE, MSERGraphDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			query.setParameter(2, program);
			List<MSERGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERGraphByChildTerritoryAndToggleAndProgram", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MSERGraphDTO> getMSERGraphProgramsSUMByParentTerritoryAndToggle(String territory, String toggle) {
		List<MSERGraphDTO> result = new ArrayList<MSERGraphDTO>();
		
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_AND_TOGGLE_SUM, MSERGraphDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			List<MSERGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERGraphProgramsSUMByParentTerritoryAndToggle", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MSERGraphDTO> getMSERGraphProgramsSUMByChildTerritoryAndToggle(String territory, String toggle) {
		List<MSERGraphDTO> result = new ArrayList<MSERGraphDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_TERRITORY_TOGGLE_SUM, MSERGraphDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			List<MSERGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERGraphProgramsSUMByChildTerritoryAndToggle", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MSERGraphDTO> getMSERGraphByChildTerritoryAndToggle(String territory, String toggle) {
		List<MSERGraphDTO> result = new ArrayList<MSERGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_TERRITORY_AND_TOGGLE, MSERGraphDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			List<MSERGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERGraphByChildTerritoryAndToggle", ex);
		}
		return result;
	}

	@Override
	public List<MSERGraphDTO> getMSERGraphNumberOfDealersEnrolledByBC_DistrictAndToggle(String territory,
			String toggle) {
		List<MSERGraphDTO> result = new ArrayList<MSERGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_NUMBER_OF_DEALERS_ENROLLED_BY_BC_DISTRICT_AND_TOGGLE, MSERGraphDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			List<MSERGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERGraphNumberOfDealersEnrolledByBC_DistrictAndToggle", ex);
		}
		return result;
	}
	
	
}
