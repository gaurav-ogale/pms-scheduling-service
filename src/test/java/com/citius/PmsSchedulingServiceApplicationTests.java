package com.citius;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.citius.entities.User;
import com.citius.model.AppointmentSlots;
import com.citius.model.Doctor;
import com.citius.model.DoctorShifts;
import com.citius.repository.AppointmentSlotRepo;
import com.citius.repository.DoctorRepository;
import com.citius.repository.UsersRepository;

@SpringBootTest
class PmsSchedulingServiceApplicationTests {

	@Autowired
	UsersRepository userRepo;

	@Autowired
	DoctorRepository drRepo;

	@Autowired
	AppointmentSlotRepo appRepo;

	@Test
	void contextLoads() {
	}

	/*
	 * @Test void addSchedule() { DateWiseSchedule dateSchedule = new
	 * DateWiseSchedule();
	 * 
	 * DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:m:s", Locale.ENGLISH);
	 * ArrayList<TimeSlots> workTime1 = new ArrayList<>(); workTime1.add(new
	 * TimeSlots(LocalTime.parse("8:30:00", dtf), LocalTime.parse("10:00:00",
	 * dtf))); TimeSlots slot = new TimeSlots(LocalTime.parse("8:30:00", dtf),
	 * LocalTime.parse("10:00:00", dtf));
	 * 
	 * ArrayList<TimeSlots> breakTime1 = new ArrayList<>(); breakTime1.add(new
	 * TimeSlots(LocalTime.parse("9:00:00", dtf), LocalTime.parse("9:30:00", dtf)));
	 * 
	 * LocalDate today = LocalDate.now();
	 * 
	 * DayOfWeek day = today.getDayOfWeek();
	 * 
	 * dateSchedule.setBreakSlots(breakTime1); dateSchedule.setDate(today);
	 * dateSchedule.setTimeSlots(slot); dateSchedule.setDayOfWeek(day);
	 * 
	 * WeekSchedule weekSchedule = new WeekSchedule();
	 * weekSchedule.setFirstDateWiseSchedule(dateSchedule);
	 * 
	 * shiftRepo.save(weekSchedule); }
	 * 
	 * @Test void scheduleAppointment() {
	 * 
	 * ArrayList<TimeSlots> aval = new ArrayList<>(); Appointments appointment = new
	 * Appointments(); WeekSchedule weekSchedule = new WeekSchedule();
	 * Set<AppointmentSlot> appSlot = new HashSet<>();
	 * 
	 * System.out.println(shiftRepo.findById(129).get());
	 * 
	 * // aval = weekSchedule.getFirstDateWiseSchedule().getTimeSlots(); // //
	 * aval.stream().forEach(slot -> { // AppointmentSlot sl = new
	 * AppointmentSlot(); // sl.setAppointmentStatus("AVAILABLE"); //
	 * sl.setAppointmentTime(new TimeSlots(slot.getStartTime(), slot.getEndTime()));
	 * // sl.setIsBreakSlot(false); // appSlot.add(sl); // }); // //
	 * appointment.setAppointmentSlots(appSlot); // appointment.setProviderId(1L);
	 * // appointment.setProviderId(1L); // // app.save(appointment);
	 */
	// }

	@Test
	void addAppointment() {
		User user = userRepo.getById(78L);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:m:s", Locale.ENGLISH);
		
		HashSet<AppointmentSlots> slots = new HashSet<>();
		
		HashSet<DoctorShifts> shifts = new HashSet<DoctorShifts>();
		
		Doctor dtr = new Doctor(user, "TEST", shifts, slots);
		
		AppointmentSlots slot1 = new AppointmentSlots(LocalTime.parse("8:30:00", dtf), LocalTime.parse("10:00:00", dtf),
				LocalDate.now(), dtr);
				
		AppointmentSlots slot2 = new AppointmentSlots(LocalTime.parse("8:30:00", dtf), LocalTime.parse("10:00:00", dtf),
						LocalDate.now(), dtr);
		slots.add(slot1);
		slots.add(slot2);
		
		DoctorShifts shift1 = new DoctorShifts(LocalDate.now(), LocalTime.parse("8:30:00", dtf), LocalTime.parse("15:00:00", dtf),
				dtr);
		DoctorShifts shift2 = new DoctorShifts(LocalDate.of(2022, 2, 18), LocalTime.parse("8:30:00", dtf), LocalTime.parse("15:00:00", dtf),
				dtr);
		shifts.add(shift1);
		shifts.add(shift2);
		
		drRepo.save(dtr);

	}

}
