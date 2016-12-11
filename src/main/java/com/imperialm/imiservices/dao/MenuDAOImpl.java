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

import com.imperialm.imiservices.dto.MenuDTO;
import com.imperialm.imiservices.dto.MenuDetailDTO;
import com.imperialm.imiservices.dto.SubMenuDTO;
import com.imperialm.imiservices.dto.request.UserRoleRequest;
import com.imperialm.imiservices.model.response.MenuResponse;
import com.imperialm.imiservices.util.IMIServicesUtil;

/**
 *
 * @author Dheerajr
 *
 */
@Repository
public class MenuDAOImpl implements MenuDAO {

	private static final Logger logger = LoggerFactory.getLogger(MenuDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuDTO> findMenuByRole(final UserRoleRequest userRoleReq) {
		final List<MenuDTO> result = new ArrayList<>();
		MenuDTO menuDTO = null;
		MenuDetailDTO menuDetailDTO = null;
		List<MenuDetailDTO> menuDetails = null;
		SubMenuDTO subMenuDTO = null;
		List<SubMenuDTO> subMenus = null;
		String prevProgramCode = "";
		String preMenuName = "";
		try {
			final Query query = em.createNativeQuery(MENU_BY_ROLE, MenuResponse.class);
			query.setParameter(1, userRoleReq.getRoleID());

			final List<MenuResponse> menuRows = query.getResultList();
			for (final MenuResponse menuRow : menuRows) {

				if ("".equals(prevProgramCode)) {
					prevProgramCode = menuRow.getProgramCode();
					menuDTO = new MenuDTO(prevProgramCode, "");
					menuDetails = new ArrayList<>();
				} else if (!prevProgramCode.equals(menuRow.getProgramCode())) {
					prevProgramCode = menuRow.getProgramCode();
					menuDetailDTO.setSubmenus(subMenus);
					menuDetails.add(menuDetailDTO);
					menuDTO.setMenus(menuDetails);
					result.add(menuDTO);
					menuDTO = new MenuDTO(prevProgramCode, "");
					menuDetails = new ArrayList<>();
					preMenuName = "";
				}

				if ("".equals(preMenuName)) {
					preMenuName = menuRow.getMenuName();
					menuDetailDTO = new MenuDetailDTO();
					menuDetailDTO.setMenuName(menuRow.getMenuName());
					subMenus = new ArrayList<>();
				} else if (!preMenuName.equals(menuRow.getMenuName())) {
					menuDetailDTO.setSubmenus(subMenus);
					menuDetails.add(menuDetailDTO);
					preMenuName = menuRow.getMenuName();
					menuDetailDTO = new MenuDetailDTO();
					menuDetailDTO.setMenuName(menuRow.getMenuName());
					subMenus = new ArrayList<>();
				}
				subMenuDTO = new SubMenuDTO();
				subMenuDTO.setSubMenu(menuRow.getSubMenuName());

				subMenus.add(subMenuDTO);
			}
			if (!menuRows.isEmpty()) {
				menuDetailDTO.setSubmenus(subMenus);
				menuDetails.add(menuDetailDTO);
				menuDTO.setMenus(menuDetails);
				result.add(menuDTO);
			}

		} catch (final NoResultException ex) {
			menuDTO = new MenuDTO();
			menuDTO.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			result.add(menuDTO);
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in findMenuByRole", ex);
			menuDTO = new MenuDTO();
			menuDTO.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
			result.add(menuDTO);
		}
		return result;
	}

}
