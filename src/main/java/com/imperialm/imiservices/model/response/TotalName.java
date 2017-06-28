package com.imperialm.imiservices.model.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class TotalName implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1642787274602523166L;
	@Id private String name;
	@Id private String total;
	@Id private String error;
	
	public TotalName(String name, String total){
		this.name = name;
		this.total = total;
	}
	
	public TotalName(){
		this.name = "";
		this.total = "";
	}
}
