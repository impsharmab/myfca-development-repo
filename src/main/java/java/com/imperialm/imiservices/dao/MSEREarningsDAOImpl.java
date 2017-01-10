package com.imperialm.imiservices.dao;

import java.io.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.imperialm.imiservices.dto.MSEREarningsDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.model.TileAttribute1;
import com.imperialm.imiservices.model.response.BannerResponse;
import com.imperialm.imiservices.model.response.EarningsResponse;
import com.imperialm.imiservices.util.IMIServicesUtil;

@Repository
public class MSEREarningsDAOImpl implements MSEREarningsDAO {
	private static Logger logger = LoggerFactory.getLogger(BannerDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<MSEREarningsDTO> getEarningsByRole(InputRequest userRoleReq) {
		// TODO Auto-generated method stub
		
		
		List<MSEREarningsDTO> result = new ArrayList<MSEREarningsDTO>();

		MSEREarningsDTO MSEREarningsDTO = null;

		try {
			final Query query = this.em.createNativeQuery(EARNING_BY_ROLE, EarningsResponse.class);
			List<EarningsResponse> EarningsRows = query.getResultList();
			int a = EarningsRows.size();
			for (EarningsResponse Earning : EarningsRows) {
				MSEREarningsDTO MSEREarningsDTOTemp = new MSEREarningsDTO();
				MSEREarningsDTOTemp.setMvp(Earning.getMvp());
				MSEREarningsDTOTemp.setExpressLane(Earning.getExpressLane());
				MSEREarningsDTOTemp.setMagnetiMarelli(Earning.getMagnetiMarelli());
				MSEREarningsDTOTemp.setMoparParts(Earning.getMoparParts());
				MSEREarningsDTOTemp.setPartsCounter(Earning.getPartsCounter());
				MSEREarningsDTOTemp.setTerritory(Earning.getTerritory());
				MSEREarningsDTOTemp.setuConnect(Earning.getuConnect());
				MSEREarningsDTOTemp.setWiAdvisor(Earning.getWiAdvisor());
				result.add(MSEREarningsDTOTemp);
			}
			
		} catch (final NoResultException ex) {
			MSEREarningsDTO = new MSEREarningsDTO();
			MSEREarningsDTO.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			result.add(MSEREarningsDTO);
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getBannersByRole", ex);
			MSEREarningsDTO = new MSEREarningsDTO();
			MSEREarningsDTO.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(MSEREarningsDTO);
		}
		return result;
	}

}
