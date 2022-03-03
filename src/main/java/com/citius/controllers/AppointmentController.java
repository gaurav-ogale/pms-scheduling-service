package com.citius.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citius.dto.AppointmentDTO;
import com.citius.model.AppointmentSlots;
import com.citius.services.AppointmentService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(description = "Controller For Appointment Scheduling", name = "Appointment Controller")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("/appointments/{doctorId}")
	public List<AppointmentDTO> getAppointmentsByDoctorId(@RequestParam("doctorId") long doctor_id) {
		return appointmentService.getAppointmentsByDoctorId(doctor_id);
	}
	
	@PostMapping("/appointments")
	public ResponseEntity<?> createDoctorWithShifts(@RequestBody AppointmentDTO appointment,@RequestHeader("doctorId") long doctorId,@RequestHeader("userId") long userId) {
		String res =  appointmentService.addAppointment(appointment, doctorId, userId);		
		if (res.equalsIgnoreCase("Success"))
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
