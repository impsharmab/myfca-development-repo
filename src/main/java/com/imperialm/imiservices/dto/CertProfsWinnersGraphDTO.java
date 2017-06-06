package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter @Setter @Entity
public class CertProfsWinnersGraphDTO implements Serializable {
	@Id
	private String parentTerritory;
	@Id
	private String childTerritory;
	@Id
	private String certType;
	@Id 
	private String points;
	@Id
	private String certified;
	@Id
	private String certifiedSpecialist;
	@Id
	private String masterCertified;
	@Id
	private String totalCertified;
	@Id
	private String masterCertBCCertRank;
	@Id
	private String masterCertNATCertRank;
	@Id
	private String allBCCertRank;
	@Id
	private String allNATCertRank;
	@Id
	private String error;
}
