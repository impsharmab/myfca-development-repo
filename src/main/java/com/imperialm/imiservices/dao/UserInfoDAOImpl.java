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

import com.imperialm.imiservices.dto.UserInfoDTO;
import com.imperialm.imiservices.util.IMIServicesUtil;

@Repository
public class UserInfoDAOImpl implements UserInfoDAO {

	private static Logger logger = LoggerFactory.getLogger(UserInfoDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<UserInfoDTO> getUserInfo(String userId) {
		List<UserInfoDTO> result = new ArrayList<UserInfoDTO>();

		UserInfoDTO UserInfoDTO = null;
		
		try {
			final Query query = this.em.createNativeQuery(GET_USERINFO, UserInfoDTO.class);
			query.setParameter(1, userId);
			query.setParameter(2, userId);
			List<UserInfoDTO> rows = query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			UserInfoDTO = new UserInfoDTO();
			UserInfoDTO.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			result.add(UserInfoDTO);
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getExpertPointsEarned", ex);
			UserInfoDTO = new UserInfoDTO();
			UserInfoDTO.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(UserInfoDTO);
		}
		return result;
	}
	
}
