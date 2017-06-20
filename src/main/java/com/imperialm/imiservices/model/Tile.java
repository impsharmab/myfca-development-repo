package com.imperialm.imiservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the tiles database table.
 *
 */
@Entity
@Table(name = "tiles")
@NamedQueries({ @NamedQuery(name = "Tile.findAll", query = "SELECT t FROM Tile t where t.delFlag = 'N' "),
		@NamedQuery(name = "Tile.findById", query = "SELECT t FROM Tile t where t.id = :tileId and t.delFlag = 'N' ") })
public class Tile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonIgnore
	private String createdBy;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@JsonIgnore
	private String delFlag;

	private String name;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@JsonIgnore
	private String updatedBy;

	/*
	 * @OneToMany
	 * 
	 * @JoinTable(name="tileattributes", joinColumns={@JoinColumn(name="TileID",
	 * referencedColumnName="id")}) private List<Tileattribute> tileAttributes;
	 */

	public Tile() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
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

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(final Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(final String updatedBy) {
		this.updatedBy = updatedBy;
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
		result = (prime * result) + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = (prime * result) + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = (prime * result) + ((delFlag == null) ? 0 : delFlag.hashCode());
		result = (prime * result) + id;
		result = (prime * result) + ((name == null) ? 0 : name.hashCode());
		result = (prime * result) + ((updateDate == null) ? 0 : updateDate.hashCode());
		result = (prime * result) + ((updatedBy == null) ? 0 : updatedBy.hashCode());
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
		final Tile other = (Tile) obj;
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
		if (updateDate == null) {
			if (other.updateDate != null) {
				return false;
			}
		} else if (!updateDate.equals(other.updateDate)) {
			return false;
		}
		if (updatedBy == null) {
			if (other.updatedBy != null) {
				return false;
			}
		} else if (!updatedBy.equals(other.updatedBy)) {
			return false;
		}
		return true;
	}

}