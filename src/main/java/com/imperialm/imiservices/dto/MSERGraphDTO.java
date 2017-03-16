package com.imperialm.imiservices.dto;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity
public class MSERGraphDTO implements Serializable{
	@Id private String child;
	@Id private String program;
	@Id private String toggle;
	@Id private double amount;
	@Id private String parent;
	@Id private String error;
}
