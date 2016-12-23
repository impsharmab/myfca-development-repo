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

	@Id
	private String rNo;
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
	 * @return the subMenu
	 */
	public String getSubMenuName() {
		return this.subMenuName;
	}

	/**
	 * @param subMenu
	 *            the subMenu to set
	 */
	public void setSubMenuName(final String subMenu) {
		this.subMenuName = subMenu;
	}

	/**
	 * @return the rNo
	 */
	public String getrNo() {
		return rNo;
	}

	/**
	 * @param rNo the rNo to set
	 */
	public void setrNo(String rNo) {
		this.rNo = rNo;
	}

}
