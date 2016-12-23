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
public class BannerResponse implements Serializable {

	@Id
	private String rNo;
	private String programCode;
	private String bannerName;
	private String fileName;
	private int bannerOrder;

	public BannerResponse() {
	}

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
	 * @return the bannerName
	 */
	public String getBannerName() {
		return this.bannerName;
	}

	/**
	 * @param bannerName
	 *            the bannerName to set
	 */
	public void setBannerName(final String bannerName) {
		this.bannerName = bannerName;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the bannerOrder
	 */
	public int getBannerOrder() {
		return this.bannerOrder;
	}

	/**
	 * @param bannerOrder
	 *            the bannerOrder to set
	 */
	public void setBannerOrder(final int bannerOrder) {
		this.bannerOrder = bannerOrder;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BannerResponse [programCode=" + this.programCode + ", bannerName=" + this.bannerName + ", fileName="
				+ this.fileName + ", bannerOrder=" + this.bannerOrder + "]";
	}

	/**
	 * @return the rNo
	 */
	public String getrNo() {
		return this.rNo;
	}

	/**
	 * @param rNo
	 *            the rNo to set
	 */
	public void setrNo(final String rNo) {
		this.rNo = rNo;
	}

}
