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
public class MenuDTO implements Serializable {

	private String programCode;
	private String error;
	private List<MenuDetailDTO> menus;

	public MenuDTO() {
	}

	public MenuDTO(final String programCode, final String error) {
		super();
		this.programCode = programCode;
		this.error = error;
	}

	public MenuDTO(final String programCode, final List<MenuDetailDTO> menus) {
		super();
		this.programCode = programCode;
		this.menus = menus;
	}

	/**
	 * @return the programCode
	 */
	public String getProgramCode() {
		return this.programCode;
	}

	/**
	 * @param programCode
	 *            the programCode to set
	 */
	public void setProgramCode(final String programCode) {
		this.programCode = programCode;
	}

	/**
	 * @return the menus
	 */
	public List<MenuDetailDTO> getMenus() {
		return this.menus;
	}

	/**
	 * @param menus
	 *            the menus to set
	 */
	public void setMenus(final List<MenuDetailDTO> menus) {
		this.menus = menus;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return this.error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(final String error) {
		this.error = error;
	}

}
