package com.imperialm.imiservices.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class TopTenChart{
	List<TileAttribute1> attribute;
	TopTenDataTable top10_advisors;
	TopTenDataTable top10_technicians;
	List<TopTenTableData> top3;
	
	
	public TopTenChart(){
		this.attribute = new ArrayList<TileAttribute1>();
		this.top10_advisors = null;
		this.top10_technicians = null;
		this.top3 = new ArrayList<TopTenTableData>();
	}
	
	/*public TopTenChart(String buttonName){
		this.attribute = new ArrayList<TileAttribute1>();
		this.datatable = new TopTenDataTable(buttonName);
	}*/
	
	public Boolean addAttribute(TileAttribute1 attribute){
		try{
			this.attribute.add(attribute);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
}
