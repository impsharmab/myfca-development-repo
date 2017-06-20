package com.imperialm.imiservices.dao;

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
public class ProgramCountDAOImpl {

	private static Logger logger = LoggerFactory.getLogger(ProgramCountDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Cacheable(value="totalDealersEnrolledByProgramID")
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
	
	@SuppressWarnings("unchecked")
	@Cacheable(value="totalDealersByProgramID")
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
	
	@SuppressWarnings("unchecked")
	@Cacheable(value="totalDealersEnrolledByProgramIDAndTerritory")
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
	
	@SuppressWarnings("unchecked")
	@Cacheable(value="totalDealersEnrolledByProgramGroupID")
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
	
	@SuppressWarnings("unchecked")
	@Cacheable(value="totalDealersByProgramGroupID")
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
	
	@SuppressWarnings("unchecked")
	@Cacheable(value="totalParticipantsEnrolledByProgramIDAndDealerCode")
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
	
	
	@SuppressWarnings("unchecked")
	@Cacheable(value="getTotalELValidated")
	public List<Integer> getTotalELValidated(){
		List<Integer> result = new ArrayList<Integer>();

		try {
			//final Query query = this.em.createNativeQuery("select count( distinct D.DealerCode) from DealerInfo D left join (select pge.* from ProgramGroupEnrollments pge join ProgramGroups pg on pge.ProgramGroupID = pg.ProgramGroupID where pg.ProgramGroupID = 1) as p on D.DealerCode = p.DealerCode join (select * from ProgramEnrollments where ProgramId = 1 and Status = 'E') PE on D.DealerCode = PE.DealerCode where D.ELFlag = 'Y'");
			final Query query = this.em.createNativeQuery("Select Count(Distinct DI.DealerCode) From DealerInfo DI Where DI.ELFlag='Y' and TerminatedDate is Null");
			List<Integer> rows = (List<Integer>) query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getTotalELValidated", ex);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public boolean checkDealerEnrollmentByProgram(int programId, String dealerCode){
		List<Integer> result = new ArrayList<Integer>();

		try {
			final Query query = this.em.createNativeQuery("select count(distinct DealerCode) from ProgramEnrollments where Status = 'E' and programId = ?0 and DealerCode = ?1 and DelFlag = 'N'");
			query.setParameter(0, programId);
			query.setParameter(1, dealerCode);
			List<Integer> rows = (List<Integer>) query.getResultList();
			result = rows;
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in checkDealerEnrollmentByProgram", ex);
		}
		return result.size()>0;
	}
	
	
}
