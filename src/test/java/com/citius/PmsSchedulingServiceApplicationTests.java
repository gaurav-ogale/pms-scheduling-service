package com.citius;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.citius.dto.DoctorShiftDetails;
import com.citius.model.AppointmentSlots;
import com.citius.model.AppointmentStatus;
import com.citius.model.Doctor;
import com.citius.repository.AppointmentSlotRepo;
import com.citius.repository.DoctorRepository;
import com.citius.repository.UsersRepository;
import com.citius.services.ShiftService;

@SpringBootTest
class PmsSchedulingServiceApplicationTests {

	@Autowired
	UsersRepository userRepo;

	@Autowired
	DoctorRepository drRepo;

	@Autowired
	AppointmentSlotRepo appRepo;

	@Autowired
	DoctorRepository drShiftRepo;

	@Autowired
	private ShiftService shiftService;

	@Test
	void contextLoads() {
	}

//	@Test
//	void addDoctorShift() {
//		Set<DoctorShifts> drShifts = new HashSet<>();
//		Doctor dr = new Doctor();
//		DoctorShifts shift = new DoctorShifts(LocalDate.now(), LocalTime.of(06, 00), LocalTime.of(15, 00), dr);
//		DoctorShifts shift2 = new DoctorShifts(LocalDate.of(2022, 2, 23), LocalTime.of(06, 00), LocalTime.of(15, 00),
//				dr);
//		drShifts.add(shift);
//		drShifts.add(shift2);
//		// Doctor dr1 = new Doctor(userRepo.getById(78L), "PHYSICIAN", drShifts);
//
//		dr.setDefaultAppointments(drShifts);
//		dr.setShifts(drShifts);
//		dr.setSpecialization("PHYSICIAN");
//		dr.setUser(userRepo.getById(78L));
//
//		drRepo.save(dr);
//
//	}

//	@Test
//	void addAppointment() {
//
//		LocalTime appointmentTime = LocalTime.of(10, 00);
//		LocalDate apptDate = LocalDate.of(2022, 2, 23);
//		Long doctorId = 197L;
//		Long userId = 76L;
//
//		Doctor doctor = drRepo.getById(doctorId);
//
//		doctor.getAppointments().stream().filter(
//				appt -> appt.getAppointmentDate().equals(apptDate) && appt.getStartTime().equals(appointmentTime))
//				.map(appt -> {
//					appt.setAppointmentStatus(AppointmentStatus.CONFIRMED.toString());
//					appt.setUser(userRepo.getById(userId));
//					System.out.println(appt.getId());
//					return appt;
//				}).forEach((appt) -> appRepo.save(appt));
//
////		for (AppointmentSlots appt : slots) {
////			if (appt.getAppointmentDate().equals(apptDate) && appt.getStartTime().equals(appointmentTime)) {
////				System.out.println(appt.getId());
////				appt.setAppointmentStatus(AppointmentStatus.CONFIRMED.toString());
////				appt.setUser(userRepo.getById(userId));
////				System.out.println(appt.getId());
////				appRepo.save(appt);
////				// slots.add(appt);
////				break;
////			}
////		}
//
//	}

	@Test
	void getShifts() {
		List<Doctor> shiftList = shiftService.getShifts(197L);

		shiftList.forEach(dr ->System.out.println(dr.toString()));
	}

}
