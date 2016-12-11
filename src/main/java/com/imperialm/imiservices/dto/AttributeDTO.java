/**
 *
 */
package com.imperialm.imiservices.dto;

import java.io.Serializable;

/**
 * @author Dheerajr
 *
 */
public class AttributeDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8994560709632350053L;
	private String name;
	private String value;
	private String type;
	private int order;

	public AttributeDTO() {
	}

	public AttributeDTO(final String name, final String value, final String type, final int order) {
		super();
		this.name = name;
		this.value = value;
		this.type = type;
		this.order = order;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(final int order) {
		this.order = order;
	}

}
