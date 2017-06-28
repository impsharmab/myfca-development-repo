package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity
public class TTTAEnrolledDTO implements Serializable {

	@Id private String territory;
	@Id private char enrollmentStatus;
	@Id private int groupA;
	@Id private int groupB;
	@Id private int groupC;
	@Id private int groupD;
	@Id private int groupE;
	@Id private String error;
	
}
