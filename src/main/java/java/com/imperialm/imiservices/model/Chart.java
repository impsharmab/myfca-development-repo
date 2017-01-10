package com.imperialm.imiservices.model;

import java.util.List;

public class Chart {
	private String title;
    private String subTitle;
    private String xaxisTitle;
    private String yaxisTitle;
    private String type;
    private List<ChartData> data;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getXaxisTitle() {
		return xaxisTitle;
	}
	public void setXaxisTitle(String xaxisTitle) {
		this.xaxisTitle = xaxisTitle;
	}
	public String getYaxisTitle() {
		return yaxisTitle;
	}
	public void setYaxisTitle(String yaxisTitle) {
		this.yaxisTitle = yaxisTitle;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<ChartData> getData() {
		return data;
	}
	public void setData(List<ChartData> data) {
		this.data = data;
	}
    
}
