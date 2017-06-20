package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Getter @Setter
public class TIDUsersDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1176311910612803125L;
	@Id private String tID;
	@Id private String name;
	@Id private String email;
	@Id private String positionCode;
	@Id private String territory;
	@Id private int roleId;
}
