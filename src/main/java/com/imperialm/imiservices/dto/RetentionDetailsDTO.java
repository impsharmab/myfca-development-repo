package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Setter @Getter
public class RetentionDetailsDTO implements Serializable {
	@Id private String dealerCode;
	@Id private String dealerName;
	@Id private String positionCode;
	@Id private double percentage;

}
