/**
 *
 */
package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Dheerajr
 *
 */
@Entity
public class ImagesDTO implements Serializable {

	@Id private String imageName;
	@Id private String fileName;
	@Id private int imageOrder;

	public ImagesDTO() {
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return this.imageName;
	}

	/**
	 * @param imageName
	 *            the imageName to set
	 */
	public void setImageName(final String imageName) {
		this.imageName = imageName;
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
	 * @return the imageOrder
	 */
	public int getImageOrder() {
		return this.imageOrder;
	}

	/**
	 * @param imageOrder
	 *            the imageOrder to set
	 */
	public void setImageOrder(final int imageOrder) {
		this.imageOrder = imageOrder;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ImagesDTO [imageName=" + this.imageName + ", fileName=" + this.fileName + ", imageOrder="
				+ this.imageOrder + "]";
	}

}
