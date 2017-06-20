package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Setter @Getter
public class TTTATopNDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8667116656279186762L;
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
