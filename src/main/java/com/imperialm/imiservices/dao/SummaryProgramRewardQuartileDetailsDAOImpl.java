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

import com.imperialm.imiservices.dto.SummaryProgramRewardQuartileGraphDTO;

@Repository
public class SummaryProgramRewardQuartileDetailsDAOImpl implements SummaryProgramRewardQuartileDetailsDAO {

	private static Logger logger = LoggerFactory.getLogger(SummaryProgramRewardQuartileDetailsDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SummaryProgramRewardQuartileGraphDTO> getSummaryProgramRewardQuartileDetailsBySIDYTD(String sid,
			String dealerCode) {
		List<SummaryProgramRewardQuartileGraphDTO> result = new ArrayList<SummaryProgramRewardQuartileGraphDTO>();
		try {
				final Query query = this.em.createNativeQuery(SELECT_BY_SID_YTD, SummaryProgramRewardQuartileGraphDTO.class);
				query.setParameter(0, sid);
				query.setParameter(1, dealerCode);
				List<SummaryProgramRewardQuartileGraphDTO> rows = query.getResultList();
				result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSummaryProgramRewardQuartileDetailsBySIDYTD", ex);
		}
		return result;
	}

}
