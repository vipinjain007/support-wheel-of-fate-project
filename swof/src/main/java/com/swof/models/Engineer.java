package com.swof.models;

public class Engineer {
	
	private String id;
	private String name;
	
	//Below parameter use in schedule API response
	private String shiftDayDate;
	private String shiftDayTime;
	private String shiftDay;
	private String shiftNightDate;
	private String shiftNightTime;
	private String shiftNight;
	
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShiftDayDate() {
		return shiftDayDate;
	}
	public void setShiftDayDate(String shiftDayDate) {
		this.shiftDayDate = shiftDayDate;
	}
	public String getShiftDayTime() {
		return shiftDayTime;
	}
	public void setShiftDayTime(String shiftDayTime) {
		this.shiftDayTime = shiftDayTime;
	}
	public String getShiftNightDate() {
		return shiftNightDate;
	}
	public void setShiftNightDate(String shiftNightDate) {
		this.shiftNightDate = shiftNightDate;
	}
	public String getShiftNightTime() {
		return shiftNightTime;
	}
	public void setShiftNightTime(String shiftNightTime) {
		this.shiftNightTime = shiftNightTime;
	}
	public String getShiftDay() {
		return shiftDay;
	}
	public void setShiftDay(String shiftDay) {
		this.shiftDay = shiftDay;
	}
	public String getShiftNight() {
		return shiftNight;
	}
	public void setShiftNight(String shiftNight) {
		this.shiftNight = shiftNight;
	}
	
}
