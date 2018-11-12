package com.fbl.work.domain;

import java.io.Serializable;

public class School implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String districtName;
	private String schoolName;
	private String state;
	
	public School() {
		
	}
	
	public School(String districtName, String schoolName, String state)
	{
		this.districtName = districtName;
		this.schoolName = schoolName;
		this.state = state;
	}
	
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
