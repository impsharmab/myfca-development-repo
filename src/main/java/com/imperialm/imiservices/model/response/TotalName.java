package com.imperialm.imiservices.model.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class TotalName implements Serializable {
	@Id private String name;
	@Id private String total;
	@Id private String error;
}
