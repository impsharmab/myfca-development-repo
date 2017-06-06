package com.imperialm.imiservices.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class TopTenTableData {
	private String tableName;
	private String tabName;
	private List<String> tableHeader;
	private List<Object> data;
	
	public TopTenTableData(){
		this.tableName = "";
		this.tabName = "";
		this.tableHeader = new ArrayList<String>();
		this.data = new ArrayList<Object>();
	}
	
	public boolean addTableHeader(String header){
		this.tableHeader.add(header);
		return true;
	}
	
	public boolean addTableHeader(Object obj){
		this.data.add(obj);
		return true;
	}
}
