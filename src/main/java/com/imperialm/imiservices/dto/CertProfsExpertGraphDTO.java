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
	private String cert;
	@Id
	private String points;
	@Id
	private String totalPoints;
	@Id
	private String bCPointRank;
	@Id
	private String nATPointRank;
	private String error;
}
