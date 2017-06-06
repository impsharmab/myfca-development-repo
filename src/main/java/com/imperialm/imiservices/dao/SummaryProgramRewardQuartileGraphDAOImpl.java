package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.SummaryProgramRewardQuartileGraphDTO;
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
public class SummaryProgramRewardQuartileGraphDAOImpl implements SummaryProgramRewardQuartileGraphDAO {

	private static Logger logger = LoggerFactory.getLogger(SummaryProgramRewardQuartileGraphDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getSummaryProgramRewardQuartileGraphByParentTerritoryYTD")
	public List<SummaryProgramRewardQuartileGraphDTO> getSummaryProgramRewardQuartileGraphByParentTerritoryYTD(
			List<String> territory) {
		List<SummaryProgramRewardQuartileGraphDTO> result = new ArrayList<SummaryProgramRewardQuartileGraphDTO>();

		try {
				final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_TERRITORY_YTD, SummaryProgramRewardQuartileGraphDTO.class);
				query.setParameter(0, territory);
				List<SummaryProgramRewardQuartileGraphDTO> rows = query.getResultList();
				result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSummaryProgramRewardQuartileGraphByParentTerritoryYTD", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getSummaryProgramRewardQuartileGraphByChildTerritoryYTD")
	public List<SummaryProgramRewardQuartileGraphDTO> getSummaryProgramRewardQuartileGraphByChildTerritoryYTD(
			String territory) {
		List<SummaryProgramRewardQuartileGraphDTO> result = new ArrayList<SummaryProgramRewardQuartileGraphDTO>();

		try {
				final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_TERRITORY_YTD, SummaryProgramRewardQuartileGraphDTO.class);
				query.setParameter(0, territory);
				List<SummaryProgramRewardQuartileGraphDTO> rows = query.getResultList();
				result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getSummaryProgramRewardQuartileGraphByChildTerritoryYTD", ex);
		}
		return result;
	}

}
