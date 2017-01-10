package com.imperialm.imiservices.model;

import java.util.ArrayList;
import java.util.List;

public class ChartData {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<ChartData> getData() {
		return data;
	}

	public void setData(List<ChartData> data) {
		this.data = data;
	}


	private String name;
	private String value;
	private List<ChartData> data;
	
	public ChartData(String name, String value, List<ChartData> data){
		this.name = name;
		this.value = value;
		this.data = data;
	}

	public ChartData() {
		this.name = "";
		this.value = "";
		this.data = new ArrayList<ChartData>();
	}	

}
