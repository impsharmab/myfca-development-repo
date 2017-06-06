package com.imperialm.imiservices.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter @Setter
public class Permissions implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1335481909533027655L;
	private String positionCode;
	private List<Boolean> permissions;
}
