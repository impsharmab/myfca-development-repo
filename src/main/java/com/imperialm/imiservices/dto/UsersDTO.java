package com.imperialm.imiservices.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity @Getter @Setter
public class UsersDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6218000391717891635L;
	@Id private String userId;
	@Id private String name;
	@Id private String email;
	@Id private String createdBy;
	@Id private Date updatedDate;
	@Id private String updatedBy;
	@Id private String delFlag;
	@Id private String hashPass;
	@Id private String salt;
	@Id private String sendEmail;
	@Id private String phoneNumber;
}
