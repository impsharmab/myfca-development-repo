package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Getter @Setter
public class SIRewardsYOYDetailsDTO implements Serializable {
	@Id private String dealerCode;
	@Id private String dealerName;
	@Id private String toggle;
	@Id private String sID;
	@Id private String name;
	@Id private String positionCode;
	@Id private double lastYearEarnings;
	@Id private double currentYearEarnings;
	@Id private int bCRank;
}
