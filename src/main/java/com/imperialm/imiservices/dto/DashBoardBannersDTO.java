package com.imperialm.imiservices.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class DashBoardBannersDTO implements Serializable{
	@Id private int dashBoardBannersID;
	@Id private int roleID;
	@Id private String image;
	@Id private String link;
	@Id private int orderBy;
	@Id private String businessCenter;
	@Id private Date createdDate;
	@Id private String createdBy;
	@Id private Date updatedDate;
	@Id private String updatedBy;
	@Id private String delFlag;
	
}
