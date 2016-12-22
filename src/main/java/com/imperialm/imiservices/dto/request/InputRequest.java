/**
 *
 */
package com.imperialm.imiservices.dto.request;

import java.io.Serializable;

/**
 * @author Dheerajr
 *
 */
public class InputRequest implements Serializable {

	private String userID;
	private Long roleID;
	private String password;
	private String territory;
	private Long tileID;

	public InputRequest() {
	}

	/**
	 * @param roleId2
	 */
	public InputRequest(final Long roleId2) {
		this.roleID = roleId2;
	}

	/**
	 * @param userID2
	 * @param roleId2
	 */
	public InputRequest(final String userID2, final Long roleId2, final String territory2, final Long tileID) {
		this.userID = userID2;
		this.roleID = roleId2;
		this.territory = territory2;
		this.tileID = tileID;
	}

	/**
	 * @param userID2
	 * @param roleId2
	 */
	public InputRequest(final String userID2, final Long roleId2) {
		this.userID = userID2;
		this.roleID = roleId2;
	}

	/**
	 * @param userID2
	 * @param password2
	 */
	public InputRequest(final String userID2, final String password2) {
		this.userID = userID2;
		this.password = password2;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return this.userID;
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
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
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
		return "InputRequest [userID=" + this.userID + ", roleID=" + this.roleID + ", password=" + this.password
				+ ", territory=" + this.territory + ", tileID=" + this.tileID + "]";
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
		result = (prime * result) + ((this.password == null) ? 0 : this.password.hashCode());
		result = (prime * result) + ((this.roleID == null) ? 0 : this.roleID.hashCode());
		result = (prime * result) + ((this.territory == null) ? 0 : this.territory.hashCode());
		result = (prime * result) + ((this.userID == null) ? 0 : this.userID.hashCode());
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
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final InputRequest other = (InputRequest) obj;
		if (this.password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!this.password.equals(other.password)) {
			return false;
		}
		if (this.roleID == null) {
			if (other.roleID != null) {
				return false;
			}
		} else if (!this.roleID.equals(other.roleID)) {
			return false;
		}
		if (this.territory == null) {
			if (other.territory != null) {
				return false;
			}
		} else if (!this.territory.equals(other.territory)) {
			return false;
		}
		if (this.userID == null) {
			if (other.userID != null) {
				return false;
			}
		} else if (!this.userID.equals(other.userID)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the territory
	 */
	public String getTerritory() {
		return this.territory;
	}

	/**
	 * @param territory
	 *            the territory to set
	 */
	public void setTerritory(final String territory) {
		this.territory = territory;
	}

	/**
	 * @return the tileID
	 */
	public Long getTileID() {
		return this.tileID;
	}

	/**
	 * @param tileID
	 *            the tileID to set
	 */
	public void setTileID(final Long tileID) {
		this.tileID = tileID;
	}

}
