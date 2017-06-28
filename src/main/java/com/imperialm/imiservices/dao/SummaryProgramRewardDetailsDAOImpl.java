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

import com.imperialm.imiservices.dto.SummaryProgramRewardDetailsDTO;
import com.imperialm.imiservices.dto.SummaryProgramRewardGraphDTO;

@Repository
public class SummaryProgramRewardDetailsDAOImpl implements SummaryProgramRewardDetailsDAO{

	private static Logger logger = LoggerFactory.getLogger(SummaryProgramRewardDetailsDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SummaryProgramRewardGraphDTO> getSummaryProgramRewardDetailsBySIDYTD(String territory, String dealerCode) {
		List<SummaryProgramRewardGraphDTO> result = new ArrayList<SummaryProgramRewardGraphDTO>();
		try {
				final Query query = this.em.createNativeQuery(SELECT_BY_SID_YTD, SummaryProgramRewardGraphDTO.class);
				query.setParameter(0, territory);
				query.setParameter(1, dealerCode);
				result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSummaryProgramRewardDetailsBySIDYTD", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SummaryProgramRewardDetailsDTO> getSummaryProgramRewardDetailsByDealerCodeYTD(String dealerCode) {
		List<SummaryProgramRewardDetailsDTO> result = new ArrayList<SummaryProgramRewardDetailsDTO>();

		try {
				final Query query = this.em.createNativeQuery(SELECT_BY_DEALERCODE_YTD, SummaryProgramRewardDetailsDTO.class);
				query.setParameter(0, dealerCode);
				result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSummaryProgramRewardDetailsByDealerCodeYTD", ex);
		}
		return result;
	}
}
