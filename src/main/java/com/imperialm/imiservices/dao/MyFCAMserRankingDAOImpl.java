package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.MyFCAMserRankingDTO;
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
public class MyFCAMserRankingDAOImpl implements MyFCAMserRankingDAO{

	private static Logger logger = LoggerFactory.getLogger(MyFCAMserRankingDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getMSERDetailsGraphByParent")
	public List<MyFCAMserRankingDTO> getMSERDetailsGraphByParent(String territory) {
		List<MyFCAMserRankingDTO> result = new ArrayList<MyFCAMserRankingDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT, MyFCAMserRankingDTO.class);
			query.setParameter(0, territory);
			List<MyFCAMserRankingDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERDetailsGraphByParent", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="getMSERDetailsGraphByChild")
	public List<MyFCAMserRankingDTO> getMSERDetailsGraphByChild(String territory) {
		List<MyFCAMserRankingDTO> result = new ArrayList<MyFCAMserRankingDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD, MyFCAMserRankingDTO.class);
			query.setParameter(0, territory);
			List<MyFCAMserRankingDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getMSERDetailsGraphByChild", ex);
		}
		return result;
	}

}
