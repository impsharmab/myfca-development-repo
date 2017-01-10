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
public class DashboardDTO implements Serializable {

	private String programcode;
	private String error;
	private List<TileDTO> tiles;

	public DashboardDTO() {
	}

	public DashboardDTO(final String programcode, final String error) {
		super();
		this.programcode = programcode;
		this.error = error;
	}

	public DashboardDTO(final String programcode, final List<TileDTO> tiles, final String error) {
		super();
		this.programcode = programcode;
		this.tiles = tiles;
		this.error = error;
	}

	public String getProgramcode() {
		return this.programcode;
	}

	public void setProgramcode(final String programcode) {
		this.programcode = programcode;
	}

	public List<TileDTO> getTiles() {
		return this.tiles;
	}

	public void setTiles(final List<TileDTO> tiles) {
		this.tiles = tiles;
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

}
