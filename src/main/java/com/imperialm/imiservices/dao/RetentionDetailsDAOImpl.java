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

import com.imperialm.imiservices.dto.RetentionDetailsDTO;

@Repository
public class RetentionDetailsDAOImpl implements RetentionDetailsDAO {
	private static Logger logger = LoggerFactory.getLogger(RetentionDetailsDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<RetentionDetailsDTO> getRetentionDetails(String dealersCode) {		
		
		List<RetentionDetailsDTO> result = new ArrayList<RetentionDetailsDTO>();
		
		try {
			final Query query = this.em.createNativeQuery(GET_RETENTION_DETAILS_BY_DEALER, RetentionDetailsDTO.class);
			query.setParameter(0, dealersCode);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getRetentionDetails", ex);
		}
		return result;
	}
}
