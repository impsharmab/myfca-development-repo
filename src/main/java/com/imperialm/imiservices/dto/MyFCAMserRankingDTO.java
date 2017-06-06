package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Getter @Setter
public class MyFCAMserRankingDTO implements Serializable {
	@Id private String parent;
	@Id private String child;
	@Id private double earningsMTD;
	@Id private int bCRank;
	
}
