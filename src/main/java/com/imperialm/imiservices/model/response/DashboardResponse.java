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
	@Id
	private Long rNo;
	private String programCode;
	private String territory;
	private String tileName;
	private int tileOrder;
	private String tileHeaderImage;
	private String tileImage;
	private String url;
	private Long tileID;

	/**
	 * @return the programCode
	 */
	public String getProgramCode() {
		return this.programCode;
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
	 * @return the tileOrder
	 */
	public int getTileOrder() {
		return this.tileOrder;
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
	 * @return the url
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(final String url) {
		this.url = url;
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
		return this.rNo;
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
		return "DashboardResponse [rNo=" + this.rNo + ", programCode=" + this.programCode + ", territory="
				+ this.territory + ", tileName=" + this.tileName + ", tileOrder=" + this.tileOrder
				+ ", tileHeaderImage=" + this.tileHeaderImage + ", tileImage=" + this.tileImage + ", url=" + this.url
				+ ", tileID=" + this.tileID + "]";
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

}
