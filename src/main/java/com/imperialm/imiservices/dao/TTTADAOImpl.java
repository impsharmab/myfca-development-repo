package com.imperialm.imiservices.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imperialm.imiservices.dto.TTTADTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.model.response.TTTAResponse;
import com.imperialm.imiservices.util.IMIServicesUtil;

public class TTTADAOImpl implements TTTADAO {
	private static Logger logger = LoggerFactory.getLogger(BannerDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<TTTADTO> getTTTAByRole(InputRequest userRoleReq) {
		// TODO Auto-generated method stub
		
		
		List<TTTADTO> result = new ArrayList<TTTADTO>();

		TTTADTO TTTADTO = null;

		try {
			final Query query = this.em.createNativeQuery(TTTA_BY_ROLE, TTTAResponse.class);
			List<TTTAResponse> TTTARows = query.getResultList();
			for (TTTAResponse TTTA : TTTARows) {
				TTTADTO TTTADTOTemp = new TTTADTO();
				TTTADTOTemp.setEnrollmentStatus(TTTA.getEnrollmentStatus());
				TTTADTOTemp.setGroupA(TTTA.getGroupA());
				TTTADTOTemp.setGroupB(TTTA.getGroupB());
				TTTADTOTemp.setGroupC(TTTA.getGroupC());
				TTTADTOTemp.setGroupD(TTTA.getGroupD());
				TTTADTOTemp.setGroupE(TTTA.getGroupE());
				TTTADTOTemp.setTerritory(TTTA.getTerritory());
				result.add(TTTADTOTemp);
			}
			
		} catch (final NoResultException ex) {
			TTTADTO = new TTTADTO();
			TTTADTO.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			result.add(TTTADTO);
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getBannersByRole", ex);
			TTTADTO = new TTTADTO();
			TTTADTO.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(TTTADTO);
		}
		return result;
	}

}
