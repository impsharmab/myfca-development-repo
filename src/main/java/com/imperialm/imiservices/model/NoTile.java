package com.imperialm.imiservices.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoTile {
	private int id;
	private String error;
	private String type;
	private String tileName;
	private String tileHeaderImage;
	private String tileURL;
	
	
	public NoTile(int id, String error, String type, String tileName, String tileHeaderImage, String tileURL){
		this.id = id;
		this.error = error;
		this.type = type;
		this.tileName = tileName;
		this.tileHeaderImage = tileHeaderImage;
		this.tileURL = tileURL;
	}
}
