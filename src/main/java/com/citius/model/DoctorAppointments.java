package com.citius.model;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.citius.entities.User;
import com.citius.entities.UserGroup;

public class DoctorAppointments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private Doctor doctor;

	@OneToMany
	private AppointmentSlots slot;

}
