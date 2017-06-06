package com.imperialm.imiservices.dto;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter @Setter @Entity
public class MyfcaMSERTotalEarningsDTO implements Serializable{
	@Id private String child;
	@Id private String program;
	@Id private String toggle;
	@Id private double amount;
	@Id private String parent;
	@Id private String error;
}
