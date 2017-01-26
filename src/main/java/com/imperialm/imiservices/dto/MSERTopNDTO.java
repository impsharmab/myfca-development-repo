package com.imperialm.imiservices.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Setter @Getter
public class MSERTopNDTO {
	@Id
	private String topNType;
	private String parentTerritory;
	private String dealerCode;
	private String dealerName;
	private String sID;
	private String name;
	private double earnings;
	private int topNRank;
	private String error;
	
}
