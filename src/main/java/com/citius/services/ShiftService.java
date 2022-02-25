package com.citius.services;

import java.util.List;

import com.citius.dto.DoctorShiftDetails;
import com.citius.model.Doctor;

public interface ShiftService {
	public List<Doctor> getShifts(long id);
}
