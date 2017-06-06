package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Getter @Setter
public class UserPositionCodeRoleDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3472359421149656917L;
	@Id private String dealerCode;
	@Id private String sID;
	@Id private String positionCode;
	@Id private boolean isPrimaryPosition;
	@Id private boolean isPrimaryDealer;
	@Id private int roleId;
}
