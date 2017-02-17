package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Setter @Getter
public class RetentionDetailsDTO implements Serializable {
	@Id private int DealerCode;
	@Id private String DealerName;
	@Id private int PositionCode;
	@Id private double Percentage;
	@Id private String error;

}
