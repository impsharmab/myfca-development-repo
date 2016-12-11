/**
 *
 */
package com.imperialm.imiservices.dto;

import java.io.Serializable;

/**
 * @author Dheerajr
 *
 */
public class ImagesDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1398447048509343150L;
	private String imageName;
	private String fileName;
	private int imageOrder;

	public ImagesDTO() {
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
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
		return fileName;
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
		return imageOrder;
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
		return "ImagesDTO [imageName=" + imageName + ", fileName=" + fileName + ", imageOrder=" + imageOrder + "]";
	}

}
