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

import com.imperialm.imiservices.dto.RewardRedemptionDetailsDTO;
import com.imperialm.imiservices.util.IMIServicesUtil;

@Repository
public class RewardRedemptionDetailsDAOImpl implements RewardRedemptionDetailsDAO{

	private static Logger logger = LoggerFactory.getLogger(RewardRedemptionDetailsDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsByDealer(String dealerCode) {
		List<RewardRedemptionDetailsDTO> result = new ArrayList<RewardRedemptionDetailsDTO>();

		try {
			final Query query = this.em.createNativeQuery(GET_DETAILS_BY_DEALER, RewardRedemptionDetailsDTO.class);
			query.setParameter(0, dealerCode);
			List<RewardRedemptionDetailsDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getRewardRedemptionDetailsByDealer", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsBySid(String sid, String dealerCode) {
		List<RewardRedemptionDetailsDTO> result = new ArrayList<RewardRedemptionDetailsDTO>();
		try {
			final Query query = this.em.createNativeQuery(GET_DETAILS_BY_SID_AND_DEALERCODE, RewardRedemptionDetailsDTO.class);
			query.setParameter(0, sid);
			query.setParameter(1, dealerCode);
			List<RewardRedemptionDetailsDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + ex);
		} catch (final Exception ex) {
			logger.error("error occured in getRewardRedemptionDetailsBySid", ex);
		}
		return result;
	}

	@Override
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsCCPByDealer(String dealerCode) {
		List<RewardRedemptionDetailsDTO> result = new ArrayList<RewardRedemptionDetailsDTO>();
		try {
			final Query query = this.em.createNativeQuery(GET_DETAILS_BY_DEALERCODE_CCP_BB, RewardRedemptionDetailsDTO.class);
			query.setParameter(0, dealerCode);
			List<RewardRedemptionDetailsDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + ex);
		} catch (final Exception ex) {
			logger.error("error occured in getRewardRedemptionDetailsCCPByDealer", ex);
		}
		return result;
	}

	@Override
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsCCPBySid(String sid, String dealerCode) {
		List<RewardRedemptionDetailsDTO> result = new ArrayList<RewardRedemptionDetailsDTO>();
		try {
			final Query query = this.em.createNativeQuery(GET_DETAILS_BY_SID_AND_DEALERCODE_CCP_BB, RewardRedemptionDetailsDTO.class);
			query.setParameter(0, sid);
			query.setParameter(1, dealerCode);
			List<RewardRedemptionDetailsDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + ex);
		} catch (final Exception ex) {
			logger.error("error occured in getRewardRedemptionDetailsCCPBySid", ex);
		}
		return result;
	}

	@Override
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsTTTAByDealer(String dealerCode) {
		List<RewardRedemptionDetailsDTO> result = new ArrayList<RewardRedemptionDetailsDTO>();
		try {
			final Query query = this.em.createNativeQuery(GET_DETAILS_BY_DEALERCODE_TTTA, RewardRedemptionDetailsDTO.class);
			query.setParameter(0, dealerCode);
			List<RewardRedemptionDetailsDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + ex);
		} catch (final Exception ex) {
			logger.error("error occured in getRewardRedemptionDetailsTTTAByDealer", ex);
		}
		return result;
	}

	@Override
	public List<RewardRedemptionDetailsDTO> getRewardRedemptionDetailsTTTABySid(String sid, String dealerCode) {
		List<RewardRedemptionDetailsDTO> result = new ArrayList<RewardRedemptionDetailsDTO>();
		try {
			final Query query = this.em.createNativeQuery(GET_DETAILS_BY_SID_AND_DEALERCODE_TTTA, RewardRedemptionDetailsDTO.class);
			query.setParameter(0, sid);
			query.setParameter(1, dealerCode);
			List<RewardRedemptionDetailsDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + ex);
		} catch (final Exception ex) {
			logger.error("error occured in getRewardRedemptionDetailsTTTABySid", ex);
		}
		return result;
	}

}
