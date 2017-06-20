package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Getter @Setter
public class CustomerFirstGraphDTO implements Serializable {
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
