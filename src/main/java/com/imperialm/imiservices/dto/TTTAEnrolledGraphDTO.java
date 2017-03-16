package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class TTTAEnrolledGraphDTO implements Serializable {
	@Id private String parent;
	@Id private String child;
	@Id private int enrolled;
	@Id private int totalGroupA;
	@Id private int totalGroupB;
	@Id private int totalGroupC;
	@Id private int totalGroupD;
	@Id private int totalGroupE;
}
