package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity
public class CertProfsExpertGraphDTO implements Serializable {
	@Id
	private String parentTerritory;
	@Id
	private String childTerritory;
	@Id
	private String certType;
	@Id
	private int cert;
	@Id
	private int points;
	@Id
	private int totalPoints;
	@Id
	private int bCPointRank;
	@Id
	private int nATPointRank;
	@Id private String error;
}
