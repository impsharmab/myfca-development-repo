/**
 *
 */
package com.imperialm.imiservices.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dheerajr
 *
 */
public class UserProfileDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8354816308554666098L;
	private String error;
	private String userID;
	private String name;
	private String email;
	private AccessDTO access;
	private List<MenuDTO> menus;
	private List<BannersDTO> banners;
	private List<DashboardDTO> dashboard;

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID
	 *            the userID to set
	 */
	public void setUserID(final String userID) {
		this.userID = userID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @return the access
	 */
	public AccessDTO getAccess() {
		return access;
	}

	/**
	 * @param access
	 *            the access to set
	 */
	public void setAccess(final AccessDTO access) {
		this.access = access;
	}

	/**
	 * @return the menus
	 */
	public List<MenuDTO> getMenus() {
		return menus;
	}

	/**
	 * @param menus
	 *            the menus to set
	 */
	public void setMenus(final List<MenuDTO> menus) {
		this.menus = menus;
	}

	/**
	 * @return the banners
	 */
	public List<BannersDTO> getBanners() {
		return banners;
	}

	/**
	 * @param banners
	 *            the banners to set
	 */
	public void setBanners(final List<BannersDTO> banners) {
		this.banners = banners;
	}

	/**
	 * @return the dashboard
	 */
	public List<DashboardDTO> getDashboard() {
		return dashboard;
	}

	/**
	 * @param dashboard
	 *            the dashboard to set
	 */
	public void setDashboard(final List<DashboardDTO> dashboard) {
		this.dashboard = dashboard;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(final String error) {
		this.error = error;
	}

}
