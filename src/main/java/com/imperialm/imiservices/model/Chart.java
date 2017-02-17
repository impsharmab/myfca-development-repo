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
	//private JsonNode drilldownData;
}
