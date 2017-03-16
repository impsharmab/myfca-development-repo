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

import com.imperialm.imiservices.dto.MSERTopNDTO;
import com.imperialm.imiservices.model.response.TotalName;
import com.imperialm.imiservices.util.IMIServicesUtil;

@Repository
public class MSERGraphDetailsDAOImpl implements MSERGraphDetailsDAO {
	private static Logger logger = LoggerFactory.getLogger(MSERGraphDetailsDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

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
}
