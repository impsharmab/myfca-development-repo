package com.imperialm.imiservices.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class TTTATopNDTO implements Serializable{
	private String topNType;
	private String parentTerritory;
	private String dealerCode;
	private String dealerName;
	private String sID;
	private String name;
	private int totalSurveys;
	private double avgSurveyScore;
	private String error;

}
