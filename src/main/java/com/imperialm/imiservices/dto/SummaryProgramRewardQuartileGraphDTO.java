package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Getter @Setter
public class SummaryProgramRewardQuartileGraphDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3714101292348524567L;
	@Id private String parent;
	@Id private String child;
	@Id private String toggle;
	@Id private double earnings;
	@Id private int quartile;
	@Id private double topQuartile;
}
