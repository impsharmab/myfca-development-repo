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

import com.imperialm.imiservices.dto.BrainBoostWinndersGraphDTO;
import com.imperialm.imiservices.util.IMIServicesUtil;

@Repository
public class BrainBoostWinndersGraphDAOImpl implements BrainBoostWinndersGraphDAO {
	private static Logger logger = LoggerFactory.getLogger(TilesDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<BrainBoostWinndersGraphDTO> getBCData(boolean filter) {
		List<BrainBoostWinndersGraphDTO> result = new ArrayList<BrainBoostWinndersGraphDTO>();

		BrainBoostWinndersGraphDTO BrainBoostWinndersGraphDTO = null;

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
	
	public List<BrainBoostWinndersGraphDTO>  getAllDistricData(List<String> list){
		List<BrainBoostWinndersGraphDTO> result = new ArrayList<BrainBoostWinndersGraphDTO>();

		BrainBoostWinndersGraphDTO BrainBoostWinndersGraphDTO = null;

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
	
	
	public List<BrainBoostWinndersGraphDTO>  getByTerritory(List<String> list){
		List<BrainBoostWinndersGraphDTO> result = new ArrayList<BrainBoostWinndersGraphDTO>();
		BrainBoostWinndersGraphDTO BrainBoostWinndersGraphDTO = null;
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

	@Override
	public List<BrainBoostWinndersGraphDTO> getByChildTerritory(List<String> list) {
		List<BrainBoostWinndersGraphDTO> result = new ArrayList<BrainBoostWinndersGraphDTO>();
		BrainBoostWinndersGraphDTO BrainBoostWinndersGraphDTO = null;
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
}
