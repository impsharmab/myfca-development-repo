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
public class DashboardResponse implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -360922292108683419L;
	@Id
	private Long rNo;
	private String programCode;
	private String tileName;
	private int tileOrder;
	private String tileHeaderImage;
	private String tileImage;
	private String url;
	private String attributeName;
	private String attributeValue;
	private int attributeOrder;
	private String format;

	/**
	 * @return the programCode
	 */
	public String getProgramCode() {
		return programCode;
	}

	/**
	 * @param programCode
	 *            the programCode to set
	 */
	public void setProgramCode(final String programCode) {
		this.programCode = programCode;
	}

	/**
	 * @return the tileName
	 */
	public String getTileName() {
		return tileName;
	}

	/**
	 * @param tileName
	 *            the tileName to set
	 */
	public void setTileName(final String tileName) {
		this.tileName = tileName;
	}

	/**
	 * @return the tileOrder
	 */
	public int getTileOrder() {
		return tileOrder;
	}

	/**
	 * @param tileOrder
	 *            the tileOrder to set
	 */
	public void setTileOrder(final int tileOrder) {
		this.tileOrder = tileOrder;
	}

	/**
	 * @return the tileHeaderImage
	 */
	public String getTileHeaderImage() {
		return tileHeaderImage;
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
		return tileImage;
	}

	/**
	 * @param tileImage
	 *            the tileImage to set
	 */
	public void setTileImage(final String tileImage) {
		this.tileImage = tileImage;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(final String url) {
		this.url = url;
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * @param attributeName
	 *            the attributeName to set
	 */
	public void setAttributeName(final String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * @return the attributeValue
	 */
	public String getAttributeValue() {
		return attributeValue;
	}

	/**
	 * @param attributeValue
	 *            the attributeValue to set
	 */
	public void setAttributeValue(final String attributeValue) {
		this.attributeValue = attributeValue;
	}

	/**
	 * @return the attributeOrder
	 */
	public int getAttributeOrder() {
		return attributeOrder;
	}

	/**
	 * @param attributeOrder
	 *            the attributeOrder to set
	 */
	public void setAttributeOrder(final int attributeOrder) {
		this.attributeOrder = attributeOrder;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(final String format) {
		this.format = format;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	/**
	 * @return the rNo
	 */
	public Long getrNo() {
		return rNo;
	}

	/**
	 * @param rNo
	 *            the rNo to set
	 */
	public void setrNo(final Long rNo) {
		this.rNo = rNo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DashboardResponse [rNo=" + rNo + ", programCode=" + programCode + ", tileName=" + tileName
				+ ", tileOrder=" + tileOrder + ", tileHeaderImage=" + tileHeaderImage + ", tileImage=" + tileImage
				+ ", url=" + url + ", attributeName=" + attributeName + ", attributeValue=" + attributeValue
				+ ", attributeOrder=" + attributeOrder + ", format=" + format + "]";
	}

}
