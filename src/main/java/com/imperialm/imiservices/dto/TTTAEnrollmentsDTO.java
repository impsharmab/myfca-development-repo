package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Getter @Setter
public class TTTAEnrollmentsDTO implements Serializable {

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
