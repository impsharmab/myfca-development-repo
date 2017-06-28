package com.imperialm.imiservices.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.imperialm.imiservices.dto.TIDUsersDTO;

@Repository
public class TIDUsersDAOImpl implements TIDUsersDAO {

	private static Logger logger = LoggerFactory.getLogger(TIDUsersDAOImpl.class);
	private static List<String> ADMIN_POSITION_CODES = new ArrayList<>(Arrays.asList("IAD","IBC","IEX","IDM","IDS","IJM"));

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

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable("checkIfAdmin")
	public boolean isAdmin(String userId) {
		List<String> result = new ArrayList<String>();
		try {
			final Query query = this.em.createNativeQuery(CHECK_IF_ADMIN);
			query.setParameter(0, userId);
			query.setParameter(1, ADMIN_POSITION_CODES);
			List<String> rows = (List<String>) query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in isAdmin", ex);
		}
		if(result.size() > 0){
			return true;
		}
		return false;
	}

}
