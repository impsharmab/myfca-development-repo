package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class UserProgramRolesDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2721819299215509777L;
	@Id private String userId;
	@Id private String programCode;
	@Id private int roleId;

}
