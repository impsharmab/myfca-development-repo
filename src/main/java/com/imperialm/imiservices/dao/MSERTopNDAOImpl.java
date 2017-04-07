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


import com.imperialm.imiservices.dto.MSERTopNDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.util.IMIServicesUtil;

@Repository
public class MSERTopNDAOImpl implements MSERTopNDAO {
	
	private static Logger logger = LoggerFactory.getLogger(TilesDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	

	@SuppressWarnings("unchecked")
	@Override
	public List<MSERTopNDTO> getTopNByRole(InputRequest userRoleReq) {
		List<MSERTopNDTO> result = new ArrayList<MSERTopNDTO>();

		MSERTopNDTO MSERTopNDTO = null;
		try {
			final Query query = this.em.createNativeQuery(TOP_N_BY_ROLE, MSERTopNDTO.class);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			MSERTopNDTO = new MSERTopNDTO();
			MSERTopNDTO.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + MSERTopNDTO);
			result.add(MSERTopNDTO);
			
		} catch (final Exception ex) {
			MSERTopNDTO = new MSERTopNDTO();
			logger.error("error occured in findTilesListByRole", ex);
			MSERTopNDTO.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(MSERTopNDTO);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MSERTopNDTO> getTopNByType(String type, int rows, String name, String period) {
		List<MSERTopNDTO> result = new ArrayList<MSERTopNDTO>();

		MSERTopNDTO MSERTopNDTO = null;
		try {
			if(name == null || period == null){
			final Query query = this.em.createNativeQuery(TOP_N_BY_TYPE, MSERTopNDTO.class);
			query.setParameter(1, rows);
			query.setParameter(2, type);
			result = query.getResultList();
			}else{
				final Query query = this.em.createNativeQuery(TOP_N_BY_TYPE_NAME_PERIOD, MSERTopNDTO.class);
				query.setParameter(1, rows);
				query.setParameter(2, type);
				query.setParameter(3, name);
				query.setParameter(4, period);
				result = query.getResultList();
			}
			
		} catch (final NoResultException ex) {
			logger.info("result in else " + MSERTopNDTO);
		} catch (final Exception ex) {
			logger.error("error occured in findTilesListByRole", ex);
		}
		return result;
	}

}
