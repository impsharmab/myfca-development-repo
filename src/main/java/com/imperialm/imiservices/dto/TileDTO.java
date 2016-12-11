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
public class TileDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5144477871277407196L;
	private String tileName;
	private String tileHeaderImage;
	private String tileImage;
	private String tileURL;
	private Integer tileOrder;

	public TileDTO(final String tileName, final String tileHeaderImage, final String tileImage, final String tileURL,
			final Integer tileOrder, final List<AttributeDTO> attributes) {
		super();
		this.tileName = tileName;
		this.tileHeaderImage = tileHeaderImage;
		this.tileImage = tileImage;
		this.tileURL = tileURL;
		this.tileOrder = tileOrder;
		this.attributes = attributes;
	}

	private List<AttributeDTO> attributes;

	public TileDTO() {
	}

	public TileDTO(final String tileName, final List<AttributeDTO> attributes) {
		this.tileName = tileName;
		this.attributes = attributes;
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
	 * @return the attributes
	 */
	public List<AttributeDTO> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes
	 *            the attributes to set
	 */
	public void setAttributes(final List<AttributeDTO> attributes) {
		this.attributes = attributes;
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
	 * @return the tileOrder
	 */
	public Integer getTileOrder() {
		return tileOrder;
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
		return tileURL;
	}

	/**
	 * @param tileURL
	 *            the tileURL to set
	 */
	public void setTileURL(final String tileURL) {
		this.tileURL = tileURL;
	}

}
