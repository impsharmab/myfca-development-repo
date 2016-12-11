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

import com.imperialm.imiservices.dto.AttributeDTO;
import com.imperialm.imiservices.dto.DashboardDTO;
import com.imperialm.imiservices.dto.TileDTO;
import com.imperialm.imiservices.dto.request.UserRoleRequest;
import com.imperialm.imiservices.model.response.DashboardResponse;
import com.imperialm.imiservices.util.IMIServicesUtil;

/**
 *
 * @author Dheerajr
 *
 */
@Repository
public class DashboardDAOImpl implements DashboardDAO {

	private static final Logger logger = LoggerFactory.getLogger(DashboardDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<DashboardDTO> findTilesListByRole(final UserRoleRequest userRoleReq) {
		final List<DashboardDTO> result = new ArrayList<>();
		DashboardDTO dashboardDTO = null;
		TileDTO tileDTO = null;
		List<TileDTO> tiles = null;
		AttributeDTO attributeDTO = null;
		List<AttributeDTO> attributes = null;
		String prevProgramCode = "";
		String prevTileName = "";
		try {
			final Query query = em.createNativeQuery(DASH_TILE_LIST_BY_ROLE, DashboardResponse.class);
			query.setParameter(1, userRoleReq.getRoleID());
			query.setParameter(2, userRoleReq.getUserID());

			final List<DashboardResponse> rows = query.getResultList();
			for (final DashboardResponse dash : rows) {

				if ("".equals(prevProgramCode)) {
					prevProgramCode = dash.getProgramCode();
					dashboardDTO = new DashboardDTO(prevProgramCode, "");
					tiles = new ArrayList<>();
				} else if (!prevProgramCode.equals(dash.getProgramCode())) {
					prevProgramCode = dash.getProgramCode();
					tileDTO.setAttributes(attributes);
					tiles.add(tileDTO);
					dashboardDTO.setTiles(tiles);
					result.add(dashboardDTO);
					dashboardDTO = new DashboardDTO(prevProgramCode, "");
					tiles = new ArrayList<>();
					prevTileName = "";
				}

				if ("".equals(prevTileName)) {
					prevTileName = dash.getTileName();
					tileDTO = new TileDTO();
					tileDTO.setTileHeaderImage(dash.getTileHeaderImage());
					tileDTO.setTileImage(dash.getTileImage());
					tileDTO.setTileName(dash.getTileName());
					tileDTO.setTileOrder(dash.getTileOrder());
					tileDTO.setTileURL(dash.getUrl());
					attributes = new ArrayList<>();
				} else if (!prevTileName.equals(dash.getTileName())) {
					tileDTO.setAttributes(attributes);
					tiles.add(tileDTO);
					prevTileName = dash.getTileName();
					tileDTO = new TileDTO();
					tileDTO.setTileHeaderImage(dash.getTileHeaderImage());
					tileDTO.setTileImage(dash.getTileImage());
					tileDTO.setTileName(dash.getTileName());
					tileDTO.setTileOrder(dash.getTileOrder());
					tileDTO.setTileURL(dash.getUrl());
					attributes = new ArrayList<>();
				}
				attributeDTO = new AttributeDTO();
				attributeDTO.setName(dash.getAttributeName());
				attributeDTO.setOrder(dash.getAttributeOrder());
				attributeDTO.setType(dash.getFormat());
				attributeDTO.setValue(dash.getAttributeValue());
				attributes.add(attributeDTO);
			}
			if (!rows.isEmpty()) {
				tileDTO.setAttributes(attributes);
				tiles.add(tileDTO);
				dashboardDTO.setTiles(tiles);
				result.add(dashboardDTO);
			}

		} catch (final NoResultException ex) {
			dashboardDTO = new DashboardDTO();
			dashboardDTO.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			result.add(dashboardDTO);
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in findTilesListByRole", ex);
			dashboardDTO = new DashboardDTO();
			dashboardDTO.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(dashboardDTO);
		}
		return result;
	}

}
