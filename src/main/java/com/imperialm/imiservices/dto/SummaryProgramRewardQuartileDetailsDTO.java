package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Getter @Setter
public class SummaryProgramRewardQuartileDetailsDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4368990952105154727L;
	@Id private String dealerCode;
	@Id private String dealerName;
	@Id private String sID;
	@Id private String name;
	@Id private String toggle;
	@Id private double earnings;
	@Id private int quartile;
	@Id private double topQuartile;
}
