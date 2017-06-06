package com.imperialm.imiservices.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class TopTenDataTable {
	private String buttonName;
	private List<TopTenTableData> data;
	
	public TopTenDataTable(String buttonName){
		this.buttonName = buttonName;
		this.data = new ArrayList<TopTenTableData>();
	}
}
