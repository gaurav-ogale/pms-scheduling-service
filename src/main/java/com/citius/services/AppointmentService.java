package com.citius.services;

import java.util.List;

import com.citius.dto.AppointmentDTO;

public interface AppointmentService {
	
	String addAppointment(AppointmentDTO appointment, long doctorId, long userId);
	List<AppointmentDTO> getAppointmentsByDoctorId(long doctor_id);

}
