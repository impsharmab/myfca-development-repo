package com.imperialm.imiservices.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Banner2DTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2972672666811681849L;
	@Id private int bannerID;
	@Id private int name;
	@Id private int fileName;
	@Id private Date createdDate;
	@Id private String createdBy;
	@Id private Date updatedDate;
	@Id private String updatedBy;
	@Id private String delFlag;
}
