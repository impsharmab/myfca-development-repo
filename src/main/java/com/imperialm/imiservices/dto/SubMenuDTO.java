/**
 *
 */
package com.imperialm.imiservices.dto;

import java.io.Serializable;

/**
 * @author Dheerajr
 *
 */
public class SubMenuDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3488080152377050614L;
	private String subMenu;

	/**
	 * @return the subMenu
	 */
	public String getSubMenu() {
		return this.subMenu;
	}

	/**
	 * @param subMenu
	 *            the subMenu to set
	 */
	public void setSubMenu(final String subMenu) {
		this.subMenu = subMenu;
	}

	public SubMenuDTO() {
	}

	public SubMenuDTO(final String subMenu) {
		super();
		this.subMenu = subMenu;
	}

}
