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

import com.imperialm.imiservices.dto.MyfcaMSERTotalEarningsDTO;

@Repository
public class MyfcaMSERTotalEarningsDAOImpl implements MyfcaMSERTotalEarningsDAO {

	
	private static Logger logger = LoggerFactory.getLogger(MyfcaMSERTotalEarningsDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getBCEarningsMyFCAMSER")
	public List<MyfcaMSERTotalEarningsDTO> getBCEarnings(boolean filter){
		List<MyfcaMSERTotalEarningsDTO> result = new ArrayList<MyfcaMSERTotalEarningsDTO>();

		try {
			if(filter){
				final Query query = this.em.createNativeQuery(SELECT_BC_FILTERED, MyfcaMSERTotalEarningsDTO.class);
				result = query.getResultList();
				
			}else{
				final Query query = this.em.createNativeQuery(SELECT_BC, MyfcaMSERTotalEarningsDTO.class);
				result = query.getResultList();
				
			}
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getBCData", ex);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Cacheable(value="getAllDistricDataMyFCAMSER")
	public List<MyfcaMSERTotalEarningsDTO>  getAllDistricData(List<String> list){
		List<MyfcaMSERTotalEarningsDTO> result = new ArrayList<MyfcaMSERTotalEarningsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_DISTRIC_BY_BC, MyfcaMSERTotalEarningsDTO.class);
			query.setParameter(0, list);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getAllDistricData", ex);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Cacheable(value="getMSERGraphByTerritoryAndToggle")
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphByTerritoryAndToggle(String territory, String toggle){
		List<MyfcaMSERTotalEarningsDTO> result = new ArrayList<MyfcaMSERTotalEarningsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_AND_TOGGLE, MyfcaMSERTotalEarningsDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getAllDistricData", ex);
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	@Cacheable(value="getMSERGraphByTerritoryAndToggleAndProgram")
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphByTerritoryAndToggleAndProgram(String territory, String toggle, String program){
		List<MyfcaMSERTotalEarningsDTO> result = new ArrayList<MyfcaMSERTotalEarningsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_AND_TOGGLE_AND_PROGRAM, MyfcaMSERTotalEarningsDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			query.setParameter(2, program);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERGraphByTerritoryAndToggleAndProgram", ex);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Cacheable(value="getMSERGraphDistinctProgramsByParentTerritoryAndToggle")
	public List<String> getMSERGraphDistinctProgramsByParentTerritoryAndToggle(String territory, String toggle){
		List<String> result = new ArrayList<String>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_DISTINCT_PROGRAM_BY_PARENT_TERRITORY_AND_TOGGLE);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			result = (List<String>) query.getResultList();
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
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphByChildTerritoryAndToggleAndProgram(String territory, String toggle,
			String program) {
		List<MyfcaMSERTotalEarningsDTO> result = new ArrayList<MyfcaMSERTotalEarningsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_TERRITORY_PROGRAM_AND_TOGGLE, MyfcaMSERTotalEarningsDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			query.setParameter(2, program);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERGraphByChildTerritoryAndToggleAndProgram", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getMSERGraphProgramsSUMByParentTerritoryAndToggle")
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphProgramsSUMByParentTerritoryAndToggle(String territory, String toggle) {
		List<MyfcaMSERTotalEarningsDTO> result = new ArrayList<MyfcaMSERTotalEarningsDTO>();
		
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_AND_TOGGLE_SUM, MyfcaMSERTotalEarningsDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERGraphProgramsSUMByParentTerritoryAndToggle", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getMSERGraphProgramsSUMByChildTerritoryAndToggle")
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphProgramsSUMByChildTerritoryAndToggle(String territory, String toggle) {
		List<MyfcaMSERTotalEarningsDTO> result = new ArrayList<MyfcaMSERTotalEarningsDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_TERRITORY_TOGGLE_SUM, MyfcaMSERTotalEarningsDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERGraphProgramsSUMByChildTerritoryAndToggle", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getMSERGraphByChildTerritoryAndToggle")
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphByChildTerritoryAndToggle(String territory, String toggle) {
		List<MyfcaMSERTotalEarningsDTO> result = new ArrayList<MyfcaMSERTotalEarningsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_TERRITORY_AND_TOGGLE, MyfcaMSERTotalEarningsDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERGraphByChildTerritoryAndToggle", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getMSERGraphNumberOfDealersEnrolledByBC_DistrictAndToggle")
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphNumberOfDealersEnrolledByBC_DistrictAndToggle(String territory,
			String toggle) {
		List<MyfcaMSERTotalEarningsDTO> result = new ArrayList<MyfcaMSERTotalEarningsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_NUMBER_OF_DEALERS_ENROLLED_BY_BC_DISTRICT_AND_TOGGLE, MyfcaMSERTotalEarningsDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERGraphNumberOfDealersEnrolledByBC_DistrictAndToggle", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getMSERGraphProgramsSUMByParentTerritoryAndToggleAndProgram")
	public List<MyfcaMSERTotalEarningsDTO> getMSERGraphProgramsSUMByParentTerritoryAndToggleAndProgram(String territory,
			String toggle, String program) {
		List<MyfcaMSERTotalEarningsDTO> result = new ArrayList<MyfcaMSERTotalEarningsDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_AND_TOGGLE_AND_PROGRAM_SUM, MyfcaMSERTotalEarningsDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			query.setParameter(2, program);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERGraphProgramsSUMByParentTerritoryAndToggleAndProgram", ex);
		}
		return result;
	}
	
	
}
