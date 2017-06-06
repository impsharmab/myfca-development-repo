package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Setter @Getter
public class SummaryProgramRewardGraphDTO implements Serializable {
	@Id private String parent;
	@Id private String child;
	@Id private String toggle;
	@Id private String program;
	@Id private double earnings;
}
