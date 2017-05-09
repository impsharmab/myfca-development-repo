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

@Repository
public class ProgramCountDAOImpl {

	private static Logger logger = LoggerFactory.getLogger(ProgramCountDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	public List<Integer> totalDealersEnrolledByProgramID(int programId){
		List<Integer> result = new ArrayList<Integer>();

		try {
			final Query query = this.em.createNativeQuery("select count(distinct DealerCode) from ProgramEnrollments where Status = 'E' and programId = (?0) and DelFlag = 'N'");
			query.setParameter(0, programId);

			List<Integer> rows = (List<Integer>) query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in totalDealersEnrolledByProgramID", ex);
		}
		return result;
	}
	
	public List<Integer> totalDealersByProgramID(int programId){
		List<Integer> result = new ArrayList<Integer>();

		try {
			final Query query = this.em.createNativeQuery("select count(distinct DealerCode) from ProgramEnrollments where programId = (?0) and DelFlag = 'N'");
			query.setParameter(0, programId);

			List<Integer> rows = (List<Integer>) query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in totalDealersEnrolledByProgramID", ex);
		}
		return result;
	}
	
	public List<Integer> totalDealersEnrolledByProgramID(int programId, String territory){
		List<Integer> result = new ArrayList<Integer>();

		try {
			final Query query = this.em.createNativeQuery("select count(distinct pe.DealerCode) from ProgramEnrollments pe inner join Hierarchy h  on pe.DealerCode = h.Child  where pe.Status = 'E' and pe.programId = ?0 and h.Parent like ?1 and DelFlag = 'N'");
			query.setParameter(0, programId);
			query.setParameter(1, territory);

			List<Integer> rows = (List<Integer>) query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in totalDealersEnrolledByProgramID", ex);
		}
		return result;
	}
	
	
	public List<Integer> totalDealersEnrolledByProgramGroupID(int programId){
		List<Integer> result = new ArrayList<Integer>();

		try {
			final Query query = this.em.createNativeQuery("select count(Distinct DealerCode) from [ProgramGroupEnrollments] where ProgramGroupID = (?0) and DelFlag = 'N' and [Status] = 'E'");
			query.setParameter(0, programId);

			List<Integer> rows = (List<Integer>) query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in totalDealersEnrolledByProgramID", ex);
		}
		return result;
	}
	
	public List<Integer> totalDealersByProgramGroupID(int programId){
		List<Integer> result = new ArrayList<Integer>();

		try {
			final Query query = this.em.createNativeQuery("select count(Distinct DealerCode) from [ProgramGroupEnrollments] where ProgramGroupID = (?0) and DelFlag = 'N'");
			query.setParameter(0, programId);

			List<Integer> rows = (List<Integer>) query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in totalDealersEnrolledByProgramID", ex);
		}
		return result;
	}
	
	public List<Integer> totalParticipantsEnrolledByProgramIDAndDealerCode(int programId, String dealerCode){
		List<Integer> result = new ArrayList<Integer>();

		try {
			final Query query = this.em.createNativeQuery("select count(Distinct gsie.SID) from [GroupSIDEnrollments] gsie inner join [ProgramGroups] pg on pg.GroupID = gsie.ProgramGroupID where pg.ProgramID = (?0) and gsie.DealerCode = ?1 and gsie.Status = 'E' and gsie.[DelFlag] = 'N'");
			query.setParameter(0, programId);
			query.setParameter(1, dealerCode);

			List<Integer> rows = (List<Integer>) query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in totalParticipantsEnrolledByProgramIDAndDealerCode", ex);
		}
		return result;
	}
	
}
