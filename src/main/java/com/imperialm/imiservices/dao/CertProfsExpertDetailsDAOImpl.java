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

import com.imperialm.imiservices.dto.CertProfsExpertDetailsDTO;

@Repository
public class CertProfsExpertDetailsDAOImpl implements CertProfsExpertDetailsDAO{

	private static Logger logger = LoggerFactory.getLogger(BrainBoostWinnersDetailsDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsByDealerCodeANDCertType(String dealerCode,
			String certType) {
		List<CertProfsExpertDetailsDTO> result = new ArrayList<CertProfsExpertDetailsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_DEALER_CODE_AND_CERT_TYPE, CertProfsExpertDetailsDTO.class);
			query.setParameter(0, dealerCode);
			query.setParameter(1, certType);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getCertProfsExpertDetailsByDealerCodeANDCertType", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsBySIDANDCertType(String sid, String certType, String dealerCode) {
		List<CertProfsExpertDetailsDTO> result = new ArrayList<CertProfsExpertDetailsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_SID_AND_CERT_TYPE, CertProfsExpertDetailsDTO.class);
			query.setParameter(0, sid);
			query.setParameter(1, certType);
			query.setParameter(2, dealerCode);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getCertProfsExpertDetailsBySIDANDCertType", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsSUMBySID(String sid, String dealerCode) {
		List<CertProfsExpertDetailsDTO> result = new ArrayList<CertProfsExpertDetailsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_SUM_BY_SID_AND_DEALER, CertProfsExpertDetailsDTO.class);
			query.setParameter(0, sid);
			query.setParameter(1, dealerCode);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getCertProfsExpertDetailsSUMBySID", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsSUMByDealerCode(String dealerCode) {
		List<CertProfsExpertDetailsDTO> result = new ArrayList<CertProfsExpertDetailsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_DEALER_SUM, CertProfsExpertDetailsDTO.class);
			query.setParameter(0, dealerCode);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getCertProfsExpertDetailsSUMByDealerCode", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsSUMBySIDANDCertType(String sid, String certType) {
		List<CertProfsExpertDetailsDTO> result = new ArrayList<CertProfsExpertDetailsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_SUM_BY_SID_AND_CERT_TYPE, CertProfsExpertDetailsDTO.class);
			query.setParameter(0, sid);
			query.setParameter(1, certType);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getCertProfsExpertDetailsSUMBySIDANDCertType", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CertProfsExpertDetailsDTO> getCertProfsExpertDetailsSUMBySID(String sid) {
		List<CertProfsExpertDetailsDTO> result = new ArrayList<CertProfsExpertDetailsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_SID_SUM, CertProfsExpertDetailsDTO.class);
			query.setParameter(0, sid);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getCertProfsExpertDetailsSUMBySID", ex);
		}
		return result;
	}

}
