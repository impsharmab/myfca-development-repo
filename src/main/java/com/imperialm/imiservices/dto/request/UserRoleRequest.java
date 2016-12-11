/**
 *
 */
package com.imperialm.imiservices.dto.request;

import java.io.Serializable;

/**
 * @author Dheerajr
 *
 */
public class UserRoleRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2485285474957736493L;

	private String userID;
	private Long roleID;
	private String password;

	public UserRoleRequest() {
	}

	/**
	 * @param roleId2
	 */
	public UserRoleRequest(final Long roleId2) {
		this.roleID = roleId2;
	}

	/**
	 * @param userID2
	 * @param roleId2
	 */
	public UserRoleRequest(final String userID2, final Long roleId2) {
		this.userID = userID2;
		this.roleID = roleId2;
	}

	/**
	 * @param userID2
	 * @param password2
	 */
	public UserRoleRequest(final String userID2, final String password2) {
		this.userID = userID2;
		this.password = password2;
	}

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
	 * @return the roleID
	 */
	public Long getRoleID() {
		return roleID;
	}

	/**
	 * @param roleID
	 *            the roleID to set
	 */
	public void setRoleID(final Long roleID) {
		this.roleID = roleID;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserRoleRequest [userID=" + userID + ", roleID=" + roleID + ", password=" + password + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((password == null) ? 0 : password.hashCode());
		result = (prime * result) + ((roleID == null) ? 0 : roleID.hashCode());
		result = (prime * result) + ((userID == null) ? 0 : userID.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final UserRoleRequest other = (UserRoleRequest) obj;
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (roleID == null) {
			if (other.roleID != null) {
				return false;
			}
		} else if (!roleID.equals(other.roleID)) {
			return false;
		}
		if (userID == null) {
			if (other.userID != null) {
				return false;
			}
		} else if (!userID.equals(other.userID)) {
			return false;
		}
		return true;
	}

}
