package com.imperialm.imiservices.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class DealerPersonnelPositionsDAOImpl implements DealerPersonnelPositionsDAO{
	
	private static Logger logger = LoggerFactory.getLogger(DealerPersonnelPositionsDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getRoleByPositionCode")
	public int getRoleByPositionCode(String positionCode) {
		int result = 0;
		try {
			final Query query = this.em.createNativeQuery(GET_ROLE_BY_POSITIONCODE);
			query.setParameter(0, positionCode);
			List<Integer> rows = (List<Integer>) query.getResultList();
			result = rows.get(0);
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getRoleByPositionCode", ex);
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="checkPositionCode")
	public boolean checkPositionCode(String positionCode) {
		try {
			final Query query = this.em.createNativeQuery(CHECK_POSITIONCODE);
			query.setParameter(0, positionCode);
			List<String> rows = (List<String>) query.getResultList();
			if(rows.size() > 0){
				return true;
			}else{
				return false;
			}
		} catch (final Exception ex) {
			logger.error("error occured in checkPositionCode", ex);
			return false;
		}
	}

}
