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
public class AccessDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6546491317884943781L;
	private String programCode;
	private List<RoleDTO> roles;

	public AccessDTO(final String programCode) {
		this.programCode = programCode;
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
	 * @return the roles
	 */
	public List<RoleDTO> getRoles() {
		return this.roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(final List<RoleDTO> roles) {
		this.roles = roles;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccessDTO [programCode=" + this.programCode + ", roles=" + this.roles + "]";
	}

}
