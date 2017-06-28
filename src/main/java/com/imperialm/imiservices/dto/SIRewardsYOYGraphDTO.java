package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class SIRewardsYOYGraphDTO implements Serializable {
	@Id private String parentTerritory;
	@Id private String childTerritory;
	@Id private String toggle;
	@Id private double lastYearEarnings;
	@Id private int bCDealerRank;
	@Id private double currentYearEarnings;
}
