package com.imperialm.imiservices.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TopTenChart{
	List<TileAttribute1> attribute;
	TopTenDataTable datatable;
	
	
	public TopTenChart(){
		this.attribute = new ArrayList<TileAttribute1>();
		this.datatable = null;
	}
	
	public TopTenChart(String buttonName, String title){
		this.attribute = new ArrayList<TileAttribute1>();
		this.datatable = new TopTenDataTable(buttonName, title);
	}
	
	public Boolean addAttribute(TileAttribute1 attribute){
		try{
			this.attribute.add(attribute);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
}
