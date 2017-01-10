package com.imperialm.imiservices.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Dheerajr
 *
 */

/**
 * The persistent class for the dashboard database table.
 *
 */

@Entity
@Table(name = "Dashboard")
@NamedQueries({ @NamedQuery(name = "Dashboard.findAll", query = "SELECT d FROM Dashboard d where d.delFlag = 'N' "),
		@NamedQuery(name = "Dashboard.findAllByRole", query = "SELECT distinct d FROM Dashboard d "
				+ " where d.role.id = :roleId and d.delFlag = 'N'") })
@NamedNativeQueries({
		@NamedNativeQuery(name = "Dashboard.TilesByRole", query = "SELECT DISTINCT  d.programcode,  tile.name 'TileName', "
				+ " d.TileOrder,   d.TileHeaderImage,  d.TileImage,  attribute.name 'AttributeName', "
				+ " tileattribute.value 'AttributeValue',  tileattribute.AttributeOrder, "
				+ " attribute.type 'Format'  FROM Dashboard d  JOIN tileattributes tileattribute "
				+ " ON tileattribute.tileid = d.tileid  JOIN attributes attribute "
				+ " ON tileattribute.attributeid = attribute.id  JOIN tiles tile  ON tile.id = d.tileid "
				+ " AND tile.id = tileattribute.tileid  AND tileattribute.roleid = d.roleid "
				+ " AND tileattribute.attributeid = attribute.id  WHERE d.roleid = ? "
				+ " AND tileattribute.delFlag = 'N'  AND attribute.delFlag = 'N'  AND d.delFlag = 'N' "
				+ " AND tileattribute.delFlag = 'N' order by d.TileOrder, tileattribute.AttributeOrder   for json auto ") })

public class Dashboard implements Serializable {
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

	private String tileHeaderImage;

	private String tileImage;

	private int tileOrder;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@JsonIgnore
	private String updatedBy;

	private String url;

	@OneToMany
	@JoinColumns({ @JoinColumn(name = "TileId") })
	private List<Tileattribute> tileAttributes;

	// bi-directional many-to-one association to Program
	@ManyToOne
	@JoinColumn(name = "ProgramCode")
	private Program program;

	// bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name = "RoleId")
	private Role role;

	public Dashboard() {
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

	public String getTileHeaderImage() {
		return this.tileHeaderImage;
	}

	public void setTileHeaderImage(final String tileHeaderImage) {
		this.tileHeaderImage = tileHeaderImage;
	}

	public String getTileImage() {
		return this.tileImage;
	}

	public void setTileImage(final String tileImage) {
		this.tileImage = tileImage;
	}

	public int getTileOrder() {
		return this.tileOrder;
	}

	public void setTileOrder(final int tileOrder) {
		this.tileOrder = tileOrder;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public Program getProgram() {
		return this.program;
	}

	public void setProgram(final Program program) {
		this.program = program;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(final Role role) {
		this.role = role;
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
		result = (prime * result) + ((program == null) ? 0 : program.hashCode());
		result = (prime * result) + ((role == null) ? 0 : role.hashCode());
		result = (prime * result) + ((tileHeaderImage == null) ? 0 : tileHeaderImage.hashCode());
		result = (prime * result) + ((tileImage == null) ? 0 : tileImage.hashCode());
		result = (prime * result) + tileOrder;
		result = (prime * result) + ((updateDate == null) ? 0 : updateDate.hashCode());
		result = (prime * result) + ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = (prime * result) + ((url == null) ? 0 : url.hashCode());
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
		final Dashboard other = (Dashboard) obj;
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
		if (program == null) {
			if (other.program != null) {
				return false;
			}
		} else if (!program.equals(other.program)) {
			return false;
		}
		if (role == null) {
			if (other.role != null) {
				return false;
			}
		} else if (!role.equals(other.role)) {
			return false;
		}
		if (tileHeaderImage == null) {
			if (other.tileHeaderImage != null) {
				return false;
			}
		} else if (!tileHeaderImage.equals(other.tileHeaderImage)) {
			return false;
		}
		if (tileImage == null) {
			if (other.tileImage != null) {
				return false;
			}
		} else if (!tileImage.equals(other.tileImage)) {
			return false;
		}
		if (tileOrder != other.tileOrder) {
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
		if (url == null) {
			if (other.url != null) {
				return false;
			}
		} else if (!url.equals(other.url)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the tileAttributes
	 */
	public List<Tileattribute> getTileAttributes() {
		return tileAttributes;
	}

	/**
	 * @param tileAttributes
	 *            the tileAttributes to set
	 */
	public void setTileAttributes(final List<Tileattribute> tileAttributes) {
		this.tileAttributes = tileAttributes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dashboard [id=" + id + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", delFlag="
				+ delFlag + ", tileHeaderImage=" + tileHeaderImage + ", tileImage=" + tileImage + ", tileOrder="
				+ tileOrder + ", updateDate=" + updateDate + ", updatedBy=" + updatedBy + ", url=" + url
				+ ", tileAttributes=" + tileAttributes + ", program=" + program + ", role=" + role + "]";
	}

}