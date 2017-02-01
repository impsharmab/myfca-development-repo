package com.imperialm.imiservices.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.GenerationType;

@Entity
@Table(name="Users", indexes = {@Index(columnList = "userId", unique=true), @Index(columnList = "email", unique=true)})
public class User {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		email = email;
	}
	public String getHashPass() {
		return HashPass;
	}
	public void setHashPass(String hashPass) {
		HashPass = hashPass;
	}
	public String getSalt() {
		return Salt;
	}
	public void setSalt(String salt) {
		Salt = salt;
	}
	@Id
	@Column(nullable = false, name="UserId")
	private String userId;
	
	@Column(nullable = false, name="Name")
	private String name;
	@Column(nullable = false, name="Email")
	private String email;
	
	private Date CreatedDate;
	private String CreatedBy;
	private String UpdatedDate;
	private String UpdatedBy;
	private char DelFlag;
	
	@Column(nullable = false)
	private String HashPass;
	@Column(nullable = false)
	private String Salt;
}
