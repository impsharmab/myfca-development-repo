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
public class UserProfileResponse implements Serializable {

	@Id
	private Long rNo;
	private String userId;
	private String name;
	private String email;
	private String programCode;
	private Long roleId;
	private String roleName;
	private Long roleLevel;

	public UserProfileResponse() {
	}

	/**
	 * @return the rNo
	 */
	public Long getrNo() {
		return this.rNo;
	}

	/**
	 * @param rNo
	 *            the rNo to set
	 */
	public void setrNo(final Long rNo) {
		this.rNo = rNo;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(final String userId) {
		this.userId = userId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
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
		return this.email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(final String email) {
		this.email = email;
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
	 * @return the roleId
	 */
	public Long getRoleId() {
		return this.roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(final Long roleId) {
		this.roleId = roleId;
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

	/**
	 * @return the roleLevel
	 */
	public Long getRoleLevel() {
		return this.roleLevel;
	}

	/**
	 * @param roleLevel
	 *            the roleLevel to set
	 */
	public void setRoleLevel(final Long roleLevel) {
		this.roleLevel = roleLevel;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserProfileResponse [rNo=" + this.rNo + ", userId=" + this.userId + ", name=" + this.name + ", email="
				+ this.email + ", programCode=" + this.programCode + ", roleId=" + this.roleId + ", roleName="
				+ this.roleName + ", roleLevel=" + this.roleLevel + "]";
	}

}
