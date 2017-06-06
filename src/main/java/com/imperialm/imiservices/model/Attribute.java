package com.imperialm.imiservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the attributes database table.
 *
 */
@Entity
@Table(name = "attributes")
@NamedQueries({ @NamedQuery(name = "Attribute.findAll", query = "SELECT a FROM Attribute a where a.delFlag = 'N' ") })
public class Attribute implements Serializable {
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

	private String type;

	@JsonIgnore
	private String updatedBy;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@Transient
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	public Attribute() {
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

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(final String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(final Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Attribute [id=" + id + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", delFlag="
				+ delFlag + ", name=" + name + ", type=" + type + ", updatedBy=" + updatedBy + ", updatedDate="
				+ updatedDate + ", value=" + value + "]";
	}

}