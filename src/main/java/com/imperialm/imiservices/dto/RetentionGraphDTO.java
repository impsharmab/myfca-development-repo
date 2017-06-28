package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Setter @Getter
public class RetentionGraphDTO implements Serializable {
	@Id private String parentTerritory;
	@Id private String childTerritory;
	@Id private String positionCode;
	@Id private double percentage;
	@Id private String error;
}
