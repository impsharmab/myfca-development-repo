package com.imperialm.imiservices.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Chart {
	private String title;
	private String subTitle;
	private String yaxisTitle;
	private String xaxisTitle;
	private String type;
	private List<ChartData> data;
	private String unit;
	private boolean avarage = false;
	private boolean customer_first = false;
	private boolean retention = false;
	//private JsonNode drilldownData;
	
	public Chart(){
		this.title = "";
		this.subTitle = "";
		this.yaxisTitle = "";
		this.xaxisTitle = "";
		this.type = "";
		this.unit = "";
		this.data = new ArrayList<ChartData>();
	}
	
	public Chart(String title, String subTitle, String yAxis, String xAxis, String type){
		this.title = title;
		this.subTitle = subTitle;
		this.yaxisTitle = yAxis;
		this.xaxisTitle = xAxis;
		this.type = type;
		this.data = new ArrayList<ChartData>();
	}
	
	public Chart(String title, String subTitle, String yAxis, String xAxis, String type, List<ChartData> data){
		this.title = title;
		this.subTitle = subTitle;
		this.yaxisTitle = yAxis;
		this.xaxisTitle = xAxis;
		this.type = type;
		this.data = data;
	}
}
