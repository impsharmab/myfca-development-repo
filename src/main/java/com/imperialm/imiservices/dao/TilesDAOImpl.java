/**
 *
 */
package com.imperialm.imiservices.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.imperialm.imiservices.dto.TileDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.model.response.TileReponse;
import com.imperialm.imiservices.util.IMIServicesUtil;

/**
 * @author Dheerajr
 *
 */
@Repository
public class TilesDAOImpl implements TilesDAO {

	private static final Logger logger = LoggerFactory.getLogger(MenuDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.imperialm.imiservices.dao.TilesDAO#findTilesByUserRoleAndTerritory(
	 * com.imperialm.imiservices.dto.request.UserRoleRequest)
	 */
	@Override
	public TileDTO findTilesByUserRoleAndTerritory(InputRequest roleRequest) {
		final TileDTO tilesDTO = new TileDTO();
		try {
			final Query query = this.em.createNativeQuery(TILES_BY_ROLES_AND_PROGRAM, TileReponse.class);
			query.setParameter(1, roleRequest.getUserID());
			query.setParameter(2, roleRequest.getTerritory());
			query.setParameter(3, roleRequest.getTileID());

			final TileReponse row = (TileReponse) query.getSingleResult();
			tilesDTO.setAttributes(IMIServicesUtil.buildString(row.getAttributes()));
			tilesDTO.setDatatable(IMIServicesUtil.buildString(row.getDatatable()));

		} catch (final NoResultException ex) {

			tilesDTO.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + tilesDTO);
		} catch (final Exception ex) {
			logger.error("error occured in findTilesListByRole", ex);
			tilesDTO.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
		}
		return tilesDTO;
	}

}
