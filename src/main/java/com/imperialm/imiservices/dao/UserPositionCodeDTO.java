package com.imperialm.imiservices.dao;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity @Setter @Getter
public class UserPositionCodeDTO implements Serializable {
	private String dealerCode;
	private String sID;
	private String positionCode;
}
