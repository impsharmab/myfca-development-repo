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

import com.imperialm.imiservices.dto.BrainBoostWinndersGraphDTO;
import com.imperialm.imiservices.dto.CertProfsExpertGraphDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersGraphDTO;
import com.imperialm.imiservices.util.IMIServicesUtil;

@Repository
public class CertProfsWinnersGraphDAOImpl implements CertProfsWinnersGraphDAO {

	private static Logger logger = LoggerFactory.getLogger(CertProfsWinnersGraphDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Cacheable(value="getBCCertifications")
	public List<CertProfsWinnersGraphDTO> getBCCertifications(boolean filter) {
		List<CertProfsWinnersGraphDTO> result = new ArrayList<CertProfsWinnersGraphDTO>();

		try {
			if(filter){
				final Query query = this.em.createNativeQuery(SELECT_BC_FILTERED, CertProfsWinnersGraphDTO.class);
				List<CertProfsWinnersGraphDTO> rows = query.getResultList();
				result = rows;
			}else{
				final Query query = this.em.createNativeQuery(SELECT_BC, CertProfsWinnersGraphDTO.class);
				List<CertProfsWinnersGraphDTO> rows = query.getResultList();
				result = rows;
			}
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getBCCertifications", ex);
		}
		return result;
	}
	
	@Cacheable(value="getAllDistricDataCPW")
	public List<CertProfsWinnersGraphDTO>  getAllDistricData(List<String> list){
		List<CertProfsWinnersGraphDTO> result = new ArrayList<CertProfsWinnersGraphDTO>();

		CertProfsWinnersGraphDTO CertProfsWinnersGraphDTO = null;

		try {
			final Query query = this.em.createNativeQuery(SELECT_DISTRIC_BY_BC, CertProfsWinnersGraphDTO.class);
			query.setParameter(0, list);
			List<CertProfsWinnersGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getAllDistricData", ex);
		}
		return result;
		
	}
	
	@Cacheable(value="getByTerritoryCPW")
	public List<CertProfsWinnersGraphDTO>  getByTerritory(List<String> list){
		List<CertProfsWinnersGraphDTO> result = new ArrayList<CertProfsWinnersGraphDTO>();

		CertProfsWinnersGraphDTO CertProfsWinnersGraphDTO = null;

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_TERRITORY, CertProfsWinnersGraphDTO.class);
			query.setParameter(0, list);
			List<CertProfsWinnersGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getAllDistricData", ex);
		}
		return result;
		
	}


	@Override
	@Cacheable(value="getByChildTerritoryListCPW")
	public List<CertProfsWinnersGraphDTO> getByChildTerritory(List<String> list) {
		List<CertProfsWinnersGraphDTO> result = new ArrayList<CertProfsWinnersGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_TERRITORY, CertProfsWinnersGraphDTO.class);
			query.setParameter(0, list);
			List<CertProfsWinnersGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getByChildTerritory", ex);
		}
		return result;
	}


	@Override
	@Cacheable(value="getByChildTerritoryCPW")
	public List<CertProfsWinnersGraphDTO> getByChildTerritory(String list) {
		List<CertProfsWinnersGraphDTO> result = new ArrayList<CertProfsWinnersGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_TERRITORY_SINGLE, CertProfsWinnersGraphDTO.class);
			query.setParameter(0, list);
			List<CertProfsWinnersGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getByChildTerritory", ex);
		}
		return result;
	}
	

}
