package com.twistlet.falcon.controller.bean;

import java.util.List;

public class Appointment {
	
	Integer id;
	
	String appointmentDate;
	
	String appointmentTime;
	
	String appointmentTimeEnd;
	
	String location;
	
	String staff;
	
	List<String> patrons;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public List<String> getPatrons() {
		return patrons;
	}

	public void setPatrons(List<String> patrons) {
		this.patrons = patrons;
	}

	public String getAppointmentTimeEnd() {
		return appointmentTimeEnd;
	}

	public void setAppointmentTimeEnd(String appointmentTimeEnd) {
		this.appointmentTimeEnd = appointmentTimeEnd;
	}
	
	

}
