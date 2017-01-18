package com.imperialm.imiservices.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TopTenTableData {
	private String tableName;
	private List<String> tableHeader;
	private List<Object> data;
	
	public TopTenTableData(){
		this.tableName = "";
		this.tableHeader = new ArrayList<String>();
		this.data = new ArrayList<Object>();
	}
}
