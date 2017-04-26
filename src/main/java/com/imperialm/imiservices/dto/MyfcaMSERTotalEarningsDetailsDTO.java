package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class MyfcaMSERTotalEarningsDetailsDTO implements Serializable{
	  @Id private String DealerCode;
	  @Id private String DealerName;
	  @Id private String SID;
	  @Id private String Name;
	  @Id private String PositionCode;
	  @Id private String Program;
	  @Id private String ProgramGroup;
	  @Id private double EarningsMTD;
	  @Id private double EarningsYTD;
	  @Id private int DealersEnrolled;
	  @Id private int ParticipantsEnrolled;
	  @Id private String error;
}
