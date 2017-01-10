package com.imperialm.imiservices.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the programs database table.
 *
 */
@Entity
@Table(name = "programs")
@NamedQuery(name = "Program.findAll", query = "SELECT p FROM Program p where p.delFlag = 'N' ")
public class Program implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	@Id
	private String code;

	@JsonIgnore
	private String createdBy;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@JsonIgnore
	private String delFlag;

	private String name;

	@JsonIgnore
	private String updatedBy;

	@JsonIgnore
	private String updatedDate;

	public Program() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(final String delFlag) {
		this.delFlag = delFlag;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(final String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(final String updatedDate) {
		this.updatedDate = updatedDate;
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
		result = (prime * result) + ((code == null) ? 0 : code.hashCode());
		result = (prime * result) + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = (prime * result) + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = (prime * result) + ((delFlag == null) ? 0 : delFlag.hashCode());
		result = (prime * result) + id;
		result = (prime * result) + ((name == null) ? 0 : name.hashCode());
		result = (prime * result) + ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = (prime * result) + ((updatedDate == null) ? 0 : updatedDate.hashCode());
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
		final Program other = (Program) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (createdBy == null) {
			if (other.createdBy != null) {
				return false;
			}
		} else if (!createdBy.equals(other.createdBy)) {
			return false;
		}
		if (createdDate == null) {
			if (other.createdDate != null) {
				return false;
			}
		} else if (!createdDate.equals(other.createdDate)) {
			return false;
		}
		if (delFlag == null) {
			if (other.delFlag != null) {
				return false;
			}
		} else if (!delFlag.equals(other.delFlag)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (updatedBy == null) {
			if (other.updatedBy != null) {
				return false;
			}
		} else if (!updatedBy.equals(other.updatedBy)) {
			return false;
		}
		if (updatedDate == null) {
			if (other.updatedDate != null) {
				return false;
			}
		} else if (!updatedDate.equals(other.updatedDate)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Program [id=" + id + ", code=" + code + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", delFlag=" + delFlag + ", name=" + name + ", updatedBy=" + updatedBy + ", updatedDate="
				+ updatedDate + "]";
	}

}