/**
 *
 */
package com.imperialm.imiservices.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dheerajr
 *
 */
public class BannersDTO implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 2751669145743233156L;
	private String programCode;
	private String error;
	private List<ImagesDTO> banners;

	public BannersDTO() {
	}

	public BannersDTO(final String programCode, final String error) {
		this.programCode = programCode;
		this.error = error;
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

	/**
	 * @return the banners
	 */
	public List<ImagesDTO> getBanners() {
		return this.banners;
	}

	/**
	 * @param banners
	 *            the banners to set
	 */
	public void setBanners(final List<ImagesDTO> banners) {
		this.banners = banners;
	}

}
