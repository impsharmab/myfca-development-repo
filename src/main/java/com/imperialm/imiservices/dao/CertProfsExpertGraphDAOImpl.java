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

import com.imperialm.imiservices.dto.CertProfsExpertGraphDTO;
import com.imperialm.imiservices.util.IMIServicesUtil;

@Repository
public class CertProfsExpertGraphDAOImpl implements CertProfsExpertGraphDAO{
	private static Logger logger = LoggerFactory.getLogger(TilesDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<CertProfsExpertGraphDTO> getExpertPointsEarned() {
		List<CertProfsExpertGraphDTO> result = new ArrayList<CertProfsExpertGraphDTO>();

		CertProfsExpertGraphDTO CertProfsExpertGraphDTO = null;

		try {
			final Query query = this.em.createNativeQuery(SELECT_BC, CertProfsExpertGraphDTO.class);
			List<CertProfsExpertGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			CertProfsExpertGraphDTO = new CertProfsExpertGraphDTO();
			CertProfsExpertGraphDTO.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			result.add(CertProfsExpertGraphDTO);
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getExpertPointsEarned", ex);
			CertProfsExpertGraphDTO = new CertProfsExpertGraphDTO();
			CertProfsExpertGraphDTO.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(CertProfsExpertGraphDTO);
		}
		return result;
	}

	@Override
	public List<CertProfsExpertGraphDTO> getParticipantCompletedByProgram() {
		List<CertProfsExpertGraphDTO> result = new ArrayList<CertProfsExpertGraphDTO>();

		CertProfsExpertGraphDTO CertProfsExpertGraphDTO = null;

		try {
			final Query query = this.em.createNativeQuery(SELECT_BC_TOTAL_RAM_JEEP, CertProfsExpertGraphDTO.class);
			List<CertProfsExpertGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			CertProfsExpertGraphDTO = new CertProfsExpertGraphDTO();
			CertProfsExpertGraphDTO.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			result.add(CertProfsExpertGraphDTO);
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getParticipantCompletedByProgram", ex);
			CertProfsExpertGraphDTO = new CertProfsExpertGraphDTO();
			CertProfsExpertGraphDTO.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(CertProfsExpertGraphDTO);
		}
		return result;
	}

}
