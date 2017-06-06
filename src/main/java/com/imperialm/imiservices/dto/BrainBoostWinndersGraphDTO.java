package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter @Setter @Entity
public class BrainBoostWinndersGraphDTO implements Serializable {
	@Id
	private String parentTerritory;
	@Id
	private String childTerritory;
	@Id
	private int points;
	@Id
	private int partcipants;
	@Id
	private int winners;
	@Id
	private int earnings;
	@Id
	private String error;
}
