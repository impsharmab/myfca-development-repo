package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class MSERTopNDTO {
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
