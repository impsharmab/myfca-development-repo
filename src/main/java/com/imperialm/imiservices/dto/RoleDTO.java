/**
 *
 */
package com.imperialm.imiservices.dto;

import java.io.Serializable;

/**
 * @author Dheerajr
 *
 */
public class RoleDTO implements Serializable {

	private Long roleID;
	private String roleName;

	/**
	 * @return the roleID
	 */
	public Long getRoleID() {
		return this.roleID;
	}

	/**
	 * @param roleID
	 *            the roleID to set
	 */
	public void setRoleID(final Long roleID) {
		this.roleID = roleID;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return this.roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(final String roleName) {
		this.roleName = roleName;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RoleDTO [roleID=" + this.roleID + ", roleName=" + this.roleName + "]";
	}

}
