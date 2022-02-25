package com.citius.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DoctorShifts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDate shiftDate;
	private LocalTime shiftStartTime;
	private LocalTime shiftEndTime;

	@ManyToOne
	@JoinColumn(name = "doctorId")
	private Doctor doctor;

	public DoctorShifts() {
	}

	public DoctorShifts(LocalDate shiftDate, LocalTime shiftStartTime, LocalTime shiftEndTime, Doctor doctor) {
		super();
		this.shiftDate = shiftDate;
		this.shiftStartTime = shiftStartTime;
		this.shiftEndTime = shiftEndTime;
		this.doctor = doctor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getShiftDate() {
		return shiftDate;
	}

	public void setShiftDate(LocalDate shiftDate) {
		this.shiftDate = shiftDate;
	}

	public LocalTime getShiftStartTime() {
		return shiftStartTime;
	}

	public void setShiftStartTime(LocalTime shiftStartTime) {
		this.shiftStartTime = shiftStartTime;
	}

	public LocalTime getShiftEndTime() {
		return shiftEndTime;
	}

	public void setShiftEndTime(LocalTime shiftEndTime) {
		this.shiftEndTime = shiftEndTime;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

}
