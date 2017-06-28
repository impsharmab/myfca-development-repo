package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class CertProfsExpertDetailsDTO implements Serializable{
	@Id private String dealerCode;
	@Id private String dealerName;
	@Id private String sID;
	@Id private String name;
	@Id private String certType;
	@Id private int cert;
	@Id private int points;
	@Id private int totalPoints;
	
}
