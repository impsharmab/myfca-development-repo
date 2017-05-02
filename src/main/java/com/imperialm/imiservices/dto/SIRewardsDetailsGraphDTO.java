package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class SIRewardsDetailsGraphDTO implements Serializable {
	@Id private String parentTerritory;
	@Id private String childTerritory;
	@Id private String toggle;
	@Id private double avgSurveyScore;
	@Id private double projectedEarnings;
	@Id private int bCDlearRankEarnings;
	@Id private int incentiveQualified;
	@Id private int trainingQualified;
	@Id private int EligibleSurveys;
	@Id private int totalSurveys;
}