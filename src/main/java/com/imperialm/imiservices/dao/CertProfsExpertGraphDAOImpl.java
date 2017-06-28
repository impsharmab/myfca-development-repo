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

import com.imperialm.imiservices.dto.CertProfsExpertGraphDTO;

@Repository
public class CertProfsExpertGraphDAOImpl implements CertProfsExpertGraphDAO{
	private static Logger logger = LoggerFactory.getLogger(CertProfsExpertGraphDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<CertProfsExpertGraphDTO> getExpertPointsEarned() {
		List<CertProfsExpertGraphDTO> result = new ArrayList<CertProfsExpertGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BC, CertProfsExpertGraphDTO.class);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getExpertPointsEarned", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgram() {
		List<CertProfsExpertGraphDTO> result = new ArrayList<CertProfsExpertGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BC_TOTAL_RAM_JEEP, CertProfsExpertGraphDTO.class);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getParticipantCompletedByProgram", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getExpertPointsEarnedByChildTerritoryList")
	public List<CertProfsExpertGraphDTO> getExpertPointsEarnedByChildTerritory(List<String> filters) {
		List<CertProfsExpertGraphDTO> result = new ArrayList<CertProfsExpertGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_TOTAL_POINTS_BY_CHILD_TERRITORY, CertProfsExpertGraphDTO.class);
			query.setParameter(0, filters);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getExpertPointsEarnedByChildTerritory", ex);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getExpertPointsEarnedByChildTerritory")
	public List<CertProfsExpertGraphDTO> getExpertPointsEarnedByChildTerritory(String filters) {
		List<CertProfsExpertGraphDTO> result = new ArrayList<CertProfsExpertGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_TOTAL_POINTS_BY_CHILD_TERRITORY_SINGLE, CertProfsExpertGraphDTO.class);
			query.setParameter(0, filters);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getExpertPointsEarnedByChildTerritory", ex);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getExpertPointsEarnedByChildTerritoryAsParentList")
	public List<CertProfsExpertGraphDTO> getExpertPointsEarnedByChildTerritoryAsParent(List<String> filters) {
		List<CertProfsExpertGraphDTO> result = new ArrayList<CertProfsExpertGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_TOTAL_POINTS_BY_CHILD_TERRITORY_AS_PARENT, CertProfsExpertGraphDTO.class);
			query.setParameter(0, filters);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getExpertPointsEarnedByChildTerritory", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getParticipantCompletedByProgramByChildTerritoryList")
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgramByChildTerritory(List<String> filters) {
		List<CertProfsExpertGraphDTO> result = new ArrayList<CertProfsExpertGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_TOTAL_RAM_JEEP_BY_CHILD_TERRITORY, CertProfsExpertGraphDTO.class);
			query.setParameter(0, filters);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getParticipantCompletedByProgramByChildTerritory", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getParticipantCompletedByProgramByChildTerritoryAndCertTypeList")
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgramByChildTerritoryAndCertType(
			List<String> filters, String certType) {
		List<CertProfsExpertGraphDTO> result = new ArrayList<CertProfsExpertGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_TOTAL_RAM_JEEP_BY_CHILD_TERRITORY_AND_CERT_TYPE, CertProfsExpertGraphDTO.class);
			query.setParameter(0, filters);
			query.setParameter(1, certType);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getParticipantCompletedByProgramByChildTerritoryAndCertType", ex);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getParticipantCompletedByProgramByChildTerritoryAndCertType")
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgramByChildTerritoryAndCertType(
			String filters, String certType) {
		List<CertProfsExpertGraphDTO> result = new ArrayList<CertProfsExpertGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_TOTAL_RAM_JEEP_BY_CHILD_TERRITORY_AND_CERT_TYPE_SINGLE, CertProfsExpertGraphDTO.class);
			query.setParameter(0, filters);
			query.setParameter(1, certType);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getParticipantCompletedByProgramByChildTerritoryAndCertType", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getExpertPointsEarnedByParentTerritoryList")
	public List<CertProfsExpertGraphDTO> getExpertPointsEarnedByParentTerritory(List<String> filters) {
		List<CertProfsExpertGraphDTO> result = new ArrayList<CertProfsExpertGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY, CertProfsExpertGraphDTO.class);
			query.setParameter(0, filters);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getExpertPointsEarnedByParentTerritory", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getExpertPointsEarnedByParentTerritorySumList")
	public List<CertProfsExpertGraphDTO> getExpertPointsEarnedByParentTerritorySum(List<String> filters) {
		List<CertProfsExpertGraphDTO> result = new ArrayList<CertProfsExpertGraphDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_SUM, CertProfsExpertGraphDTO.class);
			query.setParameter(0, filters);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getExpertPointsEarnedByParentTerritorySum", ex);
		}
		return result;
	}

}
