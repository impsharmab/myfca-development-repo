package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class HierarchyDTO implements Serializable {

	@Id private int iD;
	@Id private String programCode;
	@Id private String dealerCode;
	@Id private String bC;
	@Id private String dIST;
}
