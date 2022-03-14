package com.citius.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citius.dto.DoctorDTO;
import com.citius.services.ShiftService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(description = "Controller For Doctor Shift Operations", name = "Shifts Controller")
public class ShiftsController {

	@Autowired
	private ShiftService shiftService;

	@GetMapping("/shifts/{doctorId}")
	@Operation(summary = "Get Doctor shifts by doctor id")
	public DoctorDTO getShifts(@RequestParam("doctorId") int id) {
		return shiftService.getShifts(id);
	}

	@PostMapping("/doctorShifts")
	@Operation(summary = "Create/Add Doctor Shifts")
	public ResponseEntity<?> createDoctorWithShifts(@RequestBody DoctorDTO doctor) {
		String res =  shiftService.createDoctorShift(doctor);		
		if (res.equalsIgnoreCase("Success"))
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/doctorShifts/{doctorId}")
	@Operation(summary = "Update or change in doctor shift")
	public DoctorDTO updateDoctorData(@RequestBody DoctorDTO doctor, @RequestParam("doctorId") int id) {
		return shiftService.updateDoctorShift(doctor, id);
	}

	@DeleteMapping("/shifts/{doctorId}")
	@Operation(summary = "Delete doctor shifts")
	public ResponseEntity<?> deleteDoctorData(@RequestParam("doctorId") int id) {
		String res = shiftService.deleteDoctorShift(id);
		if (res.equalsIgnoreCase("Success"))
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
