package com.citius.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.citius.dto.DoctorShiftDetails;
import com.citius.services.ShiftService;
import com.citius.services.ShiftServiceImpl;

public class ShiftServiceTest {

	@Autowired
	private ShiftService shiftService;

//	@Test
//	void getShifts() {
//		List<DoctorShiftDetails> shiftList= shiftService.getShifts(197L);
//		
//		shiftList.forEach(System.out::println);
//	}

}
