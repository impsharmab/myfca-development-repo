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

import com.imperialm.imiservices.dto.TTTATopNDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.util.IMIServicesUtil;

@Repository
public class TTTATopNDAOImpl implements TTTATopNDAO {
	private static Logger logger = LoggerFactory.getLogger(TilesDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	

	@SuppressWarnings("unchecked")
	@Override
	public List<TTTATopNDTO> getTopNByRole(InputRequest userRoleReq) {
		List<TTTATopNDTO> result = new ArrayList<TTTATopNDTO>();

		TTTATopNDTO TTTATopNDTO = null;
		try {
			final Query query = this.em.createNativeQuery(TOP_N_BY_ROLE, TTTATopNDTO.class);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			TTTATopNDTO = new TTTATopNDTO();
			TTTATopNDTO.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TTTATopNDTO);
			result.add(TTTATopNDTO);
			
		} catch (final Exception ex) {
			TTTATopNDTO = new TTTATopNDTO();
			logger.error("error occured in findTilesListByRole", ex);
			TTTATopNDTO.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TTTATopNDTO);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TTTATopNDTO> getTopNByType(String type, int rows) {
		List<TTTATopNDTO> result = new ArrayList<TTTATopNDTO>();

		TTTATopNDTO TTTATopNDTO = null;
		try {
			final Query query = this.em.createNativeQuery(TOP_N_BY_TYPE, TTTATopNDTO.class);
			query.setParameter(1, rows);
			query.setParameter(2, type);
			result = query.getResultList();
			
		} catch (final NoResultException ex) {
			TTTATopNDTO = new TTTATopNDTO();
			TTTATopNDTO.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + TTTATopNDTO);
			result.add(TTTATopNDTO);
			
		} catch (final Exception ex) {
			TTTATopNDTO = new TTTATopNDTO();
			logger.error("error occured in getTopNByType", ex);
			TTTATopNDTO.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TTTATopNDTO);
		}
		return result;
	}

}
