package com.citius.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiftsController {
	
	@GetMapping("/shifts/{doctorId}")
	public void getShifts(@RequestParam("doctorId") int id) {
		
	}

}
