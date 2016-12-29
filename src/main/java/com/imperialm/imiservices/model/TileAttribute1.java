package com.imperialm.imiservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TileAttribute1 {
	private String name;
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getOrder() {
		return Order;
	}
	public void setOrder(String order) {
		Order = order;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@JsonProperty("Order")
	private String Order;
	private String type;
}
