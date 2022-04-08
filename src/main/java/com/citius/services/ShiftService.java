package com.citius.services;

import java.util.List;

import com.citius.dto.DoctorDTO;
import com.citius.model.Doctor;

public interface ShiftService {
	public DoctorDTO getShifts(long id);

	public String createDoctorShift(DoctorDTO doctor);

	public DoctorDTO updateDoctorShift(DoctorDTO doctor, long id);

	public String deleteDoctorShift(long doctor_id);
	
	public Doctor getDoctor(long doctor_id);
	
	public List<String> getAllSpecialization();
	
	public List<DoctorDTO> getDoctorFromSpecialization(String specialization);
}
