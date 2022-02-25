package com.citius.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.citius.userentities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

}
