package com.imperialm.imiservices.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.imperialm.imiservices.dto.AccessDTO;
import com.imperialm.imiservices.dto.BannersDTO;
import com.imperialm.imiservices.dto.DashboardDTO;
import com.imperialm.imiservices.dto.MenuDTO;
import com.imperialm.imiservices.dto.RoleDTO;
import com.imperialm.imiservices.dto.UserProfileDTO;
import com.imperialm.imiservices.dto.request.InputRequest;
import com.imperialm.imiservices.model.response.UserProfileResponse;
import com.imperialm.imiservices.util.IMIServicesUtil;

/**
 *
 * @author Dheerajr
 *
 */
@Repository
public class UserProfileDAOImpl implements UserProfileDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserProfileDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private BannerDAO bannerDAO;

	@Autowired
	private MenuDAO menuDAO;

	@Autowired
	private DashboardDAO dashboardDAO;

	@Override
	public UserProfileDTO getUserProfile(final InputRequest userRoleReq) {
		UserProfileDTO result = new UserProfileDTO();
		List<AccessDTO> accesses = null;
		List<RoleDTO> roles = null;

		final List<BannersDTO> banners = new ArrayList<>();
		final List<DashboardDTO> dashboard = new ArrayList<>();
		final List<MenuDTO> menus = new ArrayList<>();

		AccessDTO access = null;
		RoleDTO role = null;

		String prevProgramCode = "";
		String preRoleName = "";

		try {
			final Query query = this.em.createNativeQuery(USER_PROFILE_DAO, UserProfileResponse.class);
			query.setParameter(1, userRoleReq.getUserID());
			query.setParameter(2, userRoleReq.getPassword());

			final List<UserProfileResponse> userResponseRows = query.getResultList();
			for (final UserProfileResponse userResponse : userResponseRows) {
				if ("".equals(prevProgramCode)) {
					result.setUserID(userResponse.getUserId());
					result.setName(userResponse.getName());
					result.setEmail(userResponse.getEmail());
					prevProgramCode = userResponse.getProgramCode();
					access = new AccessDTO(prevProgramCode);
					accesses = new ArrayList<>();
					roles = new ArrayList<>();
				} else if (!prevProgramCode.equals(userResponse.getProgramCode())) {
					prevProgramCode = userResponse.getProgramCode();
					access.setRoles(roles);
					accesses.add(access);
					access = new AccessDTO(prevProgramCode);
					roles = new ArrayList<>();
					preRoleName = "";
				}

				if ("".equals(preRoleName)) {
					preRoleName = userResponse.getRoleName();
					role = new RoleDTO();
				} else if (!preRoleName.equals(userResponse.getRoleName())) {
					preRoleName = userResponse.getRoleName();
					role = new RoleDTO();
				}
				role.setRoleID(userResponse.getRoleId());
				role.setRoleName(userResponse.getRoleName());
				roles.add(role);
			}
			if (!userResponseRows.isEmpty()) {
				access.setRoles(roles);
				accesses.add(access);
				result.setAccess(access);
			}
			for (final AccessDTO dto : accesses) {
				for (final RoleDTO inputRole : dto.getRoles()) {
					final InputRequest inputReq = new InputRequest();
					inputReq.setRoleID(inputRole.getRoleID());
					inputReq.setUserID(userRoleReq.getUserID());
					menus.addAll(this.menuDAO.findMenuByRole(inputReq));
					banners.addAll(this.bannerDAO.getBannersByRole(inputReq));
					dashboard.addAll(this.dashboardDAO.findTilesByRole(inputReq));
				}
			}
			result.setMenus(menus);
			result.setBanners(banners);
			result.setDashboard(dashboard);
		} catch (final NoResultException ex) {
			result = new UserProfileDTO();
			result.setError(IMIServicesUtil.prepareJson("Info", "No Results found"));
			logger.info("result in else " + result);
		} catch (final Exception ex) {
			logger.error("error occured in getUserProfile", ex);
			result = new UserProfileDTO();
			result.setError(IMIServicesUtil.prepareJson("error", "error Occured" + ex.getMessage()));
		}
		return result;
	}

}
