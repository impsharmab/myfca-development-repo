package com.imperialm.imiservices.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.imperialm.imiservices.dto.TIDUsersDTO;

@Repository
public class TIDUsersDAOImpl implements TIDUsersDAO {

	private static Logger logger = LoggerFactory.getLogger(TIDUsersDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TIDUsersDTO> getTIDUsersByTID(String tid) {
		List<TIDUsersDTO> result = new ArrayList<TIDUsersDTO>();
		try {
				final Query query = this.em.createNativeQuery(SELECT_BY_USERID, TIDUsersDTO.class);
				query.setParameter(0, tid);
				result = query.getResultList();
		} catch (final Exception ex) {
			logger.error("error occured in getTIDUsersByTID", ex);
		}
		return result;
	}

}
