package com.imperialm.imiservices.model.response;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MSERTopNResponse {
	private String topNType;
	private String parentTerritory;
	private String dealerCode;
	private String dealerName;
	private String sID;
	private String name;
	private double earnings;
	private int topNRank;
}
