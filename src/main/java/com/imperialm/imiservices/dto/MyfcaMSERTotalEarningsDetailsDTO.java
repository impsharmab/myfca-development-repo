package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Getter @Setter
public class MyfcaMSERTotalEarningsDetailsDTO implements Serializable{
	  @Id private String dealerCode;
	  @Id private String dealerName;
	  @Id private String sID;
	  @Id private String name;
	  @Id private String positionCode;
	  @Id private String program;
	  @Id private String programGroup;
	  @Id private double earningsMTD;
	  @Id private double earningsYTD;
	  @Id private int dealersEnrolled;
	  @Id private int participantsEnrolled;
	  @Id private String error;
}
