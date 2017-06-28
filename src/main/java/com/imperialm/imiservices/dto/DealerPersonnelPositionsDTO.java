package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class DealerPersonnelPositionsDTO implements Serializable {

	@Id private String code;
	@Id private String name;
	@Id private String role;
	@Id private boolean isPrimary;
	@Id private boolean isSecondary;
	@Id private String delFlag;
	@Id private String roleID;
}
