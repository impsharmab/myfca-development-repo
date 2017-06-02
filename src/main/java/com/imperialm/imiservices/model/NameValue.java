package com.imperialm.imiservices.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NameValue {
	private String name;
	private Object value;
	
	public NameValue(String name, Object value){
		this.name = name;
		this.value = value;
	}
}
