package com.imperialm.imiservices.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TTTAEnrollmentsDTO implements Serializable {

	private String dealerCode;
	private String dealerName;
	private String sID;
	private String name;
	private String enrollment;
	private String positionCode;
	private String incentiveEligible;
	private String avgSurveyScore;
	private String surveyCount;
	private String level3Techs;
	private String partcipantRank;
	private String error;
	
}
