package com.imperialm.imiservices.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class ChartData {
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
	
	public boolean addData(ChartData data){
		try{
			this.data.add(data);
			return true;
		}catch(Exception ex){
			return false;
		}
	}

}
