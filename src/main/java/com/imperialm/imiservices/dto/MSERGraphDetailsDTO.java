package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MSERGraphDetailsDTO {
	  private String DealerCode;
	  private String DealerName;
	  private String SID;
	  private String Name;
	  private String PositionCode;
	  private String Program;
	  private String ProgramGroup;
	  private double EarningsMTD;
	  private double EarningsYTD;
	  private int DealersEnrolled;
	  private int ParticipantsEnrolled;
	  private String error;
}
