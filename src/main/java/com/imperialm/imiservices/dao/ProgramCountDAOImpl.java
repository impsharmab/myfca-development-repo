package com.imperialm.imiservices.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ProgramCountDAOImpl {

	private static Logger logger = LoggerFactory.getLogger(ProgramCountDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	public List<Integer> totalDealersEnrolledByProgramID(int programId){
		
		return null;
	}
	
	public List<Integer> totalDealersEnrolledByProgramGroupID(int programId){
		
		return null;
	}
	
	public List<Integer> totalParticipantsEnrolledByProgramGroupIDAndDealerCode(int programId, String dealerCode){
		
		return null;
	}
	
}
