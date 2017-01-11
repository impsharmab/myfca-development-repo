package com.imperialm.imiservices.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ChartData {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public List<ChartData> getData() {
		return data;
	}

	public void setData(List<ChartData> data) {
		this.data = data;
	}


	private String name;
	private double value;
	private List<ChartData> data;
	
	public ChartData(String name, double value, List<ChartData> data){
		this.name = name;
		this.value = value;
		this.data = data;
	}
	
	public ChartData(String name, double value){
		this.name = name;
		this.value = (double) value;
		this.data = new ArrayList<ChartData>();
	}

	public ChartData() {
		this.name = "";
		this.value = 0.0;
		this.data = new ArrayList<ChartData>();
	}	

}
