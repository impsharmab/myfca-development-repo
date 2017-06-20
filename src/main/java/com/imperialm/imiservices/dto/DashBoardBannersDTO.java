package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity @Getter @Setter
public class DashBoardBannersDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1199994986873985319L;
	
	@Id private int dashBoardBannersID;
	@Id private String image;
	@Id private int roleID;
	@Id private int orderBy;
	@Id private String businessCenter;
	@Id private String link;
	@Id private Date createdDate;
	@Id private String createdBy;
	@Id private Date updatedDate;
	@Id private String updatedBy;
	@Id private String delFlag;
}
