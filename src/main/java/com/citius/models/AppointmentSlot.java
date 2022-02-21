package com.citius.models;

import java.io.Serializable;

public class AppointmentSlot implements Serializable{

	private TimeSlots appointmentTime;
	private String appointmentStatus;
	private Boolean isBreakSlot;

	public AppointmentSlot() {
		// TODO Auto-generated constructor stub
	}

	public TimeSlots getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(TimeSlots appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public Boolean getIsBreakSlot() {
		return isBreakSlot;
	}

	public void setIsBreakSlot(Boolean isBreakSlot) {
		this.isBreakSlot = isBreakSlot;
	}

}
