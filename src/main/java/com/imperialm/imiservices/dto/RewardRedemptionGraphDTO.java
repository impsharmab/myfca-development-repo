package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Setter @Getter
public class RewardRedemptionGraphDTO implements Serializable{
	@Id private String parentTerritory;
	@Id private String childTerritory;
	@Id private int earnedPoints;
	@Id private int redeemedPoints;
	@Id private int balancePoints;
	@Id private String program;

}
