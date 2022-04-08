package com.citius.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.citius.dto.AppointmentDTO;
import com.citius.services.AppointmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(description = "Controller For Appointment Scheduling", name = "Appointment Controller")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("/appointments/{doctorId}")
	@Operation(summary = "Get All Available appointments for a doctor")
	public List<AppointmentDTO> getAppointmentsByDoctorId(@PathVariable("doctorId") String doctor_id) {
		return appointmentService.getAppointmentsByDoctorId(Long.parseLong(doctor_id));
	}

	@PostMapping("/appointments")
	@Operation(summary = "Schedule New Appointment")
	public ResponseEntity<?> createDoctorWithShifts(@RequestBody AppointmentDTO appointment) {
		String res = appointmentService.addAppointment(appointment, appointment.getUserId());
		if (res.equalsIgnoreCase("Success")) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return (ResponseEntity<?>) ResponseEntity.internalServerError();
	}

	@PutMapping("/appointments")
	@Operation(summary = "Update Appointment")
	public ResponseEntity<?> updateAppointment(@RequestBody AppointmentDTO appointment) {

		String res = appointmentService.updateAppointment(appointment, appointment.getUserId());
		if (res.equalsIgnoreCase("Success")) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return (ResponseEntity<?>) ResponseEntity.internalServerError();
	}

	@PutMapping("/removeAppointments")
	@Operation(summary = "Delete Appointment")
	public ResponseEntity<?> deleteAppointment(@RequestBody AppointmentDTO appointment) {

		String res = appointmentService.deleteAppointment(appointment, appointment.getUserId());
		if (res.equalsIgnoreCase("Success")) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return (ResponseEntity<?>) ResponseEntity.internalServerError();
	}

}
