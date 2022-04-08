package com.citius.services;

import java.util.List;

import com.citius.dto.AppointmentDTO;

public interface AppointmentService {
	
	String addAppointment(AppointmentDTO appointment, long userId);
	List<AppointmentDTO> getAppointmentsByDoctorId(long doctor_id);
	String updateAppointment(AppointmentDTO appointment, long userId);
	String deleteAppointment(AppointmentDTO appointment, long userId);

}
