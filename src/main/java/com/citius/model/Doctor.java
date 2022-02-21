package com.citius.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.citius.entities.User;

@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne
	@JoinColumn(name = "userId")
	private User user;

	private String specialization;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "doctor")
	private Set<DoctorShifts> shifts = new HashSet<DoctorShifts>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "doctor")
	private Set<AppointmentSlots> appointments = new HashSet<AppointmentSlots>();

	public Doctor() {
	}

	public Doctor(User user, String specialization, Set<DoctorShifts> shifts, Set<AppointmentSlots> appointments) {
		super();
		this.user = user;
		this.specialization = specialization;
		this.shifts = shifts;
		this.appointments = appointments;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public Set<DoctorShifts> getShifts() {
		return shifts;
	}

	public void setShifts(Set<DoctorShifts> shifts) {
		this.shifts = shifts;
	}

	public Set<AppointmentSlots> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<AppointmentSlots> appointments) {
		this.appointments = appointments;
	}

}
