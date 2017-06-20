package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.CustomerFirstDetailsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerFirstDetailsDTOImpl implements CustomerFirstDetailsDAO{

	private static Logger logger = LoggerFactory.getLogger(CustomerFirstDetailsDTOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerFirstDetailsDTO> getCustomerFirstByParentAndToggle(List<String> territory, String toggle) {
		List<CustomerFirstDetailsDTO> result = new ArrayList<CustomerFirstDetailsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_PARENT_AND_TOGGLE, CustomerFirstDetailsDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getCustomerFirstByParentAndToggle", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerFirstDetailsDTO> getCustomerFirstByChildAndToggle(List<String> territory, String toggle) {
		List<CustomerFirstDetailsDTO> result = new ArrayList<CustomerFirstDetailsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_CHILD_AND_TOGGLE, CustomerFirstDetailsDTO.class);
			query.setParameter(0, territory);
			query.setParameter(1, toggle);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getCustomerFirstByChildAndToggle", ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerFirstDetailsDTO> getCustomerFirstDetailsByDealerCodeAndToggle(String dealerCode,
			String toggle) {
		List<CustomerFirstDetailsDTO> result = new ArrayList<CustomerFirstDetailsDTO>();

		try {
			final Query query = this.em.createNativeQuery(SELECT_BY_DEALER_TOGGLE, CustomerFirstDetailsDTO.class);
			query.setParameter(0, dealerCode);
			query.setParameter(1, toggle);
			result = query.getResultList();
		} catch (final NoResultException ex) {
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getCustomerFirstDetailsByDealerCodeAndToggle", ex);
		}
		return result;
	}
}
