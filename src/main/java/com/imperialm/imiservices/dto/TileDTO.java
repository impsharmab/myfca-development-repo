/**
 *
 */
package com.imperialm.imiservices.dto;

import java.io.Serializable;
import java.util.List;

import com.imperialm.imiservices.model.TileAttribute1;
import com.imperialm.imiservices.model.TileDataTable;

/**
 * @author Dheerajr
 *
 */
public class TileDTO implements Serializable {

	private String error = "";
	private String tileName;
	private String tileHeaderImage;
	private String tileImage;
	private String tileURL;
	private Integer tileOrder;
	private List<TileAttribute1> attributes;
	private List<TileDataTable> datatable;

	public TileDTO(final String tileName, final String tileHeaderImage, final String tileImage, final String tileURL,
			final Integer tileOrder, final List<TileAttribute1> attributes, final List<TileDataTable> datatable) {
		super();
		this.tileName = tileName;
		this.tileHeaderImage = tileHeaderImage;
		this.tileImage = tileImage;
		this.tileURL = tileURL;
		this.tileOrder = tileOrder;
		this.attributes = attributes;
		this.setDatatable(datatable);
	}

	public TileDTO() {
	}

	public TileDTO(final String error) {
		this.error = error;
	}

	public TileDTO(final String tileName, final List<TileAttribute1> attributes) {
		this.tileName = tileName;
		this.attributes = attributes;
	}

	/**
	 * @return the tileName
	 */
	public String getTileName() {
		return this.tileName;
	}

	/**
	 * @param tileName
	 *            the tileName to set
	 */
	public void setTileName(final String tileName) {
		this.tileName = tileName;
	}

	/**
	 * @return the attributes
	 */
	public List<TileAttribute1> getAttributes() {
		return this.attributes;
	}

	/**
	 * @param attributes
	 *            the attributes to set
	 */
	public void setAttributes(final List<TileAttribute1> attributes) {
		this.attributes = attributes;
	}

	/**
	 * @return the tileHeaderImage
	 */
	public String getTileHeaderImage() {
		return this.tileHeaderImage;
	}

	/**
	 * @param tileHeaderImage
	 *            the tileHeaderImage to set
	 */
	public void setTileHeaderImage(final String tileHeaderImage) {
		this.tileHeaderImage = tileHeaderImage;
	}

	/**
	 * @return the tileImage
	 */
	public String getTileImage() {
		return this.tileImage;
	}

	/**
	 * @param tileImage
	 *            the tileImage to set
	 */
	public void setTileImage(final String tileImage) {
		this.tileImage = tileImage;
	}

	/**
	 * @return the tileOrder
	 */
	public Integer getTileOrder() {
		return this.tileOrder;
	}

	/**
	 * @param tileOrder
	 *            the tileOrder to set
	 */
	public void setTileOrder(final Integer tileOrder) {
		this.tileOrder = tileOrder;
	}

	/**
	 * @return the tileURL
	 */
	public String getTileURL() {
		return this.tileURL;
	}

	/**
	 * @param tileURL
	 *            the tileURL to set
	 */
	public void setTileURL(final String tileURL) {
		this.tileURL = tileURL;
	}

	/**
	 * @return the datatable
	 */
	public List<TileDataTable> getDatatable() {
		return this.datatable;
	}

	/**
	 * @param datatable
	 *            the datatable to set
	 */
	public void setDatatable(final List<TileDataTable> datatable) {
		this.datatable = datatable;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return this.error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(final String error) {
		this.error = error;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TileDTO [error=" + this.error + ", tileName=" + this.tileName + ", tileHeaderImage="
				+ this.tileHeaderImage + ", tileImage=" + this.tileImage + ", tileURL=" + this.tileURL + ", tileOrder="
				+ this.tileOrder + ", attributes=" + this.attributes + ", datatable=" + this.datatable + "]";
	}

}
