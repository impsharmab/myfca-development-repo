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

import com.imperialm.imiservices.dto.TTTAEnrollmentsSummaryDTO;
import com.imperialm.imiservices.model.response.TotalName;
import com.imperialm.imiservices.util.IMIServicesUtil;

@Repository
public class TTTAEnrollmentsSummaryDAOImpl implements TTTAEnrollmentsSummaryDAO {

	private static Logger logger = LoggerFactory.getLogger(MyfcaMSERTotalEarningsDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TTTAEnrollmentsSummaryDTO> getTTTAEnrollmentsSummaryByChildAndPositionCode(String territories,
			String positionCode) {
		List<TTTAEnrollmentsSummaryDTO> result = new ArrayList<TTTAEnrollmentsSummaryDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_TERRITORY_AND_POSITIONCODE, TTTAEnrollmentsSummaryDTO.class);
			query.setParameter(0, territories);
			query.setParameter(1, positionCode);
			List<TTTAEnrollmentsSummaryDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getTTTAEnrollmentsSummaryByChildAndPositionCode", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TTTAEnrollmentsSummaryDTO> getTTTAEnrollmentsSummaryByParentAndPositionCode(List<String> territories,
			String positionCode) {
		List<TTTAEnrollmentsSummaryDTO> result = new ArrayList<TTTAEnrollmentsSummaryDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_AND_POSITIONCODE, TTTAEnrollmentsSummaryDTO.class);
			query.setParameter(0, territories.get(0));
			query.setParameter(1, positionCode);
			List<TTTAEnrollmentsSummaryDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getTTTAEnrollmentsSummaryByParentAndPositionCode", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TotalName getTTTANATTopTechEnrolledDealerCount() {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(SELECT_NAT_TOP_TECH_ENROLLED_DEALERCOUNT, TotalName.class);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getTTTANATTopTechEnrolledDealerCount", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TotalName getTTTANATTopAdvisorEnrolledDealerCount() {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(SELECT_NAT_TOP_ADVISOR_ENROLLED_DEALERCOUNT, TotalName.class);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getTTTANATTopAdvisorEnrolledDealerCount", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TotalName getTTTANATTopEnrolledDealerCountByBCDistrictAndPositionCode(String territory) {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(SELECT_NUMBER_OF_DEALERS_ENROLLED_BY_BC_DISTRICT, TotalName.class);
			query.setParameter(0, territory);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getTTTANATTopEnrolledDealerCountByBCDistrictAndPositionCode", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TotalName getTTTANATTopTechEnrolledIncentiveEligible() {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(SELECT_NAT_TOP_TECH_IncentiveEligible, TotalName.class);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getTTTANATTopTechEnrolledIncentiveEligible", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TotalName getTTTANATTopAdvisorEnrolledIncentiveEligible() {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(SELECT_NAT_TOP_ADVISOR_IncentiveEligible, TotalName.class);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getTTTANATTopAdvisorEnrolledIncentiveEligible", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TTTAEnrollmentsSummaryDTO> getTTTAEnrollmentsSummarySUMByParentAndPositionCode(List<String> territories,
			String positionCode) {
		List<TTTAEnrollmentsSummaryDTO> result = new ArrayList<TTTAEnrollmentsSummaryDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_SUM_BY_PARENT_TERRITORY_AND_POSITIONCODE, TTTAEnrollmentsSummaryDTO.class);
			query.setParameter(0, territories.get(0));
			query.setParameter(1, positionCode);
			List<TTTAEnrollmentsSummaryDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getTTTAEnrollmentsSummarySUMByParentAndPositionCode", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Double> getTTTANATAverageSurveyScoreByPositionCode(String positionCode) {
		List<Double> result = new ArrayList<Double>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_NAT_AVERAGE_SURVEY_SCORE);
			query.setParameter(0, positionCode);
			List<Double> rows = (List<Double>) query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getTTTANATAverageSurveyScoreByPositionCode", ex);
		}
		return result;
	}

}
