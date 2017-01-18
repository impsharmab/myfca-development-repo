package com.imperialm.imiservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @ Setter
public class TileAttribute1 {
	private String name;
	private String value;
	private String type;
	
	@JsonProperty("Order")
	private String Order;
}
