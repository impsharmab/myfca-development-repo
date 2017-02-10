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

import com.imperialm.imiservices.dto.TTTAEnrolledDTO;
import com.imperialm.imiservices.model.response.TTTAResponse;
import com.imperialm.imiservices.util.IMIServicesUtil;

@Repository
public class TTTAEnrolledDAOImpl implements TTTAEnrolledDAO{
	
	private static Logger logger = LoggerFactory.getLogger(TTTAEnrolledDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<TTTAEnrolledDTO> getTTTAEnrollmentsBC(boolean enrolled) {		
		
		List<TTTAEnrolledDTO> result = new ArrayList<TTTAEnrolledDTO>();

		TTTAEnrolledDTO TTTAEnrolledDTO = null;

		try {
			if(enrolled){
				final Query query = this.em.createNativeQuery(BC_ENROLLED_DATA, TTTAEnrolledDTO.class);
				result = query.getResultList();
			}else{
				final Query query = this.em.createNativeQuery(BC_NOT_ENROLLED_DATA, TTTAEnrolledDTO.class);
				result = query.getResultList();
			}
			
		} catch (final NoResultException ex) {
			TTTAEnrolledDTO = new TTTAEnrolledDTO();
			TTTAEnrolledDTO.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			result.add(TTTAEnrolledDTO);
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getTTTAEnrollmentsBC", ex);
			TTTAEnrolledDTO = new TTTAEnrolledDTO();
			TTTAEnrolledDTO.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TTTAEnrolledDTO);
		}
		return result;
	}


}
