package com.citius.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.citius.model.AppointmentSlots;

public interface AppointmentSlotRepo extends JpaRepository<AppointmentSlots, Long> {

	@Query(value = "from AppointmentSlots a where a.appointmentDate=:appointment_date AND a.startTime=:start_time AND doctor.doctor_id=:doctorId")
	AppointmentSlots getAppointmentSlotData(LocalDate appointment_date, LocalTime start_time, long doctorId);

	@Query(value = "from AppointmentSlots a where a.doctor.doctor_id=:doctor_id AND a.appointmentStatus=:status AND a.appointmentDate>=:today")
	List<AppointmentSlots> getAppointmentsByDoctorId(long doctor_id, String status, LocalDate today);
	
	@Query(value = "from AppointmentSlots a where a.doctor.doctor_id=:doctor_id AND a.appointmentDate>=:today")
	List<AppointmentSlots> getAllAppointmentsByDoctorId(long doctor_id, LocalDate today);
	
	@Query(value = "from AppointmentSlots a where a.user.userId=:user_id")
	List<AppointmentSlots> getAppointmentsByUserId(long user_id);
	
}
