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


import com.imperialm.imiservices.dto.MyfcaMSERTopNDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.util.IMIServicesUtil;

@Repository
public class MyfcaMSERTopNDAOImpl implements MyfcaMSERTopNDAO {
	
	private static Logger logger = LoggerFactory.getLogger(TilesDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	

	@SuppressWarnings("unchecked")
	@Override
	public List<MyfcaMSERTopNDTO> getTopNByRole(InputRequest userRoleReq) {
		List<MyfcaMSERTopNDTO> result = new ArrayList<MyfcaMSERTopNDTO>();

		MyfcaMSERTopNDTO MyfcaMSERTopNDTO = null;
		try {
			final Query query = this.em.createNativeQuery(TOP_N_BY_ROLE, MyfcaMSERTopNDTO.class);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			MyfcaMSERTopNDTO = new MyfcaMSERTopNDTO();
			MyfcaMSERTopNDTO.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + MyfcaMSERTopNDTO);
			result.add(MyfcaMSERTopNDTO);
			
		} catch (final Exception ex) {
			MyfcaMSERTopNDTO = new MyfcaMSERTopNDTO();
			logger.error("error occured in findTilesListByRole", ex);
			MyfcaMSERTopNDTO.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(MyfcaMSERTopNDTO);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MyfcaMSERTopNDTO> getTopNByType(String type, int rows, String name, String period) {
		List<MyfcaMSERTopNDTO> result = new ArrayList<MyfcaMSERTopNDTO>();

		MyfcaMSERTopNDTO MyfcaMSERTopNDTO = null;
		try {
			if(name == null && period == null){
				final Query query = this.em.createNativeQuery(TOP_N_BY_TYPE, MyfcaMSERTopNDTO.class);
				query.setParameter(1, rows);
				query.setParameter(2, type);
				result = query.getResultList();
			}else if(name == null && period != null){
				final Query query = this.em.createNativeQuery(TOP_N_BY_TYPE_PERIOD, MyfcaMSERTopNDTO.class);
				query.setParameter(1, rows);
				query.setParameter(2, type);
				query.setParameter(3, period);
				result = query.getResultList();
			}else{
				final Query query = this.em.createNativeQuery(TOP_N_BY_TYPE_NAME_PERIOD, MyfcaMSERTopNDTO.class);
				query.setParameter(1, rows);
				query.setParameter(2, type);
				query.setParameter(3, name);
				query.setParameter(4, period);
				result = query.getResultList();
			}

		} catch (final NoResultException ex) {
			logger.info("result in else " + MyfcaMSERTopNDTO);
		} catch (final Exception ex) {
			logger.error("error occured in findTilesListByRole", ex);
		}
		return result;
	}

}
