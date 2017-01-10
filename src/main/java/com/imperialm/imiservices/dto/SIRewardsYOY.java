package com.imperialm.imiservices.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SIRewardsYOY implements Serializable{
	private String UserId;
	private long RoleId;
	private String Territory;
	private BigDecimal _2015ytdEarnings;
	private BigDecimal _2016ytdEarnings;
	private String error;
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public long getRoleId() {
		return RoleId;
	}
	public void setRoleId(long roleId) {
		RoleId = roleId;
	}
	public String getTerritory() {
		return Territory;
	}
	public void setTerritory(String territory) {
		Territory = territory;
	}
	public BigDecimal get_2015ytdEarnings() {
		return _2015ytdEarnings;
	}
	public void set_2015ytdEarnings(BigDecimal _2015ytdEarnings) {
		this._2015ytdEarnings = _2015ytdEarnings;
	}
	public BigDecimal get_2016ytdEarnings() {
		return _2016ytdEarnings;
	}
	public void set_2016ytdEarnings(BigDecimal _2016ytdEarnings) {
		this._2016ytdEarnings = _2016ytdEarnings;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
