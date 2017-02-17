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
import com.imperialm.imiservices.dto.CertProfsExpertGraphDTO;
import com.imperialm.imiservices.dto.CertProfsWinnersGraphDTO;
import com.imperialm.imiservices.util.IMIServicesUtil;

@Repository
public class CertProfsWinnersGraphDAOImpl implements CertProfsWinnersGraphDAO {

	private static Logger logger = LoggerFactory.getLogger(TilesDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<CertProfsWinnersGraphDTO> getBCCertifications(boolean filter) {
		List<CertProfsWinnersGraphDTO> result = new ArrayList<CertProfsWinnersGraphDTO>();

		CertProfsWinnersGraphDTO CertProfsWinnersGraphDTO = null;

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
			CertProfsWinnersGraphDTO = new CertProfsWinnersGraphDTO();
			CertProfsWinnersGraphDTO.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			result.add(CertProfsWinnersGraphDTO);
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getBCCertifications", ex);
			CertProfsWinnersGraphDTO = new CertProfsWinnersGraphDTO();
			CertProfsWinnersGraphDTO.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(CertProfsWinnersGraphDTO);
		}
		return result;
	}
	
	
	public List<CertProfsWinnersGraphDTO>  getAllDistricData(List<String> list){
		List<CertProfsWinnersGraphDTO> result = new ArrayList<CertProfsWinnersGraphDTO>();

		CertProfsWinnersGraphDTO CertProfsWinnersGraphDTO = null;

		try {
			final Query query = this.em.createNativeQuery(SELECT_DISTRIC_BY_BC, CertProfsWinnersGraphDTO.class);
			query.setParameter(0, list);
			List<CertProfsWinnersGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			CertProfsWinnersGraphDTO = new CertProfsWinnersGraphDTO();
			CertProfsWinnersGraphDTO.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			result.add(CertProfsWinnersGraphDTO);
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getAllDistricData", ex);
			CertProfsWinnersGraphDTO = new CertProfsWinnersGraphDTO();
			CertProfsWinnersGraphDTO.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(CertProfsWinnersGraphDTO);
		}
		return result;
		
	}
	

}
