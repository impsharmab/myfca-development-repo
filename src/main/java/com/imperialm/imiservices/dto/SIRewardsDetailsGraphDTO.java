package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class SIRewardsDetailsGraphDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8833989764966215067L;
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
	@Id private int advsiorsQualified;
	@Id private int managersQualified;
}