package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Getter @Setter
public class TTTAEnrollmentsSummaryDTO implements Serializable{
	@Id private String parentTerritory;
	@Id private String childTerritory;
	@Id private int totalEnrollments;
	@Id private String positionCode;
	@Id private int incentiveEligible;
	@Id private double avgSurveyScore;
	@Id private int surveyCount;
	@Id private int level3Techs;
	@Id private int tTTARank;
	@Id private int yearsOfService;
	@Id private double percentEnrolled;

}
