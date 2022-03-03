package com.citius.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.citius.userentities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long doctor_id;

	@OneToOne
	@JoinColumn(name = "userId")
	private User user;

	private String specialization;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
	private Set<DoctorShifts> shifts = new HashSet<DoctorShifts>();

	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "doctor")
	private Set<AppointmentSlots> appointments = new HashSet<AppointmentSlots>();

	public Doctor(User user, String specialization, Set<DoctorShifts> shifts) {
		super();
		this.user = user;
		this.specialization = specialization;
		this.shifts = shifts;
		this.appointments = setAllDefaultAppointments(this.getShifts(), this.appointmentSlots);
	}

	public void setDefaultAppointments(Set<DoctorShifts> shifts) {
		this.appointments = setAllDefaultAppointments(shifts, this.appointmentSlots);
	}

	private Set<AppointmentSlots> setAllDefaultAppointments(Set<DoctorShifts> drShifts,
			Function<DoctorShifts, Set<AppointmentSlots>> appointmentSlotFunc) {
		Set<AppointmentSlots> defaultAppointments = new HashSet<>();
		drShifts.forEach(shift -> {
			Set<AppointmentSlots> slot = appointmentSlotFunc.apply(shift);
			defaultAppointments.addAll(slot);
		});
		return defaultAppointments;
	}

	@Transient
	@JsonIgnore
	private Function<DoctorShifts, Set<AppointmentSlots>> appointmentSlots = doctorShift -> {
		LocalTime startTime = doctorShift.getShiftStartTime();
		LocalTime endTime = doctorShift.getShiftEndTime();

		int gapInMinutes = 60;
		int loops = ((int) Duration.between(startTime, endTime).toMinutes() / gapInMinutes);
		Set<AppointmentSlots> slotSet = new HashSet<AppointmentSlots>();
		LocalTime time = startTime;
		for (int i = 1; i <= loops; i++) {
			AppointmentSlots slot = new AppointmentSlots();
			slot.setStartTime(time);
			time = time.plusMinutes(gapInMinutes);
			slot.setEndTime(time);

			slot.setAppointmentDate(doctorShift.getShiftDate());
			slot.setAppointmentStatus("AVAILABLE");
			slot.setDoctor(doctorShift.getDoctor());
			slotSet.add(slot);
		}
		return slotSet;
	};

	public long getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(long doctor_id) {
		this.doctor_id = doctor_id;
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

//	@Override
//	public String toString() {
//		return "Doctor [doctor_id=" + doctor_id + ", user=" + user + ", specialization=" + specialization + ", shifts="
//				+ shifts + ", appointments=" + appointments + ", appointmentSlots=" + appointmentSlots + "]";
//	}
	
	

}
