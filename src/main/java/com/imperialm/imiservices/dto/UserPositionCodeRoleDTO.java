package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class UserPositionCodeRoleDTO implements Serializable{
	@Id private String dealerCode;
	@Id private String sID;
	@Id private String positionCode;
	@Id private boolean isPrimaryPosition;
	@Id private boolean isPrimaryDealer;
	@Id private int roleId;
}
