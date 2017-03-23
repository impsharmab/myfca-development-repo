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

import com.imperialm.imiservices.dto.TTTAEnrollmentsDTO;
import com.imperialm.imiservices.dto.TTTAEnrollmentsSummaryDTO;
import com.imperialm.imiservices.model.response.TotalName;
import com.imperialm.imiservices.util.IMIServicesUtil;

@Repository
public class TTTAEnrollmentsDAOImpl implements TTTAEnrollmentsDAO{
	
	private static Logger logger = LoggerFactory.getLogger(BannerDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public TotalName getTTTAEnrollmentCount() {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(ENROLLMENT_COUNT, TotalName.class);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getTTTAEnrollmentCount", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}
	
	@Override
	public TotalName getTTTAIncentiveEligibleSUM() {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(INCENTIVE_ELIGIBLE_SUM, TotalName.class);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getTTTAIncentiveEligibleSUM", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@Override
	public TotalName getTTTAAdvisorScoreAVG() {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(AVERAGE_SCORE_ADVISOR, TotalName.class);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getTTTAAdvisorScoreAVG", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@Override
	public TotalName getTTTATechnicianScoreAVG() {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(AVERAGE_SCORE_TECHNICIAN, TotalName.class);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getTTTATechnicianScoreAVG", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@Override
	public TotalName getTTTATechnicianSurveyCount() {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(COUNT_OF_SURVEY_ADVISOR, TotalName.class);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getTTTATechnicianSurveyCount", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@Override
	public TotalName getTTTAAdvisorSurveyCount() {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(COUNT_OF_SURVEY_TECHNICIAN, TotalName.class);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getTTTAAdvisorSurveyCount", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TTTAEnrollmentsDTO> getTTTAEnrollmentsBySID(String sid, String positionCode) {
		List<TTTAEnrollmentsDTO> result = new ArrayList<TTTAEnrollmentsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_SID, TTTAEnrollmentsDTO.class);
			query.setParameter(0, sid);
			query.setParameter(1, positionCode);
			List<TTTAEnrollmentsDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getTTTAEnrollmentsBySID", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TTTAEnrollmentsDTO> getTTTAEnrollmentsByDealerCode(String dealerCode, String positionCode) {
		List<TTTAEnrollmentsDTO> result = new ArrayList<TTTAEnrollmentsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_DEALERCODE, TTTAEnrollmentsDTO.class);
			query.setParameter(0, dealerCode);
			query.setParameter(1, positionCode);
			List<TTTAEnrollmentsDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getTTTAEnrollmentsByDealerCode", ex);
		}
		return result;
	}

}
