package com.imperialm.imiservices.model.response;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class TotalName {
	@Id
	private String name;
	private String total;
	private String error;
}
