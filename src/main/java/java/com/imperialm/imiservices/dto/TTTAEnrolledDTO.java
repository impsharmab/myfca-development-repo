package com.imperialm.imiservices.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class TTTAEnrolledDTO implements Serializable {

	private String UserId;
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getTerritory() {
		return Territory;
	}
	public void setTerritory(String territory) {
		Territory = territory;
	}
	public long getRoleId() {
		return RoleId;
	}
	public void setRoleId(long roleId) {
		RoleId = roleId;
	}
	public char getEnrollmentStatus() {
		return EnrollmentStatus;
	}
	public void setEnrollmentStatus(char enrollmentStatus) {
		EnrollmentStatus = enrollmentStatus;
	}
	public BigDecimal getGroupA() {
		return GroupA;
	}
	public void setGroupA(BigDecimal groupA) {
		GroupA = groupA;
	}
	public BigDecimal getGroupB() {
		return GroupB;
	}
	public void setGroupB(BigDecimal groupB) {
		GroupB = groupB;
	}
	public BigDecimal getGroupC() {
		return GroupC;
	}
	public void setGroupC(BigDecimal groupC) {
		GroupC = groupC;
	}
	public BigDecimal getGroupD() {
		return GroupD;
	}
	public void setGroupD(BigDecimal groupD) {
		GroupD = groupD;
	}
	public BigDecimal getGroupE() {
		return GroupE;
	}
	public void setGroupE(BigDecimal groupE) {
		GroupE = groupE;
	}
	private String Territory;
	private long RoleId;
	private char EnrollmentStatus;
	private BigDecimal GroupA;
	private BigDecimal GroupB;
	private BigDecimal GroupC;
	private BigDecimal GroupD;
	private BigDecimal GroupE;
	private String error;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
