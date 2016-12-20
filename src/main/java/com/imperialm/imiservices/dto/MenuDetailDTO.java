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
public class MenuDetailDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7467108186425848159L;
	private String menuName;
	private List<SubMenuDTO> submenus;

	public MenuDetailDTO() {
	}

	public MenuDetailDTO(final String menuName, final List<SubMenuDTO> submenus) {
		super();
		this.menuName = menuName;
		this.submenus = submenus;
	}

	/**
	 * @return the menuName
	 */
	public String getMenuName() {
		return this.menuName;
	}

	/**
	 * @param menuName
	 *            the menuName to set
	 */
	public void setMenuName(final String menuName) {
		this.menuName = menuName;
	}

	/**
	 * @return the submenus
	 */
	public List<SubMenuDTO> getSubmenus() {
		return this.submenus;
	}

	/**
	 * @param submenus
	 *            the submenus to set
	 */
	public void setSubmenus(final List<SubMenuDTO> submenus) {
		this.submenus = submenus;
	}

}
