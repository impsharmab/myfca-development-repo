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

import com.imperialm.imiservices.dto.MyFCAMserRankingDetailsDTO;

@Repository
public class MyFCAMserRankingDetailsDAOImpl implements MyFCAMserRankingDetailsDAO {

	private static Logger logger = LoggerFactory.getLogger(MyfcaMSERTotalEarningsDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MyFCAMserRankingDetailsDTO> getMSERDetailsBySID(String territory, String dealerCode) {
		List<MyFCAMserRankingDetailsDTO> result = new ArrayList<MyFCAMserRankingDetailsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_SID, MyFCAMserRankingDetailsDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, dealerCode);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERDetailsBySID", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MyFCAMserRankingDetailsDTO> getMSERDetailsSUMBySID(String territory, String dealerCode) {
		List<MyFCAMserRankingDetailsDTO> result = new ArrayList<MyFCAMserRankingDetailsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_SUM_BY_SID, MyFCAMserRankingDetailsDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, dealerCode);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERDetailsBySID", ex);
		}
		return result;
	}
	
}
