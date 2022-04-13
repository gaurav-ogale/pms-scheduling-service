package com.citius.services;

import java.time.LocalDate;
import java.util.List;

import com.citius.dto.AppointmentDTO;

public interface AppointmentService {
	
	String addAppointment(AppointmentDTO appointment, long userId,long doctorId);
	List<AppointmentDTO> getAppointmentsByDoctorId(long doctor_id);
	String updateAppointment(AppointmentDTO appointment, long userId);
	String deleteAppointment(AppointmentDTO appointment, long userId);
	List<AppointmentDTO> getAppointmentsByDoctorIdAndDate(long doctor_id,LocalDate date);
	List<AppointmentDTO> getUserAppointments(long userId, boolean isPast);	

}
