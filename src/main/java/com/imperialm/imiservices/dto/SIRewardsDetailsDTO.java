package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter @Setter @Entity
public class SIRewardsDetailsDTO implements Serializable {
	@Id private String dealerCode;
	@Id private String dealerName;
	@Id private String sID;
	@Id private String name;
	@Id private String toggle;
	@Id private int eligibleSurveys;
	@Id private int incentiveQualified;
	@Id private double surveyScore;
	@Id private double projectedEarnings;
	@Id private int level0;
	@Id private int level1;
	@Id private int totalSurveys;
	@Id private int bCAdvisorRankEarnings;
	@Id private int trainingQualified;
	@Id private double dealerTarget;
	@Id private String positionCode;
	@Id private String reportQuarter;
	@Id private int advisorSurveys;
}