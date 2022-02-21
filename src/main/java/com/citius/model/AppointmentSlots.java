package com.citius.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.citius.entities.User;

@Entity
public class AppointmentSlots {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalTime startTime;
	private LocalTime endTime;
	private LocalDate appointmentDate;

	@ManyToOne
	private Doctor doctor;

	@OneToOne
	private User user;

	public AppointmentSlots() {
	}

	public AppointmentSlots(Long id, LocalTime startTime, LocalTime endTime, LocalDate appointmentDate, Doctor doctor,
			User user) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.appointmentDate = appointmentDate;
		this.doctor = doctor;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
