package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class MSERDetailsGraphDTO implements Serializable {
	@Id private String parent;
	@Id private String child;
	@Id private double earningsMTD;
	@Id private int bCRank;
	
}
