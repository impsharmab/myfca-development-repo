package com.imperialm.imiservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TileDataTable {
	@JsonProperty("Part Number")
	private String PartNumber;
	
	public String getPartNumber() {
		return PartNumber;
	}

	public void setPartNumber(String partNumber) {
		PartNumber = partNumber;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private String value;
}
