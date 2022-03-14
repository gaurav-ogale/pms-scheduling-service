package com.citius.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.citius.dto.DoctorDTO;
import com.citius.model.Doctor;
import com.citius.model.DoctorShifts;
import com.citius.repository.DoctorRepository;
import com.citius.repository.DoctorShiftsRepository;
import com.citius.repository.UsersRepository;
import com.citius.userentities.User;

class ShiftServiceImplTest {

	@InjectMocks
	private ShiftServiceImpl shiftService;

	@Mock
	private DoctorRepository doctorRepository;

	@Mock
	private DoctorShiftsRepository drShiftRepository;

	@Mock
	private UsersRepository userRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetShifts() {
		User user = new User();
		user.setUserId(1L);
		user.setUserFirstName("Gaurav");
		user.setUserLastName("Test");
		Doctor doctor = new Doctor();
		doctor.setUser(user);
		doctor.setSpecialization("Test");
		List<DoctorShifts> drShifts = new ArrayList<>();
		drShifts.add(new DoctorShifts(1L, LocalDate.now(), LocalTime.of(07, 00), LocalTime.of(9, 00), doctor));
		Mockito.when(drShiftRepository.getShiftsByDoctorId(ArgumentMatchers.anyLong())).thenReturn(drShifts);
		DoctorDTO drDto = shiftService.getShifts(1L);
		assertEquals("2022-03-07", drDto.getShifts().stream().findFirst().get().getShiftDate());

	}

	@Test
	void testCreateDoctorShift() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateDoctorShift() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteDoctorShift() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDoctor() {
		fail("Not yet implemented");
	}

}
