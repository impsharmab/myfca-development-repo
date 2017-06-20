package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Setter @Getter
public class MyfcaMSERTopNDTO implements Serializable {
	@Id
	private String topNType;
	@Id private String parentTerritory;
	@Id private String dealerCode;
	@Id private String dealerName;
	@Id private String sID;
	@Id private String name;
	@Id private double earnings;
	@Id private int topNRank;
	@Id private String error;
	@Id private String toggle;
	@Id private int quantity;
	
}
