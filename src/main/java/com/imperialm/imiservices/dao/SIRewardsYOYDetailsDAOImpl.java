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

import com.imperialm.imiservices.dto.SIRewardsDetailsDTO;
import com.imperialm.imiservices.dto.SIRewardsYOYDetailsDTO;

@Repository
public class SIRewardsYOYDetailsDAOImpl implements SIRewardsYOYDetailsDAO {

	private static Logger logger = LoggerFactory.getLogger(SIRewardsYOYDetailsDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<SIRewardsYOYDetailsDTO> getSIRewardsYOYDetailsBySIDAndToggle(String sID, String dealerCode,
			String toggle) {
		List<SIRewardsYOYDetailsDTO> result = new ArrayList<SIRewardsYOYDetailsDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_SID_AND_TOGGLE, SIRewardsYOYDetailsDTO.class);
			query.setParameter(0, sID);
			query.setParameter(1, dealerCode);
			query.setParameter(2, toggle);
			List<SIRewardsYOYDetailsDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSIRewardsDetailsBySIDAndToggle", ex);
		}
		return result;
	}

	@Override
	public List<SIRewardsYOYDetailsDTO> getSIRewardsYOYDetailsByDealerCodeAndToggle(String dealerCode, String toggle) {
		List<SIRewardsYOYDetailsDTO> result = new ArrayList<SIRewardsYOYDetailsDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_DEALERCODE_AND_TOGGLE, SIRewardsYOYDetailsDTO.class);
			query.setParameter(0, dealerCode);
			query.setParameter(1, toggle);
			List<SIRewardsYOYDetailsDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSIRewardsYOYDetailsByDealerCodeAndToggle", ex);
		}
		return result;
	}
	
}
