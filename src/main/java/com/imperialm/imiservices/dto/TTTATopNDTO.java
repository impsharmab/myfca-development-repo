package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Setter @Getter
public class TTTATopNDTO implements Serializable{
	@Id private String topNType;
	@Id private String parentTerritory;
	@Id private String dealerCode;
	@Id private String dealerName;
	@Id private String sID;
	@Id private String name;
	@Id private int totalSurveys;
	@Id private double avgSurveyScore;
	@Id private String error;

}
