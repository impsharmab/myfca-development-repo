/**
 *
 */
package com.imperialm.imiservices.model.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Dheerajr
 *
 */
@Entity
public class MenuResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4474783130283863634L;

	@Id
	private Long rNo;
	private String programCode;
	private String menuName;
	private String subMenuName;

	public MenuResponse() {
	}

	public MenuResponse(final String programCode, final String menuName, final String subMenu) {
		super();
		this.programCode = programCode;
		this.menuName = menuName;
		this.subMenuName = subMenu;
	}

	/**
	 * @return the programCode
	 */
	public String getProgramCode() {
		return programCode;
	}

	/**
	 * @param programCode
	 *            the programCode to set
	 */
	public void setProgramCode(final String programCode) {
		this.programCode = programCode;
	}

	/**
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * @param menuName
	 *            the menuName to set
	 */
	public void setMenuName(final String menuName) {
		this.menuName = menuName;
	}

	/**
	 * @return the subMenu
	 */
	public String getSubMenuName() {
		return subMenuName;
	}

	/**
	 * @param subMenu
	 *            the subMenu to set
	 */
	public void setSubMenuName(final String subMenu) {
		this.subMenuName = subMenu;
	}

}
