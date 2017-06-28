package com.imperialm.imiservices.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.imperialm.imiservices.dto.UsersDTO;
import com.imperialm.imiservices.model.TwoStringItems;

@Repository
public class UserDAOImpl {

	private static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public boolean setProfile(UsersDTO user){
		boolean result = false;
		try {
			final Query query = this.em.createNativeQuery("update Users set name = ?0 , email = ?1, [SendEmail] = ?3 where userid = ?2");
			query.setParameter(0, user.getName());
			query.setParameter(1, user.getEmail());
			query.setParameter(2, user.getUserId());
			query.setParameter(3, user.getSendEmail());
			if(query.executeUpdate() > 0){
				result = true;
			}
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in setProfile", ex);
		}
		return result;
	}
	
	@Transactional
	public boolean setTextAlert(TwoStringItems object, String userId){
		boolean result = false;
		try {
			final Query query = this.em.createNativeQuery("update Users set [PhoneNumber] = ?0 , [SendText] = ?1 where userid = ?2");
			query.setParameter(0, object.getItem1());
			query.setParameter(1, object.getItem2());
			query.setParameter(2, userId);
			if(query.executeUpdate() > 0){
				result = true;
			}
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in setProfile", ex);
		}
		return result;
	}
	
	
	@Transactional
	public boolean setPassword(String id, String password, String salt){
		boolean result = false;
		try {
			final Query query = this.em.createNativeQuery("update Users set [HashPass] = HASHBYTES('SHA2_512', cast(?0  as varchar(64))) , [Salt] = ?1 where userid = ?2");
			query.setParameter(0, password + salt);
			query.setParameter(1, salt);
			query.setParameter(2, id);
			if(query.executeUpdate() > 0){
				result = true;
			}
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in setPassword", ex);
		}
		return result;
	}
	
	@Transactional
	public boolean setHashedPassword(String id, String hashedPassword, String salt){
		boolean result = false;
		try {
			final Query query = this.em.createNativeQuery("update Users set [HashPass] = ?0 , [Salt] = ?1 where userid = ?2");
			query.setParameter(0, hashedPassword);
			query.setParameter(1, salt);
			query.setParameter(2, id);
			if(query.executeUpdate() > 0){
				result = true;
			}
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in setHashedPassword", ex);
		}
		return result;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<UsersDTO> getProfile(String userId){
			List<UsersDTO> result = new ArrayList<UsersDTO>();
		try {
			final Query query = this.em.createNativeQuery("select [UserId] 'userId',[Name] 'name' ,[Email] 'email' ,[CreatedDate] 'createdDate' ,[CreatedBy] 'createdBy' ,[UpdatedDate] 'updatedDate' ,[UpdatedBy] 'updatedBy' ,[DelFlag] 'delFlag' ,[HashPass] 'hashPass' ,[Salt] 'salt' ,[SendEmail] 'sendEmail' ,[PhoneNumber] 'phoneNumber' FROM [Users] where userId = ?0", UsersDTO.class);
			query.setParameter(0, userId);
			List<UsersDTO> rows = query.getResultList();
			result = rows;
		
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getProfile", ex);
		}
		return result;
	}
	
	
}
