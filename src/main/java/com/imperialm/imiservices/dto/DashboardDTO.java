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

	/**
		 *
		 */
	private static final long serialVersionUID = -5573712786197367779L;

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
		return programcode;
	}

	public void setProgramcode(final String programcode) {
		this.programcode = programcode;
	}

	public List<TileDTO> getTiles() {
		return tiles;
	}

	public void setTiles(final List<TileDTO> tiles) {
		this.tiles = tiles;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(final String error) {
		this.error = error;
	}

}
