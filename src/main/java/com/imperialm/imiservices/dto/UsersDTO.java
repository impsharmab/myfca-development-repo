package com.imperialm.imiservices.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class UsersDTO implements Serializable {

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
