package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

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
