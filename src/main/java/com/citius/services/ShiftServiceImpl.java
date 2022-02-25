package com.citius.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citius.dto.DoctorShiftDetails;
import com.citius.model.Doctor;
import com.citius.repository.DoctorRepository;

@Service
public class ShiftServiceImpl implements ShiftService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public List<Doctor> getShifts(long id) {
		List<Doctor> shiftList = doctorRepository.getDoctorShifts(id);
		return shiftList;
	}

}
