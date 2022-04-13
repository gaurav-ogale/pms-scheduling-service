package com.citius.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citius.dto.AppointmentDTO;
import com.citius.exception.AppoinmentCreationException;
import com.citius.model.AppointmentSlots;
import com.citius.model.AppointmentStatus;
import com.citius.model.Doctor;
import com.citius.repository.AppointmentSlotRepo;
import com.citius.repository.DoctorRepository;
import com.citius.repository.UsersRepository;
import com.citius.userentities.User;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentSlotRepo appointmentRepo;

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public String addAppointment(AppointmentDTO appointment, long userId, long doctorId) {
		Doctor doctor = doctorRepository.findById(doctorId).get();
		AppointmentSlots appointmentSlot = appointmentRepo.getAppointmentSlotData(
				LocalDate.parse(appointment.getAppointmentDate(), DateTimeFormatter.ISO_DATE),
				LocalTime.parse(appointment.getStartTime(), DateTimeFormatter.ISO_TIME), doctor.getDoctor_id());
		User user = userRepository.getById(userId);
		if (appointmentSlot != null && user != null
				&& appointmentSlot.getAppointmentStatus().equalsIgnoreCase(AppointmentStatus.AVAILABLE.toString())) {
			appointmentSlot.setTitle(appointment.getTitle());
			appointmentSlot.setAppointmentStatus(AppointmentStatus.SCHEDULED.toString());
			appointmentSlot.setUser(user);
			appointmentRepo.save(appointmentSlot);
			return "Success";
		} else {
			throw new AppoinmentCreationException("User Not Present Or Slots are Invalid");
		}
	}

	@Override
	public List<AppointmentDTO> getAppointmentsByDoctorId(long doctor_id) {

		Doctor doctor = doctorRepository.getDctorIDFromUserId(doctor_id);

//		List<AppointmentSlots> apptList = appointmentRepo.getAppointmentsByDoctorId(doctor.getDoctor_id(),
//				AppointmentStatus.AVAILABLE.toString(), LocalDate.now());
		List<AppointmentSlots> apptList = appointmentRepo.getAllAppointmentsByDoctorId(doctor.getDoctor_id(),
				LocalDate.now());
		List<AppointmentDTO> apptDTOList = dbToJson(apptList);
		return apptDTOList;

	}

	private List<AppointmentDTO> dbToJson(List<AppointmentSlots> apptList) {
		List<AppointmentDTO> apptDTOList = new ArrayList<AppointmentDTO>();

		for (AppointmentSlots slot : apptList) {
			AppointmentDTO appointment = new AppointmentDTO();
			appointment.setTitle(slot.getTitle());
			appointment.setAppointmentDate(slot.getAppointmentDate().toString());
			appointment.setStartTime(slot.getStartTime().toString());
			appointment.setEndTime(slot.getEndTime().toString());
			appointment.setAppointmentStatus(slot.getAppointmentStatus());
			apptDTOList.add(appointment);
		}
		return apptDTOList;
	}

	@Override
	public String updateAppointment(AppointmentDTO appointment, long userId) {

		Doctor doctor = doctorRepository.getDctorIDFromUserId(userId);

		AppointmentSlots appointmentSlot = appointmentRepo.getAppointmentSlotData(
				LocalDate.parse(appointment.getAppointmentDate(), DateTimeFormatter.ISO_DATE),
				LocalTime.parse(appointment.getStartTime(), DateTimeFormatter.ISO_TIME), doctor.getDoctor_id());
		User user = userRepository.getById(userId);
		if (appointmentSlot != null && user != null) {
			appointmentSlot.setTitle(appointment.getTitle());
			appointmentSlot.setAppointmentStatus(appointment.getAppointmentStatus());
			appointmentSlot.setUser(user);
			appointmentRepo.save(appointmentSlot);
			return "Success";
		} else {
			throw new AppoinmentCreationException("User Not Present Or Slots are Invalid");
		}
	}

	@Override
	public String deleteAppointment(AppointmentDTO appointment, long userId) {
		Doctor doctor = doctorRepository.getDctorIDFromUserId(userId);
		AppointmentSlots appointmentSlot = appointmentRepo.getAppointmentSlotData(
				LocalDate.parse(appointment.getAppointmentDate(), DateTimeFormatter.ISO_DATE),
				LocalTime.parse(appointment.getStartTime(), DateTimeFormatter.ISO_TIME), doctor.getDoctor_id());
		User user = userRepository.getById(userId);
		if (appointmentSlot != null && user != null) {
			appointmentSlot.setTitle(null);
			appointmentSlot.setAppointmentStatus(AppointmentStatus.AVAILABLE.toString());
			appointmentSlot.setUser(null);
			appointmentRepo.save(appointmentSlot);
			return "Success";
		} else {
			throw new AppoinmentCreationException("User Not Present Or Slots are Invalid");
		}
	}

	@Override
	public List<AppointmentDTO> getAppointmentsByDoctorIdAndDate(long doctor_id, LocalDate date) {
		List<AppointmentSlots> apptList = appointmentRepo.getAllAppointmentsByDoctorIdAndDate(doctor_id, date,
				AppointmentStatus.AVAILABLE.toString());
		return dbToJson(apptList);
	}

	@Override
	public List<AppointmentDTO> getUserAppointments(long userId, boolean isPast) {
		List<AppointmentSlots> apptList = new ArrayList<AppointmentSlots>();
		if (isPast) {
			apptList = appointmentRepo.getPastUserAppointments(userId, LocalDate.now());
		} else {
			apptList = appointmentRepo.getFutureUserAppointments(userId, LocalDate.now());
		}
		return dbToJson(apptList);
	}

}
