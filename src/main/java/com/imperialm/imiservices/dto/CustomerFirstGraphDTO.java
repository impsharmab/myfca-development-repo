package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class CustomerFirstGraphDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3745282919084555512L;
	@Id private String parentTerritory;
	@Id private String childTerritory;
	@Id private double noCertification;
	@Id private double performance;
	@Id private double process;
	@Id private double voiceofEmployee;
	@Id private double training;
	@Id private double facility;
	@Id private double cFAFEAwardCertification;
	@Id private String toggle;
}
