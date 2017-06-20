package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Getter @Setter
public class MyFCAMserRankingDetailsDTO implements Serializable {

	@Id private String dealerCode;
	@Id private String dealerName;
	@Id private String sID;
	@Id private double earningsMTD;
	@Id private int bCRank;
}
