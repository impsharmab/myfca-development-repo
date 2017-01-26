package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity
public class BrainBoostWinndersGraphDTO implements Serializable {
	@Id
	private String parentTerritory;
	@Id
	private String childTerritory;
	@Id
	private String points;
	@Id
	private String partcipants;
	@Id
	private String winners;
	@Id
	private String earnings;
	private String error;
}
