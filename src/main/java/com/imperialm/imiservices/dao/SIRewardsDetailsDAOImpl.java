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

@Repository
public class SIRewardsDetailsDAOImpl implements SIRewardsDetailsDAO {

	private static Logger logger = LoggerFactory.getLogger(SIRewardsDetailsDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsBySID(String sID, String dealerCode, String quarter) {
		List<SIRewardsDetailsDTO> result = new ArrayList<SIRewardsDetailsDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_SID, SIRewardsDetailsDTO.class);
			query.setParameter(0, sID);
			query.setParameter(1, dealerCode);
			query.setParameter(2, quarter);
			List<SIRewardsDetailsDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSIRewardsDetailsBySID", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsByDealerCodeAndToggle(String dealerCode, String toggle, String quarter) {
		List<SIRewardsDetailsDTO> result = new ArrayList<SIRewardsDetailsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_DEALER_CODE_AND_TOGGLE, SIRewardsDetailsDTO.class);
			query.setParameter(0, dealerCode);
			query.setParameter(1, toggle);
			query.setParameter(2, quarter);
			List<SIRewardsDetailsDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSIRewardsDetailsByDealerCodeAndToggle", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsBySIDAndToggle(String sID, String toggle, String dealerCode, String quarter) {
		List<SIRewardsDetailsDTO> result = new ArrayList<SIRewardsDetailsDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_SID_AND_TOGGLE, SIRewardsDetailsDTO.class);
			query.setParameter(0, sID);
			query.setParameter(1, toggle);
			query.setParameter(2, dealerCode);
			query.setParameter(3, quarter);
			List<SIRewardsDetailsDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSIRewardsDetailsBySIDAndToggle", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsByDealerCodeAndToggleAndPositionCode(String dealerCode,
			String toggle, String quarter, String positionCode) {
		List<SIRewardsDetailsDTO> result = new ArrayList<SIRewardsDetailsDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_DEALER_CODE_AND_TOGGLE_AND_POSITIONCODE, SIRewardsDetailsDTO.class);
			query.setParameter(0, dealerCode);
			query.setParameter(1, toggle);
			query.setParameter(2, quarter);
			query.setParameter(3, positionCode);
			List<SIRewardsDetailsDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSIRewardsDetailsByDealerCodeAndToggleAndPositionCode", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsByDealerCodeAndToggleAndPositionCode(String dealerCode,
			String toggle, String quarter, List<String> positionCode) {
		List<SIRewardsDetailsDTO> result = new ArrayList<SIRewardsDetailsDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_DEALER_CODE_AND_TOGGLE_AND_POSITIONCODE_LIST, SIRewardsDetailsDTO.class);
			query.setParameter(0, dealerCode);
			query.setParameter(1, toggle);
			query.setParameter(2, quarter);
			query.setParameter(3, positionCode);
			List<SIRewardsDetailsDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSIRewardsDetailsByDealerCodeAndToggleAndPositionCode", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SIRewardsDetailsDTO> getSIRewardsDetailsByDealerCodeAndToggleAndPositionCode(List<String> dealerCode,
			String toggle, String quarter, List<String> positionCode) {
		List<SIRewardsDetailsDTO> result = new ArrayList<SIRewardsDetailsDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_DEALER_CODE_LIST_AND_TOGGLE_AND_POSITIONCODE_LIST, SIRewardsDetailsDTO.class);
			query.setParameter(0, dealerCode);
			query.setParameter(1, toggle);
			query.setParameter(2, quarter);
			query.setParameter(3, positionCode);
			List<SIRewardsDetailsDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSIRewardsDetailsByDealerCodeAndToggleAndPositionCode", ex);
		}
		return result;
	}

}
