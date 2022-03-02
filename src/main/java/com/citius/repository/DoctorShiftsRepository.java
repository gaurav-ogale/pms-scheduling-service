package com.citius.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.citius.model.DoctorShifts;

public interface DoctorShiftsRepository extends JpaRepository<DoctorShifts, Long> {
	
	@Query(value = "select * from doctor_shifts ds where ds.doctor_id = :id", nativeQuery = true)
	List<DoctorShifts> getShiftsByDoctorId(long id);

}
