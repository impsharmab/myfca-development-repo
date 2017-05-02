package com.imperialm.imiservices.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DashBoardBannersDTO implements Serializable {
	@Id private int dashBoardBannersID;
	@Id private String image;
	@Id private int roleID;
	@Id private int orderBy;
	@Id private String businessCenter;
	@Id private String link;
	@Id private Date createdDate;
	@Id private String createdBy;
	@Id private String updatedDate;

}
