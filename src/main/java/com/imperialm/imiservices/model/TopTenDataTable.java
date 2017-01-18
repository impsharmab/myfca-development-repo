package com.imperialm.imiservices.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TopTenDataTable {
	private String buttonName;
	private String title;
	private List<TopTenTableData> tableData;
	
	public TopTenDataTable(String buttonName, String title){
		this.buttonName = buttonName;
		this.title = title;
		this.tableData = new ArrayList<TopTenTableData>();
	}
}
