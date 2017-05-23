package com.imperialm.imiservices.ttta.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TTTAUsersDAO{

	private static Logger logger = LoggerFactory.getLogger(TTTAUsersDAO.class);
	
	@Autowired
	@Qualifier("TTTAEntityManager")
	private EntityManager em;
	
	@Transactional()
	public boolean setPassword(String id, String password, String salt){
		boolean result = false;
		try {
			final Query query = this.em.createNativeQuery("update Users set [Password] = ?0 [PasswordHash] = HASHBYTES('SHA2_512', cast(?1  as varchar(64))) , [Salt] = ?2, [ModifiedDate] = GETDATE(), [ModifiedBy] = ?3 where userid = ?3");
			query.setParameter(0, password);
			query.setParameter(1, password + salt);
			query.setParameter(2, salt);
			query.setParameter(3, id);
			if(query.executeUpdate() > 0){
				result = true;
			}
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in setPassword TTTA", ex);
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<String> getPassword(String id){
		List<String> result = new ArrayList<String>();
		try {
			final Query query = this.em.createNativeQuery("select [Password] from Users where userid = ?0");
			query.setParameter(0, id);
			result = (List<String>)query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in setPassword TTTA", ex);
		}
		return result;
	}
	
	
}
