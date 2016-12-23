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
public class TileReponse implements Serializable {

	@Id
	private Long rNo;
	private String attributes;
	private String datatable;

	public TileReponse() {
	}

	public TileReponse(final String attributes, final String dataTable) {
		this.attributes = attributes;
		this.datatable = dataTable;
	}

	/**
	 * @return the attributes
	 */
	public String getAttributes() {
		return this.attributes;
	}

	/**
	 * @param attributes
	 *            the attributes to set
	 */
	public void setAttributes(final String attributes) {
		this.attributes = attributes;
	}

	/**
	 * @return the datatable
	 */
	public String getDatatable() {
		return this.datatable;
	}

	/**
	 * @param datatable
	 *            the datatable to set
	 */
	public void setDatatable(final String datatable) {
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
