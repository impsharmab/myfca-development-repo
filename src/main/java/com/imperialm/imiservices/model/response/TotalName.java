package com.imperialm.imiservices.model.response;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

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
