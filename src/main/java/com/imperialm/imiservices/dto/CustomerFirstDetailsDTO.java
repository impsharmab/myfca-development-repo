package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class CustomerFirstDetailsDTO implements Serializable {
	@Id private String dealerCode;
	@Id private String dealerName;
	@Id private double noCertification;
	@Id private double performance;
	@Id private double process;
	@Id private double voiceofEmployee;
	@Id private double training;
	@Id private double facility;
	@Id private double cFAFEAwardCertification;
	@Id private double toggle;
}
