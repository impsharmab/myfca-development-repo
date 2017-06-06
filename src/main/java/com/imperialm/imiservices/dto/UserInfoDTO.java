package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Setter @Getter
public class UserInfoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4177013323884844943L;
	@Id private String userId;
	@Id private String name;
	@Id private String email;
	@Id private String programCode;
	@Id private int roleId;
	@Id private String territoy;
	private String error;
}

