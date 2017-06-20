package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

@Entity @Getter @Setter
public class BannerMappingsDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8527257748761720900L;
	@Id private int bannerMappingID;
	@Id private int bannerID;
	@Id private int roleID;
	@Id private int OrderBy;
	@Id private Date createdDate;
	@Id private String CreatedBy;
	@Id private Date datetime;
	@Id private String updatedBy;
	@Id private String delFlag;
	@Id private String businessCenter;
}
