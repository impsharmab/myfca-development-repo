package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.MyfcaMSERTotalEarningsDetailsDTO;
import com.imperialm.imiservices.model.response.TotalName;
import com.imperialm.imiservices.util.IMIServicesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MyfcaMSERTotalEarningsDetailsDAOImpl implements MyfcaMSERTotalEarningsDetailsDAO {
	private static Logger logger = LoggerFactory.getLogger(MyfcaMSERTotalEarningsDetailsDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public TotalName getMSERDealersCount() {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(DEALERS_COUNT, TotalName.class);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getDealersCount", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TotalName getDealersCountWithPercentage(){
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(DEALERS_COUNT_WITH_PERCENTAGE, TotalName.class);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getDealersCountWithPercentage", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TotalName getMTDByProgramAndProgramgroup(String name, String program, String programgroup) {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(MTD_BY_PROGRAM_AND_PROGRAMGROUP, TotalName.class);
			query.setParameter(1, name);
			query.setParameter(2, program);
			query.setParameter(3, programgroup);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getMTDByProgramAndProgramgroup", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TotalName getYTDByProgramAndProgramgroup(String name, String program, String programgroup) {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(YTD_BY_PROGRAM_AND_PROGRAMGROUP, TotalName.class);
			query.setParameter(1, name);
			query.setParameter(2, program);
			query.setParameter(3, programgroup);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getYTDByProgramAndProgramgroup", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TotalName getExcellanceCardAwardYTDNAT() {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(EXCELLENCE_CARD_AWARDS_YTD_NAT, TotalName.class);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getExcellanceCardAwardYTDNAT", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TotalName getExcellanceCardAwardMTDNAT() {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(EXCELLENCE_CARD_AWARDS_MTD_NAT, TotalName.class);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getExcellanceCardAwardMTDNAT", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TotalName getMSEREnrolledDealersCount() {
		// TODO Auto-generated method stub 
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(DEALERS_ENROLLED_COUNT, TotalName.class);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getDealersCount", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getMSEREarningsYTDByBCOrDistrict")
	public TotalName getMSEREarningsYTDByBCOrDistrict(String territory) {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(SELECT_TOTAL_EARNINGS_YTD_BY_BC_OR_DISTRICT, TotalName.class);
			query.setParameter(0, territory);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getMSEREarningsYTDByBCOrDistrict", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getMSEREarningsMTDByBCOrDistrict")
	public TotalName getMSEREarningsMTDByBCOrDistrict(String territory) {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(SELECT_TOTAL_EARNINGS_MTD_BY_BC_OR_DISTRICT, TotalName.class);
			query.setParameter(0, territory);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getMSEREarningsMTDByBCOrDistrict", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getMSERDealersCountByBCOrDistrict")
	public TotalName getMSERDealersCountByBCOrDistrict(String territory) {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(SELECT_DEALERCOUNT_BY_BC_OR_DISTRICT, TotalName.class);
			query.setParameter(0, territory);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			TotalName.setName("Total Dealers Enrolled");
			TotalName.setName("0");
			logger.error("error occured in getMSERDealersCountByBCOrDistrict", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getMSERParticipantEnrolledByDealerCode")
	public TotalName getMSERParticipantEnrolledByDealerCode(String dealerCode) {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(SELECT_PARTICIPANT_ENROLLED_BY_DEALERCODE, TotalName.class);
			query.setParameter(0, dealerCode);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getMSERParticipantEnrolledByDealerCode", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getParticipantExcellanceCardAwardMTDBySIDandDealer")
	public TotalName getParticipantExcellanceCardAwardMTD(String sid, String dealerCode) {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(MTD_EXCELLANCE_CARD_AWARD, TotalName.class);
			query.setParameter(0, sid);
			query.setParameter(1, dealerCode);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getParticipantExcellanceCardAwardMTD", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getParticipantExcellanceCardAwardYTDBySIDandDealer")
	public TotalName getParticipantExcellanceCardAwardYTD(String sid, String dealerCode) {
		List<TotalName> result = new ArrayList<TotalName>();

		TotalName TotalName = null;
		try {
			final Query query = this.em.createNativeQuery(YTD_EXCELLANCE_CARD_AWARD, TotalName.class);
			query.setParameter(0, sid);
			query.setParameter(1, dealerCode);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			TotalName = new TotalName();
			TotalName.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TotalName);
			result.add(TotalName);
			
		} catch (final Exception ex) {
			TotalName = new TotalName();
			logger.error("error occured in getParticipantExcellanceCardAwardYTD", ex);
			TotalName.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TotalName);
		}
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MyfcaMSERTotalEarningsDetailsDTO> getMSERGraphDetailsByDealerCode(String dealerCode) {
		List<MyfcaMSERTotalEarningsDetailsDTO> result = new ArrayList<MyfcaMSERTotalEarningsDetailsDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_DEALER_CODE, MyfcaMSERTotalEarningsDetailsDTO.class);
			query.setParameter(0, dealerCode);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + ex);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERDetailsGraphByDealerCode", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getMSERGraphDetailsBySID")
	public List<MyfcaMSERTotalEarningsDetailsDTO> getMSERGraphDetailsBySID(String sid, String dealerCode) {
		List<MyfcaMSERTotalEarningsDetailsDTO> result = new ArrayList<MyfcaMSERTotalEarningsDetailsDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_SID, MyfcaMSERTotalEarningsDetailsDTO.class);
			query.setParameter(0, sid);
			query.setParameter(1, dealerCode);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + ex);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERGraphDetailsBySID", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getMSERGraphDetailsSUMBySID")
	public List<MyfcaMSERTotalEarningsDetailsDTO> getMSERGraphDetailsSUMBySID(String sid, String dealerCode) {
		List<MyfcaMSERTotalEarningsDetailsDTO> result = new ArrayList<MyfcaMSERTotalEarningsDetailsDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_SUM_BY_SID, MyfcaMSERTotalEarningsDetailsDTO.class);
			query.setParameter(0, sid);
			query.setParameter(1, dealerCode);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + ex);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERGraphDetailsSUMBySID", ex);
		}
		return result;
	}
}
