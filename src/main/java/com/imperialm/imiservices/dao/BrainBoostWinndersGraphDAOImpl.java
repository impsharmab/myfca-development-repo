package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.BrainBoostWinndersGraphDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BrainBoostWinndersGraphDAOImpl implements BrainBoostWinndersGraphDAO {
	private static Logger logger = LoggerFactory.getLogger(BrainBoostWinndersGraphDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getBCData")
	public List<BrainBoostWinndersGraphDTO> getBCData(boolean filter) {
		List<BrainBoostWinndersGraphDTO> result = new ArrayList<BrainBoostWinndersGraphDTO>();
		try {
			if(filter){
				final Query query = this.em.createNativeQuery(SELECT_BC_FILTERED, BrainBoostWinndersGraphDTO.class);
				List<BrainBoostWinndersGraphDTO> rows = query.getResultList();
				result = rows;
			}else{
				final Query query = this.em.createNativeQuery(SELECT_BC, BrainBoostWinndersGraphDTO.class);
				List<BrainBoostWinndersGraphDTO> rows = query.getResultList();
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
	@Cacheable(value="getAllDistricData")
	public List<BrainBoostWinndersGraphDTO>  getAllDistricData(List<String> list){
		List<BrainBoostWinndersGraphDTO> result = new ArrayList<BrainBoostWinndersGraphDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_DISTRIC_BY_BC, BrainBoostWinndersGraphDTO.class);
			query.setParameter(0, list);
			List<BrainBoostWinndersGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getAllDistricData", ex);
		}
		return result;
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Cacheable(value="getByTerritory")
	public List<BrainBoostWinndersGraphDTO> getByTerritory(List<String> list){
		List<BrainBoostWinndersGraphDTO> result = new ArrayList<BrainBoostWinndersGraphDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_TERRITORY, BrainBoostWinndersGraphDTO.class);
			query.setParameter(0, list);
			List<BrainBoostWinndersGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getAllDistricData", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getByChildTerritoryList")
	public List<BrainBoostWinndersGraphDTO> getByChildTerritory(List<String> list) {
		List<BrainBoostWinndersGraphDTO> result = new ArrayList<BrainBoostWinndersGraphDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_TERRITORY, BrainBoostWinndersGraphDTO.class);
			query.setParameter(0, list);
			List<BrainBoostWinndersGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getByChildTerritory", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getByChildTerritory")
	public List<BrainBoostWinndersGraphDTO> getByChildTerritory(String list) {
		List<BrainBoostWinndersGraphDTO> result = new ArrayList<BrainBoostWinndersGraphDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_TERRITORY_SINGLE, BrainBoostWinndersGraphDTO.class);
			query.setParameter(0, list);
			List<BrainBoostWinndersGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getByChildTerritory", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BrainBoostWinndersGraphDTO> getBrainBoostWinndersGraphDTOByParentTerritory(String list) {
		List<BrainBoostWinndersGraphDTO> result = new ArrayList<BrainBoostWinndersGraphDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_TERRITORY, BrainBoostWinndersGraphDTO.class);
			query.setParameter(0, list);
			List<BrainBoostWinndersGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getBrainBoostWinndersGraphDTOByParentTerritory", ex);
		}
		return result;
	}
}
