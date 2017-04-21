package com.imperialm.imiservices.dto;

import java.io.Serializable;
public class SIRewardsYOY implements Serializable{
	private String territory;
	private double _2015ytdEarnings;
	private double _2016ytdEarnings;
	private String error;
	public String getTerritory() {
		return territory;
	}
	public void setTerritory(String territory) {
		this.territory = territory;
	}
	public double get_2015ytdEarnings() {
		return _2015ytdEarnings;
	}
	public void set_2015ytdEarnings(double _2015ytdEarnings) {
		this._2015ytdEarnings = _2015ytdEarnings;
	}
	public double get_2016ytdEarnings() {
		return _2016ytdEarnings;
	}
	public void set_2016ytdEarnings(double _2016ytdEarnings) {
		this._2016ytdEarnings = _2016ytdEarnings;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
