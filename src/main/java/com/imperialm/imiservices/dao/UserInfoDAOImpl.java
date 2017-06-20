package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.UserInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserInfoDAOImpl implements UserInfoDAO {

	private static Logger logger = LoggerFactory.getLogger(UserInfoDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfoDTO> getUserInfo(String userId) {
		List<UserInfoDTO> result = new ArrayList<UserInfoDTO>();
		try {
			final Query query = this.em.createNativeQuery(GET_USERINFO, UserInfoDTO.class);
			query.setParameter(1, userId);
			query.setParameter(2, userId);
			List<UserInfoDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getExpertPointsEarned", ex);
		}
		return result;
	}
	
}
