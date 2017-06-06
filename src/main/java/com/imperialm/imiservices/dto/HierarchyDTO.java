package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Getter @Setter
public class HierarchyDTO implements Serializable {

	@Id private int iD;
	@Id private String programCode;
	@Id private String dealerCode;
	@Id private String bC;
	@Id private String dIST;
}
