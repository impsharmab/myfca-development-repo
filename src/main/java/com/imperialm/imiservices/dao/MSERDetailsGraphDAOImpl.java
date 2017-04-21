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

import com.imperialm.imiservices.dto.MSERDetailsGraphDTO;
import com.imperialm.imiservices.dto.MSERGraphDTO;

@Repository
public class MSERDetailsGraphDAOImpl implements MSERDetailsGraphDAO{

	private static Logger logger = LoggerFactory.getLogger(MSERDetailsGraphDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<MSERDetailsGraphDTO> getMSERDetailsGraphByParent(String territory) {
		List<MSERDetailsGraphDTO> result = new ArrayList<MSERDetailsGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT, MSERDetailsGraphDTO.class);
			query.setParameter(0, territory);
			List<MSERDetailsGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERDetailsGraphByParent", ex);
		}
		return result;
	}

	@Override
	public List<MSERDetailsGraphDTO> getMSERDetailsGraphByChild(String territory) {
		List<MSERDetailsGraphDTO> result = new ArrayList<MSERDetailsGraphDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD, MSERDetailsGraphDTO.class);
			query.setParameter(0, territory);
			List<MSERDetailsGraphDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERDetailsGraphByChild", ex);
		}
		return result;
	}

}
