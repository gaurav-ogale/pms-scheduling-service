package com.citius.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citius.dto.DoctorDTO;
import com.citius.dto.DoctorShiftDTO;
import com.citius.model.Doctor;
import com.citius.model.DoctorShifts;
import com.citius.repository.DoctorRepository;
import com.citius.repository.DoctorShiftsRepository;
import com.citius.repository.UsersRepository;
import com.citius.userentities.User;

@Service
public class ShiftServiceImpl implements ShiftService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private DoctorShiftsRepository drShiftRepository;

	@Override
	public DoctorDTO getShifts(long id) {
		List<DoctorShifts> shiftList = drShiftRepository.getShiftsByDoctorId(id);
		dbToJson(shiftList);
		return dbToJson(shiftList);
	}

	private DoctorDTO dbToJson(List<DoctorShifts> shiftList) {
		DoctorDTO st = new DoctorDTO();
		Set<DoctorShiftDTO> list = new HashSet<DoctorShiftDTO>();
		for (DoctorShifts shift : shiftList) {
			DoctorShiftDTO drShift = new DoctorShiftDTO();
			drShift.setId(shift.getId());
			drShift.setShiftDate(shift.getShiftDate().toString());
			drShift.setShiftStartTime(shift.getShiftStartTime().toString());
			drShift.setShiftEndTime(shift.getShiftEndTime().toString());
			list.add(drShift);
			st.setUserId(shift.getDoctor().getUser().getUserId());
			st.setSpecialization(shift.getDoctor().getSpecialization());
			st.setUser(shift.getDoctor().getUser());
		}
		st.setShifts(list);

		return st;
	}

	@Override
	public String createDoctorShift(DoctorDTO doctorDto) {
		Doctor doctor = new Doctor();
		User user = userRepository.getById(doctorDto.getUserId());
		Doctor userDr = doctorRepository.findByUser(user);
		Set<DoctorShifts> drShifts = new HashSet<>();
		if (user != null && userDr == null) {
			doctorDto.getShifts().forEach(shift -> {
				DoctorShifts drShift = new DoctorShifts();
				drShift.setDoctor(doctor);
				drShift.setShiftDate(LocalDate.parse(shift.getShiftDate(), DateTimeFormatter.ISO_DATE));
				drShift.setShiftStartTime(LocalTime.parse(shift.getShiftStartTime(), DateTimeFormatter.ISO_TIME));
				drShift.setShiftEndTime(LocalTime.parse(shift.getShiftEndTime(), DateTimeFormatter.ISO_TIME));
				drShifts.add(drShift);
			});
			doctor.setDefaultAppointments(drShifts);
			doctor.setUser(user);
			doctor.setShifts(drShifts);
			doctor.setSpecialization(doctorDto.getSpecialization());
			doctorRepository.save(doctor);
			return "Success";
		}
		return "Error";

	}

	@Override
	public DoctorDTO updateDoctorShift(DoctorDTO doctor, long id) {
		Doctor doctorFromDb = doctorRepository.getById(id);
		if (Objects.nonNull(doctorFromDb.getShifts()) && !doctorFromDb.getShifts().equals(doctor.getShifts())) {
			Set<DoctorShifts> drShifts = new HashSet<>();
			doctor.getShifts().forEach(shift -> {
				DoctorShifts drShift = new DoctorShifts();
				drShift.setDoctor(doctorFromDb);
				drShift.setShiftDate(LocalDate.parse(shift.getShiftDate(), DateTimeFormatter.ISO_DATE));
				drShift.setShiftStartTime(LocalTime.parse(shift.getShiftStartTime(), DateTimeFormatter.ISO_TIME));
				drShift.setShiftEndTime(LocalTime.parse(shift.getShiftEndTime(), DateTimeFormatter.ISO_TIME));
				drShifts.add(drShift);
			});
			doctorFromDb.setDefaultAppointments(drShifts);
			doctorFromDb.setShifts(drShifts);
		}
		if (Objects.nonNull(doctorFromDb.getSpecialization())
				&& !doctorFromDb.getSpecialization().equalsIgnoreCase(doctor.getSpecialization())) {
			doctorFromDb.setSpecialization(doctor.getSpecialization());
		}
		doctorRepository.save(doctorFromDb);
		return doctor;
	}

	@Override
	public String deleteDoctorShift(long doctor_id) {
		if (doctorRepository.getById(doctor_id) != null)
			doctorRepository.deleteById(doctor_id);
		return "Success";
	}

	@Override
	public Doctor getDoctor(long doctor_id) {
		return doctorRepository.findById(doctor_id).get();
	}

}
