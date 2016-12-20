/**
 *
 */
package com.imperialm.imiservices.model.response;

import java.io.Serializable;
import java.sql.Clob;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Dheerajr
 *
 */
@Entity
public class TileReponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1570093722427873019L;

	@Id
	private Long rNo;
	private Clob attributes;
	private Clob datatable;

	public TileReponse() {
	}

	public TileReponse(Clob attributes, Clob dataTable) {
		this.attributes = attributes;
		this.datatable = dataTable;
	}

	/**
	 * @return the attributes
	 */
	public Clob getAttributes() {
		return this.attributes;
	}

	/**
	 * @param attributes
	 *            the attributes to set
	 */
	public void setAttributes(Clob attributes) {
		this.attributes = attributes;
	}

	/**
	 * @return the datatable
	 */
	public Clob getDatatable() {
		return this.datatable;
	}

	/**
	 * @param datatable
	 *            the datatable to set
	 */
	public void setDatatable(Clob datatable) {
		this.datatable = datatable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TileReponse [rNo=" + this.rNo + ", attributes=" + this.attributes + ", datatable=" + this.datatable
				+ "]";
	}

}
