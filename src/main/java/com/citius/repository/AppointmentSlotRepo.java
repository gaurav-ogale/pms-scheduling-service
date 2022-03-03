package com.citius.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.citius.model.AppointmentSlots;

public interface AppointmentSlotRepo extends JpaRepository<AppointmentSlots, Long> {

	@Query(value = "select a from AppointmentSlots a where a.appointmentDate=:appointment_date AND a.startTime=:start_time AND doctor.doctor_id=:doctorId")
	AppointmentSlots getAppointmentSlotData(LocalDate appointment_date, LocalTime start_time, long doctorId);

	@Query(value = "select * from appointment_slots where doctor_id=:doctor_id AND appointment_status=:status", nativeQuery = true)
	List<AppointmentSlots> getAppointmentsByDoctorId(long doctor_id, String status);

}
