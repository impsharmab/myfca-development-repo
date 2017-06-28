package com.imperialm.imiservices.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.imperialm.imiservices.dto.UserPositionCodeRoleDTO;

@Repository
public class UserPositionCodeRoleDAOImpl implements UserPositionCodeRoleDAO {
	
	private static Logger logger = LoggerFactory.getLogger(UserPositionCodeRoleDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	@Cacheable("getDealerCodePCRoleBySid")
	public List<UserPositionCodeRoleDTO> getDealerCodePCRoleBySid(String sid) {
		List<UserPositionCodeRoleDTO> result = new ArrayList<UserPositionCodeRoleDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_DEALERCODE_PC_ROLE_BY_SID, UserPositionCodeRoleDTO.class);
			query.setParameter(0, sid);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getDealerCodePCRoleBySid", ex);
		}
		return result;
	}
        
    @SuppressWarnings("unchecked")
	@Override
	public List<UserPositionCodeRoleDTO> getDealerCodePCRoleByUID(String uid, String dealerCode, String positionCode) {
		List<UserPositionCodeRoleDTO> result = new ArrayList<UserPositionCodeRoleDTO>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_DEALERCODE_PC_ROLE_BY_UID, UserPositionCodeRoleDTO.class);
			query.setParameter(0, uid);
                        query.setParameter(1, dealerCode);
                        query.setParameter(2, positionCode);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getDealerCodePCRoleBySid", ex);
		}
		return result;
	}
        
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getBCByDealerCode(String dealerCode) {
		List<String> result = new ArrayList<String>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_USER_BC_BY_DEALERCODE);
			query.setParameter(0, dealerCode);
			result = (List<String>) query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getBCByDealerCode", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDistrictByDealerCode(String dealerCode) {
		List<String> result = new ArrayList<String>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_USER_DISTRICT_BY_DEALERCODE);
			query.setParameter(0, dealerCode);
			result = (List<String>) query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getDistrictByDealerCode", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable("getUserTerritoyById")
	public List<String> getUserTerritoyById(String sid) {
		List<String> result = new ArrayList<String>();
		try {
			final Query query = this.em.createNativeQuery(SELECT_USER_TERRITORY);
			query.setParameter(0, sid);
			result = (List<String>) query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getUserTerritoyById", ex);
		}
		return result;
	}

}
