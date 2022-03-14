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
import com.citius.repository.AppointmentSlotRepo;
import com.citius.repository.UsersRepository;
import com.citius.userentities.User;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentSlotRepo appointmentRepo;

	@Autowired
	private UsersRepository userRepository;

	@Override
	public String addAppointment(AppointmentDTO appointment, long doctorId, long userId) {
		AppointmentSlots appointmentSlot = appointmentRepo.getAppointmentSlotData(
				LocalDate.parse(appointment.getAppointmentDate(), DateTimeFormatter.ISO_DATE),
				LocalTime.parse(appointment.getStartTime(), DateTimeFormatter.ISO_TIME), doctorId);
		User user = userRepository.getById(userId);
		if (appointmentSlot != null && user != null
				&& appointmentSlot.getAppointmentStatus().equalsIgnoreCase(AppointmentStatus.AVAILABLE.toString())) {
			appointmentSlot.setAppointmentStatus(AppointmentStatus.CONFIRMED.toString());
			appointmentSlot.setUser(user);
			appointmentRepo.save(appointmentSlot);
			return "Success";
		}else {
			throw new AppoinmentCreationException("User Not Present Or Slots are Invalid");
		}
	}

	@Override
	public List<AppointmentDTO> getAppointmentsByDoctorId(long doctor_id) {
		List<AppointmentSlots> apptList = appointmentRepo.getAppointmentsByDoctorId(doctor_id,
				AppointmentStatus.AVAILABLE.toString(), LocalDate.now());
		List<AppointmentDTO> apptDTOList = dbToJson(apptList);
		return apptDTOList;

	}

	private List<AppointmentDTO> dbToJson(List<AppointmentSlots> apptList) {
		List<AppointmentDTO> apptDTOList = new ArrayList<AppointmentDTO>();

		for (AppointmentSlots slot : apptList) {
			AppointmentDTO appointment = new AppointmentDTO();
			appointment.setAppointmentDate(slot.getAppointmentDate().toString());
			appointment.setStartTime(slot.getStartTime().toString());
			appointment.setEndTime(slot.getEndTime().toString());
			appointment.setAppointmentStatus(slot.getAppointmentStatus());
			apptDTOList.add(appointment);
		}
		return apptDTOList;
	}

}
