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

import com.imperialm.imiservices.dto.CertProfsWinnersDetailsDTO;

@Repository
public class CertProfsWinnersDetailsDAOImpl implements CertProfsWinnersDetailsDAO {

	private static Logger logger = LoggerFactory.getLogger(CertProfsWinnersDetailsDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<CertProfsWinnersDetailsDTO> getCertProfsWinnersDetailsByDealerCode(String dealerCode) {
		List<CertProfsWinnersDetailsDTO> result = new ArrayList<CertProfsWinnersDetailsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_DEALER_CODE, CertProfsWinnersDetailsDTO.class);
			query.setParameter(0, dealerCode);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getCertProfsWinnersDetailsByDealerCode", ex);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CertProfsWinnersDetailsDTO> getCertProfsWinnersDetailsByDealerCodeGroupBySID(String dealerCode) {
		List<CertProfsWinnersDetailsDTO> result = new ArrayList<CertProfsWinnersDetailsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_DEALER_CODE_GROUP_BY_SID, CertProfsWinnersDetailsDTO.class);
			query.setParameter(0, dealerCode);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getCertProfsWinnersDetailsByDealerCodeGroupBySID", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CertProfsWinnersDetailsDTO> getCertProfsWinnersDetailsBySID(String sID, String dealerCode) {
		List<CertProfsWinnersDetailsDTO> result = new ArrayList<CertProfsWinnersDetailsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_SID, CertProfsWinnersDetailsDTO.class);
			query.setParameter(0, sID);
			query.setParameter(1, dealerCode);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getCertProfsWinnersDetailsBySID", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CertProfsWinnersDetailsDTO> getCertProfsWinnersDetailsSUMBySID(String sID) {
		List<CertProfsWinnersDetailsDTO> result = new ArrayList<CertProfsWinnersDetailsDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_SID_SUM, CertProfsWinnersDetailsDTO.class);
			query.setParameter(0, sID);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getCertProfsWinnersDetailsSUMBySID", ex);
		}
		return result;
	}

}
