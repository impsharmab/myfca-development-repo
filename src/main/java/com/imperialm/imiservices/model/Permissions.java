package com.imperialm.imiservices.model;

import java.io.Serializable;
import java.util.List;

public class Permissions implements Serializable {
	private String positionCode;
	private List<Boolean> permissions;
	private String role;
}
