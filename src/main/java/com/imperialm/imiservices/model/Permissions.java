package com.imperialm.imiservices.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Permissions implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1335481909533027655L;
	private String positionCode;
	private List<Boolean> permissions;
}
