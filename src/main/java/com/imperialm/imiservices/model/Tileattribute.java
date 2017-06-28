package com.imperialm.imiservices.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tileattributes database table.
 *
 */
@Entity
@Table(name = "tileattributes")
@NamedQuery(name = "Tileattribute.findAll", query = "SELECT t FROM Tileattribute t where t.delFlag = 'N' ")
public class Tileattribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int attributeOrder;

	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	private String delFlag;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	private String updatedBy;

	private String value;

	// bi-directional many-to-one association to Tile
	@ManyToOne
	@JoinColumn(name = "TileId")
	private Tile tile;

	// bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name = "RoleId")
	private Role role;

	// bi-directional many-to-one association to Attribute
	@OneToMany
	@JoinColumn(name = "AttributeID")
	private List<Attribute> attribute;

	public Tileattribute() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public int getAttributeOrder() {
		return this.attributeOrder;
	}

	public void setAttributeOrder(final int attributeOrder) {
		this.attributeOrder = attributeOrder;
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

	public Tile getTile() {
		return this.tile;
	}

	public void setTile(final Tile tile) {
		this.tile = tile;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(final Role role) {
		this.role = role;
	}

	public List<Attribute> getAttribute() {
		return this.attribute;
	}

	public void setAttribute(final List<Attribute> attribute) {
		this.attribute = attribute;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tileattribute [id=" + id + ", attributeOrder=" + attributeOrder + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", delFlag=" + delFlag + ", updateDate=" + updateDate
				+ ", updatedBy=" + updatedBy + ", value=" + value + ", tile=" + tile + ", role=" + role + ", attribute="
				+ attribute + "]";
	}

}