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

import com.imperialm.imiservices.dto.BrainBoostWinnersDetailsDTO;

@Repository
public class BrainBoostWinnersDetailsDAOImpl implements BrainBoostWinnersDetailsDAO{

	private static Logger logger = LoggerFactory.getLogger(BrainBoostWinnersDetailsDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<BrainBoostWinnersDetailsDTO> getBrainBoostWinnersDetailsByDealerCode(String dealerCode, String toggle) {
		List<BrainBoostWinnersDetailsDTO> result = new ArrayList<BrainBoostWinnersDetailsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_DEALER_CODE, BrainBoostWinnersDetailsDTO.class);
			query.setParameter(0, dealerCode);
			query.setParameter(1, toggle);
			List<BrainBoostWinnersDetailsDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getBrainBoostWinnersDetailsByDealerCode", ex);
		}
		return result;
	}

	@SuppressWarnings("unused")
	@Override
	public List<BrainBoostWinnersDetailsDTO> getBrainBoostWinnersDetailsBySID(String sID, String toggle) {
		List<BrainBoostWinnersDetailsDTO> result = new ArrayList<BrainBoostWinnersDetailsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_SID, BrainBoostWinnersDetailsDTO.class);
			query.setParameter(0, sID);
			query.setParameter(1, toggle);
			List<BrainBoostWinnersDetailsDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getBrainBoostWinnersDetailsBySID", ex);
		}
		return result;
	}

	@Override
	public List<BrainBoostWinnersDetailsDTO> getBrainBoostWinnersDetailsSUMByDealerCode(String sID, String toggle) {
		List<BrainBoostWinnersDetailsDTO> result = new ArrayList<BrainBoostWinnersDetailsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_DEALERCODE_SUM, BrainBoostWinnersDetailsDTO.class);
			query.setParameter(0, sID);
			query.setParameter(1, toggle);
			List<BrainBoostWinnersDetailsDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getBrainBoostWinnersDetailsSUMByDealerCode", ex);
		}
		return result;
	}
}
