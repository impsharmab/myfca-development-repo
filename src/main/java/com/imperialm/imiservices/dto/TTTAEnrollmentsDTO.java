package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class TTTAEnrollmentsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8268549508356142472L;
	@Id private String dealerCode;
	@Id private String dealerName;
	@Id private String sID;
	@Id private String name;
	@Id private String enrollment;
	@Id private String positionCode;
	@Id private int incentiveEligible;
	@Id private double avgSurveyScore;
	@Id private int surveyCount;
	@Id private int level3Techs;
	@Id private int partcipantRank;
	
}
