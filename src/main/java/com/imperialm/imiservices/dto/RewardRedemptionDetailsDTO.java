package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Setter @Getter
public class RewardRedemptionDetailsDTO implements Serializable {
	@Id private String dealerCode;
	@Id private String dealerName;
	@Id private String sID;
	@Id private String name;
	@Id private int earnedPoints;
	@Id private int redeemedPoints;
	@Id private int balancePoints;
	@Id private String error;
}
